package ru.snake.game.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;


public class JSONLoader {
    public static JSONObject getJSONObject(String resource) throws IOException, NullPointerException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(resource)).getFile());

        String content = Files.readString(file.toPath());

        return new JSONObject(content);
    }

    public static Object getJSONValueByKey(String resource, String key) {
        JSONObject object;
        try {
            object = getJSONObject(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return object.get(key);
    }

    public static JSONArray getJSONArrayByKey(String resource, String key) {
        JSONObject object;
        try {
            object = getJSONObject(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return object.getJSONArray(key);
    }

    public static List<Object> getListByKey(String resource, String key) {
        JSONArray array = getJSONArrayByKey(resource,key);
        return array.toList();
    }
}
