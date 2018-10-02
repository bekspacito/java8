package edu.myrza.numeric_streams;

import edu.myrza.util.Dish;
import static edu.myrza.util.DishCollectionCreator.*;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class StreamExamples {
    public static void main(String[] args){

        exampleThreeInvoker();

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


    private static void exampleThreeInvoker(){

        exampleThree(7,24);

    }

    private static void exampleThree(int aUpperBound,int bUpperBond){

        IntStream.rangeClosed(1,aUpperBound)
                          .boxed()
                          .flatMap(a -> IntStream.rangeClosed(1,bUpperBond)
                                                .filter(b -> {
                                                     double c = Math.sqrt(a*a + b*b);
                                                     return !(c > Math.floor(c));
                                                 })
                                                .boxed()
                                                .map(b -> new PythTriple(a,b,Math.sqrt(a*a + b*b)))
                         )
                         .distinct()
                         .forEach(System.out::println);


    }

    private static class PythTriple {

        public final double a;
        public final double b;
        public final double c;

        public PythTriple(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PythTriple that = (PythTriple) o;

            if (Double.compare(that.a, a) != 0) return false;
            if (Double.compare(that.b, b) != 0) return false;
            return Double.compare(that.c, c) == 0;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(a);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(b);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(c);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "PythTriple{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }

}
