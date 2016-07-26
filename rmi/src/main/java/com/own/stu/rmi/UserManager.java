package com.own.stu.rmi;

import com.own.stu.bean.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by CHANEL on 2016/6/2.
 */
public interface UserManager extends Remote{
    public String getUserName() throws RemoteException;
    public Account getAdminAccount() throws RemoteException;
}
