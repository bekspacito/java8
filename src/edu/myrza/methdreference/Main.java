package edu.myrza.methdreference;


/**
 * In the case where all your lambda expression does is to call another method with the parameters passed to the lambda
 * , the Java lambda implementation provides a shorter way to express the method call.
 * */

public class Main{
    public static void main(String[] args){

        MyPrinter printerExampleOne = (String s) -> System.out.print(s);

        //Since all the lambda body does is forward the string parameter to the System.out.println() method,
        // we can replace the above lambda declaration with a method reference. Here is how a lambda method reference looks:
        MyPrinter printerExampleTwo = System.out::print;

        printerExampleOne.printer("line one\n");
        printerExampleTwo.printer("line two\n");

    }
}