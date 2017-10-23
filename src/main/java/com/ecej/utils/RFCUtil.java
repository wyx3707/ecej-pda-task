package com.ecej.utils;

import com.sap.conn.jco.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: RFC调用工具类
 * @author Jack
 * @date 2016年7月16日 下午4:11:47
 *       
 */
public class RFCUtil
{
    private static Logger logger = LoggerFactory.getLogger(RFCUtil.class);
    
    /**
     * 
     * @Description: 得到JCoDestination
     * @author Jack
     * @date 2016年7月20日 下午3:01:34
     *       
     * @param destinationName
     * @return
     * @throws JCoException
     */
    private static JCoDestination getJCoDestination(String... destinationName) throws JCoException
    {
        if(destinationName.length == 0)
            return DestinationDataProviderUtil.getInstance().getDestination("CT1");
        else
            return DestinationDataProviderUtil.getInstance().getDestination(destinationName[0]);
    }
    
    /**
     * 
     * @Description: 调用RFC function 方式传参
     * @author Jack
     * @date 2016年7月19日 下午7:41:12
     *       
     * @param map
     * @param functionName
     * @return
     */
    public static JCoFunction SendByFunction(Map<String, Object> map, String functionName, String... destinationName)
    {
        JCoFunction function = null;
        try
        {
            JCoDestination destination = getJCoDestination(destinationName);
            function = destination.getRepository().getFunction(functionName);
            for(String key : map.keySet())
            {
                function.getImportParameterList().setValue(key, map.get(key));
            }
            function.execute(destination);
        }
        catch(Exception e)
        {
            logger.error("error", e);
        }
        return function;
    }
    
    /**
     * 调用RFC函数 table 方式传参
     * 
     * @param map
     * @param tableName
     * @param functionName
     * @return
     * @throws JCoException
     */
    public static JCoFunction SendByTable(Map<String, Object> map, String tableName, String functionName, String... destinationName) throws JCoException
    {
        JCoFunction function = null;
        try
        {
            JCoDestination destination = getJCoDestination(destinationName);
            function = destination.getRepository().getFunction(functionName);
            JCoTable jcTable = function.getTableParameterList().getTable(tableName);
            jcTable.appendRow();
            for(String key : map.keySet())
            {
                jcTable.setValue(key, map.get(key));
            }
            function.execute(destination);
        }
        catch(JCoException e)
        {
            logger.error("error", e);
        }
        return function;
    }
    
    /**
     * 调用RFC函数 table 方式传参
     * 
     * @param map
     * @param tableName
     * @param functionName
     * @return
     * @throws JCoException
     */
    public static JCoFunction SendByTable(List<Map<String, Object>> list, String tableName, String functionName, String... destinationName) throws JCoException
    {
        JCoFunction function = null;
        JCoDestination destination = getJCoDestination(destinationName);
        function = destination.getRepository().getFunction(functionName);
        JCoTable jcTable = function.getTableParameterList().getTable(tableName);
        jcTable.appendRows(list.size());
        for(int i = 0; i < list.size(); i++)
        {
            jcTable.setRow(i);
            Map<String, Object> m = list.get(i);
            for(String key : m.keySet())
            {
                jcTable.setValue(key, m.get(key));
            }
        }
        function.execute(destination);
        return function;
    }
    
    /**
     * 调用RFC 函数 Structure 方式传参
     * 
     * @param map
     * @param structure
     * @param functionName
     * @return
     * @throws JCoException
     */
    public static JCoFunction SendByStructure(Map<String, Object> map, String structure, String functionName, String... destinationName) throws JCoException
    {
        JCoFunction function = null;
        try
        {
            JCoDestination destination = getJCoDestination(destinationName);
            function = destination.getRepository().getFunction(functionName);
            JCoStructure jcStructure = function.getImportParameterList().getStructure(structure);
            for(String key : map.keySet())
            {
                jcStructure.setValue(key, map.get(key));
            }
            function.execute(destination);
        }
        catch(JCoException e)
        {
            logger.error("error", e);
        }
        return function;
    }
    
    /**
     * 得到函数
     * 
     * @param functionName
     * @return
     * @throws JCoException
     */
    public static JCoFunction getJCoFunction(String functionName, String... destinationName) throws JCoException
    {
        JCoFunction function = null;
        try
        {
            JCoDestination destination = getJCoDestination(destinationName);
            function = destination.getRepository().getFunction(functionName);
        }
        catch(JCoException e)
        {
            logger.error("error", e);
        }
        return function;
    }
    
    /**
     * 
     * @Description: 执行函数
     * @author Jack
     * @date 2016年7月20日 下午3:29:20
     *       
     * @param destinationName
     */
    public static JCoFunction execute(JCoFunction function, String... destinationName)
    {
        JCoDestination destination;
        try
        {
            destination = getJCoDestination(destinationName);
            function.execute(destination);
        }
        catch(JCoException e)
        {
            logger.error("error", e);
        }
        return function;
    }
    
