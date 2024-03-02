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

	private static boolean autosave = true;

	public boolean isAutosave() {
		return autosave;
	}

	public void setAutosave(boolean autosave) {
		this.autosave = autosave;
	}

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
		if (autosave)
			save();
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
		if (autosave)
			save();
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
		if (autosave)
			save();
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
		if (autosave)
			save();
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
			settingsFile = clazz.newInstance();
		} catch (Exception ex) {
			Sys.logError(ex);
			return;
		}

		Settings.settingsFile = settingsFile;
		LoadResult result = loadFromFile(settingsFile, path);

		switch (result) {
			case FileNotFound:
				if (autosave)
					saveToFile(settingsFile, path);
				break;
			case ReadBytesMismatch:
				saveToFile(settingsFile, path);
				break;
		}
	}
	
	public static void save() {
		if (settingsFile == null)
			return;
		
		if (settingsPath == null)
			return;
		
		saveToFile(settingsFile, settingsPath);
	}
	
	private static LoadResult loadFromFile(SettingsFile settingsFile, String path) {
		FileStat fileStat = Filesystem.stat(path);
		if (fileStat == null)
			return LoadResult.FileNotFound;

		byte[] bytes = new byte[fileStat.size()];
		FileOptions options = FileOptions.ReadData;
		SDFile file = Filesystem.open(path, options);
		if (file == null) {
			Sys.logError("file cannot be opened at path " + path + " with options " + options.name());
			return LoadResult.FileNotFound;
		}

		int read = file.read(bytes);
		file.close();

		if (read != bytes.length)
			return LoadResult.ReadBytesMismatch;
		
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
			settingsFile.load(inputStream);
			return LoadResult.Ok;
		}
		catch (IOException ex) {
			Sys.logError(ex);
			return LoadResult.InternalError;
		}
	}
	
	private static void saveToFile(SettingsFile settingsFile, String path) {
		FileOptions options = FileOptions.Write;
		SDFile file = Filesystem.open(path,options);
		if (file == null) {
			Sys.logError("file cannot be opened at path " + path + " with options " + options.name());
			return;
		}
		
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

	public enum LoadResult {
		Ok(0),
		InternalError(1),
		FileNotFound(2),
		ReadBytesMismatch(3);

		final int value;

		LoadResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}