package com.testvagrant.ekam.atoms.web;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Textbox extends Element {

  @Inject
  public Textbox(WebDriver driver, By locator) {
    super(driver, locator);
  }

  @Inject
  public Textbox(WebDriver driver, MultiPlatformFinder finder) {
    super(driver, finder);
  }

  public void setText(CharSequence value) {
    super.getElement().sendKeys(value);
  }

  public void clear() {
    super.getElement().clear();
  }
}
