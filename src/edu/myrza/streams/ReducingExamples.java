package edu.myrza.streams;

import edu.myrza.util.Dish;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ReducingExamples {
    public static void main(String[] args){

        exampleTwo();

    }

    /**
     * Find sum of elements of arrays
     * */
    public static void exampleOne(){

        Integer[] numbers = new Integer[]{9,8,7,6,5,4,3,2,1};

        Integer res = Stream.of(numbers)
                            .reduce(0,(n1,n2) -> n1+n2);

        Optional<Integer> res2 = Stream.of(numbers)
                                       .reduce((n1,n2) -> n1+n2);


        System.out.println(res);
    }

    /**
     * Find max element
     * */
    public static void exampleTwo(){

        Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8,9};

        Optional<Integer> res = Stream.of(numbers)
                                      .reduce(Integer::max);

        res.ifPresent(System.out::println);

    }

    /**
     * Count an amount of dishes in the stream
     * */
    public static void exampleThree(List<Dish> dishes){

        //dumb version
        Optional<Integer> result = dishes.stream()
                                         .map(d -> 1)
                                         .reduce((a,b) -> a+b);
        result.ifPresent(System.out::println);

        //normal version
        Long res = dishes.stream().count();

    }
}
