package com.thinkgem.jeesite.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

/**
 * 解压tar.gz文件
 * 
 * @version 2017-6-14
 */
public class GZipUtils {

	private BufferedOutputStream bufferedOutputStream;

	private String zipfileName;

	public GZipUtils(String fileName) {

		this.zipfileName = fileName;

	}

	/**
	 * 解压缩tar.gz文件
	 * @param rarFileName为需要解压的文件路径(具体到文件),
	 * @param destDir为解压目标路径
	 * 
	 */
	public static void unTargzFile(String rarFileName, String destDir) {

		GZipUtils gzip = new GZipUtils(rarFileName);
		String outputDirectory = destDir;
		File file = new File(outputDirectory);
		if (!file.exists()) {
			file.mkdir();
		}
		gzip.unzipTarFile(outputDirectory);

	}

	public void unzipTarFile(String outputDirectory) {

		FileInputStream fis = null;
		ArchiveInputStream in = null;
		BufferedInputStream bufferedInputStream = null;
		try {
			fis = new FileInputStream(zipfileName);
			GZIPInputStream is = new GZIPInputStream(new BufferedInputStream(fis));
			in = new ArchiveStreamFactory().createArchiveInputStream("tar", is);
			bufferedInputStream = new BufferedInputStream(in);
			TarArchiveEntry entry = (TarArchiveEntry) in.getNextEntry();
			while (entry != null) {
				String name = entry.getName();
				String[] names = name.split("/");
				String fileName = outputDirectory;
				for (int i = 0; i < names.length; i++) {
					String str = names[i];
					fileName = fileName + File.separator + str;
				}
				if (name.endsWith("/")) {
					FileUtils.createDirectory(fileName);
				} else {
					File file = mkFile(fileName);
					bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(file));
					int b;
					while ((b = bufferedInputStream.read()) != -1) {
						bufferedOutputStream.write(b);
					}
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
				entry = (TarArchiveEntry) in.getNextEntry();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArchiveException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private File mkFile(String fileName) {

		File f = new File(fileName);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
}
