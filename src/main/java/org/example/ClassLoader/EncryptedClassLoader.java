package org.example.ClassLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptedClassLoader extends ClassLoader {
    private final int key;
    private final File dir;

    public EncryptedClassLoader(int key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File classFile = new File(dir, name.replace('.', '/') + ".class");
            if (!classFile.exists()) {
                throw new ClassNotFoundException("Class file not found: " + classFile.getAbsolutePath());
            }

            byte[] receiver = Files.readAllBytes(classFile.toPath());

            byte[] decryptedReceiver = decrypt(receiver, key);

            return defineClass(name, decryptedReceiver, 0, decryptedReceiver.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + name, e);
        }
    }

    private byte[] decrypt(byte[] data, int key) {
        byte[] decrypted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            decrypted[i] = (byte) (data[i] - key);
        }
        return decrypted;
    }
}

