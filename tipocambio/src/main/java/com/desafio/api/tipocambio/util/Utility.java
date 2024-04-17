package com.desafio.api.tipocambio.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Utility {

    public static <T> Map<String, Number> convertObjectToHashMap(T object) throws IllegalAccessException {
        Map<String, Number> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Number value = (Number) field.get(object);
            map.put(field.getName().toUpperCase(), value);
        }
        return map;
    }

}
