public class TitleSimilarity {

	public double[] resemble(String sentences[], String title) {

		double resemblence[] = new double[sentences.length];
		String words[] = null;
		String twords[] = title.split(" ");

		for (int i = 0; i < sentences.length; i++) {
			words = sentences[i].split(" ");
			for (int k = 0; k < twords.length; k++) {
				for (int j = 0; j < words.length; j++) {
					if (words[j].contains(twords[k])) {
						resemblence[i]++;
					}
				}
			}
		}

		/*
		 * for (int i = 0; i < resemblence.length; i++) {
		 * System.out.println(i+"::"+resemblence[i]); }
		 */

		return resemblence;

	}
}
