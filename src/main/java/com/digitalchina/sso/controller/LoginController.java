/*
 * Title：HelloController.java<br> Date: 2018年7月31日 下午1:22:19<br> Copyright (c) 2018 digitalchina
 * <br>
 * 
 * WebSite: http://www.digitalchina.com<br>
 */
package com.digitalchina.sso.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitalchina.sso.util.SSOUtil;

/**
 * <br>
 * 
 * @author haoyp
 * @version 1.0
 */
@Controller
@RequestMapping("/sso")
public class LoginController {
  /**
   * 
   sso的登录页面 1.首先校验cookie,若存在有效，则302重定向至目标url，否则返回登录页面
   * 
   * String<br>
   * 
   * @author haoyp
   * @exception
   */
  @RequestMapping()
  public String main(HttpServletRequest request, HttpServletResponse response, String gotoUrl,
      ModelMap map) throws IOException {
    if (gotoUrl == null) {
      gotoUrl = "http://demo1.x.com/demo1/main";
    }
    map.put("gotoUrl", gotoUrl);

    // 校验cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("ssocookie")) {
          boolean ok = SSOUtil.checkCookie(cookie.getName(), cookie.getValue());
          if (ok) {
            response.sendRedirect(gotoUrl);
          }
        }
      }
    }
    return "sso/login";
  }

  /**
   * 
   sso登录校验 1.设置cookie需要在顶级域名下面 2.用户身份通过，302至目标url；否则登录页面 String<br>
   * 
   * @author haoyp
   * @exception
   */
  @RequestMapping("/login")
  public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map,
      String username, String password, String gotoUrl) throws IOException {
    boolean ok = SSOUtil.checkUser(username, password);
    if (ok) {
      Cookie cookie = new Cookie("ssocookie", "sso");

      // cookie 设置在顶级
      cookie.setDomain("x.com");
      cookie.setPath("/");

      response.addCookie(cookie);
      // 重定向
      response.sendRedirect(gotoUrl);
    }
    map.put("gotoUrl", gotoUrl);
    return "sso/login";
  }

  /**
   * 
   提供给sso客户端校验cookie String<br>
   * 
   * @author haoyp
   * @exception
   */
  @RequestMapping("/check")
  @ResponseBody
  public String checkCookie(String cookieName, String cookieValue) throws IOException {
    boolean ok = SSOUtil.checkCookie(cookieName, cookieValue);
    if (ok) {
      return "1";
    }
    return "0";
  }
}
