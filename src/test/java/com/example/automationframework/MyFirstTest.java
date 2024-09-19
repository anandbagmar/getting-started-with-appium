package com.example.automationframework;

import com.example.Wait;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MyFirstTest {
    private final By byLoginLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"LOG IN\")");
    private final By byDoubleTapLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Double Tap\")");
    private final By byDoubleTapMeLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Double Tap Me\")");
    private final By byGoBackLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Back\")");

    private WebDriver driver;
    private static String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub/";
    private static AppiumDriverLocalService localAppiumServer;
    private static boolean IS_FULL_RESET = true;
    private static String APK_NAME = "src/test/resources/VodQA.apk";
    private static String LOG_DIR = "build";

    @BeforeSuite
    public void beforeSuite() throws MalformedURLException {
        startAppiumServer();
    }

    @AfterSuite
    public void afterSuite() {
        if (null != localAppiumServer) {
            localAppiumServer.stop();
            System.out.printf("Is Appium server running? %s%n", localAppiumServer.isRunning());
        }
    }

    @BeforeMethod
    public void setUp(Method method) throws MalformedURLException {
        System.out.println("BeforeMethod: Test - " + method.getName());
        System.out.printf("Create AppiumDriver for android test - %s%n", APPIUM_SERVER_URL);
        // Appium 2.x
        //        DesiredCapabilities uiAutomator2Options = new DesiredCapabilities();
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setCapability("platformName", "Android");

        uiAutomator2Options.setCapability(UiAutomator2Options.AUTOMATION_NAME_OPTION, "UiAutomator2");
        uiAutomator2Options.setCapability(UiAutomator2Options.DEVICE_NAME_OPTION, "Android");
        uiAutomator2Options.setCapability(UiAutomator2Options.PRINT_PAGE_SOURCE_ON_FIND_FAILURE_OPTION, true);
        uiAutomator2Options.setCapability(UiAutomator2Options.AUTO_GRANT_PERMISSIONS_OPTION, true);
        uiAutomator2Options.setCapability(UiAutomator2Options.FULL_RESET_OPTION, IS_FULL_RESET);
        uiAutomator2Options.setCapability(UiAutomator2Options.APP_OPTION, new File(APK_NAME).getAbsolutePath());
        System.out.println("UiAutomator2Options:");
        for (String capabilityName : uiAutomator2Options.getCapabilityNames()) {
            System.out.println("\t" + capabilityName + ": " + uiAutomator2Options.getCapability(capabilityName));
        }

        try {
            driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), uiAutomator2Options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1L));
        } catch (MalformedURLException e) {
            System.err.println("Error creating Appium driver for android device with capabilities: " + uiAutomator2Options);
            throw new RuntimeException(e);
        }
        System.out.printf("Created AppiumDriver for - %s%n", APPIUM_SERVER_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void myTest() {
        //      Recorded snippet by Appium Inspector
        //        var el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"LOG IN\")"));
        //        el1.click();
        //        var packageName = driver.executeScript("mobile: getCurrentPackage");
        //        var caps = driver.getSessionDetails();
        //        var el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Double Tap\")"));
        //        el2.click();
        //        var el3 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Double Tap Me\")"));
        //        el3.click();
        //        var el4 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Back\")"));
        //        el4.click();
        Wait.waitTillElementIsPresent(driver, byLoginLocator, 3).click();
        driver.findElement(byDoubleTapLocator).click();
        driver.findElement(byDoubleTapMeLocator).click();
        driver.findElement(byGoBackLocator).click();
    }

    private static void startAppiumServer() {
        System.out.println("Start local Appium server");
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        // Use any port, in case the default 4723 is already taken (maybe by another Appium server)
        serviceBuilder.usingAnyFreePort();
        serviceBuilder.withAppiumJS(new File("./node_modules/appium/build/lib/main.js"));
        serviceBuilder.withLogFile(new File(LOG_DIR + "/appium_logs.txt"));
        serviceBuilder.withArgument(GeneralServerFlag.ALLOW_INSECURE, "adb_shell");
        serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);

        localAppiumServer = AppiumDriverLocalService.buildService(serviceBuilder);
        localAppiumServer.start();
        APPIUM_SERVER_URL = localAppiumServer.getUrl().toString();
        System.out.printf("Appium server started on url: '%s'%n", localAppiumServer.getUrl().toString());
    }

}
