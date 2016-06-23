package settingXML;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by misha on 20.09.15.
 */
public class testMain {

    public static void main(String[] args ) throws ParserConfigurationException, TransformerException, IOException, SAXException, IllegalAccessException {

        SettingServer settingServerDefault = new SettingServer();
        WriteDefaultSettingXML writeDefaultSettingXML = new WriteDefaultSettingXML( settingServerDefault );
        writeDefaultSettingXML.createDefaulSetting();
        //SAXPars saxp = new SAXPars();
        //saxp.readXMLFile();

    }
}
