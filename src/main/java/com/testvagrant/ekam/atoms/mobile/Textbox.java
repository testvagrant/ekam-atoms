package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class Textbox extends Element {

  @Inject
  public Textbox(AppiumDriver<MobileElement> driver, By locator) {
    super(driver, locator);
  }

  @Inject
  public Textbox(AppiumDriver<MobileElement> driver, Finder finder) {
    super(driver, finder);
  }

  public void setText(CharSequence value) {
    super.getElement().sendKeys(value);
  }

  public void clear() {
    super.getElement().clear();
  }
}
