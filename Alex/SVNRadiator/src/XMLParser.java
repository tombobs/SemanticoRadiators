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
		
		//System.out.println("Root element :"+ doc.getDocumentElement().getNodeName());
		//System.out.println("-----------------------");
		
		NodeList nList = doc.getElementsByTagName("logentry");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				author = getTagValue("author", eElement);
				date = getTagValue("date", eElement);
				message = getTagValue("msg", eElement);
				//System.out.println("Author: " + getTagValue("author", eElement));
				//System.out.println("Date : " + getTagValue("date", eElement));
				//System.out.println("Message : " + getTagValue("msg", eElement));
				//System.out.flush();	//forces it to print everything out
			}
			makeCommitObject(author,date,message);
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);
		
		//Had a problem with null values even though the message was there. This will return a null string if the message is null.
		if (nValue == null) {
			return "";
		}

		return nValue.getNodeValue();
	}
	
	private static void makeCommitObject(String author, String date, String message) {
		CommitObject newCommit = new CommitObject(author,date,message);
		commitObjects.add(newCommit);
	}
	
	public List<CommitObject> returnCommitObjects() {
		return commitObjects;
	}
}
