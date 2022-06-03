package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.devtools.v85.network.model.Response;

import java.util.Optional;

public class NetworkLogActivity {

    /**
     * Network.enable #
     * Enables network tracking, network events will now be delivered to the client.
     *
     * */
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        //Log file

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        /**
         * Network.responseReceived #
         * Fired when HTTP response is available.
         *
         * */

        devTools.addListener(Network.responseReceived(),response ->
        {
            Response response1 = response.getResponse();
            System.out.println(response1.getStatus());
            if(response1.getStatus().toString().startsWith("4")) {
                System.out.println(response1.getUrl() + " is failing with status code " + response1.getStatus());
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[contains(text(),'Virtual Library')]")).click();

        /**
         * Network.requestWillBeSent #
         * Fired when page is about to send HTTP request.
         * */

        devTools.addListener(Network.requestWillBeSent(), request ->
        {
            Request request1 = request.getRequest();
            System.out.println(request1.getUrl());
            System.out.println(request1.getHeaders());
        });

    }
}
