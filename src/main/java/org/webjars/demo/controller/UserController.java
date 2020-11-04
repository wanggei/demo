package org.webjars.demo.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import org.webjars.demo.model.Book;
import org.webjars.demo.model.Borrow_Read;
import org.webjars.demo.model.User;
import org.webjars.demo.service.BookService;
import org.webjars.demo.service.Borrow_ReadService;
import org.webjars.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private Borrow_ReadService borrow_ReadService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/user/user_center.html")
	public String user_Center(Model model) {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String date=dateFormat.format(new Date());
		
		model.addAttribute("date", date);
		
		return "user_center";
	}
	//修改密码
	@RequestMapping(value = "/password_update.html",method=RequestMethod.GET)
	public String password_update() {
		
		
		return "user/password_update";
	}
	//进入用户中心页面
	@RequestMapping(value = "/user/usercenter.html")
	public String userCenter(){

		return "user_center";
	}
	//进入修改密码页面
	@RequestMapping(value = "/user/password_update.html",method = RequestMethod.GET)
	public String password_Html(){

		return "password_update";
	}
	
	//获取所有的借书详情
	@RequestMapping(value = "/user/book_back.html",method = RequestMethod.GET)
	public String book_back(Model model,HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Borrow_Read> borrow_Read=borrow_ReadService.selectUsername(user.getUsername());
		
		model.addAttribute("borrow_Read", borrow_Read);
		
		return "book_back";
	}
	//还书操作
	@ResponseBody
	@RequestMapping(value = "/user/black.html",method = RequestMethod.GET)
	public String black(String bookname,HttpSession session) {
//		//根据书名去操作相对应的数据
		User user=(User) session.getAttribute("user");
		
		borrow_ReadService.updateByBookName(bookname,user.getUsername());
		Book book=bookService.blurSeBoByBn(bookname);
		int num = book.getNum();
		num=num+1;
		book.setNum(num);
		bookService.update(book);
		return "success";
	}
	//批量还书操作
	@ResponseBody
	@RequestMapping(value = "/user/batch.html",method=RequestMethod.POST)
	public String batchArray(String bookname,HttpSession session) {
		User user = (User) session.getAttribute("user");
		bookname=bookname.substring(1,bookname.length()-1);
		bookname=bookname.replaceAll("\"", "");
		
		String [] string=bookname.split(",");
		
		for(String s:string) {
			
			borrow_ReadService.updateByBookName(s,user.getUsername());
			Book book=bookService.blurSeBoByBn(s);
			int num = book.getNum();
			num=num+1;
			book.setNum(num);
			bookService.update(book);
		}
		return "success";
	}
	//修改密码操作
	@ResponseBody
	@RequestMapping(value = "/user/pswup.html",method=RequestMethod.POST)
	public String password_update(String password,String passwordupdate,String passwordupdatetwo,HttpSession session) {
		//先获取所有的表单输入的信息，然后通过用户名去数据库查找是否有对应的人，没有就报错，
		//然后就比较修改的密码和确认的密码是否一致，一致就修改密码，返回用户中心首页
		User user=(User) session.getAttribute("user");
		
		String username=user.getUsername();

		User usertwo=userService.login(username);
		if(usertwo==null) {
			return "error";
		}else {
			if(!passwordupdate.equals(passwordupdatetwo)) {
				return "error";
			}else {
				userService.updatePsByUn(passwordupdate,username);
				return "success";
			}
		}
	}
	//首页借书操作
	@ResponseBody
	@RequestMapping(value = "/user/addbook",method = RequestMethod.GET)
	public String addbooks(HttpSession session,String bookname){
		List<Book> books = bookService.blurSelBookByBo(bookname);
		for (Book book:books) {
			int num = book.getNum();
			if (num==0){
				return "error";
			}else {
				num=num-1;
				book.setNum(num);
				bookService.update(book);
				User user=(User)session.getAttribute("user");
				String username=user.getUsername();

				List<String> list=new ArrayList<>();
				list.add(bookname);
				list.add(username);
				borrow_ReadService.addInfo(list.toArray());
			}

		}
		return "success";
	}
	@RequestMapping(value = "/user/addbooks",method = RequestMethod.GET)
	public String addbookss(HttpSession session,String bookname){
		List<Book> books = bookService.blurSelBookByBo(bookname);
		for (Book book:books) {
			int num = book.getNum();
			if (num==0){
				return "bookDou";
			}else {
				num=num-1;
				book.setNum(num);
				bookService.update(book);
				User user=(User)session.getAttribute("user");
				String username=user.getUsername();

				List<String> list=new ArrayList<>();
				list.add(bookname);
				list.add(username);
				borrow_ReadService.addInfo(list.toArray());
			}

		}
		return "redirect:/";
	}
	//注册用户
	@RequestMapping(value = "/addusertwo.html",method = RequestMethod.POST)
	public String addUser(User user, @RequestParam(name = "select") Integer[] roleid, HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		//添加一条用户

		userService.addUser(user, roleid);


		return "login";
	}
	@RequestMapping(value = "/bookinfo.html")
	//书本详细信息
	public String bookinfo(String bookid, Model model){
		/*Book book = bookService.blurSeBoByBn(bookname);
		System.out.println(book);
		int id = book.getId();*/
		/*String bookid=Integer.toString(id);*/
		Book oneByBid = bookService.getOneByBid(bookid);
		model.addAttribute("oneByBid",oneByBid);
		return "bookDou";
	}
}
