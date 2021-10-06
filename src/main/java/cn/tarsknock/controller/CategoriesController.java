package cn.tarsknock.controller;

import cn.tarsknock.service.BlogService;
import cn.tarsknock.service.BlogTypeService;
import cn.tarsknock.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CategoriesController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private LinkService linkService;

    @RequestMapping(path = "categories")
    public String categories(HttpServletRequest request){
        request.setAttribute("blogTypes", blogTypeService.countList());
//        request.setAttribute("blogList", blogService.list(new HashMap<String, Object>()));

        return "categories";
    }

    @RequestMapping(path = "categories/{typeId}")
    public String category(HttpServletRequest request,
                           @PathVariable("typeId") Integer typeId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("typeId", typeId);

        request.setAttribute("blogType", blogTypeService.findById(typeId));
        request.setAttribute("blogList", blogService.list(paramMap));
        return "category_detail";
    }

    @RequestMapping(path = "archives")
    public String archives(){
        return "archives";
    }

    @RequestMapping(path="links")
    public String links(HttpServletRequest request){
        request.setAttribute("links", linkService.list());
        return "links";
    }

    @RequestMapping(path = "about")
    public String about(){
        return "about";
    }
}
