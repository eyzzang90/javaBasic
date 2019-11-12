package ch14;

import java.io.File;
import java.util.stream.Stream;

/**
 * 실행결과가 복잡해 지지 않도록 peek()를 넣지 않았는데, 직접 peek()를 넣어서 변경된 결과를 확인해 보아라.
 */
public class StreamEx2 {

    public static void main(String[] args) {

        File[] fileArr = {new File("StreamEx0.java"), new File("StreamEx1.java"), new File("StreamEx2.java")};
        Stream<File> fileStream = Stream.of(fileArr);

        // map()으로 Stream<File>을 Stream<String>으로 변환
        Stream<String> filenameStream = fileStream.map(File::getName);
        filenameStream.forEach(System.out::println);        // 모든 파일의 이름을 출력

        System.out.println("-------------------------");

        fileStream = Stream.of(fileArr);
        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.')!= -1)           // 확장자가 없는것 제외
                .peek(s -> System.out.printf("fileName: %s%n", s))
                .map(s -> s.substring(s.indexOf('.')+1))    // 확장자만 출력
                .peek(s -> System.out.printf("extension: %s%n", s))
                .map(String::toUpperCase)                   // 대문자로 변환
                .peek(s -> System.out.printf("toUpperCase: %s%n", s))
                .distinct()                                 // 중복 제거
                .forEach(System.out::println);


    }

}
