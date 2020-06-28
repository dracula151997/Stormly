package com.octopus.stormly.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Serializer {

    public static String readJsonFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int available = inputStream.available();
            byte[] buffer = new byte[available];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T toObject(String json, Class<T> cls) {
        if (json == null)
            return null;

        Gson gson = new Gson();
        return gson.fromJson(json, cls);
    }

    public static <T> List<T> toListOfObject(String jsonArray, Class<T> tClass) {
        return new Gson().fromJson(jsonArray,
                TypeToken.getParameterized(List.class, tClass).getType());
    }
}
