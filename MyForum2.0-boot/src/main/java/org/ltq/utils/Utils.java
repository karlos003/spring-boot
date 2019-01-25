package org.ltq.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utils {

public String getcookie(String name,HttpServletRequest request) {
Cookie[] cookies = request.getCookies();//创建一个cookie集合并拿到cookie放入创建好的cookie集合里面
	//遍历cookie集合并判断是否有自己想要的指定cookie如果有则返回指定cookie的值，如果没有则返回空字符串
        for (Cookie cookie:cookies){
            if (name.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
	return "";
     }

}
