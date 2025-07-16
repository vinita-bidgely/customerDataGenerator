package com.bidgely.customerDataGenerator.responses.entries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * A POJO for UserManagementHomeAccountsEntry.
 *
 * @author Akshata
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserManagementHomeAccountsEntry {

	private String                             occupantType;

	private String                             ownershipType;

	private Integer                            neighbourhoodId;

	private String                             spaceHeatingType;

	private String                             state;

	private String                             countryCode;

	private Integer                            numBedrooms;

	private String                             timeZone;

	private String                             addressType;

	private String                             city;

	private Integer                            numOccupants;

	private UserManagementHomeRateEntry        rate;

	private String                             propertyType;

	private String                             postalCode;

	private String                             neighbourhoodType;

	private String                             waterHeatingType;

	private String                             phoneNumber;

	private List<UserManagementApplianceEntry> appliances;

	private String                             address;

	private Integer                            lastRawDataArchivedTS;

	private String                             propertyAgeType;

	private String                             locality;

	private String                             supplementaryNhoodIds;

	private Boolean                            showDevicePairing;

	private Boolean                            hasSolar;

	private String                             ratesSchedule;

	private Integer                            yearBuilt;

	private String                             webDataFlowTimeStamp;

	private String                             mobileDataFlowTimeStamp;

	private String                             mailingAddress;

	private String                             latitude;

	private String                             longitude;

	private String                              businessName;

	public String getBusinessName() {
		return businessName;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getMailingAddress(){
		return mailingAddress;
	}

	public String getOccupantType() {
		return occupantType;
	}

	public void setOccupantType(String occupantType) {
		this.occupantType = occupantType;
	}

	public String getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}

	public Integer getNeighbourhoodId() {
		return neighbourhoodId;
	}

	public void setNeighbourhoodId(Integer neighbourhoodId) {
		this.neighbourhoodId = neighbourhoodId;
	}

	public String getSpaceHeatingType() {
		return spaceHeatingType;
	}

	public void setSpaceHeatingType(String spaceHeatingType) {
		this.spaceHeatingType = spaceHeatingType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getNumBedrooms() {
		return numBedrooms;
	}

	public void setNumBedrooms(Integer numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getNumOccupants() {
		return numOccupants;
	}

	public void setNumOccupants(Integer numOccupants) {
		this.numOccupants = numOccupants;
	}

	public UserManagementHomeRateEntry getRate() {
		return rate;
	}

	public void setRate(UserManagementHomeRateEntry rate) {
		this.rate = rate;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNeighbourhoodType() {
		return neighbourhoodType;
	}

	public void setNeighbourhoodType(String neighbourhoodType) {
		this.neighbourhoodType = neighbourhoodType;
	}

	public String getWaterHeatingType() {
		return waterHeatingType;
	}

	public void setWaterHeatingType(String waterHeatingType) {
		this.waterHeatingType = waterHeatingType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<UserManagementApplianceEntry> getAppliances() {
		return appliances;
	}

	public void setAppliances(List<UserManagementApplianceEntry> appliances) {
		this.appliances = appliances;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPropertyAgeType() {
		return propertyAgeType;
	}

	public void setPropertyAgeType(String propertyAgeType) {
		this.propertyAgeType = propertyAgeType;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getSupplementaryNhoodIds() {
		return supplementaryNhoodIds;
	}

	public void setSupplementaryNhoodIds(String supplementaryNhoodIds) {
		this.supplementaryNhoodIds = supplementaryNhoodIds;
	}

	public Boolean getShowDevicePairing() {
		return showDevicePairing;
	}

	public void setShowDevicePairing(Boolean showDevicePairing) {
		this.showDevicePairing = showDevicePairing;
	}

	public Boolean getHasSolar() {
		return hasSolar;
	}

	public void setHasSolar(Boolean hasSolar) {
		this.hasSolar = hasSolar;
	}

	public String getRatesSchedule() {
		return ratesSchedule;
	}

	public void setRatesSchedule(String ratesSchedule) {
		this.ratesSchedule = ratesSchedule;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(Integer yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public Integer getLastRawDataArchivedTS() {
		return lastRawDataArchivedTS;
	}

	public void setLastRawDataArchivedTS(Integer lastRawDataArchivedTS) {
		this.lastRawDataArchivedTS = lastRawDataArchivedTS;
	}

	public String getMobileDataFlowTimeStamp() {
		return mobileDataFlowTimeStamp;
	}

	public void setMobileDataFlowTimeStamp(String mobileDataFlowTimeStamp) {
		this.mobileDataFlowTimeStamp = mobileDataFlowTimeStamp;
	}

	public String getWebDataFlowTimeStamp() {
		return webDataFlowTimeStamp;
	}

	public void setWebDataFlowTimeStamp(String webDataFlowTimeStamp) {
		this.webDataFlowTimeStamp = webDataFlowTimeStamp;
	}

	public boolean equals(Object obj) {

		if (obj instanceof UserManagementHomeAccountsEntry) {
			UserManagementHomeAccountsEntry userManagementHomeAccountsEntry = (UserManagementHomeAccountsEntry) obj;
			if (!(this.ownershipType == null ? userManagementHomeAccountsEntry.getOwnershipType() == null
					: this.ownershipType.equals(userManagementHomeAccountsEntry.getOwnershipType()))
					|| !(this.waterHeatingType == null ? userManagementHomeAccountsEntry.getWaterHeatingType() == null
					: this.waterHeatingType.equals(userManagementHomeAccountsEntry.getWaterHeatingType()))
					|| !(this.city == null ? userManagementHomeAccountsEntry.getCity() == null
					: this.city.equals(userManagementHomeAccountsEntry.getCity()))
					|| !(this.neighbourhoodType == null ? userManagementHomeAccountsEntry.getNeighbourhoodType() == null
					: this.neighbourhoodType.equals(userManagementHomeAccountsEntry.getNeighbourhoodType()))
					|| !(this.numBedrooms == null ? userManagementHomeAccountsEntry.getNumBedrooms() == null
					: this.numBedrooms.equals(userManagementHomeAccountsEntry.getNumBedrooms()))
					|| !(this.postalCode == null ? userManagementHomeAccountsEntry.getPostalCode() == null
					: this.postalCode.equals(userManagementHomeAccountsEntry.getPostalCode()))
					|| !(this.ratesSchedule == null ? userManagementHomeAccountsEntry.getRatesSchedule() == null
					: this.ratesSchedule.equals(userManagementHomeAccountsEntry.getRatesSchedule()))
					|| !(this.lastRawDataArchivedTS == null
					? userManagementHomeAccountsEntry.getLastRawDataArchivedTS() == null
					: this.lastRawDataArchivedTS
					.equals(userManagementHomeAccountsEntry.getLastRawDataArchivedTS()))
					|| !(this.yearBuilt == null ? userManagementHomeAccountsEntry.getYearBuilt() == null
					: this.yearBuilt.equals(userManagementHomeAccountsEntry.getYearBuilt()))
					|| !(this.countryCode == null ? userManagementHomeAccountsEntry.getCountryCode() == null
					: this.countryCode.equals(userManagementHomeAccountsEntry.getCountryCode()))
					|| !(this.propertyType == null ? userManagementHomeAccountsEntry.getPropertyType() == null
					: this.propertyType.equals(userManagementHomeAccountsEntry.getPropertyType()))
					|| !(this.state == null ? userManagementHomeAccountsEntry.getState() == null
					: this.state.equals(userManagementHomeAccountsEntry.getState()))
					|| !(this.neighbourhoodId == null ? userManagementHomeAccountsEntry.getNeighbourhoodId() == null
					: this.neighbourhoodId.equals(userManagementHomeAccountsEntry.getNeighbourhoodId()))
					|| !(this.propertyAgeType == null ? userManagementHomeAccountsEntry.getPropertyAgeType() == null
					: this.propertyAgeType.equals(userManagementHomeAccountsEntry.getPropertyAgeType()))
					|| !(this.address == null ? userManagementHomeAccountsEntry.getAddress() == null
					: this.address.equals(userManagementHomeAccountsEntry.getAddress()))
					|| !(this.addressType == null ? userManagementHomeAccountsEntry.getAddressType() == null
					: this.addressType.equals(userManagementHomeAccountsEntry.getAddressType()))
					|| !(this.locality == null ? userManagementHomeAccountsEntry.getLocality() == null
					: this.locality.equals(userManagementHomeAccountsEntry.getLocality()))
					|| !(this.showDevicePairing == null ? userManagementHomeAccountsEntry.getShowDevicePairing() == null
					: this.showDevicePairing.equals(userManagementHomeAccountsEntry.getShowDevicePairing()))
					|| !(this.timeZone == null ? userManagementHomeAccountsEntry.getTimeZone() == null
					: this.timeZone.equals(userManagementHomeAccountsEntry.getTimeZone()))
					|| !(this.numOccupants == null ? userManagementHomeAccountsEntry.getNumOccupants() == null
					: this.numOccupants.equals(userManagementHomeAccountsEntry.getNumOccupants()))
					|| !(this.spaceHeatingType == null ? userManagementHomeAccountsEntry.getSpaceHeatingType() == null
					: this.spaceHeatingType.equals(userManagementHomeAccountsEntry.getSpaceHeatingType()))
					|| !(this.occupantType == null ? userManagementHomeAccountsEntry.getOccupantType() == null
					: this.occupantType.equals(userManagementHomeAccountsEntry.getOccupantType()))
					|| !(this.phoneNumber == null ? userManagementHomeAccountsEntry.getPhoneNumber() == null
					: this.phoneNumber.equals(userManagementHomeAccountsEntry.getPhoneNumber()))
					|| !(this.supplementaryNhoodIds == null
					? userManagementHomeAccountsEntry.getSupplementaryNhoodIds() == null
					: this.supplementaryNhoodIds
					.equals(userManagementHomeAccountsEntry.getSupplementaryNhoodIds()))
					|| !(this.hasSolar == null ? userManagementHomeAccountsEntry.getHasSolar() == null
					: this.hasSolar.equals(userManagementHomeAccountsEntry.getHasSolar()))
					|| !(this.rate == null ? userManagementHomeAccountsEntry.getRate() == null
					: this.rate.equals(userManagementHomeAccountsEntry.getRate()))) {
				return false;
			}
			boolean matched = false;

			if (this.appliances != null) {
				for (UserManagementApplianceEntry applianceEntry1 : appliances) {
					for (UserManagementApplianceEntry applianceEntry2 : userManagementHomeAccountsEntry
							.getAppliances()) {
						if (applianceEntry1.equals(applianceEntry2)) {
							matched = true;
							break;
						}
					}
					if (!matched) {
						return false;
					}

				}
			}

			return true;
		}
		return false;
	}

}
