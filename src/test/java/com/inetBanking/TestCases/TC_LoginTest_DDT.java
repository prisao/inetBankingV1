package com.inetBanking.TestCases;

	//import static org.testng.Assert.assertTrue;

	import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.inetBanking.PageObjects.LoginPage;
	import com.inetBanking.Utilities.XLUtils;

	
	public class TC_LoginTest_DDT extends BaseClass
	{
	
		@Test(dataProvider="LoginData") 
		public void loginDDT(String user , String pwd) throws InterruptedException
		
		{ 
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(user);
			logger.info("user name provided");
			
			lp.setPassword(pwd);
			logger.info("password provided");
			
			lp.ClickSubmit();   
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			
			if(isAlertPresent()==true)
			{
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				Assert.assertFalse(false);
				logger.warn("login failed");
		
			}
			
			else
			{
				Assert.assertTrue(true);
				logger.info("login passed");
				lp.logout();
				Thread.sleep(3000);
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
			
			String path=System.getProperty("user.dir")+"/src/test/java/com/inetBanking/TestData/LoginData.xlsx";
			
			
		
			
			int rownum = XLUtils.getRowCount(path, "Sheet1");
			
			
			int colcount = XLUtils.getCellCount(path,"Sheet1", 1);
			
			System.out.println(rownum+" "+colcount);
			
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


