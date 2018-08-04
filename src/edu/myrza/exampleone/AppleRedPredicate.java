package edu.myrza.exampleone;

public class AppleRedPredicate implements ApplePredicate{

    public boolean test(Apple apple){

        return apple.getColor().equals("Red");

    }

}