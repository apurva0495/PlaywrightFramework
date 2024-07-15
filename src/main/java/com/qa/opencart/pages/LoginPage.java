package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	private String emailId="//input[@id='input-email']";
	private String password="//input[@id='input-password']";
	private String loginBtn="//input[@type='submit']";
	private String forgotPwdLink="a:text('Forgotten Password')";
	private String logoutLink="//a[@class='list-group-item'][normalize-space()='Logout']";
	
	public LoginPage(Page page) {
		this.page=page;
	}
	
	public String getLoginPageTitle() {
		String title= page.title();
		System.out.println("Page Title: "+title);
		return title;
	}
	
	public boolean isForgotPwdLinkExist() {
		return page.isVisible(forgotPwdLink);
	}
	
	public boolean doLogin(String appUserName, String appPassword) {
		System.out.println("App Creds: "+appUserName+ " : "+appPassword);
		page.fill(emailId, appUserName);
		page.fill(password, appPassword);
		page.click(loginBtn);
		if(page.isVisible(logoutLink)) {
			System.out.println("User is logged in successfully");
			return true;
		}
		return false;
	}
	

}
