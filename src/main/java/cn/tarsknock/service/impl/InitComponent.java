package cn.tarsknock.service.impl;

import cn.tarsknock.entity.Blog;
import cn.tarsknock.entity.BlogType;
import cn.tarsknock.service.BlogService;
import cn.tarsknock.service.BlogTypeService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();

//        BlogTypeService blogService = (BlogTypeService) applicationContext.getBean("blogTypeService");
//        List<BlogType> blogTypeList = blogService.countList();
//        application.setAttribute("blogTypeCountList",blogTypeList);

        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        Map<String, Object> paramMap = new HashMap<>(); paramMap.put("statu", "publish");
        List<Blog> blogList = blogService.list(paramMap);
        application.setAttribute("blogList", blogList);

        //文章列表保存至context，减少与数据库的交互次数
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public  static void updateContext(ServletContext application){
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        Map<String, Object> paramMap = new HashMap<>(); paramMap.put("statu", "publish");
        List<Blog> blogList = blogService.list(paramMap);
        application.setAttribute("blogList", blogList);
    }
}
