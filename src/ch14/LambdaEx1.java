package ch14;

public class LambdaEx1 {

    static void execute (MyFunction1 f) {     // 매개변수의 타입이 MyFunction인 메서드
        f.run();                              // MyFunction에 정의된 메서드 호출
    }

    static MyFunction1 getMyFunction() {      // 반환 타입이 MyFunction인 메서드
        MyFunction1 f = () -> System.out.println("f3.run()");       // 함수형 인터페이스의 추상메서드와 동등한 람다식을 가리키는 참조변수 반환 가능
        return f;
//        return () -> System.out.println("f3.run()");              // 람다식 직접반환 가능
    }

    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run() 구현
        MyFunction1 f1 = () -> System.out.println("f1.run()");

        MyFunction1 f2 = new MyFunction1() {      // 익명클래스로 run()을 구현
            @Override
            public void run() {                 // public을 반드시 붙여야함
                System.out.println("f2.run()");
            }
        };

        MyFunction1 f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);                                 // 람다식을 참조하는 참조변수를 매개변수로 지정
        execute(()-> System.out.println("run()"));   // 참조변수 없이 직접 람다식을 매개변수로 지정하는것도 가능
    }

}

@FunctionalInterface
interface MyFunction1 {
    void run();
}
