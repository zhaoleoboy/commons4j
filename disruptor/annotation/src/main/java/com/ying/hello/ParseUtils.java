package com.ying.hello;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ParseUtils {
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.ying.hello.World");
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    public static void parseMethodAnnotation() {
        Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(World.class)) {
                World annotation = method.getAnnotation(World.class);
                System.out.println(method.getName());
            }
        }
    }

    public static void parseConstructAnnotation() {
        Constructor<?>[] constructors = User.class.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(World.class)) {
                World annotation = constructor.getAnnotation(World.class);
                System.out.println(annotation);
            }
        }
    }

    public static void main(String[] args) {
        parseMethodAnnotation();
    }
}
