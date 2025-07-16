package com.bidgely.customerDataGenerator.responses.entries;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class BillCycleCodeResponseEntry {

	Long    billingCycleID;
	String  billCycleCode;
	Integer utilityID;
	Long    validFrom;
	Long    validTo;
	Integer fromMonth;
	Integer toMonth;
	Integer fromDay;
	Integer toDay;
	String  utilityName;

	public Long getBillCycleID() {
		return billingCycleID;
	}

	public String getBillCycleCode() {
		return billCycleCode;
	}

	public Integer getUtilityID() {
		return utilityID;
	}

	public Long getValidFrom() {
		return validFrom;
	}

	public Long getValidTo() {
		return validTo;
	}

	public Integer getFromMonth() {
		return fromMonth;
	}

	public Integer getToMonth() {
		return toMonth;
	}

	public Integer getFromDay() {
		return fromDay;
	}

	public Integer getToDay() {
		return toDay;
	}

	public String getUtilityName() {
		return utilityName;
	}

	public boolean equals(Object obj) {
		if (obj instanceof BillCycleCodeResponseEntry) {
			BillCycleCodeResponseEntry billCycleCodeEntry = (BillCycleCodeResponseEntry) obj;
			if (this.getBillCycleCode() != null) {
				if (!this.billCycleCode.equals(billCycleCodeEntry.getBillCycleCode())) {
					return false;
				}
			}

			if (this.utilityID != null) {
				if (!this.utilityID.equals(billCycleCodeEntry.getUtilityID())) {
					return false;
				}
			}

			if (this.validFrom != null) {
				if (!this.validFrom.equals(billCycleCodeEntry.getValidFrom())) {
					return false;
				}
			}

			if (this.validTo != null) {
				if (!this.validTo.equals(billCycleCodeEntry.getValidTo())) {
					return false;
				}
			}

			if (this.fromMonth != null) {
				if (!this.fromMonth.equals(billCycleCodeEntry.getFromMonth())) {
					return false;
				}
			}

			if (this.toMonth != null) {
				if (!this.toMonth.equals(billCycleCodeEntry.getToMonth())) {
					return false;
				}
			}

			if (this.fromDay != null) {
				if (!this.fromDay.equals(billCycleCodeEntry.getFromDay())) {
					return false;
				}
			}

			if (this.toDay != null) {
				if (!this.toDay.equals(billCycleCodeEntry.getToDay())) {
					return false;
				}
			}

			if (this.utilityName != null) {
				if (!this.utilityName.equals(billCycleCodeEntry.getUtilityName())) {
					return false;
				}
			}

			return true;
		}
		return false;
	}

}
