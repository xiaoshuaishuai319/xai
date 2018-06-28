package cn.xsshome.mvcdo.controller.blog;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.pojo.blog.ContentDO;
import cn.xsshome.mvcdo.service.blog.ContentService;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;
import cn.xsshome.mvcdo.util.WholeResponse;

/**
 * 博文
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping("/blogmanager/bContent")
public class ContentController{
	private static Logger logger = LoggerFactory.getLogger(ContentController.class);
	@Autowired
    ContentService bContentService;

	@GetMapping()
	String bContent() {
		return "blog/bContent/bContent";
	}
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	String add() {
		return "blog/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	String edit(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		return "blog/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public WholeResponse save(ContentDO bContent,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bContent.getAllowComment() == null) {
				bContent.setAllowComment(0);
			}
			if (bContent.getAllowFeed() == null) {
				bContent.setAllowFeed(0);
			}
			if(null==bContent.getType()) {
				bContent.setType("article");
			}
			bContent.setGtmCreate(new Date());
			bContent.setGtmModified(new Date());
			int count;
			if (bContent.getCid() == null || "".equals(bContent.getCid())) {
				count = bContentService.save(bContent);
			} else {
				count = bContentService.update(bContent);
			}
			if (count > 0) {
				return WholeResponse.successResponse("cid",bContent.getCid().toString());
			}
			return WholeResponse.errorResponse("500", "系统异常");
		} catch (Exception e) {
			e.printStackTrace();
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public WholeResponse update( ContentDO bContent,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bContent.setGtmCreate(new Date());
			bContentService.update(bContent);
			return WholeResponse.successResponse("修改博文操作成功");
		} catch (Exception e) {
			logger.error("修改博文操作失败"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public WholeResponse remove(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bContentService.remove(id) > 0) {
				return WholeResponse.successResponse("博文删除成功");
			}
		} catch (Exception e) {
			logger.error("remove博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	public WholeResponse remove(@RequestParam("ids[]") Long[] cids,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bContentService.batchRemove(cids);
			return WholeResponse.successResponse("批量删除博文成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
