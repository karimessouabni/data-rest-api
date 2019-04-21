package com.karimou.data.rest.api;


import com.karimou.data.rest.api.infrastructure.Account;
import com.karimou.data.rest.api.infrastructure.AccountRepository;
import com.karimou.data.rest.api.infrastructure.Holder;
import com.karimou.data.rest.api.infrastructure.HolderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankAccountTest {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HolderRepository holderRepository;

    @Test
    public void saveAccountTest(){

        Holder karim = Holder.builder()
                .firstName("kairm")
                .lastName("essouabni")
                .build();

        Account acc = Account.builder()
                .tag("SG account")
                .build();

        karim.addAccount(acc);

        holderRepository.save(karim);
        Holder karimFromDb = holderRepository.findByAccountsContains(acc).get(0);


        assertEquals(karimFromDb, karim);
    }

}
