package com.ucsc.automation.tests;

import com.ucsc.automation.pages.LoginBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddNewBook extends LoginBase {

    @Test
    public void navigateToBookList() {
        driver.findElement(By.xpath("//a[@class='nav-link nav-product']")).click();
        String actualTResult = driver.getCurrentUrl();
        String expectedResult = "https://www.automationstudy.site/admin/?page=product";
        assertEquals(actualTResult, expectedResult, "invalid URL");
        System.out.println("TC = 1= Navigated to the Book List Page");
    }

    @Test
    public void checkPageHeader(){
        WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='List of Books']"));
        String actualText = element.getText();
        String expectedText= "List of Books";
        assertEquals(actualText, expectedText,"Invalid Header");
        System.out.println("TC 2 = Header = List Of Books");
    }

    @Test
    public void createNewBook() throws InterruptedException {
//        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[normalize-space()='Create New']")).click();
        Thread.sleep(2000);
        WebElement dropdownElement = driver.findElement(By.id("select2-category_id-container"));
        dropdownElement.click();
//        WebElement option = driver.findElement(By.xpath("//*[@id=\"select2-category_id-result-w588-1\"]"));
        WebElement option = driver.findElement(By.id("select2-category_id-result-81ds-1"));
        option.click();
    }
}
