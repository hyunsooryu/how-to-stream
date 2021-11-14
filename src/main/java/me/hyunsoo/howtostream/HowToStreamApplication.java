package me.hyunsoo.howtostream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HowToStreamApplication {

    public static void main(String[] args) {
        var app = new SpringApplication(HowToStreamApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
