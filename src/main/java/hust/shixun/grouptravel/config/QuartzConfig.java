package hust.shixun.grouptravel.config;

import hust.shixun.grouptravel.util.statusJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail statusJob(){
        return JobBuilder.newJob(statusJob.class).withIdentity("statusJob").storeDurably().build();
    }

    @Bean
    public Trigger statusJobTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(statusJob())
                .withIdentity("statusJob")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
