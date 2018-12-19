package org.gariem.utils.spock.reports.listener.reportmanager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import org.gariem.utils.spock.reports.SummaryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gariem on 9.4.2018.
 */

public class ReportTestManager {

    static Map extentTestMap = new HashMap();
    static Map customTestMap = new HashMap();

    static ExtentReports extent = ExtendManager.getReporter();

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get(getThreadId()));

        if (customReports()) {
            ((EvidenceManager) customTestMap.get(getThreadId())).flush();
            customTestMap.remove(getThreadId());
        }
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        return startTest(testName, desc, true);
    }

    public static synchronized ExtentTest startTest(String testName, String desc, boolean customReports) {

        ExtentTest test = extent.startTest(testName, desc);

        extentTestMap.put(getThreadId(), test);

        if (customReports) {
            EvidenceManager evidenceManager = new EvidenceManager("Template_" + testName.replaceAll(" ", "_").trim() + ".docx");
            customTestMap.put(getThreadId(), evidenceManager);
        }

        return test;
    }

    public static synchronized void registerDriver(AppiumDriver driver) {
        EvidenceManager test = (EvidenceManager) customTestMap.get(getThreadId());
        test.setDriver(driver);
    }

    private static synchronized int getThreadId() {
        return (int) (long) (Thread.currentThread().getId());
    }

    private static synchronized boolean customReports() {
        return customTestMap.get(getThreadId()) != null;
    }

    public static synchronized void log(LogStatus status, String name, String details) {
        ExtentTest test = (ExtentTest) extentTestMap.get(getThreadId());
        test.log(status, name, details);
    }

    public static synchronized void log(LogStatus status, String name, Throwable throwable) {
        ExtentTest test = (ExtentTest) extentTestMap.get(getThreadId());
        test.log(status, name, throwable);
    }

    public static synchronized void log(LogStatus status, String name) {
        ExtentTest test = (ExtentTest) extentTestMap.get(getThreadId());
        test.log(status, name);
    }

    public static synchronized void addStep(String tag, String text) {
        EvidenceManager test = (EvidenceManager) customTestMap.get(getThreadId());
        String out = test.addStep(tag, text);

        ExtentTest extent = (ExtentTest) extentTestMap.get(getThreadId());
        extent.log(LogStatus.INFO, text, extent.addScreenCapture(out));
    }

    public static synchronized void addText(String tag, String text) {
        EvidenceManager test = (EvidenceManager) customTestMap.get(getThreadId());
        test.addText(tag, text);

        ExtentTest extent = (ExtentTest) extentTestMap.get(getThreadId());
        extent.log(LogStatus.INFO, text, text);

    }

    public static synchronized void addSummary(String tag, List<SummaryObject> list) {

        for (SummaryObject obj : list) {
            EvidenceManager test = (EvidenceManager) customTestMap.get(getThreadId());
            test.addText(tag, obj.getDescripcion());
            test.addText(tag, "Actual: " + obj.getActual() + " | Esperado: " + obj.getEsperado());
            if (obj.getActual().equals(obj.getEsperado()))
                test.addText(tag, "Resultado: Pass");
            else
                test.addText(tag, "Resultado: Fail");
        }

        list.clear();
    }

}
