package com.karimou.data.rest.api;

import com.karimou.data.rest.api.infrastructure.Account;
import com.karimou.data.rest.api.infrastructure.AccountRepository;
import com.karimou.data.rest.api.infrastructure.Holder;
import com.karimou.data.rest.api.infrastructure.HolderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataRestApiApplication.class, args);
    }



    @Bean
    CommandLineRunner onStartPopulateDatabase(AccountRepository accountRepository, HolderRepository holderRepository){
        return args ->{

            Holder karim = Holder.builder()
                    .firstName("karim")
                    .lastName("essouabni")
                    .build();

            Account account = Account.builder()
                    .tag("eco Account")
                    .build();

            karim.addAccount(account);

            holderRepository.save(karim);

        };

    }
}
