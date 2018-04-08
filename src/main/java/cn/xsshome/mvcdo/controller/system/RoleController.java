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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.common.Constant;
import cn.xsshome.mvcdo.pojo.system.RoleDO;
import cn.xsshome.mvcdo.service.system.RoleService;
import cn.xsshome.mvcdo.util.WholeResponse;


/**
 * 角色管理Controller
 * @author 小帅丶
 * @date 2018年3月9日
 */
@Controller
@RequestMapping(value="/role")
@Scope("prototype")
public class RoleController {
	@Autowired
	RoleService roleService;
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	/**
	 * 跳转角色管理页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转角色管理页面");
		return "system/role/role";
	}
	/**
	 * 加载角色数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="listRole")
	@ResponseBody
	public List<RoleDO> listRole(HttpServletRequest request,HttpServletResponse response){
		try {
			List<RoleDO> roles = roleService.list();
			return roles;
		} catch (Exception e) {
			logger.error("listRole查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 跳转角色添加页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/addRole")
	public String addRole(HttpServletRequest request,HttpServletResponse response){
		logger.info("addRole跳转角色添加页面");
		return "system/role/addRole";
	}
	/**
	 * 保存角色数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="saveRole")
	@ResponseBody
	public WholeResponse saveRole(RoleDO role,HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if(roleService.save(role)>0){
				return WholeResponse.successResponse("保存数据成功");
			}else{
				return WholeResponse.errorResponse("1", "保存失败");
			}
		} catch (Exception e) {
			logger.error("saveRole查询出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 角色编辑页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/editRole/{id}",method=RequestMethod.GET)
	public String editRole(@PathVariable("id") Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("editRole角色编辑 id参数为:"+id);
		try {
			RoleDO roleDO = roleService.get(id);
			model.addAttribute("role", roleDO);
		} catch (Exception e) {
			logger.error("editRole出错"+e.getMessage());
			model.addAttribute("msg", "系统错误，请联系管理员");
		}
		return "system/role/editRole";
	}
	/**
	 * 角色更新
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/updateRole")
	@ResponseBody
	public WholeResponse updateRole(RoleDO roleDO,HttpServletRequest request){
		logger.info("updateRole角色更新");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许修改数据");
			}
			if(roleService.update(roleDO)>0){
				return WholeResponse.successResponse("更新数据成功");
			}else{
				return WholeResponse.errorResponse("1", "更新失败");
			}
		} catch (Exception e) {
			logger.error("updateRole更新出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 角色删除
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/removeRole")
	@ResponseBody
	public WholeResponse removeRole(Long id,HttpServletRequest request){
		logger.info("removeRole角色删除");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许删除数据");
			}
			if(roleService.remove(id)>0){
				return WholeResponse.successResponse("删除数据成功");
			}else{
				return WholeResponse.errorResponse("1", "删除失败");
			}
		} catch (Exception e) {
			logger.error("saveRole查询出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 角色批量删除
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/batchRemoveRole")
	@ResponseBody
	public WholeResponse batchRemoveRole(@RequestParam("ids[]") Long[] ids,HttpServletRequest request){
		logger.info("batchRemoveRole角色批量删除");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许批量删除数据");
			}
			if(roleService.batchremove(ids)>0){
				return WholeResponse.successResponse("批量删除数据成功");
			}else{
				return WholeResponse.errorResponse("1", "批量删除失败");
			}
		} catch (Exception e) {
			logger.error("saveRole查询出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
