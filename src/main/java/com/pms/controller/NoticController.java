package com.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pms.entity.Administrator;
import com.pms.entity.Employee;
import com.pms.entity.Notice;
import com.pms.entity.ReturnData;
import com.pms.service.NoticService;
import com.pms.util.DateUtil;
import com.pms.util.IDGenerator;

@Controller
public class NoticController {

	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private NoticService noticService;

	@RequestMapping("/noticManager")
	public String noticManager() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("访问主页");
		return "NoticManager";
	}

	@ResponseBody
	@RequestMapping(value = "/queryNotice", method = RequestMethod.POST)
	public Map<String, Object> queryPersionInfo(
			@RequestParam(value = "title", defaultValue = StringUtils.EMPTY) String title,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "startDate", defaultValue = StringUtils.EMPTY) String startDate,
			@RequestParam(value = "endDate", defaultValue = StringUtils.EMPTY) String endDate,
			@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) {

		Map<String, Object> result = noticService.queryNoticInfo(page, rows, title, name, startDate,
				endDate);

		return result;

	}

	/**
	 * 更新公告信息.
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
	public ReturnData updateNotice(@RequestParam("id") String id,
			@RequestParam("title") String title, @RequestParam("content") String content,
			HttpServletRequest request) {

		Notice notice = noticService.selectNoticeById(StringUtils.trimToEmpty(id));
		try {
			notice.setId(StringUtils.trimToEmpty(id));
			notice.setTitle(StringUtils.trimToEmpty(title));
			notice.setContent(StringUtils.trimToEmpty(content));
			notice.setDate(DateUtil.getCurrentDateStr());
			// 设置发布人工号，从session中获取数据
			Object user = request.getSession().getAttribute("user");
			if (user instanceof Administrator) {
				notice.setAuthor(((Administrator) user).getId());
			} else if (user instanceof Employee) {
				notice.setAuthor(((Employee) user).getNo());
			}

			return noticService.updateNotice(notice);

		} catch (Exception e) {

			LOGGER.error("更新发生异常", e);
			return ReturnData.fail("更新发生异常");
		}
	}

	/**
	 * 添加公告信息.
	 * @param id
	 * @param title
	 * @param content
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
	public ReturnData addNotice(@RequestParam("title") String title,
			@RequestParam("content") String content, HttpServletRequest request) {

		Notice notice = new Notice();
		try {
			notice.setId(IDGenerator.getInstance().getID() + "");
			notice.setTitle(StringUtils.trimToEmpty(title));
			notice.setContent(StringUtils.trimToEmpty(content));
			notice.setDate(DateUtil.getCurrentDateStr());
			// 设置发布人工号，从session中获取数据
			Object user = request.getSession().getAttribute("user");
			if (user instanceof Administrator) {
				notice.setAuthor(((Administrator) user).getId());
			} else if (user instanceof Employee) {
				notice.setAuthor(((Employee) user).getNo());
			}

			return noticService.addNotice(notice);

		} catch (Exception e) {

			LOGGER.error("更新发生异常", e);
			return ReturnData.fail("更新发生异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
	public ReturnData deleteNotice(@RequestParam("ids") String ids) {

		return noticService.deleteNotice(ids);

	}

	@ResponseBody
	@RequestMapping(value = "/comboUser", method = RequestMethod.POST)
	public List<Map<String, Object>> comboUser() {
		List<Map<String, Object>> returnData = noticService.comboUser();

		System.out.println(JSON.toJSONString(returnData));

		return returnData;

	}

}
