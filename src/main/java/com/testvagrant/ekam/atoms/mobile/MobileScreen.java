package com.testvagrant.ekam.atoms.mobile;

import com.google.inject.Inject;
import com.testvagrant.ekam.atoms.MultiPlatformFinder;
import com.testvagrant.ekam.atoms.mobile.android.AndroidDeviceDriver;
import com.testvagrant.ekam.atoms.mobile.flutter.FlutterBy;
import com.testvagrant.ekam.atoms.mobile.flutter.FlutterElement;
import com.testvagrant.ekam.atoms.mobile.flutter.FlutterElementFinder;
import com.testvagrant.ekam.atoms.mobile.flutter.FlutterFinder;
import com.testvagrant.ekam.atoms.mobile.ios.IOSDeviceDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Pattern;

public abstract class MobileScreen extends QueryFunctions {

    @Inject
    protected WebDriver driver;
    @Inject
    protected AndroidDeviceDriver androidDeviceDriver;
    @Inject
    protected IOSDeviceDriver iosDeviceDriver;
    @Inject
    protected FlutterFinder flutterFinder;

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
    protected WebElement flutterElement(FlutterBy by, String value) {
        return getFlutterElementFinder().flutterElement(by, value);
    }

    protected WebElement flutterElement(FlutterBy by, int value) {
        return getFlutterElementFinder().flutterElement(by, value);
    }

    protected WebElement flutterElement(FlutterBy by, Pattern value) {
        return getFlutterElementFinder().flutterElement(by, value);
    }

    protected WebElement flutterElement(FlutterBy by) {
        return getFlutterElementFinder().flutterElement(by);
    }

    protected WebElement flutterElement(FlutterBy by, FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        return getFlutterElementFinder().flutterElement(by, of, matching, matchRoot, firstMatchOnly);
    }

    private FlutterElementFinder getFlutterElementFinder() {
        return new FlutterElementFinder(flutterFinder);
    }
}
