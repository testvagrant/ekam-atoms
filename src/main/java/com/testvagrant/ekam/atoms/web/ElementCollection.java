package com.testvagrant.ekam.atoms.web;

import com.google.inject.Inject;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ElementCollection {

  protected final WebDriver driver;
  private final ConditionFactory wait;
  private final By locator;
  private final Duration timeout;

  @Inject
  public ElementCollection(WebDriver driver, By locator) {
    this.driver = driver;
    this.timeout = Duration.ofSeconds(30);
    this.locator = locator;
    this.wait = buildFluentWait(timeout);
  }

  public List<String> getTextValues() {
    return get().stream().map(element -> element.getText().trim()).collect(Collectors.toList());
  }

  public List<String> getAttributeValues(String attribute) {
    return get().stream()
        .map(webElement -> webElement.getAttribute(attribute).trim())
        .collect(Collectors.toList());
  }

  public List<WebElement> get() {
    wait.atMost(Duration.ofSeconds(10))
        .until(
            () -> {
              List<WebElement> elements = driver.findElements(locator);
              return !elements.isEmpty();
            });

    return driver.findElements(locator);
  }

  public int count() {
    return get().size();
  }

  public ElementCollection waitUntil(Function<ElementCollection, Boolean> condition) {
    wait.until(() -> condition.apply(this));
    return this;
  }

  public ElementCollection waitUntilPresent() {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilPresent(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.presenceOfAllElementsLocatedBy(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilVisible() {
    try {
      waitUntilCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilInVisible() {
    try {
      waitUntilCondition(ExpectedConditions.invisibilityOfAllElements(get()));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilInVisible(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.invisibilityOfAllElements(get()), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format(
              "Error waiting for element invisibility with selector: %s. %s",
              locator, ex.getMessage()));
    }
  }

  public ElementCollection waitUntilVisible(Duration duration) {
    try {
      waitUntilCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator), duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilElementCountToBeMoreThan(int elementCountToBeGreaterThan) {
    try {
      waitUntilCondition(
          ExpectedConditions.numberOfElementsToBeMoreThan(locator, elementCountToBeGreaterThan));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilElementCountToBeMoreThan(
      int elementCountToBeGreaterThan, Duration duration) {
    try {
      waitUntilCondition(
          ExpectedConditions.numberOfElementsToBeMoreThan(locator, elementCountToBeGreaterThan),
          duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilElementCountToBeLessThan(int elementCountToBeLessThan) {
    try {
      waitUntilCondition(
          ExpectedConditions.numberOfElementsToBeLessThan(locator, elementCountToBeLessThan));
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  public ElementCollection waitUntilElementCountToBeLessThan(
      int elementCountToBeLessThan, Duration duration) {
    try {
      waitUntilCondition(
          ExpectedConditions.numberOfElementsToBeLessThan(locator, elementCountToBeLessThan),
          duration);
      return this;
    } catch (Exception ex) {
      throw new RuntimeException(
          String.format("Error waiting for element presence with selector: %s.", locator));
    }
  }

  private ConditionFactory buildFluentWait(Duration duration) {
    return Awaitility.await().atMost(duration).ignoreExceptions();
  }

  private <T> void waitUntilCondition(ExpectedCondition<T> webElementExpectedCondition) {
    waitUntilCondition(webElementExpectedCondition, timeout);
  }

  private <T> void waitUntilCondition(
      ExpectedCondition<T> webElementExpectedCondition, Duration duration) {
    wait.atMost(duration)
        .until(
            () -> {
              Object result = webElementExpectedCondition.apply(driver);
              return result != null
                      && result.getClass().getTypeName().toLowerCase().contains("boolean")
                  ? (boolean) result
                  : result != null;
            });
  }
}
