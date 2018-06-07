package com.shaker.asm.sample.aop.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yinghuihong
 * @date 2018/6/7
 */
public class ChangeToChildConstructorMethodAdapter extends MethodAdapter {

    private String superClassName;

    ChangeToChildConstructorMethodAdapter(MethodVisitor methodVisitor, String superClassName) {
        super(methodVisitor);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        // 调用父类的构造函数时
        if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
            owner = superClassName;
        }
        // 改写父类为 superClassName
        super.visitMethodInsn(opcode, owner, name, desc);
    }
}
