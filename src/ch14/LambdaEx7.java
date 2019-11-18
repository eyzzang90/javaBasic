package ch14;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Function의 합성과 Predicate의 결합
 */
public class LambdaEx7 {

    public static void main(String[] args) {


        /** [Function의 합성] */

        Function<String, Integer> f = (s) -> Integer.parseInt(s, 16);       // 문자열을 16진수로 변경
        Function<Integer, String> g = (i) -> Integer.toBinaryString(i);            // 숫자를 2진 문자열로 변환

        Function<String, String> h = f.andThen(g);              // 함수 f를 먼저 적용, 그 다음에 함수 g를 적용
        Function<Integer, Integer> h2 = f.compose(g);           // 함수 g를 먼저 적용, 그 다음에 함수 f를 적용

        System.out.println(h.apply("FF"));      // "FF" -> 255 -> "11111111"
        System.out.println(h2.apply(2));        // 2 -> "10" -> 16

        Function<String, String> f2 = x -> x;               // 항등함수(identity function)
//        Function<String, String> f2 = Function.identity();
        System.out.println(f2.apply("AAA"));

        /** [Predicate의 결합] */

        Predicate<Integer> p = i -> i < 100;
        Predicate<Integer> q = i -> i < 200;
        Predicate<Integer> r = i -> i%2 == 0;
        Predicate<Integer> notP = p.negate();       // i>= 100

        Predicate<Integer> all = notP.and(q.or(r)); // 100보다 크거나 같고, 200보다 작거나 짝수
        System.out.println(all.test(150));
        System.out.println(all.test(151));
        System.out.println(all.test(222));
        System.out.println(all.test(223));

        String str1 = "abc";
        String str2 = "abc";

        Predicate<String> p2 = Predicate.isEqual(str1);
        boolean result = p2.test(str2);
        System.out.println(result);

    }

}
