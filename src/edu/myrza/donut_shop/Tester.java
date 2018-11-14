package edu.myrza.donut_shop;

import java.util.List;

public class Tester{
    public static void main(String[] args){

        CreditCard card = new CreditCard(1000);
        Tuple<List<Donut>,Payment> purchase = DonutShop.buyDonuts(5,card);
        System.out.println(new Donut().price*5 == purchase._2.amount);
        System.out.println(card.equals(purchase._2.card));

    }
}