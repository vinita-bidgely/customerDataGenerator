package com.bidgely.responses;

import java.util.List;

public class BillCycleCodeWithIdentifierResponse {

	private String                           requestId;
	private List<BillCycleCodeResponseEntry> payload;

	public List<BillCycleCodeResponseEntry> getBillCycleCodes() {
		return payload;
	}

	public String getRequestId() {
		return requestId;
	}

	public boolean equals(Object object) {

		if (object instanceof BillCycleCodeWithIdentifierResponse) {

			BillCycleCodeWithIdentifierResponse billCycleCodeWithIdentifierResponse = (BillCycleCodeWithIdentifierResponse) object;

			if (this.getBillCycleCodes() != null) {
				boolean flag = false;
				for (BillCycleCodeResponseEntry expectedBillCycleCodeResponseEntry : this.getBillCycleCodes()) {
					flag = false;
					for (BillCycleCodeResponseEntry actualBillCycleCodeResponseEntry : billCycleCodeWithIdentifierResponse.getBillCycleCodes()) {
						if (expectedBillCycleCodeResponseEntry.equals(actualBillCycleCodeResponseEntry)) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						return false;
					}
				}
			}
			if (this.getRequestId() != null) {
				if (!this.getRequestId().equals(billCycleCodeWithIdentifierResponse.getRequestId())) {
					return false;
				}
			}

			return true;

		}
		else {
			return false;
		}

	}


}
