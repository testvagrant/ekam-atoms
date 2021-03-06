package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.interactions.Actions;

public class DeviceDriver {

  private final Actions actions;
  protected AppiumDriver<MobileElement> driver;

  @Inject
  public DeviceDriver(AppiumDriver<MobileElement> driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
  }

  public void navigateBack() {
    driver.navigate().back();
  }

  public void hideKeyboard() {
    driver.hideKeyboard();
  }

  public void sendKeys(String value) {
    int valueLength = value.length();
    for (int counter = 0; counter < valueLength; counter++) {
      actions.sendKeys(String.valueOf(value.charAt(counter))).build().perform();
    }
  }
}
