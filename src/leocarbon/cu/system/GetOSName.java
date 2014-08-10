package leocarbon.cu.system;

public class GetOSName {
    public static String lowercase = System.getProperty("os.name").toLowerCase();
    public static String formatted(){
        if(lowercase.contains("win")) return "win";
        else if(lowercase.contains("mac")) return "mac";
        else return null;
    }
}
