package com.testvagrant.ekam.atoms.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

class BaseElement {

  protected final AppiumDriver<MobileElement> driver;
  protected By locator;
  protected ConditionFactory wait;
  protected TouchAction<?> touchAction;
  protected Duration timeout;

  BaseElement(AppiumDriver<MobileElement> driver, Finder finder) {
    this.driver = driver;
    this.locator = buildLocator(finder);
    init();
  }

  BaseElement(AppiumDriver<MobileElement> driver, By locator) {
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

  protected By buildLocator(Finder finder) {
    return driver instanceof IOSDriver ? finder.getIosFindBy() : finder.getAndroidFindBy();
  }

  private ConditionFactory buildFluentWait(Duration duration) {
    return Awaitility.await().atMost(duration).ignoreExceptions();
  }

  private void init() {
    this.wait = buildFluentWait(timeout);
    this.touchAction = new TouchAction<>(driver);
    this.timeout = Duration.ofSeconds(30);
  }
}
