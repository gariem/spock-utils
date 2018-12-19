package org.gariem.utils.spock.reports.listener;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.MDC;
import org.gariem.utils.spock.reports.listener.reportmanager.ExtendManager;
import org.gariem.utils.spock.reports.listener.reportmanager.ReportTestManager;
import org.spockframework.runtime.AbstractRunListener;
import org.spockframework.runtime.model.ErrorInfo;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.IterationInfo;
import org.spockframework.runtime.model.SpecInfo;

public class DefaultTestListener extends AbstractRunListener {

    public void beforeFeature(FeatureInfo feature) {
        //GetIp ip = new GetIp()

        ReportTestManager.startTest(feature.getSpec().getName(), feature.getName());

        MDC.put("testMethodName", feature.getName());
        MDC.put("deviceName", "Ahmet");
        MDC.put("platformVersion", "10.2");
        MDC.put("appVersion", "3.4.1");

        MDC.put("testCaseName", feature.getName());
        //MDC.put("ip", ip.getIP())
    }


    public void afterFeature(FeatureInfo feature) {
        ReportTestManager.endTest();
        ExtendManager.getReporter().flush();
    }

    public void beforeIteration(IterationInfo iteration) {
        ReportTestManager.log(LogStatus.INFO, "dataValues: " + iteration.getName(), iteration.getDataValues().toString());
    }

    public void error(ErrorInfo error) {

        ReportTestManager.log(LogStatus.ERROR, "ERRROR: " + error.getMethod(), error.getException());
    }

    public void specSkipped(SpecInfo spec) {
        ReportTestManager.log(LogStatus.SKIP, "Test Skipped");
    }

    public void featureSkipped(FeatureInfo feature) {
    }

}
