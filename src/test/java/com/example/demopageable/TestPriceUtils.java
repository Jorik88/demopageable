package com.example.demopageable;

import com.example.demopageable.utils.PriceUtils;
import org.junit.Test;
import java.math.BigDecimal;

public class TestPriceUtils {

    @Test
    public void test() {
        BigDecimal coinPrice = BigDecimal.valueOf(0.1);
        BigDecimal btcPrice = BigDecimal.valueOf(5255.79);
        Integer btcFractionNumber = 8;
        Integer usdFractionNumber = 2;
        Integer coinFractionNumber = 4;

        BigDecimal priceResult = PriceUtils.convertPriceInCurrencyToToCoinPrice(btcPrice, coinPrice, coinFractionNumber, usdFractionNumber, btcFractionNumber);
        System.out.println(priceResult);
    }


    @Test
    public void test2() {
        BigDecimal coinPrice = BigDecimal.valueOf(0.1);
        BigDecimal multiply = BigDecimal.TEN.pow(Math.abs(2)).multiply(coinPrice);
        System.out.println(multiply);
    }
}
