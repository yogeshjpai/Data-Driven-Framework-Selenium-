package ddtFramework;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

public class DDFEbayProdSearch {
  @Test(dataProvider = "ebayData")
  public void f(String cat, String prod) 
  {
	  WebDriverManager.firefoxdriver().browserVersion("124").setup();
	  WebDriver driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  
	  driver.get("https://www.ebay.com/");
	  
	  driver.findElement(By.id("gh-ac")).sendKeys(prod);;
	  driver.findElement(By.id("gh-btn")).click();
  }

  @DataProvider
  public Object[][] ebayData() throws Exception 
  {
	  Object[][] data = null;
	  XSSFWorkbook wb = new XSSFWorkbook("C:\\Mind Q\\Selenium\\DDT\\ddt.xlsx");
	  XSSFSheet ws= wb.getSheet("Sheet1");
	  int rows = ws.getPhysicalNumberOfRows();
	  
	  data = new Object[rows-1][2];
	  
	  for(int i=1; i<rows; i++)
	  {
		  data[i-1][0]= ws.getRow(i).getCell(0).getStringCellValue();
		  data[i-1][1]= ws.getRow(i).getCell(1).getStringCellValue();
	  }
    return data;
  }
}
