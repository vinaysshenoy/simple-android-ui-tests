package qaApiServices.patients;

import com.embibe.optimus.utils.ScenarioContext;
import utils.ScenarioContextKeys;

import java.util.List;

public class GetPatientInfo {

    public List<String> getAllPatientsName() {

        String facilityId = ScenarioContext.getData("User", ScenarioContextKeys.FACILTIYID);
        String userId = ScenarioContext.getData("User", ScenarioContextKeys.USER_ID);
        String token = ScenarioContext.getData("User", ScenarioContextKeys.ACCESS_TOKEN);

        PatientGetRequestResponse patientGetRequestResponse = new PatientClient().get(facilityId, userId, token);
        return patientGetRequestResponse.getAllPatientsName();
    }

    public PatientGetRequestResponse getAllPatient() {

        String facilityId = ScenarioContext.getData("User", ScenarioContextKeys.FACILTIYID);
        String userId = ScenarioContext.getData("User", ScenarioContextKeys.USER_ID);
        String token = ScenarioContext.getData("User", ScenarioContextKeys.ACCESS_TOKEN);

        return new PatientClient().get(facilityId, userId, token);
    }
}