package com.udacity.sandwichclub.utils;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Машенька on 17.02.2018.
 */
public class JsonUtilsTest extends TestCase {


    @Test
    public void jsonParse1() throws Exception {
        String json = "{\"attribute\":\"mainName\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("mainName", a.get("attribute"));
    }

    @Test
    public void jsonParse2() throws Exception {
        String json = "{\"attribute\":\"mainName\",\"attr\":\"value\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("mainName", a.get("attribute"));
        assertEquals("value", a.get("attr"));
    }

    @Test
    public void jsonParse3() throws Exception {
        String json = "{\"description\":\"A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham " +
                "between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables " +
                "like lettuce, tomato, onion or pickle slices can also be included. Various kinds of " +
                "mustard and mayonnaise are also common.\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham " +
                "between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables " +
                "like lettuce, tomato, onion or pickle slices can also be included. Various kinds of " +
                "mustard and mayonnaise are also common.", a.get("description"));
    }

    @Test
    public void jsonParses4() throws Exception {
        String json = "{\"description\":\"Medianoche (\\\"midnight\\\" " +
                "in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban " +
                "communities in the United States. It is so named because of the sandwich\'s popularity " +
                "asa staple served in Havana\'s night clubs right around or after " +
                "midnight.\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Medianoche (\\\"midnight\\\" " +
                "in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban " +
                "communities in the United States. It is so named because of the sandwich\'s popularity " +
                "asa staple served in Havana\'s night clubs right around or after " +
                "midnight.", a.get("description"));
    }

    @Test
    public void jsonParses5() throws Exception {
        String json = "{\"description\":\"\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("", a.get("description"));
    }

    @Test
    public void jsonParses6() throws Exception {
        String json = "{\"description\":\"Medianoche (\\\"midnight\\\", \\\"midnight2\\\" " +
                "in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban " +
                "communities in the United States. It is so named because of the sandwich\'s popularity " +
                "asa staple served in Havana\'s night clubs right around or after " +
                "midnight.\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Medianoche (\\\"midnight\\\", \\\"midnight2\\\" " +
                "in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban " +
                "communities in the United States. It is so named because of the sandwich\'s popularity " +
                "asa staple served in Havana\'s night clubs right around or after " +
                "midnight.", a.get("description"));
    }

    @Test
    public void jsonParses7() throws Exception {
        String json = "{\"placeOfOrigin\":\"Taiwan\",\"name\":{\"mainName\":\"Gua bao\"}}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Taiwan", a.get("placeOfOrigin"));
        assertEquals("Gua bao", ((Map) a.get("name")).get("mainName"));
    }

    @Test
    public void jsonParses8() throws Exception {
        String json = "{\"name\":{\"mainName\":\"Gua bao\"},\"placeOfOrigin\":\"Taiwan\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Taiwan", a.get("placeOfOrigin"));
        assertEquals("Gua bao", ((Map) a.get("name")).get("mainName"));
    }

    @Test
    public void jsonParses9() throws Exception {
        String json = "{\"name\":{\"mainName\":\"{Gua} bao\"},\"placeOfOrigin\":\"Taiwan\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Taiwan", a.get("placeOfOrigin"));
        assertEquals("{Gua} bao", ((Map) a.get("name")).get("mainName"));
    }

    @Test
    public void jsonParses10() throws Exception {
        String json = "{\"name\":{\"mainName\":{\"realName\":\"Gua bao\"}},\"placeOfOrigin\":\"Taiwan\"}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Taiwan", a.get("placeOfOrigin"));
        assertEquals("Gua bao", ((Map) ((Map) a.get("name")).get("mainName")).get("realName"));
    }

    @Test
    public void jsonParses11() throws Exception {
        String json = "{\"ingredients\":[\"Two " +
                "or more of beef, lamb, pork, veal\",\"Onions\",\"Bread crumbs\",\"Lard\"]}";

        Map a = JsonUtils.parseObject(json);

        assertEquals("Onions", ((List) a.get("ingredients")).get(1));
    }

    @Test
    public void cutBrasses() throws Exception {
        String json = "{\"attribute\":\"mainName\",\"attr\":\"value\"}";

        json = JsonUtils.unWrap(json);

        assertEquals("\"attribute\":\"mainName\",\"attr\":\"value\"", json);
    }
}