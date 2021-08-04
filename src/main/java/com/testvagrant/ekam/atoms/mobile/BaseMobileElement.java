package com.testvagrant.ekam.atoms.mobile;

import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

class BaseMobileElement {

  protected final AppiumDriver<MobileElement> driver;
  protected By locator;
  protected ConditionFactory wait;
  protected TouchAction<?> touchAction;
  protected Duration timeout;
  protected QueryFunctions queryFunctions;

  BaseMobileElement(AppiumDriver<MobileElement> driver, MultiPlatformFinder multiPlatformFinder) {
    this.driver = driver;
    this.locator = buildLocator(multiPlatformFinder);
    init();
  }

  BaseMobileElement(AppiumDriver<MobileElement> driver, By locator) {
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
    return driver instanceof IOSDriver
        ? multiPlatformFinder.getIosFindBy()
        : multiPlatformFinder.getAndroidFindBy();
  }

  private void init() {
    this.wait = buildFluentWait(timeout);
    this.touchAction = new TouchAction<>(driver);
    this.timeout = Duration.ofSeconds(30);
    this.queryFunctions = new QueryFunctions();
  }

  private ConditionFactory buildFluentWait(Duration duration) {
    return Awaitility.await().atMost(duration).ignoreExceptions();
  }
}
