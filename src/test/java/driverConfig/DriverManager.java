package driverConfig;

import constants.Navegador;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.BasePage;

import java.io.File;

public class DriverManager {
	private WebDriver driver;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private String extensionDriver = "";

	protected void resolveDriver(Navegador nav, String ambURL) {
		File driverPath;
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("\nSistema operativo ->" + System.getProperty("os.name").toLowerCase() + " " +System.getProperty("os.version").toLowerCase() +"\n");
		if (!os.contains("mac"))
			this.extensionDriver = ".exe";
		System.out.println(nav);
		switch (nav) {
            /*case Chrome:
                System.out.println("Se selecciona Chrome");
                ChromeOptions chromeOptions = new ChromeOptions();
                if (os.contains("linux")){
                    System.out.println("entre a linux");
                    System.out.println(System.getProperty("user.name"));
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--ignore-ssl-errors=yes");
                    chromeOptions.addArguments("--window-size=1920x1080");
                }
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--remote-allow-origins=*");
                this.driver = new ChromeDriver(chromeOptions);
                this.driver.manage().deleteAllCookies();
                break;*/
			case Chrome:
				ChromeOptions chromeOptions = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", "./prerrequisitos/driver/chromedriver.exe");
				chromeOptions.addArguments("--remote-allow-origins=*");
				this.driver = new ChromeDriver(chromeOptions);
				this.driver.manage().deleteAllCookies();
				break;

			case Firefox:
				System.out.println("Se selecciona Firefox");
				WebDriverManager.firefoxdriver().setup();
				this.driver = new FirefoxDriver();
				this.capabilities.setBrowserName("Firefox");
				break;
			case Edge:
				System.out.println("Se selecciona Edge");
				WebDriverManager.edgedriver().setup();
				this.driver = new EdgeDriver();
				this.capabilities.setBrowserName("Microsoft Edge");
				break;
			default:
				System.out.println("No es posible lanzar el navegador, no se reconoce el navegador: " + nav);
				break;
		}
		this.driver.manage().window().maximize();
		this.driver.get(ambURL);
		BasePage.esperar(5);

	}


	protected WebDriver getDriver() {
		if (driver == null) {
			return driver;
		} else {
			return driver;
		}

	}
}
