package com.test.utils;
import com.test.constants.SourcePath;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {
public static String getConfigProperty(String propertyName) {
		
		Properties properties = new Properties();
		String filePath =SourcePath.CONFIG_PROPERTIES_PATH;
		
		FileInputStream inputfile = null;
		try {
			inputfile = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = null;
		
		try {
			properties.load(inputfile);
			value = properties.getProperty(propertyName);
			System.out.println("Property we got from the file is::" +value );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				inputfile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// TODO Auto-generated method stub
		return value;
	}

}
