package com.bike.controller.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author ld
 *
 */
public interface RestAction
{
	@ModelAttribute
	void initModel(Model model, HttpServletRequest request, HttpSession session);


}
