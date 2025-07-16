package com.bidgely.aws;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.Transfer.TransferState;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.bidgely.exceptions.BidgelyExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;


public class S3 {

	private final AmazonS3Client amazonS3Client;

	private static final Logger log = LoggerFactory.getLogger(S3.class);

	/**
	 * Uses the {@code DefaultAWSCredentialsProviderChain} to fetch the AWS credentials.
	 */
	private S3() {
		this.amazonS3Client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
	}

	/**
	 * Uses {@code ProfileCredentialsProvider} to fetch AWS credentials based
	 * on passed profile.
	 * @param profile
	 */
	private S3(String profile) {
		this.amazonS3Client = new AmazonS3Client(new ProfileCredentialsProvider(profile));
	}

	/**
	 * Creates a new instance of S3 class
	 * @return the newly created instance
	 */
	public static S3 getInstance() {
		if (System.getProperty("aws.profile") != null) {
			return new S3(System.getProperty("aws.profile"));
		}
		else {
			return new S3();
		}
	}

	/**
	 * Upload a file to a bucket
	 *
	 * @param bucket
	 * @param key
	 * @param filePath
	 */
	public void uploadFile(String bucket, String key, String filePath) {
		log.info("Uploading file to S3 Bucket: {}", bucket);
		log.info("Key: {} ; File: {}", key, filePath);

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, new File(filePath));
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.addUserMetadata("utility_file_name", key);
		putObjectRequest.setMetadata(objectMetadata);
		amazonS3Client.putObject(putObjectRequest);

