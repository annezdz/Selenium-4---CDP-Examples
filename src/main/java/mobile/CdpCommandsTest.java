package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Emulation.setDeviceMetricsOverride #
 * Overrides the values of device screen dimensions (window.screen.width,
 * window.screen.height, window.innerWidth,
 * window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
 * PARAMETERS
 * */

public class CdpCommandsTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

       DevTools devTools =  driver.getDevTools();
       devTools.createSession();

       Map<String, Object>deviceMetrics = new HashMap<String, Object>();
       deviceMetrics.put("width",600);
       deviceMetrics.put("height",1000);
       deviceMetrics.put("deviceScaleFactor",50);
       deviceMetrics.put("mobile",true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.xpath("//body/app-root[1]/nav[1]/button[1]/span[1]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(text(),'Library')]")).click();


    }
}
