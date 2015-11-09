package com.bike.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Config
{
	// 网站根目录 URL
	@Value("${config.webUrl}")
	private String webUrl;

	// 网站根目录物理路径
	private String webPath;

	// 文件上传路径
	@Value("${config.uploadFilePath}")
	private String uploadFilePath;

	// 管理员邮箱
	@Value("${config.adminEmail}")
	private String adminEmail;

	@Value("${config.superAccount}")
	private String superAccount;

	/** 邮件信息 **/

	// SMTP服务器地址
	@Value("${config.smtpServer}")
	private String smtpServer;

	// SMTP用户名
	@Value("${config.smtpUser}")
	private String smtpUser;

	// SMTP密码
	@Value("${config.smtpPass}")
	private String smtpPass;
	
	@Value("${config.resUrl}")
	private String resUrl;
	
	//搜索地址
	@Value("${config.baiduImg}")
	private String baiduImg;

	@Value("${config.ACCESSKEY}")
	private String ACCESSKEY;

	//七牛图片
	@Value("${config.SECRETKEY}")
	private String SECRETKEY;

	@Value("${config.qnUrl}")
	private String qnUrl;

	@Value("${config.bucket}")
	private String bucket;

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getQnUrl() {
		return qnUrl;
	}

	public void setQnUrl(String qnUrl) {
		this.qnUrl = qnUrl;
	}

	public String getSECRETKEY() {
		return SECRETKEY;
	}

	public void setSECRETKEY(String SECRETKEY) {
		this.SECRETKEY = SECRETKEY;
	}

	public String getACCESSKEY() {
		return ACCESSKEY;
	}

	public void setACCESSKEY(String ACCESSKEY) {
		this.ACCESSKEY = ACCESSKEY;
	}

	public String getWebUrl()
	{
		return webUrl;
	}

	public void setWebUrl(String webUrl)
	{
		this.webUrl = webUrl;
	}

	public String getWebPath()
	{
		return webPath;
	}

	public void setWebPath(String webPath)
	{
		this.webPath = webPath;
	}

	public String getUploadFilePath()
	{
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath)
	{
		this.uploadFilePath = uploadFilePath;
	}

	public String getAdminEmail()
	{
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail)
	{
		this.adminEmail = adminEmail;
	}

	public String getSuperAccount()
	{
		return superAccount;
	}

	public void setSuperAccount(String superAccount)
	{
		this.superAccount = superAccount;
	}

	public String getSmtpServer()
	{
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer)
	{
		this.smtpServer = smtpServer;
	}

	public String getSmtpUser()
	{
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser)
	{
		this.smtpUser = smtpUser;
	}

	public String getSmtpPass()
	{
		return smtpPass;
	}

	public void setSmtpPass(String smtpPass)
	{
		this.smtpPass = smtpPass;
	}

	/**
	 * @return the resUrl
	 */
	public String getResUrl()
	{
		return resUrl;
	}

	/**
	 * @param resUrl the resUrl to set
	 */
	public void setResUrl(String resUrl)
	{
		this.resUrl = resUrl;
	}

	public String getBaiduImg() {
		return baiduImg;
	}

	public void setBaiduImg(String baiduImg) {
		this.baiduImg = baiduImg;
	}
}
