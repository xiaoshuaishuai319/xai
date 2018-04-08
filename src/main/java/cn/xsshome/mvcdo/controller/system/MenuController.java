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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.common.Constant;
import cn.xsshome.mvcdo.common.Tree;
import cn.xsshome.mvcdo.pojo.system.MenuDO;
import cn.xsshome.mvcdo.service.system.MenuService;
import cn.xsshome.mvcdo.util.WholeResponse;


/**
 * 菜单Controller
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="/menu")
@Scope("prototype")
public class MenuController {
	private static Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	MenuService menuService;
	@RequestMapping(value="/index")
	public String index(){
		logger.info("index跳转菜单管理页面");
		return "system/menu/menu";
	}
	/**
	 * 加载菜单数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/listMenu")
	@ResponseBody
	public List<MenuDO> listMenu(HttpServletRequest request,HttpServletResponse response){
		try {
			List<MenuDO> menus = menuService.list();
			return menus;
		} catch (Exception e) {
			logger.info("listMenus查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 跳转菜单添加页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/addMenu/{pId}")
	public String addMenu(Model model,@PathVariable("pId") Long pId,HttpServletRequest request,HttpServletResponse response){
		logger.info("addMenu跳转菜单添加页面");
		try {
			model.addAttribute("pId",pId);
			if(pId==0){
				model.addAttribute("pName", "根目录");
			}else{
				model.addAttribute("pName", menuService.get(pId).getName());
			}
		} catch (Exception e) {
			logger.error("addMenu跳转菜单添加页面出错了"+e.getMessage());
		}
		return "system/menu/addMenu";
	}
	/**
	 * 保存菜单数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="saveMenu")
	@ResponseBody
	public WholeResponse saveMenu(MenuDO menuDO,HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if(menuService.save(menuDO)>0){
				return WholeResponse.successResponse("保存数据成功");
			}else{
				return WholeResponse.errorResponse("1", "保存失败");
			}
		} catch (Exception e) {
			logger.error("saveMenu查询出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 菜单编辑页面
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/editMenu/{id}",method=RequestMethod.GET)
	public String editMenu(@PathVariable("id") Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("editMenu角色编辑 id参数为:"+id);
		try {
			MenuDO menuDO = menuService.get(id);
			Long pId = menuDO.getParentId();
			model.addAttribute("pId", pId);
			if (pId == 0) {
				model.addAttribute("pName", "根目录");
			} else {
				model.addAttribute("pName", menuService.get(pId).getName());
			}
			model.addAttribute("menu", menuDO);
		} catch (Exception e) {
			logger.error("editMenu出错"+e.getMessage());
			model.addAttribute("msg", "系统错误，请联系管理员");
		}
		return "system/menu/editMenu";
	}
	/**
	 * 菜单更新
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/updateMenu")
	@ResponseBody
	public WholeResponse updateMenu(MenuDO menuDO,HttpServletRequest request){
		logger.info("updateMenu菜单更新");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许修改数据");
			}
			if(menuService.update(menuDO)>0){
				return WholeResponse.successResponse("更新数据成功");
			}else{
				return WholeResponse.errorResponse("1", "更新失败");
			}
		} catch (Exception e) {
			logger.error("updateMenu更新出错"+e.getMessage());
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
	@RequestMapping(value="/removeMenu")
	@ResponseBody
	public WholeResponse removeMenu(Long id,HttpServletRequest request){
		logger.info("removeMenu角色删除");
		try {
			HttpSession session = request.getSession();
			if(Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))){
				return WholeResponse.errorResponse("1", "测试账户不允许删除数据");
			}
			if(menuService.remove(id)>0){
				return WholeResponse.successResponse("删除数据成功");
			}else{
				return WholeResponse.errorResponse("1", "删除失败");
			}
		} catch (Exception e) {
			logger.error("removeMenu查询出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 加载菜单Tree数据
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/treeMenu")
	@ResponseBody
	public Tree<MenuDO> treeMenu(HttpServletRequest request,HttpServletResponse response){
		try {
			Tree<MenuDO> tree = menuService.getTree();
			return tree;
		} catch (Exception e) {
			logger.info("listMenus查询出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 加载菜单Tree数据根据roleid
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 */
	@RequestMapping(value="/treeMenu/{roleId}",method=RequestMethod.GET)
	@ResponseBody
	public Tree<MenuDO> treeMenu(@PathVariable("roleId") Long roleId,HttpServletRequest request,HttpServletResponse response){
		try {
			Tree<MenuDO> tree = menuService.getTree(roleId);
			return tree;
		} catch (Exception e) {
			logger.info("listMenus查询出错"+e.getMessage());
			return null;
		}
	}
}
