package summarytask3;

import java.io.IOException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import summarytask3.constants.Constants;
import summarytask3.controller.DOMController;
import summarytask3.controller.SAXController;
import summarytask3.controller.STAXController;
import summarytask3.entity.Computer;
import summarytask3.util.Sorter;

public class Main {

	private static final String EOL = System.lineSeparator();

	public static void main(String[] args) {
		try {
			execute(args[0]);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException
				| XMLStreamException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void execute(String xmlFileName)
			throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException {
		System.out.println("Input ==> " + xmlFileName + EOL);

		/*********************************************************
		 * DOM
		 *********************************************************/
		System.out.println("DOM");
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		Computer computer = domController.getComputer();
		Sorter.sortDevicesByDeviceName(computer);
		System.out.println("Sort by devices name:");
		System.out.println(computer);

		String outputXmlFile = Constants.OUTPUT_DOM_XML_FILE;
		DOMController.saveToXML(computer, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile + EOL);

		/*********************************************************
		 * SAX
		 *********************************************************/
		System.out.println("SAX");
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		computer = saxController.getComputer();
		Sorter.sortDevicesByPrice(computer);
		System.out.println("Sort by devices price:");
		System.out.println(computer);

		outputXmlFile = "output.sax.xml";
		DOMController.saveToXML(computer, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile + EOL);

		/*********************************************************
		 * StAX
		 *********************************************************/
		System.out.println("StAX");
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		computer = staxController.getComputer();
		Sorter.sortDevicesByDeviceOrigin(computer);
		System.out.println("Sort by device origins name:");
		System.out.println(computer);

		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(computer, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
	}

}
