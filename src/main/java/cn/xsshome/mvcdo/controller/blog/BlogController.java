package cn.xsshome.mvcdo.controller.blog;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsshome.mvcdo.pojo.blog.ContentDO;
import cn.xsshome.mvcdo.service.blog.ContentService;
import cn.xsshome.mvcdo.util.DateUtils;
import cn.xsshome.mvcdo.util.PageUtils;
import cn.xsshome.mvcdo.util.Query;

@RequestMapping("/blog")
@Controller
public class BlogController {
	@Autowired
    ContentService bContentService;

	@GetMapping()
	String blog() {
		return "blog/index/main";
	}

	@RequestMapping(value ="/open/list")
	@ResponseBody
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
		return "blog/index/post";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categories", categories);
		ContentDO bContentDO = bContentService.list(map).get(0);
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}
}
