package edu.myrza.donut_shop;

public class Payment{

    public final CreditCard card;
    public final int amount;

    public Payment(CreditCard card , int amount){
        this.amount = amount;
        this.card = card;
    }

}