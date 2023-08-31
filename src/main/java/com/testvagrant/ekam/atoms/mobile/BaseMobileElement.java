package com.testvagrant.ekam.atoms.mobile;

import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import com.testvagrant.ekam.atoms.mobile.ios.IOSDeviceDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

class BaseMobileElement {

    protected WebDriver webDriver;

    protected By locator;
    protected ConditionFactory wait;
    protected TouchAction<?> touchAction;
    protected Duration timeout;
    protected QueryFunctions queryFunctions;

    BaseMobileElement(WebDriver driver, MultiPlatformFinder multiPlatformFinder) {
        this.webDriver = driver;
        this.locator = buildLocator(multiPlatformFinder);
        init();
    }

  BaseMobileElement(WebDriver driver, By locator) {
    this.webDriver = driver;
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
                            Object result = expectedCondition.apply(webDriver);
                            return result != null
                                    && result.getClass().getTypeName().toLowerCase().contains("boolean")
                                    ? (boolean) result
                                    : result != null;
                        });
    }

    protected By buildLocator(MultiPlatformFinder multiPlatformFinder) {
        return webDriver instanceof IOSDriver
                ? multiPlatformFinder.getIosFindBy()
                : multiPlatformFinder.getAndroidFindBy();
    }

    private void init() {
        this.timeout = Duration.ofSeconds(30);
        this.wait = buildFluentWait(timeout);
//        this.touchAction = new TouchAction<>(androidDriver);
        this.queryFunctions = new QueryFunctions();
    }

    private ConditionFactory buildFluentWait(Duration duration) {
        return Awaitility.await().atMost(duration).ignoreExceptions();
    }
}
