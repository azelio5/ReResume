package main.lesson6;

import ru.anvar.webapp.storage.AbstractStorage;
import sun.text.normalizer.UTF16;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class FileMain {
    public static void main(String[] args) throws IOException {

        System.out.println("By stream iterator");
        Stream<String> lines = Files.lines(Paths.get("F:\\mine\\test.txt"), StandardCharsets.UTF_8);
        Iterator<String> it = lines.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("By stream lambda");

        Files.lines(Paths.get("F:\\mine\\test.txt"), StandardCharsets.UTF_8).forEach(System.out::println);//j8

        System.out.println("By stream oldstyle");

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\mine\\test.txt"), "UTF-8")); //j<7

        String line;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }

        System.out.println(AbstractStorage.FILE_STORAGE);




    }
}
