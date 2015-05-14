package br.com.luizalabsDesafio;

import java.io.IOException;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author mh
 * @since 06.10.14
 */
//@Import(MyNeo4jConfiguration.class)
@RestController("/")
@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationBoot {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApplicationBoot.class, args);
    }



}
