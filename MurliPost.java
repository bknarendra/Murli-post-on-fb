import java.util.*;
import java.io.*;
import com.restfb.*;
import java.net.*;
import com.restfb.json.*;
import com.restfb.types.*;
public class MurliPost
{
	public static void main(String args[])throws Exception{
		String temp1="",temp = "";
		URL u=new URL("http://bkwsu.org/thoughtText?lang=en");
		HttpURLConnection uc=(HttpURLConnection) u.openConnection();
		uc.setDoOutput(true);
		uc.setDoInput(true);
		uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.29 (KHTML, like Gecko) Chrome/12.0.733.0 Safari/534.29");
		uc.setRequestProperty("Connection","keep-alive");
		uc.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml");
		uc.setRequestProperty("Accept-Language","en-US,en;q=0.8");
		uc.setRequestProperty("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		uc.setRequestMethod("GET");
		uc.setInstanceFollowRedirects(false);
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ((temp=br.readLine())!=null){temp1+="\n"+temp;}
		String murli=temp1.substring(temp1.indexOf("'text':'")+8,temp1.indexOf("',\n'topic':"));
		murli=murli.replace("\\r\\n\\r\\n","\n\n");
		murli=murli.replace("\\r\\n","\n\n");
		murli=murli.replaceAll("[ \t]+", " ");
		Date date=new Date();
		murli=String.valueOf(date.getDate())+"/"+String.valueOf(date.getMonth()+1)+"/"+String.valueOf(1990+date.getYear())+"\n\n"+murli;
		System.out.println(murli);
		DefaultFacebookClient facebookClient=new DefaultFacebookClient("AAAFmZCvEqZBiABACqR2zFyYj3s8LEUkkaGLs9LPWmgoa3gx09DzzLIIenPljWBiZBDiaWS9zGirNdg5JZBJhxB5ahGgcTFooZCnI95LS7CwZDZD");
		JsonObject j=facebookClient.fetchObject("me/accounts",JsonObject.class);
		JsonArray acc=j.getJsonArray("data");
		for(int i=0;i<acc.length();i++) 
			if(acc.getJsonObject(i).getString("name").equals("Brahma Kumaris")) {
				DefaultFacebookClient facebookClient1=new DefaultFacebookClient(acc.getJsonObject(i).getString("access_token"));
				String posturl=acc.getJsonObject(i).getString("id")+"/feed";
				FacebookType publishMessageResponse=facebookClient1.publish(posturl,FacebookType.class,Parameter.with("message",murli));
				System.out.println("success");
				break;
			}
	}
}