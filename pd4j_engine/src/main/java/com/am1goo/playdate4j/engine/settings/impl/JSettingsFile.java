package com.am1goo.playdate4j.engine.settings.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import com.am1goo.playdate4j.engine.serialization.JDataInputStream;
import com.am1goo.playdate4j.engine.serialization.JDataOutputStream;
import com.am1goo.playdate4j.sdk.Sys;

public class JSettingsFile extends BaseSettingsFile {

	private static final byte[] HEADER = new byte[] { 68, 78, 88, 63 };
	private static final short VERSION = 1;

	private short version;
	
	private static final JDataInputStream.Func<String> STRING_INPUT_FUNC = new JDataInputStream.Func<String>() {
		@Override
		public String read(JDataInputStream stream) throws IOException {
			return stream.readUTF();
		}
	};
	
	private static final JDataOutputStream.Func<String> STRING_OUTPUT_FUNC = new JDataOutputStream.Func<String>() {
		@Override
		public void write(JDataOutputStream stream, String value) throws IOException {
			stream.writeUTF(value);
		}
	};
	
	private static final JDataInputStream.Func<Integer> INTEGER_INPUT_FUNC = new JDataInputStream.Func<Integer>() {
		@Override
		public Integer read(JDataInputStream stream) throws IOException {
			return stream.readInt();
		}
	};
	
	private static final JDataOutputStream.Func<Integer> INTEGER_OUTPUT_FUNC = new JDataOutputStream.Func<Integer>() {
		@Override
		public void write(JDataOutputStream stream, Integer value) throws IOException {
			stream.write(value);
		}
	};
	
	private static final JDataInputStream.Func<Float> FLOAT_INPUT_FUNC = new JDataInputStream.Func<Float>() {
		@Override
		public Float read(JDataInputStream stream) throws IOException {
			return stream.readFloat();
		}
	};
	
	private static final JDataOutputStream.Func<Float> FLOAT_OUTPUT_FUNC = new JDataOutputStream.Func<Float>() {
		@Override
		public void write(JDataOutputStream stream, Float value) throws IOException {
			stream.writeFloat(value);
		}
	};
	
	@Override
	public void load(InputStream inputStream) {
		try (JDataInputStream stream = new JDataInputStream(inputStream)) {
			byte[] header = stream.readBytes();
			if (!Arrays.equals(header, HEADER)) {
	            Sys.logError("cannot read " + getClass() + " data, because file has wrong header");
	            return;
			}
			version = stream.readShort();
			stream.readMap(strings(), STRING_INPUT_FUNC, STRING_INPUT_FUNC);
			stream.readMap(integers(), STRING_INPUT_FUNC, INTEGER_INPUT_FUNC);
			stream.readMap(floats(), STRING_INPUT_FUNC, FLOAT_INPUT_FUNC);
		}
		catch (Exception ex) {
			Sys.logError(ex);
		}
	}

	@Override
	public void save(OutputStream outputStream) {
		version = VERSION;
		try (JDataOutputStream stream = new JDataOutputStream(outputStream)) {
			stream.writeBytes(HEADER);
			stream.writeShort(version);
			stream.writeMap(strings(), STRING_OUTPUT_FUNC, STRING_OUTPUT_FUNC);
			stream.writeMap(integers(), STRING_OUTPUT_FUNC, INTEGER_OUTPUT_FUNC);
			stream.writeMap(floats(), STRING_OUTPUT_FUNC, FLOAT_OUTPUT_FUNC);
		}
		catch (Exception ex) {
			Sys.logError(ex);
		}
	}
}
