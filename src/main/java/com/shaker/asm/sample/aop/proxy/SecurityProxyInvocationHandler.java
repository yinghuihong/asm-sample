package com.shaker.asm.sample.aop.proxy;

import com.shaker.asm.sample.aop.Account;
import com.shaker.asm.sample.aop.util.SecurityChecker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理（基于反射生成代理实例）
 *
 * @author yinghuihong
 * @date 2018/6/6
 */
public class SecurityProxyInvocationHandler implements InvocationHandler {

    private Object object;

    public SecurityProxyInvocationHandler(Object o) {
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (proxy instanceof Account && method.getName().equals("operation")) {
            SecurityChecker.checkSecurity();
        }
        return method.invoke(object, args);
    }
}
