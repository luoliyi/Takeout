package text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JSON {
	//将字符串序列化成JSON
	public static String JOSON(Object obj) {
		ObjectMapper mar=new ObjectMapper();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
		mar.setDateFormat(sm);
		String fn=null;
		try {
			fn=mar.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return fn;
	}
	public static <T> T TOK(String joson,Class<T> value) {
		ObjectMapper mar=new ObjectMapper();
		T fn=null;
		try {
			fn=mar.readValue(joson,value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fn;
	}
}
