package com.inno.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inno.dao.DbLog;
import com.inno.service.FileLog;
import com.inno.service.UserLog;
import com.inno.utility.CountryModel;
import com.inno.utility.FileModel;
import com.inno.utility.RegisterModel;
import com.inno.utility.SearchModel;

@Controller
public class RegistrCntrller {
	@Autowired
	DbLog Dblogserv;
	@Autowired
	FileLog FillogServ;
	@Autowired
	UserLog UserLogServ;
	private static String saveDirectory = "D://test//";
	@RequestMapping(value="/dropdowndata",method=RequestMethod.GET)
	 public ModelAndView getcountrylist(CountryModel countryModel,HttpServletRequest request ,HttpServletResponse response){
		
		ModelAndView andView=new ModelAndView();
		
		ArrayList<CountryModel> country_data= FillogServ.getcountrydata();
		
		System.out.println(country_data);
		andView.addObject("condata", country_data);
		andView.setViewName("register");
		
		
		
		
		return andView;
		
	}
	
	
	@RequestMapping(value="/downloaddp",method=RequestMethod.GET)
	  public void downloadfildse(HttpServletRequest requst,HttpServletResponse response,RegisterModel model){
		
		System.out.println(model.getId());
		
		RegisterModel  dpModel= FillogServ.getdp(model.getId()); 
	
		if(dpModel!=null){
			try {
				
				response.setContentType("images/jpeg");
				
				
				OutputStream ostrm=response.getOutputStream();
				

				
				
				ostrm.write(dpModel.getDataimg());
				ostrm.flush();
				ostrm.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
			
			
		}
		
	
		
	}
	
	@RequestMapping(value = "/changedp", method = RequestMethod.POST)
	ModelAndView doupload(@RequestParam("file") CommonsMultipartFile[] file, HttpSession session,RegisterModel fileModel)
			throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		//byte[] bytes=file.getBytes();
	//fileModel.setId(22);
		if (file != null && file.length> 0) {

			for (CommonsMultipartFile aFile : file) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				System.out.println(aFile.getInputStream());
				
				InputStream inputStream = aFile.getInputStream();
				
				
				boolean saveDBauth = UserLogServ.uploadtoDb(inputStream,fileModel);
				if (saveDBauth){
					if (!aFile.getOriginalFilename().equals("")) {
						aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
					}
				}
			}
		}
		
		/*BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File(saveDirectory+File.separator+file.getOriginalFilename())));
		stream.write(bytes);
		stream.flush();
		stream.close();*/
		modelAndView.addObject("msg", "DP changed successfuly");
		modelAndView.setViewName("dashboard");

		return modelAndView;
	}
	
	
@RequestMapping(value="/viewprofile",method=RequestMethod.GET)
 public ModelAndView getuserprfl(RegisterModel model,HttpServletRequest request){
	ModelAndView  modelAndView= new ModelAndView();
	String idd=(String) request.getSession().getAttribute("id");
	System.out.println(idd);
	String sql="select * from springweb.register where id=? ";
	ArrayList<RegisterModel> userdatat= Dblogserv.getdbdata(sql,idd);
	if (userdatat != null) {
        
		modelAndView.addObject("message", "View Successful");
		modelAndView.setViewName("view");
		modelAndView.addObject("dbdata", userdatat);
		
		return modelAndView;
	} else {
		modelAndView.setViewName("view");
		modelAndView.addObject("message", "view failed");

	}
	return modelAndView;
	
}
	
@RequestMapping(value="/downloadDp.do",method=RequestMethod.GET)
public void downloadfile(RegisterModel fileModel,HttpServletRequest requst,HttpServletResponse response){
	System.out.println(fileModel.getId());
	if(fileModel.getName()==null){
		String ids= (String) requst.getSession().getAttribute("id");
		fileModel.setId(ids);
	}
	
	ModelAndView modelAndView = new ModelAndView();
	RegisterModel  dpzModel= FillogServ.getdp(fileModel.getId()); 
	System.out.println(dpzModel);
try {
	String key="content-disposition";
	String value=String.format("attachment; filename=\"%s\"","downoadlads");
	response.setHeader(key, value);
	response.setContentType("images/jpeg");
	
	
	OutputStream ostrm=response.getOutputStream();
	modelAndView.addObject("filmsg", "downloaded successfully");
	//modelAndView.setViewName("Fileform");


	
	ostrm.write(dpzModel.getDataimg());
	ostrm.flush();
	ostrm.close();
} catch (IOException e) {

	e.printStackTrace();
}

}




@RequestMapping(value="/searchdb")
@ResponseBody
	public ArrayList<RegisterModel> dolog(@RequestBody SearchModel search_param){
	System.out.println(search_param);
	
	ArrayList<RegisterModel>datalist= UserLogServ.searchdata(search_param.getSearchdata());
	return datalist;
	
	
}

}
