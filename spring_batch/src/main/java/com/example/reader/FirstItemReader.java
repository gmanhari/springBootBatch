package com.example.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements org.springframework.batch.item.ItemReader<Integer>{

	List<Integer> list = Arrays.asList(1,2,3,4,5);
	int i=0;
	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Integer in;
		if(i < list.size()) {
			in = list.get(i);
			i++;
			return in;
		}
		i=0;
		return null;
	}

	

	
}
