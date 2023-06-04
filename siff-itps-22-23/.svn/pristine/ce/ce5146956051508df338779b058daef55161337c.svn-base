package Control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadPropertyFileFromClassPath {

    public Properties loadProperties(String fileName) {

        Properties prop = new Properties();

        try (InputStream inputStream = ReadPropertyFileFromClassPath.class.getClassLoader()
            .getResourceAsStream(fileName)) {

            // check for null
            if (inputStream == null) {
                System.out.println("Unable to find " + fileName + " file");
                return prop;
            }
            // load a properties file from class path
            prop.load(inputStream);

        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return prop;
    }
}
