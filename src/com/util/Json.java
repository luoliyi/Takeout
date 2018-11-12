package com.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 序列化与反序列化
 * @author 大顺
 *
 */
public class Json {

	/*
	 * 时间：序列化会转换成毫秒
	 * 决解方式
	 * 一、在时间字段注释
	 * @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 * 二、在进行 序列化 与 反序列时 写入相同时间格式
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
	 * mapper.setDateFormat(sdf);
	 */
	
	
	/**
	 * 序列化
	 */
	public static String serialize(Object obj) {
		//对象映射器
		ObjectMapper mapper = new ObjectMapper(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		String result = null;
		try {
			result =  mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 反序列化
	 */
	public static <T>T deserialize(String json, Class<T> valueType) {
		T result = null;
		ObjectMapper mapper = new ObjectMapper(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		try {
			result = mapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
