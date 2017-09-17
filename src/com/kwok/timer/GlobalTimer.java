package com.kwok.timer;

import java.util.Timer;

public class GlobalTimer {
	
	private static Timer timer = new Timer();

	public static Timer getTimer() {
		return timer;
	}
}
