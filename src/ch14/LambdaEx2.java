package ch14;

public class LambdaEx2 {

    public static void main(String[] args) {
        MyFunction2 f  = () -> {};      //MyFunction2 f = (MyFunction2)(() -> {});
        Object obj = (MyFunction2)(() -> {});
        String str = ((Object)(MyFunction2)(() -> {})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

        // 컴파일러가 람다식의 타입을 어떤 형식으로 만들어 냈는지 알 수있음
        // '외부클래스이름$Lambda$번호'

    }



}

@FunctionalInterface    // 컴파일러가 함수형 인터페이스를 올바르게 정의하였는지 확인해주므로, 꼭 붙이도록 하자
interface MyFunction2 {      // 함수형 인터페이스 MyFunction을 정의
    void myMethod();    // public abstract void myMethod();
                        // ★ 함수형 인터페이스는 오직 하나의 추상 메서드만 정의되어 있어야 한다
                        // --> 람다식과 인터페이스의 메서드가 1:1로 연결되야 하기 때문임
}
