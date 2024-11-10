package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate; 
import java.time.LocalTime;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Wrappers {
   /*
    * Write your selenium wrappers here
    */

   public static void sendkeys(WebElement ele, String text) {
      
      ele.clear();
      ele.sendKeys(text);
   }

   public static void radiobutton(WebDriver driver, String text)  {
      
      WebElement radiobutton = driver.findElement(By.xpath("//span[contains(@class,'OvPDhc') and contains(text(),'"
            + text + "')]/../../..//div[@class='vd3tt']"));
      radiobutton.click();
   }

   public static void checkbox(WebDriver driver, String text) {
      
      WebElement checkbox = driver.findElement(By.xpath("//span[text()='"+ text +"']/../../../div"));
      checkbox.click();
   }

   public static void addressdropdown(WebDriver driver, String text) throws InterruptedException {
      
      WebElement addressvalue = driver.findElement(By.xpath("//div[@role='option']/span[text()='"+text+"']"));
      Thread.sleep(2000);
      addressvalue.click();
      Thread.sleep(2000);
   }

   public static String sevenDaysAgoDate() {
      LocalDate currentdate = LocalDate.now();
      LocalDate sevenDaysBefore=currentdate.minusDays(7);
      DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/DD/YYYY");
      String formattedDate=sevenDaysBefore.format(formatter);
      return formattedDate;      
   }

   public static String currentTime() {
      LocalTime currentTime = LocalTime.now();
      DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm");
      String formattedTime=currentTime.format(formatter);
      return formattedTime;      
   }

}
