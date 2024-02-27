package com.am1goo.playdate4j.engine.localization;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	private static final Map<String, Map<String, String>> locales = new HashMap<String, Map<String, String>>();

	private static final String defaultFolderPath = "locale";

	public static void load() {
		load("utf-8");
	}

	public static void load(String charset) {
		load(charset, null);
	}

	public static void load(String charset, List<String> languageCodes) {
		loadFromFolder(defaultFolderPath, charset, languageCodes);
	}

	public static void loadFromFolder(String folder, String charset, List<String> languageCodes) {
		locales.clear();

		final List<String> codes = new ArrayList<>();
		for (PDLanguage language : PDLanguage.values()) {
			codes.add(language.getCode());
		}
		if (languageCodes != null) {
			codes.addAll(languageCodes);
		}

		final StringBuilder sb = new StringBuilder();
		for (String code : codes) {
			sb.setLength(0);
			sb.append(folder);
			if (!folder.endsWith("/"))
				sb.append("/");
			sb.append("strings").append('.').append(code).append(".txt");

			String path = sb.toString();
			SDFile file = Filesystem.open(path, FileOptions.ReadData);
			if (file == null)
				continue;
			
			FilesystemBridge.FileStat stat = Filesystem.stat(path);
			if (stat == null) {
				file.close();
				continue;
			}

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
					
					String key = keyValue[0].trim();
					String value = keyValue[1];
					map.putIfAbsent(key, value);
				}
				
				locales.put(code, map);
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

		drawText(language.getCode(), key, encoding, x, y);
	}

	public static void drawText(String languageCode, String key, PDStringEncoding encoding, int x, int y) {
		if (languageCode == null)
			return;

		Map<String, String> map = locales.getOrDefault(languageCode, null);
		if (map == null)
			return;
		
		String value = map.getOrDefault(key, null);
		if (value == null)
			return;
		
		Graphics.drawText(value, encoding, x, y);
	}
}
