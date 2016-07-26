package com.own.stu.userDefined;

import com.own.stu.userDefined.service.AccountService1;
import com.own.stu.userDefined.service.AccountService2;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by bf50 on 2016/2/19.
 */
public class AccountService2Test {
    private AccountService2 accountService2;

    private final Logger logger = LoggerFactory.getLogger(AccountService2Test.class);

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        accountService2 = context.getBean("accountService2", AccountService2.class);
    }

    @Test
    public void testInject(){
        assertNotNull(accountService2);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        logger.info("first query...");
        accountService2.getAccountByName("accountName");

        logger.info("second query...");
        accountService2.getAccountByName("accountName");
    }
}
