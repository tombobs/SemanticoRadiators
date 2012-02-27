//This class takes in an *.xml file, splits into individual commit objects and then returns a list of those objects

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

	private static List<CommitObject> commitObjects = new ArrayList<CommitObject>();

	public XMLParser(String path) throws Exception {
		File file = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		String author = null;
		String date = null;
		String message = null;

		NodeList nList = doc.getElementsByTagName("logentry");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				author = getTagValue("author", eElement);
				date = getTagValue("date", eElement);
				message = getTagValue("msg", eElement);
			}
			
			makeCommitObject(author,date,message);
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);
		
		//Had a problem with null values even though the message tag was there. This will return a null string if the message is null.
		if (nValue == null) {
			return "";
		}
		
		return nValue.getNodeValue();
	}
	
	//Takes variables created above and makes a commit object from them
	private static void makeCommitObject(String author, String date, String message) {
		CommitObject newCommit = new CommitObject(author,date,message);
		commitObjects.add(newCommit);
	}
	
	//This returns the object list
	public List<CommitObject> returnCommitObjects() {
		return commitObjects;
	}
}
