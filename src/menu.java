import java.util.Scanner;

public class menu {
	public static void main(String[] args) {

		DocumentProcessor documentProcessor = new DocumentProcessor("C:\\Users\\USER01\\Desktop\\csc212project\\data\\stop.txt");
		documentProcessor.LoadDocuments("C:\\Users\\USER01\\Desktop\\csc212project\\data\\dataset.csv");	
		BasicIndex basicIndex = documentProcessor.getBasicIndex();
		QueryProcessor queryProcessor = new QueryProcessor(basicIndex, documentProcessor);
		Scanner scanner = new Scanner(System.in);
		while (true) {

			System.out.println("*** Menu ***");
			System.out.println("Total Words: " + basicIndex.getTotalWordCount());
            System.out.println("Unique Words: " + basicIndex.getUniqueWordCount());
			System.out.println("1. Boolean Retrieval");
			System.out.println("2. Ranked Retrieval");
			System.out.println("3. Indexed Documents");
			System.out.println("4. Indexed Tokens");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter Boolean query: ");
				String query = scanner.nextLine().trim();
				DocumentIDNode booleanResults = queryProcessor.ProcessANDOR(query);

				if (booleanResults == null) {
					System.out.println("No matching documents found.");
				} else {
					System.out.print("Matching Document IDs: ");
					while (booleanResults != null) {
						System.out.print(booleanResults.getDocID() + " ");
						booleanResults = booleanResults.getNext();
					}
					System.out.println();
				}
				break;

			case 2:
				System.out.print("Enter Ranked Retrieval query: ");
				String rankedQuery = scanner.nextLine().trim();
				NumNode rankedResults = queryProcessor.RankedRetrieval(rankedQuery);

				if (rankedResults == null) {
					System.out.println("No matching documents found.");
				} else {
					System.out.println("Ranked Results:");
					while (rankedResults != null) {
						System.out.println(
								"Document ID: " + rankedResults.getDocID() + " - Score: " + rankedResults.getScore());
						rankedResults = rankedResults.getNext();
					}
				}
				break;

			case 3:
			    System.out.println("\n*** all documents ***");
			    documentProcessor.getBasicIndex().displayAllDocuments();
			    break;


			case 4: 
			    System.out.println("\n*** Indexed Words ***");
			    WordNode wordNode = basicIndex.getWordHead(); // Get the head of the word list
			    while (wordNode != null) {
			        System.out.print("Word: " + wordNode.getWord() + " Document IDs: ");
			        DocumentIDNode docIDNode = wordNode.getDocumentIDs();
			        while (docIDNode != null) {
			            System.out.print(docIDNode.getDocID());
			            docIDNode = docIDNode.getNext();
			        }
			        System.out.println();
			        wordNode = wordNode.getNext();
			    }
			    break;

			case 5:
				System.out.println("Exiting the program");
				return;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
