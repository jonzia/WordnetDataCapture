// Gathering codename information from WordNet library
// Created By: Jonathan Zia
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.io.IOException;
import java.net.URL;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;

import java.io.FileWriter;
import java.io.IOException;

class WordNet {
}


public class MainClass {
	int MAX = 4;
	public static void main(String[] args) throws IOException {
		try {
			URL url = initializeDictionary();
			//INSERT WORDS FOR ANALYSIS HERE
			String[] codewords = {};
			for(int a = 1; a < codewords.length; a++) {
				String lookup = codewords[a];
				//THE CODE BELOW IS CONFIGURED FOR OUTPUTTING MERONYMS. FOR OUTPUTTING HYPERNYMS/HYPONYMS, IT MUST BE MODIFIED.
				//UNCOMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. COMMENT WHEN OUTPUTTING MERONYMS.
				//String filename = lookup.concat(".txt");
				//COMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. UNCOMMENT WHEN OUTPUTTING MERONYMS.
				String filename2 = lookup.concat(" meronyms.txt");
				//UNCOMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. COMMENT WHEN OUTPUTTING MERONYMS.
				//FileWriter writer = new FileWriter(filename, true);
				//COMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. UNCOMMENT WHEN OUTPUTTING MERONYMS.
				FileWriter writer2 = new FileWriter(filename2, true);
				MainClass temp = new MainClass();
				//UNCOMMENT THE TWO LINES BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. COMMENT WHEN OUTPUTTING MERONYMS.
				//temp.getHyponyms(lookup, url, writer);
				//temp.getHypernymTree(lookup, url, writer);
				//COMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. UNCOMMENT WHEN OUTPUTTING MERONYMS.
				temp.getMeronyms(lookup, url, writer2);
				//UNCOMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. COMMENT WHEN OUTPUTTING MERONYMS.
				//writer.close();
				//COMMENT THE LINE BELOW WHEN OUTPUTTING HYPERNYMS/HYPONYMS. UNCOMMENT WHEN OUTPUTTING MERONYMS.
				writer2.close();
				//You're all set!
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Method for specifying the Wordnet dictionary file location
	public static URL initializeDictionary() throws IOException {
		// construct the URL to the Wordnet dictionary directory
		String path = "/usr/local/WordNet-3.0/dict/";
		URL url = new URL("file", null, path);
		return url;
	}
	
	// Method for obtaining the number of synsets
	public int getNumSyn(String codename, URL url) throws IOException {
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(url);
		dict.open();
		
		// Look up first sense of the word
		IIndexWord idxWord = dict.getIndexWord (codename, POS.NOUN);
		List <IWordID> wordID = idxWord.getWordIDs(); int j = 0;
		for(IWordID id : wordID) {
//			IWord word = dict.getWord(wordID);
//			System.out.println ("Id = " + wordID );
//			System.out .println ("Lemma = " + word.getLemma());
//			System.out.println ("Gloss = " + word.getSynset().getGloss());
			j++;
		}
		return j;
	}
	
	// Method for obtaining the hypernyms of the word specified by "lookup"
	public void getHypernyms(String codename, URL url) throws IOException {
		IDictionary dict = new Dictionary(url);
		dict.open();
		
		String nextHypernym = codename;
		do {
			// Look up first sense of the word
			IIndexWord idxWord = dict.getIndexWord (nextHypernym, POS.NOUN);
			IWordID wordID = (IWordID) idxWord.getWordIDs().get(0);
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
			// Get the Hypernyms
			List <ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);
			// Print out each hypernym
			List <IWord> words;
			for(ISynsetID sid : hypernyms) {
				words = dict.getSynset(sid).getWords();
				for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
					nextHypernym = i.next().getLemma();
					System.out.println(nextHypernym);
				}
			}
		} while (nextHypernym != "entity");
		dict.close();
	}
	
	public void getHyponyms(String codename, URL url, FileWriter writer, FileWriter writer2) throws IOException {
		IDictionary dict = new Dictionary(url);
		dict.open();
		
		System.out.println(codename);
		writer.write(codename);
		writer.write("\n");
		System.out.println("~");
		writer.write("~");
		writer.write("\n");
		// Look up first sense of the word
		int j = getNumSyn(codename, url);
		for(int k = 0; k < j; k++) {
			IIndexWord idxWord = dict.getIndexWord (codename, POS.NOUN);
			IWordID wordID = (IWordID) idxWord.getWordIDs().get(k);
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
			// Get the Hyponyms
			List <ISynsetID> hyponyms = synset.getRelatedSynsets(Pointer.HYPONYM);
			// Print out each hyponym
			List <IWord> words;
			for(ISynsetID sid : hyponyms) {
				words = dict.getSynset(sid).getWords();
				for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
					String print = i.next().getLemma();
					System.out.println(print);
					writer.write(print);
					writer.write("\n");
				}
			}
		}
		dict.close();
	}
	
