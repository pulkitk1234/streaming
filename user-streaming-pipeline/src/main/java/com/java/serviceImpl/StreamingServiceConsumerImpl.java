package com.java.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
	
	public Message communicate(Message message) {
		StreamEntity entity = new StreamEntity();
		int palindrom_len;
		try {

			if (!message.getMessage().isBlank()) {
				palindrom_len = longestPalSubstr(message.getMessage());        
				entity.setDate(message.getTime());
				entity.setText(message.getMessage());
				entity.setLongest_palindrom_length(palindrom_len);
				repository.save(entity);
				System.out.println("Saved entry is: " + entity);
			} else {
				throw new EmptyMessageException("Message is not present");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;

	}
	
	
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