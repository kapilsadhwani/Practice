package com.implement.slidingWindow;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> list = new ArrayList<>();
		// position of first word in line
		int first = 0;
		while (first < words.length) {
			int width = words[first].length();
			// position of last word in line
			int last = first + 1;
			while (last < words.length
					&& width + words[last].length() + 1 <= maxWidth) {
				width += words[last++].length() + 1;
			}
			
			StringBuilder sb = new StringBuilder(maxWidth);
			int numOfSpacer = last - first - 1;
			// last line or one word in a line, left-justified
			if (last == words.length || numOfSpacer == 0) {
				sb.append(words[first]);
				for (int i = first + 1; i < last; i++) {
					sb.append(" ").append(words[i]);
				}
				
				// Append extra spaces
				for (int i = sb.length(); i < maxWidth; i++) {
					sb.append(" ");
				}
			} else {
				int spaces = (maxWidth - width) / numOfSpacer;
				// extra spaces add to left spacers
				int extra = (maxWidth - width) % numOfSpacer;
				
				// No space after the last word. Hence ignore it
				for (int i = first; i < last - 1; i++) {
					sb.append(words[i]);
					for (int j = 0; j <= spaces + ((i - first) < extra ? 1 : 0); j++) {
						sb.append(" ");
					}
				}
				sb.append(words[last - 1]);
			}
			list.add(String.valueOf(sb));
			first = last;
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth = 16;
		
		List<String> lines = fullJustify(words, maxWidth);
		
		for(String line: lines){
			System.out.println("|" + line + "|");
		}
		
		System.out.println("=====================================");
		
		words = new String[]{"What","must","be","acknowledgment","shall","be"};
		maxWidth = 16;
		lines = fullJustify(words, maxWidth);
		
		for(String line: lines){
			System.out.println("|" + line + "|");
		}
		
		System.out.println("=====================================");
		
		words = new String[]{"Science","is","what","we","understand","well","enough","to","explain",
        "to","a","computer.","Art","is","everything","else","we","do"};
		maxWidth = 20;
		lines = fullJustify(words, maxWidth);
		
		for(String line: lines){
			System.out.println("|" + line + "|");
		}
	}

}
