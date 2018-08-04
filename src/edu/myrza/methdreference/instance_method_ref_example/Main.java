package edu.myrza.methdreference.instance_method_ref_example;

/**
 * It's also possible to reference an instance method  from a lambda definition.
 * First, you need an "interface representation" of the method you're gonna reference to
 * (Here we have two such interfaces [ToStringPlaceHolder,Converter])
 * Note that signatures of both method you've declared in "interface representation" and instance method you're gonna reference to
 * must match(return type,parameter types)
 * Next, you just assign instance method you need to reference to to a "interface representation"
 * (examples are exampleOne() and exampleTwo())
 * */

public class Main {
    public static void main(String[] args){

        exampleOne();
        exampleTwo();

    }

    public static void exampleOne(){
        //we have created an instance of class we are gonna get method of
        StringToIntConverter instanceConverter = new StringToIntConverter("MARK-2");
        //magic part
        Converter converterTwo = instanceConverter::conv;

        int res = converterTwo.convert("1234");
        System.out.println(res);
    }

    public static void exampleTwo(){

        StringToIntConverter instanceConverter = new StringToIntConverter("R2D2");

        ToStringPlaceholder toStringPlaceholder = instanceConverter::toString;

        System.out.println(toStringPlaceholder.toStr());

    }
}
