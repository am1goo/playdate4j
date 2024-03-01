package com.am1goo.playdate4j.engine.settings;

import java.io.InputStream;
import java.io.OutputStream;

public interface SettingsFile {
	
	public void load(InputStream inputStream);
	public void save(OutputStream outputStream);

	public boolean hasString(String key);
	public String getString(String key, String defaultValue);
	public void setString(String key, String value);
	
	public boolean hasInteger(String key);
	public int getInteger(String key, int defaultValue);
	public void setInteger(String key, int value);
	
	public boolean hasFloat(String key);
	public float getFloat(String key, float defaultValue);
	public void setFloat(String key, float value);
}