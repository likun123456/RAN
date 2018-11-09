package com.thinkgem.jeesite.common.utils;

/**
 * 话单文本解析工具类
 * @author wangjingshi
 *
 */
public class EbmDecoder {
	
	
	
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
		return spaceCount%4==1?spaceCount/4 + 1:spaceCount/4;
	}
	
	
}
