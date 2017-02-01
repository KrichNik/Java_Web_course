package summarytask3.controller;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import summarytask3.constants.XML;
import summarytask3.entity.Computer;
import summarytask3.entity.Device;
import summarytask3.entity.Type;

/**
 * Controller for StAX parser.
 *
 */
public class STAXController extends DefaultHandler {

	private String xmlFileName;

	private Computer computer;

	public Computer getComputer() {
		return computer;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
		Device device = null;

		Type type = null;

		String currentElement = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.COMPUTER.equalsTo(currentElement)) {
					computer = new Computer();
					continue;
				}

				if (XML.DEVICE.equalsTo(currentElement)) {
					device = new Device();
					Attribute attrPrice = startElement.getAttributeByName(new QName(XML.PRICE.value()));
					device.setPrice(Integer.parseInt(attrPrice.getValue()));
					Attribute attrCritical = startElement.getAttributeByName(new QName(XML.CRITICAL.value()));
					device.setCritical(Boolean.parseBoolean(attrCritical.getValue()));
					continue;
				}

				if (XML.TYPE.equalsTo(currentElement)) {
					type = new Type();
					Attribute attrPeripheral = startElement.getAttributeByName(new QName(XML.IS_PERIPHERAL.value()));
					type.setPeripheral(Boolean.parseBoolean(attrPeripheral.getValue()));
					Attribute attrHasCooler = startElement.getAttributeByName(new QName(XML.HAS_COOLER.value()));
					type.setHasCooler(Boolean.parseBoolean(attrHasCooler.getValue()));
				}
			}

			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.NAME.equalsTo(currentElement)) {
					device.setName(characters.getData());
					continue;
				}

				if (XML.ORIGIN.equalsTo(currentElement)) {
					device.setOrigin(characters.getData());
					continue;
				}

				if (XML.ENERGY_CONCUMPTION.equalsTo(currentElement)) {
					type.setEnergyConsumption(Integer.parseInt(characters.getData()));
					continue;
				}

				if (XML.GROUP.equalsTo(currentElement)) {
					type.setGroup(characters.getData());
					continue;
				}

				if (XML.PORT.equalsTo(currentElement)) {
					type.setPort(characters.getData());
				}
			}

			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.DEVICE.equalsTo(localName)) {
					computer.getDeviceList().add(device);
					continue;
				}

				if (XML.TYPE.equalsTo(localName)) {
					device.setType(type);
				}
			}
		}
		reader.close();
	}
}
