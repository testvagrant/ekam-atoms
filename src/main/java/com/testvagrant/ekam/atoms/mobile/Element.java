package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Element extends BaseMobileElement {

  private io.appium.java_client.MobileElement element;

  @Inject
  public Element(AppiumDriver<io.appium.java_client.MobileElement> driver, By locator) {
    super(driver, locator);
  }

  @Inject
  public Element(AppiumDriver<io.appium.java_client.MobileElement> driver, MultiPlatformFinder multiPlatformFinder) {
    super(driver, multiPlatformFinder);
  }

  public String getTextValue() {
    return getElement().getText();
  }

  public String getAttributeValue(String attribute) {
    return getElement().getAttribute(attribute);
  }

  public Element click() {
    waitUntilPresent();
    getElement().click();
    return this;
  }

  public boolean hasAttribute(String attribute) {
    String value = getElement().getAttribute(attribute);
    return value != null;
  }

  public boolean isEnabled() {
    return getElement().isEnabled();
  }

  public boolean isPresent(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfElementLocated(locator), duration);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public boolean isDisplayed() {
    try {
      return getElement().isDisplayed();
    } catch (Exception ex) {
      return false;
    }
  }

  public Element waitUntilDisplayed() {
    try {
      waitUntilCondition(ExpectedConditions.visibilityOfElementLocated(locator));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element with selector: %s to be displayed.", locator));
    }
  }

  public Element waitUntilDisplayed(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.visibilityOfElementLocated(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element with selector: %s to be displayed.", locator));
    }
  }

  public Element waitUntilInvisible() {
    try {
      waitUntilCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element with selector: %s to be invisible.", locator));
    }
  }

  public Element waitUntilInvisible(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.invisibilityOfElementLocated(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element with selector: %s to be invisible.", locator));
    }
  }

  public Element waitUntilPresent() {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfElementLocated(locator));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public Element waitUntilPresent(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfElementLocated(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public Element waitUntilTextToBePresent(String text) {
    try {
      waitUntilCondition(ExpectedConditions.textToBePresentInElementLocated(locator, text));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format(
              "Error waiting for text: '%s' to be present in element with selector: %s",
              text, locator));
    }
  }

  public Element waitUntilTextToBePresent(String text, Duration duration) {
    try {
      waitUntilCondition(
          ExpectedConditions.textToBePresentInElementLocated(locator, text), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format(
              "Error waiting for text: '%s' to be present in element with selector: %s",
              text, locator));
    }
  }

  public Element waitUntilTextToBePresent() {
    try {
      wait.until(() -> !getTextValue().trim().isEmpty());
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format(
              "Error waiting for text to be present in element with selector: %s.", locator));
    }
  }

  public Element waitUntilTextToBePresent(Duration duration) {
    try {
      wait.atMost(duration).until(() -> !getTextValue().trim().isEmpty());
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format(
              "Error waiting for text to be present in element with selector: %s.", locator));
    }
  }

  public Element waitUntilTextNotToBe(String text, boolean partial) {
    try {
      wait.until(
          () ->
              partial
                  ? !getTextValue().toLowerCase().contains(text.toLowerCase())
                  : !getTextValue().toLowerCase().contentEquals(text.toLowerCase()));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format(
              "Error waiting for text not to be '%s' in element with selector: %s.",
              text, locator));
    }
  }

  public Element tap() {
    touchAction.tap(ElementOption.element(getElement())).perform();
    return this;
  }

  public Element longPress() {
    touchAction
        .longPress(ElementOption.element(getElement()))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
        .perform();

    return this;
  }

  public io.appium.java_client.MobileElement getElement() {
    try {
      wait.atMost(Duration.ofSeconds(5)).until(() -> driver.findElement(locator) != null);
      return driver.findElement(locator);
    } catch (Exception ex) {
      throw new RuntimeException(String.format("Element with selector: %s not found", locator));
    }
  }

  public <T> T find(By selector, Class<T> tClass) {
    try {
      return tClass
          .getDeclaredConstructor(WebDriver.class, By.class)
          .newInstance(driver, new ByChained(locator, selector));
    } catch (Exception ex) {
      throw new RuntimeException(String.format("Element with selector: %s not found", locator));
    }
  }

  public <T> T find(MultiPlatformFinder multiPlatformFinder, Class<T> tClass) {
    try {
      return tClass
          .getDeclaredConstructor(WebDriver.class, By.class)
          .newInstance(driver, new ByChained(locator, buildLocator(multiPlatformFinder)));
    } catch (Exception ex) {
      throw new RuntimeException(String.format("Element with selector: %s not found", locator));
    }
  }

  public Element find(By selector) {
    try {
      return new Element(driver, new ByChained(locator, selector));
    } catch (Exception ex) {
      throw new RuntimeException(String.format("Element with selector: %s not found", locator));
    }
  }
}
