import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class CopyOfGA {

	static String title;
	public static String sentences[];
	static double sentenceFitness[];
	int popSize;
	int summaryLength;
	int numOfSentences;
	double[] F_Weights = new double[5]; // 1.centrality 2.title resemblance
										// 3.numerical data 4.sentence position
										// 5.sentence length
	int crossoverProb;
	double mut_per;
	Candidate[] Initial;
	Candidate[] childArr;
	Candidate[] selectPool;
	Candidate[] next_can;
	// Candidate[] temp;
	Random rand = new Random();

	int p_per;
	int mut;
	public static int flag;
	int iter_no;
	static float start_time;

	public CopyOfGA(String doc[], int size) {
		// TODO add this to property file and read in the constructor!
		sentences = doc;
		numOfSentences = size;
		Fitness();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter title:");
		title =  sc.nextLine();
		System.out.println("Enter number of sentences in summary:");
		summaryLength = sc.nextInt();
		//System.out.println("Enter population size:");
		// Fix a population size
		popSize = 12;// sc.nextInt();
		//System.out.println("Enter centrality weight:");
		F_Weights[0] = 1;// sc.nextInt();
		//System.out.println("Enter title resemblance weight:");
		F_Weights[1] = 1;// sc.nextInt();
		//System.out.println("Enter num data weight:");
		F_Weights[2] = 1;// sc.nextInt();
		//System.out.println("Enter position weight:");
		F_Weights[3] = 1;// sc.nextInt();
		//System.out.println("Enter length weight:");
		F_Weights[4] = 1;// sc.nextInt();
		System.out.println("Enter crossover probability:");
		crossoverProb = (int) ((double) ((sc.nextInt() * popSize) / 100));
		Initial = new Candidate[popSize];
		generateInitialPop();
		evaluateFitness(Initial);
		Crossover c = new Crossover(Initial, popSize - crossoverProb,
				numOfSentences);
		childArr = c.children;
		//System.out.println("Enter mutation percent:");
		//	FIXING MUTATION PERCENTAGE TO 1%
		mut = (int) ((double) (( childArr.length) / 100));
		Mutation m = new Mutation();
		m.mutate(childArr, mut);
		//System.out.println("Enter parent percent:");
		// 		---- CHECK FOR DIFFERENT VALUES OF PARENT
		p_per = (int) ((double) ((60 * popSize) / 100));
		generateNextCan();
		//System.out.println("Enter no. of generation:");
		iter_no = 1000;
		Iteration();
	}

	void generateInitialPop() {

		for (int n = 0; n < popSize; n++) {
			int arr[] = new int[numOfSentences];
			for (int i = 0; i < summaryLength;) {
				int sen = rand.nextInt(numOfSentences);
				if (arr[sen] == 1)
					continue;
				else {
					arr[sen] = 1;
					i++;
				}

			}
			String name = "";
			for (int i = 0; i < numOfSentences; i++)
				name += arr[i] + "";
			Candidate c = new Candidate(name);
			Initial[n] = c;
		}
		System.out.println("Initial population:");
		for (int i = 0; i < popSize; i++)
			System.out.println(Initial[i].getName());
	}

	void Fitness() {
		// TFIDFSimilarity t = new TFIDFSimilarity(sentences, numOfSentences);
		// TODO change public double array of Rank to private and more sensible!
		sentenceFitness = LexRank.Rank;
	}

	void Iteration() {
		for (int i = 0; i < iter_no; i++) {
			evaluateFitness(next_can);
			Crossover c = new Crossover(next_can, popSize - crossoverProb,
					numOfSentences);
			childArr = c.children;
			Mutation m = new Mutation();
			m.mutate(childArr, mut);
			generateNextCan();
			System.out.println("Generation " + i + ":");
			flag = 1;
			Arrays.sort(next_can, Collections.reverseOrder());
			for (int j = 0; j < next_can.length; j++)
				System.out.println("name:" + next_can[j].getName() + "fit:"
						+ next_can[j].getFitness());
		}
	}

	void evaluateFitness(Candidate[] can) {
		int pos;
		double fit;

		TitleSimilarity t = new TitleSimilarity();
		double titleSim[] = t.resemble(sentences, title);

		Numerical num = new Numerical();
		double numData[] = num.numScore(sentences);
		for (int i = 0; i < can.length; i++) {
			fit = 0.0;
			pos = -1;
			for (int j = 0; j < summaryLength; j++) {
				pos = can[i].getName().indexOf("1", pos + 1);
				if (pos != -1)
 					can[i].setFitness(((F_Weights[0] * sentenceFitness[pos]
							+ F_Weights[1] * titleSim[pos] + F_Weights[2]
							* numData[pos] + F_Weights[3] * (pos / 10) + F_Weights[4]
							* (sentences[pos].length() / 100)) / (F_Weights[0]
							+ F_Weights[1] + F_Weights[2] + F_Weights[3] + F_Weights[4])) * 100);
				else
					break;
			}
			System.out.println("candidate " + i + " fitness:"
					+ can[i].getFitness());
		}
	}

	void generateNextCan() {
		flag = 0;
		Arrays.sort(Initial, Collections.reverseOrder());
		int i = 0;
		next_can = new Candidate[Initial.length];
		selectPool = new Candidate[popSize - p_per + childArr.length];
		for (i = 0; i < Initial.length; i++) {
			next_can[i] = new Candidate("");
			if (i < p_per)
				next_can[i] = Initial[i];
			else
				selectPool[i - p_per] = Initial[i];
		}
		for (i = 0; i < childArr.length; i++)
			selectPool[i + (popSize - p_per)] = childArr[i];
		evaluateFitness(selectPool);
		Roulette r = new Roulette();
		r.RW(selectPool, popSize - p_per);
		for (int j = p_per; j < Initial.length; j++)
			next_can[j] = selectPool[j - p_per];

	}

	public static void main(String args[]) {
		// GA g=new GA(d,15);
	}

}
