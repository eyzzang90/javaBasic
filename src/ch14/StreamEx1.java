package ch14;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamEx1{

    public static void main(String[] args) {

        Stream<Student> studentStream = Stream.of(
                new Student("김자바", 3, 300),
                new Student("콩자반", 1, 200),
                new Student("자바칩", 1, 100),
                new Student("자비스", 2, 200),
                new Student("감자칩", 3, 180)
        );

        studentStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder())
        );

    }

}

class Student implements Comparable<Student> {

    String name;
    int ban;
    int totalScore;

    public int getBan() {
        return ban;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ban=" + ban +
                ", totalScore=" + totalScore +
                '}';
    }
}


