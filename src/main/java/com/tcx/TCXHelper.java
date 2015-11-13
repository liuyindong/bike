package com.tcx;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class TCXHelper {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static TCXLap tcxLapFactory(Element lapElement){
		TCXLap tcxLap = new TCXLap();
		tcxLap.setStartTime(lapElement.getAttributeValue("StartTime"));
		tcxLap.setDistanceMeters(getTextValue(lapElement,"DistanceMeters"));
		tcxLap.setIntensity(getTextValue(lapElement,"Intensity"));
		tcxLap.setTotalTimeSeconds(getTextValue(lapElement,"TotalTimeSeconds"));
		tcxLap.setTriggerMethod(getTextValue(lapElement,"TriggerMethod"));

		lapElement.getNamespace();

		return tcxLap;
	}
	
	private static String getTextValue(Element parent, String childName){
		for (Element e : parent.getChildren()){
			if (e.getName().equals(childName)){
				return e.getText();
			}
		}
		return "";
	}
	
	private static void setTextValue(Element parent, String childName, String newValue){
		for (Element e : parent.getChildren()){
			if (e.getName().equals(childName)){
				e.setText(newValue);
			}
		}
	}
	
	public static List<TCXLap> processTCX(File tcx){
		List<TCXLap> tcxLaps = new ArrayList<TCXLap>();
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		try{
			document = sxb.build(tcx);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ElementFilter elementFilter = new ElementFilter("Lap");
		for (Element e : document.getDescendants(elementFilter)) {
			tcxLaps.add(tcxLapFactory(e));
		}
		return tcxLaps;
	}
	
	public static void updateTCX(File tcx, List<TCXLap> laps){
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		try{
			document = sxb.build(tcx);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, TCXLap> mapLaps = new HashMap<String, TCXLap>();
		for (TCXLap lap : laps){
			mapLaps.put(lap.getStartTime(), lap);
		}
		System.out.println(mapLaps.keySet());
		ElementFilter elementFilter = new ElementFilter("Lap");
		for (Element e : document.getDescendants(elementFilter)) {
			String st = e.getAttribute("StartTime").getValue();
			TCXLap newValues = mapLaps.get(st);
			
			if (newValues == null){
				System.out.println(st + " NOT FOUND");
			}else{
				setTextValue(e, "Intensity", newValues.getIntensity());
				//TODO: Update <Lap StartTime="2014-05-22T17:09:17Z"> <Intensity>Resting</Intensity>
			}
		}
		XMLOutputter xmlOutput = new XMLOutputter();
		 
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(document, FileUtils.openOutputStream(tcx));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	public static List<File> readTCXs(String tcxFilesDirectoryPath){
		File tcxFilesDirectory = new File(tcxFilesDirectoryPath);
		if (!tcxFilesDirectory.isDirectory() || !tcxFilesDirectory.canRead()){
			System.out.println("Cannot read " + tcxFilesDirectoryPath);
			return new ArrayList<File>();
		}

		List<File> files = Arrays.asList(tcxFilesDirectory.listFiles());
		return files;
	}
	
	public static Map<String, File> generateMap(List<File> fileList){
		Map<String, File> mapFiles = new java.util.TreeMap<String, File>(Collections.reverseOrder());
		for (File file : fileList){
			mapFiles.put(file.getName(), file);
		}
		return mapFiles;
	}
}
