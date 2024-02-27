package com.am1goo.playdate4j.engine.localization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.am1goo.playdate4j.sdk.Filesystem;
import com.am1goo.playdate4j.sdk.FilesystemBridge;
import com.am1goo.playdate4j.sdk.Filesystem.FileOptions;
import com.am1goo.playdate4j.sdk.Filesystem.SDFile;
import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Graphics.PDStringEncoding;
import com.am1goo.playdate4j.sdk.Sys;
import com.am1goo.playdate4j.sdk.Sys.PDLanguage;

public class Localization {
	
	private static Map<PDLanguage, Map<String, String>> locales = new HashMap<PDLanguage, Map<String, String>>();
	
	public static void loadFromFolder(String folder, String charset) {
		locales.clear();
		
		for (PDLanguage language : PDLanguage.values()) {
			String path = folder + "/strings." + language.getCode() + ".txt";
			SDFile file = Filesystem.open(path, FileOptions.ReadData);
			if (file == null)
				continue;
			
			FilesystemBridge.FileStat stat = new FilesystemBridge.FileStat();
			Filesystem.stat(path, stat);
			byte[] bytes = new byte[(int)stat.size()];
			int read = file.read(bytes);
			file.close();
			
			if (read != stat.size())
				continue;
			
			try {
				Map<String, String> map = new HashMap<String, String>();
				
				String text = new String(bytes, charset);
				String[] lines = text.split("\n");
				for (String line : lines) {
					String[] keyValue = line.split("=");
					if (keyValue.length < 2)
						continue;
					
					String key = keyValue[0];
					String value = keyValue[1];
					map.putIfAbsent(key, value);
				}
				
				locales.put(language, map);
			}
			catch (UnsupportedEncodingException ex) {
				Sys.logError(ex);
			}
		}
	}
	
	public static void drawText(String key, PDStringEncoding encoding, int x, int y) {
		PDLanguage language = Sys.getLanguage();
		drawText(language, key, encoding, x, y);
	}

	public static void drawText(PDLanguage language, String key, PDStringEncoding encoding, int x, int y) {
		if (language == null)
			return;
		
		Map<String, String> map = locales.getOrDefault(language, null);
		if (map == null)
			return;
		
		String value = map.getOrDefault(key, null);
		if (value == null)
			return;
		
		Graphics.drawText(value, encoding, x, y);
	}
}
