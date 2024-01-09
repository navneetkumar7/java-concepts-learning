package com.nt.jclearning8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class BasicLambda {
    public static void main(String[] args) {
        //1.  Anonymous class : class which implements the interface under the hood and provide the impl.
        IBase iBase= new IBase() {
            @Override
            public void baseThings() {
                System.out.println("base things from Anonymous class");
            }
        };
        iBase.baseThings();
        //2. Lambda function: instance of the class which implements the functional interface SAM internally.
        IBase iBaseLambdaWay= ()-> System.out.println("base thing from lambda way");
        iBaseLambdaWay.baseThings();

        // 3. Functional Interface SAM(Single Abstract Method) and default SAM in java
        Evaluate<Integer> evaluate= t->t==0;
        System.out.println("Evaluate execution: isZeroCheck: "+evaluate.isZero(20));

        // 3.1 Predicate , BiPredicate  package: java.util.function
        Predicate<Integer> predicateZeroCheck= t->t==0;
        System.out.println("Predicate execution: isZeroCheck: "+predicateZeroCheck.test(0));

        BiPredicate<String, Integer> checkLength= (str,len)->str.length()==len;
        System.out.println("Bi Predicate execution: checkLength: "+checkLength.test("hello",0));

        //3.2 Supplier : when you want to supply values without any input

        Supplier<Integer> supplier= ()->2;
        System.out.println("supplier  execution: get: "+supplier.get());

        // 3.3 Consumer and BiConsumer: use the parameter but not interested in the return type
        Consumer<String> printConsumer= str->System.out.println(str);
        System.out.println("Consumer execution:");
        List<String> names= Arrays.asList("John","Shubham");
        names.forEach(printConsumer);
        BiConsumer<String, String> biConsumer=(fname,lname)->System.out.println("BiConsumer:"+fname+ lname);
        biConsumer.accept("Navneet","Thakur");

        //3.4 Function<T,R>, T: input R: return type, BiFunction<T1,T2,R>; input T1,T2 return R
        BiFunction<Integer,Integer,String> biFunction= (val, val1)->val==val1?"EQUAL":"Not EQUAL";
        System.out.println("BI Function Execution: "+ biFunction.apply(2,3));

        // 3.5 UnaryOperator: add by 1 is a unary operator; BinaryOperator: multiplying, sub, add , div 2 numbers is a binary operator
            // UnaryOperator<T> extends Function<T,T> is a functional interface. // T apply(T t)
        // Concise version of Function, where input and output is T
        // BinaryOperator<T> extends BiFunction<T,T,T> // T apply(T t1, T t2)
        //Concise version of BiFun where inputs and o/p are same;

        //Final and effective final: this is for the local variable inside the method and has been used inside the lambda
        // declaration, compiler wont allow you to change it as the lambda has taken the snapshot of it and it should remain
        // same , as lambda can be passed as parameter inside an method to execute the local variable value can used, and so
        // there should not be any surprises.

    }
}
