package org.webjars.demo.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.demo.model.Role;
import org.webjars.demo.model.User;
import org.webjars.demo.service.RoleService;
import org.webjars.demo.service.UserRoleService;
import org.webjars.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/admin.html",method = RequestMethod.GET)
    public String admin(Model model) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(new Date());

        model.addAttribute("date", date);

        return "admin";
    }
    @RequestMapping("/admin/admin_userselect.html")
    public  String adminse(Model model, Integer pagesize, Integer pagenum){

        //获取所有的角色
        List<Role> roList= roleService.getAll();

        model.addAttribute("roList", roList);
        //获取所有的用户信息、

        if(pagesize==null) pagesize=1;
        if(pagenum==null) pagenum=10;
        PageInfo<User> userPage=userService.getUserPage(pagesize, pagenum);


        model.addAttribute("userPage", userPage);

        /*
         * List<User> userList=userService.getSelectAll();
         *
         * model.addAttribute("userList", userList);
         */

        return "admin_userselect";
    }
    @RequestMapping(value = "/admin/adduser.html",method = RequestMethod.POST)
    public String addUser(User user, @RequestParam(name = "select") Integer[] roleid, HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        //添加一条用户

        userService.addUser(user, roleid);


        return "redirect:/admin/admin_userselect.html";
    }
    //更新数据前的一些操作
    @ResponseBody
    @RequestMapping(value = "/admin/admin_userupdate.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String updateUser(Integer uid , HttpServletRequest request) {

        //通过一条id获取对应的数据
        User user =	userService.selectByUserId(uid);

        List<Role> uList=user.getRoles();

        //获取所有的权限数据
        String opt="";
        List<Role> roleList=roleService.getAll();



        for(Role rlist : roleList) {

            if(uList.contains(rlist)) {
                opt=opt+"<option selected value=\""+rlist.getId()+"\">"+rlist.getName()+"</option>\r\n";
            }else {
                opt=opt+"<option value=\""+rlist.getId()+"\">"+rlist.getName()+"</option>\r\n";
            }

        }

        return "<div class=\"modal-header\">\r\n" +
                "				        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" ><span >&times;</span></button>\r\n" +
                "				        <h4 class=\"modal-title\" >更新用户</h4>\r\n" +
                "				      </div>\r\n" +
                "				      <div class=\"modal-body\">\r\n" +
                "							<form action=\"/admin/admin_update.html\" method=\"post\" id=\"formlogin\" >\r\n" +
                "								<div class=\"form-group\">\r\n" +
                "									<label>学号:</label>\r\n" +
                "									<input type=\"text\"disabled name=\"username\" placeholder=\"学号\" class=\"form-control\" value=\""+user.getUsername()+"\"/>\r\n" +
                "									<input type=\"hidden\" name=\"id\" value=\""+user.getId()+"\"/>				"+
                "								</div>\r\n" +
                "								<div class=\"form-group\">\r\n" +
                "									<label>常用的手机号码</label>\r\n" +
                "									<input type=\"text\" name=\"phone\" placeholder=\"手机号码\" class=\"form-control\" value=\""+user.getPhone()+"\"/>\r\n" +
                "								</div>\r\n" +
                "								\r\n" +
                "								<div class=\"form-group \">\r\n" +
                "										<lable>关联的角色:</lable>\r\n" +
                "										<select class=\"selectpicker form-control\" multiple name=\"select\">\r\n" +
                                                                    opt+
                "											</select>\r\n" +
                "										</div>\r\n" +
                "								<div class=\"form-group\">\r\n" +
                "									<label>密码:</label>\r\n" +
                "									<input type=\"text\" name=\"password\" placeholder=\"密码\" class=\"form-control\" value=\""+user.getPassword()+"\"/>\r\n" +
                "								</div>\r\n" +
                "								<div class=\"form-group\">\r\n" +
                "									<label>邮箱:</label>\r\n" +
                "									<input type=\"text\" name=\"email\" placeholder=\"邮箱\" class=\"form-control\" value=\""+user.getEmail()+"\"/>\r\n" +
                "								</div>\r\n" +
                "							</form>\r\n" +
                "				      </div>\r\n" +
                "				      <div class=\"modal-footer\">\r\n" +
                "				        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n" +
                "				        <button type=\"button\" class=\"btn btn-primary\" onclick=\"updatesubmit()\" >更新用户</button>\r\n" +
                "				      </div>" ;
    }
    //更新用户操作

    @RequestMapping(value = "/admin/admin_update.html",method = RequestMethod.POST)
    public String updateUser(User u ,@RequestParam(name = "select")Integer[] uid) {

        userService.upUserRoleById(u,uid);

        return "redirect:/admin/admin_userselect.html";
    }

    //删除一条记录
    @ResponseBody
    @RequestMapping(value = "/admin/deluser.html",method = RequestMethod.GET)
    public String delUser(@RequestParam(name = "uid") Integer uid) {

        //删除一条记录首先删除这条记录所对应的role表
        userRoleService.deleteByUid(uid);

        //删除对应的用户信息

        userService.delete(uid);

        return "success";
    }
    //删除多条记录
    @ResponseBody
    @RequestMapping(value = "/admin/delbatchuser.html",method = RequestMethod.POST)
    public String delbatchuser(String uids) {
        uids=uids.substring(1, uids.length()-1);//去掉前后的[]框
        uids=uids.replaceAll("\"", "");//去掉""
        String [] uidStArr=uids.split(",");//变成String集合

        Integer [] integers=new Integer[uidStArr.length];

        for(int i=0;i<uidStArr.length;i++) {
            integers[i]=Integer.parseInt(uidStArr[i]);
        }

        userService.batchDelUserById(integers);

        return "success";
    }

    //模糊查询
    @RequestMapping(value = "/admin/blurselect.html",method = RequestMethod.GET)
    public String blurselect(String stuid,Model model) {

        int pagesize=1;
        int pagenum=10;


        PageInfo<User> userPage=userService.getUserPagese(pagesize, pagenum,stuid);

        model.addAttribute("userPage", userPage);

        return "admin_userselect";
    }

}
