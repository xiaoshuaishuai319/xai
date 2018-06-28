package cn.xsshome.mvcdo.controller.ai;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRDishDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRFuseDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRDetectService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;

/**
 * 
 * @author 小帅丶
 * @date 2018年5月9日
 * <p>Description: 图像识别后台服务</p>
 */
@Controller
@RequestMapping(value="/bdicr")
@Scope("prototype")
public class BDICRController {
	private static Logger logger = LoggerFactory.getLogger(BDFACEController.class);
	@Autowired
	private BDICRDetectService bdicrDetectService;
	/**
	 * 跳转百度菜品识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexDish")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转百度菜品识别管理页面");
		return "ai/baidu/dish";
	}
	/**
	 * 加载百度菜品识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listDish")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDICRDishDO> detectDOs = bdicrDetectService.listDish(query);
		int total = bdicrDetectService.countDish(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeDish")
	@ResponseBody
	public WholeResponse removeDish(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdicrDetectService.removeDish(id) > 0) {
				return WholeResponse.successResponse("博文删除成功");
			}
		} catch (Exception e) {
			logger.error("remove博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemoveDish")
	@ResponseBody
	public WholeResponse remove(@RequestParam("ids[]") Long[] faceId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdicrDetectService.batchRemoveDish(faceId);
			return WholeResponse.successResponse("批量删除博文成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 跳转百度图像识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexFuse")
	public String indexFuse(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转百度图像识别管理页面");
		return "ai/baidu/icrFuse";
	}
	/**
	 * 加载百度图像识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listFuse")
	public PageUtils listFuse(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDICRFuseDO> detectDOs = bdicrDetectService.listFuse(query);
		int total = bdicrDetectService.countFuse(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeFuse")
	@ResponseBody
	public WholeResponse removeFuse(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdicrDetectService.removeFuse(id) > 0) {
				return WholeResponse.successResponse("博文删除成功");
			}
		} catch (Exception e) {
			logger.error("remove博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemoveFuse")
	@ResponseBody
	public WholeResponse removeFuse(@RequestParam("ids[]") Long[] icrIds,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdicrDetectService.batchRemoveFuse(icrIds);
			return WholeResponse.successResponse("批量删除博文成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
