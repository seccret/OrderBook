package com.example.orderbook.util;

import java.security.InvalidParameterException;

import com.example.orderbook.models.Currency;

public class CurrencyTransformer {

    private static final double SEK = 10.4;

    public static double priceToSek(double price, Currency currency) throws InvalidParameterException{
        switch(currency){
            case SEK:
                return price;
            case EUR:
                return price * SEK;
            default:
                throw new InvalidParameterException("invalid currency");
        }
    }
}
