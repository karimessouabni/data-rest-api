package com.karimou.data.rest.api.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    Transfer findByAmount(Integer amount);

    Transfer findByTransmitterAcc(Account transmitterAccount);

}
