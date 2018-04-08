package cn.xsshome.mvcdo.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.MenuDO;
import cn.xsshome.mvcdo.pojo.system.UserDO;
import cn.xsshome.mvcdo.service.system.MenuService;
import cn.xsshome.mvcdo.service.system.UserService;
import cn.xsshome.mvcdo.util.LoginResponse;
import cn.xsshome.mvcdo.util.PrintUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 登录跳转Controller
 * @author 小帅丶
 */
@Controller
@RequestMapping(value="/mvcdo")
@Scope("prototype")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	MenuService menuService;
	@Autowired
	UserService userService;
	/**
	 * 跳转主页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDO userDO = userService.selectByUserName(session.getAttribute("username").toString());
		List<Tree<MenuDO>> menus = menuService.listMenuTree(userDO.getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", userDO.getName());
		model.addAttribute("username",userDO.getUsername());
		return "main";
	}
	/**
	 * 用户登录
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password,HttpServletRequest request,HttpServletResponse response){
		logger.info("login被访问，请求参数为:username="+username+"\tpassword="+password);
		LoginResponse loginResponse = null;
		HttpSession session = request.getSession();
		try {
			if(username.equals("")||password.equals("")){
				loginResponse=LoginResponse.errorLogin("用户名或密码不能为空");
				PrintUtil.printJson(response, JSONObject.toJSONString(loginResponse));
			}else{
				UserDO userDO = userService.retrieve(username, password);
				if(userDO!=null){
					session.setAttribute("username", username);
					loginResponse=LoginResponse.successLogin();
					PrintUtil.printJson(response, JSONObject.toJSONString(loginResponse));
				}else{
					loginResponse=LoginResponse.errorLogin("用户名或密码错误");
					PrintUtil.printJson(response, JSONObject.toJSONString(loginResponse));
				}
		
			}
		} catch (Exception e) {
			logger.error("出错了"+e.getMessage());
			loginResponse=LoginResponse.errorLogin("系统错误,稍后再试");
			PrintUtil.printJson(response, JSONObject.toJSONString(loginResponse));
		}
		return null;
	}
	/**
	 * 退出系统
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginout")
	public String loginOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		return "redirect:/login.jsp";
	}
}
