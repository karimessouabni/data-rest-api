package com.karimou.data.rest.api;


import com.karimou.data.rest.api.infrastructure.*;
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
public class TransferAmountTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HolderRepository holderRepository;

    @Autowired
    TransferRepository transferRepository;

    private Holder karimTransmitter;

    private Account karimAccount;

    private Holder fatiReceiver;

    private Account fatiAccount;

    private Transfer transfer300$;


    @Before
    public  void prepareTest(){

        karimTransmitter = Holder.builder()
                .firstName("kairm")
                .lastName("essouabni")
                .build();

        karimAccount = Account.builder()
                .tag("SG karim account")
                .build();

        fatiReceiver = Holder.builder()
                .firstName("fati")
                .lastName("mounaji")
                .build();

        fatiAccount = Account.builder()
                .tag("Eco fati account")
                .build();

        transfer300$ = Transfer.builder()
                .amount(300)
                .build();

        karimTransmitter.addAccount(karimAccount);
        fatiReceiver.addAccount(fatiAccount);

        holderRepository.save(fatiReceiver); // saving receiver account first

        karimAccount.makeTransfer(transfer300$, fatiAccount);

        holderRepository.save(karimTransmitter); // saving karim holder, its account, and the transfer.


    }

    @Test
    public void transferAmountBetweenAccountTest(){

        Holder karimFromDb = holderRepository.findByAccountsContains(karimAccount).get(0);

        Account dbKarimAccount = accountRepository.findByHolder(karimTransmitter);

        Account dbFatiAccount = accountRepository.findByHolder(fatiReceiver);


        Transfer dbTransfer300 = transferRepository.findByTransmitterAcc(karimAccount);


        assertEquals(karimFromDb, karimTransmitter);

        assertEquals(dbKarimAccount.getTransfersOut().toArray()[0], transfer300$ );

        assertEquals(dbFatiAccount.getTransfersIn().toArray()[0], transfer300$);


        assertEquals(dbTransfer300, transfer300$);
        assertEquals(dbTransfer300.getReceiverAcc(), fatiAccount);


    }



}
