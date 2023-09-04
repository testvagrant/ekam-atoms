package com.testvagrant.ekam.atoms.mobile.ios;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.mobile.DeviceDriver;
import org.openqa.selenium.WebDriver;

public class IOSDeviceDriver extends DeviceDriver {
  @Inject
  public IOSDeviceDriver(WebDriver driver) {
    super(driver);
  }
}
