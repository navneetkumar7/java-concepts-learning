package com.nt.jclearning8.streams;

import jdk.dynalink.beans.StaticClass;

import javax.management.ObjectName;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BasicStreams {
    public static void main(String[] args) {
        List<Integer> marks= Arrays.asList(90,85,92,96,84);
        System.out.println("Filtered marks count:"+
        marks
                .stream()
                .peek(System.out::println)
                .filter(mark->mark>90)
                .peek(System.out::println)
                .limit(1)
                .count()
        );

        creatingStreams();
        terminalOperation();
    }

    private static void terminalOperation() {
        //reductions
        Optional<String>min=Stream.of("apple","mango","grapes")
                .min((s1,s2)->s1.length()-s2.length());
        min.ifPresent(System.out::println);
        // not reductions
        Optional<String>any= Stream.of("apple","mango","grapes")
                .findAny();
        any.ifPresent(System.out::println);

        Optional<String>first= Stream.of("apple","mango","grapes")
                .findFirst();
        any.ifPresent(System.out::println);

        List<String>names=Arrays.asList("Alice","Waltmore","Cooper");
        Predicate<String>pred=val->val.startsWith("C");
        System.out.println(names.stream().anyMatch(pred));
        System.out.println(names.stream().allMatch(pred));
        System.out.println(names.stream().noneMatch(pred));

        names.stream().forEach(System.out::println);
    }

    private static void creatingStreams() {
        // from arrays
        Integer[] integers={1,2,3,4,5,6};
        Stream<Integer> integerStream= Arrays.stream(integers);
        System.out.println("integer stream count:"+integerStream.count());

        // from collection default
        List<String> fruitList=Arrays.asList("Apple","Mango","Orange");
        System.out.println("FruitList count"+fruitList.stream().count());

        Map<String,Integer> fruitsToPrices= new HashMap<>();
        fruitsToPrices.put("Apple",50);
        fruitsToPrices.put("Orange",30);
        System.out.println("fruits to price mapping count "+
                fruitsToPrices
                        .entrySet()// entry set give the set view of the map inherits the collection interface
                        .stream()
                        .count());
        // Stream.of()
        Stream<Integer> integerStream1=Stream.of(1,2,3);
        Stream<String> stringStream=Stream.of("A","B");
        Stream<BasicStreams> objectStream=Stream.of(new BasicStreams());
        Stream<Integer> infiniteStream= Stream.generate(()->{return (int)(Math.random()*10);});
        //infiniteStream.forEach(System.out::println);
        Stream<Integer> infiniteStream2= Stream.iterate(2,n->n+2);
        infiniteStream2.limit(10).forEach(System.out::println); //2,4,6,8,10 ...
    }
}
