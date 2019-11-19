package ch14;

import java.util.*;
import java.util.stream.Stream;


import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/***
 * 그룹화와 분할 - groupingBy(), partitioningBy()

 * groupingBy()는 스트림의 요소를 Function으로, partitioningBy()는 Predicate로 분류한다
 * partitioningBy() : 두 개의 그룹으로 나누어야 하는 경우 빠름
 * groupingBy() : 그 외의 경우에 사용
 */
public class StreamEx8 {

    /** [groupingBy] */
    public static void main(String[] args) {

        Student[] stuArr = {
                new Student("나자바", true, 1, 1, 300),
                new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true, 1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자바", true, 1, 2, 100),
                new Student("안지미", false, 1, 2, 50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true, 1, 3, 200),

                new Student("나자바", true, 2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true, 2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자바", true, 2, 2, 100),
                new Student("안지미", false, 2, 2, 50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true, 2, 3, 200)
        };

        System.out.printf("1. 단순그룹화(반별로 그룹화)%n");
        Map<Integer, List<Student>> stuByBan = Stream.of(stuArr)
                .collect(groupingBy(Student::getBan));                  // 기본적으로 List<T>에 담는다.
                //.collect(groupingBy(Student::getBan, toList()));      // 기본적으로 List<T>에 담는다. toList()가 생략된 형태
        for (List<Student> ban : stuByBan.values()) {
            for(Student s : ban) {
                System.out.println(s);
            }
        }


        System.out.printf("%n2. 단순그룹화(성적별로 그룹화)%n");
        Map<Student.Level, List<Student>> stuByLevel = Stream.of(stuArr)
                .collect(groupingBy(s -> {
                    if(s.getTotalScore() >= 200) return Student.Level.HIGH;
                    else if(s.getTotalScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }));

        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());
        for (Student.Level key : keySet) {
            System.out.println("[" + key + "]");
            for (Student s : stuByLevel.get(key)) {
                System.out.println(s);
            }
            System.out.println();
        }


        System.out.printf("%n3. 단순그룹화 + 통계(성적별 학생수)%n");
        Map<Student.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s -> {
                    if(s.getTotalScore() >= 200) return Student.Level.HIGH;
                    else if(s.getTotalScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }, counting()));

        for (Student.Level key : stuCntByLevel.keySet()) {
            System.out.printf("[%s]: %d명, ", key, stuCntByLevel.get(key));
        }


        System.out.printf("%n4. 다중그룹화(학년별, 반별)");
        Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan = Stream.of(stuArr)
                .collect(groupingBy(Student::getHak, groupingBy(Student::getBan)));

        for(Map<Integer, List<Student>> hak : stuByHakAndBan.values()) {
            for(List<Student> ban : hak.values()) {
                System.out.println();
                for(Student s : ban) {
                    System.out.println(s);
                }
            }
        }

        System.out.printf("%n5. 다중그룹화 + 통계(학년별, 반별 1등)%n");
        Map<Integer, Map<Integer, Student>> topStuByHackAndBan = Stream.of(stuArr)
                .collect(groupingBy(Student::getHak,
                        groupingBy(Student::getBan,
                            collectingAndThen(
                                maxBy(comparingInt(Student::getTotalScore)), Optional::get
                                )
                            )
                        )
                );

        for(Map<Integer, Student> ban : topStuByHackAndBan.values()) {
            for(Student s : ban.values()) {
                System.out.println(s);
            }
        }

        System.out.printf("%n6. 다중그룹화 + 통계(학년별, 반별 성적그룹)%n");
        Map<String, Set<Student.Level>> stuByScoreGroup = Stream.of(stuArr)
                .collect(groupingBy(s -> s.getHak() + "-" + s.getBan(),                // 학년, 반별 그룹화 후
                        mapping(s -> {                                                  // 성적그룹으로 mapping하여 set에 저장
                            if(s.getTotalScore() >= 200) return Student.Level.HIGH;
                            else if(s.getTotalScore() >= 100) return Student.Level.MID;
                            else return Student.Level.LOW;
                        }, toSet())
                ));

        Set<String> keySet2 = stuByScoreGroup.keySet();
        for(String key : keySet2) {
            System.out.println("[" +key+"]" + stuByScoreGroup.get(key));
        }




    }
}
