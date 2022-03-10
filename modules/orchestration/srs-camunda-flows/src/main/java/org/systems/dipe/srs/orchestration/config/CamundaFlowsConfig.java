package org.systems.dipe.srs.orchestration.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.systems.dipe.srs.orchestration.OrchestrationClient;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Timer;
import java.util.TimerTask;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaFlowsConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.orchestration")
    public static class Module {

        @Bean
        public ApplicationListener<ApplicationReadyEvent> listener(OrchestrationClient client) {
            return e -> {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        client.submitRequest(UuidUtils.newStr());
                    }
                }, 5_000);
            };
        }

    }

}
