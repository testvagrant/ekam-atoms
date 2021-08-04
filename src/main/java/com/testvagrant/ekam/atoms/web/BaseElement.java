package com.testvagrant.ekam.atoms.web;

import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

class BaseElement {

  protected final WebDriver driver;
  protected By locator;
  protected ConditionFactory wait;
  protected Actions actions;
  protected Duration timeout;
  protected QueryFunctions queryFunctions;

  BaseElement(WebDriver driver, MultiPlatformFinder multiPlatformFinder) {
    this.driver = driver;
    this.locator = buildLocator(multiPlatformFinder);
    init();
  }

  BaseElement(WebDriver driver, By locator) {
    this.driver = driver;
    this.locator = locator;
    init();
  }

  protected <T> void waitUntilCondition(ExpectedCondition<T> expectedCondition) {
    waitUntilCondition(expectedCondition, timeout);
  }

  protected <T> void waitUntilCondition(ExpectedCondition<T> expectedCondition, Duration duration) {
    wait.atMost(duration)
        .until(
            () -> {
              Object result = expectedCondition.apply(driver);
              return result != null
                      && result.getClass().getTypeName().toLowerCase().contains("boolean")
                  ? (boolean) result
                  : result != null;
            });
  }

  protected By buildLocator(MultiPlatformFinder multiPlatformFinder) {
    return System.getProperty("web.target", "chrome").equalsIgnoreCase("responsive")
        ? multiPlatformFinder.getResponsiveFindBy()
        : multiPlatformFinder.getWebFindBy();
  }

  private void init() {
    this.wait = buildFluentWait(timeout);
    this.actions = new Actions(driver);
    this.timeout = Duration.ofSeconds(30);
    this.queryFunctions = new QueryFunctions();
  }

  private ConditionFactory buildFluentWait(Duration duration) {
    return Awaitility.await().atMost(duration).ignoreExceptions();
  }
}
