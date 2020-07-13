package br.com.getnet.utils;

import java.util.List;

import org.openqa.selenium.WebElement;

public class Utils {

	public static boolean isTextPresent(List<WebElement> list, String title) {
		for(WebElement element: list) {
			if(element.getText().contains(title)) {
				return true;
			}
			
		}
		return false;
	}
}
