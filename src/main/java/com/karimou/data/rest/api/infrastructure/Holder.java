package com.karimou.data.rest.api.infrastructure;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"books"})
public class Holder {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holder")
    @Builder.Default
    private Set<Account> books = new HashSet<>();

    public void addAccount(Account account) {
        this.books.add(account);
        account.setHolder(this);
    }
}
