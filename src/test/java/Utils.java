import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Utils {
    public static void SetEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }
    public static int generateRandomiD(int max, int min){
        double randomID= Math.random()*(max-min)+min;
        return (int) randomID;
    }
    public static String generateRandomniD() {
        int minNID = 100000000;
        int maxNID = 999999999;
        int randomNID = (int) (Math.random() * (maxNID - minNID + 1) + minNID);
        return String.valueOf(randomNID);
    }


}
