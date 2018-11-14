package edu.myrza.donut_shop;

public class DonutShop{

    public Tuple<Payment,Donut> buyDonut(CreditCard card) {

        Donut donut = new Donut();
        //now we get rid of a side effect simply by representing the purchase in @param payment
        //and adding it to the return tuple
        Payment payment = new Payment(card,donut.price);
        return new Tuple<>(payment,donut);

    }

}