package org.acme.email;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class ReactiveMailerTest {
    @Inject
    ReactiveMailer reactiveMailer;

    @Inject
    MockMailbox mbox;

    @BeforeEach
    void clearMbox() {
        mbox.clear();
    }

    @Test
    public void testReactiveEmail() throws Exception {
        final String mailTo = "test@example.org";
        final String testingSubject = "Testing email";
        final String testingBody = "Hello World!";
        final CountDownLatch latch = new CountDownLatch(1);

        reactiveMailer.send(Mail.withText(mailTo,
                testingSubject,
                testingBody)).subscribeAsCompletionStage().join();

        assertThat(mbox.getTotalMessagesSent()).isEqualTo(1);
        List<Mail> emails = mbox.getMessagesSentTo(mailTo);

        assertThat(emails).hasSize(1);
        Mail email = emails.get(0);

        assertThat(email.getSubject()).isEqualTo(testingSubject);
        assertThat(email.getText()).isEqualTo(testingBody);
    }
}
