package com.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.annotation.OperationLogger;
import com.model.User;
import com.service.IUserService;

/**
 * 
 * @author Dingdong
 * @date 2017年5月22日
 */
@Controller
public class UserController {

	@Resource
	private IUserService userService;

	/**
	 * 查询
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("forwordIndex")
	@OperationLogger(description = "查询")
	public String forwordIndex(Model model) {

		List<User> list = userService.selectAll();
		model.addAttribute("users", JSON.toJSONString(list));
		model.addAttribute("user", list);

		return "index";
	}

	/**
	 * 添加
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("saveUser")
	@OperationLogger(description = "添加")
	public String saveUser(User user) {

		System.out.println(user.toString());
		int no = userService.insertUser(user);

		return no > 0 ? "success" : "error";
	}

	/**
	 * 添加2
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("saveRequest")
	@OperationLogger(description = "添加")
	public String saveRequest(HttpServletRequest request) {

		String name = request.getParameter("name");
		String password = request.getParameter("name");

		int no = userService.insertUser(new User(name, password, 0, 0));
		return 1 > 0 ? "success" : "error";
	}

	/**
	 * 删除
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("delete")
	@OperationLogger(description = "删除")
	public String delete(int paramId) {

		int no = userService.delete(paramId);
		return 1 > 0 ? "success" : "error";
	}

	/**
	 * 根据Id查询
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("findById")
	@OperationLogger(description = "根据Id查询")
	public String findById(Model model, int paramId) {

		User user = userService.findById(paramId);
		model.addAttribute("user", user);
		return "edit";
	}

	/**
	 * 更新
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("update")
	@OperationLogger(description = "更新")
	public String update(User user) {

		int no = userService.update(user);
		return 1 > 0 ? "success" : "error";
	}

}
