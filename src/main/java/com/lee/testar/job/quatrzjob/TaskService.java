package com.lee.testar.job.quatrzjob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * TaskService
 *
 * @author Lee
 * @date 2020/1/12
 */
@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private Scheduler scheduler;

    /**
     * @param @return    参数
     * @return List<TaskInfo>    返回类型
     * @throws
     * @Title: list
     * @Description: 任务列表
     */
    public List<TaskInfo> queryJobList() {
        LOGGER.info("TaskService--data-s-->queryJobList()");
        List<TaskInfo> list = new ArrayList<>();
        try {
            for (String groupJob : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))) {
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers) {
                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                        String cronExpression = "";
                        String createTime = "";
                        String milliSeconds = "";
                        String repeatCount = "";
                        String startDate = "";
                        String endDate = "";
                        if (trigger instanceof CronTrigger) {
                            CronTrigger cronTrigger = (CronTrigger) trigger;
                            cronExpression = cronTrigger.getCronExpression();
                            createTime = cronTrigger.getDescription();
                        } else if (trigger instanceof SimpleTrigger) {
                            SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
                            milliSeconds = simpleTrigger.getRepeatInterval() + "";
                            repeatCount = simpleTrigger.getRepeatCount() + "";
                            // test
                            startDate = "2020-01-14 00:00:00";
                            endDate = "2020-01-14 00:00:00";
//                            startDate = DateUtil.getDateStr(
//                                    simpleTrigger.getStartTime(),
//                                    DateUtil.FORMAT_HOUR_DATE_TIME);
//                            endDate = DateUtil.getDateStr(simpleTrigger.getEndTime(), DateUtil.FORMAT_HOUR_DATE_TIME);
                        }
                        TaskInfo info = new TaskInfo();
                        info.setJobName(jobKey.getName());
                        info.setJobGroup(jobKey.getGroup());
                        info.setJobDescription(jobDetail.getDescription());
                        info.setJobStatus(triggerState.name());
                        info.setCronExpression(cronExpression);
                        info.setCreateTime(createTime);

                        info.setRepeatCount(repeatCount);
                        info.setStartDate(startDate);
                        info.setMilliSeconds(milliSeconds);
                        info.setEndDate(endDate);
                        list.add(info);
                    }
                }
            }
            LOGGER.info("任务的数量为：---------------->" + list.size());
        } catch (SchedulerException e) {
            LOGGER.info("查询任务失败，原因是：------------------>" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param @param        inputMap
     * @param @return    参数
     * @return Boolean    返回类型
     * @throws
     * @Title: setSimpleTrigger
     * @Description: 简单调度
     */
    @SuppressWarnings({"unchecked"})
    public void setSimpleTriggerJob(TaskInfo info) {
        LOGGER.info("TaskService--data-s-->setSimpleTriggerJob()" + info);
        String jobName = info.getJobName();
        String jobGroup = info.getJobGroup();
        String jobDescription = info.getJobDescription();
        Long milliSeconds = Long.parseLong(info.getMilliSeconds());
        Integer repeatCount = Integer.parseInt(info.getRepeatCount());
//        Date startDate = DateUtil.parseDate(info.getStartDate());
//        Date endDate = DateUtil.parseDate(info.getEndDate());
        // test
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);// 触发器的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);// job的key值
            if (checkExists(jobName, jobGroup)) {
                LOGGER.info(
                        "===> AddJob fail, job already exist, jobGroup:{}, jobName:{}",
                        jobGroup, jobName);
//                throw new ServiceException(String.format(
//                        "Job已经存在, jobName:{%s},jobGroup:{%s}", jobName,
//                        jobGroup));
            }
            /* 简单调度 */
            SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .startAt(startDate)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInMilliseconds(milliSeconds)
                                    .withRepeatCount(repeatCount))
                    .endAt(endDate).build();
            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            LOGGER.info("任务添加失败！--->简单调度" + e.getMessage());
//            throw new ServiceException("任务添加失败！--->简单调度");
        }
    }

    /**
     * @param @param info    参数
     * @return void    返回类型
     * @throws
     * @Title: addJob
     * @Description: 保存定时任务
     */
    @SuppressWarnings("unchecked")
    public void addJob(TaskInfo info) {
        LOGGER.info("TaskService--data-s-->addJob()" + info);
        String jobName = info.getJobName(), jobGroup = info.getJobGroup(), cronExpression = info
                .getCronExpression(), jobDescription = info.getJobDescription(), createTime = DateFormatUtils
                .format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (checkExists(jobName, jobGroup)) {
                LOGGER.info(
                        "===> AddJob fail, job already exist, jobGroup:{}, jobName:{}",
                        jobGroup, jobName);
//                throw new ServiceException(String.format(
//                        "Job已经存在, jobName:{%s},jobGroup:{%s}", jobName,
//                        jobGroup));
            }

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey).withDescription(createTime)
                    .withSchedule(schedBuilder).build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            LOGGER.info("保存定时任务-->类名不存在或执行表达式错误--->复杂调度" + e.getMessage());
//            throw new ServiceException("类名不存在或执行表达式错误");
        }
    }

    /**
     * @param @param info    参数
     * @return void    返回类型
     * @throws
     * @Title: edit
     * @Description: 修改定时任务
     */
    public void editJob(TaskInfo info) {
        LOGGER.info("TaskService--data-s-->editJob()" + info);
        String jobName = info.getJobName(), jobGroup = info.getJobGroup(), cronExpression = info
                .getCronExpression(), jobDescription = info.getJobDescription(), createTime = DateFormatUtils
                .format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
//                throw new Exception(
//                        String.format("Job不存在, jobName:{%s},jobGroup:{%s}",
//                                jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey).withDescription(createTime)
                    .withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            LOGGER.info("修改定时任务-->类名不存在或执行表达式错误--->复杂调度" + e.getMessage());
//            throw new ServiceException("类名不存在或执行表达式错误");
        }

    }

    /**
     * @param @param jobName
     * @param @param jobGroup    参数
     * @return void    返回类型
     * @throws
     * @Title: delete
     * @Description: 删除定时任务
     */
    public void deleteJob(String jobName, String jobGroup) {
        LOGGER.info("TaskService--data-s-->deleteJob()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                LOGGER.info("===> delete, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            LOGGER.info("删除定时任务-->复杂调度" + e.getMessage());
//            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param @param jobName
     * @param @param jobGroup    参数
     * @return void    返回类型
     * @throws
     * @Title: pause
     * @Description: 暂停定时任务
     */
    public void pauseJob(String jobName, String jobGroup) {
        LOGGER.info("TaskService--data-s-->pauseJob()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                LOGGER.info("===> Pause success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            LOGGER.info("暂停定时任务-->复杂调度" + e.getMessage());
//            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param @param jobName
     * @param @param jobGroup    参数
     * @return void    返回类型
     * @throws
     * @Title: resume
     * @Description: 恢复暂停任务
     */
    public void resumeJob(String jobName, String jobGroup) {
        LOGGER.info("TaskService--data-s-->resumeJob()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.resumeTrigger(triggerKey);
                LOGGER.info("===> Resume success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            LOGGER.info("重新开始任务-->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param @param  jobName
     * @param @param  jobGroup
     * @param @return
     * @param @throws SchedulerException    参数
     * @return boolean    返回类型
     * @throws
     * @Title: checkExists
     * @Description: 验证任务是否存在
     */
    private boolean checkExists(String jobName, String jobGroup)
            throws SchedulerException {
        LOGGER.info("TaskService--data-s-->checkExists()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
