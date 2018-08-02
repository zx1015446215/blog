package com.zx.shark.controller.book;

import com.zx.shark.mapper.BookMapper;
import com.zx.shark.model.Book;
import com.zx.shark.model.User;
import com.zx.shark.model.UserBook;
import com.zx.shark.service.impl.BookServiceImpl;
import com.zx.shark.service.impl.UserServiceImpl;
import com.zx.shark.utils.JSONResult;
import com.zx.shark.utils.PageUtils;
import com.zx.shark.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BookMapper bookMapper;

    //书的首页
    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("book");
        List<Book> books = bookMapper.selectAllBooks();
        modelAndView.addObject("books",books);
        return modelAndView;
    }


    @RequestMapping("findbook")
    public ModelAndView findbook(){
        ModelAndView modelAndView = new ModelAndView("findbook");
        List<Book> books = bookService.findAllBook();
        modelAndView.addObject("books",books);
        return modelAndView;
    }

    /**
     * 存储书籍
     * @param request
     * @return
     */
    @RequestMapping("savebook")
    @ResponseBody
    public JSONResult savebook(HttpServletRequest request){
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String author = request.getParameter("author");
        String company = request.getParameter("company");
        Date date = Date.valueOf(request.getParameter("publishtime"));
        int total = Integer.parseInt(request.getParameter("total"));
        Book book = new Book(name,type,author,company,date,total);
        try {
            bookMapper.insertBook(book);
        }catch (Exception e){
            System.out.println("添加书籍出错原因："+e.toString());
            return JSONResult.errorMsg("添加失败");
        }
        return JSONResult.ok();
    }

    /**
     * 删除书籍
     * @param request
     * @return
     */
    @RequestMapping("deletebook")
    @ResponseBody
    public JSONResult deletebook(HttpServletRequest request){
        String[] ttr = request.getParameter("ids").split(",");
        List<Integer> booksId = new ArrayList<>();
        for(int i=0;i<ttr.length;i++){
            booksId.add(Integer.valueOf(ttr[i]));
        }
         try {
             bookService.removeBook(booksId);
         }catch (Exception e){
             return JSONResult.errorMsg("删除失败");
         }
        return JSONResult.ok();
    }

    /**
     * 预约书籍
     * @param request
     * @return
     */
    @RequestMapping("borrowbook")
    @ResponseBody
    public JSONResult borrowbook(HttpServletRequest request){
        int book_id = Integer.parseInt(request.getParameter("id"));

        try {
            bookService.borrowBook(book_id);
        }catch (Exception e){
            System.out.println("预约书籍失败的原因:"+e.toString());
            return JSONResult.errorMsg("预约失败");
        }
        return JSONResult.ok();
    }

    @RequestMapping("/cancelbook")
    @ResponseBody
    public JSONResult cancelbook(HttpServletRequest request){
        int book_id = Integer.parseInt(request.getParameter("id"));

         try{
             bookService.cancelBorrow(book_id);
         }catch (Exception e){
             System.out.println("取消预约失败原因:"+e.toString());
             return JSONResult.errorMsg("预约失败");
         }

        return JSONResult.ok();
    }
    @RequestMapping("/selectNeedbook")
    @ResponseBody
    public JSONResult selectNeedbook(HttpServletRequest request){
        List<Book> books = new ArrayList<>();
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        if(name!=null&&name.length()!=0){
            name = name+"+";
        }
        if(author!=null&&author.length()!=0){
            author = author+"+";
        }
        String type = request.getParameter("type");
        Book need = new Book(name,author,type);
        try{
            books=bookService.selectNeedBook(need);
        }catch (Exception e){
            System.out.println("查询出错原因:"+e.toString());
            return JSONResult.errorMsg("查询出错");
        }
        return JSONResult.ok(books);
    }
}
