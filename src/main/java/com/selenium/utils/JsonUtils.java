package com.selenium.utils;


import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JsonUtils {

    private static final String DATETIME_FORMAT_STRING = "yyyy/MM/dd HH:mm (z)";

    /**
     * We can use identifier instead of object in jsons
     */
    private static Gson userGson;

    /**
     * Gson without type hierarchy adapters
     */
    private static Gson gson;


    static {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().setDateFormat(DATETIME_FORMAT_STRING).setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping();

        gsonBuilder.registerTypeHierarchyAdapter(Date.class, (JsonDeserializer<? extends  Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));
        gsonBuilder.registerTypeHierarchyAdapter(Date.class, (JsonSerializer<? extends  Date>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()));


        gson = gsonBuilder.create();


    }

    public static String getJson(Object object) {
        return gson.toJson(object);
    }


    public static <T extends Object> T fromJson(Type type, String json) {
        return (T) gson.fromJson(json, type);
    }

    public static <T extends Object> T userFromJson(Type type, String json) {
        return (T) userGson.fromJson(json, type);
    }

    public static void writeJsonToFile(String json, String fileName) {
        try {
            FileUtils.writeStringToFile(new File(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> getListFromJson(Class<T[]> clazz, String json) {
        return Arrays.asList(gson.fromJson(json, clazz));
    }

}

