package com.bidgely.responses.entries;

import java.util.UUID;

public class EndPointsRegistrationEntry {
	private String liveStatsUrl;

	private String eventsUrl;

	private String endpointUrl;

	private String itemizationUrl;

	private String recommendationsUrl;

	private String insightsUrl;

	private UUID uuid;

	public String getLiveStatsUrl() {
		return liveStatsUrl;
	}

	public void setLiveStatsUrl(String liveStatsUrl) {
		this.liveStatsUrl = liveStatsUrl;
	}

	public String getEventsUrl() {
		return eventsUrl;
	}

	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public String getItemizationUrl() {
		return itemizationUrl;
	}

	public void setItemizationUrl(String itemizationUrl) {
		this.itemizationUrl = itemizationUrl;
	}

	public String getRecommendationsUrl() {
		return recommendationsUrl;
	}

	public void setRecommendationsUrl(String recommendationsUrl) {
		this.recommendationsUrl = recommendationsUrl;
	}

	public String getInsightsUrl() {
		return insightsUrl;
	}

	public void setInsightsUrl(String insightsUrl) {
		this.insightsUrl = insightsUrl;
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "ClassPojo [liveStatsUrl = " + liveStatsUrl + ", eventsUrl = " + eventsUrl + ", endpointUrl = "
				+ endpointUrl + ", itemizationUrl = " + itemizationUrl + ", recommendationsUrl = " + recommendationsUrl
				+ ", insightsUrl = " + insightsUrl + ", uuid = " + uuid + "]";
	}

}