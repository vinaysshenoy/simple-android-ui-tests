package qaApiServices.patients.response;

import lombok.Getter;
import lombok.Setter;
import qaApiServices.patients.builder.Patients;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PatientGetRequestResponse {
    private List<Patients> patients;
    private String process_token;

    public List<String> getAllPatientsName() {
        List<String> collect = patients.stream()
                .map(p -> p.getFull_name().toUpperCase())
                .collect(Collectors.toList());
        return collect;
    }

    public String getReminderConcentValueForPatient(String patientName) {
        List<Patients> collect = patients.stream()
                .filter(p -> p.getFull_name().toUpperCase().equals(patientName.toUpperCase()))
                .collect(Collectors.toList());
        return collect.get(0).getReminder_consent();
    }

    public boolean isPatientIdPresentInResponse(String patientId) {

        return patients.stream().anyMatch(p->p.getId().toUpperCase().equals(patientId.toUpperCase()));
    }

    public boolean isPatientNamePresentInResponse(String patientName) {

        return patients.stream().anyMatch(p->p.getFull_name().toUpperCase().equals(patientName.toUpperCase()));
    }
}
