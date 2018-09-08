package edu.myrza.lambda_composing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ComposingExamples {

    public static void main(String[] args){

        ComposingExamples example = new ComposingExamples();
        example.exampleFour();

    }

    private void exampleOne(){

        Comparator<String> c = Comparator.comparing(String::length);
        List<String> strList = Arrays.asList("aaa","aa","aaaa","a","aaaaaa","aaaaaaaaaaa","aa");

        strList.sort(c);
        printAll(strList);

        //reversed imposes reverse ordering of a given comparator
        strList.sort(c.reversed());
        printAll(strList);

    }

    //chaining comparators
    private void exampleTwo(){

        List<Apple> apples = Arrays.asList(
                new Apple("RED",147.3,"B"),
                new Apple("GREEN",147.3,"A"),
                new Apple("GREEN",112.3,"C"),
                new Apple("RED",110.3,"D")
        );

        Comparator<Apple> weightComparator  = Comparator.comparing(Apple::getWeigth);
        Comparator<Apple> countryComparator = Comparator.comparing(Apple::getCountryOfOrigin);

        //compares apples by weight and compares apples by country of origin if it encounters apples with the same weight
        Comparator<Apple> weightAndCountryComparator =
                weightComparator.thenComparing(countryComparator);

        apples.sort(weightAndCountryComparator);
        printAll(apples);

    }

    //composing predicates
    //Predicate has three major methods that let you create more complicated predicates:
    //negate() method which returns new predicate that is !oldPredicate
    //and() represents a logical operation &
    //or() represents a logical operation |


    //Note that the precedence of
    //methods and and or is managed from left to right using their positions in the chain. So
    //a.or(b).and(c) can be seen as (a || b) && c
    private void exampleThree(){

        List<Apple> apples = Arrays.asList(
                new Apple("RED",147.3,"B"),
                new Apple("GREEN",147.3,"A"),
                new Apple("GREEN",112.3,"C"),
                new Apple("RED",110.3,"D"),
                new Apple("YELLOW",110.3,"D"),
                new Apple("YELLOW",110.3,"D")
        );

        Predicate<Apple> redApple   = a -> a.getColor().equals("RED");
        Predicate<Apple> greenApple = a -> a.getColor().equals("GREEN");
        Predicate<Apple> heavyApple = a -> a.getWeigth() > 120 ;

        Predicate<Apple> redAndHeavy = redApple.and(heavyApple);
        Predicate<Apple> redOrGreenAndHeavy = redApple.or(greenApple).and(heavyApple);

        List<Apple> result = apples.stream().filter(redAndHeavy).collect(Collectors.toList());

        printAll(result);
        System.out.println("\n");

        result = apples.stream().filter(redOrGreenAndHeavy).collect(Collectors.toList());

        printAll(result);

    }

    public void exampleFour(){

        List<Apple> apples = Arrays.asList(
                new Apple("RED",147.3,"B"),
                new Apple("GREEN",147.3,"A"),
                new Apple("GREEN",112.3,"C"),
                new Apple("RED",110.3,"D"),
                new Apple("YELLOW",110.3,"D"),
                new Apple("YELLOW",110.3,"D")
        );


        Function<Apple,Apple> printWeight = a -> {
            System.out.print(a.getWeigth() + " ");
            return a;
        };

        Function<Apple,Apple> printColor = a -> {
            System.out.print(a.getColor() + " ");
            return a;
        };

        Function<Apple,Apple> printCountry = a -> {
            System.out.println(a.getCountryOfOrigin());
            return a;
        };

        Function<Apple,Apple> printEverything = printColor.andThen(printWeight).andThen(printCountry);
        apples.stream().forEach(printEverything::apply);

    }

    public void printAll(List list){
        list.stream().forEach(System.out::println);
    }

    private class Apple{

        private String color;
        private double weigth;
        private String countryOfOrigin;


        public Apple(String color,double weigth , String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
            this.weigth = weigth;
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCountryOfOrigin() {
            return countryOfOrigin;
        }

        public void setCountryOfOrigin(String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
        }

        public double getWeigth() {
            return weigth;
        }

        public void setWeigth(double weigth) {
            this.weigth = weigth;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weigth=" + weigth +
                    ", countryOfOrigin='" + countryOfOrigin + '\'' +
                    '}';
        }
    }

}
