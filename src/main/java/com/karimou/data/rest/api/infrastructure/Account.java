package com.karimou.data.rest.api.infrastructure;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(exclude = {"transfersOut", "transfersIn"})
public class Account {

    @Id
    @GeneratedValue
    private Integer id ;


    private String tag ;

    //ManyToOne is by default eager
    @ManyToOne
    @JoinColumn
    @ToString.Exclude // resolving debugging stackOverFlow Exception
    private Holder holder;


    @OneToMany(mappedBy = "transmitterAcc", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Transfer> transfersOut = new HashSet<>();

    @OneToMany(mappedBy = "receiverAcc", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Transfer> transfersIn = new HashSet<>();

    public void makeTransfer(Transfer transfer, Account receiverAccount) {
        this.transfersOut.add(transfer);
        receiverAccount.getTransfersIn().add(transfer);

        transfer.setTransmitterAcc(this);
        transfer.setReceiverAcc(receiverAccount);
    }

}
