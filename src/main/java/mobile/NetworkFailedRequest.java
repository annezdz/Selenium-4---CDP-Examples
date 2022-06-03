package mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;
import org.openqa.selenium.devtools.v85.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v85.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class NetworkFailedRequest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Fetch.failRequest #
         * Causes the request to fail with specified reason.
         *
         * */

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();


        /**
         * Fetch.enable #
         * Enables issuing of requestPaused events.
         * A request will be paused until client calls one of failRequest,
         * fulfillRequest or continueRequest/continueWithAuth.
         * */

        Optional<List<RequestPattern>> patterns =
                Optional.of(List.of(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));

        devTools.send(Fetch.enable(patterns,Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request ->
        {
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.xpath("//button[contains(text(),'Virtual Library')]")).click();
        Thread.sleep(3000);

    }
}
