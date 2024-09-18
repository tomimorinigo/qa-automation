package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MetodosUtiles.Utils;

public class TestDemoBlaze {

	private final String USERNAME = "admin";
	private final String PASSWORD = "admin";
	private WebDriver driver;
	private WebDriverWait wait;

	
	@BeforeMethod
	public void Precondicion() {
		// System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Utils.escribir("Abrimos el navegador en www.google.com");
	}
	
	@AfterMethod
	public void PostCondicion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	
	@Test(description = "Validar que el sitio logee correctamente")
	public void ValidarLogin() throws Exception {
		// Abrimos la pagina web
		Utils.escribir("Abrimos la pagina web www.demoblaze.com");
		driver.get("https://www.demoblaze.com/index.html");
		
		// Tomamos el boton para el login
		Utils.escribir("Tomamos el boton para el login y chequeamos que esté visible");
		WebElement loginButton = driver.findElement(By.xpath("//a[@id='login2']"));
		Assert.assertTrue(loginButton.isDisplayed());
		Utils.escribir("Hacemos click en el boton Login");
		loginButton.click();
		
		// Tomamos los inputs
		Utils.escribir("Tomamos los inputs para insertar username y password");
		WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));

		
		// Rellenamos con datos validos
		Utils.escribir("Rellenamos los campos con datos validos");
		usernameInput.sendKeys(USERNAME);
		passwordInput.sendKeys(PASSWORD);
		
		// Enviamos el formulario
		Utils.escribir("Hacemos click en el boton Submit");
		WebElement submitLogin = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
		submitLogin.click();
		
		// Comprobamos que se haya iniciado con nuestro usuario ingresado
		Utils.escribir("Obtenemos el nombre del usuario iniciado");
		WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

		String welcomeText = userElement.getText();

		// Divide el texto para obtener solo el nombre de usuario
		String actualUsername = welcomeText.replace("Welcome ", "");
		
		// Compara el nombre de usuario actual con el esperado
		Utils.escribir("Comparamos el username ingresado por el esperado");
		Assert.assertEquals(actualUsername, USERNAME);
	}
}
