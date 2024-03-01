package com.am1goo.playdate4j.engine.serialization;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JDataInputStream extends DataInputStream {

	public JDataInputStream(InputStream in) {
		super(in);
	}
	
    public byte[] readBytes() throws IOException {
        int length = readInt();
        return readBytes(length);
    }
    
    public byte[] readBytes(int length) throws IOException {
        byte[] bytes = new byte[length];
        int num = read(bytes);
        if (num != length)
            throw new IOException("wrong byte array size, actual " + num + ", expected " + length);

        return bytes;
    }
    
    public <K, V> Map<K, V> readMap(Func<K> onKey, Func<V> onValue) throws IOException {
    	Map<K, V> map = new HashMap<K, V>();
    	readMap(map, onKey, onValue);
        return map;
    }
    
    public <K, V> void readMap(Map<K, V> map, Func<K> onKey, Func<V> onValue) throws IOException {
        int size = readInt();
        for (int i = 0; i < size; ++i) {
        	K key = onKey.read(this);
        	V value = onValue.read(this);
            map.putIfAbsent(key, value);
        }
    }
    
    public interface Func<T> {
    	T read(JDataInputStream stream) throws IOException;
    }
}
