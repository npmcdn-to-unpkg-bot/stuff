package mcrpg.app.spring;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
 
import java.io.IOException;
import java.io.InputStream;
 
public class SpringFXMLLoader
{
    public static Object load(String url, Class<?> controllerClass) throws IOException
    {
    	ApplicationContext context = AbstractJavaFxApplicationSupport.getApplicationContext();
        InputStream fxmlStream = null;
        try
        {
            fxmlStream = controllerClass.getResourceAsStream(url);
            Object instance = context.getBean(controllerClass);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(instance);
            return loader.load(fxmlStream);
        }
        finally
        {
            if (fxmlStream != null)
            {
                fxmlStream.close();
            }
        }
    }
}
