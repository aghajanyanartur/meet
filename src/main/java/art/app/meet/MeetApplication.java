package art.app.meet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetApplication.class, args);
	}

}
