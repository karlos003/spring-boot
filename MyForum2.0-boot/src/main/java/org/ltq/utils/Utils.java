package org.ltq.utils;

import org.ltq.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {

    public String getcookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();//创建一个cookie集合并拿到cookie放入创建好的cookie集合里面
        //遍历cookie集合并判断是否有自己想要的指定cookie如果有则返回指定cookie的值，如果没有则返回空字符串
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    public int strNullValid(String ... strs){
        for(String str : strs){
            if(str.trim().length() == 0){
                return -1;
            }
        }
        return 0;
    }

    public void addUserCookies(User user, HttpServletResponse response){
        Cookie accountCookie = new Cookie("account", user.getUser_account());
        Cookie pwdCookie = new Cookie("pwd", user.getUser_pwd());
        accountCookie.setMaxAge(604800);
        accountCookie.setPath("/");
        pwdCookie.setMaxAge(604800);
        pwdCookie.setPath("/");
        response.addCookie(accountCookie);
        response.addCookie(pwdCookie);
    }

}
