1.   Anonymous class : class which implements the interface under the hood and provide the impl.
2. Lambda function: instance of the class which implements the functional interface SAM internally.
3. Functional Interface SAM(Single Abstract Method) and default SAM in java
4. Predicate , BiPredicate  package: java.util.function
5. Supplier : when you want to supply values without any input
6. Consumer and BiConsumer: use the parameter but not interested in the return type
7. Function<T,R>, T: input R: return type, BiFunction<T1,T2,R>; input T1,T2 return R
8. UnaryOperator: add by 1 is a unary operator; BinaryOperator: multiplying, sub, add , div 2 numbers is a binary operator
   UnaryOperator<T> extends Function<T,T> is a functional interface. // T apply(T t)
   Concise version of Function, where input and output is T
   BinaryOperator<T> extends BiFunction<T,T,T> // T apply(T t1, T t2)
   Concise version of BiFun where inputs and o/p are same.
9. Final and effective final:  This apply to  the local variable inside the method and the variable has been used inside the lambda
   declaration. Compiler wont allow you to change it as the lambda has taken the snapshot of it and it should remain
   same , as lambda can be passed as parameter inside an method to execute the local variable value can used, and so
   there should not be any surprises. Effective final is final in all senses but haven't defined the keyword final.
10. Method references: to make expression more concise, removing redundancy from the code.
    - Four Types:
      - Bound: instance known at compile time
      - Unbound: instance provided at runtime
      - Static : also considered unbound
      - Constructor
    - with method references , context is the key. SAM created is important in determining context
    - compiler turns method references into lambdas in background.
- examples in lambda package
        