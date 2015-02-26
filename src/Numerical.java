import java.util.regex.Pattern;

public class Numerical {

	public double[] score;

	/*
	 * public Numerical() {
	 * 
	 * try{ String [] st = new String[6] ; st[0]=
	 * " In June 2012, the ABS progressively released results from the 2011 Census,[3] with additional releases due in 2013."
	 * ; st[1]=
	 * "R&D Higher Education survey, conducted biennially by the ABS since 1994; in 1990 and 1992 it was collected by Department of Employment"
	 * ; st[2]=
	 * " The Indus Valley Civilisation, which spread and flourished in the northwestern part of the Indian subcontinent from c. 3300 to 1300 BCE in present-day Pakistan and northwest India, was the first major civilisation in South Asia."
	 * ; st[3]=
	 * "The Mughal dynasty ruled most of the Indian subcontinent by 1600; it went into a slow decline after 1707"
	 * ; num(st); } catch(Exception e) { System.out.println(); }
	 * 
	 * }
	 */

	double[] numScore(String[] sentences) {

		score = new double[sentences.length];
		String num = "[0-9]+";
		Pattern pat = Pattern.compile("[ .,:;!()@~+]");

		for (int i = 0; i < sentences.length; i++) {

			double numCount = 0;

			String temp[] = pat.split(sentences[i]);
			int senLen = temp.length;
			int j = 0;
			while (j < temp.length) {
				if (temp[j] == null) {
					j++;
				} else if (temp[j].matches(num)) {
					numCount++;
					j++;
				} else {
					j++;
				}
			}

			score[i] = (numCount / senLen) * 100;

		}
		
		/*for (int i = 0; i < score.length; i++) {
			System.out.println("sentence" + " " + i + " " + score[i]);
		}*/
		
		return score;
	}

}
