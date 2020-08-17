package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.Set;

public class PageBase {
    WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void waitUntilAttributeValueIs(By locator, String attribute, String value, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .attributeToBe(locator, attribute, value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAttributeValueIs(WebElement element, String attribute, String value, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .attributeToBe(element, attribute, value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilTextValueIs(WebElement element, String text, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .textToBePresentInElement(element, text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsNotVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elementList, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOfAllElements(elementList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilFrameIsLoadedAndSwitchToIt(WebElement frame, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frame));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilNumberOfWindows(int number, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .numberOfWindowsToBe(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAnotherWindowHandle(String mainHandle) {
        Set<String> setHandles = driver.getWindowHandles();
        String anotherHandle = "";
        for (String handle : setHandles) {
            if (!handle.equals(mainHandle)) anotherHandle = handle;

        }
        return anotherHandle;
    }

    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
    }

    public void fillField(WebElement textField, String value) {
        textField.click();
        textField.clear();
        textField.sendKeys(value);
    }

    public void scrollDown(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void scrollDownToViewElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView();", element);

    }

    public void swipeUpToElement(By by,int maxTimes){
        int counter = 0;
        while(driver.findElements(by).size() == 0 && counter < maxTimes){
            swipeUp();
            counter++;
        }
    }

    public void swipeUp(){
        AppiumDriver appDriver = (AppiumDriver) driver;
        TouchAction action = new TouchAction(appDriver);
        Dimension size = driver.manage().window().getSize();
        int x1 = (int) (size.width*0.5);
        int y1 = (int) (size.height*0.8);
        int y2 = (int) (size.height*0.2);
        action.press(PointOption.point(x1,y1))
                .waitAction()
                .moveTo(PointOption.point(x1,y2))
                .release()
                .perform();
    }

    public String xPathArticleName(String article){
        return "//*[@text='" + article +"']";
    }
    public void rotateScreenLandScape() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void rotateScreenPortrait() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void runBackGround(int sec){
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.runAppInBackground(Duration.ofSeconds(sec));
    }


}
