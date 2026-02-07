package org.comp;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporting {

    public static ExtentReports getReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/src/reports/report.html");
        reporter.config().setReportName("Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Optional system info
        extent.setSystemInfo("Texster", "Vivek");
        extent.setSystemInfo("Framework", "Selenium + TestNG");

        return extent;

    }


}
