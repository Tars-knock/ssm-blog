package cn.tarsknock.controller.admin;

import cn.tarsknock.entity.BlogType;
import cn.tarsknock.service.BlogTypeService;
import cn.tarsknock.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresRoles("admin")
@Controller
public class BlogTypeAdminController {
    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping("/admin/blog_type")
    public String list(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        List<BlogType> categories = blogTypeService.list(map);
        Long total = blogTypeService.getTotal(map);
        request.setAttribute("categories", categories);
        return "admin/blog_type";

    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "/admin/blog_type/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil delete(int id){
        try {
            blogTypeService.delete(id);
        }catch (Exception e){
            return ResponseUtil.fail("删除失败啦");
        }
        return ResponseUtil.success();
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "/admin/blog_type/add", method = RequestMethod.POST)
    @ResponseBody
//    public String add(int id, String typeName){
    public ResponseUtil add(BlogType blogType){
        try{
//            blogTypeService.add(blogType);
            System.out.println(blogTypeService.add(blogType));
        }catch (Exception e){
            return ResponseUtil.fail(null);
        }
        return ResponseUtil.success();
    }
}
