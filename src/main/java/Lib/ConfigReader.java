package Lib;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

    public class ConfigReader {

        public static String ipf(String key) {
            Properties property = new Properties();
            try {
                FileInputStream intstm = new FileInputStream("C:\\Users\\PavanKumarBi_2vwp2tq\\IdeaProjects\\Solutions\\config\\config.properties");
                property.load(intstm);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return property.getProperty(key);
        }
//        @Test
//        public void getREAD(){
//            System.out.println( ConfigReader.ipf("BrowserType"));
//        }
    }

