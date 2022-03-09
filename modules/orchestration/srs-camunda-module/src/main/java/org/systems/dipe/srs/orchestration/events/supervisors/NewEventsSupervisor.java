package org.systems.dipe.srs.orchestration.events.supervisors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.EventsProcessor;

import javax.annotation.PreDestroy;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Component
public class NewEventsSupervisor implements ApplicationListener<ApplicationReadyEvent> {

    private final Timer timer;
    private final EventsProcessor eventsProcessor;

    public NewEventsSupervisor(EventsProcessor eventsProcessor) {
        this.eventsProcessor = eventsProcessor;
        this.timer = new Timer("SRS-New-Events-Supervisor");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Start to process new events");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Thread.currentThread().isInterrupted()) {
                    timer.cancel();
                    return;
                }

                int events = eventsProcessor.evaluateUnprocessedEvents();
                if (events > 0) {
                    log.debug("Load and evaluate unprocessed events [{}]", events);
                }
            }
        }, 5_000, 5_000);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Shutdown events timer");
        timer.cancel();
    }

}
