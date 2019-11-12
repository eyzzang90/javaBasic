package ch14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx5 {

    public static void main(String[] args) {

        // 매개변수 없고, 반환값만 있음 T get()
        Supplier<Integer> s = () -> (int)(Math.random()*100)+1;
        System.out.println(s.get());

        // Supplier과 반대로 매개변수만 있고, 반환값이 없음 void accept(T t)
        Consumer<Integer> c = i -> System.out.print(i+", ");
        c.accept(77);

        // 일반적인 함수, 하나의 매개변수를 받아서 결과 반환 R apply(T t)
        Function<Integer, Integer> f = i -> i/10*10;        // i의 1의 자리를 없앤다.
        System.out.println(f.apply(1));

        // 매개변수는 하나,조건식 표현, 반환타입은 boolean. boolean test(T t)
        Predicate<Integer> p = i -> i%2 == 0;
        System.out.println(p.test(5));


        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);
        System.out.println(list);

        printEvenNum(p, c, list);

        List<Integer> newList = doSomething(f, list);
        System.out.println(newList);



    }

    /**
     * 제네릭 메소드(generic method)
     *
     * 제네릭 메소드란 메소드의 선언부에 타입 변수를 사용한 메소드를 의미합니다.
     * 이때 타입 변수의 선언은 메소드 선언부에서 반환 타입 바로 앞에 위치합니다.
     */

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for(int i=0; i<10; i++) {
            list.add(s.get());
        }
    }

    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        System.out.print("[");
        for(T i : list) {
            if(p.test(i)) {
                c.accept(i);
            }
        }
        System.out.println("]");
    }

    static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<T>(list.size());
        for(T i : list) {
            newList.add(f.apply(i));
        }
        return newList;
    }


}
