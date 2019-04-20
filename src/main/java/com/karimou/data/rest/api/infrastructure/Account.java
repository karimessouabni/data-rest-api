package com.karimou.data.rest.api.infrastructure;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue
    private Integer id ;


    private String tag ;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude // resolving debugging stackOverFlow Exception
    private Holder holder;
}
