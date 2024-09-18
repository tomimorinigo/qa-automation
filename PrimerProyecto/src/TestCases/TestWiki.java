package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWiki {
	@Test(description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarBusquedaWikipedia() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Documents\\test-automation\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://wikipedia.org");
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		Assert.assertTrue(searchInput.isDisplayed());
		searchInput.sendKeys("Selenium");
		searchInput.submit();
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		System.out.println("Texto encontrado "+ tituloResultado.getText());
		Assert.assertTrue(tituloResultado.isDisplayed());
		driver.close();
	}
}