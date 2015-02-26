import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StopWords {

	ArrayList<String> stopWord() {

		ArrayList<String> stopWords = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					Constants.STOPWORD_FILE));

			String str = null;
			while ((str = br.readLine()) != null) {
				stopWords.add(str);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stopWords;
	}
}
