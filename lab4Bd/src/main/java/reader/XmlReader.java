/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reader;

import reactor.Reactor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;



/**
 *
 * @author annamutovkina
 */
public class XmlReader implements ReaderFile{

    @Override
    public ArrayList<Reactor> readFile(String path) {
        ArrayList<Reactor> reactorsList = new ArrayList<>();
        Reactor reactor = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    if (elementName.equals("Reactor")) {
                        reactor = new Reactor();
                    } else if (reactor != null) {
                        reactor.setSource("xml");
                        if (elementName.equals("type")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setType(xmlEvent.asCharacters().getData());
                        } else if (elementName.equals("burnup")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setBurnup(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        } else if (elementName.equals("kpd")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setKpd(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        } else if (elementName.equals("enrichment")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setEnrichment(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        } else if (elementName.equals("thermal_capacity")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setThermal_capacity(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        } else if (elementName.equals("electrical_capacity")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setElectrical_capacity(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        } else if (elementName.equals("life_time")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setLife_time(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        } else if (elementName.equals("first_load")) {
                            xmlEvent = reader.nextEvent();
                            reactor.setFirst_load(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        }
                    }
                } else if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Reactor") && reactor != null) {
                        reactorsList.add(reactor);
                        reactor = null;
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        return reactorsList;
    }

}
