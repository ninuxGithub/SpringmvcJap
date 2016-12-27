package com.hundsun.scheduleimpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hundsun.schedule.SpringSchedule;

@Component
public class SpringScheduleImpl implements SpringSchedule {

	// 0 0/3 * * * ? ：每三分钟触发一次
	// "0 0 12 * * ?" 每天中午十二点触发
	// "0 15 10 ? * *" 每天早上10：15触发
	// "0 15 10 * * ?" 每天早上10：15触发
	// "0 15 10 * * ? *" 每天早上10：15触发
	// "0 15 10 * * ? 2005" 2005年的每天早上10：15触发
	// "0 * 14 * * ?" 每天从下午2点开始到2点59分每分钟一次触发
	// "0 0/5 14 * * ?" 每天从下午2点开始到2：55分结束每5分钟一次触发
	// "0 0/5 14,18 * * ?" 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
	// "0 0-5 14 * * ?" 每天14:00至14:05每分钟一次触发
	// "0 10,44 14 ? 3 WED" 三月的每周三的14：10和14：44触发
	// "0 15 10 ? * MON-FRI" 每个周一、周二、周三、周四、周五的10：15触发

	// "0/20 * * * * ?" : 每20秒执行一次
	
	
	@Scheduled(cron = "0/20 * * * * ?")
	@Override
	public void exeJob() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		System.out.println("enter my schedule----"+ sdf.format(d));
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://www.baidu.com/");
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if(null != entity){
				System.out.println("+++++++++++++++++GET+++++++++++++++++++++++");
				System.out.println("statusLine : "+response.getStatusLine());
			//	System.out.println("content"+ EntityUtils.toString(entity));
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// need a form 
		
		HttpPost post = new HttpPost("http://www.baidu.com/s?");

		List<BasicNameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("wd", "java"));
		UrlEncodedFormEntity uefe = null;
		CloseableHttpResponse rp = null;
		try {
			uefe = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefe);
			rp = client.execute(post);
			HttpEntity postEntity = rp.getEntity();
			if (null != postEntity) {
				System.out.println("+++++++++++++++++POST+++++++++++++++++++++++");
				System.out.println("status line is :" + rp.getStatusLine());
				System.out.println("content :" + EntityUtils.toString(postEntity));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("error hanpend");
		}
	}
	
	 /**
     * 通过GET方式发起http请求
     */
    public void requestByGetMethod(){
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //用get方法发送http请求
            HttpGet get = new HttpGet("http://www.baidu.com");
            System.out.println("执行get请求:...."+get.getURI());
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("响应状态码:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");                    
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
 
    }
     
     
    /**
     * POST方式发起http请求
     */
    public void requestByPostMethod(){
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost("http://localhost/....");          //这里用上本机的某个工程做测试
            //创建参数列表
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("j_username", "admin"));
            list.add(new BasicNameValuePair("j_password", "admin"));
            //url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(uefEntity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }
             
        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
         
    }
     
    private CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
     
    private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

}
