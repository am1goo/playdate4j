package com.am1goo.playdate4j.engine.serialization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class JDataOutputStream extends DataOutputStream {

	public JDataOutputStream(OutputStream out) {
		super(out);
	}
	
    public void writeBytes(byte[] bytes) throws IOException {
        int length = bytes.length;
        writeInt(length);
        write(bytes, 0, bytes.length);
    }
        
    public <K, V> void writeMap(Map<K, V> map, Func<K> onKey, Func<V> onValue) throws IOException {
    	write(map.size());
    	for (K key : map.keySet()) {
    		V value = map.get(key);
    		onKey.write(this, key);
    		onValue.write(this, value);
    	}
    }
    
    public interface Func<T> {
    	void write(JDataOutputStream stream, T value) throws IOException;
    }
}
