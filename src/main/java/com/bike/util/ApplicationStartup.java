package com.bike.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/** Application 初始化 **/
public class ApplicationStartup implements InitializingBean,ServletContextAware
{
	Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	private ServletContext servletContext;

	@Autowired
	private Config config;




//	@Override
	public void afterPropertiesSet() throws Exception
	{



		logger.info("\t开始初始化网站资源...");

		if(servletContext != null)
		{
			// 将 Config 保存到 Application
			//	config.setWebUrl(servletContext.getContextPath()); // 网站根目录 URL
			config.setWebPath(servletContext.getRealPath("/")); // 网站根目录物理路径
			servletContext.setAttribute("config", config);
		}


		logger.info("\t初始化网站资源结束");
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}