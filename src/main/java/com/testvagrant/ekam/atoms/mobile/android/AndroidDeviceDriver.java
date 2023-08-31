package com.testvagrant.ekam.atoms.mobile.android;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.mobile.DeviceDriver;
import org.openqa.selenium.WebDriver;

public class AndroidDeviceDriver extends DeviceDriver {

    protected WebDriver driver;

    @Inject
    public AndroidDeviceDriver(WebDriver androidDriver) {
        super(androidDriver);
    }

//  public void pressKey(AndroidKey key) {
//    driver.pressKey(new KeyEvent(key));
//  }
}
