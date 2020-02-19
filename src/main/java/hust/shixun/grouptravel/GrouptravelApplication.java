package hust.shixun.grouptravel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hust.shixun.grouptravel")
public class GrouptravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrouptravelApplication.class, args);
    }

}
