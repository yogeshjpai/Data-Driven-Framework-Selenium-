package ddtFramework;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

public class DDTEbayCatProdSearch {
  @Test(dataProvider = "EbayData")
  public void EbayCatProd(String cat, String prod)
  {
	  WebDriverManager.firefoxdriver().browserVersion("124").setup();
	  WebDriver driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  
	  driver.get("https://www.ebay.com/");
	  
	  WebElement cate = driver.findElement(By.id("gh-cat"));
	  Select s = new Select(cate);
	  s.selectByVisibleText(cat);
	  
	  driver.findElement(By.id("gh-ac")).sendKeys(prod);
	  driver.findElement(By.id("gh-btn")).click();
  }

  @DataProvider
  public Object[][] EbayData() throws Exception
  {
	  Object[][] data = null;
	  
	  XSSFWorkbook wb = new XSSFWorkbook("C:\\Mind Q\\Selenium\\DDT\\ddt.xlsx");
	  XSSFSheet ws = wb.getSheet("Sheet1");
	  int row = ws.getPhysicalNumberOfRows();
	  
	  data = new Object[row-1][2];
	  
	  for(int i=1; i<row; i++)
	  {
		  data[i-1][0]= ws.getRow(i).getCell(0).getStringCellValue();
		  data[i-1][1]= ws.getRow(i).getCell(1).getStringCellValue();
	  }  
    return data;
  }
}
