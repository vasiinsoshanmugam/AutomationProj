package com.ecom.amazon.login;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ecom.amazon.pageobjects.Homepage_PO;
import com.ecom.testBase.Initializer;
import com.ecom.testUtils.UtilsClass;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ConfigurationListener.class, MethodListener.class, ATUReportsListener.class })
public class HomepageTest extends Initializer {
	public static Homepage_PO hp = null;

	@BeforeClass
	public void preset() throws IOException {
		start();
		hp=PageFactory.initElements(wd, Homepage_PO.class);
		WebElement e1 = getElements("lnktxt_signin_btn");
		e1.click();
		WebElement e2 = getElements("css_email_txtbox");
		e2.sendKeys("vasiinso@gmail.com");
		WebElement e3 = getElements("css_pswrd_txtbox");
		e3.sendKeys("Admin@123");
		WebElement e4 = getElements("id_signin_btn");
		e4.click();

	}

	@Test(dataProvider = "getData")
	public void TestHomepage(String tcid, String mod, String Des, String precon, String ts, String td, String postcon,
			String exp, String runmod) throws IOException {
	
		lg.info("Start Test");
		if(hp!=null){
			System.out.println(" Hp not null");
		}
		lg.debug("verifying homepage test");
		if (hp.logo_img.isDisplayed()) {
			UtilsClass.getSnapShot(tcid);
			lg.info("logo is displayed and is passed");
			System.out.println("logo is displayed and is passed");
		} else {
			
			Assert.fail();
		}
		UtilsClass c = new UtilsClass();
		String[] ip = c.spltTestData(td);
		lg.info("Verifying homepage buttons");
		Assert.assertEquals(ip[0].toUpperCase(), hp.ord_histroy_btn.getText());
		Assert.assertEquals(ip[1].toUpperCase(), hp.wishlist_btn.getText());
		lg.info("Test Closed");
	}

	@DataProvider
	public static Object[][] getData() throws IOException {
		return UtilsClass.getExcelData(conf.getProperty("dataforHomepage"));

	}

	@AfterClass
	public void posttest() {
		wd.quit();
	}

}
