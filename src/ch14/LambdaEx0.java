package ch14;

public class LambdaEx0 {

    public static void main(String[] args) {

        MyFunction f = new MyFunction() {
            @Override
            public int max(int a, int b) {
                return a > b ? a : b;
            }
        };

        int  big = f.max(5, 3); // 익명 객체의 메서드를 호출

        MyFunction f1 = (a, b) -> a > b ? a : b;    // 익명 객체를 람다식으로 대체

        int big1 = f1.max(5, 3);


    }

}
interface MyFunction {
    public abstract int max(int a, int b);
}
