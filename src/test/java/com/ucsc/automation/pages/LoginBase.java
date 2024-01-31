package com.ucsc.automation.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class PageBase {
    WebDriver driver;
    @BeforeClass
    public void adminLogin() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("grouponeadmin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("group1_23#JKkki");
        driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();

    }
}
