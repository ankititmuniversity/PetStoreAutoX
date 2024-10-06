package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.joda.time.format.DateTimeFormatter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XtentReporting implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports xtent;
    public ExtentTest test;
    public String reportName;
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test_Report -" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
        sparkReporter.config().setDocumentTitle("RestAssuredAutoX");
        sparkReporter.config().setReportName("PetStore User_Module");
        sparkReporter.config().setTheme(Theme.DARK);

        xtent = new ExtentReports();
        xtent.attachReporter(sparkReporter);
        xtent.setSystemInfo("Application", "PetStore");
        xtent.setSystemInfo("Env.", "QA");
        xtent.setSystemInfo("OS", System.getProperty("os.name"));
        xtent.setSystemInfo("user", System.getProperty("user.name"));
    }
        public void onTestSuccess(ITestResult result){
            test = xtent.createTest(result.getName());
           // test.log(Status.PASS,result.getName());
            test.assignCategory(result.getMethod().getGroups());
            test.createNode(result.getName());
            test.log(Status.PASS,"Test Passed");
        }
    public void onTestFailure(ITestResult result){
        test = xtent.createTest(result.getName());
        // test.log(Status.PASS,result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Failed");
    }
    public void onTestSkipped(ITestResult result){
        test = xtent.createTest(result.getName());
        // test.log(Status.PASS,result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP,"Test Skipped");
    }
    public void onFinish(ITestContext testContext){
        xtent.flush();

    }
}
