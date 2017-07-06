package com.inno.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.inno.service.FileLog;
import com.inno.service.UserLog;
import com.inno.utility.FileModel;
import com.inno.utility.RegisterModel;

@Controller
public class FilesControler {
@Autowired
FileLog FillogServ;
	@Autowired
	UserLog UserLogServ;
	
	@RequestMapping(value="/Fileform",method=RequestMethod.GET)
	 public ModelAndView viewdata(){
      ArrayList<FileModel> sqldata=UserLogServ.viewdbfile();
      ModelAndView andView = new ModelAndView();
      andView.addObject("files", sqldata);
      andView.setViewName("Fileform");
      return andView;
      }
	
	@RequestMapping(value="/download.htm",method=RequestMethod.GET)
	  public void downloadfile(FileModel fileModel,HttpServletRequest requst,HttpServletResponse response){
		System.out.println(fileModel.getId());
		
		FileModel finaldata=FillogServ.getcopy(fileModel.getId());
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(finaldata);
	try {
		String key="content-disposition";
		String value=String.format("attachment; filename=\"%s\"","downoadlads");
		response.setHeader(key, value);
		response.setContentType(finaldata.getFiletype());
		
		
		OutputStream ostrm=response.getOutputStream();
		modelAndView.addObject("filmsg", "downloaded successfully");
		modelAndView.setViewName("Fileform");

		
		
		ostrm.write(finaldata.getFile_Data());
		ostrm.flush();
		ostrm.close();
	} catch (IOException e) {
	
		e.printStackTrace();
	}
		
	}
	
	
	@RequestMapping(value="/delete.htm", method=RequestMethod.GET)
	  public ModelAndView deletedata(FileModel fileModel){
	ModelAndView  modelAndView = new ModelAndView();
		
		boolean delteauth=FillogServ.getdelete(fileModel.getId());
		if(delteauth){
			System.out.println("delte from db");
			modelAndView.addObject("deletemsg", "Deletion successful");
			modelAndView.setViewName("Fileform");
			return modelAndView;
		}
		return modelAndView;
		
	}
	
	
	

}
