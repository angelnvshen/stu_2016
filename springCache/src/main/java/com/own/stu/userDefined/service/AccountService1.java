package com.own.stu.userDefined.service;

import com.google.common.base.Optional;
import com.own.stu.userDefined.cache.CacheContext;
import com.own.stu.userDefined.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bf50 on 2016/2/19.
 */
@Service
public class AccountService1 {
    private final Logger logger = LoggerFactory.getLogger(AccountService1.class);

    @Autowired
    private CacheContext<Account> cacheContext;

    public Account getAccountByName(String accountName) {
        Account result = cacheContext.get(accountName);
        if (result != null) {
            logger.info("get from cache... {}", accountName);
            return result;
        }

        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        Account account = accountOptional.get();
        cacheContext.addOrUpdateCache(accountName, account);
        return account;
    }

    public void reload() {
        cacheContext.evictCache();
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}
