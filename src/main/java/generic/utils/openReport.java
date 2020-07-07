package generic.utils;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class openReport {
    @Test
   public void report() {
        File htmlFile = new File("src/test/Reports/extentreports/ExtentReportResults.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

