package org.ltq.controller;


import org.ltq.entity.Comment;
import org.ltq.entity.User;
import org.ltq.service.CommentService;
import org.ltq.utils.IOUtil;
import org.ltq.utils.ValidsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/commentController")
public class CommentController {

    @Resource
    CommentService commentService;

    ValidsUtil validUtil = new ValidsUtil();

    IOUtil ioUtil = new IOUtil();

    /**
     * 请求的form表单的enctype="multipart/form-data"，request会自动转换成multipartRequest，所有参数都不为null，且String类型的值不为null和""
     *
     * @param content，评论内容
     * @param comPic       评论图片(数字，表示图片数)
     * @param session      HttpSession
     */
    @RequestMapping("/addCommentControl")
    public String addCommentControl(@RequestParam("content") String content, @RequestParam("comPic") MultipartFile comPic, HttpSession session, Map<String, Object> map) {
        //判断，图片和文字都没有时返回失败
        if (content.trim().length() == 0 && comPic.getOriginalFilename().trim().length() == 0) {
            map.put("resultType", 6);
            return "requestResult";
        }

        int imgNum = 0;

        if (comPic.getOriginalFilename().trim().length() > 0) {

            int validResult = validUtil.imgValid(comPic);

            if (validResult == -2) {
                //图片类型不对
                map.put("resultType", 8);
                return "requestResult";
            } else if (validResult == -3) {
                //图片过大
                map.put("resultType", 36);
                return "requestResult";
            } else if (validResult > 0) {
                imgNum++;
            }
        }
        User user = (User) session.getAttribute("user");
        String uname = user.getUser_name();
        String account = user.getUser_account();
        String currTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Comment comment = new Comment(account, uname, currTime, content, imgNum);
        BigInteger commentid = commentService.addComment(comment);
        if (imgNum > 0) {    //当有图片时的IO流操作
            ioUtil.copyCommentImage(commentid, comPic);
        }
        map.put("resultType", 5);
        return "requestResult";
    }

    @RequestMapping("/deleteCommentControl")
    public String deleteCommentControl(@RequestParam("commentid") BigInteger commentId, Map<String, Object> map) {
        commentService.deleteCommentByCommentId(commentId);
        File file = new File("D:\\MyForumUploadDir\\comment_picture\\" + commentId + ".jpg");
        file.delete();
        map.put("resultType", 7);
        return "requestResult";
    }

    @RequestMapping("/deleteCommentInPost")
    public String deleteCommentInPost(@RequestParam("post_id") BigInteger post_id, @RequestParam("commentid") BigInteger commentId, Map<String, Object> map) {
        commentService.deleteCommentByCommentId(commentId);
        File file = new File("D:\\MyForumUploadDir\\comment_picture\\" + commentId + ".jpg");
        file.delete();
        map.put("post_id", post_id);
        map.put("resultType", 32);
        return "requestResult";
    }

    @RequestMapping("/addPostComment")
    public String addPostComment(@RequestParam("post_id") BigInteger post_id, @RequestParam(value = "content") String content, @RequestParam(value = "comPic", required = false) MultipartFile comPic, HttpSession session, Map<String, Object> map) {
        //判断，图片和文字都没有时返回失败
        if (content.trim().length() == 0 && comPic.getOriginalFilename().trim().length() == 0) {
            map.put("post_id", post_id);
            map.put("resultType", 29);
            return "requestResult";
        }
        int imgNum = 0;

        if (comPic.getOriginalFilename().trim().length() > 0) {

            int validResult = validUtil.imgValid(comPic);

            if (validResult == -2) {
                //图片类型不对
                map.put("resultType", 30);
                map.put("post_id", post_id);
                return "requestResult";
            } else if (validResult == -3) {
                //图片过大
                map.put("resultType", 31);
                map.put("post_id", post_id);
                return "requestResult";
            } else if (validResult > 0) {
                imgNum++;
            }
        }
        //存储信息
        Date currTime = new Date(System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(currTime);
        User user = (User) session.getAttribute("user");
        String uname = user.getUser_name();
        String account = user.getUser_account();
        Comment comment = new Comment(account, uname, time, content, imgNum, post_id);
        BigInteger commentid = commentService.addCommentByPID(comment);
        if (imgNum > 0) {
            ioUtil.copyCommentImage(commentid, comPic);
        }
        map.put("post_id", post_id);
        map.put("resultType", 28);
        return "requestResult";
    }
}
