package com.example.practice;

import java.net.URI;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.PatternFileSelector;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class Main {

	public static void main(String[] args) throws Exception {
		FileSystemManager fsManager = VFS.getManager();
		FileSystemOptions opts = new FileSystemOptions();
		SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
		SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

		URI uri = new URI("ftp", ":", "172.16.0.254", 21, "/", null, null);
		
		FileObject fo = fsManager.resolveFile(uri.toString(), opts);
		
		FileSelector selector = new PatternFileSelector(".*");
		
        FileObject[] list = fo.findFiles(selector);
		
        FileObject[] childList = fo.getChildren();
		
		//FileObject[] children = fo.getChildren();
		
		System.out.println(list);
		
		for (FileObject obj :  childList) {
			System.out.println("child : "+ obj.getName());
		}
	}

}
