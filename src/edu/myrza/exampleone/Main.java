package edu.myrza.exampleone;

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("Green",123));
        apples.add(new Apple("Green",101));
        apples.add(new Apple("Red",151));

        ApplePredicate redPredicate = new AppleRedPredicate();

        System.out.println("red apples:");
        for(Apple apple : filterApples(apples,redPredicate)){
            System.out.println(apple);
        }

        System.out.println("green apples:");

        List<Apple> greenApples = filterApples(apples,(Apple apple) -> {return apple.getColor().equals("Green");});

        for(Apple apple : greenApples){
            System.out.println(apple);
        }

    }

    //We added a new parameter which represents a selecting behavior e.g. ApplePredicate predicate
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate){

        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            //we encapsulated away filtering behavior
            if(predicate.test(apple))
                result.add(apple);
        }

        return result;
    }
}