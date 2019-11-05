package com.starry.context;

/**
 * ClassName: UserApplicationContext
 * Description: 单例模式存储当前用户的ID
 *
 * @author: starryfei
 * @date: 2019-11-05 23:45
 **/
public class UserApplicationContext {
    private ThreadLocal<String> local = new ThreadLocal<>();

    private static  UserApplicationContext instance = null;

    private UserApplicationContext(){

    }

    public static synchronized UserApplicationContext getInstance(){
        if(instance == null) {
            instance = new UserApplicationContext();
            return instance;
        }
        return instance;
    }

    public void saveCurrentUser(String userId) {
        local.set(userId);
    }

    public String getCurrentUser(){
        return local.get();
    }
}
