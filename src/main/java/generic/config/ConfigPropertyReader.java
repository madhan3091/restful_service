package generic.config;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class ConfigPropertyReader {
    static InputStream inputStream;
    public static Properties prop = new Properties();

    public static Properties readConfig(String propertyFile) {
        try {
//            String propFileName = "config.properties";
            String propFileName = propertyFile;
            inputStream = ConfigPropertyReader.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {

                prop.load(inputStream);
                return prop;
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


        } catch (
                IOException ex) {

            ex.printStackTrace();
        }

        return null;
    }
}
