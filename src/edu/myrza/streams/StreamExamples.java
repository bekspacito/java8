package edu.myrza.streams;

import edu.myrza.util.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static edu.myrza.util.DishCollectionCreator.*;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class StreamExamples {
    public static void main(String[] args){

        StreamExamples examples = new StreamExamples(createDishCollection());

    }

    private List<Dish> dishes;

    public StreamExamples(List<Dish> dishes){
        this.dishes = dishes;
    }

    //return three most caloried dishes
    private void exampleOne(){

        List<String> result = dishes.stream()
                .sorted(comparing(Dish::getCalories).reversed())
                .limit(3)
                .map(Dish::getName)
                .collect(toList());

        System.out.println(result);

    }

    //you can consume streams only once
    private void exampleTwo(){

        List<String> titles = Arrays.asList("Java 8","in","action");
        Stream<String> stream = titles.stream();


        stream.forEach(System.out::println); //prints each element of the stream
        stream.forEach(System.out::println); // java.lang.IllegalStateException : stream has already been operated upon or closed

    }

    //filter example
    //gets names of all the vegeterian dishes
    public void exampleThree(List<Dish> dishes){

        List<String> vegDishes = dishes.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName)
                .collect(toList());

        vegDishes.stream()
                .forEach(System.out::println);

    }

    //filter first two meat dishes
    public void exampleFour(List<Dish> dishes){

        List<Dish> result = dishes.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());


    }

    //return a list of the number of characters for each word
    public void exampleFive(){

        List<String> words = Arrays.asList("Peace","was","never","an","option");

        List<Integer> result = words.stream()
                .map(String::length)
                .collect(toList());

    }

    public void exampleSix(){

        List<String> words = Arrays.asList("Hello","world");

        /**
         * Arrays.stream() takes an array and produces a stream based on that given array
         * flatMap() method let's you replace each value of a stream with another stream(so stream per value)
         * and then merges/connected all the generated streams into a single stream.
         * As a parameter, flatMap() takes an value-to-stream translator in order to translate
         * a values of given type into streams
         * */
        List<String> result = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        result.forEach(c -> System.out.print(c + " "));

    }

    public void exampleSeven(){

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        List<Integer> result = numbers.stream()
                                      .map(n -> n*n)
                                      .collect(toList());

        System.out.println(numbers);
        System.out.println(result);

    }

    /**
    Given two lists of numbers, how would you return all pairs of numbers? For example, given a
    list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For
    simplicity, you can represent a pair as an array with two elements
    */
    public void exampleEight(){

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);

        List<List<Integer>> result = list1.stream()
                                      .map(n -> list2.stream()
                                                        .map(k -> Arrays.asList(n,k))
                                                        .collect(toList())
                                        )
                                       .flatMap(List::stream)
                                       .collect(toList());//returns arrays of arrays

        System.out.println(result);

    }

    public void exampleEightBookVersion(){

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);

        List<int[]> result = list1.stream()
                                      .flatMap(i -> list2.stream()
                                                         .map(j -> new int[]{i,j}))
                                      .collect(toList());

    }

    /**
     * How would you extend the previous example to return only pairs whose sum is divisible by 3?
     For example, (2, 4) and (3, 3) are valid
     * */
    public void exampleNine(){

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);

        List<List<Integer>> result = list1.stream()
                                          .map(n -> list2.stream()
                                                    .filter(k -> (n+k)%3 == 0)
                                                    .map(k -> Arrays.asList(n,k))
                                                    .collect(toList())
                                          )
                                          .flatMap(List::stream)
                                          .collect(toList());//returns arrays of arrays

        System.out.println(result);

    }

    public void exampleNineBookVersion(){

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);

        List<int[]> result = list1.stream()
                                    .flatMap(i -> list2.stream()
                                            .filter(j -> (j+i)%3 == 0)
                                            .map(j -> new int[]{i,j}))
                                    .collect(toList());

    }

    /**
     * Find an arbitrary vegetarian dish
     * */
    public void exampleTen(List<Dish> dishes){

        Optional<Dish> result = dishes.stream()
                                        .filter(Dish::isVegetarian)
                                        .findAny();


    }

    public void exampleEleven(List<Dish> dishes){

        dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);

    }

    /**
     * Generate Fibonacci tuples
     * example : : (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21)
     * */
    public void exampleTwelve(){

        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0] + t[1]})
              .limit(20)
              .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

    }

    /**
     * Generate Fibonacci numbers
     * output : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
     * */
    public void exampleThreeteen(){

        Stream.iterate(new int[]{0,1},t -> new int[]{t[0] + t[1],t[0] + 2*t[1]})
                .flatMapToInt(Arrays::stream)
                .limit(11)
                .forEach(n -> System.out.print(n + " "));

    }

    private int fibonacci(int i){

        if(i == 1) return 0;
        if(i == 2) return 1;
        return fibonacci(i - 1) + fibonacci(i - 2);

    }

}
