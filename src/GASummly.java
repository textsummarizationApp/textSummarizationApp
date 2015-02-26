import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/*
 * (1) identifying the sentences of the document; (2) converting all characters to lower 
case (case folding); (3) removing very common words (stop words) which do not 
contribute to the meaning of the text – e.g., “the”, “a”, etc.; (4) removing suffixes 
(i.e., performing stemming), so that words such as “learned” and “learning” are 
converted to the standard form “learn”
 */

public class GASummly {

	public static void main(String[] args) throws Exception {

		SentenceSegmenter readline = new SentenceSegmenter();
		String[] segmentedSentences = readline.segmenter(Constants.INPUT_FILE);
		
		String[] originalText=new String[segmentedSentences.length];
		originalText=(String[]) segmentedSentences.clone();
	
		for (int i = 0; i < segmentedSentences.length; i++) {
			segmentedSentences[i] = segmentedSentences[i].toLowerCase();
		}

		StopWordFilter stopWordFilter = new StopWordFilter();
		StopWords stopword = new StopWords();
		String FilteredDoc[] = new String[segmentedSentences.length];
		FilteredDoc = stopWordFilter.Filterdoc(segmentedSentences,
				stopword.stopWord());
		
		// case Folding
		for (int i = 0; i < FilteredDoc.length; i++) {
			FilteredDoc[i] = FilteredDoc[i].toLowerCase();
		}

		//TODO change arguments of function
		GA g = new GA( originalText, FilteredDoc);
		// tfisf
		// Td_idf idf=new Td_idf(FilteredDoc,FilteredDoc.length);

	}

}
