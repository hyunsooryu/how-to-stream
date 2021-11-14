package me.hyunsoo.howtostream.api;

import me.hyunsoo.howtostream.beans.Student;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice1 {
    public static void main(String[] args) throws IOException {
        //1. Collection으로부터 스트림 얻기
        List<Student> students = List.of(
                Student.builder().name("andy").score(98).build(),
                Student.builder().name("minji").score(99).build(),
                Student.builder().name("koki").score(100).build()
            );

        Stream<Student> studentStream = students.stream();

        studentStream.forEach(s->System.out.println(s.getName()));



        //2. 배열로 부터 스트림 얻기
        String[] names = {"Andrew","Jenny","Matt"};
        Stream<String> nameStream = Arrays.stream(names);
        nameStream.forEach(name->System.out.println(name));


        int[] intArray = {1,2,3,4,5};
        IntStream intStream = Arrays.stream(intArray);

        intStream.forEach(i->System.out.println(i + " "));

        //3. 숫자 범위로부터 스트림 얻기
        IntStream stream = IntStream.rangeClosed(1, 100);
        //stream.forEach(System.out::println);
        int sum = stream.sum();
        System.out.println(sum);

        //4. 파일로 부터 스트림 얻기

        Path path = Paths.get("/Users/hyunsoo/Desktop/dev/how-to-stream/src/main/resources/linedata.txt");
        Stream<String> pathStream;

        pathStream = Files.lines(path, Charset.defaultCharset());
        pathStream.forEach(System.out::println);


    }
}
