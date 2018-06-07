package com.shaker.asm.sample.aop.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author yinghuihong
 * @date 2018/6/6
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

    private String enhancedSuperName;

    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        // ResponseChain 的下一个 ClassVisitor，这里我们将传入 ClassWriter，负责改写后代码的输出
        super(classVisitor);
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        // 改变类命名
        String enhancedName = name + "$EnhancedByASM";
        // 改变父类，这里是”AccountImpl”
        enhancedSuperName = name;
        super.visit(version, access, enhancedName, signature, enhancedSuperName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("visitField() name " + name);
        return super.visitField(access, name, descriptor, signature, value);
    }

    /**
     * 重写 visitMethod，访问到 "operation" 方法时，
     * 给出自定义 MethodVisitor，实际改写方法内容
     */
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            // 对于 "operation" 方法
            if (name.equals("operation")) {
                // 使用自定义 MethodVisitor，实际改写方法内容
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            } else if (name.equals("<init>")) {
                wrappedMv = new ChangeToChildConstructorMethodAdapter(mv, enhancedSuperName);
            }
        }
        return wrappedMv;
    }
}
