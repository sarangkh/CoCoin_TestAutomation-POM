package com.cocoin.pom.pages;

import com.cocoin.pom.pages.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SideMenuPage extends BasePage {

    //constructor
    public SideMenuPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public AddExpensePage addProject() {
        WebElement addProjectButton = getElement("ADD_PROJECT_BUTTON_ID");
        addProjectButton.click();
        if (!waitForVisibilityOf("ADD_PROJECT_LABEL_XPATH")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("ADD_PROJECT_LABEL_XPATH"));
        } else {
            reportPass("Element located with key-->" + prop.getProperty("ADD_PROJECT_LABEL_XPATH"));
            reportPass("Project creation page opened successfully");
        }
        //on addproject page
        AddExpensePage addProjectPage = new AddExpensePage(driver, test);
        return addProjectPage;
    }

    public boolean searchProject(String projectName) {
        WebElement addProjectButton = getElement("LIST_PROJECT_COLLAPSE_BUTTON_ID");
        addProjectButton.click();
        if (!waitForVisibilityOf("MANAGE_PROJECTS_CLICKABLE_MENU_ITEM_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("MANAGE_PROJECTS_CLICKABLE_MENU_ITEM_ID"));
            return false;
        } else {
            reportPass("Element located with key-->" + prop.getProperty("MANAGE_PROJECTS_CLICKABLE_MENU_ITEM_ID"));
            test.log(LogStatus.PASS, "Side menu open successfully");
        }
        test.log(LogStatus.INFO, "Searching project with project name-->" + projectName);
        String projectNameXPATH = "//*[@text='" + projectName + "']";
        if (!isElementPresentinList("xpath", projectNameXPATH)) {
            reportFail("Fail to find project-->" + projectNameXPATH);
            return false;
        } else {
            reportPass("Project available in list with name-->" + projectNameXPATH);
            return true;
        }
    }
    public void selectProject(String projectName) {
        String projectNameXPATH = "//*[@text='"+projectName+"']";
        WebElement addProjectButton = getElement("LIST_PROJECT_COLLAPSE_BUTTON_ID");
        addProjectButton.click();
        if (!waitForVisibilityOf("MANAGE_PROJECTS_CLICKABLE_MENU_ITEM_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("MANAGE_PROJECTS_CLICKABLE_MENU_ITEM_ID"));
        } else {
            reportPass("Element located with key-->" + prop.getProperty("MANAGE_PROJECTS_CLICKABLE_MENU_ITEM_ID"));
            test.log(LogStatus.PASS, "Side menu open successfully");
        }
        test.log(LogStatus.INFO, "Searching project with project name-->" + projectName);
        WebElement project = getDynamicElement("xpath",projectNameXPATH);
        project.click();
        if (!waitForVisibilityOf("ADD_TASK_HOME_ID")) {
            reportFail("Fail to find element with locator key-->" + prop.getProperty("ADD_TASK_HOME_ID"));
        } else {
            reportPass("Element located with key-->" + prop.getProperty("ADD_TASK_HOME_ID"));
            test.log(LogStatus.PASS, "Project opened successfully");
        }
    }

}
