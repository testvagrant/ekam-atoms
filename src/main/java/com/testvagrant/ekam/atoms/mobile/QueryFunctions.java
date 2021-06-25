package com.testvagrant.ekam.atoms.mobile;

import org.openqa.selenium.By;

public class QueryFunctions {

  public By queryAndroidTextViewByText(String text) {
    return queryByText("android.widget.TextView", text);
  }

  public By queryAndroidTextViewByAttribute(String attribute, String value) {
    return queryByAttribute("android.widget.TextView", attribute, value);
  }

  public By query(String value) {
    return By.xpath(value); // Default mobile query is by xpath
  }

  public By queryByText(String tagName, String text) {
    return By.xpath(String.format("//%1$s[@text='%2$s']", tagName, text));
  }

  public By queryById(String id) {
    return By.id(id);
  }

  public By queryByContentDesc(String value) {
    return queryByAttribute("content-desc", value);
  }

  public By queryByName(String name) {
    return By.name(name);
  }

  public By queryByLinkText(String linkText) {
    return By.linkText(linkText);
  }

  public By queryByPartialLinkText(String partialLinkText) {
    return By.linkText(partialLinkText);
  }

  public By queryByTagName(String tagName) {
    return By.linkText(tagName);
  }

  public By queryByAttribute(String attributeName, String value) {
    return queryByAttribute("*", attributeName, value);
  }

  public By queryByAttribute(String tagName, String attributeName, String value) {
    return query(String.format("//%s[@%s='%s']", tagName, attributeName, value));
  }
}
