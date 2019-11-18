package ch14;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

/**
 * 함수형 인터페이스 사용 예제를 기본형을 사용하는 함수형 인터페이스로 변경
 */
public class LambdaEx6 {

    public static void main(String[] args) {

        IntSupplier s = () -> (int)(Math.random()*100)+1;
        IntConsumer c = i -> System.out.print(i+", ");
        IntPredicate p = i -> i%2 == 0;
        IntUnaryOperator op = i -> i/10*10;     // Function<Integer, Integer> 혹은 IntFunction<Integer>을 사용할 수 있지만,
                                                // IntUnaryOperator가 오토박싱&언박싱 횟수가 줄어들어 성능이 더 좋다

        int[] arr = new int[10];

        makeRandomList(s, arr);
        System.out.println(Arrays.toString(arr));
        printEvenNum(p, c, arr);
        int[] newArr = doSomething(op, arr);
        System.out.println(Arrays.toString(newArr));

    }

    static void makeRandomList(IntSupplier s, int[] arr) {
        for(int i=0; i<arr.length; i++) {
            arr[i] = s.getAsInt();      // s.get()이 아님
        }
    }

    static void printEvenNum(IntPredicate p, IntConsumer c, int[] arr) {
        System.out.print("[");
        for(int i : arr) {
            if(p.test(i)) {
                c.accept(i);
            }
        }
        System.out.println("]");
    }

    static int[] doSomething(IntUnaryOperator op, int[] arr) {
        int[] newArr = new int[arr.length];

        for(int i=0; i<newArr.length; i++) {
            newArr[i] = op.applyAsInt(arr[i]);  // op.apply()가 아님
        }
        return newArr;
    }
}

