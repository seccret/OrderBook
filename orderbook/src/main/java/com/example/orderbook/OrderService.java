package com.example.orderbook;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import com.example.orderbook.models.OrderObj;
import com.example.orderbook.models.OrderSide;
import com.example.orderbook.models.Ticker;
import com.example.orderbook.payload.response.SummaryResponse;

@Component
public interface OrderService {
    OrderObj getOrder(int id) throws NotFoundException;
    OrderObj createOrder(OrderObj newOrder) throws InvalidParameterException;
    SummaryResponse getSummary(Ticker ticker, OrderSide orderSide, LocalDate date) throws InvalidParameterException;
    void genData();
}