package com.itheima.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesUtils {
    private static Properties properties = new Properties();
    private static Map<String,String> propertiesMap = new ConcurrentHashMap();

    static {
        InputStream in = null;
        try {
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(in);

            Iterator<Object> iterator = properties.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = properties.getProperty(key);
                propertiesMap.put(key, value);
            }
        }catch (Exception e) {

        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String getProperty(String key) {
        return propertiesMap.get(key);
    }

    public static void main(String[] args) {
        System.out.println(getProperty("db.driver.name"));
    }
}
