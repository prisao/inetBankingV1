package com.inetBanking.TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.PageObjects.LoginPage;
import com.inetBanking.Utilities.XLUtils;

public class TC_LoginTest_01 extends BaseClass
{

	@Test
	public void loginTest() throws InterruptedException, IOException
	
	
	{
		
		
		logger.info("url is opened");
		LoginPage lp = new LoginPage(driver);
		
		lp.setUsername(username);
		logger.info("username is entered ");
		
		lp.setPassword(password);
		logger.info("password is entered");
		
        lp.ClickSubmit();	
        logger.info("clicked submit");
        
        
        
        if(driver.getTitle().equals("Guru99 Bank......."))
        {
        	Assert.assertTrue(true);
        	logger.info("login testcase passed");
        }
        else
        {   captureScreen(driver,"loginTest");
        	Assert.assertFalse(false);
        	logger.info("login test case failed");
        }
        
	}
	
	@Test(dataProvider="LoginData") 
	public void loginDDT(String user , String pwd)
	{ 
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(user);
		lp.setPassword(pwd);
		lp.ClickSubmit();   
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("login failed");
	
		}
		
		else
		{
			Assert.assertTrue(true);
			logger.info("login passed");
			lp.logout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
		}
	}

	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		
		String path=System.getProperty("user.dir")+"src/test/java/com/inetBanking/TestData/LoginData.xlsx";
		
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path,"Sheet1", 1);
		
		String logindata[][]= new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1",i,j);
						}
		
		 
	    }
		return logindata;
	
}
}
