package org.javier.xml.ReadXML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
/**
 *
 */
public class App 
{
    public static void main( String[] args )
    {


    	try {

    		File fXmlFile = new File("D:/STARSX3-TK/Exchange Import Job DailyLoad Experienced Errors Upon Processing/05_26_2016/NewBus_STARS3XPDL_05262016-071043.xml");
    		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    		Document doc = dBuilder.parse(fXmlFile);
    				
    		doc.getDocumentElement().normalize();

    		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
    				
    		NodeList nList = doc.getElementsByTagName("RECORD");
    				
    		//System.out.println("----------------------------");
    		
    		int multiplo = 80;
    		
    		for (int temp = 0; temp < nList.getLength(); temp++) {

    			Node nNode = nList.item(temp);
    					
    			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
    					
    			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    				Element eElement = (Element) nNode;
    				System.out.print("UPDATE ST_LO_MASTER SET SENT_TO_LATITUDE = 'Y', LATITUDE_DATE_UPLOADED = SYSDATE WHERE loan_code in (" + eElement.getElementsByTagName("LOAN_CODE").item(0).getTextContent() + ",");
    				if(temp % 75 == 0){
    					System.out.print("\n");
    				}
    				//System.out.println("RECORD : " + eElement.getAttribute("RECORD"));
    				

    			}
    		}
    	    } catch (Exception e) {
    		e.printStackTrace();
    	    }
    	
    	
    }
    
    public static boolean esMultiplo(int n1,int n2){
    	if (n1%n2==0)
    		return true;
    	else
    		return false;
    }
}
