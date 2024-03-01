package com.am1goo.playdate4j.engine.settings.impl;

import java.util.HashMap;
import java.util.Map;

import com.am1goo.playdate4j.engine.settings.SettingsFile;

public abstract class BaseSettingsFile implements SettingsFile {
	
	private final Map<String, String> strings = new HashMap<String, String>();
	private final Map<String, Integer> integers = new HashMap<String, Integer>();
	private final Map<String, Float> floats = new HashMap<String, Float>();
	
	protected Map<String, String> strings() {
		return strings;
	}
	
	protected Map<String, Integer> integers(){ 
		return integers;
	}
	
	protected Map<String, Float> floats() {
		return floats;
	}
	
	@Override
	public boolean hasString(String key) {
		return strings.containsKey(key);
	}
	
	@Override
	public String getString(String key, String defaultValue) {
		return strings.getOrDefault(key, defaultValue);
	}
	
	@Override
	public void setString(String key, String value) {
		strings.putIfAbsent(key, value);
	}
	
	@Override
	public boolean hasInteger(String key) {
		return integers.containsKey(key);
	}
	
	@Override
	public int getInteger(String key, int defaultValue) {
		return integers.getOrDefault(key, defaultValue);
	}
	
	@Override
	public void setInteger(String key, int value) {
		integers.putIfAbsent(key, value);
	}
	
	@Override
	public boolean hasFloat(String key) {
		return floats.containsKey(key);
	}
	
	@Override
	public float getFloat(String key, float defaultValue) {
		return floats.getOrDefault(key, defaultValue);
	}
	
	@Override
	public void setFloat(String key, float value) {
		floats.putIfAbsent(key, value);
	}
}
