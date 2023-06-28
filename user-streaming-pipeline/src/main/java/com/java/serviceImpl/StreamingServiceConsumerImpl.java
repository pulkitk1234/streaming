package com.java.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.exception.EmptyListException;
import com.java.exception.EmptyMessageException;
import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.repository.StreamingRepository;
import com.java.service.StreamingServiceConsumer;


@Service
public class StreamingServiceConsumerImpl implements StreamingServiceConsumer {
	

	@Autowired
	StreamingRepository repository;
	
	
	/**
	  * This function act as a consumer for kafka topic.
	  *This will process the data and store it in databse.
	  * @RequestBody Message-received from kafka topic
	  * @return Message
	  */
	public Message communicate(Message message) {
		//object to store in db
		StreamEntity entity = new StreamEntity();
		
		// to store longest pallindroming length.
		int palindrom_len;
		
		try {
			
			// validating the message received
			if (!message.getMessage().get().isBlank()) {
				palindrom_len = longestPalSubstr(message.getMessage().get());        
				entity.setDate(message.getTime().get());
				entity.setText(message.getMessage().get());
				entity.setLongest_palindrom_length(palindrom_len);
				repository.save(entity);
				System.out.println("Saved entry is: " + entity);
			} 
			else {
				throw new EmptyMessageException("Message is not present");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return message;

	}
	
	
	/**
	  * This function calculates longest pallindromic length.
	  * @param str
	  * @return longest pallindromic length of str
	  */
	 int longestPalSubstr(String str)
	{
		 System.out.println("this is "+ str);
		 // Length of given string
		 int n = str.length();

		 // Stores the maximum length
		 int maxLength = 1, start = 0;

		 // Iterate over the string
		 for(int i = 0; i < str.length(); i++)
		 {

			 // Iterate over the string
			 for(int j = i; j < str.length(); j++)
			 {
				 int flag = 1;

				 // Check for palindrome
				 for(int k = 0;
						 k < (j - i + 1) / 2; k++)
					 if (str.charAt(i + k) !=
					 str.charAt(j - k))
						 flag = 0;

				 // If string [i, j - i + 1]
				 // is palindromic
				 if (flag != 0 &&
						 (j - i + 1) > maxLength)
				 {
					 start = i;
					 maxLength = j - i + 1;
				 }
			 }
		 }

	    // Return length of LPS
	    return maxLength;
	}


	 /**
	  * This function returns all the entries persisted in db by consumer.
	  */
	@Override
	public List<StreamEntity> findAllEntries() {
		List<StreamEntity> entryList = new ArrayList<StreamEntity>();

		try {			
			entryList=repository.findAll().stream().collect(Collectors.toList());			
			if (entryList.isEmpty()) {	    	
				throw new EmptyListException("No entry found in the database or Check your db connection!!");		   
			}

		} 
		catch (Exception e) {

			e.printStackTrace();

		}
		return entryList;
	}
}