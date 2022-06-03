package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.network.Network;
import org.openqa.selenium.devtools.v100.network.model.ConnectionType;

import java.util.Optional;

public class NetworkSpeed {
    public static void main(String[] args) {
        /**
         *
         * Network.emulateNetworkConditions #
         * Activates emulation of network conditions.
         *
         *
         * */

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        //Log file

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(org.openqa.selenium.devtools.v85.network.Network.enable(Optional.empty(),
                Optional.empty(), Optional.empty()));

        devTools.send(Network
                .emulateNetworkConditions
                        (false,4000,20000,100000, Optional.of(ConnectionType.ETHERNET)));

        devTools.addListener(Network.loadingFailed(), response ->
        {
            System.out.println(response.getRequestId());
            System.out.println(response.getErrorText());
            System.out.println(response.getTimestamp());
        });
        long startTime = System.currentTimeMillis();
//        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
//        driver.findElement(By.xpath("//button[contains(text(),'Virtual Library')]")).click();


        driver.navigate().to("http://google.com");
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String text = driver.findElement(By.cssSelector("h1[data-uia='hero-title']")).getText();
        System.out.println(text);

        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));
        driver.close();
        //Time: 35771

        //Time: 23035
        //Netflix test 1 - Time: 249319
        //Netflix teste 2 - Time: 31135

        /**
         * Network.loadingFailed #
         * Fired when HTTP request has failed to load.
         * */
    }
}
