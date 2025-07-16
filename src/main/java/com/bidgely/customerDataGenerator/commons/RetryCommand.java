package com.bidgely.customerDataGenerator.commons;

import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class RetryCommand<T> {
	private int retryCounter;
	private final int maxRetries;
	private final int waitTimeInMilliseconds;
	private static final Logger log = LoggerFactory.getLogger(RetryCommand.class);


	public RetryCommand(int maxRetries, int waitTimeInMilliseconds) {
		this.maxRetries = maxRetries;
		this.waitTimeInMilliseconds = waitTimeInMilliseconds;
	}

	public RetryCommand(int maxRetries) {
		this.maxRetries = maxRetries;
		// Default wait time between retry is 5 seconds
		this.waitTimeInMilliseconds = 5000;
	}

	// Takes a function and executes it, if fails, passes the function to the retry command
	public T run(Supplier<T> function) throws BidgelyExceptions {

		T temp = null;
		try {
			temp = function.get();
			if(temp == null)
				throw new NullPointerException();
			return temp;
		} catch (Exception e) {
			return retry(function);
		}
	}

	public int getRetryCounter() {
		return retryCounter;
	}

	private T retry(Supplier<T> function) throws BidgelyExceptions {
		log.info("FAILED - Command failed, will be retried " + maxRetries + " times.");
		retryCounter = 0;
		T temp = null;
		while (retryCounter < maxRetries) {
			log.info("Waiting For : " + waitTimeInMilliseconds/1000 + " seconds - Command will be retried " + maxRetries + " times.");
			// Wait for waitTimeInMilliseconds
			try {
				Thread.sleep(waitTimeInMilliseconds);
			} catch (InterruptedException e) {
				log.warn("Interrupted exception", e);
				e.printStackTrace();
			}
			retryCounter = retryCounter + 1;
			try {
				temp = function.get();
				if(temp == null)
					throw new NullPointerException();
				return temp;
			} catch (Exception ex) {
				log.info("FAILED - Command failed on retry " + retryCounter + " of " + maxRetries + " error: " + ex );
				if (retryCounter >= maxRetries) {
					log.info("Max retries exceeded.");
					break;
				}
			}
		}
		log.info("Command failed on all of " + maxRetries + " retries");
		throw new BidgelyExceptions("Command failed on all of " + maxRetries + " retries");
	}
}
