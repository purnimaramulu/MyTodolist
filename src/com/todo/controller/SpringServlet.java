package com.todo.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
//import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.todo.jdo.ToDo;
import com.todo.jdo.ToDoList;
import com.todo.util.PMF;


@Controller
public class SpringServlet {


	 
	 @RequestMapping("/hello")
	   public String hello(ModelMap model) 
		{
	      model.addAttribute("message", "Hello Spring MVC Framework!");

	      return "hello";
	   }
//	 @RequestMapping(value = "/addData", method = RequestMethod.GET)
//		
//	 public String add(HttpServletRequest request, ModelMap model) {
//
//			String data = request.getParameter("data");
//			System.out.println(" data"+data);
//			
//			ToDo c = new ToDo();
//			c.setdata(data);
//			
//			
//
//			PersistenceManager pm = PMF.get().getPersistenceManager();
//			try {
//			pm.makePersistent(c);
//			} finally {
//			pm.close();
//			}
//           return "hello";
//			}
//	 
	 
	 
	 @RequestMapping(value = "/addsave", method = RequestMethod.POST)
	@ResponseBody	
	 public String save(HttpServletRequest request) {

			String data = (String)request.getParameter("data");
			System.out.println(" data"+data);
			
			//long first14 =(long) (Math.random() * 100000000000000L);

		    //System.out.println("Randon "+first14); 
		
			
			/*ToDo c = new ToDo();
			c.setdata(data);
			c.setKey(first14);*/
			
			ToDoList d = new ToDoList();
			d.setData(data);
			

			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
			pm.makePersistent(d);
			} finally {
			pm.close();
			}
        return data;
			}

	 
	 
	 @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
		@ResponseBody	
		 public String retrieve(HttpServletRequest request) {
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				javax.jdo.Query q = pm.newQuery(ToDoList.class);
				
				List<ToDoList> results = new ArrayList<ToDoList>();
				String output = "";
				
				try {
					results = (List<ToDoList>) q.execute();
					if (results.size() > 0) {
						// good for listing
						Gson gson = new Gson();
						output = gson.toJson(results);
					}
				}catch(Exception e) {
					e.printStackTrace();
					return output;
				} 
				finally {
					q.closeAll();
					pm.close();
				}
				return output;

	 }
	 @RequestMapping(value = "/destroy", method = RequestMethod.POST)
		public @ResponseBody String delete(@RequestParam long key,
				HttpServletRequest request, ModelMap model) {
              System.out.print("deleting");
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {

				ToDoList c = pm.getObjectById(ToDoList.class, key);
				pm.deletePersistent(c);

			} finally {
				pm.close();
			}

			// return to list
			return "deleted";

		}

 @RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @RequestBody String data) {
	 
	 System.out.println(data);
	 //JSONObject	obj=	new JSONObject(data);	
	 Gson gson = new Gson();
	 TypeToken<List<ToDoList>> list = new TypeToken<List<ToDoList>>(){};
	 
	 ToDoList myList = gson.fromJson(data, ToDoList.class);
	 System.out.println(myList.getData());

////		String key = request.getParameter("data");
////
////		String mydata = request.getParameter("mydata");

	PersistenceManager pm = PMF.get().getPersistenceManager();
	try {

			ToDoList c= pm.getObjectById(ToDoList.class, myList.getKey());

			c.setData(myList.getData());
		pm.makePersistent(c);

		} finally {
			pm.close();
		}
		
	

	// return to list
	return "hello";

	}

	 
}
	 

