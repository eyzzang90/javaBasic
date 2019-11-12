package ch14;

public class LambdaEx3 {

    public static void main(String[] args) {

        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}

class Outer {

    int val = 10;       //Outer.this.val

    class Inner {

        int val = 20;   //this.val

        void method(int i) {        // void method(final int i)
            int val = 30;           // final int val = 30;
            i = 10;                 // 에러. 상수의 값 변경 불가.

            // System.out.println(i);      일반적인 상태로는 값 변경 가능.

/*            MyFunction3 f = () -> {
                System.out.println(" i : " + i);        // 람다식 내에서 참조하는 지역변수는 final이 붙지 않았어도 상수로 간주.
                System.out.println(" val : " + val);
                System.out.println(" this.val : " + ++this.val);
                System.out.println(" Outer.this.val : " + ++Outer.this.val);

            };

            f.myMethod();*/
        }
    }   // Innter 클래스 끝

}   // Outer 클래스 끝


@FunctionalInterface
interface MyFunction3 {
    void myMethod();
}
