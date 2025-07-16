package com.bidgely.responses.apiResponses;

import com.bidgely.exceptions.BidgelyExceptions;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@javax.xml.bind.annotation.XmlRootElement
public class MetaUserHomeGatewaysApiResponse extends HashMap<String, MetaUserHomeGatewayApiResponse> {

	/**
	 *
	 */
	private static final long serialVersionUID = 6368023234239394735L;

	public String getGatewayIdForGatewayType(String gatewayType) throws BidgelyExceptions {
		String gws = "0";

		for (String gwsKey : this.keySet()) {
			if (this.get(gwsKey).getType().equals(gatewayType)) {
				gws = gwsKey.substring(gwsKey.lastIndexOf("/") + 1);
			}
		}

		if (gws.equals("0")) {
			throw new BidgelyExceptions(
					"None of the gateways corresponded to " + gatewayType + " passed for the user");
		}

		return gws;
	}
}

