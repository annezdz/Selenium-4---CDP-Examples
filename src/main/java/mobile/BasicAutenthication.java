package mobile;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAutenthication {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        //Predicate, consumer - consume and manipulate data


        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");

        //Cast Authentication

        ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo","bar"));

        driver.get("http://httpbin.org/basic-auth/foo/bar");

    }
}
