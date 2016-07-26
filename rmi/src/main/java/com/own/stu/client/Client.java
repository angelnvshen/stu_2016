package com.own.stu.client;

import com.own.stu.rmi.UserManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by CHANEL on 2016/6/2.
 */
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        UserManager manager = (UserManager) Naming.lookup("rmi://localhost:8888/Rmanager");

        System.out.println(manager.getAdminAccount());
        System.out.println(manager.getUserName());

    }
}
