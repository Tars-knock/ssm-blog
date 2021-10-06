package cn.tarsknock.controller.admin;

import cn.tarsknock.entity.Blog;
import cn.tarsknock.entity.PageBean;
import cn.tarsknock.lucene.BlogIndex;
import cn.tarsknock.service.BlogService;
import cn.tarsknock.service.BlogTypeService;
import cn.tarsknock.service.CommentService;
import cn.tarsknock.service.LinkService;
import cn.tarsknock.service.impl.InitComponent;
import cn.tarsknock.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class BlogAdminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTypeService blogTypeService;
    private BlogIndex blogIndex = new BlogIndex();



    @RequestMapping(path = "/admin/article")
    public String blogList(HttpServletRequest request,
                           @RequestParam(value = "page", required = false, defaultValue = "1") String page){
        /**
         * article_list页面 一页有多少行
         */
        int pageSize = 6;

        PageBean pageBean = new PageBean(Integer.parseInt(page), pageSize, blogService.getTotal(null).intValue());
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", pageBean.getStart());
        paramMap.put("size", pageBean.getPageSize());
        request.setAttribute("articles", blogService.list(paramMap));
        request.setAttribute("pageInfo", pageBean);
        return "admin/article_list";
    }

    @RequestMapping(path = "/admin/article/edit")
    public String editBlog(HttpServletRequest request,
                           @RequestParam(value = "id", required = false) String id){
//        ServletContext servletContext = request.getServletContext();
//        request.setAttribute("categories",servletContext.getAttribute("blogTypeCountList"));
        if(id == null) {
            request.setAttribute("categories", blogTypeService.countList());
            return "admin/article_edit";
        }
        else{
            Blog blog = blogService.findById(Integer.parseInt(id));
            request.setAttribute("categories", blogTypeService.countList());
            request.setAttribute("contents",blog);
            return "admin/article_edit";
        }
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "/admin/article/{addOrUpdate}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil addAndUpdate(@PathVariable("addOrUpdate") String mod, Blog blog, HttpServletRequest request){
        if ("add".equals(mod)) {
            try {
                blogService.add(blog);

//                blogIndex.addIndex(blog);

                //刷新context缓存
//                ServletContext servletContext = request.getServletContext();
//                servletContext.setAttribute("blogList", blogService.countList());
                InitComponent.updateContext(request.getServletContext());

                return ResponseUtil.success();
            } catch (Exception e) {
                return ResponseUtil.fail("发布失败");
            }
        } else if ("update".equals(mod)) {
            try {
                blogService.updateContent(blog);

//                blogIndex.updateIndex(blog);

                //刷新context缓存
//                ServletContext servletContext = request.getServletContext();
//                servletContext.setAttribute("blogList", blogService.countList());
                InitComponent.updateContext(request.getServletContext());

                return ResponseUtil.success();
            } catch (Exception e) {
                return ResponseUtil.fail("更改失败");
            }
        } else {
            return ResponseUtil.fail("缺少参数：statu");
        }
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "/admin/article/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil deleteBlog(String id, HttpServletRequest request){
        try {
            blogService.delete(Integer.parseInt(id));

//            blogIndex.deleteIndex(Integer.parseInt(id));

            //刷新context缓存
//            ServletContext servletContext = request.getServletContext();
//            servletContext.setAttribute("blogList", blogService.countList());
            InitComponent.updateContext(request.getServletContext());

            return ResponseUtil.success();
        }catch (Exception e){
            return ResponseUtil.fail("删除失败");
        }
    }

    @RequestMapping(path = "/admin/article/uploadImg")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartFile imageFile,
                                           HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();

        // 获取上传的原始文件名
        String fileName = imageFile.getOriginalFilename();
        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/shared/articleImg");
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
            imageFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回 JSON 数据，
        result.put("success", true);
        result.put("message", "success");
        result.put("url", request.getContextPath() +"/static/shared/articleImg/"+file.getName());
        result.put("type", "image/"+fileSuffix.substring(1));

        return result;
    }


}
