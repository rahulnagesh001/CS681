package edu.umb.cs681.hw17;

@FunctionalInterface
public interface Observer {
	public void update(Observable obs, Object obj);
}