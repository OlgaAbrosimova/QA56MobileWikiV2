package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPageHelper extends PageBase {
    @FindBy(xpath = "//*[contains(@text,'Search Wikipedia')]" )
    WebElement searchField;

    @FindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchInput;

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articlesNamesList;

    @FindBy(xpath="//*[@class='android.widget.FrameLayout'][@content-desc='My lists']")
    WebElement openMyListsButton;

    public SearchPageHelper(WebDriver driver) {
        super(driver);
    }

    public SearchPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(searchField,15);
        return this;
    }

    public String getSearchFieldText(){
        return searchField.getText();
    }

    public SearchPageHelper enterSearchText(String text) {
        System.out.println("x: " + searchField.getLocation().x);
        System.out.println("y: " + searchField.getLocation().y);
        searchField.click();
        waitUntilElementIsClickable(searchInput,10);
        searchInput.sendKeys(text);
        waitUntilAllElementsAreVisible(articlesNamesList,15);
        System.out.println("Articles quantity: "+ articlesNamesList.size());
        return this;
    }

    public boolean existArticleInSearchResult(String article) {
        Boolean flag = false;
        for(WebElement element: articlesNamesList){
            if(element.getText().equals(article)) flag=true;
        }
        return flag;
    }

    public SearchPageHelper openArticle(String article) {
        driver.findElement(By.xpath(xPathArticleName(article))).click();
        return this;
    }
    public SearchPageHelper openArticle2(String article) {
        for(WebElement element: articlesNamesList){
            if(element.getText().equals(article)){
                element.click();
                break;
            } else {
                driver.findElement(By.xpath(xPathArticleName(article))).click();
                break;
            }
        }
        return this;
    }

    public SearchPageHelper openMyListsPage() {
        openMyListsButton.click();
        return this;
    }

    ///public String xPathArticleName(String article){
    //        return "//*[@text='" + article +"']";
    //    }

    public void openArticleMenu(String article){
        AppiumDriver appDriver = (AppiumDriver) (driver);
        TouchAction action = new TouchAction(appDriver);
        WebElement articleName = driver.findElement(By.xpath(xPathArticleName(article)));
        int x = articleName.getLocation().x+3;
        int y = articleName.getLocation().y+3;
        action.longPress(PointOption.point(x,y))
                .waitAction()
                .release()
                .perform();
    }

    public void closeArticleMenu(){
        AppiumDriver appDriver = (AppiumDriver) (driver);
        TouchAction action = new TouchAction(appDriver);
        WebElement menuOpen = driver.findElement(By.xpath("//*[@text='Open']"));
        int x = (int) (menuOpen.getLocation().x*0.5);
        int y = menuOpen.getLocation().y;
        action.press(PointOption.point(x,y))
                .waitAction()
                .release()
                .perform();

    }
}
