package com.karimou.data.rest.api.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {


    @Override
    Optional<Transfer> findById(Integer integer);


    // better performance for large data fetched from DB
    // the old way was using list which pull all the resultSet
    // to memory before it can be handed to the service
    Stream<Transfer> findByAmount(String amount);



    Transfer findByTransmitterAcc(Account transmitterAccount);

}
