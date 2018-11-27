package com.example.practice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileFilterSelector;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.PatternFileSelector;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.apache.commons.vfs2.util.FileObjectUtils;

public class StringManipulation {

	public static void main(String[] args) throws URISyntaxException, Exception {
		// TODO Auto-generated method stub

		FileSystemManager fsManager = VFS.getManager();
		FileSystemOptions opts = new FileSystemOptions();
		SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
		SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
		// FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);
		FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

		Map<String, Integer> paramMap = new CalendarUtil().getPresentTime();
		
		String templateString = "/files/${DATE.NOW.YYYY}/${DATE.NOW.MM}/${DATE.NOW.DD}/Ekaplus_EMIR.${DATE.NOW.YYYY}${DATE.NOW.MM}${DATE.NOW.DD}.*.xlsx";
		StringSubstitutor sub = new StringSubstitutor(paramMap);
		String resolvedPath = sub.replace(templateString);

		URI uri = new URI("ftp", ":", "172.16.0.254", 21, null, null, null);
		
		FileObject fo = fsManager.resolveFile(uri.toString(), opts);

		FileSelector selector = new PatternFileSelector(resolvedPath);
		FileObject[] list = fo.findFiles(selector);

		List<FileObject> matched = new ArrayList<FileObject>();

		System.out.println(list.length);
		for (FileObject obj : list) {
			String filename = obj.getName().toString();
			System.out.println(filename.substring(filename.lastIndexOf('/') + 1));
		}

		matched.forEach((obj) -> System.out.println(obj.getName().toString()));

	}

}
