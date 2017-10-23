package com.ecej.utils;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: Jco链接工具类
 * @author Jack
 * @date 2016年7月19日 下午4:56:31
 */
public class DestinationDataProviderUtil implements DestinationDataProvider
{
    private static final DestinationDataProviderUtil instance;
    private static Map<String, Properties> destinations;
    private static DestinationDataEventListener eventListener;
    private static Properties properties;
    
    public DestinationDataProviderUtil()
    {
        if(instance == null)
        {
            destinations = new HashMap<String, Properties>();
        }
    }
    
    static
    {
        instance = new DestinationDataProviderUtil();
        try
        {
            Environment.registerDestinationDataProvider(instance);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        properties = PropertiesUtil.getProperities();
        addDestination(ConstantUtils.CCS_DESTINATION);
        addDestination(ConstantUtils.CRM_DESTINATION);
        addDestination(ConstantUtils.CCS_DESTINATION_FQ);
    }
    
    private static void addDestination(String destinationName)
    {
        if(StringUtils.isEmpty(destinationName))
            return;
        if(properties == null)
        {
            synchronized(destinations)
            {
                destinations.remove(destinationName);
            }
            eventListener.deleted(destinationName);
        }
        else
        {
            Properties connectProperties = ConfigSetting(destinationName);
            synchronized(destinations)
            {
                destinations.put(destinationName, connectProperties);
            }
            eventListener.updated(destinationName);
        }
    }
    
    private static Properties ConfigSetting(String destination)
    {
        String mshost = "";
        String isgroup = "";
        String group = "";
        String sysnr = "";
        String client = "";
        String user = "";
        String passwd = "";
        String r3name = "";
        String asHost = "";
        switch(destination)
        {
            case ConstantUtils.CRM_DESTINATION:
                mshost = SourceUtils.getSimpleMessage("JCO_CRM_MSHOST");
                isgroup = SourceUtils.getSimpleMessage("JCO_CRM_ISGROUP");
                group = SourceUtils.getSimpleMessage("JCO_CRM_GROUP");
                sysnr = SourceUtils.getSimpleMessage("JCO_CRM_SYSNR");
                client = SourceUtils.getSimpleMessage("JCO_CRM_CLIENT");
                user = SourceUtils.getSimpleMessage("JCO_CRM_USER");
                passwd = SourceUtils.getSimpleMessage("JCO_CRM_PASSWD");
                r3name = SourceUtils.getSimpleMessage("JCO_CRM_R3NAME");
                asHost = SourceUtils.getSimpleMessage("JCO_CRM_ASHOST");
                break;
            case ConstantUtils.CCS_DESTINATION:
                mshost = SourceUtils.getSimpleMessage("JCO_MSHOST");
                isgroup = SourceUtils.getSimpleMessage("JCO_ISGROUP");
                group = SourceUtils.getSimpleMessage("JCO_GROUP");
                sysnr = SourceUtils.getSimpleMessage("JCO_SYSNR");
                client = SourceUtils.getSimpleMessage("JCO_CLIENT");
                user = SourceUtils.getSimpleMessage("JCO_USER");
                passwd = SourceUtils.getSimpleMessage("JCO_PASSWD");
                r3name = SourceUtils.getSimpleMessage("JCO_R3NAME");
                asHost = SourceUtils.getSimpleMessage("JCO_ASHOST");
                break;
            case ConstantUtils.CCS_DESTINATION_FQ:
                mshost = SourceUtils.getSimpleMessage("JCO_MSHOST_FQ");
                isgroup = SourceUtils.getSimpleMessage("JCO_ISGROUP_FQ");
                group = SourceUtils.getSimpleMessage("JCO_GROUP_FQ");
                sysnr = SourceUtils.getSimpleMessage("JCO_SYSNR_FQ");
                client = SourceUtils.getSimpleMessage("JCO_CLIENT_FQ");
                user = SourceUtils.getSimpleMessage("JCO_USER_FQ");
                passwd = SourceUtils.getSimpleMessage("JCO_PASSWD_FQ");
                r3name = SourceUtils.getSimpleMessage("JCO_R3NAME_FQ");
                asHost = SourceUtils.getSimpleMessage("JCO_ASHOST_FQ");
        }
       return ConfigSetting(mshost, isgroup, group, sysnr, client, user, passwd, r3name, asHost);
    }
    
    private static Properties ConfigSetting(String mshost, String isgroup, String group, String sysnr, String client, String user, String passwd, String r3name, String asHost)
    {
        Properties connectProperties = new Properties();
        if(isgroup.contains("1"))
        {
            connectProperties.setProperty(DestinationDataProviderUtil.JCO_MSHOST, mshost);
            connectProperties.setProperty(DestinationDataProviderUtil.JCO_GROUP, group);
            connectProperties.setProperty(DestinationDataProviderUtil.JCO_R3NAME, r3name);
        }
        else
        {
            connectProperties.setProperty(DestinationDataProviderUtil.JCO_ASHOST, asHost);
        }
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_SYSNR, sysnr);
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_CLIENT, client);
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_USER, user);
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_PASSWD, passwd);
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_LANG, "zh"); // 登录语言
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_POOL_CAPACITY, "90"); // 最大连接数
        connectProperties.setProperty(DestinationDataProviderUtil.JCO_PEAK_LIMIT, "100"); // 最大连接线程
        return connectProperties;
    }
    
    public static DestinationDataProviderUtil getInstance()
    {
        return instance;
    }
    
    /**
     * 
     * @Description: 得到JCoDestination
     * @author Jack
     * @date 2016年7月19日 下午6:19:16
     *
     * @param destinations
     * @return
     * @throws JCoException
     */
    public JCoDestination getDestination(String destinationName) throws JCoException
    {
        if(destinationName != null && destinationName.equals(ConstantUtils.CRM_DESTINATION))
            return JCoDestinationManager.getDestination(ConstantUtils.CRM_DESTINATION);
        if(destinationName != null && destinationName.equals(ConstantUtils.CCS_DESTINATION))
            return JCoDestinationManager.getDestination(ConstantUtils.CCS_DESTINATION);
        if(destinationName != null && destinationName.equals(ConstantUtils.CCS_DESTINATION_FQ))
            return JCoDestinationManager.getDestination(ConstantUtils.CCS_DESTINATION_FQ);
        return null;
    }

    @Override
    public Properties getDestinationProperties(String arg0)
    {
        if(destinations.containsKey(arg0))
        {
            return destinations.get(arg0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener arg0)
    {
        this.eventListener = arg0;
        
    }

    @Override
    public boolean supportsEvents()
    {
        return true;
    }
}