package by.home.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

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

		if (images != null && images.size() > 0) {
			mav.addObject("imagesJsp", images);
		}

		mav.setViewName("image");

		return mav;

	}

	@RequestMapping(value = "/imagesJson")
	@ResponseBody
	public List<Image> getImagesJson() throws CommandException {
		List<Image> images;
		try {
			images = imageService.getImages();
		} catch (ServiceException e) {
			throw new CommandException(e.getMessage());
		}

		return images;

	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)

	public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		File uploadedFile = null;
		String name = null;

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				name = file.getOriginalFilename();

				String rootPath = request.getSession().getServletContext().getRealPath("/images");
				File dir = new File(rootPath);

				if (!dir.exists()) {
					dir.mkdirs();
				}

				uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream = null;
				try
				{
					stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
					
					stream.write(bytes);
					stream.flush();
				}
				finally {
					if(stream != null)
						stream.close();	
				}
				
				Metadata metadata = ImageMetadataReader.readMetadata(uploadedFile);
				Image image = new Image();
				for (Directory directory : metadata.getDirectories()) {
					for (Tag tag : directory.getTags()) {

						String strTagName = tag.getTagName();
						
						if (strTagName.equalsIgnoreCase("File Name")) {
							image.setImageName(tag.getDescription());
						}
						
						if (strTagName.equalsIgnoreCase("artist")) {
							image.setArtist(tag.getDescription());
						}
						if (strTagName.equalsIgnoreCase("Model")) {
							image.setModel(tag.getDescription());
						}
						if (strTagName.equalsIgnoreCase("Image Height")) {
							image.setImageHeight(tag.getDescription());
						}

						if (strTagName.equalsIgnoreCase("Image Width")) {
							image.setImageWidth(tag.getDescription());
						}

					}
					if (directory.hasErrors()) {
						for (String error : directory.getErrors()) {
							System.err.format("ERROR: %s", error);
						}
					}
				}
				
				try{
					imageService.addImage(image);
				}catch (ServiceException e) {
					throw new CommandException("Could not add image");
				}
				return "redirect:/";

			} catch (Exception e) {
				return "upload failed " + e.getMessage();
			}
		} else {
			return "upload failed because the file was empty.";
		}
		
		
	}

}