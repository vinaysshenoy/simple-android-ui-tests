package patients;

import lombok.Getter;
import lombok.Setter;
import utils.Date;
import utils.RandomValue;

import java.util.List;

@Getter
@Setter
public class Patients {

    private String id ;
    private String gender;
    private String full_name ;
    private String status;
    private String date_of_birth;
    private int age;
    private String age_updated_at ;
    private String deleted_at ;
    private String created_at ;
    private String updated_at;
    private Address address;
    private List<Phone_numbers> phone_numbers;
    private String contacted_by_counsellor;
    private String could_not_contact_reason;
    private String recorded_at;
    private List<Business_identifiers> business_identifiers;
    private String call_result;

    public Patients(){}

    public Patients(String id, String gender, String full_name, String status, String date_of_birth, int age, String age_updated_at, String deleted_at, String created_at, String updated_at, Address address, List<Phone_numbers> phone_numbers) {
        this.id = id;
        this.gender = gender;
        this.full_name = full_name;
        this.status = status;
        this.date_of_birth = date_of_birth;
        this.age = age;
        this.age_updated_at = age_updated_at;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.address = address;
        this.phone_numbers = phone_numbers;
    }


    public static class Builder {
        private String id = RandomValue.getRandomPatientId();
        private String gender;
        private String full_name = RandomValue.getRandomPatientName();
        private String status;
        private String date_of_birth;
        private int age;
        private String age_updated_at = Date.getCurrentDateIn_RFC339_Format();
        private String deleted_at = "";
        private String created_at = Date.getCurrentDateIn_RFC339_Format();
        private String updated_at = Date.getCurrentDateIn_RFC339_Format();
        private Address address;
        private List<Phone_numbers> phone_numbers;



        public Builder() {
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withPhoneNumber(List<Phone_numbers> ph) {
            this.phone_numbers = ph;
            return this;
        }

        public Builder withGender(String value) {
            this.gender = value;
            return this;
        }

        public Builder withStatus(String value) {
            this.status = value;
            return this;
        }

        public Builder withAge(int value) {
            this.age = value;
            return this;
        }

        public Builder withCreatedAt(String value) {
            this.created_at = value;
            return this;
        }

        public Builder withUpdatedAt(String value) {
            this.updated_at = value;
            return this;
        }

        public Patients build(){
            return new Patients(id,gender,full_name,status,date_of_birth,age,age_updated_at,deleted_at,created_at,updated_at,address,phone_numbers);
        }

    }
}
