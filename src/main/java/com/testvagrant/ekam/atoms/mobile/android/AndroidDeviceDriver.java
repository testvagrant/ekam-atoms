package com.testvagrant.ekam.atoms.mobile.android;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.mobile.DeviceDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebDriver;

public class AndroidDeviceDriver extends DeviceDriver {

    @Inject
    public AndroidDeviceDriver(WebDriver driver) {
        super(driver);
    }

  public void pressKey(AndroidKey key) {
      getAndroidDriver().pressKey(new KeyEvent(key));
  }

  public void hideKeyBoard(){
        getAndroidDriver().hideKeyboard();
  }

    public AndroidDriver getAndroidDriver(){
        return ((AndroidDriver) driver);
  }
}
