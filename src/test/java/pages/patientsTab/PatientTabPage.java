package pages.patientsTab;

import com.embibe.optimus.utils.ScenarioContext;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BasePage;
import pages.SearchPage;
import utils.ScenarioContextKeys;

public class PatientTabPage extends BasePage {
    private AppiumDriver driver;

    private SearchPage searchSection;
    private RecentPatientSection recentPatientSection;

    @FindBy(id = "patients_scan_simple_card")
    private WebElement scanBPPassportButton;

    @FindBy(id = "patients_user_awaitingapproval_title")
    private WebElement approvalMessage;

    @FindBy(id = "patients_dismiss_user_approved_status")
    private WebElement GotITButton;

    @FindBy(id = "sync_indicator_root_layout")
    private WebElement syncLink;

    public PatientTabPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        searchSection = new SearchPage(driver);
        recentPatientSection = new RecentPatientSection(driver);
    }

    public void verifyPatientTab() {
        waitForElementToBeVisible(scanBPPassportButton);
        Assert.assertEquals(scanBPPassportButton.getText(), "Scan BP Passport");
        Assert.assertTrue(GotITButton.isDisplayed());
//        GotITButton.click();
    }

    public void selectPatientFromSearchList() {
        String patientName = ScenarioContext.getData("User", ScenarioContextKeys.PATIENT_NAME);
        searchSection.selectsPatientFromSearchList(patientName);
    }

    public void tapsOnSyncLink() {
        syncLink.click();
    }

    public void verifiesSeeAllOption() {
        recentPatientSection.verifiesSeeAllOption();
    }

    public void selectPatientFromRecentPatientList(String patientNAme) throws Exception {
        recentPatientSection.selectPatientFromRecentPatientList(patientNAme);
    }

    public void tapsOnSearchTextBox() {
        searchSection.tapsOnSearchTextBox();
    }

    public void isPatientPresent(String patientName) throws Exception {
        recentPatientSection.isPatientPresent(patientName);
    }

    public void isPatientNotPresent(String patientName) {
        recentPatientSection.isPatientNotPresent(patientName);
    }

    public void selectAnyPatient() {
        recentPatientSection.selectAnyPatientFromRecentPatientSection();
    }

    public void shouldNotShowUpOnTopOfList() {
        recentPatientSection.shouldNotShowUpOnTopOfList();
    }

    public void shouldShowUpOnTopOfList() {
        recentPatientSection.shouldShowUpOnTopOfRecentPatinetList();
    }

    public void verifyNoRecentPatientText() {
        recentPatientSection.verifyNoRecentPatientText();
    }
}
