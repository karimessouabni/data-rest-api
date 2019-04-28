package com.karimou.data.rest.api.infrastructure;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Transfer {

    @Id
    @GeneratedValue
    private Integer id ;

    private Integer amount;

    // ManyToOne is by default Eager
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Account transmitterAcc;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Account receiverAcc;





}
