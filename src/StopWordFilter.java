import java.io.IOException;
import java.util.ArrayList;

/*
 *  Removes stop words from segmented sentences vector  
 *  
 */

public class StopWordFilter {

	String[] Filterdoc(String doc_vec[], ArrayList<String> stopWords)
			throws IOException {

		for (String word : stopWords) {
			for (int i = 0; i < doc_vec.length; i++) {
				doc_vec[i] = doc_vec[i].replaceAll("\\b" + word + "\\b", "");
			}
		}
		return doc_vec;
	}

}
