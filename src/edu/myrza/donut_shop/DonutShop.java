package edu.myrza.donut_shop;

public class DonutShop{

    public static Donut buyDonut(CreditCard card){

        Donut  donut = new Donut();
        //charging credit card is a god damn side effect
        card.charge(donut.price);
        return donut;
    }

}