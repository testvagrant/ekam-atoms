package com.testvagrant.ekam.atoms.web;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebPage extends QueryFunctions {

  @Inject protected BrowserDriver browserDriver;
  @Inject protected WebDriver driver;

  protected Element element(By locator) {
    return new Element(driver, locator);
  }

  protected Element element(MultiPlatformFinder finder) {
    return new Element(driver, finder);
  }

  protected Textbox textbox(By locator) {
    return new Textbox(driver, locator);
  }

  protected Textbox textbox(MultiPlatformFinder finder) {
    return new Textbox(driver, finder);
  }

  protected Dropdown dropdown(By locator) {
    return new Dropdown(driver, locator);
  }

  protected MultiPlatformFinder finder(By findBy, By responsiveFindBy) {
    return MultiPlatformFinder.builder()
        .webFindBy(findBy)
        .responsiveFindBy(responsiveFindBy)
        .build();
  }

  protected ElementCollection elementCollection(By locator) {
    return new ElementCollection(driver, locator);
  }

  @Override
  public String toString() {
    return "{"
            + "\"browserDriver\":" + browserDriver
            + ", \"driver\":" + driver
            + "}";
  }
}
