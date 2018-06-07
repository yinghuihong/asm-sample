package com.shaker.asm.sample.aop.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yinghuihong
 * @date 2018/6/6
 */
public class AddSecurityCheckMethodAdapter extends MethodAdapter {

    AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(mv);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/shaker/asm/sample/aop/util/SecurityChecker", "checkSecurity", "()V");
    }
}