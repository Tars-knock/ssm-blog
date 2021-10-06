package cn.tarsknock.controller.admin;

import cn.tarsknock.entity.Link;
import cn.tarsknock.entity.PageBean;
import cn.tarsknock.service.LinkService;
import cn.tarsknock.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequiresRoles("admin")
@Controller
public class LinkAdminController {

    @Autowired
    private LinkService linkService;

    @RequestMapping(path = "admin/links")
    public String link(HttpServletRequest request){
        request.setAttribute("links", linkService.list());
        return "admin/links";
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "admin/links/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil save(Link link){
        try{
            if(link.getId()==null) {
                linkService.add(link);
                return ResponseUtil.success();
            }
            else{
                linkService.update(link);
                return ResponseUtil.success();
            }
        }catch (Exception e){
            return ResponseUtil.fail("保存失败");
        }
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "admin/links/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil delete(String id){
        try{
            linkService.delete(Integer.parseInt(id));
            return ResponseUtil.success();
        }catch (Exception e){
            return ResponseUtil.fail("删除失败");
        }
    }
}
