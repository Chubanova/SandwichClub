package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtils {
    private static final String valueRegexp = "(^\"\"(,|$)|^\".*?[^\\\\](\",|\"$))";
    private static final Pattern valuePattern = Pattern.compile(valueRegexp);

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        Map<String, Object> jsonPars = parseObject(json);

        sandwich.setMainName(((Map) jsonPars.get("name")).get("mainName").toString());
        sandwich.setPlaceOfOrigin(jsonPars.get("placeOfOrigin").toString());
        sandwich.setAlsoKnownAs((List<String>) ((Map) jsonPars.get("name")).get("alsoKnownAs"));
        sandwich.setDescription((String) jsonPars.get("description"));
        sandwich.setImage((String) jsonPars.get("image"));
        sandwich.setIngredients((List<String>) jsonPars.get("ingredients"));

        return sandwich;
    }

    public static Map<String, Object> parseObject(String json) {
        Map<String, Object> jsonPars = new HashMap<>();
        json = unWrap(json);

        while (json.length() != 0) {
            if (json.startsWith(",")) json = json.substring(1);
            String key = unWrap(json.split(":")[0]);
            json = json.substring(key.length() + 3);
            String firstSymbol = json.substring(0, 1);
            StringBuilder value;

            switch (firstSymbol) {
                case "\"":
                    Matcher matcher = valuePattern.matcher(json);
                    value = new StringBuilder();
                    if (matcher.find()) {
                        value = new StringBuilder(matcher.group(0));
                    }
                    if (value.toString().endsWith(","))
                        value = new StringBuilder(value.substring(0, value.length() - 1));
                    value = new StringBuilder(unWrap(value.toString()));

                    jsonPars.put(key, value.toString());
                    if (value.length() > 0)
                        json = json.substring(value.length() + 2);
                    else if (json.length() >= 2)
                        json = json.substring(2);
                    break;
                case "{":
                    value = extractBlockInBrackets(json, '{', '}');

                    jsonPars.put(key, parseObject(value.toString()));
                    json = json.substring(value.length());
                    break;
                case "[":
                    value = extractBlockInBrackets(json, '[', ']');

                    jsonPars.put(key, parseArray(value.toString()));
                    json = json.substring(value.length());
                    break;
                default:
                    break;
            }

        }
        return jsonPars;
    }

    private static StringBuilder extractBlockInBrackets(String json, char bracketStart, char bracketEnd) {
        StringBuilder value;
        int openedBraces = 1;
        int closedBraces = 0;
        value = new StringBuilder();
        value.append(bracketStart);
        for (int i = 1; i < json.length() && openedBraces != closedBraces; i++) {
            if (json.charAt(i) == bracketStart) openedBraces++;
            if (json.charAt(i) == bracketEnd) closedBraces++;
            value.append(json.charAt(i));
        }
        return value;
    }

    private static List parseArray(String s) {
        List<String> result = new ArrayList<>();
        s = unWrap(s);
        if (!s.isEmpty()) {
            String firstSymbol = s.substring(0, 1);

            switch (firstSymbol) {
                case "\"":
                    while (!s.isEmpty()) {
                        if (s.startsWith(",")) s = s.substring(1);
                        Matcher matcher = valuePattern.matcher(s);
                        if (matcher.find()) {
                            String value = matcher.group(0);
                            if (value.endsWith(","))
                                value = value.substring(0, value.length() - 1);
                            value = unWrap(value);
                            result.add(value);
                            if (value.length() > 0)
                                s = s.substring(value.length() + 2);
                            else if (s.length() >= 2)
                                s = s.substring(2);
                        }
                    }
                    break;
                case "{":
                    break;
                case "[":
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    static String unWrap(String string) {
        return string != null && string.length() >= 2 ? string.substring(1, string.length() - 1) : string;
    }
}
