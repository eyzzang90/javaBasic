package ch14;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx3 {

    public static void main(String[] args) {

        Student[] studentArray = {
                new Student("김자바", 3, 300),
                new Student("콩자반", 1, 200),
                new Student("자바칩", 1, 100),
                new Student("자비스", 2, 200),
                new Student("감자칩", 3, 180)
        };

        Stream<Student> studentStream = Stream.of(studentArray);

        studentStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);


        studentStream = Stream.of(studentArray);    // 스트림 다시 생성
        IntStream studentScoreStream = studentStream.mapToInt(Student::getTotalScore);

        IntSummaryStatistics stat = studentScoreStream.summaryStatistics();
        System.out.println("count: " + stat.getCount());
        System.out.println("sum: " + stat.getSum());
        System.out.println("average: " + stat.getAverage());
        System.out.println("max: " + stat.getMax());
        System.out.println("min: " + stat.getMin());


    }



}
