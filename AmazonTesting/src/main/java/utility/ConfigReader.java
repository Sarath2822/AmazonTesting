package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties prop;

	public Properties initProperties() throws IOException {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resource\\config\\config.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println();
		}

		return prop;
	}
}
