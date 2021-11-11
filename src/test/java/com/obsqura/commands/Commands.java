package com.obsqura.commands;

import com.obsqura.utility.ExcelUtility;
import com.sun.deploy.association.Action;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Commands {
    WebDriver driver;

    public void testInitialize(String browser) {
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
       // driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void setup() {
        testInitialize("chrome");
            }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
            File screenShot=takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot,new File("./Screenshots/"+result.getName()+".png"));
        }
        // driver.close();
        // driver.quit();
    }

    @Test(priority = 1, enabled = false)
    public void verifyUserLogin() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys("aleenamariya97@gmail.com");
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("aleena97");
        WebElement submit = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        submit.click();
        WebElement userName = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUserName = userName.getText();
        String expectedUserName = "aleenamariya97@gmail.com";
        Assert.assertEquals(actualUserName, expectedUserName, "log-in failed");
    }

    @Test(priority = 2, enabled = false)
    public void verifyHomePageTitle() {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "invalid home page title");
    }

    @Test(priority = 3, enabled = false)
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFile = driver.findElement(By.id("uploadfile_0"));
        chooseFile.sendKeys("C:\\Selenium\\data\\sample.txt");
        WebElement acceptTerms = driver.findElement(By.id("terms"));
        acceptTerms.click();
        WebElement submitFile = driver.findElement(By.id("submitbutton"));
        submitFile.click();
    }

    @Test(priority = 4, enabled = false)
    public void verifyConformationAlert() {
        driver.get("http://demo.guru99.com/test/delete_customer.php");
        WebElement customerId = driver.findElement(By.name("cusid"));
        customerId.sendKeys("12345");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        // alert.dismiss();
        String alertText = alert.getText();
        System.out.println(alertText);
    }

    @Test(priority = 5, enabled = false)
    public void verifyFramesInHTML() {
        driver.get("https://demoqa.com/frames");
        //  switch to frame using id/name
        //driver.switchTo().frame("frame1");
        //switch to frame using index
        driver.switchTo().frame(1);
        // switch to frame using web element
        //WebElement frameElement = driver.findElement(By.id("frame1"));
        // driver.switchTo().frame(frameElement);
        WebElement frameOneText = driver.findElement(By.id("sampleHeading"));
        String frameText = frameOneText.getText();
        System.out.println(frameText);
        driver.switchTo().parentFrame();
        //driver.switchTo().defaultContent();
    }

    @Test(priority = 6, enabled = false)
    public void verifyTotalNumberOfFrames() {
        driver.get("https://demoqa.com/frames");
        List<WebElement> totalFrames = driver.findElements(By.tagName("iframe"));
        int size = totalFrames.size();
        System.out.println(size);
    }

    @Test(priority = 7, enabled = false)
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement clickMe = driver.findElement(By.id("alertButton"));
        clickMe.click();
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
    }

    @Test(priority = 8, enabled = false)
    public void verifyAlertAfter5sec() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        WebElement clickMe = driver.findElement(By.id("timerAlertButton"));
        clickMe.click();
        Thread.sleep(5000);
        Alert AlertAfter5sec = driver.switchTo().alert();
        AlertAfter5sec.dismiss();
    }

    @Test(priority = 9, enabled = false)
    public void verifyToolsQAConfirmationAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement clickMe = driver.findElement(By.id("confirmButton"));
        clickMe.click();
        Alert confirmationAlert = driver.switchTo().alert();
        confirmationAlert.accept();
    }

    @Test(priority = 10, enabled = false)
    public void verifyPromptAlert() {
        driver.get("https://demoqa.com/alerts");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,750)");
        WebElement clickMe = driver.findElement(By.id("promtButton"));
        clickMe.click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("aleena");
        promptAlert.accept();
    }

    @Test(priority = 11, enabled = false)
    public void verifyCheckBox() {
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement checkBox = driver.findElement(By.id("gridCheck"));
        checkBox.click();
        boolean clickButton = checkBox.isSelected();
        Assert.assertTrue(clickButton, "check box  is not selected");

    }

    @Test(priority = 12, enabled = false)
    public void verifyMultipleWindow() {
        driver.get("http://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement clickHere = driver.findElement(By.linkText("Click Here"));
        clickHere.click();
        Set<String> windows = driver.getWindowHandles();
        System.out.println(windows);
        Iterator<String> handles = windows.iterator();
        while (handles.hasNext()) {
            String childWindow = handles.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement email = driver.findElement(By.name("emailid"));
                email.sendKeys("aleenamariya97@gmail.com");
                WebElement submit = driver.findElement(By.name("btnLogin"));
                submit.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);

    }

    @Test(priority = 13, enabled = false)
    public void verifyMultipleCheckBoxDemo() {
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement select = driver.findElement(By.id("button-two"));
        select.click();
        WebElement selectAll = driver.findElement(By.id("is_checked"));
        String actualValue = selectAll.getAttribute("value");
        String expectedValue = "true";
        Assert.assertEquals(actualValue, expectedValue, " all check boxes are not selected");

    }

    @Test(priority = 14, enabled = false)
    public void singleInputFiled() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement message = driver.findElement(By.id("single-input-field"));
        message.sendKeys("this is a sample");
        WebElement showMessage = driver.findElement(By.id("button-one"));
        showMessage.click();
        WebElement yourMessage = driver.findElement(By.id("message-one"));
        String expectedMessage = "this is a sample";
        String actualMessage = yourMessage.getText().substring(15);
        Assert.assertEquals(actualMessage, expectedMessage, "messages are not equal");

    }

    @Test(priority = 15, enabled = false)
    public void twoInputField() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement valueA = driver.findElement(By.id("value-a"));
        valueA.sendKeys("12");
        WebElement valueB = driver.findElement(By.id("value-b"));
        valueB.sendKeys("14");
        WebElement getTotal = driver.findElement(By.id("button-two"));
        getTotal.click();
        WebElement total = driver.findElement(By.id("message-two"));
        int actualSum = Integer.parseInt(total.getText().substring(14));
        int expectedSum = 26;
        Assert.assertEquals(actualSum, expectedSum, "Sum is not equal");

    }

    @Test(priority = 16, enabled = false)
    public void verifyDropDowns() {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        WebElement dropDown = driver.findElement(By.name("country"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("INDIA");
        //select.selectByValue("INDIA");
        //select.selectByIndex(10);
    }

    @Test(priority = 17, enabled = false)
    public void verifyDropDownValues() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        List<String> expectedColourValue = new ArrayList<>();
        expectedColourValue.add("Red");
        expectedColourValue.add("Yellow");
        expectedColourValue.add("Green");
        WebElement colourDropDown = driver.findElement(By.id("single-input-field"));
        Select select = new Select(colourDropDown);
        List<WebElement> actualColourWebElements = select.getOptions();
        List<String> actualColourValue = new ArrayList<>();
        for (int i = 1; i < actualColourWebElements.size(); i++) {
            actualColourValue.add(actualColourWebElements.get(i).getText());
        }
        Assert.assertEquals(actualColourValue, expectedColourValue, "drop down value mismatch found in colour list");
    }

    @Test(priority = 18, enabled = false)
    public void verifyMultiSelectDropDown() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement multiSelect = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(multiSelect);
        select.selectByVisibleText("Red");
        select.selectByVisibleText("Yellow");
        select.selectByVisibleText("Green");
        //select.deselectAll();
        //select.deselectByIndex(0);
        //select.deselectByValue("Red");
        //select.deselectByVisibleText("Yellow");
        List<String> expectedSelectedOptions = new ArrayList<>();
        expectedSelectedOptions.add("Red");
        expectedSelectedOptions.add("Yellow");
        expectedSelectedOptions.add("Green");
        List<WebElement> SelectedOptions = select.getAllSelectedOptions();
        List<String> actualSelectedOption = new ArrayList<>();
        for (int i = 0; i < SelectedOptions.size(); i++) {
            actualSelectedOption.add(SelectedOptions.get(i).getText());
        }
        Assert.assertEquals(actualSelectedOption, expectedSelectedOptions, "dropdown value mismatch founded in selected colour list");

       /* WebElement firstSelectedOption=select.getFirstSelectedOption();
        String actualFirstSelectedOption=firstSelectedOption.getText();
        String expectedFirstSelectedOption="Red";
        Assert.assertEquals(actualFirstSelectedOption,expectedFirstSelectedOption,"dropdown value mismatch founded in colour list ");*/

    }

    @Test(priority = 19, enabled = false)
    public void VerifyJavascriptExecutor() {
        driver.get("http://demowebshop.tricentis.com/login");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,750)");
        js.executeScript("document.getElementById('newsletter-email').value='test1@gmail.com'");
        js.executeScript("document.getElementById('newsletter-subscribe-button').click()");

    }

    @Test(priority = 20, enabled = false)
    public void verifyStaticTable() {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> tableWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr/td"));
        List<String> tableCellValues = new ArrayList<>();
        for (int i = 0; i < tableWebElement.size(); i++) {
            tableCellValues.add(tableWebElement.get(i).getText());
        }
        System.out.println(tableCellValues);
    }

    @Test(priority = 21, enabled = false)
    public void verifyTableRow() {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<String> expectedValues = new ArrayList<>();
        expectedValues.add("Island Trading");
        expectedValues.add("Helen Bennett");
        expectedValues.add("UK");
        //List<WebElement>  tableWebElement= driver.findElements(By.xpath("//table[@id='customers']//tr/td"));
        List<WebElement> rowWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr"));
        List<String> tableCellValues = new ArrayList<>();
        for (int i = 0; i < rowWebElement.size(); i++) {
            List<WebElement> actualRowWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr[" + i + "]/td"));
            for (int j = 0; j < actualRowWebElement.size(); j++) {
                tableCellValues.add(actualRowWebElement.get(j).getText());
            }
        }
        if (tableCellValues.get(9).equals("Island Trading")) {
            System.out.println(tableCellValues);
            //Assert.assertEquals(tableCellValues, expectedValues, "expected element not available in the table");
        }
    }

    @Test(priority = 22, enabled = false)
    public void verifyRegistration() throws IOException {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        ExcelUtility excel = new ExcelUtility();
        List<String> registerData = excel.getString("/src/main/resources/TestData.xlsx", "RegistrationData");
        WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys(registerData.get(0));
        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys(registerData.get(1));
        WebElement number = driver.findElement(By.name("phone"));
        number.sendKeys(registerData.get(2));
        WebElement email = driver.findElement(By.id("userName"));
        email.sendKeys(registerData.get(3));
        WebElement address = driver.findElement(By.name("address1"));
        address.sendKeys(registerData.get(4));
        WebElement city = driver.findElement(By.name("city"));
        city.sendKeys(registerData.get(5));
        WebElement state = driver.findElement(By.name("state"));
        state.sendKeys(registerData.get(6));
        WebElement pin = driver.findElement(By.name("postalCode"));
        pin.sendKeys(registerData.get(7));
        WebElement country = driver.findElement(By.name("country"));
        Select select = new Select(country);
        select.selectByVisibleText(registerData.get(8));
        WebElement userName = driver.findElement(By.id("email"));
        userName.sendKeys(registerData.get(9));
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys(registerData.get(10));
        WebElement confirmPassword = driver.findElement(By.name("confirmPassword"));
        confirmPassword.sendKeys(registerData.get(11));
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
    }

    @Test(priority = 23, enabled = false)
    public void verifySample() throws InterruptedException {
        driver.get("https://buffalocart.com/demo/billing/public/login");
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("admin");
        WebElement passWord = driver.findElement(By.id("password"));
        passWord.sendKeys("123456");
        WebElement login = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        login.click();
        WebElement endTour = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm']"));
        endTour.click();
        List<String> expectedValue = new ArrayList<>();
        expectedValue.add("Users");
        expectedValue.add("Roles");
        expectedValue.add("Sales Commission Agents");
        WebElement userManagement = driver.findElement(By.xpath("//span[@class='title']"));
        userManagement.click();
        Thread.sleep(500);
        List<WebElement> userManagementValue = driver.findElements(By.xpath("//ul[@class='treeview-menu menu-open']//span"));
        List<String> actualUserManagementValue = new ArrayList<>();
        for (int i = 0; i < userManagementValue.size(); i++) {
            actualUserManagementValue.add(userManagementValue.get(i).getText());
        }
        Assert.assertEquals(actualUserManagementValue, expectedValue, "that option is not present");
    }

    @Test(priority = 24, enabled = false)
    public void verifyDoubleClick() {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickButton).build().perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 25, enabled = false)
    public void rightClick() {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClickButton = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(rightClickButton).build().perform();
    }

    @Test(priority = 26, enabled = false)
    public void verifyMouseOver() {
        driver.get("https://demoqa.com/menu/");
        WebElement mainItem2= driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(mainItem2).build().perform();
        //actions.moveToElement(mainItem2,50,50).build().perform();
        //actions.moveByOffset(100,100).build().perform();
    }
    @Test(priority = 27,enabled = false)
    public void verifyDragAndDrop(){
        driver.get("https://demoqa.com/droppable");
        WebElement dragMe= driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement drop=driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions actions=new Actions(driver);
        actions.dragAndDrop(dragMe,drop).build().perform();
    }
    @Test(priority = 28,enabled = false)
    public void verifyDragAndDropByOffset(){
        driver.get("https://demoqa.com/dragabble");
        WebElement drag= driver.findElement(By.xpath("//div[@class='drag-box ui-draggable ui-draggable-handle']"));
        int xOffset=drag.getLocation().getX();
        int yOffset=drag.getLocation().getY();
        int x=xOffset-50;
        int y=yOffset-50;
        System.out.println(xOffset +"y" +yOffset);
        Actions actions=new Actions(driver);
        actions.dragAndDropBy(drag,x,y).build().perform();
    }
    @Test(priority = 29,enabled = false)
    public void verifyClickAndHold(){
        driver.get("https://demoqa.com/resizable");
        WebElement resizable= driver.findElement(By.xpath("//div[@id='resizable']//span"));
        Actions actions=new Actions(driver);
        actions.clickAndHold(resizable).dragAndDropBy(resizable,300,206).build().perform();
    }
    @Test(priority = 30,enabled = false)
    public void multipleDragAndDrop(){
        driver.get("https://selenium08.blogspot.com/2020/01/click-and-hold.html");
        WebElement sortA=  driver.findElement(By.xpath("//li[text()='A']"));
        WebElement sortC=driver.findElement(By.xpath("//li[text()='C']"));
        Actions actions=new Actions(driver);
        actions.clickAndHold(sortC).dragAndDrop(sortC,sortA).build().perform();

    }
    @Test(priority = 31,enabled = false)
    public void verifyScreenShot() throws IOException {
        driver.get("https://www.google.com");
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File screenShot=takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File("./Screenshots/"+"Test.png"));
    }
    @Test(priority = 32,enabled = false)
    public void verifyScreenshot(){
        driver.get("https://www.google.com");
        String expectedTitle="Google123";
        String actualTitle= driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"invalid title");
    }
    @Test(priority = 33,enabled = false)
    @Deprecated
    public void verifyWeightsInSelenium(){
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement submit= driver.findElement(By.xpath("//input[@name='submit']"));
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(submit));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='submit']")));
    }
    }


