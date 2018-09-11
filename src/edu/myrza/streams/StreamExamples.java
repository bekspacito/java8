package edu.myrza.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static edu.myrza.streams.util.DishCollectionCreator.*;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class StreamExamples {
    public static void main(String[] args){

        StreamExamples examples = new StreamExamples(createDishCollection());
        examples.exampleTwo();

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
}
