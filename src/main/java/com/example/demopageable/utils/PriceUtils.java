package com.example.demopageable.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtils {

    public static BigDecimal convertPriceInCurrencyToToCoinPrice(BigDecimal priceInCurrency,
                                                                  BigDecimal coinPrice,
                                                                  Integer coinFractionNumber,
                                                                  Integer usdFractionNumber,
                                                                  Integer currencyFractionNumber) {
        if (priceInCurrency == null || coinPrice == null || coinFractionNumber == null || usdFractionNumber == null || currencyFractionNumber == null) {
            throw new IllegalArgumentException("Error convert currency amount. One of the arguments is null");
        }

        BigDecimal minimalUnitCurrencyInUsd = toMinimalUnitInUsd(priceInCurrency, usdFractionNumber);
        BigDecimal minimalUnitCoinInUsd = toMinimalUnitInUsd(coinPrice, usdFractionNumber);

        BigDecimal minimalUnitInCurrency = toMinimalUnitInCurrency(minimalUnitCurrencyInUsd, currencyFractionNumber);
        BigDecimal minimalUnitInCoin = toMinimalUnitInCurrency(minimalUnitCoinInUsd, coinFractionNumber);
        return new BigDecimal(countPrice(minimalUnitInCurrency, minimalUnitInCoin));
    }

    private static BigDecimal toMinimalUnitInUsd(BigDecimal price, Integer usdFractionNumber) {
        return BigDecimal.TEN.pow(Math.abs(usdFractionNumber)).multiply(price);
    }

    private static BigDecimal toMinimalUnitInCurrency(BigDecimal price, Integer fractionNumber) {
        return price.setScale(fractionNumber, RoundingMode.HALF_EVEN).divide(BigDecimal.TEN.pow(Math.abs(fractionNumber)), RoundingMode.HALF_EVEN);
    }

    private static double countPrice(BigDecimal minimalUnitInCurrency, BigDecimal minimalUnitInCoin) {
        return 1.0 / (minimalUnitInCurrency.doubleValue() / minimalUnitInCoin.doubleValue());
    }
}
