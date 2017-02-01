package summarytask3.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import summarytask3.constants.Constants;
import summarytask3.constants.XML;
import summarytask3.entity.Computer;
import summarytask3.entity.Device;
import summarytask3.entity.Type;

/**
 * Controller for DOM parser.
 *
 */
public class DOMController {

	private String xmlFileName;

	private Computer computer;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Computer getComputer() {
		return computer;
	}

	public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		if (validate) {
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();
		db.setErrorHandler(new DefaultHandler() {

			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e;
			}

		});

		Document document = db.parse(xmlFileName);
		Element root = document.getDocumentElement();

		computer = new Computer();

		NodeList deviceNodes = root.getElementsByTagName(XML.DEVICE.value());
		for (int i = 0; i < deviceNodes.getLength(); i++) {
			Device device = getDevices(deviceNodes.item(i));
			computer.getDeviceList().add(device);
		}
	}

	private Device getDevices(Node dNode) {
		Device device = new Device();
		Element devElement = (Element) dNode;

		Node nameNode = devElement.getElementsByTagName(XML.NAME.value()).item(0);
		device.setName(nameNode.getTextContent());

		Node originNode = devElement.getElementsByTagName(XML.ORIGIN.value()).item(0);
		device.setOrigin(originNode.getTextContent());

		NodeList typeNodeList = devElement.getElementsByTagName(XML.TYPE.value());
		for (int i = 0; i < typeNodeList.getLength(); i++) {
			Type type = getType(typeNodeList.item(i));
			device.setType(type);
		}

		int price = Integer.parseInt(devElement.getAttribute(XML.PRICE.value()));
		device.setPrice(price);

		boolean critical = Boolean.valueOf(devElement.getAttribute(XML.CRITICAL.value()));
		device.setCritical(critical);

		return device;
	}

	private Type getType(Node typeNode) {
		Type type = new Type();
		Element typeElement = (Element) typeNode;

		Node energyConsumptionNode = typeElement.getElementsByTagName(XML.ENERGY_CONCUMPTION.value()).item(0);
		type.setEnergyConsumption(Integer.parseInt(energyConsumptionNode.getTextContent()));
		Node groupNode = typeElement.getElementsByTagName(XML.GROUP.value()).item(0);
		type.setGroup(groupNode.getTextContent());

		NodeList portNodeList = typeElement.getElementsByTagName(XML.PORT.value());
		for (int i = 0; i < portNodeList.getLength(); i++) {
			type.setPort(portNodeList.item(i).getTextContent());
		}

		boolean isPeripheral = Boolean.valueOf(typeElement.getAttribute(XML.IS_PERIPHERAL.value()));
		type.setPeripheral(isPeripheral);

		boolean hasCooler = Boolean.valueOf(typeElement.getAttribute(XML.HAS_COOLER.value()));
		type.setHasCooler(hasCooler);

		return type;
	}

	public static Document getDocument(Computer computer) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();
		Element rootElement = document.createElement(XML.COMPUTER.value());
		document.appendChild(rootElement);
		for (Device device : computer.getDeviceList()) {
			Element devElement = document.createElement(XML.DEVICE.value());
			rootElement.appendChild(devElement);

			Element nameElement = document.createElement(XML.NAME.value());
			nameElement.setTextContent(device.getName());
			devElement.appendChild(nameElement);

			Element originElement = document.createElement(XML.ORIGIN.value());
			originElement.setTextContent(device.getOrigin());
			devElement.appendChild(originElement);

			Type type = device.getType();
			Element typeElement = document.createElement(XML.TYPE.value());
			devElement.appendChild(typeElement);
			typeToDocument(document, type, typeElement);

			devElement.setAttribute(XML.PRICE.value(), String.valueOf(device.getPrice()));
			devElement.setAttribute(XML.CRITICAL.value(), String.valueOf(device.isCritical()));
		}
		return document;
	}

	private static void typeToDocument(Document document, Type type, Element typeElement) {
		Element energyConsumptionElement = document.createElement(XML.ENERGY_CONCUMPTION.value());
		energyConsumptionElement.setTextContent(String.valueOf(type.getEnergyConsumption()));
		typeElement.appendChild(energyConsumptionElement);

		Element groupElement = document.createElement(XML.GROUP.value());
		groupElement.setTextContent(type.getGroup());
		typeElement.appendChild(groupElement);

		for (String port : type.getPorts()) {
			Element portElement = document.createElement(XML.PORT.value());
			portElement.setTextContent(port);
			typeElement.appendChild(portElement);
		}

		typeElement.setAttribute(XML.IS_PERIPHERAL.value(), String.valueOf(type.isPeripheral()));
		typeElement.setAttribute(XML.HAS_COOLER.value(), String.valueOf(type.isHasCooler()));
	}

	public static void saveToXML(Computer computer, String xmlFileName)
			throws ParserConfigurationException, TransformerException {
		// Test -> DOM -> XML
		saveToXML(getDocument(computer), xmlFileName);
	}

	public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
		StreamResult result = new StreamResult(new File(xmlFileName));
		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		// run transformation
		t.transform(new DOMSource(document), result);
	}

	public static void main(String[] args) {
		DOMController domContr = new DOMController(Constants.INPUT_XML_FILE);
		try {
			domContr.parse(true);
		} catch (SAXException ex) {
			System.err.println("XML not valid");
		} catch (ParserConfigurationException | IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("XML valid");
		System.out.print("Here is the test: \n" + domContr.getComputer());
	}
}
