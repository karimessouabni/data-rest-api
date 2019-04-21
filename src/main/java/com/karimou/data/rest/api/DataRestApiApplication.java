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
                    .holder(karim)
                    .build();

            karim.addAccount(account); // when Karim open an Account in our Bank

            holderRepository.save(karim); // this save karim and it added account in the DB

        };

    }
}
