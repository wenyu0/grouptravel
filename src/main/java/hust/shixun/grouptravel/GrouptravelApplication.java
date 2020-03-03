package hust.shixun.grouptravel;

import hust.shixun.grouptravel.util.statusJob;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@SpringBootApplication
public class GrouptravelApplication {

    public static void main(String[] args){
        SpringApplication.run(GrouptravelApplication.class, args);
    }

}
