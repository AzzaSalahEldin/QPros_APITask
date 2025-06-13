package ip.swagger.petstore.config;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Objects;

public class BaseTest {
    @BeforeSuite
    public void cleanAllureResults() {
        File allureResults = new File("allure-results");
        if (allureResults.exists()) {
            for (File file : Objects.requireNonNull(allureResults.listFiles())) {
                file.delete();
            }
        }
    }
}
