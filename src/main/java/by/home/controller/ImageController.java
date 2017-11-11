package by.home.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.home.command.exception.CommandException;
import by.home.entity.Image;
import by.home.service.ImageService;
import by.home.service.exception.ServiceException;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getImage() throws CommandException {
		List<Image> images;
		try {
			images = imageService.getImages();
		} catch (ServiceException e) {
			throw new CommandException(e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView();
		
		if(images != null && images.size() > 0){
			mav.addObject("imagesJsp", images);
		}
		
		mav.setViewName("image");

		return mav;
		
	}

	@RequestMapping(value = "/imagesJson")
	@ResponseBody
	public List<Image> getImagesJson() throws CommandException{
		List<Image> images;
		try {
			images = imageService.getImages();
		} catch (ServiceException e) {
			throw new CommandException(e.getMessage());
		}
		return images;
		
	}
}
