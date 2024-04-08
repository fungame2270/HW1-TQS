package pt.ua.BusApp.IntegrationTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(SeleniumJupiter.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class FrontendITTest {
    @Test
  public void woof(FirefoxDriver driver) {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(900, 1018));
    driver.findElement(By.id("origin")).click();
    {
      WebElement dropdown = driver.findElement(By.id("origin"));
      dropdown.findElement(By.xpath("//option[. = 'Porto']")).click();
    }
    driver.findElement(By.cssSelector("#origin > option:nth-child(2)")).click();
    driver.findElement(By.id("destination")).click();
    {
      WebElement dropdown = driver.findElement(By.id("destination"));
      dropdown.findElement(By.xpath("//option[. = 'Santarém']")).click();
    }
    driver.findElement(By.cssSelector("#destination > option:nth-child(4)")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("woof");
    driver.findElement(By.id("reservate")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("span"));
      assert(elements.size() > 0);
    }
  }
}
