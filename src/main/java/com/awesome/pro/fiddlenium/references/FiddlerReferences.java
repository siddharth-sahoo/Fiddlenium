package com.awesome.pro.fiddlenium.references;

/**
 * References related to Fiddler.
 * @author siddharth.s
 */
public class FiddlerReferences {
	
	// Exec Actions
	public final static String EXEC_SAVE_TO_FILE = "fehour";
	public final static String EXEC_START = "start";
	public final static String EXEC_STOP = "stop";
	public final static String EXEC_CLEAR = "cls";
	public final static String EXEC_QUIT = "quit";
	
	// Configuration Constants
	public final static String PROPERTY_FIDDLER_PATH = "fiddlerDirectory";
	public final static String PROPERTY_FIDDLER_BINARY = "fiddlerBinary";
	public final static String PROPERTY_EXEC_ACTION_BINARY = "execActionBinary";
	public final static String PROPERTY_FIDDLER_PORT = "proxyPort";
	public final static String PROPERTY_FIDDLER_PROXYHOST = "proxyHost";
	public final static String PROPERTY_TRAFFIC_FILE = "trafficFile";
	public final static String PROPERTY_QUIET_MODE = "quiet";
	public final static String PROPERTY_SLEEP_TIME = "sleepTime";
	
	// Default Configurations
	public final static String DEFAULT_FIDDLER_PATH = "C:/Program Files (x86)/Fiddler2/";
	public final static String DEFAULT_FIDDLER_BINARY = "Fiddler.exe";
	public final static String DEFAULT_EXEC_ACTION_BINARY = "ExecAction.exe";
	public final static int DEFAULT_FIDDLER_PORT = 8889;
	public final static String DEFAULT_FIDDLER_PROXYHOST = "localhost";
	public final static String DEFAULT_FIDDLER_PROXYHOST_1 = "127.0.0.1";
	public final static boolean DEFAULT_QUIET_MODE = true;
	public final static String DEFAULT_TRAFFIC_FILE = "D:/traffic.txt";
	
	public final static long WAIT_FIDDLER_STARTUP = 2000;
	
	// Remote proxy server end points
	private final static String SERVLET_BASE = "/SampleServer/rest/";
	public final static String SERVLET_START = SERVLET_BASE + "start";
	public final static String SERVLET_STOP = SERVLET_BASE + "stop";
	public final static String SERVLET_NEW_PAGE = SERVLET_BASE + "page";
	public final static String SERVLET_CLEAR = SERVLET_BASE + "clear";
	public final static String SERVLET_TRAFFIC_ALL = SERVLET_BASE + "traffic/all";
	public final static String SERVLET_TRAFFIC_CURR = SERVLET_BASE + "traffic/curr";
	public final static String SERVLET_PAGE_KEY = SERVLET_BASE + "key";
	

}
