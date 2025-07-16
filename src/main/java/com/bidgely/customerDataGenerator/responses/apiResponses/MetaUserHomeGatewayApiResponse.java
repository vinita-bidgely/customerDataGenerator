package com.bidgely.customerDataGenerator.responses.apiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaUserHomeGatewayApiResponse {
	
	private static final Logger logger = LoggerFactory.getLogger(MetaUserHomeGatewayApiResponse.class);

	String  fwdPath;

	String  gbReadingType;

	Integer confTime;

	String  model;

	String  desc;

	Boolean notifyEmailStatusChange;

	String  data1;

	Boolean decommissioned;

	String  data2;

	String  type;

	String  id;

	String  confError;

	String  notifyEmailState;

	String  token;

	String  linkedGateway;

	Boolean notifyEmailWeeklyStatus;

	String  mfq;

	String  path;

	Boolean auto;

	Integer maxGapLength;

	String  endpointId;

	Boolean hasFastPollMode;

	Boolean authStatus;

	public String getFwdPath() {
		return fwdPath;
	}

	public String getGbReadingType() {
		return gbReadingType;
	}

	public Integer getConfTime() {
		return confTime;
	}

	public String getModel() {
		return model;
	}

	public String getDesc() {
		return desc;
	}

	public Boolean isNotifyEmailStatusChange() {
		return notifyEmailStatusChange;
	}

	public String getData1() {
		return data1;
	}

	public Boolean isDecommissioned() {
		return decommissioned;
	}

	public String getData2() {
		return data2;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public String getConfError() {
		return confError;
	}

	public String getNotifyEmailState() {
		return notifyEmailState;
	}

	public String getToken() {
		return token;
	}

	public String getLinkedGateway() {
		return linkedGateway;
	}

	public Boolean isNotifyEmailWeeklyStatus() {
		return notifyEmailWeeklyStatus;
	}

	public String getMfq() {
		return mfq;
	}

	public String getPath() {
		return path;
	}

	public Boolean isAuto() {
		return auto;
	}

	public Integer getMaxGapLength() {
		return maxGapLength;
	}

	public String getEndpointId() {
		return endpointId;
	}

	public Boolean isHasFastPollMode() {
		return hasFastPollMode;
	}

	public Boolean isAuthStatus() {
		return authStatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MetaUserHomeGatewayApiResponse) {
			MetaUserHomeGatewayApiResponse metaUserHomeGatewayApiResponse = (MetaUserHomeGatewayApiResponse) obj;
			if (this.fwdPath != null) {
				if (!this.fwdPath.equals(metaUserHomeGatewayApiResponse.getFwdPath())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected fwdPath : " + this.fwdPath + "\nActual fwdPath :" + metaUserHomeGatewayApiResponse.getFwdPath());
					return false;
				}
			}
			if (this.gbReadingType != null) {
				if (!this.gbReadingType.equals(metaUserHomeGatewayApiResponse.getGbReadingType())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected gbReadingType : " + this.gbReadingType + "\nActual gbReadingType :" + metaUserHomeGatewayApiResponse.getGbReadingType());
					return false;
				}
			}
			if (this.confTime != null) {
				if (!this.confTime.equals(metaUserHomeGatewayApiResponse.getConfTime())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected confTime : " + this.confTime + "\nActual confTime :" + metaUserHomeGatewayApiResponse.getConfTime());
					return false;
				}
			}
			if (this.model != null) {
				if (!this.model.equals(metaUserHomeGatewayApiResponse.getModel())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected model : " + this.model + "\nActual model :" + metaUserHomeGatewayApiResponse.getModel());
					return false;
				}
			}
			if (this.desc != null) {
				if (!this.desc.equals(metaUserHomeGatewayApiResponse.getDesc())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected desc : " + this.desc + "\nActual desc :" + metaUserHomeGatewayApiResponse.getDesc());
					return false;
				}
			}
			if (this.notifyEmailStatusChange != null) {
				if (!this.notifyEmailStatusChange.equals(metaUserHomeGatewayApiResponse.isNotifyEmailStatusChange())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected notifyEmailStatusChange : " + this.notifyEmailStatusChange + "\nActual notifyEmailStatusChange :" + metaUserHomeGatewayApiResponse.isNotifyEmailStatusChange());
					return false;
				}
			}
			if (this.data1 != null) {
				if (!this.data1.equals(metaUserHomeGatewayApiResponse.getData1())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected data1 : " + this.data1 + "\nActual data1 :" + metaUserHomeGatewayApiResponse.getData1());
					return false;
				}
			}
			if (this.decommissioned != null) {
				if (!this.decommissioned.equals(metaUserHomeGatewayApiResponse.isDecommissioned())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected decommissioned : " + this.decommissioned + "\nActual decommissioned :" + metaUserHomeGatewayApiResponse.isDecommissioned());
					return false;
				}
			}
			if (this.data2 != null) {
				if (!this.data2.equals(metaUserHomeGatewayApiResponse.getData2())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected data2 : " + this.data2 + "\nActual data2 :" + metaUserHomeGatewayApiResponse.getData2());
					return false;
				}
			}
			if (this.type != null) {
				if (!this.type.equals(metaUserHomeGatewayApiResponse.getType())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected type : " + this.type + "\nActual type :" + metaUserHomeGatewayApiResponse.getType());
					return false;
				}
			}
			if (this.id != null) {
				if (!this.id.equals(metaUserHomeGatewayApiResponse.getId())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected id : " + this.id + "\nActual id :" + metaUserHomeGatewayApiResponse.getId());
					return false;
				}
			}

			if (this.confError != null) {
				if (!this.confError.equals(metaUserHomeGatewayApiResponse.getConfError())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected confError : " + this.confError + "\nActual confError :" + metaUserHomeGatewayApiResponse.getConfError());
					return false;
				}
			}
			if (this.notifyEmailState != null) {
				if (!this.notifyEmailState.equals(metaUserHomeGatewayApiResponse.getNotifyEmailState())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected notifyEmailState : " + this.notifyEmailState + "\nActual notifyEmailState :" + metaUserHomeGatewayApiResponse.getNotifyEmailState());
					return false;
				}
			}
			if (this.token != null) {
				if (!this.token.equals(metaUserHomeGatewayApiResponse.getToken())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected token : " + this.token + "\nActual token :" + metaUserHomeGatewayApiResponse.getToken());
					return false;
				}
			}
			if (this.notifyEmailWeeklyStatus != null) {
				if (!this.notifyEmailWeeklyStatus.equals(metaUserHomeGatewayApiResponse.isNotifyEmailWeeklyStatus())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected notifyEmailWeeklyStatus : " + this.notifyEmailWeeklyStatus + "\nActual notifyEmailWeeklyStatus :" + metaUserHomeGatewayApiResponse.isNotifyEmailWeeklyStatus());
					return false;
				}
			}
			if (this.mfq != null) {
				if (!this.mfq.equals(metaUserHomeGatewayApiResponse.getMfq())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected mfq : " + this.mfq + "\nActual mfq :" + metaUserHomeGatewayApiResponse.getMfq());
					return false;
				}
			}
			if (this.path != null) {
				if (!this.path.equals(metaUserHomeGatewayApiResponse.getPath())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected path : " + this.path + "\nActual path :" + metaUserHomeGatewayApiResponse.getPath());
					return false;
				}
			}
			if (this.auto != null) {
				if (!this.auto.equals(metaUserHomeGatewayApiResponse.isAuto())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected auto : " + this.auto + "\nActual auto :" + metaUserHomeGatewayApiResponse.isAuto());
					return false;
				}
			}
			if (this.maxGapLength != null) {
				if (!this.maxGapLength.equals(metaUserHomeGatewayApiResponse.getMaxGapLength())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected maxGapLength : " + this.maxGapLength + "\nActual maxGapLength :" + metaUserHomeGatewayApiResponse.getMaxGapLength());
					return false;
				}
			}
			if (this.endpointId != null) {
				if (!this.endpointId.equals(metaUserHomeGatewayApiResponse.getEndpointId())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected endpointId : " + this.endpointId + "\nActual endpointId :" + metaUserHomeGatewayApiResponse.getEndpointId());
					return false;
				}
			}
			if (this.hasFastPollMode != null) {
				if (!this.hasFastPollMode.equals(metaUserHomeGatewayApiResponse.isHasFastPollMode())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected hasFastPollMode : " + this.hasFastPollMode + "\nActual hasFastPollMode :" + metaUserHomeGatewayApiResponse.isHasFastPollMode());
					return false;
				}
			}
			if (this.authStatus != null) {
				if (!this.authStatus.equals(metaUserHomeGatewayApiResponse.isAuthStatus())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected authStatus : " + this.authStatus + "\nActual authStatus :" + metaUserHomeGatewayApiResponse.isAuthStatus());
					return false;
				}
			}
			if (this.linkedGateway != null) {
				if (!this.linkedGateway.equals(metaUserHomeGatewayApiResponse.getLinkedGateway())) {
					logger.error("MetaUserHomeGatewayAPIResponse : expected linkedGateway : " + this.linkedGateway + "\nActual linkedGateway :" + metaUserHomeGatewayApiResponse.getLinkedGateway());
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
