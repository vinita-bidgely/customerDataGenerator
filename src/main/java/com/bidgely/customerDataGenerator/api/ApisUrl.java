package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ApisUrl {

		private ApisUrl() {

		}

		/**
		 * Enum to define the keys that can be substituted in the URI
		 *
		 * @author Apurv
		 *
		 */
		public static enum UriSubstitutorKeys {
			BASE_URL("baseURL"),
			USER_UUID("userUUID"),
			HOME("home"),
			T0("t0"),
			T1("t1"),
			MODE("mode"),
			GATEWAY("gateway"),
			METER("meterId"),
			COUNTRY("country"),
			ZIP("zip"),
			PILOT_ID("pilotId"),
			UTILITY_ID("utilityId"),
			MEASUREMENT_TYPE("measurementType"),
			STREAM("stream"),
			ENDPOINT_UUID("endpointUUID"),
			INQUIRY_ID("inquiryId"),
			FROM_DATE("fromDate"),
			TO_DATE("toDate"),
			BILLSTART_DATE("billStartDate"),
			EMAIL("email"),
			TOKEN("token"),
			APPLIANCEID("applianceId"),
			NOTIFICATIONTYPE("notificationType"),
			SEARCH_STRING("searchString"),
			QUERY_PARAMS("queryParams"),
			NOTIFICATION_CHANNEL("notificationChannel"),
			EVENT_NAME("eventName"),
			EVENT_UUID("eventUUID"),
			HYBRID("hybrid"),
			TOTAL_FROM_INVOICE("totalFromInvoice"),
			SHOW_FAILED_BILLING_CYCLES("showFailedBillingCycles"),
			ROUND("round"),
			USER_ENROLL_FILENAME("userEnrollFilename"),
			FILENAME("fileName"),
			CHUNK_FILENAME("chunkFileName"),
			LOCALE("locale"),
			TEMPLATE_TYPE("templateType"),
			BOOLEAN("boolean"),
			LIMIT("limit"),
			METER_NUMBER("meterNumber"),
			AUDITOR_ID("auditorId"),
			WORK_ORDER_ID("workOrderId"),
			EXPERIMENT_NAME("experimentName"),
			EXPERIMENT_START("experimentStart"),
			EXPERIMENT_END("experimentEnd"),
			EXPERIMENT_DEFINATION("experimentDefinition"),
			CLUSTER_NAMES("clusterNames"),
			CLUSTER_ID("clusterId"),
			PDF_TYPE("pdfType"),
			ENTITY_ID("entityId"),

			UAT_BASE_URL("uatBaseUrl"),
			NTYPE("ntype"),
			RELEVANCE_SCORE("minimumRelevanceScore"),
			COUNT("count"),
			PILOT_ID_OR_PILOT_UUID("pilotIdOrPilotUUID"),
			SHOW_PERCENTAGE("showPercentage"),
			LOOKER_SPACE_ID("lookerSpaceId"),
			GRANT_TYPE("grantType"),
			SCOPE("scope"),
			CLIENT_ID("clientId"),
			CLIENT_SECRET("clientSecret"),
			DATATYPE("dataType"),
			NID("nid"),
			START_TIME("startTime"),
			END_TIME("endTime"),
			METRIC_TYPE("metricType"),
			BC_START_TS("bcStartTs"),
			XPLENTY_CLUSTER_ID("xplentyClusterId"),
			XPLENTY_PACKAGE_ID("xplentyPackageId"),
			XPLENTY_JOB_ID("xplentyJobId"),
			DELIVERY_MODE("deliveryMode"),
			IDENTIFIER_ID("identifierId"),
			ENTITY_TYPE("entityType"),
			CONFIG_TYPE("configType"),
			PRODUCT_TYPE("productType"),
			ALLOW_DUPLICATES("allowDuplicates"),
			APPLIANCE_IDS("applianceIds"),
			EXTERNAL_ACCOUNT_ID("externalAccountId"),
			SCRIPT_PATH("scriptPath"),
			USER_HASH("userHash"),
			PLAN("plan"),
			TIMEZONE("timeZone"),
			CTYPE("cType"),
			LEVEL("level"),
			RATE_PLAN("ratePlan"),
			NOTIFICATION_STATUS("notificationStatus")
			;

			private String key;

			UriSubstitutorKeys(String key) {
				this.key = key;

			}

			@Override
			public String toString() {
				return this.key;
			}
		}

		/**
		 * Enum that defines the URIs
		 *
		 * @author Apurv
		 *
		 */
		public static enum GetUriFor {
			GETTING_NOTIFICATIONS_FOR_USER(
					"/2.1/users/${userUUID}/homes/${home}/notifications"),
			BROWSE_2DOT1(
					"/2.1/users/${userUUID}/homes/${home}/browse?t0=${t0}&t1=${t1}&mode=${mode}"),
			GET_RAW_METER_DATA(
					"/streams/users/${userUUID}/homes/${home}/load.json?t0=${t0}&t1=${t1}"),
			BACKEND_HAN_UPLOAD(
					"/2.1/users/${userUUID}/homes/${home}/gws/${gateway}/hanIngestion"),
			BACKEND_GB_UPLOAD(
					"/gb/users/${userUUID}/homes/${home}/gws/${gateway}/ELECTRIC/900/rawcsv"),
			BACKEND_HAN_V1_UPLOAD(
					"/v1/users/${userUUID}/homes/${home}/gateways/${gateway}/upload"),
			BACKEND_USER_CREATION("/2.1/batch/userAndHome"),
			PUBLIC_API_USER_CREATION("/v2.0/users"),
			PUBLIC_API_USER("/v2.0/users/${userUUID}"),
			PUBLIC_API_USER_ENDPOINT("/v2.0/users/${userUUID}/endpoints"),
			PUBLIC_API_DATA_UPLOAD(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}"),
			META_USER_HOME_GATEWAY(
					"/meta/users/${userUUID}/homes/${home}/gws/${gateway}"),
			META_USER_HOME_GATEWAY_METERS(
					"/meta/users/${userUUID}/homes/${home}/gws/${gateway}/meters"),
			META_USER_HOME_GATEWAYS("/meta/users/${userUUID}/homes/${home}/gws"),
			META_USER_HOME_GATEWAY_METER(
					"/meta/users/${userUUID}/homes/${home}/gws/${gateway}/meters/${meterId}"),
			META_USER_HOME("/meta/users/${userUUID}/homes/${home}"),
			META_USER("/meta/users/${userUUID}"),
			UTILITY_BILLING_CYCLES("/2.1/utilityBillingCycles"),
			WEATHER_API(
					"/weather/${country}/${zip}/data?t0=${t0}&t1=${t1}&mode=${mode}"),
			WEATHER_API_MONTH_MODE(
					"/weather/${country}/${zip}/data?t0=${t0}&t1=${t1}&mode=${mode}&user=${userUUID}&home=${home}"),
			AGGREGATED_CONSUMPTION_DATA(
					"/billingdata/users/${userUUID}/homes/${home}/consumption/ctypes/energy_consumption?t0=${t0}&t1=${t1}&mode=${mode}"),
			AGGREGATED_BILLING_COST(
					"/billingdata/users/${userUUID}/homes/${home}/consumption/ctypes/billing_cost?t0=${t0}&t1=${t1}&mode=${mode}&measurementType=${measurementType}"),
			AGGREGATED_DELETE_DATA(
					"/billingdata/users/${userUUID}/homes/${home}/consumption"),
			FAST_POLL(
					"/streams/users/${userUUID}/homes/${home}/gws/${gateway}/fastPoll"),
			GET_HAN_DISAGG_DATA(
					"/billingdata/users/${userUUID}/homes/${home}/consumption/ctypes/appliance_disagg?t0=${t0}&t1=${t1}&mode=${mode}"),
			GET_GB_DISAGG_DATA(
					"/streams/users/${userUUID}/homes/${home}/appdata/hourly.json?t0=${t0}&t1=${t1}&debug=1"),
			MODIFIED_API(
					"/meta/users/${userUUID}/homes/${home}/modified?urgent=true"),
			CLUSTERS_API(
					"/1.0/UtilityClusters/clusters/pilot/${pilotId}"),
			CLUSTERS_UTILITY_API(
					"/1.0/UtilityClusters/clusters/pilot/${pilotId}/utility/${utilityId}"),
			GET_BILLING_CYCLE_FOR_PILOT(
					"/2.1/utilityBillingCycles/utility/${pilotId}"),
			LABEL_TIMESTAMPS(
					"/streams/users/${userUUID}/homes/${home}/labelTimestamps/${measurementType}/${stream}"),
			NOTIFICATIONS_REGISTRATION(
					"/2.1/users/${userUUID}/notificationRegistration"),
			ITEMIZATION(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/itemization?fromDate=${fromDate}&toDate=${toDate}&mode=${mode}"),
			APPLIANCE_EVENTS(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/events?fromDate=${fromDate}&toDate=${toDate}"),
			INSIGHTS(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/insights?billStartDate=${billStartDate}"),
			RECOMMENDATIONS(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/recommendations"),
			RECOMMENDATIONS_FOR_APPLIANCE(
					"/users/${userUUID}/homes/${home}/recommendations?applianceId=${applianceId}"),
			LIVE_STATS(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/livestats"),
			TEACH_APPLIANCE_EVENTS(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/inquiries"),
			APPLIANCE_INQUIRY(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/inquiries/${inquiryId}"),
			SUBSCRIPTIONS("/v2.1/notifications/pilot/${pilotId}/subscriptions"),
			NOTIFICATIONS_FOR_USER(
					"/2.1/users/${userUUID}/homes/${home}/notifications"),
			GET_UUID_FROM_EMAIL("/meta/userNames/${email}"),
			RANDOM_TIPS("/2.0/users/${userUUID}/randomtips?locale=en_US"),
			BILLING_CYCLES_FOR_USER(
					"/billingdata/users/${userUUID}/homes/${home}/billingcycles?t0=${t0}&t1=${t1}&measurementType=${measurementType}"),
			NOTIFICATIONS_NS("/notification/ns/${userUUID}/"),
			TIPS("/2.0/users/${userUUID}/tips?locale=en_US"),
			ITEMIZATION_DETAILS(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/itemizationDetails?t0=${t0}&t1=${t1}&mode=${mode}"),
			APPLIANCES("/2.1/users/${userUUID}/homes/${home}/appliances"),
			ALL_LABEL_TIMESTAMPS(
					"/streams/users/${userUUID}/homes/${home}/allLabelTimestamps/${measurementType}/${dataType}"),
			LAST_DATA_TIMESTAMP(
					"/streams/users/${userUUID}/homes/${home}/gws/${gateway}/lastDataTimestamps"),
			TOKENS_API("/meta/tokens/${token}"),
			NOTIFICATIONS_DELIVERYMODES(
					"/v2.1/notifications/user/${userUUID}/deliveryModes"),
			COMMUNICATION_PREFERENCES(
					"/2.1/communication-preferences/users/${userUUID}/homes/1"),
			ACTIVATE_SMS_CHANNEL(
					"/2.1/sms/users/${userUUID}/homes/${home}/activate-channel"),
			NOTIFICATION_STATUS(
					"/notification/notificationStatus/${userUUID}/${home}/${notificationType}"),

			NOTIFICATION_STATUS_MEASUREMENT(
					"/notification/notificationStatus/${userUUID}/${home}/${notificationType}/${measurementType}/${deliveryMode}"),
			GB_RAW_CONSUMPTION_DATA(
					"/streams/users/${userUUID}/homes/${home}/gws/${gateway}/meters/${meterId}/gb.json?t0=${t0}&t1=${t1}"),
			HAN_RAW_CONSUMPTION_DATA(
					"/streams/users/${userUUID}/homes/${home}/load.json?t0=${t0}&t1=${t1}"),
			USER_TEXT_SEARCH("/v2.0/users/search?text=${searchString}"),
			USER_PARAMETER_SEARCH("/v2.0/users?${queryParams}"),

			RECENT_USERS_LIST("/v2.0/users?pilotId=${queryParams}"),

			FILE_CHUNKS("/2.0/gbingestion/utilities/${pilotId}/utility-files/${fileName}/chunkErrorsList"),

			ALL_UTILITY_NOTIFICATIONS_FOR_USER(
					"/2.1/utility_notifications/users/${userUUID}"),
			ALL_UTILITY_NOTIFICATIONS_FOR_USER_WITH_DURATION(
					"/2.1/utility_notifications/users/${userUUID}?from=${t0}&to=${t1}"),
			UTILITY_NOTIFICATION_FOR_USER(
					"/2.1/utility_notifications/notifications/${userUUID}"),
			SUBSCRIBE_NOTIFICATION_CHANNEL_FOR_NOTIFICATION_TYPE(
					"/v2.1/notifications/users/${userUUID}/preferences/${notificationChannel}/subscribe"),
			DELIVERY_STATUS(
					"/notification/deliveryStatus/${userUUID}/${home}/${eventName}/mode/${mode}"),
			GB_RAW_CONSUMPTION_DATA_IN_CSV(
					"/streams/users/${userUUID}/homes/${home}/gws/${gateway}/meters/${meterId}/gb.csv?t0=${t0}&t1=${t1}"),
			RUN_AGGREGATIONS(
					"/billingdata/users/${userUUID}/homes/${home}/run/aggregations"),
			ADR_CREATE_EVENT(
					"/event/pilots/${pilotId}/events/${eventUUID}?type=ACTIONDR"),
			ADR_USER_EVENT_DETAILS(
					"/event/pilots/${pilotId}/events/${eventUUID}/users/${userUUID}?type=ACTIONDR"),
			ADR_EVENT_DETAILS(
					"/event/pilots/83/events/4ea36a9c-4250-4e98-a274-9cec215e8810?type=ACTIONDR"),
			SURVEY("/v2.0/users/${userUUID}/homes/1/survey?templateType=user_survey&user_role=ROLE_USER&channel=INSIGHTS&return_to_insights=true&locale=en_US"),
			SURVEY_V3("/v3.0/users/${userUUID}/homes/1/survey/default"),
			SURVEY_V3_GET("/v3.0/users/${userUUID}/homes/1/survey"),
			HYBRID_STATUS(
					"/v2.0/disaggregation/users/${userUUID}/homes/1/hybridStatus?measurementType=${measurementType}"),
			ITEMIZATION_DETAILS_EXTENDED(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/itemizationDetails?extended=true&t0=${t0}&t1=${t1}&hybrid=${hybrid}&mode=${mode}&totalFromInvoice=${totalFromInvoice}&showFailedBillingCycles=${showFailedBillingCycles}&round=${round}&showPercentage=${showPercentage}"),
			RAW_METER_DATA(
					"/streams/users/${userUUID}/homes/1/rawmeterdata?t0=${t0}&t1=${t1}"),
			INGESTION_ERRORS(
					"/2.0/gbingestion/utilities/${pilotId}/utility-files/${fileName}/${chunkFileName}/chunkErrors"),
			UNSUBSCRIBE_NOTIFICATION_CHANNEL_FOR_NOTIFICATION_TYPE(
					"/v2.1/notifications/users/${userUUID}/preferences/${notificationChannel}/unsubscribe"),
			USERS_CONFIGURATION("/entities/${userUUID}/configs"),
			UTILITY_BILLING_DATA(
					"/billingdata/users/${userUUID}/homes/${home}/utilitydata?t0=${t0}&t1=${t1}"),
			RAW_WEATHER_API(
					"/weather/${country}/${zip}?t0=${t0}&t1=${t1}&mode=${mode}"),
			GENERIC_SURVEY(
					"/v2.0/users/${userUUID}/homes/1/survey?locale=${locale}&templateType=${templateType}&user_role=ROLE_USER&channel=STANDALONE_HP_EMAIL&channel=INSIGHTS"),
			USER_METER_SEARCH(
					"/v2.0/users/search/meter/${meterNumber}?showMeterId=${boolean}&limit=${limit}"),
			FIELD_AUDIT_WORKLIST(
					"/v2.0/field-audit/${auditorId}/worklist"),
			LIST_CUSTOM_RECOMMENDATIONS_FOR_WORK_ORDER_ID(
					"/v2.0/custom_recommendations/user/${userUUID}?workOrderId=${workOrderId}"),
			CREATE_CUSTOM_RECOMMENDATIONS(
					"/v2.0/custom_recommendations/user/${userUUID}/work_order/${workOrderId}"),
			SCHEDULE_FIELD_AUDIT(
					"/v2.0/field-audit/${auditorId}/worklist/${workOrderId}"),
			FIELD_AUDIT_SURVEY(
					"/v2.0/users/${userUUID}/homes/1/survey?templateType=field_audit&locale=en_US&work_order_id=${workOrderId}&return_to_insights=true"),
			FIELD_AUDIT_SIGNATURE(
					"/v2.0/field-audit/${auditorId}/worklist/${workOrderId}/signature"),
			LIST_FIELD_AUDIT_SCHEDULES(
					"/v2.0/field-audit/schedules?pilotId=${pilotId}"),
			AB_TESTING_EXPERIMENT_CREATION("/2.1/ab-experiment"),

			AB_EXPERIMENT_CLUSTERS_BY_PILOTID("/2.1/ab-experiment/pilot/${pilotId}"),
			AB_TESTING_USER_ASSIGNMENT_TO_A_CLUSTER(
					"/2.1/ab-experiment/users/${userUUID}/cluster/${clusterId}"),
			SUBSCRIPTIONS_API_FOR_CLUSTER(
					"/v2.1/notifications/cluster/${clusterId}/subscriptions"),
			CLUSTER_FOR_USER("/2.1/ab-experiment/users/${userUUID}/clusters"),
			DELETE_BILL_CYCLES(
					"/billingdata/users/${userUUID}/homes/1/utilitydata"),

			BILL_PROJECTION_RESPONSE(
					"/2.1/users/${userUUID}/homes/1/billprojections"),

			TIME_BAND_DATA_UPLOAD(
					"/v2.0/submit-survey/users/${userUUID}"),
			BILL_PROJECTION_RESPONSE_USER(
					"/2.1/users/${userUUID}/homes/1/billprojections?measurementType=${measurementType}"),
			ITEMIZATION_WITH_HYBRID_QUERY(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/itemization?fromDate=${fromDate}&toDate=${toDate}&mode=${mode}&hybrid=${hybrid}"),
			GENERATE_PDF(
					"/analytics-server/v2.0/generate-pdf/${pdfType}"),
			RECO_META_DATA_AT_PILOT_LEVEL(
					"/recommendationsMetaData/pilots/${pilotId}"),
			RECO_META_DATA_AT_PILOT_LEVEL_PRODUCT(
					"/recommendationsMetaData/pilots/${pilotId}?productType=${mode}"),
			RECO_META_DATA_AT_PILOT_LEVEL_DETAILED(
					"/recommendationsMetaData/pilots/${pilotId}?showRecommendationDetailsList=true"),
			GATEWAY_DECOMMISSION(
					"/meta/users/${userUUID}/homes/${home}/gws/${gateway}/decommission"),
			UPDATE_ENTITY_CONFIG("/entities/${entityId}/configs"),
			TB_APP_DATA(
					"/streams/users/${userUUID}/homes/${home}/tbappdata/monthly.json?t0=${t0}&t1=${t1}&measurementType=${measurementType}"),
			RECOMMENDATIONS_LIST_FOR_AN_USER(
					"/users/${userUUID}/homes/1/recommendations?count=${count}&minimumRelevanceScore=${minimumRelevanceScore}&measurementType=${measurementType}"),
			GB_DATA_CASS(
					"/streams/users/${userUUID}/homes/${home}/gws/${gateway}/meters/${meterId}/gbdata"),
			NGEN_ASSIGNMENT("/nhs/${ntype}"),
			RECOMMENDATION_FEEDBACK(
					"/2.0/recommendation-feedback?billStart=${t0}&billEnd=${t1}"),
			INSIGHTS_HOME(
					"/v2.0/users/${userUUID}/homes/1/insights?t0=${t0}&t1=${t1}"),
			USERS_CONFIGURATION_DETAILS("/entities/user/${userUUID}/configs"),
			USERS_CONFIGURATION_DETAILS_EVENT_NAME("/entities/user/${userUUID}.event_name/configs"),

			GLOBAL_STRING_RESOURCES("/2.1/stringResources?locale=${locale}"),

			PILOT_STRING_RESOURCES("/2.1/stringResources/pilot/${entityId}?locale=${locale}"),

			CLUSTER_STRING_RESOURCES("/2.1/stringResources/cluster/${clusterId}?locale=${locale}"),
			GLOBAL_CONFIGURATION_DETAILS("/entities/GLOBAL/GLOBAL/configs"),
			PILOT_CONFIGURATION_DETAILS("/entities/pilot/${entityId}/configs"),
			AB_TESTING_EECLUSTERED("/v2.0/utilities/${pilotIdOrPilotUUID}"),

			TOU_PROGRAM("/2.1/tou-program/program-participation/userId/${userUUID}/appId/${applianceId}/"),

			AGGREGATED_COST("/billingdata/users/${userUUID}/homes/1/aggregatedCost/${applianceId}/${cType}?planNumber=${plan}&t0=${t0}&t1=${t1}&mode=month&tz=${timeZone}"),

			ITEMIZATION_DETAILS_WITH_PERCENTAGE(
					"/v2.0/users/${userUUID}/endpoints/${endpointUUID}/itemizationDetails?t0=${t0}&t1=${t1}&mode=${mode}&showPercentage=${showPercentage}"),
			BIDGELY_OAUTH("/oauth/token?grant_type=${grantType}&scope=${scope}"),
			LOOKER_OAUTH(
					"/api/3.0/login?client_id=${clientId}&client_secret=${clientSecret}"),
			LOOKER_GET_SPACES_UNDER_A_SPACE(
					"/api/3.0/spaces/${lookerSpaceId}/children"),
			LOOKER_GET_DASHBOARDS_UNDER_A_SPACE(
					"/api/3.0/spaces/${lookerSpaceId}/dashboards"),
			LOOKER_SCHEDLE_DASHBOARD(
					"/api/3.0/scheduled_plans/run_once"),
			NHS_API("/nhs/${ntype}/ids"),
			NHS_IDS_API("/nhs/${ntype}/ids/${nid}"),
			NEIGHBOURHOOD_METRICS(
					"/nhs/${ntype}/ids/${nid}/nmetrics/${metricType}/data?startTime=${startTime}&endTime=${endTime}"),
			PHONE_SURVEY_POST(
					"/v2.0/users/${userUUID}/homes/${home}/survey?templateType=phone_audit&user_role=ROLE_PHONE_AUDITOR&channel=PHONE_AUDIT_SURVEY&" +
							"return_to_insights=true&work_order_id=${workOrderId}&locale=en_US"),
			PHONE_FIELD_AUDIT_DELIVERY_MODE(
					"/v2.0/field-audit/user/${userUUID}/worklist/${auditorId}/audit-report"),
			SELECT_AUDIT_RECOMENDATIONS(
					"/2.0/recommendationsByAppliance/work_order/${workOrderId}/users/${userUUID}/home/1"),
			REPORT_NOTIFICATION_STATUS(
					"/notification/notificationStatus/${userUUID}/${home}/${notificationType}/${deliveryMode}"),
			NEIGHBOURHOOD_COMPARISION(
					"/v2.0/configs/neighbourhood_comparison/user/${userUUID}"),
			NEIGHBOURHOOD_DATA(
					"/v2.0/users/${userUUID}/homes/1/neighbourhood-data?startTime=${t0}&endTime=${t1}&measurementType=${measurementType}"),

			NEIGHBOURHOOD_DATA_MONTH_MODE(
					"/v2.0/users/${userUUID}/homes/1/neighbourhood-data?mode=month&startTime=${t0}&endTime=${t1}&measurementType=${measurementType}"),
			RECOMMENDATION_DETAILS(
					"/2.0/recommendations/users/${userUUID}/home/${home}?bcStartTs=${t0}&count=${count}&allowDuplicates=${allowDuplicates}&appliances=${applianceIds}&locale=${locale}&measurementType=${measurementType}"),
			CHECK_CLUSTER_STATUS("/bidgely-qa/api/clusters/$xplentyClusterId"),
			CHECK_XPLENTY_CLUSTER_STATUS(
					"/bidgely-qa/api/clusters/${xplentyClusterId}"),
			CREATE_XPLENTY_CLUSTERS("/bidgely-qa/api/clusters"),
			LIST_XPLENTY_CLUSTERS(
					"/bidgely-qa/api/clusters?sort=created&direction=desc"),
			TRIGGER_XPLENTY_JOB("/bidgely-qa/api/jobs"),
			CHECK_XPLENTY_JOB_STATUS("/bidgely-qa/api/jobs/${xplentyJobId}"),
			RECOMMENDATIONS_FOR_API_TO_FILE(
					"/users/${userUUID}/homes/1/recommendations?count=${count}&minimumRelevanceScore=${minimumRelevanceScore}&bc_start=${bcStartTs}"),
			AGGREGATED_CONSUMPTION_DATA_WITH_MTYPE(
					"/billingdata/users/${userUUID}/homes/${home}/consumption/ctypes/energy_consumption?t0=${t0}&t1=${t1}&mode=${mode}&measurementType=${measurementType}"),
			AGGREGATED_CONSUMPTION_DATA_WITH_TOU(
					"/billingdata/users/${userUUID}/homes/${home}/consumption/ctypes/tou?t0=${t0}&t1=${t1}&mode=${mode}"),
			AGGREGATED_BILLING_COST_WITH_MTYPE(
					"/billingdata/users/${userUUID}/homes/${home}/consumption/ctypes/billing_cost?t0=${t0}&t1=${t1}&mode=${mode}&measurementType=${measurementType}"),
			BROWSE_WITH_MTYPE(
					"/2.1/users/${userUUID}/homes/${home}/browse?t0=${t0}&t1=${t1}&mode=${mode}&measurementType=${measurementType}"),
			PHONE_SURVEY_GET(
					"/v2.0/users/${userUUID}/homes/1/survey?templateType=phone_audit&user_role=ROLE_PHONE_AUDITOR&channel=PHONE_AUDIT_SURVEY&locale=en_US"),
			DASHBOARD_SURVEY_GET(
					"/v2.0/users/${userUUID}/homes/1/survey?templateType=user_survey&user_role=ROLE_USER&channel=INSIGHTS&locale=en_US"),
			FIELD_AUDIT_SURVEY_GET(
					"/v2.0/users/${userUUID}/homes/1/survey?templateType=field_audit&locale=en_US&return_to_insights=true"),
			GET_BILLING_CYCLE_WITH_IDENTIFIER_FOR_PILOT(
					"/2.1/utilityBillingCycles/utility/${pilotId}/identifier/${identifierId}"),
			BILL_ANALYSER_API("/2.1/call-center/users/${userUUID}/compare?firstBillStart=${t0}&secondBillStart=${t1}&overrideActualDiff=true"),
			PILOT_CONFIG_ENDPOINT_API("/v2.0/configs/${configType}/${entityType}/${pilotId}"),
			BILL_ANALYSER_ADD_NOTES_API("/2.1/call-center/calldata"),
			BILL_ANALYSER_NOTES_DETAILS_API("/2.1/call-center/users/${userUUID}/call-details"),
			BILL_ANALYSER_SURVEY_API("/v2.0/users/${userUUID}/homes/1/survey?locale=${locale}&templateType=${templateType}"),
			INSIGHT_DEFINITION_FOR_PILOT_API("/v2.0/insights/${pilotId}/"),
			INTERESTING_INSIGHTS_API("/v2.1/users/${userUUID}/homes/1/insights?from=${t0}&to=${t1}&productType=${productType}&insightsPerGroup=${count}"),
			USER_TYPE("/meta/users/${userUUID}/usertype"),
			WHOLE_HOME_WEB_RECOMMENDATIONS_DETAILS("/2.0/recommendations/users/${userUUID}/home/${home}?locale=${locale}&bcStartTs=${t0}&pageId=WholeHome&productType=${productType}&measurementType=${measurementType}"),
			USER_ACCOUNT_PROGRAMS("/v3.0/internal/users/${userUUID}/homes/${home}/account/programs"),
			NOTIFICATION_CONTENT("/v3.0/internal/notifications/${userUUID}/content"),
			APPLIANCE_LEVEL_RECOMMENDATIONS_DETAILS(
					"/2.0/recommendations/users/${userUUID}/home/${home}?locale=${locale}&bcStartTs=${t0}&applianceId=${applianceId}&productType=${productType}"),
			UPDATE_USER_AUDIT_REPORT_STATUS_API("/v2.0/field-audit/${auditorId}/worklist/${workOrderId}/FA_REPORT_SENT"),
			USER_LOGIN_LESS("/v2.0/user-auth/cipher?user-id=${userUUID}&pilot-id=${pilotId}"),
			USER_ALERT_PREFERENCE("/v2.0/dashboard/users/${userUUID}/communication/alert-preference"),
			ITEMIZATION_WIDGET_DATA("/v2.0/dashboard/users/${userUUID}/itemization-widget-data?measurement-type=${measurementType}&date-format=MONTH_IN_WORDS_SPACE_DAY_COMMA_YEAR&locale=${locale}"),
			ENV_IMPACT_DATA("/v2.0/dashboard/users/${userUUID}/env-impact-widget-data?measurement-type=${measurementType}&mode=${mode}&start=${t0}&end=${t1}&locale=${locale}"),
			WEATHER_IMPACT_DATA("/v2.0/dashboard/users/${userUUID}/weather-impact?measurement-type=${measurementType}&locale=${locale}"),
			SPONSORED_REPO("/v2.0/dashboard/users/${userUUID}/sponsored-reco?measurement-type=${measurementType}"),
			INSIGHT_FEED_DATA("/v2.0/dashboard/users/${userUUID}/insight-feed-data?date-format=MONTH_IN_WORDS_SPACE_DAY_COMMA_YEAR&locale=${locale}"),
			RECO_FEED("/v2.0/dashboard/users/${userUUID}/reco-feed-data?locale=${locale}"),
			RECOMMENDATIONS_RECO_FEED("/2.0/recommendations/users/${userUUID}/home/1?measurement-type=${measurementType}&appliances=${applianceId}&locale=${locale}&keepNoApplianceReco=true&show-only-tips=true"),
			USAGE_WIDGET_DATA("/v2.0/dashboard/users/${userUUID}/usage-widget-data?measurement-type=${measurementType}&date-format=MONTH_IN_WORDS_SPACE_DAY_COMMA_YEAR&locale=${locale}"),
			FETCH_NEIGHBOURHOOD_CONSUMPTION_VALUES("/v2.0/users/${userUUID}/homes/1/nc-user-category-thresholds?startTime=${t0}&endTime=${t1}&measurement-type=${measurementType}"),
			APPLIANCE_CONSUMPTION("/billingdata/users/${userUUID}/homes/1/appliances/consumption?t0=${t0}" + "&mode=${mode}"),
			USAGE_CHART_DETAILS("/v2.0/dashboard/users/${userUUID}/usage-chart-details?measurement-type=${measurementType}&mode=month&start=${t0}&end=${t1}&date-format=DATE_TIME&locale=en_US&next-bill-cycle=false&show-at-granularity=false&skip-ongoing-cycle=false"),

			HOUR_LEVEL_COST_CONSUMPTION("/1.0/tou-bill-projection/hour-level-cost-consumption-map/user/${userUUID}/hid/1/measurementType/${measurementType}/today/${t1}"),
			SHC_PDF_WIDGET("/v2.0/pdf-report/users/${userUUID}/shc-bargraph-and-widget?measurement-type=${measurementType}"),
			FEATURE_CONFIG_MAPPING("/dynamic-cx/v1.0/feature-config-mapping/get?product=${productType}"),
			FEATURE_RESOLUTION_MAPPING("/dynamic-cx/v1.0/feature-metric/pilot/${pilotId}?product=${productType}&level=${level}"),

			CREATE_USER_ATTRIBUTES("/utility-user-attributes/v1.0/entities/${pilotId}/attributes/upload"),
			USER_ATTRIBUTE_MAPPING("/user-attributes/v1.0/users/${userUUID}/attributes"),
			GET_USER_ATTRIBUTES("/utility-user-attributes/v1.0/entities/${pilotId}/attributes"),
			RATE_COMPARISON("/v3.0/users/${userUUID}/homes/${ratePlan}/rate-comparison/candidate-rates?measurement-type=ELECTRIC"),
			AGGREGATION_MESSAGE_ROUTE("/message-router/queue-name/AggregationReadyUsers"),
			SWITCH_RATE_COMPARISON("/v3.0/users/${userUUID}/homes/1/rate-comparison?measurement-type=ELECTRIC&locale=en_US&scale-values=false"),
			FETCH_NOTIFICATIONS("/2.1/utility_notifications/10037/fetch-notifications?startTime=${startTime}&endTime=${endTime}&offset=0&limit=10&eventName=${eventName}&notificationStatus=REVOKED"),
			FETCH_NOTIFICATIONS_FOR_USER("/2.1/utility_notifications/fetch-notifications-for-user?userID=${userUUID}&startTime=${startTime}&endTime=${endTime}&notificationStatus=${notificationStatus}&eventName=${notificationType}"),
			TOU_SAVINGS_POTENTIAL("/1.0/tou-savings-potential/user/${userUUID}/eventName/${eventName}/measurementType/${measurementType}/ratePlanNumber/${ratePlan}?billStart=${startTime}");


			private              String uri;
			private static final Logger logger = LoggerFactory.getLogger(ApisUrl.class);

			GetUriFor(String uri) {
				this.uri = uri;
			}

			/**
			 * Get the URL for the API
			 *
			 * @param valueMap
			 * @return
			 * @throws BidgelyExceptions
			 */
			public String toString(Map<String, String> valueMap)
					throws BidgelyExceptions {
				StrSubstitutor substitutor = new StrSubstitutor(valueMap);
				String url = substitutor.replace(uri);
				logger.info(url);
				if (url.contains("${")) {
					throw new BidgelyExceptions("Provide all parameters for " +
							this.name() + ". Current URL: " + url);
				}
				return url;
			}

			@Override
			public String toString() {
				return uri;
			}
		}
}
