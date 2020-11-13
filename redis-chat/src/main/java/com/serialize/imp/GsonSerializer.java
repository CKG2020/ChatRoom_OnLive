package com.serialize.imp;


import com.google.gson.Gson;
import com.serialize.Serializer;

public class GsonSerializer extends Serializer {
//    private Gson gson;
    private Gson gson=new Gson();
    @SuppressWarnings("unchecked")
    @Override
    public <T> byte[] serializer(T obj) {
        if (null==obj){
            String s = gson.toJson("ERROR");
            return s.getBytes();
        }
        String s = gson.toJson(obj);
        System.out.println("obj"+s);
        return s.getBytes();
    }

    @Override
    public <T> Object deserializer(byte[] bytes, Class<T> clazz) {
        T t = gson.fromJson(new String(bytes), clazz);
        System.out.println("xxx"+t);
        return t;
    }

    public static void main(String[] args) {
        GsonSerializer gsonSerializer = new GsonSerializer();
        byte[] xxxes = gsonSerializer.serializer("xxx");
        for (int x = 0; x <xxxes.length; x++) {
            System.out.println(xxxes[x]);
        }
        System.out.println("=============================");
        Object deserializer = gsonSerializer.deserializer(xxxes, String.class);
//        System.out.println(deserializer.toString());

    }
}
