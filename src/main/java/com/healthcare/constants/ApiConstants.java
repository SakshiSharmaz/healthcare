package com.healthcare.constants;



import java.util.Collection;

public class ApiConstants {

    // CONTENT TYPE
    public static final String REST_JSON_CONTENT_TYPE = "application/json";

    // AUTHENTICATION
    public static final String AUTHORIZATION = "Authorization";
    public static final String USER_ID_NOT_FOUND = "User ID not found";
    public static final String WRONG_PASSWORD = "Wrong password";
    public static final String BEARER = "Bearer ";
    public static final String HEADER = "header";
    public static final String MESSAGE = "message";

    // USER TYPES
    public static final String SUPER_ADMIN = "Super Admin";
    public static final String COUNTRY_ADMIN = "Country Admin";
    public static final String HEALTH_FACILITY_ADMIN = "Health Facility Admin";
    public static final String RECEPTIONIST = "Receptionist";

    // ADDITIONAL CONSTANTS
    public static final String SUCCESS = "success";
    public static final String INVALID_USER = "iNVALID uSER";
    public static final String LOGIN_SUCCESSFUL = "You have successfully logged in";
    public static final String UNAUTHORIZED = "You are unauthorized to perform this action.";
    public static final String UNEXPECTED_ERROR = "Something went wrong. Please contact your Administrator.";

    // API PATHS
    public static final String REST_BASE_PATH = "/Heartcare";
    public static final String REST_AUTHENTICATION_PATH = "/authenticate_users";
    public static final String LOGIN = "/login";
    public static final String CHANGE_PASSWORD = "/change_password";
    public static final String FORGOT_PASSWORD = "/forgot_password";
    public static final String AUTHORIZE = "/authorize";
    public static final String COUNTRIES = "/countries";
    public static final String REST_LEVEL_PATH = "/levels";
    public static final String REST_USERS_PATH = "/users";
    public static final String LEVEL1_LIST = "/level1_list";
    public static final String LEVEL2_LIST = "/level2_list/{level1Id}";
    public static final String REST_MEDICINE_PATH = "/medicine";

    public static final String LEVEL3_LIST = "/level3_list/{level2Id}";
    public static final String LEVEL4_LIST = "/level4_list/{level3Id}";
    public static final String PROFILE = "/profile";

    public static final String USERID = "/{userId}";
    public static final String GENERATE_PASSWORD = "/generate_password";
    public static final String DISABLE_USER = "/disable_user";
    public static final String NOTIFY_USER = "/notifyUser";
    public static final String REST_TEMPLATE_PATH = "/template";
    public static final String VALIDATE = "/validate";
    public static final String BULK_UPDATE = "/bulk_update";

    public static final String REST_SYNC_PATH = "/sync_data";
    public static final String REST_PATIENT_DETAILS_SYNC = "/patient_details_sync";
    public static final String REST_VILLAGE_DETAILS_SYNC = "/village_details_sync";
    public static final String REST_HOSPITAL_DETAILS_SYNC = "/hospital_details_sync";
    public static final String REST_COUNTRY_CONFIG_SYNC = "/country_config_sync";

    public static final String CLASS_LIST = "/class_list";
    public static final String CATEGORY_LIST = "/category_list";
    public static final String MED_ID = "/{medId}";
    public static final String MEDICINE_LIST = "/medicine_list";
    public static final String VALIDATE_MEDICATION_FILE = "/validate_medication_file";
    public static final String BULK_UPLOAD_MEDICINES = "/bulk_upload_medicines";
    public static final String ENABLE_DISABLE_MEDICATION = "/enable_disable_medication";
    public static final String MED_CATEGORY = "/category_classes";

    public static final String COUNTRY_LIST = "/country_list";
    public static final String DIVISIONS_PHARMACY_LIST = "divisions_pharmacy_list";
    public static final String UPDATED_USERS_LIST = "updated_users_list";

    public static final String PHARMACIST = "Pharmacist";
    public static final String GENERAL_EMPLOYEE = "General Employee";
    public static final String PHYSICIAN = "Physician";
    public static final String VILLAGE_HOSPITAL_LIST = "/village_hospital_list";
    public static final String SMS_EXCEPTION = "SMS Exception";
    public static final String EMAIL_EXCEPTION = "Email Exception";

    public static final String TEMP_CONTROLLER = "/temp_controller";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String USER_ID = "userId";
    public static final String OTP = "OTP";
    public static final String USER_ROLE = "userRole";
    public static final String URL = "url";
    public static final String OFFLINE_USERS_LIST = "/offline_users_list";
    public static final String MEDICINE_NOT_FOUND = "Medicine ID not found";
    public static final String REST_MEDICINES_PATH = "/medicines";
    public static final String REST_EXAMINATION_PATH = "/examination";
    public static final String EXAMINATION_NOT_FOUND = "Examination ID not found";
    public static final String EXAMINATION_ID = "/{examinationId}";
    public static final String EXAMINATION_LIST = "/examination_list";
    public static final String ENABLE_DISABLE_EXAMINATION = "/enable_disable_examination";
    public static final String VALIDATE_EXAMINATION_FILE = "/validate_examination_file";
    public static final String BULK_UPLOAD_EXAMINATIONS = "/bulk_upload_examinations";

    public static final String BULK_UPLOAD_INTERVENTIONS = "/bulk_upload_interventions";
    public static final String VALIDATE_INTERVENTION_FILE = "/validate_intervention_file";
    public static final String ENABLE_DISABLE_INTERVENTION = "/enable_disable_intervention";
    public static final String INTERVENTION_LIST = "/intervention_list";
    public static final String INTERVENTION_ID = "/{interventionId}";
    public static final String REST_INTERVENTION_PATH = "/intervention";
    public static final String INTERVENTION_NOT_FOUND = "Intervention ID not found";

    public static final String REST_ADMINISTRATIVE = "/administrative_levels";
    public static final String CREATE_PROVINCE = "/create_province";
    public static final String CREATE_CITY = "/create_city";
    public static final String CREATE_DISTRICT = "/create_district";
    public static final String CREATE_HEALTH_FACILITY = "create_health_facility";
    public static final String CREATE_VILLAGE = "create_village";
    public static final String LEVEL_NAMES = "/level_names";
    public static final String EDIT_LEVELS = "/edit_levels";
    public static final String VIEW_LEVEL_DETAILS = "view_level_details";
    public static final String REST_ADMINISTRATIVE_LEVELS_PATH = "/administrative_levels_template";
    public static final String BULK_UPLOAD_ADMINISTRATIVE_LEVELS = "/bulk_upload_levels";

    public static final String REST_POPULATION_DISTRIBUTION = "/population_distribution";
    public static final String POPULATION_DATA_NOT_FOUND = "No Population data available";
    public static final String VALIDATE_ADMINISTRATIVE_LEVEL_FILE = "/validate_administrative_levels";
}