    /**
     * 
     * @Description: 批量执行sap 函数， 适用于 既有Table 和 其他参数调用
     * @author clg
     * @date 2016年7月5日 下午4:17:54
     *       
     * @param listTable
     *            table内部参数
     * @param importParameterMap
     *            table外参数
     * @param tableName
     * @param functionName
     * @return
     * @throws JCoException
     */
    public static JCoFunction SendByTableList(List<Map<String, Object>> listTable, Map<String, Object> importParameterMap, String tableName, String functionName, String... destinationName) throws JCoException
    {
        JCoFunction function = null;
        try
        {
            JCoDestination destination = getJCoDestination(destinationName);
            function = destination.getRepository().getFunction(functionName);
            JCoTable jcTable = function.getTableParameterList().getTable(tableName);
            jcTable.appendRows(listTable.size());
            // 赋值Table 参数
            for(int i = 0; i < listTable.size(); i++)
            {
                jcTable.setRow(i);
                Map<String, Object> m = listTable.get(i);
                for(String key : m.keySet())
                {
                    jcTable.setValue(key, m.get(key));
                }
            }
            // 赋值Table 以外的参数
            for(String key : importParameterMap.keySet())
            {
                function.getImportParameterList().setValue(key, importParameterMap.get(key));
            }
            function.execute(destination);
        }
        catch(JCoException e)
        {
            logger.error("error", e);
        }
        return function;
    }
    
    //
    // /**
    // * 调用RFC 函数 Structure 方式传参
    // *
    // * @param map
    // * @param structure
    // * @param functionName
    // * @return
    // * @throws JCoException
    // */
    // public static JCoFunction SendByStructure(JCoDestination destination, Map<String, Object> map, String structure, String functionName) throws JCoException
    // {
    // JCoFunction function = null;
    // try
    // {
    // function = destination.getRepository().getFunction(functionName);
    // JCoStructure jcStructure = function.getImportParameterList().getStructure(structure);
    // for(String key : map.keySet())
    // {
    // jcStructure.setValue(key, map.get(key));
    // }
    // function.execute(destination);
    // }
    // catch(JCoException e)
    // {
    // logger.error("error", e);
    // }
    // return function;
    // }
    //
    // /**
    // * 调用RFC函数 table 方式传参
    // *
    // * @param map
    // * @param tableName
    // * @param functionName
    // * @return
    // * @throws JCoException
    // */
    // public static JCoFunction SendByTable(JCoDestination destination, Map<String, Object> map, String tableName, String functionName) throws JCoException
    // {
    // JCoFunction function = null;
    // try
    // {
    // function = destination.getRepository().getFunction(functionName);
    // JCoTable jcTable = function.getTableParameterList().getTable(tableName);
    // jcTable.appendRow();
    // for(String key : map.keySet())
    // {
    // jcTable.setValue(key, map.get(key));
    // }
    // function.execute(destination);
    // }
    // catch(JCoException e)
    // {
    // logger.error("error", e);
    // }
    // return function;
    // }
    //
    // /**
    // * 调用RFC函数 table 方式传参
    // *
    // * @param map
    // * @param tableName
    // * @param functionName
    // * @return
    // * @throws JCoException
    // */
    // public static JCoFunction SendByTable(JCoDestination destination, List<Map<String, Object>> list, String tableName, String functionName) throws JCoException
    // {
    // JCoFunction function = null;
    // try
    // {
    // function = destination.getRepository().getFunction(functionName);
    // JCoTable jcTable = function.getTableParameterList().getTable(tableName);
    // jcTable.appendRows(list.size());
    // for(int i = 0; i < list.size(); i++)
    // {
    // jcTable.setRow(i);
    // Map<String, Object> m = list.get(i);
    // for(String key : m.keySet())
    // {
    // jcTable.setValue(key, m.get(key));
    // }
    // }
    // function.execute(destination);
    // }
    // catch(JCoException e)
    // {
    // logger.error("error", e);
    // }
    // return function;
    // }
    //
    // /**
    // * 得到函数
    // *
    // * @param functionName
    // * @return
    // * @throws JCoException
    // */
    // public static JCoFunction getJCoFunction(JCoDestination destination, String functionName) throws JCoException
    // {
    // JCoFunction function = null;
    // try
    // {
    // function = destination.getRepository().getFunction(functionName);
    // }
    // catch(JCoException e)
    // {
    // logger.error("error", e);
    // }
    // return function;
    // }
    //
    //
    //
    // /**
    // *
    // * @Description: 批量执行sap 函数， 适用于 既有Table 和 其他参数调用
    // * @author clg
    // * @date 2016年7月5日 下午4:17:54
    // *
    // * @param listTable
    // * table内部参数
    // * @param importParameterMap
    // * table外参数
    // * @param tableName
    // * @param functionName
    // * @return
    // * @throws JCoException
    // */
    // public static JCoFunction SendByTableList(JCoDestination destination, List<Map<String, Object>> listTable, Map<String, Object> importParameterMap, String tableName, String functionName) throws JCoException
    // {
    // JCoFunction function = null;
    // try
    // {
    // function = destination.getRepository().getFunction(functionName);
    // JCoTable jcTable = function.getTableParameterList().getTable(tableName);
    // jcTable.appendRows(listTable.size());
    // // 赋值Table 参数
    // for(int i = 0; i < listTable.size(); i++)
    // {
    // jcTable.setRow(i);
    // Map<String, Object> m = listTable.get(i);
    // for(String key : m.keySet())
    // {
    // jcTable.setValue(key, m.get(key));
    // }
    // }
    // // 赋值Table 以外的参数
    // for(String key : importParameterMap.keySet())
    // {
    // function.getImportParameterList().setValue(key, importParameterMap.get(key));
    // }
    // function.execute(destination);
    // }
    // catch(JCoException e)
    // {
    // logger.error("error", e);
    // }
    // return function;
    // }
}
