package com.zx.shark.controller.blog;

import com.zx.shark.model.ContentDO;
import com.zx.shark.service.ContentService;
import com.zx.shark.utils.DateUtils;
import com.zx.shark.utils.PageUtils;
import com.zx.shark.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/blog")
@CrossOrigin(origins = "*")
@Controller
public class BlogController {
    @Autowired
    ContentService contentService;

    //首页
    @GetMapping()
    String blog(){
        return "index/index";
    }

        //获取首页文章的集合
    @ResponseBody
    @GetMapping("/open/list")
    public PageUtils openList(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        List<ContentDO> ContentList = contentService.list(query);
        int total = contentService.count(query);
        PageUtils pageUtils = new PageUtils(ContentList,total);
        return pageUtils;
    }

    @GetMapping("/open/post/{cid}")
    ModelAndView post(@PathVariable("cid") long cid, ModelAndView modelAndView){
        ContentDO contentDO = contentService.get(cid);
        modelAndView.addObject("bContent",contentDO);
        modelAndView.addObject("gtmModified", DateUtils.format(contentDO.getGtm_modified()));
        modelAndView.setViewName("index/article");
        return modelAndView ;
    }

    @GetMapping("/open/page/{categories}")
        ModelAndView about(@PathVariable("categories")String categories,ModelAndView modelAndView){
        Map<String, Object> map = new HashMap<>(16);
        map.put("categories", categories);
        ContentDO bContentDO =null;
        if(contentService.list(map).size()>0){
            bContentDO = contentService.list(map).get(0);
        }
        modelAndView.addObject("bContent", bContentDO);
        modelAndView.setViewName("index/post");
        return modelAndView;
        }
}
