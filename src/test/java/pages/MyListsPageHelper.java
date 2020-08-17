package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyListsPageHelper extends PageBase {
    @FindBy(id = "org.wikipedia:id/item_title")
    List<WebElement> listArticleTitles;

    public MyListsPageHelper(WebDriver driver) {
        super(driver);
    }

    public MyListsPageHelper openList(String name){
        driver.findElement(By.xpath("//*[@text = '" + name + "']")).click();
        return this;
    }

    public MyListsPageHelper waitUntilPageIsLoaded() {
        waitUntilAllElementsAreVisible(listArticleTitles,20);
        return this;
    }

}
