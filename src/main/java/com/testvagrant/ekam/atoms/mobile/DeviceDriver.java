package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DeviceDriver {

    private final Actions actions;
    protected WebDriver driver;

    @Inject
    public DeviceDriver(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void sendKeys(String value) {
        int valueLength = value.length();
        for (int counter = 0; counter < valueLength; counter++) {
            actions.sendKeys(String.valueOf(value.charAt(counter))).build().perform();
        }
    }

    protected <T extends WebDriver> T getDriver(Class<T> driverType) {
        if (driverType.isInstance(this.driver)) {
            return (T) this.driver;
        } else {
            throw new IllegalArgumentException("Driver is not an instance of " + driverType.getName());
        }
    }
}
