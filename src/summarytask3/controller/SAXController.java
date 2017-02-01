package summarytask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import summarytask3.constants.Constants;
import summarytask3.constants.XML;
import summarytask3.entity.Computer;
import summarytask3.entity.Device;
import summarytask3.entity.Type;

/**
 * Controller for SAX parser.
 *
 */
public class SAXController extends DefaultHandler {

	private String xmlFileName;

	private String currentElement;

	private Computer computer;

	private Device device;

	private Type type;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	};

	public Computer getComputer() {
		return computer;
	}

	// ///////////////////////////////////////////////////////////
	// CONTENT HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.COMPUTER.equalsTo(currentElement)) {
			computer = new Computer();
			return;
		}

		if (XML.DEVICE.equalsTo(currentElement)) {
			device = new Device();
			device.setCritical(Boolean.parseBoolean(attributes.getValue(uri, XML.CRITICAL.value())));
			device.setPrice(Integer.parseInt(attributes.getValue(uri, XML.PRICE.value())));
			return;
		}

		if (XML.TYPE.equalsTo(currentElement)) {
			type = new Type();
			type.setPeripheral(Boolean.parseBoolean(attributes.getValue(uri, XML.IS_PERIPHERAL.value())));
			type.setHasCooler(Boolean.parseBoolean(attributes.getValue(uri, XML.HAS_COOLER.value())));
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String elementText = new String(ch, start, length).trim();

		// return if content is empty
		if (elementText.isEmpty()) {
			return;
		}

		if (XML.NAME.equalsTo(currentElement)) {
			device.setName(elementText);
			return;
		}

		if (XML.ORIGIN.equalsTo(currentElement)) {
			device.setOrigin(elementText);
			return;
		}

		if (XML.ENERGY_CONCUMPTION.equalsTo(currentElement)) {
			type.setEnergyConsumption(Integer.parseInt(elementText));
			return;
		}

		if (XML.GROUP.equalsTo(currentElement)) {
			type.setGroup(elementText);
			return;
		}

		if (XML.PORT.equalsTo(currentElement)) {
			type.setPort(elementText);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (XML.DEVICE.equalsTo(localName)) {
			computer.getDeviceList().add(device);
			return;
		}

		if (XML.TYPE.equalsTo(localName)) {
			device.setType(type);
			return;
		}
	}
}
