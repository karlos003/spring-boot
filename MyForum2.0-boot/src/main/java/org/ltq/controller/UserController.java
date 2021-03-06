package org.ltq.controller;

import org.ltq.entity.Post;
import org.ltq.entity.User;
import org.ltq.service.PostService;
import org.ltq.service.UserService;
import org.ltq.utils.IOUtil;
import org.ltq.utils.Utils;
import org.ltq.utils.ValidsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RequestMapping("userController")
@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    PostService postService;

    private ValidsUtil validUtil = new ValidsUtil();

    private IOUtil ioUtil = new IOUtil();

    private Utils util = new Utils();

    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestParam("user_account")String user_account,@RequestParam("user_pwd")String user_pwd,HttpSession session,HttpServletResponse response){
        User user = userService.queryUserByAccount(user_account);
        System.out.println(user_account+"--"+user_pwd);
        if(user_account.trim().length() == 0 || user_pwd.trim().length() == 0){
            return "{\"status\" : -1 }";
        }
        if(user!=null){
            if(user.getUser_pwd().equals(user_pwd)){
                session.setAttribute("user", user);
                util.addUserCookies(user,response);
                return "{\"status\" : 1 }";
            }
            return "{\"status\" : -3 }";
        }
        return "{\"status\" : -2 }";
    }

    @RequestMapping("signUp")
    @ResponseBody
    public String signUp(@RequestParam("user_account")String user_account,@RequestParam("user_pwd")String user_pwd,@RequestParam("user_name")String user_name,HttpSession session,HttpServletResponse response){
        User user = new User(user_account,user_pwd,user_name);
        int validResult = validUtil.signUpValid(user);
        if (validResult != 0) {
            return "{\"status\" : "+validResult+"}";
        }else{
            int result = userService.addUser(user);
            if (result == 1) {
                //注册成功
                session.setAttribute("user", user);
                util.addUserCookies(user,response);
                return "{\"status\" : 1 }";
            } else if (result == -1) {
                //存在相同账号
                return "{\"status\" : -1 }";
            } else {
                //存在相同昵称
                return "{\"status\" : -2 }";
            }
        }

    }

    @RequestMapping("logOffControl")
    public String logOffControl(HttpSession session, Map<String, Object> map) {
        session.removeAttribute("user");
        map.put("resultType", 19);
        return "requestResult";
    }

    @RequestMapping("updateUserPhotoControl")
    public String updateUserPhotoControl(@RequestParam(value = "user_photo") MultipartFile img, HttpSession session, Map<String, Object> map) {
        int validResult = validUtil.imgValid(img);
        //判断图片
        if (validResult == -1) {
            map.put("resultType", 16);
            return "requestResult";
        } else if (img.getSize() > 2097152) {
            map.put("resultType", 25);
            return "requestResult";
        }
        if (validResult > 0) {
            //获取当前用户
            User user = (User) session.getAttribute("user");
            ioUtil.copyUserImage(user, img);
            User tempUser = new User(user.getUser_account(), 1);
            userService.updateUserPhotoByAccount(tempUser);
            user.setUser_photo(1);
            //更新session中的user.photo信息
            session.setAttribute("user", user);
            map.put("resultType", 11);
            return "requestResult";
        } else {
            map.put("resultType", 12);
            return "requestResult";
        }

    }


    @RequestMapping(value = "changePasswordControl")
    public String changePasswordControl(@RequestParam("pwd") String newPwd, @RequestParam("currentPwd") String currentPwd, HttpSession session, Map<String, Object> map) {
        int i = util.strNullValid(newPwd, currentPwd);
        if (i == -1) {
            map.put("resultType", 17);
            return "requestResult";

        }
        if (validUtil.pwdValid(newPwd) == false) {
            map.put("resultType", 37);
            return "requestResult";
        }
        User user = (User) session.getAttribute("user");
        String systemPwd = userService.queryUserPwdByAccount(user.getUser_account());
        if (!systemPwd.equals(currentPwd)) {
            map.put("resultType", 13);
            return "requestResult";
        } else {
            User tempUser = new User(user.getUser_account(), newPwd);
            userService.updateUserPwdByAccount(tempUser);
            user.setUser_pwd(newPwd);
            session.setAttribute("user", user);
            map.put("resultType", 14);
            return "requestResult";
        }
    }

    @RequestMapping("addSubsPost")
    public String addSubsPost(@RequestParam("post_id") BigInteger post_id, HttpSession session, Map<String, Object> map) {
        Date currentTime = new Date(System.currentTimeMillis());
        String user_account = ((User) session.getAttribute("user")).getUser_account();
        User temp = new User(user_account, post_id, currentTime);
        boolean result = userService.addOrRemoveUserSubsPost(temp);
        if (result) {
            map.put("resultType", 33);
            map.put("post_id", post_id);
            return "requestResult";
        } else {
            map.put("resultType", 34);
            map.put("post_id", post_id);
            return "requestResult";
        }

    }

    @RequestMapping("showSubsPost/{user_account}/{currentPage}")
    public String showSubsPost(@PathVariable("user_account") String user_account, @PathVariable("currentPage") int currentPage, HttpSession session, Map<String, Object> map) {
        List<Post> posts = userService.queryUserSubPost(user_account, currentPage, 10);
        if (posts == null) {
            map.put("user_account", user_account);
            map.put("resultType", 41);
            return "requestResult";
        }
        session.setAttribute("user_location", "userSubPosts");
        session.setAttribute("currentPage", currentPage);
        map.put("user_account", user_account);
        map.put("posts", posts);
        return "userSubPosts";
    }


    @RequestMapping("queryUserPosts/{user_account}/{currentPage}")
    public String queryUserPosts(@PathVariable("user_account") String user_account, @PathVariable("currentPage") Integer currentPage, HttpSession session, Map<String, Object> map) {
        List<Post> posts = postService.queryPostInfoByAcc(user_account, currentPage, 15);
        if (posts == null) {
            map.put("user_account", user_account);
            map.put("resultType", 43);
            return "requestResult";
        }
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("user_location", "userPosts");
        map.put("user_account", user_account);
        map.put("posts", posts);
        return "userPosts";
    }

    @RequestMapping("addOrRmSub/{user_account}/{sub_account}")
    public String addOrRmSub(@PathVariable("user_account") String user_account, @PathVariable("sub_account") String sub_account, Map<String, Object> map) {
        User user = new User();
        user.setUser_account(user_account);
        user.setSub_account(sub_account);
        boolean result = userService.addOrRemoveUserSub(user);
        if (result) {
            map.put("sub_account", sub_account);
            map.put("user_account", user_account);
            map.put("resultType", 38);
            return "requestResult";
        } else {
            map.put("sub_account", sub_account);
            map.put("user_account", user_account);
            map.put("resultType", 39);
            return "requestResult";
        }
    }


}
