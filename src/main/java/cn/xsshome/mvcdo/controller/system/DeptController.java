package cn.xsshome.mvcdo.controller.system;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.common.Constant;
import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.DeptDO;
import cn.xsshome.mvcdo.service.system.DeptService;
import cn.xsshome.mvcdo.util.WholeResponse;



/**
 * 部门管理
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="/dept")
@Scope("prototype")
public class DeptController {
	private static Logger logger = LoggerFactory.getLogger(DeptController.class);
	@Autowired
	private DeptService sysDeptService;
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转部门管理页面");
		return "system/dept/dept";
	}
	/**
	 * 加载部门数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="listDept")
	@ResponseBody
	public List<DeptDO> listDept(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> query = new HashMap<String, Object>();
			List<DeptDO> sysDeptList = sysDeptService.list(query);
			return sysDeptList;
		} catch (Exception e) {
			logger.info("listDept查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 跳转部门添加页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/addDept/{pId}")
	public String addDept(Model model,@PathVariable("pId") Long pId,HttpServletRequest request,HttpServletResponse response){
		logger.info("addDept跳转部门添加页面");
		try {
			model.addAttribute("pId",pId);
			if(pId==0){
				model.addAttribute("pName", Constant.DEPT_ROOT_NAME);
			}else{
				model.addAttribute("pName",sysDeptService.get(pId).getName());
			}
		} catch (Exception e) {
			logger.error("addDept跳转部门添加页面出错了"+e.getMessage());
		}
		return "system/dept/addDept";
	}
	/**
	 * 保存部门数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="saveDept")
	@ResponseBody
	public WholeResponse saveDept(DeptDO deptDO,HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if(sysDeptService.save(deptDO)>0){
				return WholeResponse.successResponse("新加部门成功");
			}else{
				return WholeResponse.errorResponse("1", "新加部门失败");
			}
		} catch (Exception e) {
			logger.error("saveDept保存出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 部门编辑页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/editDept/{id}",method=RequestMethod.GET)
	public String editDept(@PathVariable("id") Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("editDept部门编辑 id参数为:"+id);
		try {
			DeptDO deptDO = sysDeptService.get(id);
			if(Constant.DEPT_ROOT_ID.equals(deptDO.getParentId())) {
				model.addAttribute("parentDeptName",Constant.DEPT_ROOT_NAME);
			}else {
				DeptDO parDept = sysDeptService.get(deptDO.getParentId());
				model.addAttribute("parentDeptName", parDept.getName());
			}
			model.addAttribute("sysDept", deptDO);
		} catch (Exception e) {
			logger.error("editDept出错"+e.getMessage());
			model.addAttribute("msg", "系统错误，请联系管理员");
		}
		return "system/dept/editDept";
	}
	/**
	 * 部门更新
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/updateDept")
	@ResponseBody
	public WholeResponse updateDept(DeptDO deptDO,HttpServletRequest request){
		logger.info("updateDept部门更新");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许修改数据");
			}
			if(sysDeptService.update(deptDO)>0){
				return WholeResponse.successResponse("更新部门成功");
			}else{
				return WholeResponse.errorResponse("1", "更新部门失败");
			}
		} catch (Exception e) {
			logger.error("updateDept部门更新出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 菜单删除
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/removeDept")
	@ResponseBody
	public WholeResponse removeDept(Long deptId,HttpServletRequest request){
		logger.info("removeDept部门删除");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许删除数据");
			}
			if(sysDeptService.remove(deptId)>0){
				return WholeResponse.successResponse("删除部门成功");
			}else{
				return WholeResponse.errorResponse("1", "删除部门失败");
			}
		} catch (Exception e) {
			logger.error("removeDept查询出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 加载部门tree数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/tree")
	@ResponseBody
	public Tree<DeptDO> listTree(){
		try {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree = sysDeptService.getTree();
			return tree;
		} catch (Exception e) {
			logger.info("tree查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 树形展示页面
	 * @return
	 */
	@RequestMapping(value="/treeView")
	String treeView() {
		return  "system/dept/deptTree";
	}
}
