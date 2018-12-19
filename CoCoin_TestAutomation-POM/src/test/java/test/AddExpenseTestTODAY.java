package test;

import com.cocoin.pom.pages.AccountViewPage;
import com.cocoin.pom.pages.AddExpensePage;
import com.cocoin.pom.pages.SideMenuPage;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AddExpenseTestTODAY extends BaseTest  {
    String testName="Add Expenses Test for TODAY";
    @BeforeClass
    public void setUp() throws Exception {
        prepareAndroidForAppium();
    }
    @Test
    public void addExpenseTestToday() throws InterruptedException {
        test = rep.startTest(testName);
        test.log(LogStatus.INFO, "Starting the test ");
        //Create object of home(add expense page)
        AddExpensePage addExpensePage = new AddExpensePage(driver, test);
        //Verify that home page opened successfully
        addExpensePage.verifyAddExpensePage();
        //Add new expense with selecting category and amount
        test.log(LogStatus.INFO, "Adding new expense");
        addExpensePage.addExpense(100, "Breakfast");
        addExpensePage.addExpense(200, "Lunch");
        //Select account to verify expenses
        test.log(LogStatus.INFO, "Selecting account...entering pin");
        AccountViewPage accountViewPage = addExpensePage.selectAccount(1234);
        if(!accountViewPage.verifyExpenses("TODAY", "$300"))
            test.log(LogStatus.FAIL, "Failed to verify expenses for TODAY");
        else {
            test.log(LogStatus.PASS, "Total Expenses verified successfully for TODAY");
        }
    }
}




