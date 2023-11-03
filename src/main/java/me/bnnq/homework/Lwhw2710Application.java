package me.bnnq.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("me.bnnq.homework.Repositories")
public class Lwhw2710Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Lwhw2710Application.class, args);
    }

}
