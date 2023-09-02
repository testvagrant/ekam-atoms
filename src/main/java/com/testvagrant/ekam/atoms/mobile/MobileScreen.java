package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class MobileScreen extends QueryFunctions {

    @Inject
    protected WebDriver driver;

    protected Element element(By locator) {
        return new Element(driver, locator);
    }

    protected Element element(MultiPlatformFinder finder) {
        return new Element(driver, finder);
    }

    protected Textbox textbox(By locator) {
        return new Textbox(driver, locator);
    }

    protected Textbox textbox(MultiPlatformFinder finder) {
        return new Textbox(driver, finder);
    }

    protected ElementCollection elementCollection(By locator) {
        return new ElementCollection(driver, locator);
    }

    protected ElementCollection elementCollection(MultiPlatformFinder finder) {
        return new ElementCollection(driver, finder);
    }

    protected MultiPlatformFinder finder(By androidFindBy, By iosFindBy) {
        return MultiPlatformFinder.builder().androidFindBy(androidFindBy).iosFindBy(iosFindBy).build();
    }

    @SuppressWarnings("unchecked")
    protected <T extends WebDriver> T getDriver(Class<T> driverType) {
        if (driverType.isInstance(driver)) {
            return (T) driver;
        } else {
            throw new IllegalArgumentException("Driver is not an instance of " + driverType.getName());
        }
    }

}
