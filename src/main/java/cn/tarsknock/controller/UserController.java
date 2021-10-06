package cn.tarsknock.controller;

import cn.tarsknock.entity.User;
import cn.tarsknock.service.UserService;
import cn.tarsknock.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    public String loginCheck(User user , String from, HttpServletRequest request){
//        String username = user.getUsername();
//        String password = user.getPassword();
////        进行安全验证
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
//        try {
////            传递token给shiro的realm
//            subject.login(token);
//            if(from.equals("illegalUsers")){
//                return "redirect://";
//            }
//            else{
//                return "redirect:/"+from;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            request.setAttribute("User", user);//将已填写的内容填回去，提升用户体验
//            request.setAttribute("errorInfo", "用户名或密码错误");//给一个错误提示
//        }
//        return null;
//    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginCheck(User user ,
                             String from,
                             HttpServletRequest request){
        String username = user.getUsername();
        String password = user.getPassword();
//        进行安全验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        try {
//            传递token给shiro的realm
            subject.login(token);
            if(from.endsWith("login")||from.endsWith("register")||from == null){
                return "redirect:/";
            }
            else{
                return "redirect:"+from;
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("User", user);//将已填写的内容填回去，提升用户体验
            request.setAttribute("errorInfo", "用户名或密码错误");//给一个错误提示
        }
        return null;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)

    public String login( @RequestHeader(value = "Referer", required = false, defaultValue = "") String Referer,
                         HttpServletRequest request){
        request.setAttribute("from", Referer);
        return "login";
    }


    //注册事宜
    @RequestMapping(path = "/register")
    public String register(){
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String addUser(User user, HttpServletRequest request){
        userService.add(user);
//        TODO: 记得更新userservice
        request.setAttribute("User", user);
        return "redirect:/login";
    }


    @RequestMapping(value = "/register/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile dropzFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        // 获取上传的原始文件名
        String fileName = dropzFile.getOriginalFilename();
        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/shared");
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        // 判断并创建上传用的文件夹
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            // 写入文件
            dropzFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回 JSON 数据，这里只带入了文件名
        result.put("fileName", "static/shared/"+file.getName());

//        ResponseUtil response = ResponseUtil.success(result);
        return result;
    }
}
