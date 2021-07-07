package com.testvagrant.ekam.atoms.web;

import org.openqa.selenium.By;

public class QueryFunctions {
  public By query(String value) {
    return value.contains("//") ? By.xpath(value) : By.cssSelector(value);
  }

  public By queryById(String id) {
    return By.id(id);
  }

  public By queryByName(String name) {
    return By.name(name);
  }

  public By queryByClass(String className) {
    return By.className(className);
  }

  public By queryByAttribute(String attribute, String value) {
    String selector =
        String.format(
            "//*[normalize-space(translate(@%1$s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) = '%2$s']",
            attribute, value.toLowerCase());

    return query(selector);
  }

  public By queryByAttribute(String attribute, String value, Boolean partial) {
    if (!partial) {
      return queryByAttribute(attribute, value);
    }

    String selector =
        String.format(
            "//*[contains(normalize-space(translate(@%1%s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) , '%2$s')]",
            attribute, value.toLowerCase());

    return query(selector);
  }

  public By queryByText(String text) {
    String selector =
        String.format(
            "//*[normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) = '%1$s']",
            text.toLowerCase());
    return query(selector);
  }

  public By queryByText(String text, Boolean partial) {
    if (!partial) {
      return queryByText(text);
    }

    String selector =
        String.format(
            "//*[contains(normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) , '%1$s')]",
            text.toLowerCase());

    return query(selector);
  }

  public String ignoreSpaceAndCase(String text) {
    return ignoreSpaceAndCase(text, false);
  }

  public String ignoreSpaceAndCase(String text, boolean partial) {
    if (partial) {
      return String.format(
          "contains(normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) , '%1$s')",
          text.toLowerCase());
    }
    return String.format(
        "normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) = '%1$s'",
        text.toLowerCase());
  }
}
