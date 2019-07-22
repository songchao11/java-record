package com.song.record.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class QuartzUtils {
    private static Logger logger = LoggerFactory.getLogger(QuartzUtils.class);

    private static String jobGroup = "parkSchedulerJobGroup";

    private static SchedulerFactory schedFact;

    private static Scheduler scheduler;

    static{
        try {
            schedFact = new StdSchedulerFactory();
            scheduler = schedFact.getScheduler();
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
    }

    public static void createJob(String jobName, String cronExpression, Class<? extends Job> T) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            if (trigger == null) {
                JobDetail jobDetail = JobBuilder.newJob(T).withIdentity(jobName, jobGroup).build();
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                Date ft = scheduler.scheduleJob(jobDetail, trigger);

                logger.info(jobDetail.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                        + trigger.getCronExpression());
            } else {
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                ((CronTriggerImpl) trigger).setStartTime(new Date());
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
    }

    public static void startScheduler(){
        logger.info("------- Starting Scheduler ----------------");
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("开启scheduler出现异常",e);
        }
        logger.info("------- Started Scheduler -----------------");
    }

    public static boolean deleteJob(String jobName) {
        try {
            Scheduler scheduler = schedFact.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            return  scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public static void updateJob(String jobName, String cronExpression) {
        logger.info("更新 {} 定时器，间隔为：{}",jobName,cronExpression);
        try {
            Scheduler scheduler = schedFact.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(trigger == null) {
                logger.warn("没有该名称的定时器");
                return;
            }
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException  e) {
            logger.error(e.getMessage());
        }
    }

    // 项目结束时执行
    public static void shutDown() {
        Scheduler scheduler;
        try {
            scheduler = schedFact.getScheduler();
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
    }
}
