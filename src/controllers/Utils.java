package controllers;

import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> fromString(String string) {
		String[] strings = string.replace("[", "").replace("]", "").split(", ");
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			result.add(strings[i]);
		}
		return result;
	}

}
