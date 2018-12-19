package com.cocoin.pom.pages;

import com.cocoin.pom.pages.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

public class AddExpensePage extends BasePage {
    //Constructor
    public AddExpensePage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }
    //Function to validate that home page is loaded successfully
    public void verifyAddExpensePage() {
    	if(!waitForVisibilityOf("COCOINLABLE_XPATH")) {
	    	reportFail("Fail to find element with locator key-->"+prop.getProperty("COCOINLABLE_XPATH"));
	    	test.log(LogStatus.INFO, "Failed to load home page");
    	}
    	else
	    	//reportPass("Element located with key-->"+prop.getProperty("COCOINLABLE_XPATH"));
	        test.log(LogStatus.INFO, "Home page opened successfully");
    }
    //function to add new expense 
    //Parameters - amount and category
   public void addExpense(int amount, String catagory) {
	 //Select Category
	   WebElement expenseCat = getElementbyText(catagory);
       expenseCat.click();
	   //Enter amount
	   String number = String.valueOf(amount);
	   for(int i = 0; i < number.length(); i++) {
	       int j = Character.digit(number.charAt(i), 10);
	       WebElement calcNum = getElementbyText(Integer.toString(j));
	       calcNum.click();
	   }   
       WebElement enterButton = getElementsByResourceId(1,prop.getProperty("RESOURCEID_COCOIN_HOME"));
       enterButton.click();	
       reportPass("Expense-->$"+amount+" added successfully for-->"+catagory);     
   }
   
   //Select account to verify total expenses 
   //parameter - pin
   //returns Account View Page
   public AccountViewPage selectAccount(int pin) {
	   //click on hamburger menu to select account
	   WebElement selectAccount = getElement("HAMBURGER_XPATH");
	   selectAccount.click();
	   try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	   //Enter pin
	   String pinNumber = String.valueOf(pin);
	   for(int i = 0; i < pinNumber.length(); i++) {
	       int j = Character.digit(pinNumber.charAt(i), 10);
	       WebElement pinCalcNum = getElementbyText(Integer.toString(j));
	       pinCalcNum.click();
	   }
       reportPass("Pin entered successfully");
	   try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       reportPass("Account opened successfully");
       //Return Account view page
	   AccountViewPage accountViewPage = new AccountViewPage(driver, test);
	   return accountViewPage;
   }
}