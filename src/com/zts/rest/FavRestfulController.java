/**
 * 
 */
package com.zts.rest;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Zhangkh
 *
 */
@RestController
public class FavRestfulController {
	
	//http://localhost:8080/TestRestful/getUserName?name=zhangkh
	@RequestMapping(value="getUserName",method=RequestMethod.GET)
    public  String getUserName(@RequestParam(value="name") String name){
		String res = "{\"name\":\""+name+"\"}";
        return res;
    }
	
	@RequestMapping(value="hello",method=RequestMethod.GET)
    public String hello(){
        return "hello world!";
    }
	
	//http://localhost:8080/TestRestful/getName/zhangkh/123
	@RequestMapping(value="getName/{name}/{id}",method=RequestMethod.GET)
    public @ResponseBody String getName(  @PathVariable  String name, @PathVariable  String id){
        return name+" "+id;
    }
	
	@RequestMapping("getFavUser")
    public  FavUser getFavUser(@RequestParam("userName") String userName,@RequestParam("userId")String userId,@RequestParam("userAge")int userAge){
        FavUser favUser = new FavUser();
        favUser.setUserId(userId);
        favUser.setUserName(userName);
        favUser.setUserAge(userAge);
        return favUser;
    }
     
    @RequestMapping("getFavUserBody")
    public FavUser getFavUserBody(@RequestBody String body){
        ObjectMapper mapper = new ObjectMapper();
        FavUser favUser = null;
        try {
            favUser = mapper.readValue(body,  FavUser.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return favUser;
    }


}
