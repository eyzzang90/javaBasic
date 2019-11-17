package ch14;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/***
 * 그룹화와 분할 - groupingBy(), partitioningBy()

 * groupingBy()는 스트림의 요소를 Function으로, partitioningBy()는 Predicate로 분류한다
 * partitioningBy() : 두 개의 그룹으로 나누어여 하는 경우 빠름
 * groupingBy() : 그 외의 경우에 사용
 */
public class StreamEx7 {

    /** [partitioningBy] */
    public static void main(String[] args) {

        Student[] stuArr = {
                new Student("나자바", true, 1, 1, 300),
                new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true, 1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자마", true, 1, 2, 100),
                new Student("안지미", false, 1, 2, 50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true, 1, 3, 200),

                new Student("나자바", true, 2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true, 2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자마", true, 2, 2, 100),
                new Student("안지미", false, 2, 2, 50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true, 2, 3, 200)
        };

        System.out.printf("1. 단순분할(성별로 분할) %n");
        Map<Boolean, List<Student>> stuBySex = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale));       // 학생을 성별로 분할

        List<Student> maleStudent = stuBySex.get(true);
        List<Student> femaleStudent = stuBySex.get(false);

        System.out.println("-남---------------------");
        for(Student s : maleStudent) {
            System.out.println(s);
        }
        System.out.println("-여---------------------");
        for (Student s : femaleStudent) {
            System.out.println(s);
        }


        System.out.printf("%n2. 단순분할 + 통계(성별 학생수) %n");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale, Collectors.counting()));    // 성별 + 수

        System.out.println("남학생 수 : " + stuNumBySex.get(true));
        System.out.println("여학생 수 : " + stuNumBySex.get(false));


        System.out.printf("%n3. 단순분할 + 통계(성별 1등)%n");
        Map<Boolean, Optional<Student>> topScoreBySex = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale, maxBy(comparingInt(Student::getTotalScore))));

        System.out.println("남학생 1등: " + topScoreBySex.get(true));
        System.out.println("여학생 1등: " + topScoreBySex.get(false));


        Map<Boolean, Student> topScoreBySex2 = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,
                        collectingAndThen(      // Student를 반환결과로 얻기위해, Optional::get과 사용
                            maxBy(comparingInt(Student::getTotalScore)), Optional::get
                        )
                ));

        System.out.println("남학생 1등: " + topScoreBySex2.get(true));
        System.out.println("여학생 1등: " + topScoreBySex2.get(false));


        System.out.printf("%n4. 다중분할(성별 불합격자, 100점 이하)%n");
        Map<Boolean, Map<Boolean, List<Student>>> failedStuBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,
                        partitioningBy(s -> s.getTotalScore() <= 100)));    // partitioninBy를 두번 사용한다.
        List<Student> failedMaleStu = failedStuBySex.get(true).get(true);
        List<Student> failedFemaleStu = failedStuBySex.get(false).get(false);

        System.out.println("-남---------------------");
        for(Student s : failedMaleStu) {
            System.out.println(s);
        }
        System.out.println("-여---------------------");
        for (Student s : failedFemaleStu) {
            System.out.println(s);
        }



    }
}

