package com.karimou.data.rest.api;

import com.karimou.data.rest.api.infrastructure.*;
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

            Holder fati = Holder.builder()
                    .firstName("fati")
                    .lastName("mounaji")
                    .build();

            Account karimAccount = Account.builder()
                    .tag("eco Account")
                    .holder(karim)
                    .build();


            Account fatiAccount = Account.builder()
                    .tag("salary Account")
                    .holder(fati)
                    .build();

            Transfer transfer100Euros = Transfer.builder()
                    .amount(100)
                    .build();



            karim.addAccount(karimAccount); // when Karim open an Account in the Bank
            fati.addAccount(fatiAccount);// when Fati open an Account in the Bank
            holderRepository.save(fati); // fati shoul be saved before the Transfer. karim not necessary

            karimAccount.makeTransfer(transfer100Euros, fatiAccount); // karim : KarimAccnt  --100€--> FatiAccnt

            /**
             * Due to CASCADES this :
             * 1 first save the holder karim,
             * 2 then it save the karim account
             * 3 and save finally the transfer linking karim and Fati accounts
             */
            holderRepository.save(karim);



        };

    }
}
