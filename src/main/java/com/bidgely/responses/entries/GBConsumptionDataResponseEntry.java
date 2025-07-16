package com.bidgely.responses.entries;

import com.bidgely.commons.SharedResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GBConsumptionDataResponseEntry
		implements
		Comparable<GBConsumptionDataResponseEntry> {

	private static final Logger logger = LoggerFactory.getLogger(GBConsumptionDataResponseEntry.class);
	private Integer duration;

	private Integer time;

	private Integer direction;

	private String  usagePointId;

	private Double  value;

	private Integer dataQuality;

	private Double cost;

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getUsagePointId() {
		return usagePointId;
	}

	public void setUsagePointId(String usagePointId) {
		this.usagePointId = usagePointId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getDataQuality() {
		return dataQuality;
	}

	public void setDataQuality(Integer dataQuality) {
		this.dataQuality = dataQuality;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object obj) {

		boolean notFound = false;
		if (obj instanceof GBConsumptionDataResponseEntry) {
			GBConsumptionDataResponseEntry gbConsumptionDataResponseEntry = (GBConsumptionDataResponseEntry) obj;

			if (this.duration != null) {
				if (!this.duration.equals(gbConsumptionDataResponseEntry.getDuration())) {
					notFound = true;
				}
			}
			if (this.time != null) {
				if (!this.time.equals(gbConsumptionDataResponseEntry.getTime())) {
					notFound = true;
				}
			}
			if (this.direction != null) {
				if (!this.direction.equals(gbConsumptionDataResponseEntry.getDirection())) {
					notFound = true;
				}
			}
			if (this.usagePointId != null) {
				if (!this.usagePointId.equals(gbConsumptionDataResponseEntry.getUsagePointId())) {
					notFound = true;
				}
			}
			if (this.value != null) {
				if (!this.value.equals(gbConsumptionDataResponseEntry.getValue())) {
					notFound = true;
				}
			}
			if (this.dataQuality != null) {
				if (!this.dataQuality.equals(gbConsumptionDataResponseEntry.getDataQuality())) {
					notFound = true;
				}
			}
			if (this.cost != null) {
				if (!this.cost.equals(gbConsumptionDataResponseEntry.getCost())) {
					notFound = true;
				}
			}
			if(notFound){
				logger.error("GBConsumptionDataResponse : expected entry ->  Duration :"+ this.duration +" time: " +this.time +" direction: " +this.direction
						+" usagePointId: " +this.usagePointId+" value: " +this.value+" dataQuality: " +this.dataQuality+" cost: " +this.cost+
						"\nActual Response :\n" +
						SharedResources.gson.toJson(gbConsumptionDataResponseEntry));
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean compare(Object obj) {

		if (obj instanceof GBConsumptionDataResponseEntry) {
			GBConsumptionDataResponseEntry gbConsumptionDataResponseEntry = (GBConsumptionDataResponseEntry) obj;

			if (this.duration != null) {
				if (!this.duration.equals(gbConsumptionDataResponseEntry.getDuration())) {

					return false;
				}
			}
			if (this.time != null) {
				if (!this.time.equals(gbConsumptionDataResponseEntry.getTime())) {
					return false;
				}
			}
			if (this.direction != null) {
				if (!this.direction.equals(gbConsumptionDataResponseEntry.getDirection())) {
					return false;
				}
			}
			if (this.value != null) {
				if (!this.value.equals(gbConsumptionDataResponseEntry.getValue())) {
					return false;
				}
			}
			if (this.cost != null) {
				if (!this.cost.equals(gbConsumptionDataResponseEntry.getCost())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(GBConsumptionDataResponseEntry entry) {
		int entryTime = entry.getTime();
		return this.time - entryTime;
	}
}
