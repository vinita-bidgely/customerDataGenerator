package com.bidgely.configs.BaseConfig;

//import com.bidgely.qa.core.context.variables.ExecutionVariables;
import com.bidgely.exceptions.BidgelyExceptions;

import java.util.UUID;

public abstract class BaseConfig {

	private String userUUID;

	public UUID getUserUUID(ExecutionVariables executionVariables) {

		if (this.userUUID == null) {
			userUUID = "userUUID";
		}

		if (executionVariables.containsKey(userUUID)) {
			return ((UUID) executionVariables.get(userUUID));
		}
		else {
			return UUID.fromString(userUUID);
		}

	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	private String getAbsoluteFilePath(String path) {
		return System.getProperty("dataFilePath") + path;
	}

	public Object getSubstitutedValue(String value, ExecutionVariables executionVariables) {
		if (!executionVariables.containsKey(value)) {
			return value;
		}
		else {
			return executionVariables.get(value);
		}
	}

	public String getSubstitutedFilePaths(String value, ExecutionVariables executionVariables)
			throws BidgelyExceptions {
		if (!executionVariables.containsKey(value)) {
			return getAbsoluteFilePath(value);
		}
		else {
			return String.valueOf(executionVariables.get(value));
		}
	}

	//    private synchronized String copyToTemp(String filePath) throws BidgelyExceptions {
	//        File newFile;
	//        Path copiedFile = null;
	//        String prefix, suffix;
	//
	//        if(filePath.substring(filePath.lastIndexOf(File.separator), filePath.length()).lastIndexOf('.') != -1) {
	//            prefix = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf('.'));
	//            suffix = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
	//        }
	//        else {
	//            prefix = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
	//            suffix = "";
	//        }
	//
	//        try {
	//            newFile = File.createTempFile(prefix, suffix);
	//            copiedFile = Files.copy(new File(filePath).toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	//        }
	//        catch (IOException e) {
	//            throw new BidgelyExceptions(e);
	//        }
	//        copiedFile.toFile().deleteOnExit();
	//        return copiedFile.toFile().getAbsolutePath();
	//    }
}
