package com.funcui.pageclasses;
import com.funcui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ResultsPage extends BasePage {

    public ResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /***
     * Variables
     * URL
     * Title
     */
    public WebDriver driver;
    private final String URL = "query=";
    private final String COURSES_LIST = "xpath=>//div[@class='course-listing']";

    /***
     * Methods
     */
    public boolean isOpen() {
        return getURL().contains(URL);
    }

    public int coursesCount() {
        List<WebElement> coursesList = getElementList(COURSES_LIST, "Courses List");
        return coursesList.size();
    }

    public boolean verifySearchResult() {
        boolean result = false;
        if (coursesCount() > 0) {
            result = true;
        }
        result = isOpen() && result;
        return result;
    }

    public boolean verifyFilterCourseCount(int expectedCount) {
        return coursesCount() == expectedCount;
    }
}