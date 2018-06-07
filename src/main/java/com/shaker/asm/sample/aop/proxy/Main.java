package com.shaker.asm.sample.aop.proxy;

import com.shaker.asm.sample.aop.Account;
import com.shaker.asm.sample.aop.AccountImpl;

import java.lang.reflect.Proxy;

/**
 * @author yinghuihong
 * @date 2018/6/6
 */
public class Main {

    public static void main(String[] args) {
        Account proxy = (Account) Proxy.newProxyInstance(
                Account.class.getClassLoader(),
                new Class[]{Account.class},
                new SecurityProxyInvocationHandler(new AccountImpl())
        );
        proxy.operation();
    }
}
