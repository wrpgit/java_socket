package com.wrpxcx.swingUi;

import javax.security.auth.login.AccountLockedException;

/**
 * @author: wrp
 * @TODO: 提供每个UI的示例对象
 * @time: 2020-05-15 07:57
 **/
public class UIFactory {

    private static LoginUI loginUI = new LoginUI();
    private static RqUI rqUI;
    private static AddFriendUI addFriendUI;
    private static SignUpUI signUpUI;

    public static LoginUI getLoginUI() {

        return loginUI;
    }

    public static RqUI getRqUI() {
        if(rqUI == null) {

            rqUI = new RqUI();
        }
        return rqUI;
    }

    public static AddFriendUI getAddFriendUI(){
        if(addFriendUI == null) {

            addFriendUI = new AddFriendUI();
        }
        return addFriendUI;
    }
    public static void closeAddFriendUI() {
        addFriendUI = null;
    }

    public static SignUpUI getSignUpUI() {
        if(signUpUI == null) {
            signUpUI = new SignUpUI();
        }
        return signUpUI;
    }
    public static void closeSignUI(){
        signUpUI = null;
    }
}
