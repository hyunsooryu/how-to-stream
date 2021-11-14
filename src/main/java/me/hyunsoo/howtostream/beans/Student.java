package me.hyunsoo.howtostream.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Student implements Comparable<Student>{
    private String name;
    private int score;

    @Override
    public int compareTo(Student student) {
        if(score < student.getScore()){
            return -1;
        }else if(score == student.getScore()){
            return 0;
        }
            return 1;
    }
}
