package pl.eurobusiness;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
 @EnableAutoConfiguration
 public class SampleController {

 @RequestMapping("/")
 @ResponseBody
 String home() {
 return "Hello World!";
 }

 @RequestMapping("/test")
 String test(){
     return "test";
 }

 public static void main(String[] args) throws Exception {
 SpringApplication.run(SampleController.class, args);
 }
 }