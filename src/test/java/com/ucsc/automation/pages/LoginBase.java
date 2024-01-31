package com.ucsc.automation.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class LoginBase extends PageBase {
    @BeforeClass
    public void adminLogin() throws InterruptedException {
        System.out.println("Before class - Login");
        driver.get("https://www.automationstudy.site/admin/login.php");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("grouponeadmin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("group1_23#JKkki");
        driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();

        String homePageUrl = "https://www.automationstudy.site/admin/";
        Thread.sleep(4000);
        String actualCurrentURL= driver.getCurrentUrl();
        assertEquals(actualCurrentURL,homePageUrl, "Login Failed");
        System.out.println();

    }
}
