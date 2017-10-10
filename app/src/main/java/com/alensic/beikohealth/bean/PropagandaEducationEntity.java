package com.alensic.beikohealth.bean;

import java.io.Serializable;

/**
 * 知识实体类
 * Created by 郑依民 on 2016/8/1.
 */
public class PropagandaEducationEntity implements Serializable {

    /**
     * propaganda_education_sn : pecontent_708000ec-c5f4-4ff5-a023-00bab200dc96
     * create_by : admin
     * create_time : 1470376167000
     * update_by : admin
     * update_time : 1470376167000
     * audit_state : 1
     * propaganda_education_title : 啊啊啊
     * summary_image :
     * summary_content : 啊啊啊啊
     * propaganda_education_content : 啊啊啊啊啊啊啊啊
     * is_disabled : 0
     * deleted_time : 1470376167000
     * record_hospital_no : 01
     * good_job_num : 0
     * share_num : 0
     * read_num : 0
     * is_publish : 0
     */

    private String propaganda_education_sn;
    private String create_by;
    private long create_time;
    private String update_by;
    private long update_time;
    private int audit_state;
    private String propaganda_education_title;// 标题
    private String summary_image;
    private String summary_content;// 摘要
    private String propaganda_education_content;// 内容
    private int is_disabled;
    private long deleted_time;
    private String record_hospital_no;
    private int good_job_num;
    private int share_num;
    private int read_num;
    private int is_publish;
    private String summary_image_path;
    private String propaganda_education_collect_sn;
    private String good_job_sn;
    private String pe_video_path;
    private long push_time;

    public String getPe_video_path() {
        return pe_video_path;
    }

    public void setPe_video_path(String pe_video_path) {
        this.pe_video_path = pe_video_path;
    }

    public long getPush_time() {
        return push_time;
    }

    public void setPush_time(long push_time) {
        this.push_time = push_time;
    }

    public String getPropaganda_education_collect_sn() {
        return propaganda_education_collect_sn;
    }

    public void setPropaganda_education_collect_sn(String propaganda_education_collect_sn) {
        this.propaganda_education_collect_sn = propaganda_education_collect_sn;
    }

    public String getGood_job_sn() {
        return good_job_sn;
    }

    public void setGood_job_sn(String good_job_sn) {
        this.good_job_sn = good_job_sn;
    }

    public String getSummary_image_path() {
        return summary_image_path;
    }

    public void setSummary_image_path(String summary_image_path) {
        this.summary_image_path = summary_image_path;
    }

    public String getPropaganda_education_sn() {
        return propaganda_education_sn;
    }

    public void setPropaganda_education_sn(String propaganda_education_sn) {
        this.propaganda_education_sn = propaganda_education_sn;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public int getAudit_state() {
        return audit_state;
    }

    public void setAudit_state(int audit_state) {
        this.audit_state = audit_state;
    }

    public String getPropaganda_education_title() {
        return propaganda_education_title;
    }

    public void setPropaganda_education_title(String propaganda_education_title) {
        this.propaganda_education_title = propaganda_education_title;
    }

    public String getSummary_image() {
        return summary_image;
    }

    public void setSummary_image(String summary_image) {
        this.summary_image = summary_image;
    }

    public String getSummary_content() {
        return summary_content;
    }

    public void setSummary_content(String summary_content) {
        this.summary_content = summary_content;
    }

    public String getPropaganda_education_content() {
        return propaganda_education_content;
    }

    public void setPropaganda_education_content(String propaganda_education_content) {
        this.propaganda_education_content = propaganda_education_content;
    }

    public int getIs_disabled() {
        return is_disabled;
    }

    public void setIs_disabled(int is_disabled) {
        this.is_disabled = is_disabled;
    }

    public long getDeleted_time() {
        return deleted_time;
    }

    public void setDeleted_time(long deleted_time) {
        this.deleted_time = deleted_time;
    }

    public String getRecord_hospital_no() {
        return record_hospital_no;
    }

    public void setRecord_hospital_no(String record_hospital_no) {
        this.record_hospital_no = record_hospital_no;
    }

    public int getGood_job_num() {
        return good_job_num;
    }

    public void setGood_job_num(int good_job_num) {
        this.good_job_num = good_job_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public int getRead_num() {
        return read_num;
    }

    public void setRead_num(int read_num) {
        this.read_num = read_num;
    }

    public int getIs_publish() {
        return is_publish;
    }

    public void setIs_publish(int is_publish) {
        this.is_publish = is_publish;
    }

    @Override
    public String toString() {
        return "PropagandaEducationEntity{" +
                "propaganda_education_sn='" + propaganda_education_sn + '\'' +
                ", create_by='" + create_by + '\'' +
                ", create_time=" + create_time +
                ", update_by='" + update_by + '\'' +
                ", update_time=" + update_time +
                ", audit_state=" + audit_state +
                ", propaganda_education_title='" + propaganda_education_title + '\'' +
                ", summary_image='" + summary_image + '\'' +
                ", summary_content='" + summary_content + '\'' +
                ", propaganda_education_content='" + propaganda_education_content + '\'' +
                ", is_disabled=" + is_disabled +
                ", deleted_time=" + deleted_time +
                ", record_hospital_no='" + record_hospital_no + '\'' +
                ", good_job_num=" + good_job_num +
                ", share_num=" + share_num +
                ", read_num=" + read_num +
                ", is_publish=" + is_publish +
                ", summary_image_path='" + summary_image_path + '\'' +
                ", propaganda_education_collect_sn='" + propaganda_education_collect_sn + '\'' +
                ", good_job_sn='" + good_job_sn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o==null)
            return false;
        return this.toString().equals(o.toString());
    }
}
