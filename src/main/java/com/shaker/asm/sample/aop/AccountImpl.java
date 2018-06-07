package com.shaker.asm.sample.aop;

/**
 * @author yinghuihong
 * @date 2018/6/6
 */
public class AccountImpl implements Account {

    @Override
    public void operation() {
        System.out.println("operation...");
        //TODO real operation
    }
}
