package pages;

import cucumber.api.java.an.E;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.TestRunner;

public class StockPage {
    private String url;
    private static WebDriver driver;
    private Double currentPrice;
    private final String STOCK_URL = "https://finance.yahoo.com/";
    private Double highestPrice;
    private Double lowestPrice;
    private Double earningsPerShare;
    private String symbol;

    private WebElement searchBox;

    public StockPage(String symbol) {
        this.symbol = symbol;
        this.driver = TestRunner.driver;
    }

    public void openBrowser(){
        //driver = new ChromeDriver();
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
        final String searchSelector = "#fin-srch-assist > input";
        final String searchId = "fin-srch-assist";
        WebElement searchBox = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(searchSelector)));
        searchBox.sendKeys(symbol);

        //prevent search from starting before all keys are entered
        WebDriverWait keysEntered = new WebDriverWait(driver, 10);
        keysEntered.until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector(searchSelector), symbol));
        searchBox.submit();

    }

    public void setCurrentPrice(){
        final String priceXpath = "//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]";
        WebElement price = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceXpath)));

        //data can briefly appear as N/A, so wait until it is not N/A to get the current price
        WebDriverWait waitForData = new WebDriverWait(driver, 5);
        //use xpath because element may have been replaced in the DOM when it was updated
        waitForData.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath(priceXpath), "N/A")));
        currentPrice = Double.parseDouble(price.getText().replaceAll(",", ""));

    }

    public Double getCurrentPrice(){
        return currentPrice;
    }

    public void setHighAndLowForYear(){
        final String priceRangeXpath = "//*[@id=\"quote-summary\"]/div[1]/table/tbody/tr[6]/td[2]";
        WebElement priceRangeElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceRangeXpath)));

        //data can briefly appear as N/A, so wait until it is not N/A to get the current price
        WebDriverWait waitForData = new WebDriverWait(driver, 5);
        //use xpath because element may have been replaced in the DOM when it was updated
        waitForData.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath(priceRangeXpath), "N/A")));
        String prices = priceRangeElement.getText();
        int mid = prices.indexOf("-");
        lowestPrice = Double.parseDouble(prices.substring(0, mid-1).replaceAll(",", ""));
        highestPrice = Double.parseDouble(prices.substring(mid+1, prices.length()).replaceAll(",", ""));
    }

    public Double getHigh(){
        return highestPrice;
    }

    public Double getLow(){
        return lowestPrice;
    }

    public Integer calculatePercentBelowHigh(){
        int percent = (int) Math.rint((highestPrice-currentPrice) / highestPrice * 100);
        //percent.intValue();
        return percent;
    }

    public Integer calculatePercentAboveLow(){
        int percent = (int) Math.rint((currentPrice-lowestPrice) / lowestPrice * 100);
        return percent;

    }

    public Double setEarningsPerShare(){
        final String epsXpath = "//*[@id=\"quote-summary\"]/div[2]/table/tbody/tr[4]/td[2]/span";
        WebElement epsElement = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id=\"quote-summary\"]/div[2]/table/tbody/tr[4]/td[2]/span")));

        //data can briefly appear as N/A, so wait until it is not N/A to get the current price
        WebDriverWait waitForData = new WebDriverWait(driver, 5);
        //use xpath because element may have been replaced in the DOM when it was updated
        waitForData.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath(epsXpath), "N/A")));
        earningsPerShare = Double.parseDouble(epsElement.getText());

        return earningsPerShare;

    }

    public Double getEarningsPerShare(){
        return earningsPerShare;
    }
}
