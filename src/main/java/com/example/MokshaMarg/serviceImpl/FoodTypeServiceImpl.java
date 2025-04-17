package com.example.MokshaMarg.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.entity.FoodType;
import com.example.MokshaMarg.repository.FoodTypeRepository;
import com.example.MokshaMarg.service.FoodTypeService;
import com.example.MokshaMarg.util.FoodTypeEnum;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@Override
	public void saveAllFoodType() {
		FoodType food1 = new FoodType();
		food1.setName(FoodTypeEnum.VEG.name());
		FoodType food2 = new FoodType();
		food2.setName(FoodTypeEnum.NON_VEG.name());
		List<FoodType> list = new ArrayList<>();
		list.add(food1);
		list.add(food2);
		list.stream().forEach((ele) -> {
			Optional<FoodType> foodType = foodTypeRepository.findByName(ele.getName());
			if(foodType.isEmpty()) {
				foodTypeRepository.save(ele);
			}
		});
	}
}
