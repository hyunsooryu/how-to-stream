package me.hyunsoo.howtostream.api;


import me.hyunsoo.howtostream.beans.Person;
import me.hyunsoo.howtostream.beans.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPipeLine {

    public static void main(String[] args) {
        /**
         *   대량의 데이터를 가공해서, 축소하는 것을 일반적으로 리덕션이라고 하는데,
         *   데이터의 합계, 평균값, 카운팅, 최대값, 최소값 등이 대표적인 리덕션의 결과물이라호 볼 수 있다.
         *   그러나, 컬렉션의 요소를 리덕션 한 결과물로 바로 집계할 수 없는 경우에는, 집계하기 좋도록,
         *   필터링, 맵핑, 정렬, 그룹핑 등의 중간처리가 필요하다.
         */

        //1. flatMap - 1)
        List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
        ).stream().flatMap(student->List.of(student.getName()).stream())
                .forEach(System.out::println);

        //1. flatMap - 2)
        List<String> nums = Arrays.asList("10, 20, 30", "40, 50, 60");
        nums.stream()
                .flatMapToInt(str->{
                   String[] arr = str.split(",");
                   int[] new_arr = new int[arr.length];
                   int i = 0;
                   for(String target : arr){
                       new_arr[i++] = Integer.valueOf(target.trim());
                   };
                   return Arrays.stream(new_arr);
                }).forEach(i -> System.out.println(i));


        //2. mapXXX - 1)
        List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .map(Student::getScore)
                .forEach(System.out::println);

        //2. mapXXX - 2)
        List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .map(student -> {
                    Person person = new Person();
                    person.setName(student.getName());
                    return person;
                }).map(Person::getName)
                .forEach(System.out::println);


        int[] intArray = {1,2,3,4,5,6};
        Arrays.stream(intArray)
                .asDoubleStream()
                .forEach(System.out::println);

        Arrays.stream(intArray)
                .boxed()
                .forEach(obj->System.out.println(obj.intValue()));



        //3.sorted
        List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .sorted((Student s1, Student s2)->{
                  if(s1.getScore() < s2.getScore()){
                      return 1;
                  }else if(s1.getScore() == s2.getScore()){
                      return 0;
                  }
                  return -1;
                }).forEach(S->System.out.println(S.toString()));


        System.out.println("============================");

        boolean hasQ =
        List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .sorted(Comparator.naturalOrder())
                .noneMatch(student -> {
                    return student.getName().contains("q");
                });

        System.out.println(hasQ);


        //기본 집계
        long count = Arrays.stream(new int[]{1,2,3,4,5})
                .filter(n->n%2==0)
                .count();

        System.out.println("2의 배수 : " + count);

        long sum = Arrays.stream(new int[]{1,2,3,4,5})
                .filter(n->n%2==0)
                .sum();

        System.out.println("짝수의 합 : " + sum);

        double avg = Arrays.stream(new int[]{1,2,3,4,5})
                .average().getAsDouble();

        System.out.println("평균 값 : " + avg);

        int max = Arrays.stream(new int[]{1,2,3,4,5})
                .max().orElseGet(()->{
                    return 1;
                });

        System.out.println("최대 값 : " + max);

        int tmpMax = 0;

        Arrays.stream(new int[]{})
                .max().ifPresentOrElse((val)->{
                    System.out.println(val);
        }, ()->{
                    System.out.println("no value");
        });


        int _sum1 = List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
        ).stream()
                .mapToInt(Student::getScore)
                .reduce(0,(score1, score2)->{
                    return score1 + score2;
                });

        System.out.println(_sum1);

        int _sum2 = List.of(
                Student.builder().name("andy").build(),
                Student.builder().name("minji").score(10).build(),
                Student.builder().name("koki").score(12).build()
        ).stream()
                .mapToInt(Student::getScore)
                .reduce((score1, score2)->score1+score2)
                .orElse(0);
        System.out.println(_sum2);





    }















}
