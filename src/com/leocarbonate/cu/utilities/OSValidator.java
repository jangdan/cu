package com.leocarbonate.cu.utilities;
 
public class OSValidator {
    
    public static String OSLowerCase = System.getProperty("os.name").toLowerCase();
    public static String OS = System.getProperty("os.name");
    
	public static void main(String[] args) {
 
		System.out.println(OSLowerCase);
 
		if (isWindows()) {
			System.out.println("This is Windows");
		} else if (isMac()) {
			System.out.println("This is Mac OS X");
		} else if (isUnix()) {
			System.out.println("This is 'Nix");
		} else if (isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("This is null");
		}
	}
 
	public static boolean isWindows() {
		return (OSLowerCase.indexOf("win") >= 0);
	}
 
	public static boolean isMac() {
		return (OSLowerCase.indexOf("mac") >= 0);
	}
 
	public static boolean isUnix() {
		return (OSLowerCase.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
 
	public static boolean isSolaris() {
		return (OSLowerCase.indexOf("sunos") >= 0);
	}
 
}