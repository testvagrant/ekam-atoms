package com.testvagrant.ekam.atoms.mobile.flutter;


import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FlutterFinder implements FlutterElementActions {
    RemoteWebDriver driver;
    FileDetector fileDetector;
    private static final String FINDER_TYPE = "finderType";

    @Inject
    public FlutterFinder(WebDriver driver) {
        this.driver = (RemoteWebDriver) driver;
        this.fileDetector = keys -> null;
    }

    @Override
    public FlutterElement byValueKey(String key) {
        FlutterElement flutterElement = new FlutterElement(ImmutableMap
                .of(FINDER_TYPE, "ByValueKey",
                        "keyValueType", "String",
                        "keyValueString", key));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement byValueKey(int key) {
        FlutterElement flutterElement = new FlutterElement(ImmutableMap
                .of(FINDER_TYPE, "ByValueKey",
                        "keyValueType", "int",
                        "keyValueString", key));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement byType(String type) {
        FlutterElement flutterElement = new FlutterElement(ImmutableMap.of(
                FINDER_TYPE, "ByType",
                "type", type
        ));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement byToolTip(String toolTipText) {
        FlutterElement flutterElement= new FlutterElement(ImmutableMap.of(
                FINDER_TYPE, "ByTooltipMessage",
                "text", toolTipText
        ));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement byText(String input) {
        FlutterElement flutterElement= new FlutterElement(ImmutableMap.of(
                FINDER_TYPE, "ByText",
                "text", input
        ));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement byAncestor(FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        Map<String, Object> matchIdentifier = new HashMap<>(ImmutableMap.of(
                FINDER_TYPE, "Ancestor",
                "matchRoot", matchRoot,
                "firstMatchOnly", firstMatchOnly
        ));
        matchIdentifier.put("of", of.getRawMap());
        matchIdentifier.put("matching", matching.getRawMap());
        FlutterElement flutterElement= new FlutterElement(matchIdentifier);
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement byDescendant(FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        Map<String, Object> matchIdentifier = new HashMap<>(ImmutableMap.of(
                FINDER_TYPE, "Descendant",
                "matchRoot", matchRoot,
                "firstMatchOnly", firstMatchOnly
        ));
        matchIdentifier.put("of", of.getRawMap());
        matchIdentifier.put("matching", matching.getRawMap());
        FlutterElement flutterElement= new FlutterElement(matchIdentifier);
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement bySemanticsLabel(String label) {
        FlutterElement flutterElement= new FlutterElement(ImmutableMap.of(
                FINDER_TYPE, "BySemanticsLabel",
                "isRegExp", false,
                "label", label
        ));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement bySemanticsLabel(Pattern label) {
        FlutterElement flutterElement =new FlutterElement(ImmutableMap.of(
                FINDER_TYPE, "BySemanticsLabel",
                "isRegExp", true,
                "label", label.toString()
        ));
        flutterElement.setParent(driver);
        flutterElement.setFileDetector(fileDetector);
        return flutterElement;
    }

    @Override
    public FlutterElement pageBack() {
        return new FlutterElement(ImmutableMap.of(FINDER_TYPE, "PageBack"));
    }
}