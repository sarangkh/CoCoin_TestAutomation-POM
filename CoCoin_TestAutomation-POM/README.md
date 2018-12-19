AppiumDemo
==========
## I have built POM Automation Framework to start doing functional automation of any mobile application. It's the most advanced framework which consist of advance reporting and supports parallel execution on multiple real android devices.  

## Functional Testing of CoCoin Expense Management app using Appium

## Objective

Intent of this project is to illustrate how **Appium** can be used to test mobile application **Android** and parallel execution of the tests with multiple android devices connected.

## Android App Tests
This would precisely explain how to get started with Android App testing, write the first test and then the test suite management.

This java project has been created using **Eclipse IDE Version: 2018-09 (4.9.0)**. **POM(pom.xml)** File manages the dependency of **Selenium and all the other required supporting libraries**. Project is using **TestNG annotation**. We have also bundled the respective mobile applications under the apps folder for ease.

## Please note:- Report for tests execution on Google pixel are available at E:\CoCoin_TestAutomation-POM\Reports\Mon_Dec_17_11_08_10_IST_2018.html

## Steps:

- **Appium Set up** - Before running the project you need to download and install Appium. Once this this done, do the android SDK set up as mentioned below.

- **Android SDK Set up** - Download Android SDK or download Android Studio which will setup android SDK, set up the android environment variables appropriately. 

- **Android device Set up** - This is the most tedious process which vary as per hardware and devices availability.
In case of any challenges in setting up test environment please contact me.For this demo I have used my Google Pixel 2 XL. Make sure that you have installed google driver using android studio(to open sdk manager in android studio create dummy project and double click on shift button). Connect the android device with laptop and select file transfer for USB and in developer options enable USB debugging. It will install required ADB drivers which needed to establish connection between pc and mobile device.
Run adb command and it should connect to mobile device by displaying device id on console.

- **Download the project repository**
- **In exclipse or intellij IDE import as an existing maven project**
- **Rebuild**
- **Modify the test environment in QLConstants.java file availble at directory - 	src/main/CoCoin_Automation_POM/src/main/java/com/cocoin/pom/util/QLConstants.java **
- **Modifying device name,ID and android version would be enough for execution **
- **Start the appium server and keep it running **
- **For first time instal the app manually and set the pin as "1234" **
- **Using this framework apk file will be installed automatically using adb but welcome UI slides are not handeled 	yet or you can let the first test install the app that test will fail, then from second itteration onwards it will   	work fine**
- **Run the test suit by right clicking on testng.xml as "Run as Suit"  **

##Document to understand Automation Framework is provided with source code ##

## For any help please contact me on sarangkhedkar@gmail.com or Mob:-+919096021387*
