package com.example.demopageable.repository;

import com.example.demopageable.model.PurchaseOrderStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class PurchaseOrderStatisticCustomRepository {

    private final MongoTemplate mongoTemplate;

    private static class LongValue{
        Long totalCount;
    }

    @Autowired
    public PurchaseOrderStatisticCustomRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public int getCountUniqUsers() {
        Aggregation aggregation = newAggregation(group("userId").addToSet("userId").as("totalCount"));
        AggregationResults<LongValue> aggregate = mongoTemplate.aggregate(aggregation, PurchaseOrderStatistic.class, LongValue.class);
        return aggregate.getMappedResults().size();
    }
}
