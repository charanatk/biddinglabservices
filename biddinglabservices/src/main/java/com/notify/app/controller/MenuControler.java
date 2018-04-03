package com.notify.app.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.notify.app.model.MenuBean;
import com.notify.app.repo.MenuRep;

/**
*
* @author charan kandula
* @version 1.0
* 
* 
*/

@RestController
@RequestMapping("/menu/v1")
public class MenuControler {

	@Autowired
	MenuRep MenuRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/saveMenu")
	ResponseEntity<MenuBean> saveMenu(
			@RequestBody MenuBean menuBean) {
			logger.info("Staring saveMenu");
			//if (encoding.equalsIgnoreCase("charan")) {
			
			menuBean.setCreatedDate(Calendar.getInstance().getTime().toString());
		//	menuBean.setModifiedDate(Calendar.getInstance().getTime().toString());
			MenuRep.save(menuBean);
			logger.info("Ended saveMenu");
			return new ResponseEntity<MenuBean>(menuBean,
					HttpStatus.OK);
//		} else {
//			return new ResponseEntity("Unauthorized",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
//		}
	}

	@GetMapping("/getAllMenu")
	ResponseEntity<List<MenuBean>> getAllMenu() {
		logger.info("Enter into getAllMenu");
		
		logger.info("Ended into getAllMenu");
		return new ResponseEntity<List<MenuBean>>(MenuRep.findAll(),
				HttpStatus.OK);
	}

	@GetMapping("/getMenu/{MenuId}")
	ResponseEntity<MenuBean> getMenu(@PathVariable("MenuId") Integer sprintId) {
		logger.info("Enter into getMenu");
		logger.info("Ended into getMenu");
		return new ResponseEntity<MenuBean>(MenuRep.findOne(sprintId),
				HttpStatus.OK);
	}

	@GetMapping("/deleteMenu/{MenuId}")
	boolean deleteItem(@PathVariable("MenuId") Integer MenuId) {
		logger.info("Enter into deleteItem");
		MenuRep.delete(MenuId);
		logger.info("Ended deleteItem");
		return MenuRep.exists(MenuId);
	}
	
	@GetMapping("/getPageMenu/{start}/{size}")
	ResponseEntity<List<MenuBean>> getPageMenu(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
		
			logger.info("Stating getPageMenu");
		if (start != null && size != null && size != 0) {
			Iterable<MenuBean> bean = MenuRep.findAll(new PageRequest(
					start, size));
			ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
			if (bean != null) {
				for (MenuBean menuBean : bean) {
					menuList.add(menuBean);
				}
				logger.info("Ended getPageMenu");
				return new ResponseEntity<List<MenuBean>>(menuList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<MenuBean>>(menuList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("Mandatry parameter missing..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	
	@GetMapping("/getMenuItems")
	public List<MenuBean> getMbeans(){
		return MenuRep.findAll();
	}
	
	@GetMapping("/getSortPageMenu/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<MenuBean>> getSortPageMenu(@PathVariable("start") Integer start,@PathVariable("size") Integer size,@PathVariable("dir") String direction,@PathVariable("field") String field) {
		
			logger.info(this.getClass()+"Stating getSortPageMenu");
		if (start != null && size != null && size != 0 && !direction.isEmpty() && direction != null && !field.isEmpty() && field != null) {
			
			Direction sort = null;
			if(direction.equalsIgnoreCase("ASC"))
				 sort = Sort.Direction.ASC;
			else if(direction.equalsIgnoreCase("DESC")){
				 sort = Sort.Direction.DESC;
			    }
			
			Iterable<MenuBean> bean = MenuRep.findAll(new PageRequest(
					start, size,sort,field));
			ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
			if (bean != null) {
				for (MenuBean menuBean : bean) {
					menuList.add(menuBean);
				}
				logger.info(this.getClass()+"Ended getSortPageMenu");
				return new ResponseEntity<List<MenuBean>>(menuList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<MenuBean>>(menuList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("Mandatry parameter missing..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	
	@RequestMapping(value = "/image", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif", method = RequestMethod.GET)
    public @ResponseBody byte[] getImage() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("D:\\biddinglab\\webapp\\webcontent\\jsp\\images\\bid\\bidding.png");
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write( bufferedImage  , "jpg", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	@PostMapping("/uploadMenu")
	public @ResponseBody ResponseEntity<Object> uploadFile(
	    @RequestParam("menuPic") MultipartFile menuPic) {
		 logger.info(this.getClass()+"uploadFile method started");
	  
	  try {
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	    String filename = menuPic.getOriginalFilename();
	    //String directory = NotifyConstant.MANU_IMAGES_LOC;
	    String filepath = Paths.get(null, filename).toString();
	    
	    // Save the file locally
	    BufferedOutputStream stream =
	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    stream.write(menuPic.getBytes());
	    stream.close();
	    logger.info(this.getClass()+"uploadFile method ended");
	  }
	  catch (Exception e) {
	    logger.error(e.getMessage());
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
