package by.home.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.home.command.exception.CommandException;
import by.home.entity.Image;
import by.home.service.ImageService;
import by.home.service.exception.ServiceException;


@Controller
@RequestMapping("/")
public class ImageController {
 
	@Autowired
	private ImageService imageService;
	
	 @RequestMapping("/")
	    public String getImage() throws CommandException{
		 /*try {
			List<Image> images = imageService.getImages();
		} catch (ServiceException e) {
			throw new CommandException(e.getMessage());
		}*/
	        return "image";
	    }
    

}
