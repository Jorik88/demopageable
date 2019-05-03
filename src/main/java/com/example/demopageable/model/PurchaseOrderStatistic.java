package com.example.demopageable.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class PurchaseOrderStatistic {

    @Id
    private Long purchaseOrderId;
    private Long userId;
    private Long coinAmount;
    private Long amountInUSD;
    private String currency;
    private Double sumInCurrency;
    private Long createdDate;
}
