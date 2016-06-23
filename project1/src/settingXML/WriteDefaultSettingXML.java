package settingXML;

/**
 * Created by misha on 20.09.15.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;

public class WriteDefaultSettingXML {


    private final Class classForWrite;
    private final Object objectForWrite;

    public WriteDefaultSettingXML( Object objectForWrite ) {
        this.classForWrite = objectForWrite.getClass();
        this.objectForWrite = objectForWrite;
    }

    public void createDefaulSetting() throws TransformerException, ParserConfigurationException, IllegalAccessException {
        Document doc = getDoc();
        Element root = createRootElements(doc);
        createFields( doc, root);
        writeFileXML(doc);
    }

    private Document getDoc () throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().newDocument();
        return doc;
    }

    private Element createRootElements ( Document doc ) {
        Element root = doc.createElement(classForWrite.getSimpleName());
        doc.appendChild(root);
        return root;
    }

    private void createFields ( Document doc, Element root) throws IllegalAccessException {
        Field[] fields = classForWrite.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Element child = doc.createElement(field.getName());
            root.appendChild(child);
            String test = field.getType().getName();
            child.setTextContent(field.get(objectForWrite).toString());
            child.setAttribute("type", test);
        }
    }

    private void writeFileXML( Document doc) throws TransformerException {

        File file = new File( SettingServer.pathXML  );
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));

    }
}