		try {
			Thread.sleep(10000);
		}
		catch (InterruptedException e) {
			// Do nothing
			e.printStackTrace();
		}
		log.debug("Upload successful");
	}

	/**
	 * Uploads a folder to the given S3 bucket
	 * @param bucket - where you want to upload
	 * @param key - folder name in the s3 bucket
	 * @param folderPath - the source folder.
	 */
	public void uploadFolder(String bucket, String key, File folderPath) {
		TransferManager transferManager = TransferManagerBuilder.standard().build();
		try {
			MultipleFileUpload multipleFileUpload = transferManager.uploadDirectory(bucket, key, folderPath, true);
			log.info("Uploading result folder to the S3 bucket: {}, into the folder: {}", bucket, key);
			multipleFileUpload.waitForCompletion();
			TransferState transferState = multipleFileUpload.getState();
			log.info("Transfer status of the folder to the bucket has been {} ", transferState);
		}
		catch (Exception e) {
			log.error("Exception occurred while uploading the run result folder to s3 bucket", e);
		}
		transferManager.shutdownNow();
	}

	public String getFile(String bucket, String fileName)
			throws BidgelyExceptions {
		log.debug("Downloading file from S3 Bucket: " + bucket);
		log.debug("File: " + fileName);
		S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, fileName));
		try {
			Thread.sleep(10000);
		}
		catch (InterruptedException e) {
			// Do nothing
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));
		File downloadedFile;
		try {
			downloadedFile = File.createTempFile("Temp_", ".txt");
			Writer writer = new OutputStreamWriter(new FileOutputStream(downloadedFile));
			String line;

			while ((line = reader.readLine()) != null) {
				writer.write(line + System.lineSeparator());
			}

			writer.close();
			log.debug("Download successful");
			return (downloadedFile.getAbsolutePath());
		}
		catch (IOException e) {
			throw new BidgelyExceptions(e);
		}
	}

	public String getFileWithFileExtension(String bucket, String fileName,
			String fileExtension)
			throws BidgelyExceptions {
		log.debug("Downloading file from S3 Bucket: " + bucket);
		log.debug("File: " + fileName);

		File downloadedFile;
		try {
			downloadedFile = File.createTempFile(
					fileName.substring(fileName.lastIndexOf("/"), fileName.indexOf(".")) +
							"_", "." +
							fileExtension);

			amazonS3Client.getObject(new GetObjectRequest(bucket, fileName), downloadedFile);

			log.debug("Download successful");
			return (downloadedFile.getAbsolutePath());
		}
		catch (IOException e) {
			throw new BidgelyExceptions(e);
		}
	}

	/**
	 * This method will return files in the passed 'bucket' with the passed 'prefix'.
	 *
	 * @param bucket
	 * @return
	 * @throws BidgelyExceptions
	 */
	public List<String> listFiles(String bucket, String pilotId,
			List<String> prefixList)
			throws BidgelyExceptions {

		List<ObjectListing> objectListingList = new ArrayList<ObjectListing>();
		for (String prefix : prefixList) {
			objectListingList.add(amazonS3Client.listObjects(bucket, pilotId.concat(prefix)));
		}

		String key = null;
		List<String> fileList = new ArrayList<>();

		for (ObjectListing list : objectListingList) {
			List<S3ObjectSummary> summaries = list.getObjectSummaries();
			for (S3ObjectSummary summary : summaries) {
				key = summary.getKey();
				fileList.add(getFile(bucket, key));
			}

			while (list.isTruncated()) {
				list = amazonS3Client.listNextBatchOfObjects(list);
				summaries = list.getObjectSummaries();
				for (S3ObjectSummary summary : summaries) {
					key = summary.getKey();
					fileList.add(getFile(bucket, key));
				}

			}
		}

		return fileList;
	}

	public String getInteractionFile(String bucket,
			String prefix) {

		List<ObjectListing> objectListingList = new ArrayList<ObjectListing>();

		objectListingList.add(amazonS3Client.listObjects(bucket, prefix));

		String key = null;
		String filepath = null;

		S3ObjectSummary latestSummary;
		Date latestModifiedDate = null;
		for (ObjectListing list : objectListingList) {
			List<S3ObjectSummary> summaries = list.getObjectSummaries();
			for (S3ObjectSummary summary : summaries) {
				Date lastModified = summary.getLastModified();
				if (latestModifiedDate == null || lastModified.after(latestModifiedDate)) {
					latestModifiedDate = lastModified;
					key = summary.getKey();
				}
			}
		}

		if(key != null && key.contains(".json")){
			File downloadedFile = null;
			try {
				downloadedFile = File.createTempFile("interactions", ".json");
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}

			amazonS3Client.getObject(new GetObjectRequest(bucket, key), downloadedFile);
			log.debug("Download successful");
			filepath = downloadedFile.getAbsolutePath();
		}
		else{
			return "jsonNotPresent";
		}

		return filepath;
	}

	/**
	 * This method will return files in the passed 'bucket' with the passed 'prefix'.
	 *
	 * @param bucket
	 * @param generatedAfterTimestampInSeconds
	 * @return
	 * @throws BidgelyExceptions
	 */
	public List<String> listFiles(String bucket,
			List<String> prefixList,
			String generatedAfterTimestampInSeconds)
			throws BidgelyExceptions {

		List<ObjectListing> objectListingList = new ArrayList<ObjectListing>();
		for (String prefix : prefixList) {
			objectListingList.add(amazonS3Client.listObjects(bucket, prefix));
		}

		String key = null;
		long fileGeneratedAfter = Long.parseLong(generatedAfterTimestampInSeconds.trim());

		List<String> fileList = new ArrayList<>();

		for (ObjectListing list : objectListingList) {
			List<S3ObjectSummary> summaries = list.getObjectSummaries();
			for (S3ObjectSummary summary : summaries) {
				if (summary.getLastModified().getTime() >= fileGeneratedAfter) {
					key = summary.getKey();
					fileList.add(getFile(bucket, key));
				}
			}

			while (list.isTruncated()) {
				list = amazonS3Client.listNextBatchOfObjects(list);
				summaries = list.getObjectSummaries();
				for (S3ObjectSummary summary : summaries) {
					if (summary.getLastModified().getTime() >=
							fileGeneratedAfter) {
						key = summary.getKey();
						fileList.add(getFile(bucket, key));
					}
				}
			}
		}

		return fileList;
	}

	/**
	 * This method will return fileNames in the passed 'bucket' with the passed 'prefix'.
	 *
	 * @param bucket
	 * @param pilotId
	 * @param generatedAfterTimestampInSeconds
	 * @return
	 * @throws BidgelyExceptions
	 */
	public List<String> listFileNames(String bucket, String pilotId,
			List<String> prefixList,
			String generatedAfterTimestampInSeconds)
			throws BidgelyExceptions {

		int i=0;
		List<ObjectListing> objectListingList = new ArrayList<ObjectListing>();
		for (String prefix : prefixList) {
			ObjectListing objectListing  = amazonS3Client.listObjects(bucket, pilotId.concat(prefix));
			while(objectListing.getObjectSummaries().isEmpty() && i < 6){
				try {
					Thread.sleep(30000);
					objectListing  = amazonS3Client.listObjects(bucket, pilotId.concat(prefix));
					i++;
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			objectListingList.add(objectListing);
		}

		long fileGeneratedAfter = Long.parseLong(generatedAfterTimestampInSeconds.trim());

		List<String> fileList = new ArrayList<>();

		for (ObjectListing list : objectListingList) {
			List<S3ObjectSummary> summaries = list.getObjectSummaries();
			if(pilotId.equals("10022") && summaries.size() > 1){
				if(summaries.get(1).getLastModified().getTime() > summaries.get(0).getLastModified().getTime()){
					fileList.add(summaries.get(1).getKey());
				}
				else{
					fileList.add(summaries.get(0).getKey());
				}
			}

			else{
				for (S3ObjectSummary summary : summaries) {
					log.debug(summary.getKey());
					fileList.add(summary.getKey());
				}
			}

			while (list.isTruncated()) {
				log.info("fetching the file from truncated list");
				list = amazonS3Client.listNextBatchOfObjects(list);
				summaries = list.getObjectSummaries();
				for (S3ObjectSummary summary : summaries) {
					if (summary.getLastModified().getTime() >=
							fileGeneratedAfter) {
						log.debug(summary.getKey());
						fileList.add(summary.getKey());
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * This method will return fileNames in the passed 'bucket' with the passed 'prefix'.
	 *
	 * @param bucket
	 * @param generatedAfterTimestampInSeconds
	 * @return
	 * @throws BidgelyExceptions
	 */
	public List<String> listFileNames(String bucket,
			List<String> prefixList,
			String generatedAfterTimestampInSeconds)
			throws BidgelyExceptions {

		List<ObjectListing> objectListingList = new ArrayList<ObjectListing>();
		for (String prefix : prefixList) {
			objectListingList.add(amazonS3Client.listObjects(bucket, prefix));
		}

		long fileGeneratedAfter = Long.parseLong(generatedAfterTimestampInSeconds.trim());

		List<String> fileList = new ArrayList<>();

		for (ObjectListing list : objectListingList) {
			List<S3ObjectSummary> summaries = list.getObjectSummaries();
			for (S3ObjectSummary summary : summaries) {
				if (summary.getLastModified().getTime() >= fileGeneratedAfter) {
					log.debug(summary.getKey());
					fileList.add(summary.getKey());
				}
			}

			while (list.isTruncated()) {
				list = amazonS3Client.listNextBatchOfObjects(list);
				summaries = list.getObjectSummaries();
				for (S3ObjectSummary summary : summaries) {
					if (summary.getLastModified().getTime() >=
							fileGeneratedAfter) {
						log.debug(summary.getKey());
						fileList.add(summary.getKey());
					}
				}
			}
		}
		return fileList;
	}


	public Set<String> listFolderNames(String bucket, String prefix, String folderInFocus)
			throws BidgelyExceptions {

		ObjectListing ObjectListing = amazonS3Client.listObjects(bucket, prefix);
		Set<String> uniqueFolders = new HashSet<>();
		try {
			for (S3ObjectSummary content : ObjectListing.getObjectSummaries()) {
				String key = content.getKey();
				if (key.startsWith(prefix)) {
					String remainingPath = key.substring(prefix.length());
					remainingPath = remainingPath.startsWith("/") ?
							remainingPath.substring(1) :
							remainingPath;
					int firstSlashIndex = remainingPath.indexOf('/');
					int secondSlashIndex = remainingPath.indexOf('/', firstSlashIndex + 1);

					if (firstSlashIndex != -1 && secondSlashIndex != -1) {
						String deliveryType = remainingPath.substring(0, firstSlashIndex); // deliveryType=HER_MONTHLY_REPORT
						String fuelType = remainingPath.substring(firstSlashIndex + 1, secondSlashIndex); // fuelType=ELECTRIC
						String combinedFolder = fuelType + "-" + deliveryType.substring(deliveryType.indexOf('=') + 1); // fuelType=ELECTRIC-HER_MONTHLY_REPORT

						if (combinedFolder.startsWith(folderInFocus)) {
							uniqueFolders.add(combinedFolder);
						}
					}
				}
			}
			return uniqueFolders;
		}
		catch (Exception e) {
			return uniqueFolders;
		}
	}

	/**
	 * Deletes an entire folder in s3
	 * @param bucketName the source s3 bucket
	 * @param folderPath the folder you want to delete
	 */
	public void deleteS3Folder(String bucketName, String folderPath) {
		for (S3ObjectSummary file : amazonS3Client.listObjects(bucketName, folderPath).getObjectSummaries()) {
			amazonS3Client.deleteObject(bucketName, file.getKey());
		}
	}

	public void deleteAllFiles(String bucketName) {
		ObjectListing objectListing = amazonS3Client.listObjects(bucketName);

		while (true) {
			for (S3ObjectSummary file : objectListing.getObjectSummaries()) {
				amazonS3Client.deleteObject(bucketName, file.getKey());
				System.out.println("Deleted: " + file.getKey());
			}

			if (objectListing.isTruncated()) {
				objectListing = amazonS3Client.listNextBatchOfObjects(objectListing);
			} else {
				break;
			}
		}

		System.out.println("All files deleted from bucket: " + bucketName);
	}

	/**
	 * Checks if the specified buckets exists or not.
	 * @param bucketName the bucket which needs to be checked
	 * @return true if S3 bucket exists, false if not
	 */
	public boolean doesS3BucketExist(String bucketName) {
		return amazonS3Client.doesBucketExistV2(bucketName);
	}

	public void downloadFolder(String bucketName, String keyPrefix,
			String destinationDirectory) {
		try {
			TransferManager transferManager = new TransferManager(amazonS3Client);
			File fileDirectory = new File(destinationDirectory);
			MultipleFileDownload multipleFileDownload = transferManager.downloadDirectory(bucketName, keyPrefix, fileDirectory);
			multipleFileDownload.waitForCompletion();
			log.info("Transfer of folder from bucket to local machine is successful.");
		}
		catch (Exception e) {
			log.error("Exception occurred while downloading the run result from s3 to remote machine ", e);
		}
	}
}
