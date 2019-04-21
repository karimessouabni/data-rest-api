package com.karimou.data.rest.api;


import com.karimou.data.rest.api.infrastructure.Account;
import com.karimou.data.rest.api.infrastructure.AccountRepository;
import com.karimou.data.rest.api.infrastructure.Holder;
import com.karimou.data.rest.api.infrastructure.HolderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HolderAccountTest {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HolderRepository holderRepository;

    private Holder karim;

    private Account acc;


    @Before
    public  void prepareTest(){

         karim = Holder.builder()
                .firstName("kairm")
                .lastName("essouabni")
                .build();

         acc = Account.builder()
                .tag("SG account")
                .build();

        karim.addAccount(acc);
        holderRepository.save(karim);

    }

    @Test
    public void findHolderByAccountTest(){


        Holder karimFromDb = holderRepository.findByAccountsContains(acc).get(0);

        assertEquals(karimFromDb, karim);
    }

    @Test
    public void findAccountByHolderTest(){


      Account accountsFound = accountRepository.findByHolder(karim);

        assertEquals(accountsFound, acc);
    }

}
