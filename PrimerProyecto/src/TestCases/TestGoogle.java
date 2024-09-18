package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGoogle {

	private WebDriver driver;

	@BeforeMethod
	public void Precondicion() {
		// System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
		driver = new FirefoxDriver();
		Reporter.log("Abrimos el navegador en www.google.com");
	}
	
	@AfterMethod
	public void PostCondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	@Test(description = "Validar que las busquedas en Google funcionan")
	public void ValidarBusquedaGoogle() throws Exception {

		driver.get("http://google.com");
		WebElement searchInput = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));

		Reporter.log("Verificamos que la caja de busqueda este desplegada");
		Assert.assertTrue(searchInput.isDisplayed());

		Reporter.log("Ingresamos el valor Messi");
		searchInput.sendKeys("Messi");

		Reporter.log("Presionamos Enter");
		searchInput.submit();

		WebElement tituloResultado = driver
				.findElement(By.xpath("//div[@role='heading'][normalize-space()='Lionel Messi']"));
		
		System.out.println("Texto encontrado " + tituloResultado.getText());

		Reporter.log("Verificamos que el titulo se ha desplegado");
		Assert.assertTrue(tituloResultado.isDisplayed(), "No se mostró el titulo");

		Reporter.log("Verificamos que contenga la palabra Messi");
		Assert.assertEquals(tituloResultado.getText(), "Lionel Messi", "El valor Messi no fue mostrado");

		Reporter.log("Cerramos el navegador");
	}
}
