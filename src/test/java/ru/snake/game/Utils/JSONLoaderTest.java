package ru.snake.game.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONLoaderTest {

    @Test
    void getJSONObject_Creates_Correct_JSON_Object() {
        JSONObject object;
        JSONObject mockObject = new JSONObject("""
                {
                  "one": "two",
                  "arr": [1,2,3]
                }""");
        try {
            object = JSONLoader.getJSONObject("mock.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(mockObject.toString(), object.toString());
    }

    @Test
    void getJSONObject_Return_Expected_Value_By_Key() {
        JSONObject object;

        try {
            object = JSONLoader.getJSONObject("mock.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals("two", object.get("one"));
    }

    @Test
    void getJSONObject_Throws_NullPointerException_With_Invalid_Resource() {
        assertThrows(NullPointerException.class, () -> {
            JSONObject object = JSONLoader.getJSONObject("nonexistent.json");
        });
    }

    @Test
    void getJSONValueByKey_Return_Expected_Value() {
        String key = (String) JSONLoader.getJSONValueByKey("mock.json", "one");
        assertEquals("two", key);
    }

    @Test
    void getJSONArrayByKey_Return_Expected_JSONArray_Object() {
        JSONArray mockArray = new JSONArray("[1,2,3]");
        JSONArray array = JSONLoader.getJSONArrayByKey("mock.json", "arr");

        assertEquals(mockArray.toList(),array.toList());
    }

    @Test
    void getListByKey_Return_Expected_List_Of_Values() {
        List<Integer> mockList = new ArrayList<>(Arrays.asList(1,2,3));
        List<Object> list = JSONLoader.getListByKey("mock.json", "arr");
        assertEquals(mockList,list);
    }
}