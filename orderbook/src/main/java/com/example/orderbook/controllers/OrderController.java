package com.example.orderbook.controllers;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import com.example.orderbook.OrderService;
import com.example.orderbook.models.OrderObj;
import com.example.orderbook.models.OrderSide;
import com.example.orderbook.models.Ticker;
import com.example.orderbook.payload.response.SummaryResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

  @Autowired
  OrderService scv;

  @GetMapping("/get-order")
  @ResponseBody
  public OrderObj getOrder(@RequestParam(name="id") int id) {
    try {
      return scv.getOrder(id);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Could not find order with id: " + id, e);
    }
  }

  @PostMapping("/create-order")
  @ResponseBody
  public OrderObj createOrder(@RequestBody OrderObj newOrder) {
    try {
      return scv.createOrder(newOrder);
       } catch (InvalidParameterException e) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "Invalid input", e);
    }
  }

  @GetMapping("/get-summary")
  @ResponseBody
  public SummaryResponse getSummary(@RequestParam(name="ticker") Ticker ticker, @RequestParam(name="orderSide") OrderSide orderSide, @RequestParam(name="date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
    return scv.getSummary(ticker, orderSide, date);
  }

  @GetMapping("/gen-data")
  @ResponseBody
  public void genData() {
    scv.genData();
  }

}