package com.obsqura.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {
    WebDriver driver;
    public  void testInitialize(String browser) {
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Selenium\\driverfiles\\chromedriver.exe");
             driver= new ChromeDriver();
        }

       else if(browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver","C:\\Selenium\\driverfiles\\msedgedriver.exe");
            driver=new EdgeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\Selenium\\driverfiles\\geckodriver-v0.30.0-win32\\geckodriver.exe");
            driver=new FirefoxDriver();


        }
       driver.manage().window().maximize();
       driver.get("http://demo.guru99.com/test/newtours/");
       driver.close();

    }

        public static void main(String[] args) {
            BrowserLaunch launch=new BrowserLaunch();
           launch.testInitialize("firefox");

    }
}
