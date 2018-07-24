package com.zx.shark.controller.book;

import com.zx.shark.model.Book;
import com.zx.shark.model.User;
import com.zx.shark.model.UserBook;
import com.zx.shark.service.impl.BookServiceImpl;
import com.zx.shark.service.impl.UserServiceImpl;
import com.zx.shark.utils.JSONResult;
import com.zx.shark.utils.PageUtils;
import com.zx.shark.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    UserServiceImpl userService;

    //书的首页
    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("book");
        List<Book> books = bookService.listAllBooks();
        modelAndView.addObject("books",books);
        return modelAndView;
    }


    @RequestMapping("findbook")
    public ModelAndView findbook(){
        ModelAndView modelAndView = new ModelAndView("findbook");
        //获取全部书籍的信息
        List<Book> books = bookService.listAllBooks();
        //获取用户预约书籍的id号
//        String username = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        List<Integer> listid = bookService.selectUserBooks("zhuxin");
        for(Book book:books){
            if(listid.contains(book.getId())){
                book.setFlag(true);
            }
        }
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
            bookService.saveBook(book);
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
        //从SecurityContextHolder中获取用户名
        String username = "zhuxin";
//        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getCredentials()+"  "+authentication.getPrincipal());
        //根据用户名从数据库中获取用户的id
        User user = userService.findUserByUsername(username);
        int user_id = Math.toIntExact(user.getId());
        //将book_id和user_id添加如数据库表book_user中，并在book中将remain减少1
        UserBook userBook = new UserBook(user_id,book_id);
        try {
            bookService.saveUserBook(userBook);
            bookService.updateBookRemain(book_id);
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
        //从SecurityContextHolder中获取用户名
        String username = "zhuxin";
//        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getCredentials()+"  "+authentication.getPrincipal());
        //根据用户名从数据库中获取用户的id
        User user = userService.findUserByUsername(username);
        int user_id = Math.toIntExact(user.getId());
        UserBook userBook = new UserBook(user_id,book_id);
         try{
             //书籍总库的某书剩余量加1
             bookService.RebackBookRemain(book_id);
             //取消某书与借阅人之间的联系
             bookService.deleteUserBook(userBook);
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
        String type = request.getParameter("type");
        Book need = new Book(name,author,type);
        try{
            books = bookService.selectNeedBook(need);
            List<Integer> listid = bookService.selectUserBooks("zhuxin");
            for(Book book:books){
                if(listid.contains(book.getId())){
                    book.setFlag(true);
                }
            }
        }catch (Exception e){
            System.out.println("查询出错原因:"+e.toString());
            return JSONResult.errorMsg("查询出错");
        }
        return JSONResult.ok(books);
    }
}
