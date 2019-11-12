package ch14;

import java.io.File;
import java.util.Arrays;
import java.util.List;
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


    }

}
