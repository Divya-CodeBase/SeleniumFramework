package org.comp;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.utils.BaseMethods;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extent= ExtentReporting.getReport();
    ThreadLocal<ExtentTest> st= new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        st.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());

        st.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        st.get().fail(result.getThrowable());
        String screenshotPath = null;
        try {
            screenshotPath = getScreenshot(result.getMethod().getMethodName(), getDriver());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        st.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

}
