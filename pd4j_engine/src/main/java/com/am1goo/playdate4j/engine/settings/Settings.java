package com.am1goo.playdate4j.engine.settings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.am1goo.playdate4j.engine.settings.impl.JSettingsFile;
import com.am1goo.playdate4j.sdk.Filesystem;
import com.am1goo.playdate4j.sdk.Filesystem.FileOptions;
import com.am1goo.playdate4j.sdk.Filesystem.FileWhence;
import com.am1goo.playdate4j.sdk.Filesystem.SDFile;
import com.am1goo.playdate4j.sdk.FilesystemBridge.FileStat;
import com.am1goo.playdate4j.sdk.Sys;

public class Settings {
	
	private static final String defaultFilePath = ".settings.json";
	
	private static SettingsFile settingsFile = null;
	private static String settingsPath = null;
	
	public boolean hasString(String key) {
		if (settingsFile == null)
			return false;
		
		return settingsFile.hasString(key);
	}
	
	public String getString(String key, String defaultValue) {
		if (settingsFile == null)
			return defaultValue;
		
		return settingsFile.getString(key,  defaultValue);
	}
	
	public void setString(String key, String value) {
		if (settingsFile == null)
			return;
		
		settingsFile.setString(key, value);
	}
	
	public boolean hasInteger(String key) {
		if (settingsFile == null)
			return false;
		
		return settingsFile.hasInteger(key);
	}
	
	public int getInteger(String key, int defaultValue) {
		if (settingsFile == null)
			return defaultValue;
		
		return settingsFile.getInteger(key, defaultValue);
	}
	
	public void setInteger(String key, int value) {
		if (settingsFile == null)
			return;
		
		settingsFile.setInteger(key, value);
	}
	
	public boolean hasFloat(String key) {
		if (settingsFile == null)
			return false;
		
		return settingsFile.hasFloat(key);
	}
	
	public float getFloat(String key, float defaultValue) {
		if (settingsFile == null)
			return defaultValue;
		
		return settingsFile.getFloat(key, defaultValue);
	}
	
	public void setFloat(String key, float value) {
		if (settingsFile == null)
			return;
		
		settingsFile.setFloat(key, value);
	}

	public boolean hasBoolean(String key) {
		if (settingsFile == null)
			return false;
		
		return settingsFile.hasInteger(key);
	}
	
	public boolean getBoolean(String key, boolean defaultValue) {
		if (settingsFile == null)
			return defaultValue;
		
		int intValue = settingsFile.getInteger(key, defaultValue ? 1 : 0);
		return intValue > 0;
	}
	
	public void setBoolean(String key, boolean value) {
		if (settingsFile == null)
			return;
		
		settingsFile.setInteger(key, value ? 1 : 0);
	}
	
	public static void load() {
		load(JSettingsFile.class, defaultFilePath);
	}

	public static void load(Class<? extends SettingsFile> clazz, String path) {
		if (settingsFile != null) {
			saveToFile(settingsFile, settingsPath);
			settingsFile = null;
			settingsPath = null;
		}
		
		SettingsFile settingsFile = null;
		try {
			settingsFile = (SettingsFile) clazz.newInstance();
		}
		catch (Exception ex) {
			Sys.logError(ex);
			return;
		}
		
		Settings.settingsFile = settingsFile;
		loadFromFile(settingsFile, path);
	}
	
	public static void save() {
		if (settingsFile == null)
			return;
		
		if (settingsPath == null)
			return;
		
		saveToFile(settingsFile, settingsPath);
	}
	
	private static void loadFromFile(SettingsFile settingsFile, String path) {
		FileStat fileStat = Filesystem.stat(path);
		if (fileStat == null)
			return;
		
		byte[] bytes = new byte[fileStat.size()];
		SDFile file = Filesystem.open(path, FileOptions.ReadData);
		if (file == null)
			return;
			
		int read = file.read(bytes);
		file.close();

		if (read != bytes.length)
			return;
		
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
			settingsFile.load(inputStream);
		}
		catch (IOException ex) {
			Sys.logError(ex);
		}
	}
	
	private static void saveToFile(SettingsFile settingsFile, String path) {
		SDFile file = Filesystem.open(path, FileOptions.Write);
		if (file == null)
			return;
		
		file.seek(0, FileWhence.Begin);
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			settingsFile.save(outputStream);
			file.write(outputStream.toByteArray());
		}
		catch (IOException ex) {
			Sys.logError(ex);
		}
		finally {
			file.flush();
			file.close();	
		}
	}
}