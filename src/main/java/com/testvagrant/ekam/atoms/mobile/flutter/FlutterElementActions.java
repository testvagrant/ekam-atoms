package com.testvagrant.ekam.atoms.mobile.flutter;

import java.util.regex.Pattern;

public interface FlutterElementActions {
    FlutterElement byAncestor(FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly);

    FlutterElement byDescendant(FlutterElement of, FlutterElement matching, boolean matchRoot, boolean firstMatchOnly);

    FlutterElement bySemanticsLabel(String label);

    FlutterElement bySemanticsLabel(Pattern label);

    FlutterElement byText(String input);

    FlutterElement byToolTip(String toolTipText);

    FlutterElement byType(String type);

    FlutterElement byValueKey(String key);

    FlutterElement byValueKey(int key);

    FlutterElement pageBack();
}
