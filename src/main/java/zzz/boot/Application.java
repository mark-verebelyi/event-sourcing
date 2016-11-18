package zzz.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zzz.framework.command.EnableCommandInfrastructure;

@SpringBootApplication
@EnableCommandInfrastructure("zzz.boot.domain")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
