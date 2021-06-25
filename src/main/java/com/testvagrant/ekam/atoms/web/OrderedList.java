package com.testvagrant.ekam.atoms.web;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderedList extends UnOrderedList {

  @Inject
  public OrderedList(WebDriver driver, By locator) {
    super(driver, locator);
  }
}
