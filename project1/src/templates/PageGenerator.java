package templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateBooleanModel;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Агент on 09.08.15.
 */
public class PageGenerator {
    private static final Configuration CFG = new Configuration();

    public static String getPage( Map<String, Object> pageVariables, String srcHtml, String pageHtml ) {
        Writer stream = new StringWriter();

        try {
         String path = srcHtml + File.separator + pageHtml;
         Template template =  CFG.getTemplate(  path );
         template.process( pageVariables, stream );
        } catch (IOException |TemplateException  e) {
            e.printStackTrace();
        }
        String streamString = stream.toString();

        return streamString;
    }

    public static TemplateBooleanModel wrapperBoolean ( boolean value ) {
        if ( value ) return TemplateBooleanModel.TRUE;
        return TemplateBooleanModel.FALSE;
    }
}