package com.mobtexting.sms;
import java.io.*;
import java.net.*;

public class Client{
	public String ACCESS_TOKEN;

	public Client(String ACCESS_TOKEN){
		this.ACCESS_TOKEN = ACCESS_TOKEN;
	}

	public String send(String to, String from, String body, char service) {
		String apiEndPoint = "https://portal.mobtexting.com/api/v2/";
		String urlToRead = apiEndPoint + "sms/send?access_token=" + this.ACCESS_TOKEN + "&message="
				    + body  + "&sender="+ from + "&to=" + to + "&service=" + service;

		StringBuilder result = new StringBuilder();

		URL url = null;

		try{
			url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		}catch(Exception e){
			System.out.println(e);
		}

		return result.toString();
	}
}
