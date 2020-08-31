package com.appium.tests;

import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class ViewsTests extends BaseUI {



    @Test
    public void viewsOptionsNames() {
        mainMenu.tapAnyTextView("Views");
        views.tapAnyTextView("Expandable Lists");
        expandableLists.tapAnyTextView("1. Custom Adapter");
        customerAdapter.tapAnyTextView("People Names");
        peopleNames.comparValuesInUiAndInFile("Names");
    }

    @Test
    public void pressCustomerdAdapterMenu() {
        mainMenu.clickAnyTextView("Views");
        views.tapAnyTextView("Expandable Lists");
        expandableLists.tapAnyTextView("1. Custom Adapter");
        customerAdapter.longPressAnyTextView("People Names");
        Assert.assertTrue(peopleNames.setAnyTextViewElement("Sample menu").isDisplayed());
        Assert.assertTrue(peopleNames.setAnyTextViewElement("Sample action").isDisplayed());

    }

    @Test
    public void swipeDateWidgets() {
        mainMenu.clickAnyTextView("Views");
        views.tapAnyTextView("Date Widgets");
        dateWidgets.tapAnyTextByAndroidUIAutomator("2. Inline");
        dateWidgets.swipeClockElements("12", "9");
        dateWidgets.swipeClockElements("15", "0");
        Assert.assertEquals(dateWidgets.setCurrentHoursElement().getText(), "9");
        Assert.assertEquals(dateWidgets.setCurrentMinutesElement().getText(), "00");



    }


    @Test
    public void radioGroups() {
        mainMenu.clickAnyTextView("Views");
        views.scrollTo("Radio Group");
        views.tapAnyTextByAndroidUIAutomator("Radio Group");
        //  tap(driver.findElementByAndroidUIAutomator("text(\"Radio Group\")"));
        radioGroup.tapAnyRadioButton("Snack");
        radioGroup.javaWaitSec(5);
        Assert.assertTrue(Boolean.parseBoolean(radioGroup.setAnyRadioButton("Snack").getAttribute("checked")));
        radioGroup.tapAnyButton("CLEAR");
        List<WebElement> radioButtons = radioGroup.collectAndroidButtons();
        for (int i = 0; i < radioButtons.size(); i++) {
            boolean isSelected = radioButtons.get(i).isSelected();
            String title = radioButtons.get(i).getText();
            System.out.println(isSelected);
            System.out.println(title);
            if (isSelected) {
                Assert.fail(title + " radio-button is selected");
            }
        }

    }


}
