package com.prep.implement.string;

public class StringIntoChunks {
	public void breakIntoChunks(String source, int bufferSize) {
        int lastIndexProcessed = 0;
        while (lastIndexProcessed < source.length()) {
            int endIndex = (lastIndexProcessed + bufferSize);
            if (endIndex <= source.length()) {
                String choppedString = source.substring(lastIndexProcessed, (lastIndexProcessed + bufferSize));
                int minIndex = Math.min(choppedString.length(), choppedString.lastIndexOf(" "));
                choppedString = choppedString.substring(0, minIndex);
                System.out.println("[" + choppedString + "]");
                lastIndexProcessed = lastIndexProcessed + choppedString.length();
            } else {
                String choppedString = source.substring(lastIndexProcessed, source.length());
                System.out.println("[" + choppedString + "]");
                lastIndexProcessed = source.length();
            }
        }
    }

	public static void main(String[] args) {
		StringIntoChunks sic = new StringIntoChunks();
		// TODO Auto-generated method stub
		String message = "one two three four five six seven eight   nine";
		
		// Cut String into BufferSize whilst maintaining word integrity
		sic.breakIntoChunks(message, 15);
	}

}
