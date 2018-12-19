package com.cocoin.pom.pages;

import com.cocoin.pom.pages.base.BasePage;
import com.cocoin.pom.util.QLConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends BasePage {
    //constructor
    public LandingPage(WebDriver driver,ExtentTest test) {
        super(driver,test);
    }

    public void verifyHomePage() {
        if(!waitForVisibilityOf("MENU_CLASS"))
        reportFail("Fail to find element with locator key-->"+prop.getProperty("MENU_CLASS"));
        else
        reportPass("Element located with key-->"+prop.getProperty("MENU_CLASS"));
    }
    public void addTask() throws InterruptedException {
        WebElement addTask = getElement("ADD_TASK_HOME_ID");
        addTask.click();
        if(!waitForVisibilityOf("TASK_NAME_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("TASK_NAME_ID"));
        }
        else {
            reportPass("Element located with key-->" + prop.getProperty("TASK_NAME_ID"));
            test.log(LogStatus.PASS, "User can enter task");
        }
            WebElement taskNemeEdit = getElement("TASK_NAME_ID");
            taskNemeEdit.sendKeys("Sample Task");
            WebElement createTaskSubmit = getElement("ANDROID_SUBMIT_BUTTON_ID");
            createTaskSubmit.click();
            Thread.sleep(100);
            createTaskSubmit.click();
        //verify that task created successfully and returned to home page
        if(!waitForVisibilityOf("ADD_TASK_HOME_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("ADD_TASK_HOME_ID"));
        }
        else {
            reportPass("Element located with key-->" + prop.getProperty("ADD_TASK_HOME_ID"));
            test.log(LogStatus.PASS, "Task created successfully");
        }
    }
    public void selectTask(String taskName) throws InterruptedException {
        String taskNameXPATH = "//*[@text='" + taskName + "']";
        WebElement task = getDynamicElement("xpath", taskNameXPATH);
        task.click();
        if (!waitForVisibilityOf("COMPLETE_TASK_BUTTON_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("COMPLETE_TASK_BUTTON_ID"));
        } else {
            reportPass("Element located with key-->" + prop.getProperty("COMPLETE_TASK_BUTTON_ID"));
            test.log(LogStatus.PASS, "Complete task button is visible");
        }
    }
    public void taskOperations(String operation) throws InterruptedException {

        if ("Complete".equals(operation)) {
            WebElement completeTask = getElement("COMPLETE_TASK_BUTTON_ID");
            completeTask.click();
        } else if ("Schedule".equals(operation)) {
            WebElement scheduleTask = getElement("SCHEDULE_TASK_BUTTON_ID");
            scheduleTask.click();
        } else {
            WebElement completeTaskDefault = getElement("COMPLETE_TASK_BUTTON_ID");
            completeTaskDefault.click();
        }
    }
    public SideMenuPage gotoSideMenuPage() {
        WebElement menu = getElement("MENU_CLASS");
        menu.click();
        if(!waitForVisibilityOf("INBOX_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("INBOX_ID"));
        }
        else {
            reportPass("Element located with key-->" + prop.getProperty("INBOX_ID"));
            test.log(LogStatus.PASS, "Side menu open successfully");
        }
        //on side menu page
        SideMenuPage sideMenuPage = new SideMenuPage(driver,test);
        return sideMenuPage;
    }
}
