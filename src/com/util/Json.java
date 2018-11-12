package com.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ���л��뷴���л�
 * @author ��˳
 *
 */
public class Json {

	/*
	 * ʱ�䣺���л���ת���ɺ���
	 * ���ⷽʽ
	 * һ����ʱ���ֶ�ע��
	 * @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 * �����ڽ��� ���л� �� ������ʱ д����ͬʱ���ʽ
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd HH:mm:ss");
	 * mapper.setDateFormat(sdf);
	 */
	
	
	/**
	 * ���л�
	 */
	public static String serialize(Object obj) {
		//����ӳ����
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
	 * �����л�
	 */
	public static <T>T deserialize(String json, Class<T> valueType) {
		T result = null;
		ObjectMapper mapper = new ObjectMapper(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd HH:mm:ss");
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
