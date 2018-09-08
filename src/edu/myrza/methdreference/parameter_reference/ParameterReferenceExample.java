package edu.myrza.methdreference.parameter_reference;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class ParameterReferenceExample {
    public static void main(String[] args){

        ParameterReferenceExample examples = new ParameterReferenceExample();
        examples.exampleOne();

    }

    //find first appearance of substr in str
    /**
     * The Java compiler will attempt to match the referenced method against the first parameter type,
     * using the remain parameters' types as parameters to the referenced method.
     * */
    private void exampleOne(){

        Finder finderOne = (str,substr) -> str.indexOf(substr);
        Finder finderTwo = String::indexOf;

        String str = "hello friend...";
        String substr = "friend";
        System.out.println(finderOne.find(str,substr) == finderTwo.find(str,substr)); //will return true 'cos they do exactly the same actions
    }

    //sorts strings ignoring cases
    private void exampleTwo(){

        List<String> stringList = Arrays.asList("a","b","A","B","C","c");
        stringList.sort(String::compareToIgnoreCase);
        //which will turn into stringList.sort((s1,s2) -> s1.compareToIgnoreCase(s2))

    }

    private interface Finder{
        int find(String str,String substr);
    }
}

