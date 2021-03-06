package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Textbox extends Element {

  @Inject
  public Textbox(AppiumDriver<io.appium.java_client.MobileElement> driver, By locator) {
    super(driver, locator);
  }

  @Inject
  public Textbox(AppiumDriver<io.appium.java_client.MobileElement> driver, MultiPlatformFinder multiPlatformFinder) {
    super(driver, multiPlatformFinder);
  }

  public void setText(CharSequence value) {
    super.getElement().sendKeys(value);
  }

  public void clear() {
    super.getElement().clear();
  }
}
