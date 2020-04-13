package gestionCompte.app;


import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gestionCompte.app.service.FilesStorageService;


@SpringBootApplication
public class Application implements CommandLineRunner {
	@Resource
	  FilesStorageService storageService;
 
	  public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	  }
	  
	  @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }

}