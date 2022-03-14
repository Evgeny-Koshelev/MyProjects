package com.nc.edu.java.contract.DI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Finder {
	
	private static final char PACKEGE_SEPARATOR = '.';

    private static final char NEW_SEPARATOR = '/';

    private static final String CLASS_FILE = ".class";

    private static final String PACKAGE_ERROR = "ErrorPath: unable to get resources from packege";

    //Scans the package, and returns a list of its classes
    public static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PACKEGE_SEPARATOR, NEW_SEPARATOR);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }
    
    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + PACKEGE_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE)) {
            int endIndex = resource.length() - CLASS_FILE.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
        
    }


}
