package com.wrpxcx.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wrp
 * @TODO: 精简jre
 * @time: 2020-06-02 20:39
 **/
public class EasyJre {
    private static String sourse = "C:/Users/asus/Desktop/RQ/class.txt";
    public static void main(String[] args) throws IOException {
        final List<String> list = new ArrayList<>();
        Files.readAllLines(Paths.get(sourse), Charset.forName("GBK")).forEach(s -> {
            if(s.contains("RQ") && !list.contains(s.split("RQ")[1])) {
                list.add(s.split("RQ")[1]);
            }
            //return list;
        });
        System.out.println(list);
    }

}
