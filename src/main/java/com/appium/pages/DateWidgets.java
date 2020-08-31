package com.appium.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DateWidgets extends BaseActions{

    public DateWidgets(AndroidDriver<WebElement> driver, WebDriverWait wait) {
        super(driver, wait);
    }



    public WebElement setElementInClock(String option){
        Reports.log("Set clock element: " + option);
        return driver.findElementByXPath("//*[@content-desc='" + option + "']");
    }


    public WebElement setCurrentHoursElement(){
        return driver.findElementById("android:id/hours");
    }

    public WebElement setCurrentMinutesElement(){
        return driver.findElementById("android:id/minutes");
    }


    public void swipeClockElements(String option1, String option2){
        swipe(setElementInClock(option1), setElementInClock(option2));
    }
}
