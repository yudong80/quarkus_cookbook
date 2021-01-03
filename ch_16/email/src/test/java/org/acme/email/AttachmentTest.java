package org.acme.email;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.quarkus.mailer.Attachment;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class AttachmentTest {
    @Inject
    Mailer mailer;

    @Inject
    MockMailbox mbox;

    @BeforeEach
    void clearMBox() {
        mbox.clear();
    }

    // tag::attachment[]
    @Test
    void attachmentTest() throws Exception {
        final String mailTo = "test@example.org";
        final String testingSubject = "email with Attachment";
        final String html = "<strong>E-mail by:</strong>" + "\n" +
                "<p><img src=\"cid:logo@quarkus.io\"/></p>";    // <1>

        sendEmail(mailTo, testingSubject, html);

        Mail email = mbox.getMessagesSentTo(mailTo).get(0);
        List<Attachment> attachments = email.getAttachments();

        assertThat(email.getHtml()).isEqualTo(html);
        assertThat(attachments).hasSize(1);
        assertThat(attachments.get(0).getFile())
                .isEqualTo(new File(getAttachmentURI()));
    }

    private void sendEmail(String to, String subject, String body) 
          throws URISyntaxException {
        final File logo = new File(getAttachmentURI());

        Mail email = Mail.withHtml(to, subject, body)
                .addInlineAttachment("quarkus-logo.svg",
                        logo,
                        "image/svg+xml",
                        "<logo@quarkus.io>");   // <2>

        mailer.send(email);
    }
    // end::attachment[]

    private URI getAttachmentURI() throws URISyntaxException {
        return Objects.requireNonNull(AttachmentTest.class.getClassLoader()
                .getResource("META-INF/resources/quarkus_logo.svg"))
                .toURI();
    }
}
