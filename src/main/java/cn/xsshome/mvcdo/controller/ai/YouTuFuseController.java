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
import cn.xsshome.mvcdo.pojo.ai.tencent.dbo.YouTuDetectFuseDO;
import cn.xsshome.mvcdo.service.ai.tencent.YouTuFuseService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月05日
 * <p>Description: 优图识别管理模块</p>
 */
@Controller
@RequestMapping(value="/youtu")
@Scope("prototype")
public class YouTuFuseController {
	private static Logger logger = LoggerFactory.getLogger(YouTuFuseController.class);
	@Autowired
	private YouTuFuseService youTuFuseService;
	/**
	 * 跳转优图识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexYouTuFuse")
	public String indexYouTuFuse(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字识别管理页面");
		return "ai/tencent/youtu";
	}
	/**
	 * 加载百度文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listYouTuFuse")
	public PageUtils listYouTuFuse(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<YouTuDetectFuseDO> detectDOs = youTuFuseService.listYouTuFuse(query);
		int total = youTuFuseService.countYouTuFuse(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeYouTuFuse")
	@ResponseBody
	public WholeResponse removeYouTuFuse(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (youTuFuseService.removeYouTuFuse(id) > 0) {
				return WholeResponse.successResponse("删除成功");
			}
		} catch (Exception e) {
			logger.error("remove优图出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemoveYouTuFuse")
	@ResponseBody
	public WholeResponse batchRemoveYouTuFuse(@RequestParam("ids[]") Long[] youtuId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			youTuFuseService.batchRemoveYouTuFuse(youtuId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除优图出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
