Reading Data from Princeton Wordnet into Text Files

The purpose of this program is to scan the Princeton Wordnet database and store the hyponym and meronym data as well as full hypernym trees such that this data may be analyzed by another program. This program uses the JWI API from MIT to read and translate the Wordnet 3.0 database into workable .txt files for use in applications such as MATLAB. This program was used to build the dataset for the Codenames AI also available on GitHub.

The output .txt file format is as follows for hyponyms and hypernms ("word.txt"):
-------------------------
word
~
hyponyms
~
Level 1 Hypernyms
~
Level 2 Hypernyms
~
...
~
Level MAX hypernyms (MAX is a const int that can be modified at the top of MainClass.java)
-------------------------

The output .txt file format is as follows for meronyms ("word meronyms.txt"):
-------------------------
word
~
meronyms
-------------------------


TO RUN:

1. Download and install the Princeton Wordnet lexical database.

2. Download and install the JWI API for Wordnet.

3. Download the WordnetDataCapture repository.

In MainClass class within MainClass.java:

4. Insert words for analysis in the array titled "codenames".

5. Follow the instructions in the comments in order to select either hypernyms/hyponyms or meronyms for output.

6. This data can then be further analyzed using another program such as MATLAB.
