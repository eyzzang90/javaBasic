package ch14;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/***
 * Collector 인터페이스 구현하기
 */
public class CollectorEx1 {

    public static void main(String[] args) {

        String[] strArr = {"aaa", "bbb", "ccc"};
        Stream<String> stringStream = Stream.of(strArr);

        String result = stringStream.collect(new ConcatCollector());

        System.out.println(Arrays.toString(strArr));
        System.out.println("result : " + result);

    }
}

class ConcatCollector implements Collector<String, StringBuilder, String> {

    /** 작업 결과를 저장할 공간을 제공 */
    @Override
    public Supplier<StringBuilder> supplier() {
        return () -> new StringBuilder();
//        return StringBuilder::new;
    }

    /** 스트림의 요소를 수집(collect)할 방법을 제공 */
    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb, s) -> sb.append(s);
//        return StringBuilder::append;
    }

    /** 두 저장공간을 병합할 방법을 제공(병렬 스트림) */
    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (sb, sb2) -> sb.append(sb2);
//        return StringBuilder::append;
    }

    /** 결과를 최종적으로 변환할 방법을 제공 */
    @Override
    public Function<StringBuilder, String> finisher() {
        return sb -> sb.toString();
//        return StringBuilder::toString;
    }

    /** 컬렉터가 수행하는 작업의 속성에 대한 정보를 제공
     * Characteristics.CONCURRENT : 병렬로 처리할 수 있는 작업
     * Characteristics.UNORDERED : 스트림의 요소의 순서가 유지될 필요가 없는 작업
     * Characteristics.IDENTITY_FINISH : finisher()가 항등 함수인 작업 (작업결과 변환이 필요X, identity() return x -> x)
     * */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.CONCURRENT,
                Characteristics.UNORDERED
        ));
//        return Collections.emptySet();      // 지정할 특성이 없는 경우
    }
}
