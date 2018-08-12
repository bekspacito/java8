package edu.myrza.exampleone;

import java.util.ArrayList;
import static java.util.Comparator.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main{
    public static void main(String[] args){

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("Green",123));
        apples.add(new Apple("Green",101));
        apples.add(new Apple("Red",151));
        apples.add(new Apple("Green",148));
        apples.add(new Apple("Red",132));
        apples.add(new Apple("Red",115));
        apples.add(new Apple("Green",121));
        apples.add(new Apple("Red",134));

        Predicate<Apple> redApplePredicate    = (Apple a) -> a.getColor().equals("Red");
        Predicate<Apple> heavyApplePredicate  = (Apple a) -> a.getWeight() > 150;

        Predicate<Apple> redAndHeavyPredicate = redApplePredicate.and(heavyApplePredicate);

        System.out.println("red apples : "   + filterApples(apples,redApplePredicate));
        System.out.println("Heavy apples : " + filterApples(apples,heavyApplePredicate));
        System.out.println("red and heavy apples " + filterApples(apples,redAndHeavyPredicate));

        functionComposingExample();

    }

    public static void sortApplesByWeigth(List<Apple> inventory){

        inventory.sort(comparing(Apple::getWeight));


    }

    //We added a new parameter which represents a selecting behavior e.g. ApplePredicate predicate
    public static List<Apple> filterApples(List<Apple> inventory, Predicate predicate){

        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            //we encapsulated away filtering behavior
            if(predicate.test(apple))
                result.add(apple);
        }

        return result;
    }

    public static void functionComposingExample(){

        Function<Integer,Integer> f = (Integer x) -> 3*x;
        Function<Integer,Integer> g = (Integer x) -> x + 1;

        Function<Integer,Integer> h = f.andThen(g); //g(f(x))

        Function<Integer,Integer> k = f.compose(g); //f(g(x))

        System.out.println("h(" + 1 + ") = " + h.apply(1)); //4
        System.out.println("h(" + 10 + ") = " + h.apply(10)); //31

        System.out.println("k(" + 1 + ") = " + k.apply(1));  //6
        System.out.println("k(" + 10 + ") = " + k.apply(10)); //33

    }

}