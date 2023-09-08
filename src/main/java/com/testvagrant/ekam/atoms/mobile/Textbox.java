package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Textbox extends Element {

  @Inject
  public Textbox(WebDriver driver, By locator) {
    super(driver, locator);
  }

  @Inject
  public Textbox(WebDriver driver, MultiPlatformFinder multiPlatformFinder) {
    super(driver, multiPlatformFinder);
  }

  public void setText(CharSequence value) {
    super.getElement().sendKeys(value);
  }

  public void clear() {
    super.getElement().clear();
  }
}
