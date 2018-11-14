package edu.myrza.donut_shop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DonutShop{

    public static Tuple<Payment,Donut> buyDonut(final CreditCard card) {

        Donut donut = new Donut();
        //now we get rid of a side effect simply by representing the purchase in @param payment
        Payment payment = new Payment(card,donut.price);
        return new Tuple<>(payment,donut);

    }

    public static Tuple<List<Donut>,Payment> buyDonuts(final int quantity,final CreditCard ccard){

        List<Donut> donuts = IntStream.range(0,quantity)
                                      .mapToObj(i -> new Donut())
                                      .collect(Collectors.toList());

        Payment payment = new Payment(ccard,donuts.stream().mapToInt(d -> d.price).sum());

        return new Tuple<>(donuts,payment);
    }

}