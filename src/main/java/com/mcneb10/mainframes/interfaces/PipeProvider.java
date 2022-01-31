package com.mcneb10.mainframes.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public class PipeProvider {
	private HashMap<String, ArrayList<String>> pipes;
	public PipeProvider() {
		pipes = new HashMap<String, ArrayList<String>>();
	}
	public boolean hasPipe(String pipeName) {
		return pipes.containsKey(pipeName);
	}
	public String getLatestPipe(String pipeName) {
		return null;
	}
	public ArrayList<String> getPipe(String pipeName) {
		return pipes.get(pipeName);
	}
	public String[] getPipeList() {
		return (String[]) pipes.keySet().toArray();
	}
	public void addPipe(String pipeName) {
		pipes.put(pipeName, new ArrayList<String>());
	}
	public void addToPipe(String pipeName, String value) {
		pipes.get(pipeName).add(value);
	}
}
