package me.hyunsoo.howtostream.api;


import me.hyunsoo.howtostream.beans.Student;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamCollector {

    public static void main(String[] args) {
        
        List.of(
                Student.builder().name("andy").score(95).build(),
                Student.builder().name("minji").score(82).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .filter(s->s.getScore() >= 90)
                .collect(Collectors.toList()).forEach(System.out::println);

        Collector<Student, ?, List<Student>> collector = Collectors.toList();

        List.of(
                Student.builder().name("andy").score(95).build(),
                Student.builder().name("minji").score(82).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .filter(s->s.getScore() >= 90)
                .collect(collector).forEach(System.out::println);


        Collector<Student, ?, HashSet<Student>> setCollector = Collectors.toCollection(()->{
            return new HashSet<Student>();
        });

       System.out.println("======================================================");

       HashSet<Student> studentHashSet =  List.of(
                Student.builder().name("andy").score(95).build(),
                Student.builder().name("minji").score(82).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .filter(student -> (StringUtils.hasLength(student.getName()) && student.getName().length() <= 4))
                .collect(setCollector);

       studentHashSet.stream().forEach(System.out::println);

        Collector<Student, ?, ConcurrentMap<String, Integer>> mapCollector = Collectors.toConcurrentMap(Student::getName, Student::getScore);

        ConcurrentMap<String, Integer> map =  List.of(
                Student.builder().name("andy").score(95).build(),
                Student.builder().name("minji").score(82).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .collect(mapCollector);

        map.forEach((k, v)->{
            System.out.println(k + " : " + v);
        });


        Collector<Student, ?, Map<String,Integer>> hashMapCollector = Collectors.toMap(Student::getName, Student::getScore);

        Map<String, Integer> tmpMap =  List.of(
                Student.builder().name("andy").score(95).build(),
                Student.builder().name("minji").score(82).build(),
                Student.builder().name("koki").score(100).build()
        ).stream().collect(hashMapCollector);

    }
}
