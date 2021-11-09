package com.obsqura.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class BrowserLaunch {
    static WebDriver driver;
    public static void testInitialize( String browser){
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driverfiles\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Selenium\\driverfiles\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\driverfiles\\geckodriver-v0.30.0-win32\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("http://demowebshop.tricentis.com/");
    }
    public static void main(String[] args) {
        BrowserLaunch.testInitialize("chrome");
        String title= driver.getTitle();
        String url= driver.getCurrentUrl();
        String source=driver.getPageSource();
        System.out.println(title);
        System.out.println(url);
       // System.out.println(source);

WebElement loginMenu= driver.findElement(By.className("ico-login"));
String loginText=loginMenu.getText();
System.out.print(loginText +" ");
loginMenu.click();
WebElement userName= driver.findElement(By.id("Email"));
userName.sendKeys("aleena97@gmail.com");
userName.clear();
String classValue= userName.getAttribute("class");
        System.out.print(classValue+ " " );
        String tagName=userName.getTagName();
        System.out.print(tagName+" " );
        WebElement password= driver.findElement(By.id("Password"));
      //  password.sendKeys("aleena");

        WebElement submit= driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
      // submit.click();
        //driver.close();
        //WebElements
      /* WebElement userName= driver.findElement(By.id("Email"));
        userName.sendKeys("aleena@gmail.com");
        userName.click();
        WebElement name= driver.findElement(By.name("Email"));
        WebElement  className= driver.findElement(By.className("Email"));
        WebElement xpath= driver.findElement(By.xpath("//*[@id=\"Email\"]"));
        WebElement cssSelector= driver.findElement(By.cssSelector("#Email"));
        WebElement tagName= driver.findElement(By.tagName("input"));
        WebElement linkText= driver.findElement(By.linkText("Log in"));
        WebElement partialLinkTest= driver.findElement(By.partialLinkText("Log"));*/
    }
}
