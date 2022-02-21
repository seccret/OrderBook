package com.example.orderbook.models;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class OrderObj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private Ticker ticker;
    @Enumerated(EnumType.STRING)
    private OrderSide orderSide;
    private int volume;
    private double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    
    public OrderObj(){}

    public OrderObj(Ticker ticker, OrderSide orderSide, int volume, double price, Currency currency){
        this.ticker = ticker;
        this.orderSide = orderSide;
        this.volume = volume;
        this.price = price;
        this.currency = currency;
        this.date = LocalDateTime.now();
    }

    public int getId(){
        return id;
    }

    public Ticker getTicker(){
        return ticker;
    }

    public OrderSide getOrderSide(){
        return orderSide;
    }

    public int getVolume(){
        return volume;
    }

    public double getPrice(){
        return price;
    }

    public Currency getCurrency(){
        return currency;
    }

    public LocalDateTime getDate(){
        return date;
    }
    public void setDate(LocalDateTime date){
        this.date = date;
    }


}
