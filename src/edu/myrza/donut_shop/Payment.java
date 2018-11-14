package edu.myrza.donut_shop;

public class Payment{

    public final CreditCard card;
    public final int amount;

    public Payment(CreditCard card , int amount){
        this.amount = amount;
        this.card = card;
    }

    //now we can combine two payments
    public Payment combine(Payment payment){
        if(card.equals(payment.card))
            return new Payment(card,this.amount + payment.amount);
        else
            throw new  IllegalStateException("Cards dont match...");
    }

}