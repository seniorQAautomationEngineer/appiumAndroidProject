package com.appium.tests;

import com.appium.pages.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseUI {
    protected AndroidDriver<WebElement> driver;
    protected WebDriverWait wait;
    protected WiFiSettings wiFiSettings;
    protected MainMenu mainMenu;
    protected Views views;
    protected ExpandableLists expandableLists;
    protected CustomerAdapter customerAdapter;
    protected DateWidgets dateWidgets;
    protected RadioGroup radioGroup;
    protected PeopleNames peopleNames;
    protected Preferences preferences;


    protected TestBox testBox;

    protected enum TestBox {
        LOCAL, SAUCE
    }


    @BeforeMethod(groups = {"android"}, alwaysRun = true)
    @Parameters({"testBox", "deviceName"})
    public void setUp(@Optional("local") String box, @Optional("Nexus6") String device, Method method) throws MalformedURLException {
        Reports.start(method.getDeclaringClass().getName() + " : " + method.getName());
        File path = new File("ApiDemos-debug.apk");

        if (box.equalsIgnoreCase("local")) {
            testBox = TestBox.LOCAL;
        } else if (box.equalsIgnoreCase("sauce")) {
            testBox = TestBox.SAUCE;
        }

        switch (testBox) {
            case LOCAL:

                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                caps.setCapability(MobileCapabilityType.APP, path.getAbsolutePath());
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                break;

            case SAUCE:
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("username", "alex19851985");
                capabilities.setCapability("accessKey", "ea2fc046-d156-439b-aebe-f58287d450d9");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", device);
                capabilities.setCapability("platformVersion","8.1");
               // capabilities.setCapability("app", "storage:filename=ApiDemos-debug.apk");
                //capabilities.setCapability("app", "storage:9d790ed1-01c6-4fe0-8119-9402f43e0e89");
               // capabilities.setCapability("app", "sauce-storage:"+path.getAbsolutePath());
                capabilities.setCapability("app", "https://github.com/appium/appium/blob/master/sample-code/apps/ApiDemos-debug.apk");
                capabilities.setCapability("browserName", "");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("appiumVersion", "1.17.1");
                driver = new AndroidDriver<WebElement>(new URL("http://" + System.getenv("SAUCE_USERNAME") + ":" + System.getenv("SAUCE_ACCESS_KEY") + "@ondemand.saucelabs.com:80/wd/hub"),
                        capabilities);
                break;
        }
                wait = new WebDriverWait(driver, 20);
                mainMenu = new MainMenu(driver, wait);
                views = new Views(driver, wait);
                wiFiSettings = new WiFiSettings(driver, wait);
                expandableLists = new ExpandableLists(driver, wait);
                dateWidgets = new DateWidgets(driver, wait);
                radioGroup = new RadioGroup(driver, wait);
                peopleNames = new PeopleNames(driver, wait);
                preferences = new Preferences(driver, wait);
                customerAdapter = new CustomerAdapter(driver, wait);

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }


        @AfterMethod
        public void afterActions (ITestResult testResult){
            if (testResult.getStatus() == ITestResult.FAILURE) {
                Reports.fail(driver, testResult.getName());
            }
            Reports.stop();
            driver.quit();
        }


    }



