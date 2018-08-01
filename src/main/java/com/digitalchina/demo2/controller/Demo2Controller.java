/*
 *	Title：HelloController.java<br>
 *  Date: 2018年7月31日 下午1:22:19<br>
 * 	Copyright (c) 2018 digitalchina <br>
 *
 * 	WebSite: http://www.digitalchina.com<br>
 * 
 */
package com.digitalchina.demo2.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digitalchina.demo2.util.HttpClientUtil;

/**
 *  <br>
 * 
 * @author haoyp
 * @version 1.0
 */
@Controller
@RequestMapping("/demo2")
public class Demo2Controller {
  
  @RequestMapping("/main")
  public String main(HttpServletRequest request, HttpServletResponse response) throws IOException{
    //校验cookie
    Cookie[] cookies = request.getCookies();
    if(cookies!=null){
      for (Cookie cookie: cookies) {
        if(cookie.getName().equals("ssocookie")){
          String url = "http://sso.x.com/sso/check?cookieName="
              + cookie.getName() + "&cookieValue=" + cookie.getValue();
          String rs = HttpClientUtil.doGet(url, 2000, null, null);
          if(rs.equals("1")){
            return  "demo2/index";
          }
        }
      }
    }
    //sso登录
    response.sendRedirect("http://sso.x.com/sso?gotoUrl=http://demo1.x.com/demo2/main");
    return null;
  }
}

