package edu.myrza.exampleone;

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){



    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){

        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getName().equals("green"))
                result.add(apple);
        }

        return result;
    }
}