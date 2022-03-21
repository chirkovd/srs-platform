package org.systems.dipe.srs.test;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.systems.dipe.srs.test")
public class SrsTestApplication {

    public static void main(String... args) {
        // TODO convert to command line test client
        // TODO create suite runner
        // TODO create test scripts
        new SpringApplicationBuilder()
                .sources(SrsTestApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .build()
                .run(args);
    }

}
