package ch14;

import java.io.File;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamEx0 {

    public static void main(String[] args) {

        String[] strArr = {"aaa", "ccc", "bbb"};
        List<String> strList = Arrays.asList(strArr);

        for(String str : strArr) {
            System.out.println(str);
        }

        for(String str : strList) {
            System.out.println(str);
        }

        // 이전의 정렬 && 출력 방식
        System.out.println("====이전의 정렬 && 출력 방식=====");

        Arrays.sort(strArr);
        for(String str : strArr) {
            System.out.println(str);
        }


        for(String str : strList) { // static 메서드이므로 아래 List<String>까지 sorting이 변한다.
            System.out.println(str);
        }


        String[] strArr2 = {"aaa", "ccc", "bbb"};
        List<String> strList2 = Arrays.asList(strArr2);

        // 스트림 생성
        Stream<String> strStream2 = Arrays.stream(strArr2);
        Stream<String> strStream1 = strList2.stream();


        // 스트림을 활용한 정렬 && 출력 방식
        System.out.println("====스트림을 활용한 정렬 && 출력 방식=====");

        strStream2.sorted().forEach(System.out::println);   // 일회성이여서 영향이 없음(?)
        strStream1.forEach(System.out::println);

        /** [컬렉션] */

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> intStream =  list.stream();
        Stream<Integer> paralleStream = list.parallelStream();  // 병렬 처리 스트림

        intStream.forEach(System.out::println);     // 스트림의 모든 요소 출력
        //intStream.forEach(System.out::println);     // 에러. 스트림은 일회성, 이미 닫혀있음
        paralleStream.forEach(System.out::println);

        /** [배열] */

        String [] array = new String[]{"배열", "스트림", "테스트", "입니다"};
        Stream<String> stream = Stream.of(array);
        Stream<String> arrayStream = Arrays.stream(array);
        Stream<String> streamOfArrayPart = Arrays.stream(array, 1, 3);  // 1~2요소 [스트림, 테스트]

        streamOfArrayPart.forEach(System.out::println);

        /** [특정 범위의 정수] */

        IntStream.range(1, 6);          // [1, 2, 3, 4, 5]
        IntStream.rangeClosed(1, 6);    // [1, 2, 3, 4, 5, 6]

        LongStream.range(1, 8);         // [1, 2, 3, 4, 5, 6, 7]
        LongStream.rangeClosed(1, 8);   // [1, 2, 3, 4, 5, 6, 7, 8]

        /** [임의의 수] */

        IntStream intStream1 = new Random().ints();
        //intStream1.forEach(System.out::println);            // 무한 스트림
        intStream1.limit(5).forEach(System.out::println);   // 유한 스트림(5개만 출력)

        IntStream intStream2 = new Random().ints(5);    // 크기가 5인 난수 스트림
        intStream2.forEach(System.out::println);

        // 지정된 범위
        IntStream intStream3 = new Random().ints(5, 1, 11);
        intStream3.forEach(System.out::println);


        /** [Stream.iterate()] */
        Stream<Integer> iteratedStream = Stream.iterate(30, n -> n+2).limit(5);     // [30, 32, 34, 36, 38]
        iteratedStream.forEach(System.out::println);

        /** [Stream.gererate()] */
        Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
        generatedStream.forEach(System.out::println);

        /** [두 스트림의 연결] */
        String[] firstHalf = {"1월", "2월", "3월", "4월", "5월", "6월"};
        String[] secondHalf = {"7월", "8월", "9월", "10월", "11월", "12월"};

        Stream<String> strs1 = Stream.of(firstHalf);
        Stream<String> strs2 = Stream.of(secondHalf);
        Stream<String> strs3 = Stream.concat(strs1, strs2);
        System.out.println(strs3.count());

        /** [빈 스트림] */
        Stream emptyStream = Stream.empty();
        long count = emptyStream.count();
        System.out.println(count);


        /** [정렬] */

        Stream<String> strStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        strStream.sorted().forEach(System.out::print);                                  // 기본정렬 [CCaaabbcdd]
        System.out.println();

        strStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        strStream.sorted(Comparator.naturalOrder()).forEach(System.out::print);        // 기본정렬 [CCaaabbcdd]
        System.out.println();

        strStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        strStream.sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::print);      // 반환이 int인 람다식도 가능
        System.out.println();

        strStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        strStream.sorted(String::compareTo).forEach(System.out::print);
        System.out.println();

        Stream<String> reverseStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        reverseStream.sorted(Comparator.reverseOrder()).forEach(System.out::print);     // 기본정렬 역순
        System.out.println();

        Stream<String> insentiveStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        insentiveStream.sorted(String.CASE_INSENSITIVE_ORDER).forEach(System.out::print);   // 대소문자 구분 안함
        System.out.println();

        Stream<String> lengthStream = Stream.of("dd", "CC", "aaa", "bb", "c");
        lengthStream.sorted(Comparator.comparing(String::length)).forEach(System.out::print);   // 길이 순 정렬


        /** 변환 map() */
        // 파일 스트림 생성
        Stream<File> fileStream = Stream.of(new File("StreamEx1.java"), new File("StreamEx0.java"));
        // map()으로 Stream<File>을 Stream<String>으로 변환
        Stream<String> filenameStream = fileStream.map(File::getName);
        filenameStream.forEach(System.out::println);

        fileStream = Stream.of(new File("StreamEx1.java"), new File("StreamEx0.java"));
        Stream<String> fileAbsolutePathStream = fileStream.map(File::getAbsolutePath);
        fileAbsolutePathStream.forEach(System.out::println);


        /** 조회 peek() */
        fileStream = Stream.of(new File("StreamEx1.java"), new File("StreamEx0.java"));
        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.') != -1)      // 확장자 없는것은 제외
                .peek(s -> System.out.printf("fileName : %s%n", s))     // 파일명 출력
                .map(s -> s.substring(s.indexOf('.')+1))                // 확장자만 추출
                .peek(s -> System.out.printf("extension: %s%n", s))     // 확장자를 출력
                .forEach(System.out::println);

        /** mapToInt, mapToLong, mapToDouble */

        Student[] students = {new Student("김자바", 3, 300),
                new Student("콩자반", 1, 200),
                new Student("자바칩", 1, 100),
                new Student("자비스", 2, 200),
                new Student("저거봐", 3,  180)
        };

        Stream<Student> studentStream = Stream.of(students);
        Stream<Integer> studentScoreStream1 = studentStream.map(Student::getTotalScore);
        studentStream = Stream.of(students);
        IntStream studentScoreStream2 = studentStream.mapToInt(Student::getTotalScore);

        // 최종연산 sum() , average() -> 스트림이 닫혀서 한번만 호출이 가능
//        int totalScore =  studentScoreStream2.sum();
//        OptionalDouble averageScore = studentScoreStream2.average();

        // summaryStatistics 사용
        IntSummaryStatistics stat = studentScoreStream2.summaryStatistics();

        long totalScore = stat.getSum();
        double average =  stat.getAverage();

        System.out.println("totalScore : " + totalScore +", average : " + average);


        // 로또번호를 생성해서 출력하는 코드
        Set<Integer> lotto = new HashSet<>();
        while(true) {
            int ranNum = (int)(Math.random()*45)+1;
            if(lotto.add(ranNum)) {
                if(lotto.size() == 6) {
                    break;
                }
            }
        }
        List<Integer> lottoList = new ArrayList<>(lotto);
        Collections.sort(lottoList);
        for(int num : lottoList) {
            System.out.print(num + ",");
        }

        System.out.println("------------------------");

        IntStream intStream4 = new Random().ints(1, 46);
        IntStream lottoStream =  intStream4.limit(6).distinct().sorted();
        Stream<String> strLottoStream = lottoStream.mapToObj(i -> i+",");
        strLottoStream.forEach(System.out::print);

        System.out.println();

        /** [Optional 객체 생성] */
        String str = "abc";
        Optional<String> optVal1 = Optional.of(str);
        Optional<String> optVal2 = Optional.of("abc");
        Optional<String> optVal3 = Optional.of(new String("abc"));

        str = null;
//        Optional<String> optVal4 = Optional.of(str);    // NullPointerException
        Optional<String> optVal5 = Optional.ofNullable(str);

        Optional<String> optVal6 = null;
        Optional<String> optVal7 = Optional.<String>empty();    // 빈 객체로 초기화 하는것이 바람직

        /** [Optional 객체 값 가져오기] */
//        Optional<String> optVal8 = Optional.of("abc");
        Optional<String> optVal8 = Optional.ofNullable(null);
//        String str1 = optVal8.get();
        String str2 = optVal8.orElse("널이다");
        System.out.println(str2);

        String str3 = optVal8.orElseGet(String::new);                   // Null 이면 Null 을 대체할 값 반환
        String str4 = optVal8.orElseThrow(NullPointerException::new);   // Null 이면 지정된 예외 발생





    }

}

