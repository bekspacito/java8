package edu.myrza.donut_shop;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

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

    /**
     * @param payments represent a list of mixed payments for example :
     * [pc1 pc2 pc2 pc1 pc1 pc3 pc4 pc2 pc1 pc1] where pc(n)- payment of credit card number (n)
     * method groupByCard() groups them by card : [pc1 pc1 pc1 pc1 pc1] [pc2 pc2 pc2] [pc3] [pc4]
     * and combines them
     * */
    public static List<Payment> groupByCard(List<Payment> payments){
        return payments.stream()
                       .collect(groupingBy(p -> p.card))
                       .values()
                       .stream()
                       .map(list -> list.stream().reduce(Payment::combine).get())
                       .collect(toList());
    }

}