package com.nt.jclearning8.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicStreams {
    public static void main(String[] args) {
        List<Integer> marks = Arrays.asList(90, 85, 92, 96, 84);
        System.out.println("Filtered marks count:" +
                marks
                        .stream()
                        .peek(System.out::println)
                        .filter(mark -> mark > 90)
                        .peek(System.out::println)
                        .limit(1)
                        .count()
        );

        creatingStreams();
        terminalOperation();
        intermediateOperation();
        primitiveStreams();
    }

    private static void primitiveStreams() {
        //IntStream, DoubleStream,LongStream
        IntStream intStream = Stream.of(1, 2, 3, 4).mapToInt(s -> s);
        System.out.println("IntStream:" + intStream.average());
    }

    private static void intermediateOperation() {
        //filter , distinct, limit
        Stream.of("eagle", "eagle", "EAGLE")
                .distinct()
                .forEach(s -> System.out.println(s));
        //map() is for transforming data
        //sorted(), sorted(Comparator<T>)
    }

    private static void terminalOperation() {
        //reductions
        Optional<String> min = Stream.of("apple", "mango", "grapes")
                .min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println);
        // not reductions
        Optional<String> any = Stream.of("apple", "mango", "grapes")
                .findAny();
        any.ifPresent(System.out::println);

        Optional<String> first = Stream.of("apple", "mango", "grapes")
                .findFirst();
        any.ifPresent(System.out::println);

        List<String> names = Arrays.asList("Alice", "Waltmore", "Cooper");
        Predicate<String> pred = val -> val.startsWith("C");
        System.out.println(names.stream().anyMatch(pred));
        System.out.println(names.stream().allMatch(pred));
        System.out.println(names.stream().noneMatch(pred));

        names.stream().forEach(System.out::println);

        //reduce

        Stream<Integer> numbers = Stream.of(1, 2, 3, 4);
        System.out.println("reduce terminal operation to addition of numbers:" +
                numbers
                        .reduce(10, (a, b) -> a + b));

        BinaryOperator<Integer> bo = (a, b) -> a + b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(1);
        Stream<Integer> multipleElements = Stream.of(1, 2, 3, 4);
        // reduce with binary operator only no identity
        empty.reduce(bo).ifPresent(System.out::println); //
        oneElement.reduce(bo).ifPresent(System.out::println); //1
        multipleElements.reduce(bo).ifPresent(System.out::println); //10

        // useful in parallel streams- the streams can be decomposed and reassembled by separate threads.
        Stream<String> stream = Stream.of("car", "bus", "train", "aeroplane");
        int length = stream
                .reduce(0,
                        (str1, str) -> str1 + str.length(),
                        (n1, n2) -> n1 + n2);
        System.out.println("total chars+" + length);

        //collect()
        // below case useful in parallel processing
        StringBuilder word = Stream.of("ad", "sds", "adas")
                .collect(() -> new StringBuilder(),
                        (sb, str) -> sb.append(str).append(","),
                        (sb1, sb2) -> sb1.append(sb2));
        System.out.println(word);

        // Collectors interface
        String s = Stream.of("apple", "mango", "cake")
                .collect(Collectors.joining(","));
        System.out.println(s);
        Double s1 = Stream.of("apple", "mango", "cake")
                .collect(Collectors.averagingInt(s2 -> s2.length()));
        System.out.println(s1);
        //Collectors.toMap()
        Map<String, Integer> map = Stream.of("apple", "mango", "cake")
                .collect(Collectors.toMap(s3 -> s3, s3 -> s3.length()));
        System.out.println(map);

        // duplicate keys: illegal state exception: appending the values : merge function
        Map<Integer, String> reverseMap = Stream.of("apple", "mango", "cake", "tart", "choco")
                .collect(Collectors.toMap(
                        s3 -> s3.length(),
                        s3 -> s3,
                        (s11, s22) -> s11 + "," + s22));
        System.out.println(reverseMap);

        // what if we want keys would be sorted : TreeMap
        TreeMap<Integer, String> treeMap = Stream.of("apple", "mango", "cake", "tart", "choco")
                .collect(Collectors.toMap(
                        s3 -> s3.length(),
                        s3 -> s3,
                        (s11, s22) -> s11 + "," + s22,
                        () -> new TreeMap<>()));
        System.out.println(treeMap);

        //Collectors.groupingBy()
        Stream<String> names1 = Stream.of("Chandler", "Joe", "Joe", "Monica");
        Map<Integer, List<String>> mapOfNames = names1.collect(Collectors.groupingBy(str -> str.length()));
        System.out.println(mapOfNames);

        // unique values in list
        Stream<String> names2 = Stream.of("Chandler", "Joe", "Joe", "Monica");
        Map<Integer, Set<String>> mapOfNames1 = names2
                .collect(Collectors.groupingBy(str -> str.length(), Collectors.toSet()));
        System.out.println(mapOfNames1);
        // keys sorted
        Stream<String> names3 = Stream.of("Chandler", "Joe", "Joe", "Monica");
        TreeMap<Integer, List<String>> mapOfNames2 = names3
                .collect(Collectors.groupingBy(str -> str.length(), TreeMap::new, Collectors.toList()));
        System.out.println(mapOfNames2);

        //partitioningBy
        Stream<String> names4 = Stream.of("Chandler", "Joe", "Joe", "Monica");
        Map<Boolean, List<String>> namesMap = names4.collect(Collectors.partitioningBy(s2 -> s2.startsWith("J")));
        System.out.println(namesMap);

    }

    private static void creatingStreams() {
        // from arrays
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        Stream<Integer> integerStream = Arrays.stream(integers);
        System.out.println("integer stream count:" + integerStream.count());

        //char stream
        String val = "helloword";
        Stream<Character> charStream = val.chars().mapToObj(s -> (Character) (char) s);
        String valJ = charStream.map(s -> String.valueOf(s)).collect(Collectors.joining(","));
        System.out.println(valJ);

        // First non repeating character
        String v1 = "pprogrnoiammaing";
        v1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(es -> es.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresentOrElse(ch -> System.out.println("first non repeating " + ch), () -> System.out.println("not present"));

        // from collection default
        List<String> fruitList = Arrays.asList("Apple", "Mango", "Orange");
        System.out.println("FruitList count" + fruitList.stream().count());

        Map<String, Integer> fruitsToPrices = new HashMap<>();
        fruitsToPrices.put("Apple", 50);
        fruitsToPrices.put("Orange", 30);
        System.out.println("fruits to price mapping count " +
                fruitsToPrices
                        .entrySet()// entry set give the set view of the map inherits the collection interface
                        .stream()
                        .count());
        // Stream.of()
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3);
        Stream<String> stringStream = Stream.of("A", "B");
        Stream<BasicStreams> objectStream = Stream.of(new BasicStreams());
        //infinite streams
        Stream<Integer> infiniteStream = Stream.generate(() -> {
            return (int) (Math.random() * 10);
        });
        //infiniteStream.forEach(System.out::println);
        Stream<Integer> infiniteStream2 = Stream.iterate(2, n -> n + 2);
        infiniteStream2.limit(10).forEach(System.out::println); //2,4,6,8,10 ...,20
    }
}
