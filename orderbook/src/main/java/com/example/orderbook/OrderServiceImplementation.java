package com.example.orderbook;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.orderbook.models.Currency;
import com.example.orderbook.models.OrderObj;
import com.example.orderbook.models.OrderSide;
import com.example.orderbook.models.Ticker;
import com.example.orderbook.payload.response.SummaryResponse;
import com.example.orderbook.repository.OrderRepository;
import com.example.orderbook.util.CurrencyTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    OrderRepository jpa;

    @Override 
    public OrderObj getOrder(int id) throws NotFoundException{
        return jpa.findById(id).orElseThrow(()-> new NotFoundException());
    }

    @Override 
    public OrderObj createOrder(OrderObj newOrder) throws InvalidParameterException{
        if (newOrder.getTicker() == null || 
                newOrder.getOrderSide() == null ||
                newOrder.getVolume() == 0 ||
                newOrder.getPrice() == 0 ||
                newOrder.getCurrency() == null){
            throw new InvalidParameterException("Missing input parameters, check your request");
        }
        newOrder.setDate(LocalDateTime.now());
        return jpa.save(newOrder);
    }

    @Override
    public SummaryResponse getSummary(Ticker ticker, OrderSide orderSide, LocalDate date) throws InvalidParameterException{
        if (ticker == null || 
                orderSide == null ||
                date == null){
            throw new InvalidParameterException("Missing input parameters, check your request");
        }

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX);

        OrderObj[] records = jpa.findByTickerOrderSideDate(ticker, orderSide, startOfDay, endOfDay);
        SummaryResponse res = new SummaryResponse();
        res.setNumOrders(records.length);
        double averagePrice = 0.0;
        for(int i = 0; i < records.length; i++){
            double price = CurrencyTransformer.priceToSek(records[i].getPrice(), records[i].getCurrency());
            if(res.getMaxPrice() < price){
                res.setMaxPrice(price);
            }
            if(res.getMinPrice() > price){
                res.setMinPrice(price);
            }
            averagePrice += price;
        }
        if(averagePrice > 0){
            res.setAveragePrice(averagePrice/records.length);
        }

        return res;
    }

    @Override
    public void genData(){
        jpa.save(new OrderObj(Ticker.GME, OrderSide.BUY,1, 134.34, Currency.EUR));
        jpa.save(new OrderObj(Ticker.SAVE, OrderSide.BUY,2, 38.3, Currency.SEK));
        jpa.save(new OrderObj(Ticker.SAVE, OrderSide.BUY,3, 32.3, Currency.SEK));
        jpa.save(new OrderObj(Ticker.SAVE, OrderSide.BUY,1, 3.61, Currency.EUR));
    }
    
}
