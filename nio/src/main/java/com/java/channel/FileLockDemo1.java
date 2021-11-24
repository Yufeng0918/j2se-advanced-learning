package com.java.channel;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;

public class FileLockDemo1 {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("./hello.txt");
		FileChannel channel = in.getChannel();
		
		channel.lock(0, Integer.MAX_VALUE, true);
		Thread.sleep(60 * 60 * 1000);  
		
		channel.close();
		in.close();
	}
	
}
