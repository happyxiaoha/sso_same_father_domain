/*
 * Title：SSOUtil.java<br> Date: 2018年8月1日 下午3:30:21<br> Copyright (c) 2018 digitalchina <br>
 * 
 * WebSite: http://www.digitalchina.com<br>
 */
package com.digitalchina.sso.util;

/**
 * <br>
 * 
 * @author haoyp
 * @version 1.0
 */
public class SSOUtil {
  public static final String USERNAME = "user";
  public static final String PASSWORD = "123";
  
  public static final String COOKIENAME = "ssocookie";
  public static final String COOKIEVALUE = "sso";
  
  public static boolean checkUser(String userName, String password) {
    if (userName.equals(USERNAME) && password.equals(PASSWORD)) {
      return true;
    }
    return false;
  }
  public static boolean checkCookie(String cookieName, String cookieValue) {
    if (cookieName.equals(COOKIENAME) && cookieValue.equals(COOKIEVALUE)) {
      return true;
    }
    return false;
  }
}
