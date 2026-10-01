package com.learn.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static synchronized ExtentReports getExtentReports(){
        if (extentReports==null){
            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("target/extent-reports/extent-report.html");
            sparkReporter.config().setDocumentTitle("SauceDemo Automation Report");
            sparkReporter.config().setReportName("SauceDemo Automation Execution Report");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "Selenium + Java + Cucumber");
            extentReports.setSystemInfo("Application", "SauceDemo");
        }
        return extentReports;

    }
}
