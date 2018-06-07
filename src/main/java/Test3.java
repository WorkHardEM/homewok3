import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\ChromeDriver\\chromedriver.exe");
        EventFiringWebDriver driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.register(new EventHandler());

        login(driver);
        createdCategorie(driver);
        searchCategory(driver);

        driver.quit();
    }

    public static void login(WebDriver driver) throws InterruptedException {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("webinar.test@gmail.com");
        WebElement passwordField = driver.findElement(By.name("passwd"));
        passwordField.sendKeys("Xcg7299bnSmMuRLp9ITw");
        WebElement loginButton = driver.findElement(By.name("submitLogin"));
        loginButton.click();
    }

    public static void createdCategorie(WebDriver driver) throws InterruptedException {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement catalog = driver.findElement(By.xpath(".//*[@id='subtab-AdminCatalog']/a"));
        action.moveToElement(catalog).perform();
        TimeUnit.SECONDS.sleep(5);
        WebElement categories = driver.findElement(By.xpath(".//*[@id='subtab-AdminCategories']/a"));
        categories.click();
        TimeUnit.SECONDS.sleep(3);
        WebElement addCategorie = driver.findElement(By.cssSelector(".process-icon-new"));
        addCategorie.click();
        TimeUnit.SECONDS.sleep(3);
        WebElement nameField = driver.findElement(By.cssSelector("#name_1"));
        nameField.sendKeys("New Category For Test");
        WebElement saveButton = driver.findElement(By.cssSelector("#category_form_submit_btn"));
        saveButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='content']/div[3]/div")));
    }

    public static void searchCategory(WebDriver driver) {
        String nameNewCategory = "New Category For Test";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement filterName = driver.findElement(By.name("categoryFilter_name"));
        filterName.sendKeys("New Category For Test", Keys.RETURN);
        wait.until(ExpectedConditions.textToBe(By.xpath(".//*[@class='pointer']"), nameNewCategory));
    }
}