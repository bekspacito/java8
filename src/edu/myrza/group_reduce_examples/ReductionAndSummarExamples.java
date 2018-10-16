package edu.myrza.group_reduce_examples;

import edu.myrza.util.Dish;
import static edu.myrza.util.DishCollectionCreator.*;

import java.util.*;

import static java.util.stream.Collectors.*;

public class ReductionAndSummarExamples {
    public static void main(String[] args){

        exampleThree();

    }

    /**
     * Count an overall amount of dishes in arg list
     * */
    static void exampleOne(List<Dish> dishes){

        Long res  = dishes.stream().collect(counting());
        Long res2 = dishes.stream().count();

    }

    //get dishes with max and min calories
    static void exampleTwo(){

        List<Dish> dishes = createDishCollection();

        Comparator<Dish> compareCalories = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish  = dishes.stream().collect(maxBy(compareCalories));
        Optional<Dish> leastCalorieDish = dishes.stream().collect(minBy(compareCalories));

        mostCalorieDish.ifPresent(d -> System.out.println("most calorie dish is " + d));
        leastCalorieDish.ifPresent(d -> System.out.println("least calorie dish is " + d));

    }

    //find total number of calories in the menu
    static void exampleThree(){

        List<Dish> dishes = createDishCollection();

        //first version
        int result = dishes.stream().mapToInt(Dish::getCalories).sum();

        //more general version
        int result2 = dishes.stream().collect(summingInt(Dish::getCalories));

        //even more general version
        //here the first argument is initial value of the result, it's also a value which is returned if stream happens to be empty
        //second value is a mapper which maps dished into integer values
        //third argument is a reduction mechanism
        int result3 = dishes.stream().collect(reducing(0,Dish::getCalories,(i,j) -> i + j));


        String strResult = Arrays.asList(result,result2,result3).stream()
                                                                .map(String::valueOf)
                                                                .collect(joining(","));

        System.out.println(strResult);

    }

    //find average calorie of the menu
    static void exampleFour(){

        List<Dish> dishes = createDishCollection();

        double result = dishes.stream()
                              .collect(averagingInt(Dish::getCalories));

    }

    //find min, max, average, and an amount of the dishes in the menu
    static void exampleFive(){

        List<Dish> dishes = createDishCollection();

        IntSummaryStatistics statistics = dishes.stream()
                                                .collect(summarizingInt(Dish::getCalories));

        System.out.println(statistics);

    }

    //get one string short version of the menu
    static void exampleSix(){

        List<Dish> dishes = createDishCollection();

        String menu = dishes.stream()
                            .map(Dish::getName)
                            .collect(joining(" , "));

        System.out.println("[" + menu + "]");
    }

}
