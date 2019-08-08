package pages.patientPrimaryInformation;

import com.embibe.optimus.utils.ScenarioContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BasePage;
import utils.Date;
import utils.RandomValue;
import utils.ScenarioContextKeys;

import java.util.List;

public class BpSection extends BasePage {
    private AppiumDriver driver;

    @FindBy(id = "bloodpressureentry_systolic")
    private MobileElement systolicBp;

    @FindBy(id = "bloodpressureentry_diastolic")
    private MobileElement diastolicBp;

    @FindBy(id = "bloodpressureentry_next_arrow")
    private WebElement nextArrow;

    @FindBy(id = "bloodpressureentry_day")
    private WebElement day;

    @FindBy(id = "bloodpressureentry_month")
    private WebElement month;

    @FindBy(id = "bloodpressureentry_year")
    private WebElement year;

    @FindBy(id = "patientsummary_item_newbp")
    private WebElement addNewBpButton;

    @FindBy(id = "alertTitle")
    private WebElement updatePopupText;

    @FindBy(id = "android:id/button1")
    private WebElement savePhoneNumberButton;

    @FindBy(id = "android:id/button2")
    private WebElement skipPhoneNumberButton;

    @FindBy(id = "patientsummary_item_bp_days_ago")
    private WebElement daysAgoInfo;

    @FindBy(id = "bloodpressureentry_remove")
    private WebElement removeLink;

    @FindBy(id = "android:id/button1")
    private WebElement removeButton;

    @FindBy(id = "android:id/button2")
    private WebElement cancelButton;

    @FindBy(id = "patientsummary_item_bp_placeholder")
    private WebElement bpSummary;

    @FindBy(id = "patientsummary_item_layout")
    private List<WebElement> summaryLayout;

    @FindBys({@FindBy(id = "patientsummary_item_bp_readings")})
    private List<WebElement> bpReadings;

    @FindBy(id="bloodpressureentry_bp_date")
    private WebElement bpDate;


    public BpSection(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this.driver = driver;
    }

//    private void enterSystolicBp(String value) {
//        systolicBp.sendKeys(value);
//    }
//
//    private void enterDiastolicBp(String value) {
//        diastolicBp.clear();
//        diastolicBp.sendKeys(value);
//    }
//
//    public void updateBpInfo(String systolic, String diastolic) {
//        systolicBp.clear();
//        systolicBp.sendKeys(systolic);
//    }

    public void enterBpInfo(String systolic, String diastolic) {

        String reading=systolic+" / "+diastolic;
        System.out.println(reading+"reading");
        ScenarioContext.putData("User",ScenarioContextKeys.READING,reading);
        systolicBp.setValue(systolic);
        diastolicBp.setValue(diastolic);
    }

    public void tapsOnNextArrow() {
        nextArrow.click();
    }

    public void entersDate(String sDate) {
        String[] str = sDate.split("-");
        String dd = str[0];
        String mm = str[1];
        String yy = str[2];

        bpDate.click();
        waitForElementToBeClickable(day);
        day.clear();
        day.sendKeys(dd);
        month.clear();
        month.sendKeys(mm);
        pressEnter();
    }

    public void tapsOnAddNewBpButton() {
        addNewBpButton.click();
    }


    @FindBy(id = "addphone_phone")
    private WebElement phoneNumberTextFeild;

    @FindBy(id = "updatephone_phone")
    private WebElement updatePhoneNumberTextFeild;

    public void addPhoneNumber() {
        phoneNumberTextFeild.sendKeys(RandomValue.getRandomPhoneNumber() + "\n");
        savePhoneNumberButton.click();
    }

    public void verifiesUpdatePopUp() {
        Assert.assertTrue(updatePopupText.getText().equals("Update phone number"));
    }

    public void tapsOnSkipButton() {
        skipPhoneNumberButton.click();
    }

    public void enterBackDate() {
        entersDate(Date.getBackDateIn_DD_MM_YYYY_Format(10));
    }

    public void tapsOnEditBpLink() {
        daysAgoInfo.click();
    }

    public void tapsOnRemoveLink() {
        removeLink.click();
    }

    public void tapsOnCancelButton() {
        cancelButton.click();
    }

    public void tapsOnRemoveButton() {
        removeButton.click();
    }

    public void verifiesBpSummary() {
        Assert.assertTrue(bpSummary.getText().equals("No blood pressures added"), "No Blood pressure added text should be displayed");
    }

    public int getSummaryLayoutCount() {
        return summaryLayout.size();
    }

    public void verfiesBpList() {
        int count = getSummaryLayoutCount();
        if (count == 0) {
            Assert.fail("Bp list should be displayed");
        }
    }

    public void removeAllBpInfo() {

        System.out.println(summaryLayout.size() + "size");
        int size = summaryLayout.size();
        for (int i = 0; i <= size - 1; i++) {
            tapsOnEditBpLink();
            tapsOnRemoveLink();
            tapsOnRemoveButton();
        }
    }

    public void updatePhonenumber() {
        updatePhoneNumberTextFeild.sendKeys(RandomValue.getRandomPhoneNumber());
        savePhoneNumberButton.click();
    }

    @FindBy(id = "patientsummary_item_layout")
    private WebElement bpLayout1;

    private By heartIcon = By.id("patientsummary_bp_reading_heart");
    private By bpLevel = By.id("patientsummary_item_bp_level");
    private By daysAgo = By.id("patientsummary_item_bp_days_ago");

    public void verifiesDaysInformationForBackDate(String reading) {

        for (WebElement ele : bpReadings) {
            if (ele.getText().equals(reading)) {

                WebElement bpLayout = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+reading+"')]/.."));
                Assert.assertTrue(bpLayout.findElement(By.id("patientsummary_item_bp_readings")).getText().equals(reading));
                Assert.assertTrue(bpLayout.findElement(bpLevel).isDisplayed());
                Assert.assertTrue(bpLayout.findElement(heartIcon).isDisplayed());
                Assert.assertTrue(bpLayout.findElement(daysAgo).getText().replaceAll("[^a-zA-Z0-9]", "").contains("Edit10daysago"));
            }
        }
    }

    public void verifiesDaysInfo(String reading) {
        for (WebElement ele : bpReadings) {
            if (ele.getText().equals(reading)) {
                WebElement bpLayout = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+reading+"')]/.."));
                Assert.assertTrue(bpLayout.findElement(By.id("patientsummary_item_bp_readings")).getText().equals(reading));
                Assert.assertTrue(bpLayout.findElement(bpLevel).isDisplayed());
                Assert.assertTrue(bpLayout.findElement(heartIcon).isDisplayed());
                Assert.assertTrue(bpLayout.findElement(daysAgo).getText().replaceAll("[^a-zA-Z0-9]", "").contains("EditToday"));
            }
        }
    }
}
