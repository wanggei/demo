package org.webjars.demo.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.demo.model.Book;
import org.webjars.demo.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class AdminBookController {

    @Autowired
    BookService bookService;

    //获取所有的书本信息
    @RequestMapping(value = "/admin/book.html")
    public String bookindex(Model model,Integer PageSize,Integer PageNum){

        if(PageSize==null) PageSize=1;
        if(PageNum==null)  PageNum=10;

        PageInfo<Book> pageInfo=bookService.getAllByPage(PageSize,PageNum);
        model.addAttribute("pageInfo",pageInfo);
        return "book";
    }
    //获取当前所需要更新书本的信息
    @ResponseBody
    @RequestMapping(value = "/admin/update_book.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateFirst(String bid){

        Book book=bookService.getOneByBid(bid);

        return "<div class=\"modal-header\">\n" +
                "\t\t\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" ><span >&times;</span></button>\n" +
                "\t\t\t\t\t\t\t<h4 class=\"modal-title\" >更新图书</h4>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"modal-body\">\n" +
                "\t\t\t\t\t\t\t<form action=\"/admin/update.html\" method=\"post\" id=\"from\" >\n" +
                "\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
                "\t\t\t\t\t\t\t\t\t<label>书名:</label>\n" +
                "\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"bookname\"  class=\"form-control\" value=\""+book.getBookname()+"\"/>\n" +
                "\t\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"id\"  class=\"form-control\" value=\""+book.getId()+"\"/>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
                "\t\t\t\t\t\t\t\t\t<label>作者</label>\n" +
                "\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"author\" class=\"form-control\" value=\""+book.getAuthor()+"\"/>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
                "\t\t\t\t\t\t\t\t\t<label>库存</label>\n" +
                "\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"num\"  class=\"form-control\" value=\""+book.getNum()+"\"/>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
                "\t\t\t\t\t\t\t\t\t<label>出版社</label>\n" +
                "\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"press\"  class=\"form-control\" value=\""+book.getPress()+"\"/>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
                "\t\t\t\t\t\t\t\t\t<label>价格</label>\n" +
                "\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"price\"  class=\"form-control\" value=\""+book.getPrice()+"\"/>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</form>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"modal-footer\">\n" +
                "\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n" +
                "\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" onclick=\"updatesubmit()\">更新书本</button>\n" +
                "\t\t\t\t\t\t</div>";
    }
    //实现更新操作
    @RequestMapping(value = "/admin/update.html",method = RequestMethod.POST)
    public String updateSencond(Book b){

            bookService.upBookById(b);

        return "redirect:/admin/book.html";
    }

    //实现删除一条记录
    @ResponseBody
    @RequestMapping(value = "/admin/deleteOne.html",method = RequestMethod.GET)
    public String deleteone(Integer bookid){
        bookService.deleteOneById(bookid);
        return "success";
    }
    //实现多条删除
    @ResponseBody
    @RequestMapping(value = "/admin/delbatchbook.html",method = RequestMethod.POST)
    public String deletebatch(String  bookidJson ){
        bookidJson=bookidJson.substring(1, bookidJson.length()-1);//去掉前后的[]框
        bookidJson=bookidJson.replaceAll("\"", "");//去掉""
        String [] uidStArr=bookidJson.split(",");//变成String集合
        Integer [] integers=new Integer[uidStArr.length];

        for(int i=0;i<uidStArr.length;i++) {
            bookService.deleteOneById(Integer.parseInt(uidStArr[i]));
        }

        return "success";
    }
    //模糊查询
    @RequestMapping(value = "/admin/booksel.html",method = RequestMethod.GET)
    public String blurselect(String bookname,Model model) {

        int pagesize=1;
        int pagenum=10;

        PageInfo<Book> userPage=bookService.getBookPage(pagesize,pagenum,bookname);

        model.addAttribute("pageInfo",userPage);

        return "book";
    }
    //添加图书
    @RequestMapping(value = ("/admin/addbook"),method = RequestMethod.POST)
    public String FileUpload(Book b,@RequestParam("image") MultipartFile multi, HttpServletRequest request) throws IllegalStateException, IOException, IOException {
        //判断文件是否存在
        if(!multi.isEmpty()) {
            String path="C:\\demo02\\src\\main\\resources\\static\\images\\";

            //获取文件名字
            String file2=multi.getOriginalFilename();

            File filepath=new File(path, file2);

            //判断是否有路径，没有就创建一个
            if(!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            multi.transferTo(filepath);
            b.setImages("/images/"+file2);
            bookService.addBook(b);
        }
        return "redirect:/admin/book.html";
    }
}

