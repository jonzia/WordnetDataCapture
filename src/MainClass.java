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
			String[] codewords = {"africa",	"africa",	"agent",	"air",	"alien",	"alp",	"amazon",	"ambulance",	"america",	"angel",	"antarctica",	"apple",	"arm",	"atlantis",	"australia",	"aztec",	"back",	"ball",	"band",	"bank",	"bar",	"bark",	"bat",	"battery",	"beach",	"bear",	"beat",	"bed",	"beijing",	"bell",	"belt",	"berlin",	"bermuda",	"berry",	"bill",	"block",	"board",	"bolt",	"bomb",	"bond",	"boom",	"boot",	"bottle",	"bow",	"box",	"bridge",	"brush",	"buck",	"buffalo",	"bug",	"bugle",	"button",	"calf",	"canada",	"cap",	"capital",	"car",	"card",	"carrot",	"casino",	"cast",	"cat",	"cell",	"centaur",	"center",	"chair",	"change",	"charge",	"check",	"chest",	"chick",	"china",	"chocolate",	"church",	"circle",	"cliff",	"cloak",	"club",	"code",	"cold",	"comic",	"compound",	"concert",	"conductor",	"contract",	"cook",	"copper",	"cotton",	"court",	"cover",	"crane",	"crash",	"cricket",	"cross",	"crown",	"cycle",	"czech",	"dance",	"date",	"day",	"death",	"deck",	"degree",	"diamond",	"dice",	"dinosaur",	"disease",	"doctor",	"dog",	"draft",	"dragon",	"dress",	"drill",	"drop",	"duck",	"dwarf",	"eagle",	"egypt",	"embassy",	"engine",	"england",	"europe",	"eye",	"face",	"fair",	"fall",	"fan",	"fence",	"field",	"fighter",	"figure",	"file",	"film",	"fire",	"fish",	"flute",	"fly",	"foot",	"force",	"forest",	"fork",	"france",	"game",	"gas",	"genius",	"germany",	"ghost",	"giant",	"glass",	"glove",	"gold",	"grace",	"grass",	"greece",	"green",	"ground",	"ham",	"hand",	"hawk",	"head",	"heart",	"helicopter",	"himalayas",	"hole",	"hollywood",	"honey",	"hood",	"hook",	"horn",	"horse",	"horseshoe",	"hospital",	"hotel",	"ice",	"ice cream",	"india",	"iron",	"ivory",	"jack",	"jam",	"jet",	"jupiter",	"kangaroo",	"ketchup",	"key",	"kid",	"king",	"kiwi",	"knife",	"knight",	"lab",	"lap",	"laser",	"lawyer",	"lead",	"lemon",	"leprechaun",	"life",	"light",	"limousine",	"line",	"link",	"lion",	"litter",	"loch ness",	"lock",	"log",	"london",	"luck",	"mail",	"mammoth",	"maple",	"marble",	"march",	"mass",	"match",	"mercury",	"mexico",	"microscope",	"millionaire",	"mine",	"mint",	"missile",	"model",	"mole",	"moon",	"moscow",	"mount",	"mouse",	"mouth",	"mug",	"nail",	"needle",	"net",	"new york",	"night",	"ninja",	"note",	"novel",	"nurse",	"nut",	"octopus",	"oil",	"olive",	"olympus",	"opera",	"orange",	"organ",	"palm",	"pan",	"pants",	"paper",	"parachute",	"park",	"part",	"pass",	"paste",	"penguin",	"phoenix",	"piano",	"pie",	"pilot",	"pin",	"pipe",	"pirate",	"pistol",	"pit",	"pitch",	"plane",	"plastic",	"plate",	"platypus",	"play",	"plot",	"point",	"poison",	"pole",	"police",	"pool",	"port",	"post",	"pound",	"press",	"princess",	"pumpkin",	"pupil",	"pyramid",	"queen",	"rabbit",	"racket",	"ray",	"revolution",	"ring",	"robin",	"robot",	"rock",	"rome",	"root",	"rose",	"roulette",	"round",	"row",	"ruler",	"satellite",	"saturn",	"scale",	"school",	"scientist",	"scorpion",	"screen",	"scuba diver",	"seal",	"server",	"shadow",	"shakespeare",	"shark",	"ship",	"shoe",	"shop",	"shot",	"sink",	"skyscraper",	"slip",	"slug",	"smuggler",	"snow",	"snowman",	"sock",	"soldier",	"soul",	"sound",	"space",	"spell",	"spider",	"spike",	"spine",	"spot",	"spring",	"spy",	"square",	"stadium",	"staff",	"star",	"state",	"stick",	"stock",	"straw",	"stream",	"strike",	"string",	"sub",	"suit",	"hero",	"swing",	"switch",	"table",	"tablet",	"tag",	"tail",	"tap",	"teacher",	"telescope",	"temple",	"theater",	"thief",	"thumb",	"tick",	"tie",	"time",	"tokyo",	"tooth",	"torch",	"tower",	"track",	"train",	"triangle",	"trip",	"trunk",	"tube",	"turkey",	"undertaker",	"unicorn",	"vacuum",	"van",	"vet",	"wake",	"wall",	"war",	"washer",	"washington",	"watch",	"water",	"wave",	"web",	"well",	"whale",	"whip",	"wind",	"witch",	"worm",	"yard",};
			for(int a = 1; a < codewords.length; a++) {
				String lookup = codewords[a];
				//String filename = lookup.concat(".txt");
				//String filename2 = lookup.concat(" weights.txt");
				String filename3 = lookup.concat(" meronyms.txt");
				String filename4 = lookup.concat(" meronym weights.txt");
				//FileWriter writer2 = new FileWriter(filename2, true);
				//FileWriter writer = new FileWriter(filename, true);
				FileWriter writer3 = new FileWriter(filename3, true);
				FileWriter writer4 = new FileWriter(filename4, true);
				MainClass temp = new MainClass();
				//temp.getHyponyms(lookup, url, writer, writer2);
				//temp.getHypernymTree(lookup, url, writer, writer2);
				temp.getMeronyms(lookup, url, writer3, writer4);
				//writer.close();
				//writer2.close();
				writer3.close();
				writer4.close();
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
		writer2.write(codename);
		writer.write("\n");
		writer2.write("\n");
		System.out.println("~");
		writer.write("~");
		writer2.write("~");
		writer.write("\n");
		writer2.write("\n");
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
					writer2.write("0.5");
					writer.write("\n");
					writer2.write("\n");
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
			writer2.write("~");
			writer.write("\n");
			writer2.write("\n");
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
									writer2.write(Integer.toString(print));
									writer.write("\n");
									writer2.write("\n");
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
		writer3.write(codename);
		writer4.write(codename);
		writer3.write("\n");
		writer4.write("\n");
		System.out.println("~");
		writer3.write("~");
		writer4.write("~");
		writer3.write("\n");
		writer4.write("\n");
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
					writer3.write(print);
					writer4.write("0.5");
					writer3.write("\n");
					writer4.write("\n");
				}
			}
		}
		dict.close();
	}
	
}