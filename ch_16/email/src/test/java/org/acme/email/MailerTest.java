package org.acme.email;

import java.util.List;

import javax.inject.Inject;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class MailerTest {
    @Inject
    Mailer mailer;

    @Inject
    MockMailbox mbox;

    @BeforeEach
    void clearMBox() {
        mbox.clear(); // <1>
    }

    @Test
    public void assertBasicTextEmailSent() {
        final String mailTo = "test@example.org";
        final String testingSubject = "Testing email";
        final String testingBody = "Hello World!";

        mailer.send(Mail.withText(mailTo,
                testingSubject,
                testingBody));

        assertThat(mbox.getTotalMessagesSent()).isEqualTo(1); // <2>
        List<Mail> emails = mbox.getMessagesSentTo(mailTo); // <3>

        assertThat(emails).hasSize(1);
        Mail email = emails.get(0);

        assertThat(email.getSubject()).isEqualTo(testingSubject);
        assertThat(email.getText()).isEqualTo(testingBody);
    }
}
