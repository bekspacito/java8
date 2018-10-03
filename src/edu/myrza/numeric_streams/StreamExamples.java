package edu.myrza.numeric_streams;

import edu.myrza.util.Dish;
import static edu.myrza.util.DishCollectionCreator.*;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args){

        exampleThree();

    }

    //get overall amount of calories
    private static void exampleOne(){

        List<Dish> dishes = createDishCollection();

        int sumOfCalories =  dishes.stream()
                                   .mapToInt(Dish::getCalories)
                                   .sum();


    }

    //calculate the average amount of calories
    private static void exampleTwo(){

        List<Dish> dishes = createDishCollection();

        OptionalDouble result = dishes.stream()
                                      .mapToInt(Dish::getCalories)
                                      .average();

        result.ifPresent(System.out::println);

    }


    private static void exampleThree(){

     int aUpperBound = 100;
     int bUpperBound = 100;

     Stream<int[]> res = IntStream.range(1,aUpperBound)
                                     .boxed()
                                     .flatMap(a -> IntStream.range(a,bUpperBound)
                                                            .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                                            .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a*a + b*b)})
                                     );

      res.limit(5)
         .forEach(a -> System.out.println("[" + a[0] + "," + a[1] + "," + a[2] + "]"));

    }

}
