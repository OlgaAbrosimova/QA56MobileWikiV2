package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyCurrentListHelper extends PageBase {
    @FindBy(id="org.wikipedia:id/item_title")
    WebElement title;
    @FindBy(id="org.wikipedia:id/page_list_item_title")
    List<WebElement> articleTitlesList;
    @FindBy(id="org.wikipedia:id/action_mode_close_button")
    WebElement closeListButton;

    public MyCurrentListHelper(WebDriver driver) {
        super(driver);
    }

    public MyCurrentListHelper waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(title,15);
        //waitUntilAllElementsAreVisible(articleTitlesList,15);
        return this;
    }

    public boolean existsArticle(String article){
        boolean flag = false;
        for (WebElement title: articleTitlesList){
            if (title.getText().equals(article)) flag = true;
        }
        return flag;
    }

    public MyCurrentListHelper removeArticleFromListUsingSwipe(String article) {
        AppiumDriver appDriver = (AppiumDriver) (driver);
        TouchAction action = new TouchAction(appDriver);
        WebElement articleName = driver.findElement(By.xpath(xPathArticleName(article)));
        int x1 = articleName.getLocation().x*5;
        int y1 = articleName.getLocation().y;
        int x2 = articleName.getLocation().x/10;
        action.longPress(PointOption.point(x1,y1))
                .moveTo(PointOption.point(x2,y1))
                .release()
                .perform();
        waitUntilElementIsClickable(closeListButton,15);
        return this;
    }

    public MyCurrentListHelper closeList() {
        //waitUntilElementIsClickable(closeListButton,10);
        closeListButton.click();
        return this;
    }
}
