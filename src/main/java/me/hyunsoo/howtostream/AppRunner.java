package me.hyunsoo.howtostream;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    final ResourceLoader resourceLoader;

    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:/linedata.txt");
        Path path = Paths.get(resource.getURI());
        Stream<String> pathStream = Files.lines(path, Charset.defaultCharset());
        pathStream.forEach(System.out::println);

        System.out.println("========================================");

        //BufferedReader lines() 이용
        File file = path.toFile();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Stream<String> bufferStream = bufferedReader.lines();
        bufferStream.forEach(System.out::println);


        System.out.println("========================================");
        Path _path = Paths.get("/Users/hyunsoo/Desktop/dev");

        Stream<Path> pathStream1 = Files.list(_path);
        pathStream1.forEach(p->System.out.println(p.getFileName()));



    }
}
