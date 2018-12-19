package org.gariem.utils.spock.reports.listener.reportmanager;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

/**
 * Created by gariem on 9.4.2018.
 */
public class ExtendManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir") + File.separator + "appium-";
            extent = new ExtentReports(workingDir + "report.html", true);
        }
        return extent;
    }
}
