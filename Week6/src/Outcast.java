import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
	private WordNet words;
	public Outcast(WordNet wordnet) {
		this.words = wordnet;
		// constructor takes a WordNet object
	}
	public String outcast(String[] nouns) {
		// given an array of WordNet nouns, return an outcast
		int max = Integer.MIN_VALUE;
		String retVal = "";
		for(int i = 0; i < nouns.length; i++) {
			int temp = 0;
			String noun1 = nouns[i];
			for(int j = 0; j < nouns.length; j++) {
				String noun2 = nouns[j];
				temp += words.distance(noun1, noun2);
			}
			System.out.println(noun1 + " " + temp);
			if(temp > max) {
				retVal = noun1;
				max = temp;
			}
		}
		return retVal;
	}

	public static void main(String[] args) {
		// see test client below
		WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}

}
