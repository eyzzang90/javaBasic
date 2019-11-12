package ch14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LambdaEx4 {

    public static void main(String[] args) {

        // Predicate Test
        Predicate<String> isEmptyStr = s -> s.length() == 0;
        String s = "";

        if(isEmptyStr.test(s)) {    // if(s.length() == 0)
            System.out.println("This is an empty String.");
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<10; i++) {
            list.add(i);
        }

        // list의 모든 요소를 출력
        list.forEach(i -> System.out.print(i+", "));
        System.out.println();

        // list에서 2또는 3의 배수를 제거한다.
        list.removeIf(x -> x%2 == 0 || x%3 == 0);
        System.out.println(list);

        // list의 각 요소에 10을 곱한다.
        list.replaceAll(i -> i*10);
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("월", "파란체크남방");
        map.put("화", "파랑셔츠");
        map.put("수", "초록체크남방");
        map.put("목", "흰색셔츠");
        map.put("금", "청남방");

        // map의 모든 요소를 {k, v}의 형식으로 출력한다.
        map.forEach((k, v) -> System.out.print("{"+ k +", "+ v +"}, "));
        System.out.println();
    }

}


@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}