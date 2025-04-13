package com.example.MokshaMarg.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.repository.FoodTypeRepository;
import com.example.MokshaMarg.service.FoodTypeService;
import com.example.MokshaMarg.util.FoodTypeEnum;

@Service
public class FoodTypeServiceImpl implements FoodTypeService{
	
	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@Override
	public void saveAllFoodType() {
		List<String> foodType=new ArrayList<>();
		foodType.add(FoodTypeEnum.VEG.name());
		foodType.add(FoodTypeEnum.NON_VEG.name());
	}
	

}
