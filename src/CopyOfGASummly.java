/*
 * (1) identifying the sentences of the document; (2) converting all characters to lower 
case (case folding); (3) removing very common words (stop words) which do not 
contribute to the meaning of the text � e.g., �the�, �a�, etc.; (4) removing suffixes 
(i.e., performing stemming), so that words such as �learned� and �learning� are 
converted to the standard form �learn�
 */

public class CopyOfGASummly {

	public static void main(String[] args) throws Exception {

		SentenceSegmenter readline = new SentenceSegmenter();
		String[] segmentedSentences = readline.segmenter(Constants.INPUT_FILE);

		String[] originalText = new String[segmentedSentences.length];
		originalText = (String[]) segmentedSentences.clone();
		String[] result;
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

		// TODO change arguments of function
		CopyOfGA g = new CopyOfGA(originalText, FilteredDoc);
		// Result string will have the final result containing the summarized
		// document
		result = g.returnResult();
		for (int i = 0; i < result.length; i++)
			System.out.println(result[i]);
		// tfisf
		// Td_idf idf=new Td_idf(FilteredDoc,FilteredDoc.length);

	}

	String[] summarize(String article) throws Exception {
		
		String[] result = null;
		CopyOfSentenceSegmenter readline = new CopyOfSentenceSegmenter();
		String[] segmentedSentences = readline.segmenter(article);

		String[] originalText = new String[segmentedSentences.length];
		originalText = (String[]) segmentedSentences.clone();

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
		GA g = new GA(originalText, FilteredDoc);

		//Result string will have the final result containing the summarized document
		result = g.returnResult();

		return result;
	}
}
