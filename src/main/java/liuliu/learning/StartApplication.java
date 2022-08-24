package liuliu.learning;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import liuliu.learning.db.entity.UserInfo;
import liuliu.learning.db.service.UserInfoService;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
@SpringBootApplication
public class StartApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Autowired
	private UserInfoService userInfoService;

	@Override
	public void run(String... args) {
	    //database one	    
		try {
			List<UserInfo> listAll = this.userInfoService.listAllOne();
			listAll.forEach((userInfo) -> log.info(userInfo.toString()));
		} catch (Exception e) {
			//MyException myException = (MyException) e.getCause();
			//log.info("MyException name:{}", myException.getName());
			log.error("try catch Exception", e);
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail("email@email.com");
		userInfo.setId(1001);
		userInfo.setName("name");
		this.userInfoService.insertOne(userInfo);

		//database two
        try {
            List<UserInfo> listAll2 = this.userInfoService.listAllTwo();
            listAll2.forEach((userInfo2) -> log.info(userInfo2.toString()));
        } catch (Exception e) {
            log.error("try catch Exception", e);
        }
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setEmail("email@email.com");
        userInfo2.setId(1001);
        userInfo2.setName("name");
        this.userInfoService.insertTwo(userInfo2);
	}

}
