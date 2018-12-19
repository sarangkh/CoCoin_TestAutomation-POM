package test;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

import com.cocoin.pom.util.ExtentManager;
import com.cocoin.pom.util.QLConstants;
import com.cocoin.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public ExtentReports rep = ExtentManager.getInstance();
    public ExtentTest test;
    public AndroidDriver driver;
    public Xls_Reader xls = new Xls_Reader(QLConstants.XLS_PATH);

    public void prepareAndroidForAppium() throws MalformedURLException {
        Logger.getRootLogger().setLevel(Level.ERROR);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", QLConstants.APK_PATH);
        capabilities.setCapability("appium-version", "1.8");
        capabilities.setCapability("platformName", QLConstants.PLATFORM);
        capabilities.setCapability("platformVersion", QLConstants.DEVICE_VERSION);
        capabilities.setCapability("appPackage", QLConstants.APP_PACKAGE);
        capabilities.setCapability("appActivity", QLConstants.APP_ACTIVITY);
        capabilities.setCapability("udid", QLConstants.DEVICE_ID);
        capabilities.setCapability("deviceName", QLConstants.DEVICE_NAME);
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        driver = new AndroidDriver(new URL(QLConstants.HUB_URL), capabilities);
    }
    @AfterMethod
    public void quit() {
        if (rep != null) {
            rep.endTest(test);
            rep.flush();
        }
        if (driver != null)
            driver.quit();
    }
}