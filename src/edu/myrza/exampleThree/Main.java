package edu.myrza.exampleThree;

import java.util.Arrays;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args){


        Integer[] array = {9,8,7,6,5,4,3,2,1};

        //displays initial version
        forEach(array,(Integer el) -> System.out.print(el + " "));
        System.out.println();

        //sorts the array
        Arrays.sort(array,(Integer a,Integer b) -> a.compareTo(b) );

        //displays sorted version
        forEach(array,(Integer el) -> System.out.print(el + " "));
    }

    public static <T> void forEach(T[] list,Consumer<T> c){
        for(T el : list)
            c.accept(el);
    }

}
