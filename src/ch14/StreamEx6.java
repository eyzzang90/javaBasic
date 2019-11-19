package ch14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamEx6 {

    public static void main(String[] args) {

        Student[] stuArr = {
          new Student("이자바", 3, 300),
          new Student("김자바", 1, 200),
          new Student("안자바", 2, 100),
          new Student("박자바", 2, 150),
          new Student("소자바", 1, 200),
          new Student("나자바", 3, 290),
          new Student("감자바", 3, 180),
        };

        /** [학생 이름만 뽑아서 List<String>에 저장] */
        List<String> names = Stream.of(stuArr).map(Student::getName)
                                .collect(Collectors.toList());      // toList()로 스트림의 모든 요소를 컬렉션에 수집

        ArrayList<String> nameList = names.stream()
                                    .collect(Collectors.toCollection(ArrayList::new));  // toCollection()에 컬렉션의 생성자 참조를 매개변수로 넣어
                                                                                        // 특정 컬렉션 지정

        /** [스트림을 배열로 변환] */
        Student[] strArr2 = Stream.of(stuArr).toArray(Student[]::new);
        for(Student s : strArr2) {
            System.out.println(s);
        }

        /** [스트링림을 Map<String, Student>로 변환, 학생 이름이 key] */
        Map<String, Student> stuMap = Stream.of(stuArr)
                                        .collect(Collectors.toMap(s -> s.getName(), p -> p));
        for(String name : stuMap.keySet()) {
            System.out.println(name+": " + stuMap.get(name));
        }

        /** [통계 - counting] */
        long count = Stream.of(stuArr).collect(counting());     // Collectors.counting()
        System.out.println("count: " + count);

        /** [통계 - summingInt] */
        long totalScore = Stream.of(stuArr)
                            .collect(summingInt(Student::getTotalScore));       // Collectors.summingInt
        System.out.println("totalScore: " + totalScore);

        /** [통계 - maxBy] */
        Optional<Student> topStudent = Stream.of(stuArr)
                .collect(maxBy(Comparator.comparingInt(Student::getTotalScore)));
        System.out.println("topStudent: " + topStudent.get());

        /** [통계 - summarizingInt] */
        IntSummaryStatistics stat = Stream.of(stuArr)
                .collect(summarizingInt(Student::getTotalScore));       // Collectors.summarizingInt
        System.out.println(stat);

        /** [리듀싱 - reducing] */
        totalScore = Stream.of(stuArr)
                        .collect(reducing(0, Student::getTotalScore, Integer::sum));

        System.out.println("totalScore: " + totalScore);


        /** [문자열 결함 - joining()]
         *  스트림의 요소가 문자열이 아닌 경우, map()을 이용해서 스트림의 요소를 문자열로 변환
         * */
        String stuNames = Stream.of(stuArr).map(Student::getName).collect(joining(",", "{", "}"));
        System.out.println(stuNames);
    }
}
