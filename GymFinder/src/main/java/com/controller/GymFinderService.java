package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymnasium.Gymnasium;
import com.gymnasium.GymsOperations;

@Controller
public class GymFinderService {

	@RequestMapping(value="/gymnasiums", method = RequestMethod.GET)
	public ModelAndView  gymnasiums(@RequestParam("location") String location) {
		GymsOperations gyms = new GymsOperations();
		List<Gymnasium> gymsList = gyms.fetchGymsDetails(location);
		
		ModelAndView model = new ModelAndView("results");
		model.addObject("gyms", gymsList);
		
		return model;

	}
	
}