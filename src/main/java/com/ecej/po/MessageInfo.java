package com.ecej.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wyx on 2017-10-09.
 * 
 * @author wyx
 * @title messageinfo
 * @date 2017-10-09
 */
public class MessageInfo implements Serializable {
    /**
     * 
     * msgcode 
     * msgCode
     */
    private Integer msgcode;

    /**
     * 
     * createtime 
     * createtime
     */
    private Date createtime;

    /**
     * 
     * hasread 
     * hasRead
     */
    private Integer hasread;

    /**
     * 
     * msgcontent 
     * msgContent
     */
    private String msgcontent;

    /**
     * 
     * msgreceiver 
     * msgReceiver
     */
    private String msgreceiver;

    /**
     * 
     * msgtype 
     * msgType
     */
    private String msgtype;

    /**
     * 
     * msgtypedesc 
     * msgTypeDesc
     */
    private String msgtypedesc;

    /**
     * 
     * tasktype 
     * taskType
     */
    private String tasktype;

    /**
     * 
     * updatetable 
     * updateTable
     */
    private String updatetable;

    /**
     * 
     * updatetime 
     * updatetime
     */
    private Date updatetime;

    /**
     * 
     * taskno 
     * taskNo
     */
    private String taskno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.msgCode
     *
     * @return the value of messageinfo.msgCode
     *
     * @mbg.generated
     */
    public Integer getMsgcode() {
        return msgcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.msgCode
     *
     * @param msgcode the value for messageinfo.msgCode
     *
     * @mbg.generated
     */
    public void setMsgcode(Integer msgcode) {
        this.msgcode = msgcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.createtime
     *
     * @return the value of messageinfo.createtime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.createtime
     *
     * @param createtime the value for messageinfo.createtime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.hasRead
     *
     * @return the value of messageinfo.hasRead
     *
     * @mbg.generated
     */
    public Integer getHasread() {
        return hasread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.hasRead
     *
     * @param hasread the value for messageinfo.hasRead
     *
     * @mbg.generated
     */
    public void setHasread(Integer hasread) {
        this.hasread = hasread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.msgContent
     *
     * @return the value of messageinfo.msgContent
     *
     * @mbg.generated
     */
    public String getMsgcontent() {
        return msgcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.msgContent
     *
     * @param msgcontent the value for messageinfo.msgContent
     *
     * @mbg.generated
     */
    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.msgReceiver
     *
     * @return the value of messageinfo.msgReceiver
     *
     * @mbg.generated
     */
    public String getMsgreceiver() {
        return msgreceiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.msgReceiver
     *
     * @param msgreceiver the value for messageinfo.msgReceiver
     *
     * @mbg.generated
     */
    public void setMsgreceiver(String msgreceiver) {
        this.msgreceiver = msgreceiver == null ? null : msgreceiver.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.msgType
     *
     * @return the value of messageinfo.msgType
     *
     * @mbg.generated
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.msgType
     *
     * @param msgtype the value for messageinfo.msgType
     *
     * @mbg.generated
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.msgTypeDesc
     *
     * @return the value of messageinfo.msgTypeDesc
     *
     * @mbg.generated
     */
    public String getMsgtypedesc() {
        return msgtypedesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.msgTypeDesc
     *
     * @param msgtypedesc the value for messageinfo.msgTypeDesc
     *
     * @mbg.generated
     */
    public void setMsgtypedesc(String msgtypedesc) {
        this.msgtypedesc = msgtypedesc == null ? null : msgtypedesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.taskType
     *
     * @return the value of messageinfo.taskType
     *
     * @mbg.generated
     */
    public String getTasktype() {
        return tasktype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.taskType
     *
     * @param tasktype the value for messageinfo.taskType
     *
     * @mbg.generated
     */
    public void setTasktype(String tasktype) {
        this.tasktype = tasktype == null ? null : tasktype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.updateTable
     *
     * @return the value of messageinfo.updateTable
     *
     * @mbg.generated
     */
    public String getUpdatetable() {
        return updatetable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.updateTable
     *
     * @param updatetable the value for messageinfo.updateTable
     *
     * @mbg.generated
     */
    public void setUpdatetable(String updatetable) {
        this.updatetable = updatetable == null ? null : updatetable.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.updatetime
     *
     * @return the value of messageinfo.updatetime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.updatetime
     *
     * @param updatetime the value for messageinfo.updatetime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column messageinfo.taskNo
     *
     * @return the value of messageinfo.taskNo
     *
     * @mbg.generated
     */
    public String getTaskno() {
        return taskno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column messageinfo.taskNo
     *
     * @param taskno the value for messageinfo.taskNo
     *
     * @mbg.generated
     */
    public void setTaskno(String taskno) {
        this.taskno = taskno == null ? null : taskno.trim();
    }
}