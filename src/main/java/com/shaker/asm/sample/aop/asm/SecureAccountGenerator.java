package com.shaker.asm.sample.aop.asm;

import com.shaker.asm.sample.aop.Account;
import com.shaker.asm.sample.aop.AccountImpl;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yinghuihong
 * @date 2018/6/7
 */
public class SecureAccountGenerator {

    private static AccountGeneratorClassLoader classLoader = new AccountGeneratorClassLoader();

    private static Class secureAccountClass;

    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException, ClassNotFoundException {
        generateSecureAccount().operation();
    }

    private static Account generateSecureAccount() throws ClassFormatError, InstantiationException, IllegalAccessException, IOException {
        if (null == secureAccountClass) {
            ClassReader cr = new ClassReader(AccountImpl.class.getName());
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();

            File file = new File("./AccountImpl$EnhancedByASM.class");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.close();

            secureAccountClass = classLoader.defineClassFromClassFile("com.shaker.asm.sample.aop.AccountImpl$EnhancedByASM", data);
        }
        return (Account) secureAccountClass.newInstance();
    }

    private static class AccountGeneratorClassLoader extends ClassLoader {

        Class defineClassFromClassFile(String className, byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0, classFile.length);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            System.out.println("findClass() " + name);
            return super.findClass(name);
        }
    }
}
