Page Object Model Test automation Framework
============================================
## Objective

Intent of this document is to understand this framework which is designed using JAVA object oriented techniques. Every page is a class and by creating object of page methods can be called while developing tests.

## Framework components:

- **MAVEN** - This project is created as maven project and all the libraries required are maintained in **pom.xml** file as a dependencies. On importing as a Maven project it will donload all the dependencies.

- **Selenium and Appium** - Selenium and and appium is been used for automating UI actions on mobile application.

- **TestNG** - TestNG is used for execution of test cases. Refer to **testng.xml** file to understand test cases. 

- **Extent Reports** - Extend reports are integrated for advance reporting. you can check existing execution reports in Reports folder.

- **Extent Reports** - Extend reports are integrated for advance reporting. you can check existing execution reports in Reports folder. Extent manager is developed for generating these reports in src/....util package.

- **POI** - XLS reader is developed using apache POI libraries to develop reusable data operation methods which can be utilities while doing parameterization of test data in test cases.

**Please note:-** parameterization is not yet implemented in current framework but all the data util methods are developed. It needs some more time to parameterize the test data.

- **Properties File** - Properties file is used to store all the UI locators which is nothing but a object repository of framework.

- **BasePage** - This is the base page of framework in which all the UI operations methods are available which can be called in creating other pages.It has common methods like getElement, reportPass, ReportFail, capture screenshots etc.

- **Pages** - Per page one class can be created by extending BasePage in which all the actions and verification methods are developed which needs to reuse while developing test.

- **BaseTest**  - To setup the connection between mobile device and development machine.

- **FirstTest** - Test has objects created as per pages in test flow and respective UI actions and verification methods are called. Test flows from test-->BaseTest-->device connection then Page-->BasePage(common methods)-->user actions and verifications.

- **Reports** - Advance HTML report is available at the end of test for analysis. Report has cool featured like dashboard, filters and test environment details etc. Existing run reports are available for reference.

- **TestData** - It is a spreadsheet which maintains the test data needed for test cases. This feature is developed in framework but not implemented yet in test cases because of time crunch.

- **apps** Mobile application's apk file is kept here.

## Please refer to executed reports in "Reports" folder for reference ##

## Now as this framework is in place which has all the reusable components then adding test cases is easy for any automation test developer ##

## I have identified many test case which can be automate but it will take more time to add more test cases ##

## Test which I have added is:

- adding new expense by selecting category which is a common function and can be called for any category and amount.
- Selecting account by entering Pin
- Selecting tab for verifying expense as Today, This Week or This month - this is again common function which selects view tab and amount to be validated
- In similar manner many methods can be developed to test all the pages in application which needs time.
- Execution ,reporting and logging is taken care by framework.

## This same framework can be integrated to Cucumber for BDD test automation or at Selenium grid for parallel execution of test cases ##

## Scheduling of execution can be done using any CI tool like Jenkins and reports can be sent to stake holders at the end of the execution ##