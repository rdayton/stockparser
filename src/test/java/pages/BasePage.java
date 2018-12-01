package pages;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BasePage {

    private WebDriver driver;
    protected String pageTitle;
    private static Logger log = Logger.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void navigateTo(String url){
        driver.get(url);
    }
}
