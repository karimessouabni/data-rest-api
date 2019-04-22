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
@EqualsAndHashCode(exclude = {"accounts"})
public class Holder {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    //OneToMany is by default Lazy
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holder") // Using mapped by to no create a join table
    @Builder.Default //to force lombok to initialize the Set
    // list or set is the same thing here
    private Set<Account> accounts = new HashSet<>();



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="share_holder")
    @Builder.Default
    private Set<Share> shares = new HashSet<>();

    public void addAccount(Account account) {
        this.accounts.add(account);
        account.setHolder(this);
    }

    public void removeAccount(Account account) {
        this.accounts.remove(account);
        account.setHolder(null);
    }
}
