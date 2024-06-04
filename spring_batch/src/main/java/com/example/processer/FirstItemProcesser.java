package com.example.processer;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcesser implements ItemProcessor<Integer, Long>{

	@Override
	public Long process(Integer item) throws Exception {
		// TODO Auto-generated method stub
		return Long.valueOf(item +1);
	}

}
