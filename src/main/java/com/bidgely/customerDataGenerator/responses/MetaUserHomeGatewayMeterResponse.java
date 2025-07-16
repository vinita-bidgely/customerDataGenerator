package com.bidgely.customerDataGenerator.responses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaUserHomeGatewayMeterResponse {

	private static final Logger logger = LoggerFactory.getLogger(MetaUserHomeGatewayMeterResponse.class);
	private String  metertype;
	private String  masterPath;
	private Boolean dflt;
	private String  userDesc;
	private String  model;
	private String  id;
	private String  fwdPath;
	private String  path;
	private String  measurement;
	private String  desc;
	private String  token;
	private String  firstDataTimestamp;
	private String  contractEnd;
	private String  contractStart;
	private String  solar;
	private String  lastDataTimestamp;

	public String getMetertype() {
		return metertype;
	}

	public String getFirstDataTimestamp() {
		return firstDataTimestamp;
	}

	public String getContractEnd() {
		return contractEnd;
	}

	public String getContractStart() {
		return contractStart;
	}

	public String getSolar() {
		return solar;
	}

	public String getLastDataTimestamp() {
		return lastDataTimestamp;
	}

	public String getMeterType() {
		return metertype;
	}

	public String getMasterPath() {
		return masterPath;
	}

	public Boolean getDflt() {
		return dflt;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public String getModel() {
		return model;
	}

	public String getId() {
		return id;
	}

	public String getFwdPath() {
		return fwdPath;
	}

	public String getPath() {
		return path;
	}

	public String getMeasurement() {
		return measurement;
	}

	public String getDesc() {
		return desc;
	}

	public String getToken() {
		return token;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MetaUserHomeGatewayMeterResponse) {
			MetaUserHomeGatewayMeterResponse metaUserHomeGatewayMeterResponse = (MetaUserHomeGatewayMeterResponse) obj;

			if (this.metertype != null) {
				if (!this.metertype.equals(metaUserHomeGatewayMeterResponse.getMeterType())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected metertype : " + this.metertype + "\nActual metertype :" + metaUserHomeGatewayMeterResponse.getMeterType());
					return false;
				}
			}
			if (this.masterPath != null) {
				if (!this.masterPath.equals(metaUserHomeGatewayMeterResponse.getMasterPath())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected masterPath : " + this.masterPath + "\nActual masterPath :" + metaUserHomeGatewayMeterResponse.getMasterPath());
					return false;
				}
			}
			if (this.dflt != null) {
				if (!this.dflt.equals(metaUserHomeGatewayMeterResponse.getDflt())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected dflt : " + this.dflt + "\nActual dflt :" + metaUserHomeGatewayMeterResponse.getDflt());
					return false;
				}
			}
			if (this.userDesc != null) {
				if (!this.userDesc.equals(metaUserHomeGatewayMeterResponse.getUserDesc())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected userDesc : " + this.userDesc + "\nActual userDesc :" + metaUserHomeGatewayMeterResponse.getUserDesc());
					return false;
				}
			}
			if (this.model != null) {
				if (!this.model.equals(metaUserHomeGatewayMeterResponse.getModel())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected model : " + this.model + "\nActual model :" + metaUserHomeGatewayMeterResponse.getModel());
					return false;
				}
			}
			if (this.id != null) {
				if (!this.id.equals(metaUserHomeGatewayMeterResponse.getId())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected id : " + this.id + "\nActual id :" + metaUserHomeGatewayMeterResponse.getId());
					return false;
				}
			}
			if (this.fwdPath != null) {
				if (!this.fwdPath.equals(metaUserHomeGatewayMeterResponse.getFwdPath())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected fwdPath : " + this.fwdPath + "\nActual fwdPath :" + metaUserHomeGatewayMeterResponse.getFwdPath());
					return false;
				}
			}
			if (this.path != null) {
				if (!this.path.equals(metaUserHomeGatewayMeterResponse.getPath())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected path : " + this.path + "\nActual path :" + metaUserHomeGatewayMeterResponse.getPath());
					return false;
				}
			}
			if (this.measurement != null) {
				if (!this.measurement.equals(metaUserHomeGatewayMeterResponse.getMeasurement())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected measurement : " + this.measurement + "\nActual measurement :" + metaUserHomeGatewayMeterResponse.getMeasurement());
					return false;
				}
			}
			if (this.desc != null) {
				if (!this.desc.equals(metaUserHomeGatewayMeterResponse.getDesc())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected desc : " + this.desc + "\nActual desc :" + metaUserHomeGatewayMeterResponse.getDesc());
					return false;
				}
			}
			if (this.token != null) {
				if (!this.token.equals(metaUserHomeGatewayMeterResponse.getToken())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected token : " + this.token + "\nActual token :" + metaUserHomeGatewayMeterResponse.getToken());
					return false;
				}
			}
			if (this.firstDataTimestamp != null) {
				if (!this.firstDataTimestamp.equals(metaUserHomeGatewayMeterResponse.getFirstDataTimestamp())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected firstDataTimestamp : " + this.firstDataTimestamp + "\nActual firstDataTimestamp :" + metaUserHomeGatewayMeterResponse.getFirstDataTimestamp());
					return false;
				}
			}
			if (this.contractEnd != null) {
				if (!this.contractEnd.equals(metaUserHomeGatewayMeterResponse.getContractEnd())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected contractEnd : " + this.contractEnd + "\nActual contractEnd :" + metaUserHomeGatewayMeterResponse.getContractEnd());
					return false;
				}
			}
			if (this.solar != null) {
				if (!this.solar.equals(metaUserHomeGatewayMeterResponse.getSolar())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected solar : " + this.solar + "\nActual solar :" + metaUserHomeGatewayMeterResponse.getSolar());
					return false;
				}
			}
			if (this.lastDataTimestamp != null) {
				if (!this.lastDataTimestamp.equals(metaUserHomeGatewayMeterResponse.getLastDataTimestamp())) {
					logger.error("MetaUserHomeGatewayMeterResponse : expected lastDataTimestamp : " + this.lastDataTimestamp + "\nActual lastDataTimestamp :" + metaUserHomeGatewayMeterResponse.getLastDataTimestamp());
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
