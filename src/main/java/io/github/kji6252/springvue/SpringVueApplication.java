package io.github.kji6252.springvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class SpringVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVueApplication.class, args);
    }

}
