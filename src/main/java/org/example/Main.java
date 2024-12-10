package org.example;

import org.example.ClassCrypter.MyClassCrypter;
import org.example.ClassLoader.EncryptedClassLoader;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        MyClassCrypter.writeCryptedClass();

        int encryptionKey = 5;
        File encryptedClassesDir = new File("CryptedClass");

        EncryptedClassLoader loader = new EncryptedClassLoader(encryptionKey, encryptedClassesDir, ClassLoader.getSystemClassLoader());

        Class<?> clazz = loader.loadClass("org.example.Model.MyClass");

        Object instance = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("myMethod").invoke(instance);
    }
}