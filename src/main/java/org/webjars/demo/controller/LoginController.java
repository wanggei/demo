package org.webjars.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.webjars.demo.model.Book;
import org.webjars.demo.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/loginuser.html")
    public String login(String username, String password, Model model){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            List<Book> all = bookService.getAll();
            model.addAttribute("books",all);

            return "qihangke";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
    @RequestMapping("/logout.html")
    public  String logout(HttpSession session){
        session.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "redirect:/";
    }
}
