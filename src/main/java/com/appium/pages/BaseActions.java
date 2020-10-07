package com.appium.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class BaseActions {

    protected AndroidDriver<WebElement> driver;
    protected WebDriverWait wait;

    public BaseActions(AndroidDriver<WebElement> driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement setAnyTextViewElement(String text){
        Reports.log("Click text: " + text);
        return driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']");
    }



    public List<WebElement> collectAndroidButtons(){
        return driver.findElementsByXPath("//android.widget.RadioButton");
    }

    public void clickAnyTextView(String text){
        Reports.log("Click text: " + text);
        driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']").click();
    }


    public void tapAnyTextView(String text){
        Reports.log("Click text: " + text);
        tap(driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']"));
    }



    public WebElement setAnyRadioButton(String text){
        Reports.log("Click text: " + text);
        return driver.findElementByXPath("//android.widget.RadioButton[@text='" + text + "']");
    }


    public WebElement setRadioButton(){
        return driver.findElementByXPath("//android.widget.RadioButton");
    }

    public void tapAnyRadioButton(String text){
        Reports.log("Click text: " + text);
        tap(setAnyRadioButton(text));
    }


    public void longPressAnyTextView(String text){
        Reports.log("Click text: " + text);
        longPress(driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']"));
    }




    public void javaWaitSec(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tapAnyButton(String text){
        Reports.log("Click button: " + text);
        tap(driver.findElementByXPath("//android.widget.Button[@text='" + text + "']"));
    }


    public void tapAnyTextByAndroidUIAutomator(String text){
        Reports.log("Click text: " + text);
        tap(driver.findElementByAndroidUIAutomator("text(\"" + text + "\")"));
      //  tap(driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']"));
    }


    public void tap(WebElement webElement) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(tapOptions().withElement(element(webElement))).perform();
    }

    public void longPress(WebElement webElement) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(longPressOptions().withElement(element(webElement)).withDuration(ofSeconds(2))).release().perform();
    }


    public void swipe(WebElement firstElement, WebElement secondElement) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(longPressOptions()
                        .withElement(element(firstElement))
                        .withDuration(ofSeconds(3)))
                .moveTo(element(secondElement))
                .release()
                .perform();
    }
    public void dragAndDrop(WebElement firstElement, WebElement secondElement) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(element(firstElement))
                .moveTo(element(secondElement))
                .release()
                .perform();
    }

    public void scrollTo(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
    }


    public void comparValuesInUiAndInFile(String nameOfFile) {
        String[] expectedValues = null;
        try {
            expectedValues = Files.readAllLines(Paths.get("DataFiles", nameOfFile+".csv")).stream().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("EXPECTED LIST");
        for (int j = 1; j < expectedValues.length; j++) {
            System.out.println(j + 1 + " " + setAnyTextViewElement(expectedValues[j]).getText());


        }
    }

}
