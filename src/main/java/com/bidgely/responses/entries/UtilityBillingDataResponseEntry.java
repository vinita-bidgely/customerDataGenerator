package com.bidgely.responses.entries;

import com.bidgely.commons.SharedResources;
import com.bidgely.responses.InvoiceDataList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UtilityBillingDataResponseEntry {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilityBillingDataResponseEntry.class);

	private Integer               lastModified;

	private List<InvoiceDataList> invoiceDataList;

	private String                billCycleVariableOffPeakCost;

	private String                billCycleVariablePeakCost;

	private Boolean               bidgelyGeneratedInvoice;

	private String                billCyclePeakConsumption;

	private Double                cost;

	private Integer               billingStartTs;

	private Integer               billingEndTs;

	private String                billCycleOffPeakConsumption;

	private String                invoiceCode;

	private Boolean               empty;

	private Double                value;
	private String                userType;
	private Double gasValue;
	private Double gasCost;

	public String getUserType() {
		return userType;
	}

	public Double getGasValue() {
		return gasValue;
	}

	public Double getGasCost() {
		return gasCost;
	}

	private String                billCycleFixedCost;

	public Integer getLastModified() {
		return lastModified;
	}

	public void setLastModified(Integer lastModified) {
		this.lastModified = lastModified;
	}

	public List<InvoiceDataList> getInvoiceDataList() {
		return invoiceDataList;
	}

	public void setInvoiceDataList(List<InvoiceDataList> invoiceDataList) {
		this.invoiceDataList = invoiceDataList;
	}

	public String

	getBillCycleVariableOffPeakCost() {
		return billCycleVariableOffPeakCost;
	}

	public void setBillCycleVariableOffPeakCost(
			String billCycleVariableOffPeakCost) {
		this.billCycleVariableOffPeakCost = billCycleVariableOffPeakCost;
	}

	public String

	getBillCycleVariablePeakCost() {
		return billCycleVariablePeakCost;
	}

	public void setBillCycleVariablePeakCost(String billCycleVariablePeakCost) {
		this.billCycleVariablePeakCost = billCycleVariablePeakCost;
	}

	public Boolean getBidgelyGeneratedInvoice() {
		return bidgelyGeneratedInvoice;
	}

	public void setBidgelyGeneratedInvoice(Boolean bidgelyGeneratedInvoice) {
		this.bidgelyGeneratedInvoice = bidgelyGeneratedInvoice;
	}

	public String getBillCyclePeakConsumption() {
		return billCyclePeakConsumption;
	}

	public void setBillCyclePeakConsumption(String billCyclePeakConsumption) {
		this.billCyclePeakConsumption = billCyclePeakConsumption;
	}

	public Integer getBillingStartTs() {
		return billingStartTs;
	}

	public void setBillingStartTs(Integer billingStartTs) {
		this.billingStartTs = billingStartTs;
	}

	public Integer getBillingEndTs() {
		return billingEndTs;
	}

	public void setBillingEndTs(Integer billingEndTs) {
		this.billingEndTs = billingEndTs;
	}

	public String getBillCycleOffPeakConsumption() {
		return billCycleOffPeakConsumption;
	}

	public void setBillCycleOffPeakConsumption(
			String billCycleOffPeakConsumption) {
		this.billCycleOffPeakConsumption = billCycleOffPeakConsumption;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Boolean getEmpty() {
		return empty;
	}

	public void setEmpty(Boolean empty) {
		this.empty = empty;
	}

	public String getBillCycleFixedCost() {
		return billCycleFixedCost;
	}

	public void setBillCycleFixedCost(String billCycleFixedCost) {
		this.billCycleFixedCost = billCycleFixedCost;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof UtilityBillingDataResponseEntry) {
			UtilityBillingDataResponseEntry utilityBillingDataResponseEntry = (UtilityBillingDataResponseEntry) obj;

			if (this.billingStartTs != null) {
				if (!this.billingStartTs.equals(utilityBillingDataResponseEntry.getBillingStartTs())) {
					logger.error("UtilityBillingDataResponse : expected billingStartTs : " + this.billingStartTs + "\nActual billingStartTs :" + utilityBillingDataResponseEntry.getBillingStartTs());
					return false;
				}
			}
			if (this.billingEndTs != null) {
				if (!this.billingEndTs.equals(utilityBillingDataResponseEntry.getBillingEndTs())) {
					logger.error("UtilityBillingDataResponse : expected billingEndTs : " + this.billingEndTs + "\nActual billingEndTs :" + utilityBillingDataResponseEntry.getBillingEndTs());
					return false;
				}
			}
			if (this.value != null) {

				if (!this.value.equals(utilityBillingDataResponseEntry.getValue())) {
					logger.error("UtilityBillingDataResponse : expected value : " + this.value + "\nActual value :" + utilityBillingDataResponseEntry.getValue());
					return false;
				}
			}
			if (this.cost != null) {

				if (!this.cost.equals(utilityBillingDataResponseEntry.getCost())) {
					logger.error("UtilityBillingDataResponse : expected cost : " + this.cost + "\nActual cost :" + utilityBillingDataResponseEntry.getCost());
					return false;
				}
			}
			if (this.invoiceCode != null) {
				if (!this.invoiceCode.equals(utilityBillingDataResponseEntry.getInvoiceCode())) {
					logger.error("UtilityBillingDataResponse : expected invoiceCode : " + this.invoiceCode + "\nActual invoiceCode :" + utilityBillingDataResponseEntry.getInvoiceCode());
					return false;
				}
			}
			if (this.lastModified != null) {
				if (!(this.lastModified.equals(utilityBillingDataResponseEntry.getLastModified()))) {
					logger.error("UtilityBillingDataResponse : expected lastModified : " + this.lastModified + "\nActual lastModified :" + utilityBillingDataResponseEntry.getLastModified());
					return false;
				}
			}
			if (this.invoiceDataList != null) {
				boolean found = true;

				for (InvoiceDataList expected_entry : this.invoiceDataList) {
					found = false;

					for (InvoiceDataList actual_entry : utilityBillingDataResponseEntry.invoiceDataList) {

						if (expected_entry.equals(actual_entry)) {
							found = true;
							break;
						}

					}
					if (!found) {

						logger.error("UtilityBillingDataResponse : expected entry that's missing in actual response : \n" + SharedResources.gson.toJson(expected_entry) +
								"\nActual Response :\n" +
								SharedResources.gson.toJson(utilityBillingDataResponseEntry.invoiceDataList));
						return false;

					}
				}
				return found;
			}
			if (this.billCycleVariableOffPeakCost != null) {

				if (!this.billCycleVariableOffPeakCost.equals(utilityBillingDataResponseEntry.getBillCycleVariableOffPeakCost())) {
					logger.error("UtilityBillingDataResponse : expected billCycleVariableOffPeakCost : " + this.billCycleVariableOffPeakCost + "\nActual billCycleVariableOffPeakCost :" + utilityBillingDataResponseEntry.getBillCycleVariableOffPeakCost());
					return false;
				}
			}
			if (this.billCycleVariablePeakCost != null) {

				if (!this.billCycleVariablePeakCost.equals(utilityBillingDataResponseEntry.getBillCycleVariablePeakCost())) {
					logger.error("UtilityBillingDataResponse : expected billCycleVariablePeakCost : " + this.billCycleVariablePeakCost + "\nActual billCycleVariablePeakCost :" + utilityBillingDataResponseEntry.getBillCycleVariablePeakCost());
					return false;
				}
			}
			if (this.bidgelyGeneratedInvoice != null) {

				if (!this.bidgelyGeneratedInvoice.equals(utilityBillingDataResponseEntry.getBidgelyGeneratedInvoice())) {
					logger.error("UtilityBillingDataResponse : expected bidgelyGeneratedInvoice : " + this.bidgelyGeneratedInvoice + "\nActual bidgelyGeneratedInvoice :" + utilityBillingDataResponseEntry.getBidgelyGeneratedInvoice());
					return false;
				}
			}
			if (this.billCyclePeakConsumption != null) {
				if (!this.billCyclePeakConsumption.equals(utilityBillingDataResponseEntry.getBillCyclePeakConsumption())) {
					logger.error("UtilityBillingDataResponse : expected billCyclePeakConsumption : " + this.billCyclePeakConsumption + "\nActual billCyclePeakConsumption :" + utilityBillingDataResponseEntry.getBillCyclePeakConsumption());
					return false;
				}
			}
			if (this.billCycleOffPeakConsumption != null) {

				if (!this.billCycleOffPeakConsumption.equals(utilityBillingDataResponseEntry.getBillCycleOffPeakConsumption())) {
					logger.error("UtilityBillingDataResponse : expected billCycleOffPeakConsumption : " + this.billCycleOffPeakConsumption + "\nActual billCycleOffPeakConsumption :" + utilityBillingDataResponseEntry.getBillCycleOffPeakConsumption());
					return false;
				}
			}
			if (this.billCycleFixedCost != null) {
				if (!this.billCycleFixedCost.equals(utilityBillingDataResponseEntry.getBillCycleFixedCost())) {
					logger.error("UtilityBillingDataResponse : expected billCycleFixedCost : " + this.billCycleFixedCost + "\nActual billCycleFixedCost :" + utilityBillingDataResponseEntry.getBillCycleFixedCost());
					return false;
				}
			}
			if (this.empty != null) {
				if (!this.empty.equals(utilityBillingDataResponseEntry.getEmpty())) {
					logger.error("UtilityBillingDataResponse : expected empty : " + this.empty + "\nActual empty :" + utilityBillingDataResponseEntry.getEmpty());
					return false;
				}
			}

			return true;
		}
		return false;
	}

	public boolean compare(Object obj) {

		if (obj instanceof UtilityBillingDataResponseEntry) {
			UtilityBillingDataResponseEntry utilityBillingDataResponseEntry = (UtilityBillingDataResponseEntry) obj;

			if (this.billingStartTs != null) {
				if (!this.billingStartTs.equals(utilityBillingDataResponseEntry.getBillingStartTs())) {
					return false;
				}
			}
			if (this.billingEndTs != null) {

				if (!this.billingEndTs.equals(utilityBillingDataResponseEntry.getBillingEndTs())) {
					return false;
				}
			}
			if (this.value != null) {

				if (!this.value.equals(utilityBillingDataResponseEntry.getValue())) {
					return false;
				}
			}
			if (this.cost != null) {

				if (!this.cost.equals(utilityBillingDataResponseEntry.getCost())) {
					return false;
				}
			}
			if (this.invoiceCode != null) {
				if (!this.invoiceCode.equals(utilityBillingDataResponseEntry.getInvoiceCode())) {
					return false;
				}
			}
			if (this.invoiceDataList != null) {

				if (!this.invoiceDataList.equals(utilityBillingDataResponseEntry.getInvoiceDataList())) {
					return false;
				}
			}
			if (this.billCycleVariableOffPeakCost != null) {

				if (!this.billCycleVariableOffPeakCost.equals(utilityBillingDataResponseEntry.getBillCycleVariableOffPeakCost())) {
					return false;
				}
			}
			if (this.billCycleVariablePeakCost != null) {

				if (!this.billCycleVariablePeakCost.equals(utilityBillingDataResponseEntry.getBillCycleVariablePeakCost())) {
					return false;
				}
			}
			if (this.bidgelyGeneratedInvoice != null) {

				if (!this.bidgelyGeneratedInvoice.equals(utilityBillingDataResponseEntry.getBidgelyGeneratedInvoice())) {
					return false;
				}
			}
			if (this.billCyclePeakConsumption != null) {
				if (!this.billCyclePeakConsumption.equals(utilityBillingDataResponseEntry.getBillCyclePeakConsumption())) {
					return false;
				}
			}
			if (this.billCycleOffPeakConsumption != null) {

				if (!this.billCycleOffPeakConsumption.equals(utilityBillingDataResponseEntry.getBillCycleOffPeakConsumption())) {
					return false;
				}
			}
			if (this.billCycleFixedCost != null) {
				if (!this.billCycleFixedCost.equals(utilityBillingDataResponseEntry.getBillCycleFixedCost())) {
					return false;
				}
			}
			if (this.empty != null) {
				if (!this.empty.equals(utilityBillingDataResponseEntry.getEmpty())) {
					return false;
				}
			}

			return true;
		}
		return false;
	}
}
