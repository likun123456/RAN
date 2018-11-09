package com.thinkgem.jeesite.modules.cms.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ModifyFileUtil {
	
	private String path;
	private final String target;
	private final String newContent;

	public ModifyFileUtil(String path, String target, String newContent) {
		// 操作目录。从该目录开始。该文件目录下及其所有子目录的文件都将被替换。
		this.path = path;
		// target:需要被替换、改写的内容。
		this.target = target;
		// newContent:需要新写入的内容。
		this.newContent = newContent;

		operation();
	}

	private void operation() {
		File file = new File(path);
		if (file.isFile())
			operationFile(file);
	}


	public void operationFile(File file) {

		try {
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
			File tmpfile = new File(file.getParentFile().getAbsolutePath()
					+ "\\" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;
			while (true) {
				str = reader.readLine();

				if (str == null)
					break;

				if (str.contains(target)) {
					writer.write(newContent + "\n");

					flag = true;
				} else
					writer.write(str + "\n");
			}

			is.close();

			writer.flush();
			writer.close();

			if (flag) {
				file.delete();
				tmpfile.renameTo(new File(file.getAbsolutePath()));
			} else
				tmpfile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//代码测试：假设有一个test文件夹，test文件夹下含有若干文件或者若干子目录，子目录下可能也含有若干文件或者若干子目录（意味着可以递归操作）。
		//把test目录下以及所有子目录下（如果有）中文件含有"hi"的字符串行替换成新的"hello,world!"字符串行。
		new ModifyFileUtil("E:/root/wireshark/preferences", "diameter.sctp.ports", "diameter.sctp.ports: 3868,3869,3870,3871,4508,4509,4868,9177,55377,5081");
	}

}
