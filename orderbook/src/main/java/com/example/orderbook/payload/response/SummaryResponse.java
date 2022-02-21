package com.example.orderbook.payload.response;

public class SummaryResponse {
    private double averagePrice;
    private double maxPrice;
    private double minPrice;
    private int numOrders;
    
    public SummaryResponse(){
        this.minPrice = Double.POSITIVE_INFINITY;
        this.maxPrice = 0.0;
        this.averagePrice = 0.0;
        this.numOrders = 0;
    }

    public double getAveragePrice(){
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice){
        this.averagePrice = averagePrice;
    }

    public double getMaxPrice(){
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice){
        this.maxPrice = maxPrice;
    }

    public double getMinPrice(){
        return minPrice;
    }

    public void setMinPrice(double minPrice){
        this.minPrice = minPrice;
    }


    public int getNumOrders(){
        return numOrders;
    }

    public void setNumOrders(int numOrders){
        this.numOrders = numOrders;
    }
}
