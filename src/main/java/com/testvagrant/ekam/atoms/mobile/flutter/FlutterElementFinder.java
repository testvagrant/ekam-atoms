package com.testvagrant.ekam.atoms.mobile.flutter;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;

import java.util.regex.Pattern;

public class FlutterElementFinder {
    private FlutterFinder flutterFinder;

    @Inject
    public FlutterElementFinder(FlutterFinder flutterFinder) {
        this.flutterFinder = flutterFinder;
    }

    public WebElement flutterElement(FlutterBy by, String value) {
        FlutterElement flutterElement = findFlutterElement(by, value);
        return convertToWebElement(flutterElement);
    }

    public WebElement flutterElement(FlutterBy by) {
        if (by == FlutterBy.PAGE_BACK) {
            FlutterElement flutterElement = flutterFinder.pageBack();
            return convertToWebElement(flutterElement);
        } else {
            throw new IllegalArgumentException("Unsupported FlutterBy: " + by);
        }
    }

    public WebElement flutterElement(FlutterBy by, int value) {
        FlutterElement flutterElement;
        if (by == FlutterBy.VALUE_KEY) {
            flutterElement = flutterFinder.byValueKey(value);
        } else {
            throw new IllegalArgumentException("Unsupported FlutterBy: " + by);
        }
        return convertToWebElement(flutterElement);
    }

    public WebElement flutterElement(FlutterBy by, FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        FlutterElement flutterElement = findFlutterElement(by, of, matching, matchRoot, firstMatchOnly);
        return convertToWebElement(flutterElement);
    }

    public WebElement flutterElement(FlutterBy by, Pattern label) {
        FlutterElement flutterElement = findFlutterElement(by, label);
        return convertToWebElement(flutterElement);
    }

    private FlutterElement findFlutterElement(FlutterBy by, String value) {
        FlutterElement element;
        switch (by) {
            case VALUE_KEY:
                element = flutterFinder.byValueKey(value);
                break;
            case TYPE:
                element = flutterFinder.byType(value);
                break;
            case TOOL_TIP:
                element = flutterFinder.byToolTip(value);
                break;
            case TEXT:
                element = flutterFinder.byText(value);
                break;
            case SEMANTICS_LABEL:
                element = flutterFinder.bySemanticsLabel(value);
                break;
            default:
                throw new IllegalArgumentException("Unsupported FlutterBy: " + by);
        }
        return element;
    }

    private FlutterElement findFlutterElement(FlutterBy by, FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly) {
        if (by == FlutterBy.ANCESTOR) {
            return flutterFinder.byAncestor(of, matching, matchRoot, firstMatchOnly);
        } else if (by == FlutterBy.DESCENDANT) {
            return flutterFinder.byDescendant(of, matching, matchRoot, firstMatchOnly);
        } else {
            throw new IllegalArgumentException("Unsupported FlutterBy: " + by);
        }
    }

    private FlutterElement findFlutterElement(FlutterBy by, Pattern label) {
        if (by == FlutterBy.SEMANTICS_LABEL) {
            return flutterFinder.bySemanticsLabel(label);
        } else {
            throw new IllegalArgumentException("Unsupported FlutterBy: " + by);
        }
    }

    private WebElement convertToWebElement(FlutterElement flutterElement) {
        try {
            return (WebElement) flutterElement;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid Flutter element");
        }
    }
}

