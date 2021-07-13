package com.implement.dp;

import java.util.ArrayList;
import java.util.List;

public class GenerateAbbreviations {
	private void solve(List<String> result, String word, int pos, String abbr,
			int count) {
		if (pos == word.length()) {
			if (count > 0)
				abbr = abbr + count;

			result.add(abbr);
			return;
		} 
		// Don't include letter, abbreviate i.e count++
		solve(result, word, pos + 1, abbr, count + 1);

		/*
		 * Include letter If count > 0, add it before considering next character
		 * Reset count to 0
		 */
		if (count > 0)
			abbr = abbr + count;

		abbr = abbr + word.charAt(pos);

		solve(result, word, pos + 1, abbr, 0);
	
	}

	public List<String> generateAbbreviations(String word) {
		List<String> result = new ArrayList<String>();
		solve(result, word, 0, "", 0);

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenerateAbbreviations ga = new GenerateAbbreviations();

		String input = "word";

		System.out.println(ga.generateAbbreviations(input));
	}

}
