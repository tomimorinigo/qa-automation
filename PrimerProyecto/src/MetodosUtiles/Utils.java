package MetodosUtiles;

import org.testng.Reporter;

public class Utils {

	public static void escribir(String linea) {
		System.out.println(linea);
		Reporter.log(linea);
	}
}
