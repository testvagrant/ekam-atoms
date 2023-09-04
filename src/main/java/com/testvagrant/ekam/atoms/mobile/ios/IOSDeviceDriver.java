package com.testvagrant.ekam.atoms.mobile.ios;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.mobile.DeviceDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public class IOSDeviceDriver extends DeviceDriver {
  @Inject
  public IOSDeviceDriver(WebDriver driver) {
    super(driver);
  }
  public void hideKeyBoard(){
    getIosDriver().hideKeyboard();
  }

  public IOSDriver getIosDriver(){
    return ((IOSDriver) driver);
  }
}
