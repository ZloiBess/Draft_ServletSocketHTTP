package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Field;

public class MyParserJSON {
    @SneakyThrows
    public static <T> T jsonForObject(String json, Class<T> obj) {
        T res = obj.newInstance();

        BufferedReader br = new BufferedReader(new StringReader(json));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("{") || line.equals("}")) continue;

            String key = getKey(line);
            String value = getValue(line);

            Field field = obj.getDeclaredField(key);
            field.setAccessible(true);

            field.set(res, value);
        }

        return res;
    }

    private static String getValue(String line) {
        int start = line.indexOf(":")+3;
        int end = line.lastIndexOf("\"");
        return line.substring(start,end);
    }

    private static String getKey(String line) {
        int start = line.indexOf("\"")+1;
        int end = line.indexOf(":")-1;
        return line.substring(start, end);
    }
}
