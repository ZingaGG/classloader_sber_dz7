package org.example.ClassCrypter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class MyClassCrypter {

    private static String inputFilePath = "target/classes/org/example/Model/MyClass.class"; // Откуда грузим
    private static String outputFilePath = "CryptedClass/MyClass.class"; // Куда загружаем
    private static int key = 5; // Ключ шифрования

    public static void writeCryptedClass() throws IOException {
        byte[] classBytes = Files.readAllBytes(new File(inputFilePath).toPath());
        byte[] encryptedBytes = crypt(classBytes, key);

        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            fos.write(encryptedBytes);
        }

        System.out.println("Class file encrypted successfully.");
    }


    private static byte[] crypt(byte[] data, int key) {
        byte[] encrypted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            encrypted[i] = (byte) (data[i] + key);
        }
        return encrypted;
    }
}
