package com.funcui.testclasses;

import com.funcui.base.BaseTest;
import com.funcui.base.CheckPoint;
import com.funcui.pageclasses.CategoryFilterPage;
import com.funcui.pageclasses.SearchBarPage;
import com.funcui.utilities.Constants;
import com.funcui.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class AllCoursesTests extends BaseTest {

    @DataProvider(name = "verifySearchCourseData")
    public Object[][] getVerifySearchCourseData(){
        Object[][] testData = ExcelUtility.getTestData("verify_search_course");
        return testData;
    }

    @BeforeClass
    public void setUp() {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "AllCoursesTests");
    }

    @Test(dataProvider = "verifySearchCourseData")
    public void verifySearchCourse(String courseName) {
        nav.allCourses();
        search = new SearchBarPage(driver);
        result = search.course(courseName);
        boolean searchResult = result.verifySearchResult();
        CheckPoint.markFinal("test-02", searchResult, "search course verification");
    }

    @Test(enabled = false)
    public void filterByCategory() {
        nav.allCourses();
        category = new CategoryFilterPage(driver);
        result = category.select("Software IT");
        int count = category.findCoursesCount("Software IT");
        boolean filterResult = result.verifyFilterCourseCount(count);
        CheckPoint.markFinal("test-03", filterResult, "filter by category verification");
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}