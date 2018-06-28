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
import cn.xsshome.mvcdo.pojo.system.WeChatUserInfoDO;
import cn.xsshome.mvcdo.service.system.WechatUserInfoService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;
/**
 * 
 * @author 小帅丶
 * @date 2018年6月26日
 * <p>Description: 微信用户管理后台</p>
 */
@Controller
@RequestMapping(value="/wechat")
@Scope("prototype")
public class WeChatController {
	private static Logger logger = LoggerFactory.getLogger(WeChatController.class);
	@Autowired
	private WechatUserInfoService userInfoService;
	/**
	 * 跳转微信用户管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexWeChat")
	public String indexWeChat(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字识别管理页面");
		return "system/wechatuserinfo/wechatuserinfo";
	}
	/**
	 * 加载数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listUserInfo")
	public PageUtils listWeChatUserInfo(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<WeChatUserInfoDO> userInfoDOs = userInfoService.list(query);
		int total = userInfoService.count(query);
		PageUtils pageUtils = new PageUtils(userInfoDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeUserInfo")
	@ResponseBody
	public WholeResponse removeUserInfo(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (userInfoService.remove(id) > 0) {
				return WholeResponse.successResponse("删除成功");
			}
		} catch (Exception e) {
			logger.error("removeUserInfo出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemoveUserInfos")
	@ResponseBody
	public WholeResponse batchRemoveUserInfos(@RequestParam("ids[]") Long[] ids,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			userInfoService.batchRemove(ids);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("batchRemoveUserInfos批量删除出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
