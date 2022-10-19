package edu.hanoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication()
//@ImportResource("classpath:config.xml")
@ComponentScan(basePackages = {"edu.*"})
public class SpringClazzApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringClazzApplication.class, args);
    }
}
