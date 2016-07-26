package com.own.stu.userDefined;

import com.own.stu.userDefined.service.AccountService1;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by bf50 on 2016/2/19.
 */
public class AccountService1Test {
    private AccountService1 accountService1;

    private final Logger logger = LoggerFactory.getLogger(AccountService1Test.class);

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext1.xml");
        accountService1 = context.getBean("accountService1", AccountService1.class);
    }

    @Test
    public void testInject(){
        assertNotNull(accountService1);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        accountService1.getAccountByName("accountName");
        accountService1.getAccountByName("accountName");

        accountService1.reload();
        logger.info("after reload ....");

        accountService1.getAccountByName("accountName");
        accountService1.getAccountByName("accountName");
    }
}
