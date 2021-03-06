package com.tasks.pages;

import com.tasks.utilities.BrowserUtils;
import com.tasks.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class HomePage extends BasePage {

    @FindBy(css = ".footer__menu")
    public WebElement footerMenu;

    @FindBy(xpath = "//ul[@class=\"footer__socials\"]/li")
    public List<WebElement> socialMediaLinks;


    ArrayList<String> socialMediaLinksPageTitle =new ArrayList<>();

    public void switchToWindow() {

        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.scrollToEndOfPage();
//        JavascriptExecutor jsx = (JavascriptExecutor)Driver.get();
//        jsx.executeScript("window.scrollBy(0,4700)", "");
        String currentWindow = Driver.get().getWindowHandle();
        for (WebElement socialMediaLink : socialMediaLinks) {
            socialMediaLink.click();
            BrowserUtils.waitForClickablility(socialMediaLink, 10);
            //      Driver.get().switchTo().window(currentWindow);7
        }
        Set<String> windowHandles = Driver.get().getWindowHandles();

        Iterator<String> window = windowHandles.iterator();

        do {
            String windowHandle = window.next().toString();
            Driver.get().switchTo().window(windowHandle);
            BrowserUtils.waitForPageToLoad(10);
            BrowserUtils.waitFor(5);
            socialMediaLinksPageTitle.add(getPageTitle());
        } while (window.hasNext());  // bi fazla run yapiyor

//            for (String windowHandle : windowHandles) {
//                    Driver.get().switchTo().window(windowHandle);
//                    BrowserUtils.waitForPageToLoad(10);
//                    socialMediaLinksPageTitle.add(getPageTitle());
//            }

        Driver.get().switchTo().window(currentWindow);
        System.out.println("socialMediaLinksPageTitle = " + socialMediaLinksPageTitle);

    }

    public boolean verifyPageTitle(List<String> socialMediaLinks) {

        boolean flag;
        boolean twitterFlag = false;
        boolean linkedinFlag = false;

        for (String pageTitle : socialMediaLinksPageTitle) {
            for (String socialMediaLink : socialMediaLinks) {
                if (pageTitle.contains(socialMediaLink)) {
                    System.out.println("pageTitle = " + pageTitle);
                    if (socialMediaLink.equals("Twitter")) {
                        twitterFlag = true;
                    } else if (socialMediaLink.equals("LinkedIn"))
                        linkedinFlag = true;
                }
            }
        }
        System.out.println("linkedinFlag = " + linkedinFlag);
        System.out.println("twitterFlag = " + twitterFlag);

        flag = twitterFlag && linkedinFlag;

        System.out.println("flag = " + flag);
        return flag;

    }

    @FindBy(xpath = "//ul[@class='footer__socials']/li")
    public List<WebElement> links;

    public void navigateToWindows() {

        BrowserUtils.waitForPageToLoad(10);

        for (WebElement hand : links) {
            hand.click();
            BrowserUtils.waitFor(3);
        }

    }

    public boolean checkPageTitle(List<String> socialM) {

        //eksik..
        boolean flag = false;
        for (String media : socialM){
            for (String handle : Driver.get().getWindowHandles()) {
                Driver.get().switchTo().window(handle);
                if (Driver.get().getTitle().contains(media)) {
                    flag =true;
                    System.out.println("Mello= " + Driver.get().getTitle());
                }else {
                    System.out.println("Hello = " + Driver.get().getTitle());
                }

            }

        }


       // Set<String> windows = Driver.get().getWindowHandles();

        return flag;

    }
}
