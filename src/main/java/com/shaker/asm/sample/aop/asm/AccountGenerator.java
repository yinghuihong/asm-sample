//package com.shaker.asm.sample.aop.asm;
//
//import org.objectweb.asm.ClassAdapter;
//import org.objectweb.asm.ClassReader;
//import org.objectweb.asm.ClassWriter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//
///**
// * @author yinghuihong
// * @date 2018/6/6
// */
//public class AccountGenerator {
//
//    public static void main(String[] args) throws Exception {
//        ClassReader cr = new ClassReader("com.shaker.asm.sample.aop.AccountImpl");
//        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
//        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//        byte[] data = cw.toByteArray();
//
//        File file = new File("./AccountImpl.class");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(data);
//        fileOutputStream.close();
//
////        ClassLoader classLoader = new ClassLoader() {
////            @Override
////            protected Class<?> findClass(String name) {
////                if (!name.equals("AccountImpl")) {
////                    return null;
////                }
////                System.out.println("xxx");
////                return defineClass(name, data, 0, data.length);
////            }
////        };
////        Class<?> clazz = classLoader.loadClass("com.shaker.asm.sample.aop.AccountImpl");
////        System.out.println(clazz);
////        AccountImpl account = (AccountImpl) clazz.newInstance();
////        account.operation();
//    }
//}
