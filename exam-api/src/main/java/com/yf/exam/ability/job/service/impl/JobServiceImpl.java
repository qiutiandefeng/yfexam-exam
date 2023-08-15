package com.yf.exam.ability.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yf.exam.ability.job.enums.JobGroup;
import com.yf.exam.ability.job.service.JobService;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author bool
 */
@Log4j2
@Service
public class JobServiceImpl implements JobService {

    /**
     * Quartz定时任务核心的功能实现类
     */
    private Scheduler scheduler;

    /**
     * 注入
     * @param schedulerFactoryBean
     */
    public JobServiceImpl(@Autowired SchedulerFactoryBean schedulerFactoryBean) {
        scheduler = schedulerFactoryBean.getScheduler();
    }


    @Override
    public void addCronJob(Class jobClass, String jobName, String cron, String data) {


        String jobGroup = JobGroup.SYSTEM;

        // 自动命名
        if(StringUtils.isEmpty(jobName)){
            jobName = jobClass.getSimpleName().toUpperCase() + "_"+IdWorker.getIdStr();
        }

        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                log.info("++++++++++任务：{} 已存在", jobName);
                this.deleteJob(jobName, jobGroup);
            }

            log.info("++++++++++构建任务：{},{},{},{},{} ", jobClass.toString(), jobName, jobGroup, cron, data);

            //构建job信息
            jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
            //用JopDataMap来传递数据
            jobDetail.getJobDataMap().put(TASK_DATA, data);

            //按新的cronExpression表达式构建一个新的trigger
            Trigger trigger = null;

            // 有表达式的按表达式
            if(!StringUtils.isEmpty(cron)){
                log.info("+++++表达式执行:"+ JSON.toJSONString(jobDetail));
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();
            }else{
                // 无表达式则立即执行
                log.info("+++++立即执行:"+ JSON.toJSONString(jobDetail));
                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).startNow().build();
            }

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addCronJob(Class jobClass, String jobName, String data) {
        // 立即执行任务
        this.addCronJob(jobClass, jobName, null, data);
    }


    @Override
    public void pauseJob(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            scheduler.pauseTrigger(triggerKey);
            log.info("++++++++++暂停任务：{}", jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            scheduler.resumeTrigger(triggerKey);
            log.info("++++++++++重启任务：{}", jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
            scheduler.deleteJob(jobKey);
            log.info("++++++++++删除任务：{}", jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
