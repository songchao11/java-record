package com.song.record.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzTest {

    public static void main(String[] args){
        System.out.println("开始执行");
        QuartzUtils.createJob("testJob", "0/1 * * * * ?", TestJob.class);
    }

}

class TestJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("这是定时任务！");
    }
}
