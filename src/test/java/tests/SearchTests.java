package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentArticlePageHelper;
import pages.SearchPageHelper;
import org.testng.Assert;
import util.DataProviders;



public class SearchTests extends TestBase {
    SearchPageHelper searchPage;
    CurrentArticlePageHelper articleSeleniumSoftware;

    @BeforeMethod
    public void initTests(){
        searchPage = PageFactory.initElements(driver,SearchPageHelper
                .class);
        articleSeleniumSoftware =
                new CurrentArticlePageHelper(driver,"Selenium (software)");
        searchPage.waitUntilPageIsLoaded();
    }

    @Test
    public void wikiTest()  {
        Assert.assertEquals("Search Wikipedia", searchPage
                .getSearchFieldText());
    }

    @Test
    public void searchArticle(){
        searchPage.enterSearchText("Selenium");
        Assert.assertTrue(searchPage
                .existArticleInSearchResult("Selenium (software)"));
    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "searchArticle")
    public void searchArticleAndOpen2(String search, String article){
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware
                .isOpenedCorrectly());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "searchArticlesFromFile")
    public void searchArticleAndOpen(String search, String article){
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware
                .isOpenedCorrectly());
    }

    @Test
    public void searchArticleOpenAndRotate(){
        String search = "Selenium";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.rotateScreenLandScape(); // povorot
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        searchPage.rotateScreenPortrait();
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
    }

    @Test
    public void searchArticleOpenAndBackground(){
        String search = "Selenium";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.runBackGround(2);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
    }

    @Test
    public void searchArticleOpenAndSwipe(){
        String search = "Selenium";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.swipeUp();
        articleSeleniumSoftware.swipeUp();
        articleSeleniumSoftware.swipeUp();
    }

    @Test
    public void searchArticleOpenAndSwipeToFooter(){
        String search = "Selenium";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.swipeToFooter();
        Assert.assertTrue(articleSeleniumSoftware.isEndOfArticle());
    }


    @Test
    public void searchArticleAndOpenMenuArticle(){
        String search = "Selenium";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticleMenu(article);
        searchPage.closeArticleMenu();
    }



}
