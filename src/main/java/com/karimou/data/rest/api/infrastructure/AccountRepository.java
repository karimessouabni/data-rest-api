package com.karimou.data.rest.api.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


Account findByHolder(Holder holder);

    @Async
    CompletableFuture<Account> findByTagEquals(String tag);
}
