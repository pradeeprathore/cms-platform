package com.ascent.cms.core;

public class ErrorCode
{

	public static class CommonConstants
	{

		public static final String ENTITY_NOT_FOUND = "EC_COMMON_01";

		public static final String SYNTATICALY_INCORRECT = "EC_COMMON_02";

		public static final String REQUIRED_PARAM_MISSING = "EC_COMMON_03";

		public static final String CLINIC_NOT_FOUND = "EC_COMMON_04";

		public static final String DOCTOR_NOT_FOUND = "EC_COMMON_05";

		public static final String INVALID_STATUS = "EC_COMMON_06";

		public static final String DOCTOR_CLINIC_ASSIGNMENT_EXIST = "EC_COMMON_07";

		public static final String PASSWORD_DOES_NOT_MATCH = "EC_COMMON_08";

		public static final String INVALID_DATE = "EC_COMMON_09";

		public static final String INVALID_REGISTRATION_ID = "EC_COMMON_10";

		public static final String INVALID_INVOICE_ID = "EC_COMMON_11";

		public static final String INVALID_DEFAULT_PRICE = "EC_COMMON_12";

	}

	public static class AuthConstants
	{

		public static final String BAD_CREDENTIALS = "EC_AUTH_01";

		public static final String USER_IS_DISABLED = "EC_AUTH_02";

		public static final String USER_IS_INACTIVE = "EC_AUTH_03";

		public static final String INVALID_USER_NAME_OR_PASSWORD = "EC_AUTH_04";
	}

	public static class ResourceNotFoundConstants
	{

		public static final String CODE_TYPE_NOT_FOUND = "EC_RNF_01";

		public static final String COUNTRY_NOT_FOUND = "EC_RNF_02";

		public static final String GROUP_NOT_FOUND = "EC_RNF_03";

		public static final String PATENT_NOT_FOUND = "EC_RNF_04";

		public static final String LAB_NOT_FOUND = "EC_RNF_05";

		public static final String DOCTOR_NOT_FOUND = "EC_RNF_06";

		public static final String FAVORITE_MEDICINE_NOT_FOUND = "EC_RNF_07";

		public static final String FAVORITE_DIAGNOSIS_NOT_FOUND = "EC_RNF_08";

		public static final String FAVORITE_COMPLAIN_NOT_FOUND = "EC_RNF_09";

		public static final String CLINIC_NOT_FOUND = "EC_RNF_10";

		public static final String DEGREE_PASSED_NOT_FOUND = "EC_RNF_11";

		public static final String EXPERTISE_NOT_FOUND = "EC_RNF_12";

		public static final String DOCTOR_CLINIC_ASSIGNMENT_NOT_FOUND = "EC_RNF_13";

		public static final String DISEASE_HISTORY_NOT_FOUND = "EC_RNF_14";

		public static final String SURGICAL_HISTORY_NOT_FOUND = "EC_RNF_15";

		public static final String IMMUNIZATION_HISTORY_NOT_FOUND = "EC_RNF_16";

		public static final String MEDICATION_NOT_FOUND = "EC_RNF_17";

		public static final String APPUSER_NOT_FOUND = "EC_RNF_18";

		public static final String MEDICAL_CASE_NOT_FOUND = "EC_RNF_19";

		public static final String VISIT_NOT_FOUND = "EC_RNF_20";

		public static final String MEDICINE_PRESCRIPTION_NOT_FOUND = "EC_RNF_21";

		public static final String DIAGNOSIS_PRESCRIPTION_NOT_FOUND = "EC_RNF_22";

		public static final String APPOINTMENT_NOT_FOUND = "EC_RNF_23";

		public static final String FOLLOW_UP_NOT_FOUND = "EC_RNF_24";

		public static final String ALLERGY_HISTORY_NOT_FOUND = "EC_RNF_25";

		public static final String FAVORITE_LAB_NOT_FOUND = "EC_RNF_26";

		public static final String AVAILABLE_MEDICAL_TEST_NOT_FOUND = "EC_RNF_27";

		public static final String FAVORITE_MEDICAL_SHOP_FOUND = "EC_RNF_28";

		public static final String ROLE_NOT_FOUND = "EC_RNF_29";

		public static final String MEDICAL_SHOP_NOT_FOUND = "EC_RNF_30";

		public static final String FAMILY_HISTORY_NOT_FOUND = "EC_RNF_31";

		public static final String LAB_TEST_HISTORY_NOT_FOUND = "EC_RNF_32";

		public static final String MEDICINE_NOT_FOUND = "EC_RNF_33";

		public static final String INVOICE_NOT_FOUND = "EC_RNF_34";

		public static final String FAVORITE_BILLABLE_SERVICE_NOT_FOUND = "EC_RNF_35";

		public static final String ASSISTANT_NOT_FOUND = "EC_RNF_36";

		public static final String ACCESS_TOKEN_NOT_FOUND = "EC_RNF_37";

		public static final String FAVORITE_DISEASE_NOT_FOUND = "EC_RNF_38";

		public static final String LAB_REPORT_NOT_FOUND = "EC_RNF_39";

		public static final String EMAIL_ID_HAS_NOT_PROVIDED = "EC_RNF_40";

		public static final String OBSERVED_COMPLAIN_NOT_FOUND = "EC_RNF_41";

		public static final String DIAGNOSED_DISEASE_NOT_FOUND = "EC_RNF_42";

		public static final String VISIT_ATTACHMENT_NOT_FOUND = "EC_RNF_43";
	}

	public static class AppointmentConstant
	{

		public static final String DCA_NOT_ACTIVE = "EC_BF_01";

		public static final String ONLINE_BOOKING_DISABLED = "EC_BF_02";

		public static final String SLOT_ALREADY_BOOKED = "EC_BF_03";

		public static final String SCHEDULE_ALREADY_EXIST = "EC_BF_04";

		public static final String MAX_BOOKING_PER_SLOT_ZERO = "EC_BF_05";

		public static String PATIENT_ALREADY_HAS_APPOINTMENT = "EC_BF_06";
		
		public static String APPOINTMENT_SCHEDULE_EXIST = "EC_BF_07";

	}

	public static class AuthorizationConstants
	{

		public static final String NOT_AUTHORIZED = "EC_AUTHORIZATION_01";

	}

	public static class InvalidRequest
	{

		public static final String EMAIL_NOT_FOUND = "EC_IR_01";

		public static final String RESET_PASSWORD_REQUEST_ALREADY_EXIST = "EC_IR_02";

		public static final String RESET_PASSWORD_REQUEST_EXPIRED = "EC_IR_03";

		public static final String EMAIL_ALREADY_TAKEN = "EC_IR_04";

		public static final String TILL_DATE_BEFORE_FROM_DATE = "EC_IR_05";

		public static final String MEDICAL_CASE_IS_REQUIRED = "EC_IR_06";

		public static final String NO_MEDICINES_TO_SAVE = "EC_IR_07";

		public static final String PRESCRIPTION_ALREADY_REFERED = "EC_IR_08";

		public static final String END_DATE_BEFORE_START_DATE = "EC_IR_09";

		public static final String NEXTDUE_DATE_BEFORE_PERFORMED_DATE = "EC_IR_10";

		public static final String INVALID_FILE_SIZE = "EC_IR_11";

		public static final String INVALID_FILE_SIZE_25_MB = "EC_IR_12";

	}

}
