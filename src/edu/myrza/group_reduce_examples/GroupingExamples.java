package edu.myrza.group_reduce_examples;

import edu.myrza.util.Dish;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static edu.myrza.util.DishCollectionCreator.*;
import static java.util.stream.Collectors.*;

public class GroupingExamples{
    public static void main(String[] args){

        exampleSix();

    }

    //group dishes by their type
    static void exampleOne(){

        List<Dish> dishes = createDishCollection();

        Map<Dish.Type,List<Dish>> result = dishes.stream()
                                                 .collect(groupingBy(Dish::getType));

        System.out.println(result);
    }

    //classify as “diet” all dishes with 400 calories or fewer, set to
    //“normal” the dishes having between 400 and 700 calories, and set to “fat” the ones with more
    //than 700 calories.
    static void exampleTwo(){

        List<Dish> dishes = createDishCollection();

        Map<String,List<Dish>> result = dishes.stream()
                                              .collect(groupingBy(dish -> {
                                                       if(dish.getCalories() <= 400) return "DIET";
                                                  else if(dish.getCalories() <= 700) return "NORMAL";
                                                  else                               return "FAT";
                                              }));

        System.out.println(result);

    }

    //do grouping by both dish type and calorie levels
    static void exampleThree(){

        List<Dish> dishes = createDishCollection();

        Map<Dish.Type,Map<String,List<Dish>>> result = dishes.stream()
                                                       .collect(groupingBy(Dish::getType,
                                                                    groupingBy(dish -> {
                                                                             if(dish.getCalories() <= 400) return "DIET";
                                                                        else if(dish.getCalories() <= 700) return "NORMAL";
                                                                        else                               return "FAT";
                                                                    })
                                                                )
                                                       );

        System.out.println(result);
    }

    //count amounts of dishes of each type
    static void exampleFour(){

        List<Dish> dishes = createDishCollection();

        Map<Dish.Type,Long> result = dishes.stream()
                                              .collect(groupingBy(Dish::getType,counting()));

        System.out.println(result);


    }

    //get total calories by type
    static void exampleFive(){

        List<Dish> dishes = createDishCollection();

        Map<Dish.Type,Integer> result = dishes.stream()
                                                .collect(groupingBy(Dish::getType,summingInt(Dish::getCalories)));

        System.out.println(result);


    }

    //get CaloricLevels that are available in the menu for each type of Dish.
    static void exampleSix(){

        List<Dish> dishes = createDishCollection();

        Map<Dish.Type,Set<String>> result = dishes.stream().collect(
                                                                groupingBy(Dish::getType,mapping(
                                                                                dish -> {
                                                                                    if(dish.getCalories() <= 400)      return "DIET";
                                                                                    else if(dish.getCalories() <= 700) return "NORMAL";
                                                                                    else                               return "FAT";
                                                                                    }
                                                                ,toSet())));

        System.out.println(result);

    }

}