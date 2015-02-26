import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GA {

	static String title;
	public static String sentences[];
	public static String original[];
	static double SenFitness[];

	int pop_size;
	int SummaryLength;
	int nSentences;

	double[] F_Weights = new double[5]; // 1.centrality 2.title resemblance
										// 3.numerical data 4.sentence position
										// 5.sentence length
	int crossoverProb;
	double mut_per;

	Candidate[] Initial;
	Candidate[] childArr;
	Candidate[] selectPool;
	Candidate[] next_cand;
	// Candidate[] temp;
	Random rand = new Random();

	int p_per;
	int mut;
	public static int flag;
	int iter_no;
	static float start_time;

	public GA(String originalDoc[], String filteredDoc[]) {

		sentences = filteredDoc;
		original = originalDoc;
		nSentences = filteredDoc.length;

		Fitness();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter title:");
		title = sc.nextLine();
		System.out.println("Enter number of sentences in summary:");
		SummaryLength = sc.nextInt();
		/*
		// System.out.println("Enter centrality weight:");
		F_Weights[0] = 1;// sc.nextInt();
		// System.out.println("Enter title resemblance weight:");
		F_Weights[1] = 1;// sc.nextInt();
		// System.out.println("Enter numeric data weight:");
		F_Weights[2] = 1;// sc.nextInt();
		// System.out.println("Enter position weight:");
		F_Weights[3] = 1;// sc.nextInt();
		// System.out.println("Enter length weight:");
		F_Weights[4] = 1;// sc.nextInt();

		// System.out.println("Enter population size:");
		pop_size = nSentences;// sc.nextInt();

		// System.out.println("Enter crossover probability:");
		crossoverProb = (int) ((double) ((70 * pop_size) / 100));
		Initial = new Candidate[pop_size];
		generateInitialPop();
		evaluateFitness(Initial);
        
		Crossover c = new Crossover(Initial, pop_size - crossoverProb,
				nSentences);
		childArr = c.children;
		// System.out.println("Enter mutation percent:");
		mut = (int) ((double) ((childArr.length) / 100));
		Mutation m = new Mutation();
		m.mutate(childArr, mut);
		// System.out.println("Enter parent percent:");
		p_per = (int) ((double) ((60 * pop_size) / 100));
		generateNextCandidate();
		// System.out.println("Enter no. of generation:");
		iter_no = 1000;
		Iteration(); */
		Fitness();
		printResult();
	}

	void printResult() {
		/*
		 *   Display the best sentences (GA)
		 */
		/*
		Double max;
		max = -100.0;
		int index = 0;
		for (int i = 0; i < next_cand.length; i++) {
			if (Double.isNaN(next_cand[i].Fit))
				next_cand[i].Fit = -1;
		}*/
		/*
		 * To display top k sentences according to fitness
		 */

		/*for (int j = 0; j < SummaryLength; j++) {
			max = -100.0;
			for (int i = 0; i < next_cand.length; i++) {
				if (Double.compare(next_cand[i].Fit, max) > 0) {
					max = next_cand[i].Fit;
					index = i;
				}
			}
			for (int i = 0; i < next_cand.length; i++) {
			//	System.out.println(next_cand[i].Fit);
			}

			//System.out.println(original[index] + "Fitness is "
			//		+ next_cand[index].Fit);
			//next_cand[index].Fit = -100.0;
			 
			 */
			Double max;
			max = -100.0;
			int index = 0;
			Integer[] index1=new Integer[SummaryLength];
		    //Arrays.sort(SenFitness);
			//for(int i=0;i<SenFitness.length;i++)
				//System.out.println(SenFitness[i]);
			for (int j = 0; j < SummaryLength; j++) {
				max = -100.0;
				for (int i = 0; i < SenFitness.length; i++) {
					if (Double.compare(SenFitness[i], max) > 0) {
						max = SenFitness[i];
						index1[j] = i;
					}
				}
				SenFitness[index1[j]]=-100.0;
				
			}
			Arrays.sort(index1);
			System.out.println("Your summary is");
			for(int i=0;i<index1.length;i++)
				System.out.println(original[index1[i]]+".");
			
			
	}

	void generateInitialPop() {

		for (int n = 0; n < pop_size; n++) {

			int arr[] = new int[nSentences];
			for (int i = 0; i < SummaryLength;) {
				int sen = rand.nextInt(nSentences);
				if (arr[sen] == 1)
					continue;
				else {
					arr[sen] = 1;
					i++;
				}
			}

			String name = "";
			for (int i = 0; i < nSentences; i++)
				name += arr[i] + "";
			Candidate c = new Candidate(name);
			Initial[n] = c;
		}

		// System.out.println("Initial population:");
		for (int i = 0; i < pop_size; i++) {
			// System.out.println(Initial[i].getName());
		}
	}

	void Fitness() {
		// TODO a lot!
		Td_idf t = new Td_idf(sentences, nSentences);
		SenFitness = LexRank.Rank;
	}

	void Iteration() {

		for (int i = 0; i < iter_no; i++) {
			evaluateFitness(next_cand);
			Crossover c = new Crossover(next_cand, pop_size - crossoverProb,
					nSentences);
			childArr = c.children;
			Mutation m = new Mutation();
			m.mutate(childArr, mut);
			generateNextCandidate();

			// System.out.println("Generation "+i+":");
			flag = 1;
			Arrays.sort(next_cand, Collections.reverseOrder());
			for (int j = 0; j < next_cand.length; j++) {
				// System.out.println("name:"+next_cand[j].getName()+"fit:"+next_cand[j].getFitness());
			}
		}
	}

	void evaluateFitness(Candidate[] cand) {

		int pos;
		double fit;

		TitleSimilarity t = new TitleSimilarity();

		double titleSim[] = t.resemble(sentences, title);
		// Fitness();
		Numerical num = new Numerical();
		double numData[] = num.numScore(sentences);
		
		for (int i = 0; i < cand.length; i++) {
			fit = 0.0;
			pos = 0;
			for (int j = 0; j < SummaryLength; j++) {
				pos = cand[i].getName().indexOf("1", pos + 1);
				if (pos != -1)
					cand[i].setFitness(((F_Weights[0] * SenFitness[pos]
							+ F_Weights[1] * titleSim[pos] + F_Weights[2]
							* numData[pos] + F_Weights[3] * (pos / 10) + F_Weights[4]
							* (sentences[pos].length() / 100)) / (F_Weights[0]
							+ F_Weights[1] + F_Weights[2] + F_Weights[3] + F_Weights[4])) * 100);
				else
					break;
			}
			if(Double.isNaN(cand[i].getFitness())){
				//System.out.println(i+" "+pos+" "+cand[i].getName()+"\n "+cand[i].getFitness());
				continue;
			}
			// System.out.println("canddidate "+i+" fitness:"+cand[i].getFitness());
		}
	}

	void generateNextCandidate() {
		flag = 0;
		Arrays.sort(Initial, Collections.reverseOrder());
		int i = 0;
		next_cand = new Candidate[Initial.length];
		selectPool = new Candidate[pop_size - p_per + childArr.length];
		for (i = 0; i < Initial.length; i++) {
			next_cand[i] = new Candidate("");
			if (i < p_per)
				next_cand[i] = Initial[i];
			else
				selectPool[i - p_per] = Initial[i];
		}
		for (i = 0; i < childArr.length; i++)
			selectPool[i + (pop_size - p_per)] = childArr[i];
		evaluateFitness(selectPool);
		Roulette r = new Roulette();
		r.RW(selectPool, pop_size - p_per);
		for (int j = p_per; j < Initial.length; j++)
			next_cand[j] = selectPool[j - p_per];
	}

	public static void main(String args[]) {
		// GA g=new GA(d,15);
	}

}
