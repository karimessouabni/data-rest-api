package com.karimou.data.rest.api.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Integer> {


    List<Holder> findByAccountsContains(Account account);
}
