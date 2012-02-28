//This class takes in an *.xml file, splits into individual commit objects and then returns a list of those objects
//A class for taking an *.xml file output from SVN command line
//and splitting it into individual objects

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
		String projectPath = null;
		String projectName = null;

		NodeList nList = doc.getElementsByTagName("logentry");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				author = getTagValue("author", eElement);
				date = getTagValue("date", eElement);
				message = getTagValue("msg", eElement);
				NodeList paths = eElement.getElementsByTagName("paths");

				if (paths.getLength() > 0) {

					Element pathsEl = (Element) paths.item(0);
					projectPath = getTagValue("path", pathsEl);
				}

				StringTokenizer splitter = new StringTokenizer(projectPath, "/");
				projectName = splitter.nextToken();

				CommitObject newCommit = new CommitObject(projectName, author,
						date, message);
				commitObjects.add(newCommit);
			}
		}
	}

	private static String getTagValue(String sTag, Element eElement) {

		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		// Had a problem with null values even though the message tag was there.
		// This will return a null string if the message is null.
		if (nValue == null) {
			return "";
		}

		return nValue.getNodeValue();
	}

	// This returns the object list
	public List<CommitObject> returnCommitObjects() {
		
		return commitObjects;
	}
}
