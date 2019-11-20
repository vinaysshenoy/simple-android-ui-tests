package steps;

import com.embibe.optimus.utils.ScenarioContext;
import com.testvagrant.commons.utils.JsonUtil;
import com.testvagrant.monitor.exceptions.DeviceReleaseException;
import com.testvagrant.optimus.device.OptimusController;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import qaApiServices.deleteUser.DeleteApi;
import utils.OptimusImpl;
import utils.ScenarioContextKeys;

import java.io.IOException;


public class StartingSteps extends BaseSteps {

    @Before
    public void setUp(Scenario scenario) throws Exception {
        String testFeed = System.getProperty("testFeed") + ".json";
        String appJson = new JsonUtil().getAppJson(testFeed);
        controller = new OptimusController(appJson, scenario);
        smartBOTs = controller.registerSmartBOTs();
        System.out.println("Appium Services are..."+smartBOTs.size());
        smartBOTs.forEach(smartBOT -> System.out.println(smartBOT.getAppiumService()));
        optimus = new OptimusImpl(smartBOTs);

    }

    @After
    public void tearDown(Scenario scenario) throws IOException, DeviceReleaseException {
        if (scenario.isFailed()) {
            byte[] failedScreens = optimus.getScreenCapture();
            scenario.embed(failedScreens, "image/png");
        }
        controller.deRegisterSmartBOTs(smartBOTs);
        new DeleteApi().DeleteUser();

        try{
        getChromeDriver().quit();}catch (Exception e){}
    }
}
