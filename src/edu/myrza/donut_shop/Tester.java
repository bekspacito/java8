package edu.myrza.donut_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tester{
    public static void main(String[] args){

        test2();

    }

    public static void test1(){

        CreditCard card = new CreditCard(1000);
        Tuple<List<Donut>,Payment> purchase = DonutShop.buyDonuts(5,card);
        System.out.println(new Donut().price*5 == purchase._2.amount);
        System.out.println(card.equals(purchase._2.card));

    }

    public static void test2(){

        CreditCard card1 = new CreditCard(1000);
        CreditCard card2 = new CreditCard(700);
        CreditCard card3 = new CreditCard(7000);

        List<Tuple<Payment,Donut>> purchases = new ArrayList<>();

        //card1 = 3
        //card2 = 3
        //card3 = 3
        purchases.add(DonutShop.buyDonut(card1));
        purchases.add(DonutShop.buyDonut(card1));
        purchases.add(DonutShop.buyDonut(card2));
        purchases.add(DonutShop.buyDonut(card3));
        purchases.add(DonutShop.buyDonut(card3));
        purchases.add(DonutShop.buyDonut(card2));
        purchases.add(DonutShop.buyDonut(card1));
        purchases.add(DonutShop.buyDonut(card2));
        purchases.add(DonutShop.buyDonut(card3));

        List<Payment> mixedPayments = purchases.stream()
                                                .map(p -> p._1)
                                                .collect(Collectors.toList());

        List<Payment> combinedByCardPayments = Payment.groupByCard(mixedPayments);

        System.out.println(combinedByCardPayments.size() == 3);
        System.out.println(combinedByCardPayments.get(0).amount == new Donut().price*3);
        System.out.println(combinedByCardPayments.get(1).amount == new Donut().price*3);
        System.out.println(combinedByCardPayments.get(2).amount == new Donut().price*3);
    }
}