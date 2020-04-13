//package com.PlaylistApplication.SeleniumTest;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;
//
//public class PlaylistApplicationSelTests {
//	
//	
//	@Test
//	public void testCreatePlaylistUI() {
//		
//		System.setProperty("webdriver.chrome.driver", "src/main/java/com/PlaylistApplication/WebDriver/chromedriver");
//		
//		WebDriver driver = new ChromeDriver();		
//		driver.manage().window().maximize();
//		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		driver.get("http://34.244.56.233:3000");
//		System.out.println(driver.getTitle());
//		
//		WebElement createPlaylistBtn = driver.findElement(By.className("btn1"));
//		createPlaylistBtn.click();
//		
//		driver.findElement(By.id("name")).sendKeys("Petrit's playlist");
//		driver.findElement(By.id("description")).sendKeys("This is my playlist for jogging");
//		
//		driver.close();
//	}
//
//}
