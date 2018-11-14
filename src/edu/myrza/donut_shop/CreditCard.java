package edu.myrza.donut_shop;

public class CreditCard{

    private int account;

    public CreditCard(int money){
        this.account = money;
    }

    public void charge(int amount){
        account -= amount;
    }

    public void add(int amount){
        account += amount;
    }

    public int currentAmount(){
        return account;
    }

}