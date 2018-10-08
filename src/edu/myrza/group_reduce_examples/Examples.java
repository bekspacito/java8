package edu.myrza.group_reduce_examples;

import edu.myrza.util.Dish;
import static edu.myrza.util.DishCollectionCreator.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class Examples {
    public static void main(String[] args){

        exampleTwo();

    }

    /**
     * Count an overall amount of dishes in arg list
     * */
    static void exampleOne(List<Dish> dishes){

        Long res  = dishes.stream().collect(counting());
        Long res2 = dishes.stream().count();

    }

    static void exampleTwo(){

        List<Dish> dishes = createDishCollection();

        Comparator<Dish> compareCalories = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish  = dishes.stream().collect(maxBy(compareCalories));
        Optional<Dish> leastCalorieDish = dishes.stream().collect(minBy(compareCalories));

        mostCalorieDish.ifPresent(d -> System.out.println("most calorie dish is " + d));
        leastCalorieDish.ifPresent(d -> System.out.println("least calorie dish is " + d));

    }

}
