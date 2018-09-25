package edu.myrza.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Transactions {

    public static void main(String[] args){

        exampleOne();
        exampleTwo();
        exampleThree();
        exampleFive();
        exampleSix();
        exampleSeven();
        exampleEight();

    }

    public static List<Transaction> getTestData(){

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        return transactions;

    }

    //Find all transactions in the year 2011 and sort them by value (small to high).
    public static void exampleOne(){

        List<Transaction> testData = getTestData();

        List<Transaction> result = testData.stream()
                                           .filter(t -> t.getYear() == 2011)
                                           .sorted(comparing(Transaction::getValue))
                                           .collect(toList());

        result.forEach(System.out::println);

    }

    //What are all the unique cities where the traders work?
    public static void exampleTwo(){

        List<Transaction> testData = getTestData();

        List<String> result = testData.stream()
                                      .map(t -> t.getTrader().getCity())
                                      .distinct()
                                      .collect(toList());

        result.forEach(System.out::println);
    }

    //Find all traders from Cambridge and sort them by name.
    public static void exampleThree(){

        List<Transaction> testData = getTestData();

        List<Trader> names = testData.stream()
                                     .map(Transaction::getTrader)
                                     .filter(trader -> trader.getCity().equals("Cambridge"))
                                     .distinct()
                                     .sorted(comparing(Trader::getName))
                                     .collect(toList());

        names.forEach(System.out::println);

    }

    //Are any traders based in Milan?
    public static void exampleFive(){

        List<Transaction> testData = getTestData();

        Boolean result = testData.stream()
                                 .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println("is there any traders in Milan : " + (result ? "Yes" : "No"));

    }

    //Print all transactions’ values from the traders living in Cambridge.
    public static void exampleSix(){

        List<Transaction> testData = getTestData();

        testData.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

    }

    //What’s the highest value of all the transactions?
    public static void exampleSeven(){

        List<Transaction> testData = getTestData();

        Optional<Integer> result = testData.stream()
                                           .map(Transaction::getValue)
                                           .reduce(Integer::max);

        result.ifPresent(System.out::println);

    }

    //Find the transaction with the smallest value
    public static void exampleEight(){

        List<Transaction> testData = getTestData();

        Optional<Transaction> result = testData.stream()
                                               .reduce((t1,t2) -> t1.getValue() > t2.getValue() ? t2 : t1);

        result.ifPresent(System.out::println);

    }
}
