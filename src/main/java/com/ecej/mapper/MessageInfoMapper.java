package com.ecej.mapper;

import com.ecej.po.MessageInfo;
import com.ecej.po.MessageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    long countByExample(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int deleteByExample(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer msgcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int insert(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int insertSelective(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    List<MessageInfo> selectByExampleWithBLOBs(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    List<MessageInfo> selectByExample(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    MessageInfo selectByPrimaryKey(Integer msgcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageinfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MessageInfo record);
}