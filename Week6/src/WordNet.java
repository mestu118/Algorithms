import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
	private final HashMap<String, Bag<Integer>> _synsetsToId;
	private final HashMap<Integer, String> _IdtoSynset;
	private int _sizeSynset;
	private Digraph _digraphHyper;
	private final SAP _sap;
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		// Instantiating the _synset data structure 
		this._synsetsToId = new HashMap<String, Bag<Integer>>();
		this._IdtoSynset = new HashMap<Integer, String>();
		
		//Checking for null objects
 		if (synsets == null || hypernyms == null) {
			   // throw IllegalArgumentException if input is not a rooted DAG
		   throw new java.lang.IllegalArgumentException();
	   }
 		
 		// Reading in files 
	   In inSyn = new In(synsets);
	   In inHyp = new In(hypernyms);
	   
	   // Reading all lines from synsets and hypernyms 
	   String[] allSynsets = inSyn.readAllLines();
	   String[] allHypernyms = inHyp.readAllLines();
	   
	   //Putting all of the synsets in the _synset data strucutre 
	   putInSyn(allSynsets);
	   
	   //instantiating _hypernyms with the size of _synsets 
	   _digraphHyper = new Digraph(_IdtoSynset.size());
	   
	   //Putting all of the values of hypernyms into _hypernyms
	   	putInHyp(allHypernyms);
	   	_sap = new SAP(_digraphHyper);
	}
	
	private void putInSyn(String[] allVals) {
		Bag<Integer> bag;
		for(String s : allVals) {
			String[] temp = s.split(",");
			String[] vals = temp[1].split(" ");
			_IdtoSynset.put(Integer.parseInt(temp[0]), temp[1]);
			for(int i = 0; i < vals.length; i++) {
				bag = _synsetsToId.get(vals[i]);
				if (bag == null) {
                    bag = new Bag<Integer>();
                    bag.add(Integer.parseInt(temp[0]));
                    _synsetsToId.put(vals[i], bag);
                } else {
                    bag.add(Integer.parseInt(temp[0]));
                }
			}
		}
		
		//synset size
		_sizeSynset = (_synsetsToId.size() - 1);
	}
	
	private void putInHyp(String[] allVals) {
		for(int i = 0; i < allVals.length; i++) {
			String[] temp = allVals[i].split(",");
			for(int j = 1; j < temp.length; j++) {
				_digraphHyper.addEdge(i, Integer.parseInt(temp[j]));
			}
		}
	}


	// returns all WordNet nouns
   public Iterable<String> nouns() {
	   Stack<String> nounWords = new Stack<String>();
	   for(String s : _synsetsToId.keySet()) {
		   nounWords.push(s);
	   }
	   return _synsetsToId.keySet();
   }

	   // is the word a WordNet noun?
   public boolean isNoun(String word) {
	   if(word == null) {
		   throw new java.lang.IllegalArgumentException(); 
	   }
	   return _synsetsToId.containsKey(word); 
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {
	   if(nounA == null || nounB == null) {
		   throw new java.lang.IllegalArgumentException(); 
	   }
	   if(!(_synsetsToId.containsKey(nounA) && _synsetsToId.containsKey(nounB))) {
		   throw new java.lang.IllegalArgumentException();
	   }

	   return _sap.length(_synsetsToId.get(nounA), _synsetsToId.get(nounB));

   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
	   if (nounA == null || nounB == null) {
		   throw new java.lang.IllegalArgumentException();
	   }
	   if(!(_synsetsToId.containsKey(nounA) && _synsetsToId.containsKey(nounB))) {
		   throw new java.lang.IllegalArgumentException();
	   }

	   // should throw IllegalArgumentException unless both of the nouns are
	   // Wordnet nouns 
	   return _IdtoSynset.get(_sap.ancestor(_synsetsToId.get(nounA), _synsetsToId.get(nounB)));
   }
   // do unit testing of this class
   public static void main(String[] args) {
	   WordNet testing = new WordNet("wordnet/synsets15.txt", "wordnet/hypernyms15Tree.txt");
	   System.out.println(testing.distance("a", "c"));
   }


}
