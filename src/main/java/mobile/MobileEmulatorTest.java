package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Optional;

public class MobileEmulatorTest {

    /**
     *
     * Emulation.setDeviceMetricsOverride #
     * Overrides the values of device screen dimensions (window.screen.width,
     * window.screen.height, window.innerWidth,
     * window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
     * PARAMETERS
     * */

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();

        devTools.createSession();

        //send command to CDP Methods-> CDP Methods will invoke and get access to chrome dev tools
        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.xpath("//body/app-root[1]/nav[1]/button[1]/span[1]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(text(),'Library')]")).click();
        driver.close();
        //Network.getRequestPostData
    }
}
