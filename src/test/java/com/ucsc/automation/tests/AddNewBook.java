package com.ucsc.automation.tests;

import com.ucsc.automation.pages.LoginBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.sql.RowId;
import java.time.Duration;
import java.time.LocalDateTime;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class AddNewBook extends LoginBase {

    String bookName = null;

    @Test
    public void navigateToBookList() {
        driver.findElement(By.xpath("//a[@class='nav-link nav-product']")).click();
        String actualTResult = driver.getCurrentUrl();
        String expectedResult = "https://www.automationstudy.site/admin/?page=product";
        assertEquals(actualTResult, expectedResult, "invalid URL");
        System.out.println("TC 1 = Navigated to the Book List Page");
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
        driver.findElement(By.xpath("//a[normalize-space()='Create New']")).click();
        WebElement categoryDropdownElement = driver.findElement(By.id("select2-category_id-container"));
        categoryDropdownElement.click();
        WebElement option = driver.findElement(By.xpath("/html[1]/body[1]/span[1]/span[1]/span[2]/ul[1]/li[4]"));
        option.click();

        WebElement subCategoryDropdownElement = driver.findElement(By.id("select2-sub_category_id-container"));
        subCategoryDropdownElement.click();
        WebElement option1 = driver.findElement(By.xpath("/html[1]/body[1]/span[1]/span[1]/span[2]/ul[1]/li[1]"));
        option1.click();

        bookName = "Secret Seven" + LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC);
        String author = "Saraa";
        driver.findElement(By.xpath("//textarea[@name='title']")).sendKeys(bookName);
        driver.findElement(By.xpath("//textarea[@name='author']")).sendKeys(author);
        driver.findElement(By.xpath("//div[@role='textbox']//p")).sendKeys("The book describes about children");

        WebElement statusDropdownElement = driver.findElement(By.id("status"));
        statusDropdownElement.click();
        WebElement statusOption = driver.findElement(By.xpath("//option[normalize-space()='Active']"));
        statusOption.click();

//        WebElement browseButton = driver.findElement(By.id("customFile"));
//        String filepath = "E:/Cakes/New folder/images.jpg";
//        browseButton.sendKeys(filepath);
//        saveButtonEle.click();

        String filepath = "E:/Cakes/New folder/images.jpg";
        driver.findElement(By.id("customFile")).sendKeys(filepath);
        driver.findElement(By.xpath("//button[@class='btn btn-flat btn-primary']")).click();

//        WebElement title = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[3]"));
//        String actualResult = title.getText();
//        String expectedResult = bookName;
//        assertEquals(actualResult, expectedResult, "not valid book");
//        System.out.println("TC 3 = Newly Added Book title = Secret seven");

        WebElement createdBookTitleElement = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[3]"));
        String actualCreatedBookTitleElementText = createdBookTitleElement.getText();
        String expectedBookTitle = bookName;
        assertEquals(actualCreatedBookTitleElementText, bookName);
        System.out.println("TC 3 = Newly Added Book title = Secret seven");
    }
    @Test
    public void searchNewlyAddedBook(){
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Secret Seven");
        WebElement authorName = driver.findElement(By.xpath("//tbody/tr[1]/td[4]"));
        String actualSearchResult = authorName.getText();
        String expectedSearchResult = "Saraa";
        assertEquals(actualSearchResult, expectedSearchResult, "Search result is not valid");
        System.out.println("TC 4 = Author is Saraa of the newly added book");
        driver.findElement(By.xpath("//input[@type='search']")).clear();

        driver.findElement(By.xpath("//input[@type='search']")).click();
    }

    @Test
    public void filterRecords() throws InterruptedException {
        WebElement showEntriesDropdown = driver.findElement(By.xpath("//select[@name='DataTables_Table_0_length']"));
        showEntriesDropdown.click();
        sleep(3000);
        WebElement filterValues = driver.findElement(By.xpath("//option[@value='25']"));
        filterValues.click();
        sleep(3000);

        WebElement showLoadedEntriesCount = driver.findElement(By.id("DataTables_Table_0_info"));
        String actualEntriesCount = showLoadedEntriesCount.getText();
        String expectedEntriesCount = "Showing 1 to 19 of 19 entries";
        assertEquals(actualEntriesCount,expectedEntriesCount, "Valid entries count is not displayed");
        System.out.println("TC 5 = Showing 1 to 10 of 19 entries");
    }

    @Test
    public void navigateEditBookPage (){
        WebElement actionDropdown = driver.findElement(By.xpath("//tbody/tr[1]/td[6]/button[1]"));
        actionDropdown.click();
        WebElement editOptionSelection = driver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[@class='dropdown-item'][normalize-space()='Edit']"));
        editOptionSelection.click();

        WebElement actualEditPageHeading = driver.findElement(By.xpath("//h3[contains(text(),'Update')]"));
        String actualResult = actualEditPageHeading.getText();
        String expectedResult = "Update Product";
        assertEquals(actualResult, expectedResult, "invalid page Header");
        System.out.println("TC 6 = Navigated to the Edit page");
    }

    @Test
    public void editSubCategoryAndDetailsOfBook(){
        Select drpCategory = new Select(driver.findElement(By.id("sub_category_id")));
        drpCategory.selectByVisibleText("Programming");
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys("  and Book Is written by Saraa");
        driver.findElement(By.xpath("//button[@class='btn btn-flat btn-primary']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Secret seven");
        WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[3]"));
        String actualTitle = bookTitle.getText();
        String expectedTitle = bookName;
        assertEquals(actualTitle,expectedTitle, "Invalid Title");
        driver.findElement(By.xpath("//input[@type='search']")).clear();
        driver.findElement(By.xpath("//input[@type='search']")).click();
        System.out.println("TC 7 = Edited Book search in book list");
    }

    @Test
    public void deleteBook () throws InterruptedException {
        driver.findElement(By.xpath("//tbody/tr[1]/td[6]/button[1]")).click();
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[@class='dropdown-item delete_data'][normalize-space()='Delete']")).click();
        driver.findElement(By.xpath("//button[@id='confirm']")).click();
    }
}

