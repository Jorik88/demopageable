package com.example.demopageable.repository;

import com.example.demopageable.model.PurchaseOrderStatistic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseOrderStatisticRepository extends MongoRepository<PurchaseOrderStatistic, Long> {

    boolean existsByUserId(Long userId);
}
