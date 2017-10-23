package com.ecej.mapper;

import com.ecej.po.ICHInstallInfo;
import com.ecej.po.ICHInstallInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ICHInstallInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    long countByExample(ICHInstallInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int deleteByExample(ICHInstallInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int insert(ICHInstallInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int insertSelective(ICHInstallInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    List<ICHInstallInfo> selectByExample(ICHInstallInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    ICHInstallInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ICHInstallInfo record, @Param("example") ICHInstallInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ICHInstallInfo record, @Param("example") ICHInstallInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ICHInstallInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ichinstallinfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ICHInstallInfo record);
}