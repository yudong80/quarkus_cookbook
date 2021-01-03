package org.acme.scheduling;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

@ApplicationScoped
public class Scheduler {

    private AtomicInteger count = new AtomicInteger();

    int get() {
        return count.get();
    }

    @Scheduled(every = "5s")
    void fiveSeconds(ScheduledExecution execution) {
        count.incrementAndGet();
        System.out.println("Running counter: 'fiveSeconds'. Next fire: "
                + execution.getTrigger().getNextFireTime());
    }
}
