package com.own.stu.rmi;

import com.own.stu.bean.Account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by CHANEL on 2016/6/2.
 */
public class UserManagerImpl extends UnicastRemoteObject implements UserManager {

    public UserManagerImpl() throws RemoteException{
    }

    @Override
    public String getUserName() throws RemoteException {
        return "Tommy Lee";
    }

    @Override
    public Account getAdminAccount() throws RemoteException {
        Account account=new Account();
        account.setUsername("admin");
        account.setPassword("admin");
        return account;
    }
}
