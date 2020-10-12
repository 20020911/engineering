package com.example.utils;


import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressUtil {
    private static Logger log = LoggerFactory.getLogger(AddressUtil.class);
    public static String getCityInfo(String ip) {
        DbSearcher searcher = null;
        try {
            //ip2region.db是离线地址定位库，可以自行在网上下载
            //本文中将**ip2region.db**可放到resources/ip2region 目录下
            String dbPath = AddressUtil.class.getResource("/ip2region/ip2region.db").getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
                dbPath = tmpDir + File.separator + "ip.db";
                file = new File(dbPath);
                InputStream resourceAsStream = AddressUtil.class.getClassLoader().getResourceAsStream("classpath:ip2region/ip2region.db");
                if (resourceAsStream != null) {
                    FileUtils.copyInputStreamToFile(resourceAsStream, file);
                }
            }
            DbConfig config = new DbConfig();
            searcher = new DbSearcher(config, file.getPath());
            Method method = searcher.getClass().getMethod("btreeSearch", String.class);
            if (!Util.isIpAddress(ip)) {
                log.error("Error: Invalid ip address");
            }
            DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
            return dataBlock.getRegion();
        } catch (Exception e) {
            log.error("获取地址信息异常，{}", e.getMessage());
            return StringUtils.EMPTY;
        } finally {
            if (searcher != null) {
                try {
                    searcher.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getV4IP() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com/";

        String inputLine = "";
        String read = "";
        try {
            URL url = new URL(chinaz);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((read = in.readLine()) != null) {
                inputLine += read;
            }
            System.out.println(inputLine);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile("\\<strong class\\=\"red\">(.*?)\\<\\/strong>");
        Matcher m = p.matcher(inputLine);
        if(m.find()){
            String ipstr = m.group(1);
            System.out.println(ipstr);
        }
        return ip;
    }
    public static String getLocalIpAddr() {

        String clientIP = null;
        Enumeration<NetworkInterface> networks = null;
        try {
            //获取所有网卡设备
            networks = NetworkInterface.getNetworkInterfaces();
            if (networks == null) {
                //没有网卡设备 打印日志  返回null结束
                System.out.println("networks  is null");
                return null;
            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
        InetAddress ip;
        Enumeration<InetAddress> addrs;
        // 遍历网卡设备
        while (networks.hasMoreElements()) {
            NetworkInterface ni = networks.nextElement();
            try {
                //过滤掉 loopback设备、虚拟网卡
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
                    continue;
                }
            } catch (SocketException e) {
               e.getMessage();
            }
            addrs = ni.getInetAddresses();
            if (addrs == null) {
                System.out.println("InetAddress is null");
                continue;
            }
            // 遍历InetAddress信息
            while (addrs.hasMoreElements()) {
                ip = addrs.nextElement();
                if (!ip.isLoopbackAddress() && ip.isSiteLocalAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    try {
                        clientIP = ip.toString().split("/")[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        clientIP = null;
                    }
                }
            }
        }
        return clientIP;
    }
    public static void main(String[] args) {
        try {
            String ip = AddressUtil.getLocalIpAddr();
            System.out.println(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(getCityInfo("192.168.40.1"));
    }
}
