package me.hyunsoo.howtostream.api;

import me.hyunsoo.howtostream.beans.Kid;
import me.hyunsoo.howtostream.beans.MaleStudent;
import me.hyunsoo.howtostream.beans.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCustomCollector {

    public static void main(String[] args) {

        // 사용자 정의 컨테이너에 수집 하기
        List<Kid> kids = List.of(
                Kid.builder().name("A").score(10).sex(Kid.Sex.MALE).city(Kid.City.SEOUL).build(),
                Kid.builder().name("B").score(20).sex(Kid.Sex.FEMALE).city(Kid.City.PUSAN).build(),
                Kid.builder().name("C").score(15).sex(Kid.Sex.MALE).city(Kid.City.SEOUL).build(),
                Kid.builder().name("D").score(13).sex(Kid.Sex.FEMALE).city(Kid.City.PUSAN).build()
        );


        MaleStudent maleStudent = kids.stream()
                .filter(kid->kid.getSex() == Kid.Sex.MALE)
                .collect(
                        MaleStudent::new,
                        MaleStudent::accumulate,
                        MaleStudent::combine
                );

        maleStudent.getList().stream().forEach(System.out::println);


        //List를 HashMap 으로 변경 내가 원하는 조건으로 customizing
        HashMap<String, Kid> hashMap = kids.parallelStream()
                .filter(kid->kid.getSex() == Kid.Sex.MALE)
                .collect(
                        ()->{
                            System.out.println("HELLO~0");
                            return new HashMap<String, Kid>(); //Supplier
                        },
                        (map, kid)->{
                            System.out.println("HELLO~1");
                            map.put(kid.getName(), kid);    //accumulator
                        },
                        (map1, map2)->{
                            System.out.println("HELLO~2");
                            map2.forEach(map1::putIfAbsent); //combine
                        });
        System.out.println("hashMap : " + hashMap);
        hashMap.forEach((k, v)->{
            System.out.println("key : " + k + " val : " + v.getName() + " " + v.getSex());
        });


        //group by 시전해보기 - 1


        Function<Kid, Kid.Sex> classifier = Kid::getSex;

        Collector<Kid, ?, Map<Kid.Sex, List<Kid>>> collector = Collectors.groupingBy(classifier);

        Map<Kid.Sex, List<Kid>> kidsGroupBySex = kids.stream()
                .collect(collector);

        kidsGroupBySex.forEach((sex, list)->{
            System.out.println("sex : " + sex.name());
            list.stream().forEach(System.out::println);
        });


        //group by 시전해보기 - 2

        Map<Kid.City, List<String>> dic = kids.stream().collect(Collectors.groupingBy(Kid::getCity, Collectors.mapping(Kid::getName, Collectors.toList())));

        dic.forEach((k, v)->{
            System.out.println("key : " + k);
            v.stream().forEach(System.out::println);
        });

        //group by 시전해보기 - 3

        Map<Kid.City, List<String>> dic2 = kids.stream().collect(Collectors.groupingBy(
                Kid::getCity,
                ()->new HashMap<Kid.City, List<String>>(),
                Collectors.mapping(Kid::getName, Collectors.toList())
        ));



        dic2.forEach((k, v)->{
            System.out.println("key : " + k);
            v.stream().forEach(System.out::println);
        });

        Map<Kid.City, Double> dic3 = kids.stream().collect(Collectors.groupingBy(
                Kid::getCity,
                HashMap::new,
                Collectors.averagingDouble(Kid::getScore)
        ));


        dic3.forEach((k, v)->{
            System.out.println("key : " + k);
            System.out.println("avg score : " + v);
        });


        Map<Kid.City, Long> dic4 = kids.stream().collect(Collectors.groupingBy(Kid::getCity, Collectors.counting()));

        dic4.forEach((k, v)->{
            System.out.println("key : " + k);
            System.out.println("cnt : " + v);
        });

        Comparator<Kid> comparator = new Comparator<Kid>() {
            @Override
            public int compare(Kid k1, Kid k2) {
                if(k1.getScore() == k2.getScore()){
                    return 0;
                }else if(k1.getScore() < k2.getScore()){
                    return 1;
                }
                return -1;
            }
        };


        Map<Kid.City, Optional<Kid>> tmpMap = kids.stream()
                .collect(Collectors.groupingBy(Kid::getCity, Collectors.maxBy(comparator)));

        tmpMap.forEach((k, v)->{
            System.out.println("key : " + k.name());
            System.out.println(v.orElse(Kid.builder().build()));
        });




















    }
}
