package com.bidgely.responses.entries;

public class PublicApiErrorEntry {

	private String  message;

	private Integer code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean equals(Object obj) {

		if (obj instanceof PublicApiErrorEntry) {
			PublicApiErrorEntry publicApiErrorEntry = (PublicApiErrorEntry) obj;

			if (!(this.code == null ? publicApiErrorEntry.getCode() == null
					: this.code.equals(publicApiErrorEntry.getCode())) ||
					!(this.message == null
							? publicApiErrorEntry.getMessage() != null
							: this.message.equals(publicApiErrorEntry.getMessage()))) {
				return false;
			}
			return true;

		}
		return false;
	}

}

