package com.appium.tests;

import com.appium.pages.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WiFiSettingsTests extends  BaseUI{

    @Test
    public void wiFiSettings() {
        mainMenu.clickAnyTextView("Preference");
        preferences.tapAnyTextView("3. Preference dependencies");
        if(!Boolean.parseBoolean(wiFiSettings.setWiFiCheckBox().getAttribute("checked"))){
            wiFiSettings.selectWiFi();
        }
        wiFiSettings.javaWaitSec(5);
        Assert.assertTrue(Boolean.parseBoolean(wiFiSettings.setWiFiCheckBox().getAttribute("checked")));
        wiFiSettings.selectAndTypeWiFiSettings(Data.wiFi);
        wiFiSettings.tapAnyButton("OK");
        wiFiSettings.clickWiFiSettings();
        Assert.assertEquals(Data.wiFi, wiFiSettings.setEditTextWidget().getText());
        wiFiSettings.tapAnyButton("CANCEL");
    }
}
