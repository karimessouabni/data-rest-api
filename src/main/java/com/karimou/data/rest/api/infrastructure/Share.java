package com.karimou.data.rest.api.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Share {


    @Id
    @GeneratedValue
    private Integer id ;


    private String companyName;

    private Integer value ;



    @ManyToMany(mappedBy = "shares")
    private Set<Holder> holders = new HashSet<>();



}
