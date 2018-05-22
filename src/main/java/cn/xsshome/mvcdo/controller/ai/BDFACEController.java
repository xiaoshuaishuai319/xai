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

import cn.xsshome.mvcdo.common.Constant;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDFaceDetectDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDFaceDetectService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;
/**
 * 人脸管理模块
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="/bdface")
@Scope("prototype")
public class BDFACEController {
	private static Logger logger = LoggerFactory.getLogger(BDFACEController.class);
	@Autowired
	private BDFaceDetectService bdFaceDetectService;
	/**
	 * 跳转百度人脸检测管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转百度人脸检测管理页面");
		return "ai/baidu/face";
	}
	/**
	 * 加载百度人脸检测数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDFaceDetectDO> detectDOs = bdFaceDetectService.list(query);
		int total = bdFaceDetectService.count(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public WholeResponse remove(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdFaceDetectService.remove(id) > 0) {
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
	@PostMapping("/batchRemove")
	@ResponseBody
	public WholeResponse remove(@RequestParam("ids[]") Long[] faceId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (Constant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdFaceDetectService.batchRemove(faceId);
			return WholeResponse.successResponse("批量删除博文成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
