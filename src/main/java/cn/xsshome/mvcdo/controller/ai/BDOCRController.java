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
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRBankCardDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRGeneralDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRIdCardDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDOCRDetectService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;
/**
 * 
 * @author 小帅丶
 * @date 2018年5月18日
 * <p>Description: 文字识别管理模块</p>
 */
@Controller
@RequestMapping(value="/bdocr")
@Scope("prototype")
public class BDOCRController {
	private static Logger logger = LoggerFactory.getLogger(BDOCRController.class);
	@Autowired
	private BDOCRDetectService bdocrDetectService;
	/**
	 * 跳转百度文字识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexOcrGeneral")
	public String indexOcrGeneral(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字识别管理页面");
		return "ai/baidu/ocr";
	}
	/**
	 * 加载百度文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listOcrGeneral")
	public PageUtils listOcrGeneral(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDOCRGeneralDO> detectDOs = bdocrDetectService.listOcrGeneral(query);
		int total = bdocrDetectService.countOcrGeneral(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeOcrGeneral")
	@ResponseBody
	public WholeResponse removeOcrGeneral(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdocrDetectService.removeOcrGeneral(id) > 0) {
				return WholeResponse.successResponse("删除成功");
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
	@PostMapping("/batchRemoveOcrGeneral")
	@ResponseBody
	public WholeResponse batchRemoveOcrGeneral(@RequestParam("ids[]") Long[] ocrId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdocrDetectService.batchRemoveOcrGeneral(ocrId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 跳转百度文字idcard识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexOcrIdCard")
	public String indexOcrIdCard(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字idcard识别管理页面");
		return "ai/baidu/ocridcard";
	}
	/**
	 * 加载百度idcard文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listOcrIdCard")
	public PageUtils listOcrIdCard(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDOCRIdCardDO> detectDOs = bdocrDetectService.listOcrIdCard(query);
		int total = bdocrDetectService.countOcrIdCard(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeOcrIdCard")
	@ResponseBody
	public WholeResponse removeOcrIdCard(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdocrDetectService.removeOcrIdCard(id) > 0) {
				return WholeResponse.successResponse("删除成功");
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
	@PostMapping("/batchRemoveOcrIdCard")
	@ResponseBody
	public WholeResponse batchRemoveOcrIdCard(@RequestParam("ids[]") Long[] ocrId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdocrDetectService.batchRemoveOcrIdCard(ocrId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	
	/**
	 * 跳转百度文字bankcard识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexOcrBankCard")
	public String indexOcrBankCard(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字bankcard识别管理页面");
		return "ai/baidu/ocrbankcard";
	}
	/**
	 * 加载百度bankcard文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listOcrBankCard")
	public PageUtils listOcrBankCard(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDOCRBankCardDO> detectDOs = bdocrDetectService.listOcrBankCard(query);
		int total = bdocrDetectService.countOcrIdCard(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除bankcard
	 */
	@PostMapping("/removeOcrBankCard")
	@ResponseBody
	public WholeResponse removeOcrBankCard(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdocrDetectService.removeOcrBankCard(id) > 0) {
				return WholeResponse.successResponse("删除成功");
			}
		} catch (Exception e) {
			logger.error("remove出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除bankcard
	 */
	@PostMapping("/batchRemoveOcrBankCard")
	@ResponseBody
	public WholeResponse batchRemoveOcrBankCard(@RequestParam("ids[]") Long[] ocrId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdocrDetectService.batchRemoveBankIdCard(ocrId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
