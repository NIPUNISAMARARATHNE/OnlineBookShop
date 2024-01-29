package com.ucsc.automation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class AdminPanelLogin {

        WebDriver driver;


        @Test
        public void launchAdmin(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://www.automationstudy.site/admin/login.php");
            String ExpectedURL=("https://www.automationstudy.site/admin/login.php");
            String ActualURL= driver.getCurrentUrl();
            assertEquals(ExpectedURL, ActualURL, "not valid");
        }
        @Test
        public void validationMSgCheckOnlyClickingSignInButton() throws InterruptedException {
            driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
            WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-danger text-white err_msg']"));
            String expectedResult = "Incorrect username or password";
            String actualResult = element.getText();
            assertEquals(actualResult, expectedResult, "invalid msg content" );
            System.out.println(actualResult);
            System.out.println("TC 1 = Empty username & password");
            System.out.println();

        }
        @Test
        public void validationMSGCheckWithInvalidPWD() throws InterruptedException {
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("grouponeadmin");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("group1_23#JKkkit");
            driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
            WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-danger text-white err_msg']"));
            String expectedResult = "Incorrect username or password";
            String actualResult = element.getText();
            assertEquals(actualResult, expectedResult, "invalid msg content");
            System.out.println(actualResult);
            System.out.println("TC 2 = invalid password use to login");
            System.out.println();
        }

        @Test
        public void validationMSGCheckWithInvalidUsername() throws InterruptedException {
            driver.navigate().refresh();
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("grouponeadmin1");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("group1_23#JKkki");
            driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
            WebElement element=  driver.findElement(By.xpath("//div[@class='alert alert-danger text-white err_msg']"));
            String expectedResult= "Incorrect username or password";
            String actualResult= element.getText();
            assertEquals(actualResult,expectedResult, "invalid msg content");
            System.out.println(actualResult);
            System.out.println("TC 3 = invalid username use to login");
            System.out.println();

        }


        @Test
        public void adminLogin() throws InterruptedException {
            driver.navigate().refresh();
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("grouponeadmin");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("group1_23#JKkki");
            driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
            String expectCurrentURL = "https://www.automationstudy.site/admin/";
            Thread.sleep(4000);
            String actualCurrentURL= driver.getCurrentUrl();
            assertEquals(actualCurrentURL,expectCurrentURL, "invalid URL");

            String actualTitle = driver.getTitle();
            String expectTitle = "Online Book Shop";
            assertEquals(actualTitle, expectTitle, "Title mismatched");
            System.out.println("Title - Online Book Shop");


            System.out.println("Current URL = https://www.automationstudy.site/admin/");

            System.out.println("TC 4 = Correct username & correct password");
            System.out.println("Successfully Log in to the admin panel");
            System.out.println();
        }

        @Test
        public void verifyLogin() throws InterruptedException {
            WebElement element = driver.findElement(By.xpath("//a[normalize-space()='Online Book Shop - Admin']"));
            String expectedResult= "Online Book Shop - Admin";
            String actualResult= element.getText();
            assertEquals(actualResult, expectedResult, "Title name is not correct");
            System.out.println("TC 5 = Admin Panel Heading Name = Online Book Shop - Admin");
        }

        @Test
        public void logout() throws InterruptedException {
            driver.findElement(By.xpath("//button[@class='btn btn-rounded badge badge-light dropdown-toggle dropdown-icon']")).click();
            driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
            Thread.sleep(4000);
            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.automationstudy.site/admin/login.php";
            assertEquals(actualUrl, expectedUrl, "Not login URl");
            System.out.println("TC 6 = After Logout Login URL = https://www.automationstudy.site/admin/login.php ");
        }

        @Test
        public void navigateTOWebsiteAfterLogout() throws InterruptedException {
            Thread.sleep(4000);
            driver.findElement(By.xpath("//a[normalize-space()='Go to Website']")).click();
            String actualURL = driver.getCurrentUrl();
            String expectedURL = "https://www.automationstudy.site/";
            assertEquals(actualURL,expectedURL, "invalid admin login page url");

//      driver.quit();
        }
    }


