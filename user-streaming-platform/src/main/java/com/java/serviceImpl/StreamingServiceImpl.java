package com.java.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.repository.StreamingRepository;
import com.java.service.StreamingService;

@Service
public class StreamingServiceImpl implements StreamingService {
	
//	@Autowired
//	StreamEntity entity;
	@Autowired
	StreamingRepository repository;
	
	public Message communicate(Message message) {
		int palindrom_len=longestPalSubstr(message.getMessage());
		System.out.println(palindrom_len);
		StreamEntity entity=new StreamEntity();
		Timestamp instant= Timestamp.from(Instant.now());  
		String s1=String.valueOf(instant);
		System.out.println("date is "+s1);
		entity.setDate(s1);
		entity.setText(message.getMessage());
		entity.setLongest_palindrom_length(palindrom_len);
		repository.save(entity);
		
		return new Message(message.getMessage());
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
}
