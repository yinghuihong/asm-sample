package com.shaker.asm.sample.aop.decorator;

import com.shaker.asm.sample.aop.Account;
import com.shaker.asm.sample.aop.util.SecurityChecker;

/**
 * @author yinghuihong
 * @date 2018/6/6
 */
public class AccountWithSecurityCheck implements Account {

    private Account account;

    public AccountWithSecurityCheck(Account account) {
        this.account = account;
    }

    @Override
    public void operation() {
        SecurityChecker.checkSecurity();
        account.operation();
    }
}
