package com.testvagrant.ekam.atoms.mobile.ios;

import com.google.inject.Inject;

public class IOSDeviceDriver{

  protected IOSDeviceDriver iosDeviceDriver;
  @Inject
  public IOSDeviceDriver(IOSDeviceDriver driver) {
    this.iosDeviceDriver = driver;
  }
}
