public class BasicIndex {
    private DocumentNode Head; 
    private WordNode WordHead;
    private int totalWordCount;

    public BasicIndex() {
        Head = null;
        WordHead = null;
    }

    public WordNode getWordHead() {
		return WordHead;
	}

	public void setWordHead(WordNode wordHead) {
		WordHead = wordHead;
	}

	public DocumentNode getHead() {
        return Head;
    }
 
    //*Big-O: O(n + m)
    public void AddDocument(int docID, String[] words) {
        DocumentNode NewDoc = new DocumentNode(new Document(docID, String.join(" ", words)));

        
        for (String word : words) {
        	WordNode WordNode = AddWordNode(word); 
            WordNode.addDocumentID(docID);
            totalWordCount++;
        }

       
        if (Head == null) {
            Head = NewDoc;
        } else {
            DocumentNode current = Head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(NewDoc);
        }
    }

 // Big-O: O(n)
    private WordNode AddWordNode(String word) {
    	WordNode Current = WordHead; 
        while (Current != null) {
            if (Current.getWord().equals(word)) {
                return Current; 
            }
            if (Current.getNext() == null) break;
            Current = Current.getNext();
        }

     
        WordNode NewNode = new WordNode(word);
        if (Current == null) {
            WordHead = NewNode; 
        } else {
            Current.setNext(NewNode); 
        }
        return NewNode;
    }

 
    // Big-O: O(n)
    public String[] GetWordsinDocument(int docID) {
        DocumentNode Current = Head;
        while (Current != null) {
            if (Current.getDocument().getDocumentId() == docID) {
                return Current.getDocument().getContent().split("\\s+");
            }
            Current = Current.getNext();
        }
        return null; 
    }


 // Big-O: O(n)
    public DocumentIDNode GetDocuments(String word) {
    	WordNode current = WordHead; 
        while (current != null) {
            if (current.getWord().equals(word)) {
                return current.getDocumentIDs(); 
            }
            current = current.getNext();
        }
        return null; 
    }


    
 // Big-O: O(n)
    public void displayAllDocuments() {
        DocumentNode current = Head;
        while (current != null) {
            System.out.print("Document ID: " + current.getDocument().getDocumentId());
            System.out.println(" Content: " + current.getDocument().getContent());
            current = current.getNext();
        }
    }
    public int getTotalWordCount() {
        return totalWordCount;
    }
    //big O(n)
    public int getUniqueWordCount() {
        int count = 0;
        WordNode current = WordHead;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
