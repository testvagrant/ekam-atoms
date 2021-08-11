package com.testvagrant.ekam.atoms.web;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.function.Function;

public class Element extends BaseWebElement {

  @Inject
  public Element(WebDriver driver, By locator) {
    super(driver, locator);
  }

  @Inject
  public Element(WebDriver driver, MultiPlatformFinder finder) {
    super(driver, finder);
  }

  public String getTextValue() {
    return getElement().getText().trim();
  }

  public String getAttributeValue(String attribute) {
    return getElement().getAttribute(attribute);
  }

  public Element click() {
    waitUntilPresent();
    getElement().click();
    return this;
  }

  public Element hover() {
    waitUntilPresent();
    actions.moveToElement(getElement()).build().perform();
    return this;
  }

  public boolean hasAttribute(String attribute) {
    String value = getElement().getAttribute(attribute);
    return value != null;
  }

  public boolean isEnabled() {
    return getElement().isEnabled();
  }

  public Element dragDrop(Element destination) {
    actions.dragAndDrop(getElement(), destination.getElement()).build().perform();
    return this;
  }

  public boolean isPresent() {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfElementLocated(locator));
      return true;
    } catch (Exception ex) {
      return false;
    }
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

  public Element scrollIntoView() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement());
    return this;
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

  public Element waitUntilInvisible() {
    try {
      waitUntilCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
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

  public Element waitUntilInvisible(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.invisibilityOfElementLocated(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element with selector: %s to be invisible.", locator));
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

  public Element waitUntilPresent(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfElementLocated(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public Element waitUntil(Function<Element, Boolean> condition) {
    wait.until(() -> condition.apply(this));
    return this;
  }

  public WebElement getElement() {
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

  public Element find(MultiPlatformFinder selector) {
    try {
      return new Element(driver, new ByChained(locator, buildLocator(selector)));
    } catch (Exception ex) {
      throw new RuntimeException(String.format("Element with selector: %s not found", locator));
    }
  }
}
