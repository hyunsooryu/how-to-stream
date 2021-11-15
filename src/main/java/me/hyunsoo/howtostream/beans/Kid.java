package me.hyunsoo.howtostream.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Kid {
    public enum Sex {MALE, FEMALE}
    public enum City {SEOUL, PUSAN}

    private String name;
    private int score;
    private Sex sex;
    private City city;

}
