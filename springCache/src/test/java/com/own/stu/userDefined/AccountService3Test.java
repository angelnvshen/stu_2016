package com.own.stu.userDefined;

import com.own.stu.userDefined.model.Account;
import com.own.stu.userDefined.service.AccountService3;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by bf50 on 2016/2/19.
 */
public class AccountService3Test {
    private AccountService3 accountService3;

    private final Logger logger = LoggerFactory.getLogger(AccountService3Test.class);

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        accountService3 = context.getBean("accountService3", AccountService3.class);
    }

    @Test
    public void testInject(){
        assertNotNull(accountService3);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        logger.info("first query...");
        accountService3.getAccountByName("accountName");

        logger.info("second query...");
        accountService3.getAccountByName("accountName");
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account1 = accountService3.getAccountByName("accountName1");
        logger.info(account1.toString());
        Account account2 = accountService3.getAccountByName("accountName2");
        logger.info(account2.toString());

        account2.setId(121212);
        accountService3.updateAccount(account2);

        // account1会走缓存
        account1 = accountService3.getAccountByName("accountName1");
        logger.info(account1.toString());
        // account2会查询db
        account2 = accountService3.getAccountByName("accountName2");
        logger.info(account2.toString());

    }

    @Test
    public void testReload() throws Exception {
        accountService3.reload();
        // 这2行查询数据库
        accountService3.getAccountByName("somebody1");
        accountService3.getAccountByName("somebody2");

        // 这两行走缓存
        accountService3.getAccountByName("somebody1");
        accountService3.getAccountByName("somebody2");



//        http://www.cnblogs.com/rollenholt/articles/3561612.html

    }
}
