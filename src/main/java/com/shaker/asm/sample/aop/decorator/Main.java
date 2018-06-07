package com.shaker.asm.sample.aop.decorator;

import com.shaker.asm.sample.aop.Account;
import com.shaker.asm.sample.aop.AccountImpl;

/**
 * @author yinghuihong
 * @date 2018/6/6
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===============================================");
        Account account = new AccountImpl();
        account.operation();
        System.out.println();
        System.out.println("===============================================");
        new AccountWithSecurityCheck(account).operation();
    }
}
