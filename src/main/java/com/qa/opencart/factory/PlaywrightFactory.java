package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;
	Properties prop;
	
	private static ThreadLocal<Browser> tlBrowser=new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext=new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage=new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright=new ThreadLocal<>();
	
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	public static Page getPage() {
		return tlPage.get();
	}
	
	
	public Page initBrowser(Properties prop) {

		//playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		String browserName=prop.getProperty("browser").trim();
		switch (browserName) {
		case "chromium":
			//browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			//browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			//browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			//browser=playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		case "edge":
			//browser=playwright.chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;
		default:
			System.out.println("Please pass the right browser name.............");
			break;
		}
		//context=browser.newContext();
		tlBrowserContext.set(getBrowser().newContext());
		//page=context.newPage();
		tlPage.set(getBrowserContext().newPage());
		//page.navigate(prop.getProperty("url").trim());
		getPage().navigate(prop.getProperty("url").trim());
		
		return getPage();

	}
	
	public Properties init_prop() {
		
		try {
			FileInputStream file=new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			prop=new Properties();
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String takeScreenshot() {
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
	}

}