	public void getHypernymTree(String codename, URL url, FileWriter writer, FileWriter writer2) throws IOException {
		IDictionary dict = new Dictionary(url);
		dict.open();
		String[] array = {codename};
		String[] array2;
		int treeCounter = 1;
		do {
			int counter1 = 0; // Counter to append array.length
			int counter2 = 0; // Counter for adding values to array
			System.out.println("~");
			writer.write("~");
			writer.write("\n");
			for(int l = 0; l < array.length; l++) {
				if(array[l] != null) {
					// Look up first sense of the word
					IIndexWord idxWord = dict.getIndexWord (array[l], POS.NOUN);
					int j = getNumSyn(array[l], url);
					for(int k = 0; k < j; k++) {
						IWordID wordID = (IWordID) idxWord.getWordIDs().get(k);
						IWord word = dict.getWord(wordID);
						ISynset synset = word.getSynset();
						// Get the Hypernyms
						List <ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);
						// Print out each hypernym
						List <IWord> words;
						for(ISynsetID sid : hypernyms) {
							words = dict.getSynset(sid).getWords();
							for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
								String temp1 = i.next().getLemma();
								//System.out.println(temp1);
								if(Objects.equals(temp1, "entity") == false) {
									counter1++;
								}
							}
						}
					}
				}
				else
					break;
			}
			array2 = new String[counter1];
			for(int l = 0; l < array.length; l++) {
				if (array[l] != null) {
					// Look up first sense of the word
					IIndexWord idxWord = dict.getIndexWord (array[l], POS.NOUN);
					int j = getNumSyn(array[l], url);
					for(int k = 0; k < j; k++) {
						IWordID wordID = (IWordID) idxWord.getWordIDs().get(k);
						IWord word = dict.getWord(wordID);
						ISynset synset = word.getSynset();
						// Get the Hypernyms
						List <ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);
						// Print out each hypernym
						List <IWord> words;
						for(ISynsetID sid : hypernyms) {
							words = dict.getSynset(sid).getWords();
							for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
								String temp1 = i.next().getLemma();
								if(Objects.equals(temp1, "entity") == false && contains(array2,temp1) == false) {
									array2[counter2] = temp1;
									counter2++;
									System.out.println(temp1);
									writer.write(temp1);
									int print = treeCounter;
									writer.write("\n");
								}
							}
						}
					}
				}
				else
					break;
			}
			array = new String[array2.length];
			array = array2;
			treeCounter++;
		} while(treeCounter < MAX);
		dict.close();
	}
	
	public boolean contains(String[] array, String repeat) {
		boolean result = false;
		for(int i = 0; i < array.length; i++) {
			String currentCodeword = array[i];
			if(Objects.equals(currentCodeword, repeat))
				result = true;
		}
		return result;
	}
	
	public void getMeronyms(String codename, URL url, FileWriter writer3, FileWriter writer4) throws IOException {
		IDictionary dict = new Dictionary(url);
		dict.open();
		
		System.out.println(codename);
		writer2.write(codename);
		writer2.write("\n");
		System.out.println("~");
		writer2.write("~");
		writer2.write("\n");
		// Look up first sense of the word
		int j = getNumSyn(codename, url);
		for(int k = 0; k < j; k++) {
			IIndexWord idxWord = dict.getIndexWord (codename, POS.NOUN);
			IWordID wordID = (IWordID) idxWord.getWordIDs().get(k);
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
			// Get the Hyponyms
			List <ISynsetID> hyponyms = synset.getRelatedSynsets(Pointer.MERONYM_PART);
			// Print out each hyponym
			List <IWord> words;
			for(ISynsetID sid : hyponyms) {
				words = dict.getSynset(sid).getWords();
				for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
					String print = i.next().getLemma();
					System.out.println(print);
					writer2.write(print);
					writer2.write("\n");
				}
			}
		}
		dict.close();
	}
	
}
