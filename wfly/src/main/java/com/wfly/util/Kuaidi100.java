package com.wfly.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Kuaidi100 {
	private static String KEY;
	private static String CUSTOMER;

	private static String KUAIDI_AUTONUMBER_URL;
	private static String KUDIDI_QUERY_URL;

	static {
		Properties props = new Properties();
		String kuaidiConfig = "properties/Kuaidi100.properties";
		FileInputStream is=null;
		try {
			is = new FileInputStream(Kuaidi100.class.getResource ("/").getFile ()+kuaidiConfig);
			props.load(is);
			KEY = props.getProperty("key");
		} catch (IOException e) {
			System.err.println("读取配置文件失败:"+kuaidiConfig);
			e.printStackTrace();
		}finally {
			try {
				if(is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static {
		String configUri = "properties/Kuaidi100.properties";
		FileInputStream is = null;
		try {
			is = new FileInputStream(Kuaidi100.class.getResource("/").getFile()+configUri);
			Properties props = new Properties();
			props.load(is);
			
			KEY = props.getProperty("key");
			CUSTOMER = props.getProperty("customer");
			KUAIDI_AUTONUMBER_URL = props.getProperty("kuaidi_autonumber_url");
			KUDIDI_QUERY_URL = props.getProperty("kudidi_query_url");
			
		} catch (IOException e) {
			System.err.println("读取配置文件失败:"+configUri);
			e.printStackTrace();
		} finally {
			if(is!= null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * XXX:快递公司的编号(com)一定要传,不清楚则通过Autonumber()查询
	 * XXX:from 和 to 最好不要传 传了反而会错!
	 * TODO:先不用http线程池,后续量大再修改
	 * 快递查询
	 * @param num 查询单号
	 * @param com 快递的所在邮政公司编号
	 * @param from 出发地 (可不传)
	 * @param to 到达地 (可不传)
	 * @return
	 */
	public static JSONObject Query(String num,String com,String from, String to) {
		String param ="{\"com\":\""+com+"\",\"num\":\""+num+"\",\"from\":\""+from+"\",\"to\":\""+to+"\"}";
		String customer =CUSTOMER;
		String key = KEY;
		
//		String sign = MD5.encode(param+key+customer);
		String sign = new SimpleHash("MD5", param+key+customer).toString().toUpperCase();//调用shiro的MD5加密
//		HashMap params = new HashMap();
//		params.put("param",param);
//		params.put("sign",sign);
//		params.put("customer",customer);
		JSONObject reJson = null;
		try {
			HttpPost httpPost = new HttpPost(KUDIDI_QUERY_URL);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
			nvps.add(new BasicNameValuePair("param", param));  
	        nvps.add(new BasicNameValuePair("customer", customer)); 
	        nvps.add(new BasicNameValuePair("sign", sign)); 
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps));  
			
			reJson = JSONObject.fromObject(client(httpPost));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reJson;
	}
	
	/**
	 * XXX:快递公司的编号(com)一定要传,不清楚则通过Autonumber()查询
	 * 快递查询
	 * @param num 查询单号
	 * @param com 快递的所在邮政公司编号
	 * @return
	 */
	public static JSONObject Query(String num,String com) {
		return Query(num, com, "", "");
	}
	
	/**
	 * XXX:快递100提供的API并不保证返回的快递公司编码是正确的
	 * TODO:先不用http线程池,后续量大再修改
	 * 
	 * 根据快递单号查询快递公司->获取第一个返回
	 * @param num
	 * @return 快递公司编号
	 * 
	 */
	public static String AutonumberFirst(String num) {
		HttpGet httpGet = new HttpGet(KUAIDI_AUTONUMBER_URL+"?num="+num+"&key="+KEY);

		JSONArray reJson = JSONArray.fromObject(client(httpGet));
		
		return reJson.getJSONObject(0).getString("comCode");
	}
	
	/**
	 * XXX:快递100提供的API并不保证返回的快递公司编码是正确的
	 * TODO:先不用http线程池,后续量大再修改
	 * 
	 * 根据快递单号查询快递公司->获取全部返回
	 * @param num
	 * @return 快递公司编号
	 * 
	 */
	public static List<String> AutonumberAll(String num) {
		HttpGet httpGet = new HttpGet(KUAIDI_AUTONUMBER_URL+"?num="+num+"&key="+KEY);

		JSONArray reJson = JSONArray.fromObject(client(httpGet));
		
		List<String> re = new ArrayList<String>();
		for (int i = 0; i < reJson.size(); i++) {
			re.add(reJson.getJSONObject(i).getString("comCode"));
		}
		return re;
	}
	

	private static String client(HttpRequestBase httpGet) {
		CloseableHttpClient createDefault = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		String reString=null;
		try {
			httpResponse = createDefault.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode==200){
				HttpEntity entity = httpResponse.getEntity();
				reString = EntityUtils.toString(entity,"utf-8");
				EntityUtils.consume(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
			close(httpResponse);
		}
		return reString;
	}

	/**
	 * 关闭返回
	 * @param response
	 */
	private static void close(CloseableHttpResponse response ){
		try {
			if (response != null) {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
