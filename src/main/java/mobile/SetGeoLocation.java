package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

public class SetGeoLocation {

    /**
     * Emulation.setGeolocationOverride #
     * Overrides the Geolocation Position or Error.
     * Omitting any of the parameters emulates position unavailable.
     *
     * */
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map<String, Object> coordinates = new HashMap<String, Object>();
        coordinates.put("latitude",40);
        coordinates.put("longitude", 3);
        coordinates.put("accuracy", 1);

        driver.executeCdpCommand("Emulation.setGeolocationOverride",coordinates);

        driver.navigate().to("http://google.com");
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        Thread.sleep(2000);
        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String text = driver.findElement(By.cssSelector("h1[data-uia='hero-title']")).getText();
        System.out.println(text);

    }
}
