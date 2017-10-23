package com.ecej.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {
	// 配置文件名称
	private static String propertyFileName = "remote-sap.properties";

	/**
	 * @Title: getProperitiesByKey @Description:
	 *         TODO(读取配置文件setting.properties,获取key对应的value) @param @param
	 *         key @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String getProperitiesByKey(String key) {
		try {
			Resource resource = new ClassPathResource(propertyFileName);
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return props.get(key).toString();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {

		}
		return "";
	}

	/**
	 * @Title: getProperities @Description:
	 *         TODO(读取配置文件setting.properties) @param @param key @param @return
	 *         设定文件 @return String 返回类型 @throws
	 */
	public static Properties getProperities() {
		try {
			Resource resource = new ClassPathResource(propertyFileName);
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return props;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {

		}
		return null;
	}

	/**
	 * @Title: getProperitiesByKey @Description:
	 *         TODO(返回value,如果查询失败，则返回默认值) @param @param key @param @param
	 *         defaultValue 默认值 @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String getProperitiesByKey(String key, String defaultValue) {
		try {
			Resource resource = new ClassPathResource(propertyFileName);
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			Object val = props.get(key);
			if (val == null)
				return defaultValue;
			else
				return val.toString();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {

		}
		return "";
	}

}
