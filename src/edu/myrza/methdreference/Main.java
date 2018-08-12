package edu.myrza.methdreference;


import edu.myrza.exampleone.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * In the case where all your lambda expression does is to call another method with the parameters passed to the lambda
 * , the Java lambda implementation provides a shorter way to express the method call.
 * */

public class Main{
    public static void main(String[] args){

        MyPrinter printerExampleOne = (String s) -> System.out.print(s);

        //Since all the lambda body does is forward the string parameter to the System.out.println() method,
        // we can replace the above lambda declaration with a method reference. Here is how a lambda method reference looks:
        MyPrinter printerExampleTwo = System.out::print;

        printerExampleOne.printer("line one\n");
        printerExampleTwo.printer("line two\n");

        stringToIntegerExample();
        listOfStringsContain();
        constructorRef();


    }

    private static void stringToIntegerExample(){

        /**This will turn into lambda expression such like : (String s) -> Integer.parseInt(s)
         * so Integer.parseInt is wrapped in lambda expression*/
        Function<String,Integer> function = Integer::parseInt;

        Integer res1 = function.apply("1234");
        Integer ser2 = function.apply("1010");
        Integer res3 = function.apply("4321");

        System.out.println(res1 + ser2 + res3);

    }

    private static void listOfStringsContain(){

        /**
         *   BiPredicate : (T,U) -> boolean
         *  (List<String> list,String token) -> list.contains(token)
         * */
        BiPredicate<List<String>,String> contains = List::contains;

        List<String> list = Arrays.asList("A","B","C","D");

        System.out.println(contains.test(list,"A"));
        System.out.println(contains.test(list,"E"));
        System.out.println(contains.test(list,"F"));
        System.out.println(contains.test(list,"B"));

    }

    private static void constructorRef(){

        /**
         * Will turn into lambda such like : (String color,Integer weight) -> new Apple(color,weight)
         * */
        BiFunction<String,Integer,Apple> appleFactory = Apple::new;

        Apple redApple   = appleFactory.apply("RED",150);
        Apple greenApple = appleFactory.apply("GREEN",120);
        Apple heavyApple = appleFactory.apply("RED",200);

        System.out.println(redApple);
        System.out.println(greenApple);
        System.out.println(heavyApple);

    }

}