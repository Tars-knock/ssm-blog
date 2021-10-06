package cn.tarsknock.controller;

import cn.tarsknock.entity.Blog;
import cn.tarsknock.entity.PageBean;
import cn.tarsknock.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(path = "/")
    public ModelAndView index(@RequestParam(value = "page", required = false, defaultValue = "1") String page,
                              HttpServletRequest request){
        int pageSize = 10;

        ModelAndView mav = new ModelAndView();
        PageBean pageBean = new PageBean(Integer.parseInt(page), pageSize, blogService.getTotal(null).intValue());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", pageBean.getStart());     paramMap.put("size", pageBean.getPageSize()); paramMap.put("statu", "publish");
        mav.addObject("blogList", blogService.list(paramMap));
        mav.addObject("pageInfo", pageBean);
        mav.setViewName("index");
        return mav;
    }
}
