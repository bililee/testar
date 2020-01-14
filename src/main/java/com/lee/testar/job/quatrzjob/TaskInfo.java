package com.lee.testar.job.quatrzjob;

import java.io.Serializable;

/**
 * TaskInfo
 *
 * @author Lee
 * @date 2020/1/12
 */
public class TaskInfo implements Serializable {
    private static final long serialVersionUID = -8054692082716173379L;

    /**
     * 增加或修改标识
     */
    private int id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务描述
     */
    private String jobDescription;

    /**
     * 任务状态
     */
    private String jobStatus;

    /**
     * 任务表达式
     */
    private String cronExpression;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 间隔时间（毫秒）
     */
    private String milliSeconds;

    /**
     * 重复次数
     */
    private String repeatCount;

    /**
     * 起始时间
     */
    private String startDate;

    /**
     * 终止时间
     */
    private String endDate;

    /**
     * @return the milliSeconds
     */
    public String getMilliSeconds() {
        return milliSeconds;
    }

    /**
     * @param milliSeconds the milliSeconds to set
     */
    public void setMilliSeconds(String milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    /**
     * @return the repeatCount
     */
    public String getRepeatCount() {
        return repeatCount;
    }

    /**
     * @param repeatCount the repeatCount to set
     */
    public void setRepeatCount(String repeatCount) {
        this.repeatCount = repeatCount;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
