package com.example.orderbook.repository;

import java.time.LocalDateTime;

import com.example.orderbook.models.OrderObj;
import com.example.orderbook.models.OrderSide;
import com.example.orderbook.models.Ticker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderObj, Integer> {

    @Query("SELECT o FROM OrderObj o WHERE o.ticker = ?1 AND o.orderSide = ?2 AND o.date >= ?3 AND o.date <= ?4")
    OrderObj[] findByTickerOrderSideDate(Ticker ticker, OrderSide orderSide, LocalDateTime startDate, LocalDateTime endDate);
}
