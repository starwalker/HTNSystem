package tcrn.tmti.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;

import tcrn.tmti.exception.HTNSystemException;

public class SystemConfiguration {
	
	private String mmserverHost;
	private String mmserverPort;
	private String wsdServerEnabled;
	private String wsdServerhost;
	private String semantictype;
	private String inputDirectoryPath;
	private String outputDirectoryPath;
	
	public String getInputDirectoryPath() {
		return inputDirectoryPath;
	}

	public void setInputDirectoryPath(String inputDirectoryPath) {
		this.inputDirectoryPath = inputDirectoryPath;
	}

	public String getMmserverHost() {
		return mmserverHost;
	}

	public void setMmserverHost(String mmserverHost) {
		this.mmserverHost = mmserverHost;
	}

	public String getMmserverPort() {
		return mmserverPort;
	}

	public void setMmserverPort(String mmserverPort) {
		this.mmserverPort = mmserverPort;
	}

	public String getWsdServerEnabled() {
		return wsdServerEnabled;
	}

	public void setWsdServerEnabled(String wsdServerEnabled) {
		this.wsdServerEnabled = wsdServerEnabled;
	}

	public String getWsdServerhost() {
		return wsdServerhost;
	}

	public void setWsdServerhost(String wsdServerhost) {
		this.wsdServerhost = wsdServerhost;
	}

	public String getSemantictype() {
		return semantictype;
	}

	public void setSemantictype(String semantictype) {
		this.semantictype = semantictype;
	}

	public SystemConfiguration getPropertyValues() throws HTNSystemException, Exception {
		CodeSource codeSource = SystemConfiguration.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		File jarDir = jarFile.getParentFile();
		File propFile = null;
		if (jarDir != null && jarDir.isDirectory()) {
			propFile = new File(jarDir, "config.properties");
		}
		if(propFile==null)
			throw new HTNSystemException("could not find property file");
		//System.out.println(propFile.getName());
		Properties prop = new Properties();
		InputStream inputStream = new FileInputStream(propFile);
		prop.load(inputStream);
        mmserverHost = prop.getProperty("mm.server.host").trim();
        mmserverPort = prop.getProperty("mm.server.port").trim();
        wsdServerEnabled = prop.getProperty("wsd.server.enabled").trim();
        wsdServerhost = prop.getProperty("wsd.server.host").trim();
        semantictype = prop.getProperty("mm.semantic.restrict").trim();
        inputDirectoryPath = prop.getProperty("htnsystem.input.directory").trim();
        outputDirectoryPath = prop.getProperty("htnsystem.output.directory").trim();
        
        //System.out.println(mmserverHost+mmserverPort+wsdServerEnabled+wsdServerhost+semantictype);
        SystemConfiguration sysConfig = new SystemConfiguration();
        sysConfig.setMmserverHost(mmserverHost);
        sysConfig.setMmserverPort(mmserverPort);
        sysConfig.setWsdServerEnabled(wsdServerEnabled);
        sysConfig.setWsdServerhost(wsdServerhost);
        sysConfig.setSemantictype(semantictype);
        sysConfig.setInputDirectoryPath(inputDirectoryPath);
        sysConfig.setOutputDirectoryPath(outputDirectoryPath);
        
        return sysConfig;
	}

	public String getOutputDirectoryPath() {
		return outputDirectoryPath;
	}

	public void setOutputDirectoryPath(String outputDirectoryPath) {
		this.outputDirectoryPath = outputDirectoryPath;
	}

}
