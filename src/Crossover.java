import java.util.Random;

public class Crossover {

	Random rand = new Random();
	public static Candidate children[];

	// childNum = popSize - crossoverProb
	Crossover(Candidate[] candidates, int childNum, int nSentences) {

		children = new Candidate[childNum];
		nSentences = nSentences / 2;

		for (int i = 0; i < childNum; i++) {
			children[i] = new Candidate();
			int n = rand.nextInt(candidates.length);
			Candidate c = candidates[n];
			String per = c.getName().substring(nSentences);
			// System.out.print("p:"+c.getName());
			String child = permutation(per);
			children[i].setName((candidates[n].getName().substring(0,
					nSentences)) + child);
			// System.out.println("c:"+children[i].getName());
		}
	}

	String permutation(String st) {
		int count = 0;
		int pos = 0;
		char parent[] = st.toCharArray();
		for (int i = 0; i < st.length(); i++)
			if (parent[i] == '1')
				count++;

		int arr[] = new int[st.length()];
		for (int i = 0; i < count;) {
			pos = rand.nextInt(arr.length);
			if (arr[pos] == 1)
				continue;
			arr[pos] = 1;
			i++;
		}
		st = "";
		for (int i = 0; i < arr.length; i++)
			st += arr[i];
		return st;
	}

}
