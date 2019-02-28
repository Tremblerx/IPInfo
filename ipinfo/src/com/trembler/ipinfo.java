package com.trembler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ip",urlPatterns = {"/ipinfo"})
public class ipinfo extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String ip = request.getRemoteAddr();
		String path = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
		URLConnection url = new URL(path).openConnection();
		url.connect();
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.getInputStream(), StandardCharsets.UTF_8));
		String line = null;
		while ((line = reader.readLine())!=null){
			stringBuilder.append(line);
		}

		JSONObject jsonInfo = JSON.parseObject(stringBuilder.toString());

		System.out.println(stringBuilder.toString());

		if(jsonInfo.getString("code").equals("0")){
			String data = jsonInfo.getString("data");
			JSONObject ipInfo = JSON.parseObject(data);

			pw.println("<table align=center>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>ip：");
			pw.println("<td align=center/>"+ipInfo.getString("ip"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>国家：");
			pw.println("<td align=center/>"+ipInfo.getString("country"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>国家id：");
			pw.println("<td align=center/>"+ipInfo.getString("country_id"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>地区：");
			pw.println("<td align=center/>"+ipInfo.getString("area"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>地区id：");
			pw.println("<td align=center/>"+ipInfo.getString("area_id"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>省份：");
			pw.println("<td align=center/>"+ipInfo.getString("region"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>省份id：");
			pw.println("<td align=center/>"+ipInfo.getString("region_id"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>城市：");
			pw.println("<td align=center/>"+ipInfo.getString("city"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>城市id：");
			pw.println("<td align=center/>"+ipInfo.getString("city_id"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>县区：");
			pw.println("<td align=center/>"+ipInfo.getString("county"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>县区id：");
			pw.println("<td align=center/>"+ipInfo.getString("county_id"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>运营商：");
			pw.println("<td align=center/>"+ipInfo.getString("isp"));
			pw.println("<tr/>");
			pw.println("<tr align=center>");
			pw.println("<td align=right/>运营商id：");
			pw.println("<td align=center/>"+ipInfo.getString("isp_id"));
			pw.println("<tr/>");

//			System.out.println(ipInfo.getString("ip"));
//			System.out.println(ipInfo.getString("country"));    // 中国
//			System.out.println(ipInfo.getString("country_id")); // CN
//			System.out.println(ipInfo.getString("area"));       // 华北
//			System.out.println(ipInfo.getString("area_id"));    // 100000
//			System.out.println(ipInfo.getString("region"));     // 北京市
//			System.out.println(ipInfo.getString("region_id"));  // 110000
//			System.out.println(ipInfo.getString("city"));       // 北京市
//			System.out.println(ipInfo.getString("city_id"));    // 110000
//			System.out.println(ipInfo.getString("county"));     // 朝阳区
//			System.out.println(ipInfo.getString("county_id"));  // 110105
//			System.out.println(ipInfo.getString("isp"));        // 联通
//			System.out.println(ipInfo.getString("isp_id"));     // 100026
		}



	}



}
