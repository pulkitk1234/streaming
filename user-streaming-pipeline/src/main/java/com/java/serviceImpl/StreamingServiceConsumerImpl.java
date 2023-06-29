package com.java.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	

	Logger logger = LoggerFactory.getLogger(StreamingServiceConsumerImpl.class);
	
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
				
				StringBuffer str=new StringBuffer(message.getMessage().get());
				String reverse=str.reverse().toString();
				palindrom_len = longestPalSubstr(message.getMessage().get(),reverse,message.getMessage().get().length(),reverse.length());        
				entity.setDate(message.getTime().get());
				entity.setText(message.getMessage().get());
				entity.setLongest_palindrom_length(palindrom_len);
				logger.info("Saved entry is: " + entity);
				
				repository.save(entity);
				
				
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
	int longestPalSubstr(String S1, String S2, int n, int m){
	    
        int[][] t= new int[n+1][m+1];
        int res=0;
        
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(i==0 || j==0){
                    t[i][j]=0;
                }
            }
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(S1.charAt(i-1)==S2.charAt(j-1)){
                    t[i][j]=1+t[i-1][j-1];
                }
                else{
                    t[i][j]=0;
                }
                res=Math.max(res,t[i][j]);
            }
        }
        return res;
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