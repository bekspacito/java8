package edu.myrza.exampleone;

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){

        


    }

    //We added a new parameter which represents a selecting behavior e.g. ApplePredicate predicate
    public static List<Apple> filterGreenApples(List<Apple> inventory,ApplePredicate predicate){

        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            //we encapsulated away filtering behavior
            if(predicate.test(apple))
                result.add(apple);
        }

        return result;
    }
}