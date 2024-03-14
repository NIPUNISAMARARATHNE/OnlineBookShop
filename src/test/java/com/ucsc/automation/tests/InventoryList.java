package com.ucsc.automation.tests;

import com.ucsc.automation.pages.LoginBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class InventoryList extends LoginBase {

    @Test
    public void navigateTOInventoryList(){
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/aside[1]/div[1]/div[4]/div[1]/div[1]/nav[1]/ul[1]/li[3]/a[1]")).click();
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.automationstudy.site/admin/?page=inventory";
        assertEquals (actualURL, expectedURL, "invalid URL");
    }

    @Test
    public void searchBook() {
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Cypress");
        WebElement BookName = driver.findElement(By.xpath("//small[1]"));
        String actualBookName = BookName.getText();
        String expectedBookName = "Title: Cypress";
        assertEquals(actualBookName, expectedBookName, "invalid BookName");
    }
}
