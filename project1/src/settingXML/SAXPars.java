package settingXML;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
/**
 * Created by misha on 20.09.15.
 */

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SAXPars extends DefaultHandler {

    private String thisElement = "";
    private SettingServer settingServer;
    private Class classSettingServer;
    private Map<String, String> valueType = new HashMap<>();
    private Map<String, String> valueXML = new HashMap<>();

    public SAXPars( SettingServer settingServer ) {
        super();
        this.settingServer = settingServer;
        classSettingServer = settingServer.getClass();
    }

    public void readXMLFile () throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File( SettingServer.pathXML ), this);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("item")) {
            String a= new String(ch, start, length);
            System.out.println("Value: " + a);
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }




    }
