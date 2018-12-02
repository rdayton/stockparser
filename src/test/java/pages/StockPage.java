package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class StockPage {
    private String url;
    private static WebDriver driver;
    private Double currentPrice;
    private final String STOCK_URL = "https://finance.yahoo.com/";
    private static Logger log = Logger.getLogger(BasePage.class.getName());
    private Double highestPrice;
    private Double lowestPrice;
    private Double earningsPerShare;
    private String symbol;

    private WebElement searchBox;

    public StockPage(String symbol) {
        this.symbol = symbol;
    }

    public void openBrowser(){
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void goToHomePage(){
        driver.get(STOCK_URL);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public String getSymbol(){
        return symbol;
    }

    public void searchForStock(){
        String searchXpath = "//*[@id=\"fin-srch-assist\"]/input";
        WebElement searchBox = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchXpath)));
        searchBox.sendKeys(symbol);
        WebDriverWait keysEntered = new WebDriverWait(driver, 10);
        keysEntered.until(ExpectedConditions.textToBePresentInElementLocated(By.id("fin-srch-assist"), symbol));
        searchBox.submit();

    }

    public double getCurrentPrice(){
        String priceXpath = "//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]";
        WebElement price = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceXpath)));
        currentPrice = Double.parseDouble(price.getText());
        return currentPrice;
    }

    public void getHighAndLowForYear(){
        String priceRangeXpath = "//*[@id=\"quote-summary\"]/div[1]/table/tbody/tr[6]/td[2]";
        WebElement priceRangeElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceRangeXpath)));
        String prices = priceRangeElement.getText();
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
        String epsXpath = "//*[@id=\"quote-summary\"]/div[2]/table/tbody/tr[4]/td[2]/span";
        WebElement epsElement = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"quote-summary\"]/div[2]/table/tbody/tr[4]/td[2]/span")));
        earningsPerShare = Double.parseDouble(epsElement.getText());
        return earningsPerShare;
    }
}
