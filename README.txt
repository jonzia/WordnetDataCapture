Reading Data from Princeton Wordnet into Text Files

The purpose of this program is to scan the Princeton Wordnet database and store the hyponym and meronym data as well as full hypernym trees. This program uses the JWI API from MIT to read and translate the Wordnet 3.0 database into workable .txt files for use in applications such as MATLAB. This program was used to build the dataset for the Codenames AI also available on GitHub.

TO RUN:

1. Download and install the Princeton Wordnet lexical database.

2. Download and install the JWI API for Wordnet.

3. Download the WordnetDataCapture repository.

4. Insert words for analysis in the array titled "codenames".

5. If desired, modify the meronym, hypernym, and hyponym functions provided based on the API documentation in order to obtain the relevant attributes for the words you are searching for.

6. This data can then be further analyzed using another program.