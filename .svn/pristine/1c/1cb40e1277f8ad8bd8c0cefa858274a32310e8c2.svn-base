package com.thinkgem.jeesite.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 话单文本解析工具类
 * @author zhuguangrui
 *
 */
public class CdrDecoder {
	
	
	
	/**
	 * 读取前面空格个数
	 * @param str
	 * @return
	 */
	public static int getPreSpaceLength(String str){
		int count=0;
		int len = str.length();
		for(int i=0;i<len;i++){
			if(str.charAt(i)!=' '){
				return i;
			}else{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 读取节点的层级
	 * @param str
	 * @return
	 */
	public static int getNodeLevel(String str){
		int spaceCount = getPreSpaceLength(str);
		return spaceCount%2==1?spaceCount/2 + 1:spaceCount/2;
	}
	
	/**
	 * 读取节点的value
	 * @param str
	 * @return
	 */
	public static String getNodeValue(String line) {
		String value = line.trim().substring(getNodeKey(line).length()).trim();
		return value.split("'")[0];
	}
	
	/**
	 * 读取节点带进制的value
	 * @param str
	 * @return
	 */
	public static String getNodeFullValue(String line) {
		return line.trim().substring(getNodeKey(line).length()).trim();
	}
	
	/**
	 * 读取节点的key
	 * @param str
	 * @return
	 */
	public static String getNodeKey(String str) {
		String trimStr = str.trim();
		int len = trimStr.length();
		for(int i=0;i<len;i++){
			if(trimStr.charAt(i)==' '){
				return trimStr.substring(0, i);
			}
		}
		return trimStr;
	}

	/**
	 * 翻译servingNodeType的值
	 * @param value
	 * @return
	 */
	public static String getServingNodeType(String value) {
		switch (value) {
		case "00":
			return "sGSN";
		case "01":
			return "pMIPSGW";
		case "02":
			return "gTPSGW";
		case "03":
			return "ePDG";
		case "04":
			return "hSGW";
		case "05":
			return "mME";
		case "06":
			return "tWAN";
		}
		return value;
	}
	
	/**
	 * 日期由数据格式转换为可识别格式 <i>yyyy-MM-dd hh:mm:ss</i>
	 * @param current
	 * @return
	 */
	public static String datetimeTransform(String current) {
		if(current.length()>=12){
			current = current.substring(0,12);
		}else{
			return current;
		}
		Date date = null;
		try {
			date = new SimpleDateFormat("yyMMddHHmmss").parse(current);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * 翻译16进制IP地址
	 * @param value
	 * @return
	 */
	public static String change16to10(String value){
		if(StringUtils.isNotBlank(value) && value.length()>=8){
			String s1 = value.substring(0, 2);
			String s2 = value.substring(2, 4);
			String s3 = value.substring(4, 6);
			String s4 = value.substring(6, 8);
			String ipStr = Integer.parseInt(s1, 16)+"."+Integer.parseInt(s2, 16)+"."+Integer.parseInt(s3, 16)+"."+Integer.parseInt(s4, 16);
			return ipStr;
		}
		return value;
	}
	
	/**
	 * 翻译ratType
	 * @param value
	 * @return
	 */
	public static String getRatType(String value){
		switch (value) {
		case "1":
			return "UTRAN";
		case "2":
			return "GERAN";
		case "3":
			return "WLAN";
		case "4":
			return "GAN";
		case "5":
			return "HSPA Evolution";
		case "6":
			return "EUTRAN";
		case "102":
			return "3GPP2 eHRPD";
		case "103":
			return "3GPP2 HRPD";
		}
		return value;
	}
	
	
	
	
	/**
	 * 翻译causeForRecClosing的值
	 * @param value
	 * @return
	 */
	public static String getCauseForRecClosing(String value) {
		switch (value) {
		case "0":
			return "normalRelease";
		case "4":
			return "abnormalRelease";
		case "16":
			return "volumeLimit";
		case "17":
			return "timeLimit";
		case "18":
			return "servingNodeChange";
		case "19":
			return "maxChangeCond";
		case "22":
			return "rATChange";
		case "23":
			return "mSTimeZoneChange";
		case "24":
			return "sGSNPLMNIDChange";
		case "100":
			return "managementInitRelease";
		case "102":
			return "creditControlChange";
		case "104":
			return "creditControlInitRelease";
		case "105":
			return "policyControlInitRelease";
		}
		return value;
	}
	
	/**
	 * 翻译chChSelectionMode的值
	 * @param value
	 * @return
	 */
	public static String getChChSelectionMode(String value) {
		switch (value) {
		case "0":
			return "sGSNSupplied";
		case "3":
			return "homeDefault";
		case "100":
			return "radiusSupplied";
		case "101":
			return "roamingClassBased";
		}
		return value;
	}
	
	/**
	 * 翻译pdnPDNType的值
	 * @param value
	 * @return
	 */
	public static String getPdnPDNType(String value) {
		switch (value) {
		case "0121":
			return "PDP Context IPv4";
		case "0157":
			return "PDP Context IPv6";
		case "01":
			return "EPS Bearer IPv4";
		case "02":
			return "EPS Bearer IPv6";
		case "03":
			return "EPS Bearer IPv4v6";
		}
		return value;
	}
	
	/**
	 * 翻译apnSelectionMode的值
	 * @param value
	 * @return
	 */
	public static String getApnSelectionMode(String value) {
		switch (value) {
		case "0":
			return "mSorNetworkProvidedSubscriptionVerified";
		case "1":
			return "mSProvidedSubscriptionNotVerified";
		case "2":
			return "networkProvidedSubscriptionNotVerified";
		}
		return value;
	}
	
	/**
	 * 翻译serviceConditionChange的值
	 * @param value
	 * @return
	 */
	public static String getServiceConditionChange(String value) {
		
		String binValue = hexString2binaryString(value.substring(2));
		String[] scValues = new String[32];
		scValues[0]="qoSChange";
		scValues[1]="sGSNChange";
		scValues[2]="sGSNPLMNIDChange";
		scValues[3]="tariffTimeSwitch";
		scValues[4]="pDPContextRelease";
		scValues[5]="rATChange";
		scValues[6]="serviceIdledOut";
		scValues[9]="serviceStop";
		scValues[10]="dCCATimeThresholdReached";
		scValues[11]="dCCAVolumeThresholdReached";
		scValues[12]="dCCAServiceSpecificUnitThresholdReached";
		scValues[13]="dCCATimeExhausted";
		scValues[14]="dCCAVolumeExhausted";
		scValues[15]="dCCAValidityTimeout";
		scValues[17]="dCCAReauthorisationRequest";
		scValues[18]="dCCAContinueOngoingSession";
		scValues[19]="dCCARetryAndTerminateOngoingSession";
		scValues[20]="dCCATerminateOngoingSession";
		scValues[21]="cGI-SAIChange";
		scValues[22]="rAIChange";
		scValues[23]="dCCAServiceSpecificUnitExhausted";
		scValues[24]="recordClosure";
		scValues[29]="eCGIChange";
		scValues[30]="tAIChange";
		StringBuffer scValue = new StringBuffer();
		for(int i=0;i<binValue.length();i++){
			if(binValue.charAt(i) == '1'){
				scValue.append(scValues[i]);
				scValue.append(",");
			}
		}
		return scValue.substring(0,scValue.length()-1);
	}
	
	/**
	 * 转换为2进制的字符串，每一位转换成4位
	 * @param hexString
	 * @return
	 */
	public static String hexString2binaryString(String hexString){
		if(hexString == null || hexString.length()%2 != 0){
			return hexString;
		}
		String binString = "";
		for(int i=0;i<hexString.length();i++){
			binString += String.format("%04d", Integer.parseInt(
					Integer.toBinaryString(Integer.valueOf(hexString.substring(i, i+1), 16))));
		}
		return binString;
	}
	
	/**
	 * 翻译对应分类的value
	 * @param c
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getTransformValue(String c,String value,String fullValue,boolean isMobile){
		String endStr = " (" + fullValue + ")";
		switch (c) {
		case "ip":
			return change16to10(value);
		case "time":
			return datetimeTransform(value);
		case "ratType":
			return getRatType(value);
		case "servingNodeType":
			return "".equals(getServingNodeType(value))?"":getServingNodeType(value) + endStr;
		case "imsi":
			return value.substring(0, value.lastIndexOf("F"));
		case "msisdn":
			return value.substring(2, value.lastIndexOf("F"));
		case "causeForRecClosing":
			return getCauseForRecClosing(value) + endStr;
		case "chChSelectionMode":
			return getChChSelectionMode(value) + endStr;
		case "apnSelectionMode":
			return getApnSelectionMode(value) + endStr;
		case "pdnType":
			return getPdnPDNType(value) + endStr;
		case "serviceConditionChange":
			return getServiceConditionChange(value) + endStr;
		case "duration":
			return value + " (s)";
		case "PLMNIdentifier":
			return getPLMNIdentifier(value);
		case "datavolumelink":
			return value + " (Byte)";
		case "qosInfo":
			return getQosInfo(value) + " (bps)";
		case "fullValue":
			return fullValue;
		case "hide":
			return getHide(value,isMobile);
		case "recordType":
			return getRecordType(value);
		}
		return value;
	}

	/**
	 * 翻译RecordType的值
	 * @param value
	 * @return
	 */
	private static String getRecordType(String value) {
		switch(value){
		case "85":
			return value + " (pgwRecord)";
		}
		return value;
	}

	/**
	 * 隐藏原始值
	 * @param value
	 * @return
	 */
	private static String getHide(String value,boolean isMobile) {
		if(!isMobile){
			User user = UserUtils.getUser();
			String adminAccount = Global.getConfig("adminAccount");
			if ((user!= null && user.isAdmin()) || adminAccount.contains(user.getLoginName())){
				return value;
			}
		}
		return "******";
	}

	/**
	 * 翻译qosInfo的值
	 * @param value
	 * @return
	 */
	private static Integer getQosInfo(String value) {
		return Integer.valueOf(value, 16);
	}

	/**
	 * 翻译PLMNIdentifier的值
	 * @param value
	 * @return
	 */
	private static String getPLMNIdentifier(String value) {
		StringBuffer sb = new StringBuffer();
		if(value.length()==6){
			sb.append(value.charAt(1)).append(value.charAt(0)).append(value.charAt(5))
			.append('-').append(value.charAt(2)).append(value.charAt(4)).append(value.charAt(3));
			return sb.toString().replaceAll("F", "");
		}
		return value;
	}
	
}
