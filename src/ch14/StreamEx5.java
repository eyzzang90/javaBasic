package ch14;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 최종연산
 */
public class StreamEx5 {

    public static void main(String[] args) {

        String[] strArr = {"Inheritance", "Java", "Lambda", "stream", "OptionalDouble"};

        Stream.of(strArr).forEach(System.out::println);         // 스트림의 요소 소모

        boolean noEmptyStr = Stream.of(strArr).noneMatch(s -> s.length()==0);
        System.out.println("noEmptyStr : " + noEmptyStr);

        Optional<String> sword = Stream.of(strArr)
                                    .filter(s -> s.charAt(0) == 's').findFirst();

        System.out.println("sword: " + sword.get());

        /** Stream<String>을 IntStream으로 변환 */
        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream5 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream6 = Stream.of(strArr).mapToInt(String::length);

        int count = intStream1.reduce(0, (a,b) -> a+1);
        System.out.println("count : " + count);

        int sum = intStream2.reduce(0, (a,b) -> a+b);
        System.out.println("sum : " + sum);

        OptionalInt max = intStream3.reduce(Integer::max);  // int max(int a, int b)
        System.out.println("max : " + max.getAsInt());

        OptionalInt max2 = intStream4.reduce((a,b) -> a > b ? a : b);
        System.out.println("max2 : " + max2.getAsInt());

        OptionalInt min = intStream5.reduce(Integer::min);  // int min(int a, int b)
        System.out.println("min : " + min.getAsInt());

        OptionalInt min2 = intStream6.reduce((a,b) -> a < b ? a : b);
        System.out.println("min2 : " + min2.getAsInt());
    }
}
