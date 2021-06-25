package com.testvagrant.ekam.atoms.web;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends Element {

  @Inject
  public Button(WebDriver driver, String buttonText) {
    super(
        driver,
        By.xpath(
            String.format(
                "//button[%1$s] | //button[.//*[%1$s]]",
                new QueryFunctions().ignoreSpaceAndCase(buttonText))));
  }
}
