package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest{

	
		
	@Test
	public void homePageTitleTest() {
		String title=homePage.getHomePageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void homePageURLTest() {
		String url=homePage.getHomePageURL();
		Assert.assertEquals(url, prop.getProperty("url"));
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
			
		};
	}
	
	@Test(dataProvider="getProductData")
	public void searchTest(String productName) {
		String actualSearchHeader=homePage.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}
	
	
}
