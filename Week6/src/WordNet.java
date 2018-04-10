import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;

public class WordNet {
	Map<Integer, Set<String>> _synsets;
	Map<Integer, Set<Integer>> _hypernyms;
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		_synsets = new HashMap<Integer, Set<String>>();
		_hypernyms = new HashMap<Integer, Set<Integer>>();
		if (synsets == null || hypernyms == null) {
			   // throw IllegalArgumentException if input is not a rooted DAG
			   throw new java.lang.IllegalArgumentException();
		   }
		   In inSyn = new In(synsets);
		   In inHyp = new In(hypernyms);
		   String[] allSynsets = inSyn.readAllLines();
		   String[] allHypernyms = inHyp.readAllLines();
		   putInSyn(allSynsets);
		   putInHyp(allHypernyms);

	}
	
	private void putInSyn(String[] allVals) {
		for(String s : allVals) {
			String[] temp = s.split(",");
			String[] vals = temp[1].split(" ");
			Set<String> mySet = new HashSet<String>(Arrays.asList(vals));
			_synsets.put(Integer.parseInt(temp[0]), mySet);
		}
	}
	
	private void putInHyp(String[] allVals) {
		for(String s : allVals) {
			String[] temp = s.split(",");
			if(temp.length == 1) {
				_hypernyms.put(Integer.parseInt(temp[0]), null);
			}
			else {
				Integer[] vals =  strToint(temp[1].split(" "));
				Set<Integer> mySet = new HashSet<Integer>(Arrays.asList(vals));
				_hypernyms.put(Integer.parseInt(temp[0]), mySet);
			}
		}
	}
	
	private Integer[] strToint(String[] strArr) {
		Integer[] intArr = new Integer[strArr.length];
		for(int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}


	   // returns all WordNet nouns
   public Iterable<String> nouns() {
	   return null;
   }

	   // is the word a WordNet noun?
   public boolean isNoun(String word) {
	   if(word == null) {
		   throw new java.lang.IllegalArgumentException(); 
	   }
	   return false; 
   }

	   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {
	   if(nounA == null || nounB == null) {
		   throw new java.lang.IllegalArgumentException(); 
	   }
	   // should throw IllegalArgumentException unless both of the nouns are
	   // Wordnet nouns 
	   return 0; 
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
	   if (nounA == null || nounB == null) {
		   throw new java.lang.IllegalArgumentException();
	   }

	   // should throw IllegalArgumentException unless both of the nouns are
	   // Wordnet nouns 
	   return "";
   }
   // do unit testing of this class
   public static void main(String[] args) {
	   WordNet testing = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
   }

}
