package cn.xsshome.mvcdo.controller.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.DeptDO;
import cn.xsshome.mvcdo.pojo.system.RoleDO;
import cn.xsshome.mvcdo.pojo.system.UserDO;
import cn.xsshome.mvcdo.service.system.RoleService;
import cn.xsshome.mvcdo.service.system.UserService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;


/**
 * 用户管理Controller
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="/user")
@Scope("prototype")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转用户管理页面");
		return "system/user/user";
	}
	/**
	 * 加载用户数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/listUser")
	@ResponseBody
	public PageUtils listUser(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response){
		try {
			//查询列表数据 对分页参数进行类型转换
			Query query = new Query(params);
			List<UserDO> userDOs = userService.list(query);
			int total = userService.count(query);
			PageUtils pageUtils = new PageUtils(userDOs, total);
			return pageUtils;
		} catch (Exception e) {
			logger.info("listUser查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 添加用户
	 * @param model 权限数据
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/addUser")
	public String addUser(Model model){
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles",roles);
		return "system/user/addUser";
	}
	/**
	 * 编辑用户
	 * @param model 权限数据 用户数据
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/editUser/{id}")
	public String editUser(Model model,@PathVariable("id")Long id){
		try {
			UserDO userDO = userService.get(id);
			model.addAttribute("user",userDO);
			List<RoleDO> roles = roleService.list(id);
			model.addAttribute("roles",roles);
		} catch (Exception e) {
			logger.error("editUser出错"+e.getMessage());
			model.addAttribute("msg", "系统错误，请联系管理员");
		}
		return "system/user/editUser";
	}
	/**
	 * 保存新加用户
	 * @param userDO  用户数据
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/saveUser")
	@ResponseBody
	public WholeResponse saveUser(UserDO userDO,HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if(userService.save(userDO)>0){
				return WholeResponse.successResponse("保存数据成功");
			}else{
				return WholeResponse.errorResponse("1", "保存失败");
			}
		} catch (Exception e) {
			logger.error("saveUser保存出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 用户更新
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public WholeResponse updateUser(UserDO userDO,HttpServletRequest request){
		logger.info("updateUser用户更新");
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许修改数据");
			}
			if(userService.update(userDO)>0){
				return WholeResponse.successResponse("更新数据成功");
			}else{
				return WholeResponse.errorResponse("1", "更新失败");
			}
		} catch (Exception e) {
			logger.error("updateUser更新出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 用户删除
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/removeUser")
	@ResponseBody
	public WholeResponse removeUser(Long id,HttpServletRequest request){
		logger.info("removeUser用户删除");
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许删除数据");
			}
			if(userService.remove(id)>0){
				return WholeResponse.successResponse("删除数据成功");
			}else{
				return WholeResponse.errorResponse("1", "删除失败");
			}
		} catch (Exception e) {
			logger.error("removeUser用户删除出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 用户批量删除
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/batchRemoveUser")
	@ResponseBody
	public WholeResponse batchRemoveUser(@RequestParam("ids[]") Long[] ids,HttpServletRequest request){
		logger.info("batchRemoveUser用户批量删除");
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许批量删除数据");
			}
			if(userService.batchremove(ids)>0){
				return WholeResponse.successResponse("批量删除数据成功");
			}else{
				return WholeResponse.errorResponse("1", "批量删除失败");
			}
		} catch (Exception e) {
			logger.error("batchRemoveUser用户批量删除出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 跳转重置密码页面
	 * @param userId 用户id
	 * @param model 对象数据
	 * @return
	 */
	@RequestMapping(value="/resetPwd/{id}",method=RequestMethod.GET)
	public String resetPwd(@PathVariable("id") Long userId,Model model){
		try {
			UserDO userDO = new UserDO();
			userDO.setUserId(userId);
		} catch (Exception e) {
			logger.error("跳转重置密码页面出错了"+e.getMessage());
		}
		return "system/user/reset_pwd";
	}
	/**
	 * 重置密码方法
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/resetPwd",method=RequestMethod.POST)
	@ResponseBody
	public WholeResponse restPwd(UserDO userDO,HttpServletRequest request){
		logger.info("restPwd重置密码");
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许执行重置密码");
			}
			if(userService.resetPwd(userDO)>0){
				return WholeResponse.successResponse("重置密码成功");
			}else{
				return WholeResponse.errorResponse("1", "重置密码失败");
			}
		} catch (Exception e) {
			logger.error("restPwd重置密码出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 更新密码方法
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	@ResponseBody
	public WholeResponse updatePwd(UserDO userDO,HttpServletRequest request){
		String pwdOld = request.getParameter("pwdOld");
		String pwdNew = request.getParameter("pwdNew");
		logger.info("updatePwd更新密码  旧密码=="+pwdOld+"===新密码==="+pwdNew);
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许执行重置密码");
			}
			UserDO dbUserDO = userService.get(userDO.getUserId());
			if(!pwdOld.equals(dbUserDO.getPassword())){
				return WholeResponse.errorResponse("2", "旧密码不正确");
			}else if (pwdNew.equals(dbUserDO.getPassword())) {
				return WholeResponse.errorResponse("3", "新密码与旧密码相同");
			}else{
				userDO.setPassword(pwdNew);
				if(userService.updatePwd(userDO)>0){
					return WholeResponse.successResponse("修改密码成功");
				}else{
					return WholeResponse.errorResponse("1", "修改密码失败");
				}
			}
		} catch (Exception e) {
			logger.error("updatePwd修改密码出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 检测是否是已经存在的用户名
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/exitName",method=RequestMethod.POST)
	@ResponseBody
	public boolean exitName(@RequestParam Map<String, Object> params){
		boolean flag = userService.exit(params);
		return !flag;
	}
	/**
	 * 加载部门树形结构数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/tree")
	@ResponseBody
	public Tree<DeptDO> listTree(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree = userService.getTree();
			return tree;
		} catch (Exception e) {
			logger.info("listUser查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 树形展示页面
	 * @return
	 */
	@RequestMapping(value="/treeView")
	String treeView() {
		return  "system/user/userTree";
	}
	/**
	 * 个人页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/personal")
	String personal(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDO userDO = userService.selectByUserName(session.getAttribute("username").toString());
		model.addAttribute("user", userDO);
		return "system/user/personal";
	}
	/**
	 * 个人页面修改
	 * @param userDO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePersonal",method=RequestMethod.POST)
	@ResponseBody
	public WholeResponse updatePersonal(UserDO userDO,HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			if(AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许修改数据");
			}
			if(userService.updatePersonal(userDO)>0){
				return WholeResponse.successResponse("修改数据成功");
			}else{
				return WholeResponse.errorResponse("1", "修改数据失败");
			}
		} catch (Exception e) {
			logger.error("updatePeronal修改数据出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
