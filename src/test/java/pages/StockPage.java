package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class StockPage {
    private String url;
    private WebDriver driver;
    private Double currentPrice;
    private final String STOCK_URL = "https://finance.yahoo.com/";
    private static Logger log = Logger.getLogger(BasePage.class.getName());
    private Double highestPrice;
    private Double lowestPrice;
    private Double initialEps;

    private WebElement searchBox;

    public StockPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        //super(driver);
    }

    public void goToHomePage(){
        driver.get(STOCK_URL);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void searchForStock(String symbol){
        searchBox.sendKeys(symbol+Keys.ENTER);
    }

    public double getCurrentPrice(){
      WebElement price = driver.findElement(By.xpath("//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]"));
      currentPrice = Double.parseDouble(price.getText());
      return currentPrice;
    }

    public void getHighandLowForYear(){
        String prices = driver.findElement(By.xpath("//*[@id=\"quote-summary\"]/div[1]/table/tbody/tr[6]/td[2]")).getText();
        Integer mid = prices.indexOf("-");
        highestPrice = Double.parseDouble(prices.substring(0, mid-1));
        lowestPrice = Double.parseDouble(prices.substring(mid+1, prices.length()));
    }

    public Double calculatePercentBelowHigh(){
        return (highestPrice-currentPrice) / currentPrice * 100;
    }

    public Double calculatePercentAboveLow(){
        return (currentPrice-lowestPrice) / lowestPrice * 100;

    }

    public Double getEarningsPerShare(){
        WebElement eps = driver.findElement(By.xpath("//*[@id=\"quote-summary\"]/div[2]/table/tbody/tr[4]/td[2]/span"));
        initialEps = Double.parseDouble(eps.getText());
        return initialEps;
    }
}
