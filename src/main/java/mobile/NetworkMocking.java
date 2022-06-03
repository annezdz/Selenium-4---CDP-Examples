package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Fetch Domain - Intercept existent calls
         * A domain for letting clients substitute browser's network layer with client code.
         *
         *
         * */

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools =  driver.getDevTools();
        devTools.createSession();


        /**
         * Fetch.enable #
         * Enables issuing of requestPaused events.
         * A request will be paused until client calls one of failRequest,
         * fulfillRequest or continueRequest/continueWithAuth.
         * */

        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));

        /**
         * Fetch.requestPaused #
         * Issued when the domain is enabled and the request URL matches the specified filter.
         * The request is paused until the client responds with one of continueRequest, failRequest
         * or fulfillRequest.
         * */

        devTools.addListener(Fetch.requestPaused(), request ->
        {
            if(request.getRequest().getUrl().contains("shetty")) {
                String mockedUrl = request.getRequest().getUrl().replace("=shetty","=badGuy");
                System.out.println(mockedUrl);

                /**
                 * Fetch.continueRequest #
                 * Continues the request, optionally modifying some of its parameters.
                 * */

                devTools.send(Fetch.continueRequest(request.getRequestId(),
                        Optional.of(mockedUrl),
                        Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(request.getRequestId(),
                        Optional.of(request.getRequest().getUrl()),
                        Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty()));
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.xpath("//button[contains(text(),'Virtual Library')]")).click();
        Thread.sleep(3000);

        System.out.println(driver.findElement(By.cssSelector("p")).getText());



    }
}
