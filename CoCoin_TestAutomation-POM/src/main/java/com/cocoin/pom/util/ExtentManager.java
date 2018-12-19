package com.cocoin.pom.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
       private static ExtentReports extent;

    private ExtentManager(){}

    public static ExtentReports getInstance() {
        if (extent == null) {
            Date d= new Date();
            String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
            extent = new ExtentReports(QLConstants.REPORT_PATH+fileName, true, DisplayOrder.NEWEST_FIRST);

            // optional
            //	extent.config().documentTitle("Automation Report")
            //			.reportName("Regression").reportHeadline("");
            extent.loadConfig(new File(System.getProperty("user.dir")+"src\\test\\resources\\ReportsConfig.xml"));
            // optional
            extent.addSystemInfo("CoCoi Mobile Application", QLConstants.APP_VERSION).addSystemInfo(
                    "Environment", QLConstants.PLATFORM).addSystemInfo("Device Name",QLConstants.DEVICE_NAME)
                    .addSystemInfo("Device ID",QLConstants.DEVICE_ID)
                    .addSystemInfo("Android Version",QLConstants.DEVICE_VERSION);
        }
        return extent;
    }
}
