package com.inno.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inno.service.FileLog;
import com.inno.service.UserLog;
import com.inno.utility.FileModel;
import com.inno.utility.LoginModel;
import com.inno.utility.RegisterModel;

@Controller

public class LoginController {
	@Autowired
	UserLog UserLogServ;
	@Autowired
	FileLog FillogServ;
	@Autowired
	HttpSession session;

	private static String saveDirectory = "D://test//";

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public ModelAndView logincontrol(RegisterModel MODEL, HttpServletResponse responsese) {
		ModelAndView modelAndView = new ModelAndView();
		
		
		System.out.println("user:"+MODEL.getName() + MODEL.getPassword());

		boolean auth = UserLogServ.UserLogin(MODEL);
		if (auth) {
			session.setAttribute("websessn", MODEL.getRole());
			session.setAttribute("id", MODEL.getId());
			modelAndView.setViewName("dashboard");
			Cookie cookie = new Cookie("ckid", "#Role@::" +MODEL.getRole());

			responsese.addCookie(cookie);
			return modelAndView;
		} else {
			modelAndView.addObject("message", "Login fAILED TrY ONCE more");
			modelAndView.setViewName("index");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView Regcontrol( RegisterModel model) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		
		 InputStream inputStream=model.getImage().getInputStream();
		System.out.println(
				"name :=" + model.getAddress() + "&" + model.getId() + "&" + model.getName() + "&" + model.getEmail());
		boolean regauth = UserLogServ.RegData(model,inputStream);
		if (regauth) {

			ArrayList<RegisterModel> CtrlAuth = UserLogServ.viewdata();
			if (CtrlAuth != null) {	
				modelAndView.addObject("message", "Registration Successful");
				modelAndView.setViewName("dashboard");
				modelAndView.addObject("dbdata", CtrlAuth);

				return modelAndView;

			}
		
		} else {
			modelAndView.setViewName("register");
			modelAndView.addObject("message", "Registration failed tryONce mORE");
		}
		
		return modelAndView;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView postupdate(RegisterModel registerModel) {

		ModelAndView modelAndView = new ModelAndView();
		boolean UpdateAuth = UserLogServ.Updatedb(registerModel);
		if (UpdateAuth) {

			ArrayList<RegisterModel> updateddata = UserLogServ.viewdata();
			if (updateddata != null) {
				modelAndView.setViewName("view");
				modelAndView.addObject("message", "Updation sucessful");
				modelAndView.addObject("dbdata", updateddata);
				return modelAndView;
			} else {
				modelAndView.setViewName("view");
				modelAndView.addObject("message", "Updation failed");

			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView doview(RegisterModel Viewobj,HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<RegisterModel> CtrlAuth = UserLogServ.viewdata();
		if (CtrlAuth != null) {
	               
			modelAndView.addObject("message", "View Successful");
			modelAndView.setViewName("view");
			modelAndView.addObject("dbdata", CtrlAuth);
			
			return modelAndView;
		} else {
			modelAndView.setViewName("view");
			modelAndView.addObject("message", "view failed");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView dosearch(@RequestParam() String searchdata) {

		ModelAndView modelAndView = new ModelAndView();

		ArrayList<RegisterModel> SearchData = UserLogServ.searchdata(searchdata);
		if (SearchData != null) {
			modelAndView.addObject("message", "Search success");
			modelAndView.setViewName("dashboard");
			modelAndView.addObject("dbdata", SearchData);
			return modelAndView;
		} else {

			modelAndView.addObject("message", "search fail");
			modelAndView.setViewName("dashboard");
			return modelAndView;
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView dodelete(@RequestParam("name") String name) {
		ModelAndView modelAndView = new ModelAndView();
		boolean deleteResult = UserLogServ.Deleteuser(name);
		if (deleteResult) {
			ArrayList<RegisterModel> CtrlAuth = UserLogServ.viewdata();
			if (CtrlAuth != null) {
				modelAndView.addObject("message", "deletion Successful");
				modelAndView.setViewName("view");
				modelAndView.addObject("dbdata", CtrlAuth);
				return modelAndView;
			} else {
				modelAndView.addObject("message", "deletion fail");
				modelAndView.setViewName("view");
				return modelAndView;
			}
		}
		return modelAndView;

	}

	@RequestMapping(value = "/search1", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<RegisterModel> srchnewdata(@RequestParam String searchdata) {

		ArrayList<RegisterModel> dbdata = UserLogServ.searchdata(searchdata);
		if (dbdata != null) {

			System.out.println("size of searchdata" + dbdata.size());

			return dbdata;
		} else {
			System.out.println("fail searc");
			return null;
		}
	}

	@RequestMapping(value = "/Logout")
	String dologout(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getSession().getAttribute("websessn"));
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	ModelAndView doupload(@RequestParam("file") CommonsMultipartFile[] file, HttpSession session,RegisterModel fileModel)
			throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		//byte[] bytes=file.getBytes();
	
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
		modelAndView.addObject("uploadreslt", "File uploaded successfuly");
		modelAndView.setViewName("Fileform");

		return modelAndView;
	}

	
	
	
}