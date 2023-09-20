package cn.tarsknock.controller.admin;

import cn.tarsknock.entity.User;
import cn.tarsknock.service.BlogService;
import cn.tarsknock.service.CommentService;
import cn.tarsknock.service.LinkService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private LinkService linkService;


    @RequiresAuthentication
    @RequiresRoles("admin")
    @RequestMapping(path = "/admin")
    public String adminIndex(HttpServletRequest request){
        User curUser = (User) request.getSession().getAttribute("currentUser");
        if(curUser == null ||( !curUser.getUsername().equals("Tars-knock") && !curUser.getUsername().equals("visitor"))){
            return "redirect:/login";
        }

        Map<String, Object> statistics = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", 0);
        paramMap.put("size", 5);

        statistics.put("comments", commentService.list(null).size());
        statistics.put("articals", blogService.list(new HashMap<String, Object>()).size());
        statistics.put("links", linkService.list().size());

        request.setAttribute("articles", blogService.list(paramMap));
        request.setAttribute("comments", commentService.list(paramMap));
        request.setAttribute("statistics", statistics);


        return "admin/index";
    }
}
