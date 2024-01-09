# Java 8 features
## Lamda Functions and functional interfaces
1. Anonymous class : class which implements the interface under the hood and provide the impl.
2. Lambda function: instance of the class which implements the functional interface SAM internally.
3. Functional Interface SAM(Single Abstract Method) and default SAM in java:
   - Predicate , BiPredicate  package: java.util.function
   - Supplier : when you want to supply values without any input
   - Consumer and BiConsumer: use the parameter but not interested in the return type
   - Function<T,R>, T: input R: return type, BiFunction<T1,T2,R>; input T1,T2 return R
   - UnaryOperator: add by 1 is a unary operator; BinaryOperator: multiplying, sub, add , div 2 numbers is a binary operator
      UnaryOperator<T> extends Function<T,T> is a functional interface. // T apply(T t)
      Concise version of Function, where input and output is T
   - BinaryOperator<T> extends BiFunction<T,T,T> // T apply(T t1, T t2)
      Concise version of BiFun where inputs and o/p are same.
4. Final and effective final:  This apply to  the local variable inside the method and the variable has been used inside the lambda
   declaration. Compiler won't allow you to change it as the lambda has taken the snapshot of it and it should remain
   same , as lambda can be passed as parameter inside an method to execute the local variable value can used, and so
   there should not be any surprises. Effective final is final in all senses but haven't defined the keyword final.
5. Method references: to make expression more concise, removing redundancy from the code.
    - Four Types:
      - Bound: instance known at compile time
      - Unbound: instance provided at runtime
      - Static : also considered unbound
      - Constructor
    - with method references , context is the key. SAM created is important in determining context
    - compiler turns method references into lambdas in background.
### examples in lambda package

## Streams
1. Streams are all about processing data efficiently, streams do not hold data or not another way of organising data, like an array or a collection.
2. The **Pipeline**: A stream pipeline consists of operations that rin on a stream to produce a result
    - 3 parts to a stream pipeline:
      - **Source**- where the stream comes from e.g list 
      - **Intermediate operations**:transforms the stream into another one. e.g filter()
      - **Terminal operation**:required to start the stream processing and produce the result. e.g count() , foreach()
3. Streams can only be used once, once terminal operation executed ,streams are not longer usable
4. Streams are lazy, need terminal operation for processing.
5. Creating Streams:
    - Arrays.stream() : creating stream using array.
    - Default Collection interface method stream()
    - Stream.of() is a static generically-typed utility method that accepts a varargs parameter and returns an ordered stream of those values. **public static < T> Stream< T> of(T...values)**
    - Creating streams from a file: File.lines() method used to stream a file.It provides one ine at a time from the file as a data element in the stream. **public static Stream< String> lines(Path path)**
    - infinite streams: 
      - Stream.generate() : **public static < T> Stream< T> generate(Supplier< T> supplier)**
      - Stream.iterate(): **public static < T> Stream< T> iterate(T seed, UnaryOperator<T> fn)**
6. Terminal operation:
   - Terminal operations kick-start the stream processing.
   - Reductions are a special type of terminal operation where ALL of the contents of the stream are combined into a single primitive or Object e.g.long or collection.
     - count() ->long
     - min(Comparator),max(Comparator)->Optional<T>- stream may be empty
     - reduce()-> varies
     - collect()-> varies
   - Other terminal operations:
     - findAny(),findFirst()-> Optional<T>
     - allMatch(), anyMatch(), noneMatch()-> boolean
     - forEach()-> void
### examples in streams package