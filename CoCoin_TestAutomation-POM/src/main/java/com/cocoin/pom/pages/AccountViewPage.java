package com.cocoin.pom.pages;
import com.cocoin.pom.pages.base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;

public class AccountViewPage extends BasePage {
	    //Constructor
	public AccountViewPage(WebDriver driver, ExtentTest test) {
	    super(driver,test);
	}
	public boolean verifyExpenses(String view, String amount) {
		//Select view tab
    	List<WebElement> list = driver.findElements(By.xpath("//*[@resource-id='com.nightonke.cocoin:id/psts_tab_title']"));
    	for (int i=0; i<list.size();i++){
    		if (view.equals(list.get(i).getAttribute("text"))){
    				list.get(i).click();	
    	    }
    	}
        reportPass(view+"opened successfully");
    	//verify total expenses
		WebElement totalExpenses = getElement("TOTAL_EXPENSE_AMOUNT_XPATH");
		String actualAmount = totalExpenses.getText().trim();
		if (actualAmount.equals(amount)) {
			//test log
		return true;
		}
		else 
		return false;
	}
	
}
