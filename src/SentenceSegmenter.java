import java.io.BufferedReader;
import java.io.FileReader;

public class SentenceSegmenter {
	
	String[] segmenter(String File) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(File));
		String line = null;
		String article = null;
		while ( (line = br.readLine()) != null ) {
			article += line;
		}

		int stopperCount = article.length() - article.replace(".", "").length()
				+ article.length() - article.replace("?", "").length()
				+ article.length() - article.replace("!", "").length();

		String[] sentences = new String[stopperCount];

		int startIndex = 0;
		boolean quoteFlag = false;
		int counter = 0;

		char[] textArray = article.toCharArray();

		// add word length
		int wordlength = 0;

		for (int i = 0; i < textArray.length; i++) {
					
			if (textArray[i] == '\"') {
				if (quoteFlag)
					quoteFlag = false;
				else
					quoteFlag = true;
			} else if (textArray[i] == '.' || textArray[i] == '?'
					|| textArray[i] == '!') {
						char c = textArray[i-1];
				if( textArray[i] == '.' && wordlength < 3 || (c >= '0' && c <= '9')) {wordlength  = 0; continue; }
				if (!quoteFlag) {
					if (i - startIndex > 2){
						System.out.println(article.substring(startIndex, i + 1));
						sentences[counter++] = article.substring(startIndex, i + 1);
					}
					startIndex = i = i + 1;
				}
			}wordlength += 1;
				if (textArray[i] == '.' || textArray[i] == '?'
					|| textArray[i] == '!' || textArray[i] == ' ')
					wordlength = 0;
		}
		return sentences;
	}
}
