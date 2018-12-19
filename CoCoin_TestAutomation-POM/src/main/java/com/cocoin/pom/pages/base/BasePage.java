package com.cocoin.pom.pages.base;
import com.cocoin.pom.pages.SideMenuPage;
import com.cocoin.pom.util.QLConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BasePage {

    protected WebDriver driver;
    public Properties prop;
    public ExtentTest test;
    public SideMenuPage menu;

    //constructor
    public BasePage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        //into the property file
    	if(prop==null){
        prop = new Properties();
        String path=System.getProperty("user.dir")+"//src//test//resources//project.properties";
        try {
            FileInputStream fs = new FileInputStream(path);
            prop.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    //menu
    public SideMenuPage getMenu(){
        return menu;
    }

    protected boolean waitForVisibilityOf(String locatorKey) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            if(locatorKey.endsWith("_XPATH")){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(locatorKey))));
                return true;}
            else if (locatorKey.endsWith("_ID")){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty(locatorKey))));
                return true;}
            else if(locatorKey.endsWith("_CLASS")){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty(locatorKey))));
                return true;}
            else{
                reportFail("Locator not found -->"+locatorKey);
                return false;
            }
        }catch(Exception ex){
            reportFail("Test failed - " +ex.getMessage());
            return false;
        }
    }
    public WebElement getDynamicElement(String locatorKey, String value){
        WebElement e=null;
        try{
            if(locatorKey.equals("xpath"))
                e = driver.findElement(By.xpath(value));
            else if(locatorKey.equals("id"))
                e = driver.findElement(By.id(value));
            else if(locatorKey.equals("className"))
                e = driver.findElement(By.className(value));
            else{
                reportFail("Element not found with locator-->"+locatorKey+"and value -->"+value);
            }
        }catch(Exception ex){
            reportFail("Test failed - " +ex.getMessage());
        }
        return e;
    }
    protected WebElement getElement(String locatorKey){
        WebElement e=null;
        try{
            if(locatorKey.endsWith("_XPATH"))
                e = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
            else if(locatorKey.endsWith("_ID"))
                e = driver.findElement(By.id(prop.getProperty(locatorKey)));
            else if(locatorKey.endsWith("_CLASS"))
                e = driver.findElement(By.className(prop.getProperty(locatorKey)));
            else{
                reportFail("Element not found with locator-->"+locatorKey);
            }
        }catch(Exception ex){
            reportFail("Test failed - " +ex.getMessage());
        }
        return e;
    }
    protected WebElement getElementbyText(String text){
        WebElement e=null;
			try{
				e = driver.findElement(By.xpath("//*[@text='"+text+"']"));
			}	catch(Exception ex){
			    reportFail("Test failed - " +ex.getMessage());
			}
				return e;
    }
    protected WebElement getElementsByResourceId(int index, String resourceId){
    		WebElement e=null;
            try{
            	List<WebElement> list = driver.findElements(By.xpath("//*[@resource-id='"+resourceId+"']"));
            	if(!list.isEmpty()) {
            		e = list.get(index);
            	}
            }catch(Exception ex){
                reportFail("Test failed - " +ex.getMessage());
            }
            return e;
    }
    protected void waitForClickabilityOf(String locatorKey) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            if(locatorKey.endsWith("_XPATH"))
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(locatorKey))));
            else if(locatorKey.endsWith("_ID"))
                wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(locatorKey))));
            else if(locatorKey.endsWith("_CLASS"))
                wait.until(ExpectedConditions.elementToBeClickable(By.className(prop.getProperty(locatorKey))));
            else{
                reportFail("Locator not found -->"+locatorKey);
            }
        }catch(Exception ex){
            reportFail("Test failed - " +ex.getMessage());
        }
    }
    public boolean isElementPresent(String locatorKey){
        int s = 0;
        try{
            if(locatorKey.endsWith("_XPATH"))
                s = driver.findElements(By.xpath(prop.getProperty(locatorKey))).size();
            else if(locatorKey.endsWith("_ID"))
                s = driver.findElements(By.id(prop.getProperty(locatorKey))).size();
            else if(locatorKey.endsWith("_CLASS"))
                s = driver.findElements(By.className(prop.getProperty(locatorKey))).size();
            else{
                reportFail("Element not found with locator-->"+locatorKey);
            }
        }catch(Exception ex){
            reportFail("Test failed - " +ex.getMessage());
        }
        if(s==0)
            return false;
        else
            return true;
    }
    public boolean isElementPresentinList(String locatorKey, String searchText){
        List<WebElement> e=null;
        if(locatorKey.equals("xpath"))
            e = driver.findElements(By.xpath(searchText));
        else if(locatorKey.equals("id"))
            e = driver.findElements(By.id(searchText));
        else if(locatorKey.equals("class"))
            e = driver.findElements(By.className(searchText));
        else{
            reportFail("Locator not found "+locatorKey+"and"+searchText);
        }
        if(e.size()>0)
            return true;
        else
            return false;
    }
    //Reporting functions
    public void reportPass(String passMsg){
        takeScreenshot();
        test.log(LogStatus.PASS, passMsg);

    }
    public void reportFail(String failureMsg){
        takeScreenshot();
        test.log(LogStatus.FAIL, failureMsg);
        Assert.fail(failureMsg);
    }
    //Capturing screenshots
    public void takeScreenshot(){
        // decide the file name
        Date d = new Date();
        String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
        String path= QLConstants.SCREENSHOT_PATH+screenshotFile;
        // take screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //add screenshot to report
        test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
                test.addScreenCapture(path));
    }
    //Other Common Functions
    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }
    public void swipeLeftToRight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.01);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.9);
        swipeObject.put("endY", 0.6);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }
    public void swipeRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }
    public void swipeFirstCarouselFromRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.2);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.2);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }
    public void performTapAction(WebElement elementToTap) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("x", (double) 360); // in pixels from left
        tapObject.put("y", (double) 170); // in pixels from top
        tapObject.put("element", Double.valueOf(((RemoteWebElement) elementToTap).getId()));
        js.executeScript("mobile: tap", tapObject);
    }
}
