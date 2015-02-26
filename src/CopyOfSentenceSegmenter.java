
public class CopyOfSentenceSegmenter {

	String[] segmenter(String article) throws Exception {

		String s[] = new String[article.split("[\\.\\!\\?]").length];
		
		s = article.split("[\\.\\!\\?]");
		/*for (int i = 0; i < s.length; i++) {
			System.out.print("Line " + i + "+" + s[i] + "\n");
		}*/

		return s;
	}
}
