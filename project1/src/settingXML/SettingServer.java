package settingXML;

import java.io.File;

/**
 * Created by misha on 20.09.15.
 */
public class SettingServer {

    public static final String pathXML = "." + File.separator + "setting.xml";

    private int portServer ;
    private int gameMechanicsGameTime ;
    private int gameMechanicsStepTime ;

    public void setDefaultSetting() {
        this.portServer = 8080;
        this.gameMechanicsGameTime = 15 * 1000;
        this.gameMechanicsStepTime = 100;
    }
}
