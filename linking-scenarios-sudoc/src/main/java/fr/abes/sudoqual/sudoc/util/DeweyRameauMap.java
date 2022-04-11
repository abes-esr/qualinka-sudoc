/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DeweyRameauMap {
	
	private static final Logger logger = LoggerFactory.getLogger(DeweyRameauMap.class);
    
    private DeweyRameauMap(){}

    public static Integer convertirDeweyRameau(Map<String,String> map, String dewey) {
        if (map.isEmpty()) {
            map = getDeweyRameauMap();
        }
        try {
            if (dewey.length() > 3) {
                dewey = dewey.substring(0, 3);
            }
            
            String rameau = map.get(dewey);
            if(rameau == null) {
            	if(logger.isWarnEnabled()) {
            		logger.warn("no rameau for the following dewey : '" + dewey + "'");
            	}
            	return null;
            }

            return Integer.parseInt(rameau);
        } catch (Exception e) {
        	if(logger.isWarnEnabled()) {
        		logger.warn(dewey + ", " + map.get(dewey) + " => ", e);
        	}
            return null;
        }
    }

    private static Object deweyRameauMapLock = new Object();
    private static Map<String, String> deweyRameauMap = null;
    public static Map<String,String> getDeweyRameauMap() {
    	synchronized (deweyRameauMapLock) {
	    	if(deweyRameauMap == null) {
		    	deweyRameauMap = new HashMap<>();
		        for (int i = 0; i < 10; i++) {
		            if (i != 4 && i != 5 && i != 6) {
		            	deweyRameauMap.put("00" + i, "000");
		            }
		        }
		        for (int i = 10; i < 20; i++) {
		        	deweyRameauMap.put("0" + i, "000");
		        }
		        for (int i = 20; i < 30; i++) {
		        	deweyRameauMap.put("0" + i, "020");
		        }
		        for (int i = 30; i < 100; i++) {
		        	deweyRameauMap.put("0" + i, "000");
		        }
		
		        for (int i = 100; i < 130; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "100");
		        }
		        for (int i = 130; i < 140; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "130");
		        }
		        for (int i = 140; i < 150; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "100");
		        }
		        for (int i = 150; i < 160; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "150");
		        }
		        for (int i = 160; i < 200; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "100");
		        }
		        for (int i = 200; i < 300; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "200");
		        }
		        for (int i = 300; i < 320; i++) {
		            if (i != 305) {
		            	deweyRameauMap.put(String.valueOf(i), "300");
		            }
		        }
		        deweyRameauMap.put("305", "305");
		        for (int i = 320; i < 330; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "320");
		        }
		        for (int i = 330; i < 340; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "330");
		        }
		        for (int i = 340; i < 350; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "340");
		        }
		        for (int i = 350; i < 355; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "350");
		        }
		        for (int i = 355; i < 360; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "355");
		        }
		        for (int i = 360; i < 370; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "360");
		        }
		        for (int i = 370; i < 380; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "370");
		        }
		        for (int i = 380; i < 390; i++) {
		        	deweyRameauMap.put(String.valueOf(i), "330");
		        }
		        for (int i = 390; i < 400; i++) {
		            deweyRameauMap.put(String.valueOf(i), "391");
		        }
		        for (int i = 400; i < 420; i++) {
		            deweyRameauMap.put(String.valueOf(i), "401");
		        }
		        for (int i = 420; i < 500; i++) {
		            deweyRameauMap.put(String.valueOf(i), "400");
		        }
		        for (int i = 500; i < 510; i++) {
		            deweyRameauMap.put(String.valueOf(i), "500");
		        }
		        for (int i = 510; i < 520; i++) {
		            deweyRameauMap.put(String.valueOf(i), "510");
		        }
		        for (int i = 520; i < 530; i++) {
		            deweyRameauMap.put(String.valueOf(i), "520");
		        }
		        for (int i = 530; i < 540; i++) {
		            deweyRameauMap.put(String.valueOf(i), "530");
		        }
		        for (int i = 540; i < 550; i++) {
		            deweyRameauMap.put(String.valueOf(i), "540");
		        }
		        for (int i = 550; i < 560; i++) {
		            deweyRameauMap.put(String.valueOf(i), "550");
		        }
		        for (int i = 560; i < 570; i++) {
		            deweyRameauMap.put(String.valueOf(i), "560");
		        }
		        for (int i = 570; i < 580; i++) {
		            if (i != 577 && i != 579) {
		                deweyRameauMap.put(String.valueOf(i), "570");
		            }
		        }
		        deweyRameauMap.put("577", "577");
		        deweyRameauMap.put("579", "579");
		
		        for (int i = 580; i < 590; i++) {
		            deweyRameauMap.put(String.valueOf(i), "580");
		        }
		        for (int i = 590; i < 600; i++) {
		            deweyRameauMap.put(String.valueOf(i), "590");
		        }
		        for (int i = 600; i < 610; i++) {
		            deweyRameauMap.put(String.valueOf(i), "600");
		        }
		        for (int i = 610; i < 620; i++) {
		            if (i != 615) {
		                deweyRameauMap.put(String.valueOf(i), "610");
		            }
		        }
		        deweyRameauMap.put("615", "615");
		        deweyRameauMap.put("004", "621");
		        deweyRameauMap.put("005", "621");
		        deweyRameauMap.put("006", "621");
		        for (int i = 620; i < 630; i++) {
		            deweyRameauMap.put(String.valueOf(i), "600");
		        }
		        for (int i = 630; i < 640; i++) {
		            deweyRameauMap.put(String.valueOf(i), "630");
		        }
		        for (int i = 640; i < 650; i++) {
		            deweyRameauMap.put(String.valueOf(i), "640");
		        }
		        for (int i = 650; i < 660; i++) {
		            deweyRameauMap.put(String.valueOf(i), "650");
		        }
		        for (int i = 660; i < 690; i++) {
		            deweyRameauMap.put(String.valueOf(i), "600");
		        }
		        for (int i = 690; i < 700; i++) {
		            deweyRameauMap.put(String.valueOf(i), "690");
		        }
		        for (int i = 700; i < 710; i++) {
		            deweyRameauMap.put(String.valueOf(i), "700");
		        }
		        for (int i = 710; i < 720; i++) {
		            deweyRameauMap.put(String.valueOf(i), "710");
		        }
		        for (int i = 720; i < 730; i++) {
		            deweyRameauMap.put(String.valueOf(i), "720");
		        }
		        for (int i = 730; i < 740; i++) {
		            deweyRameauMap.put(String.valueOf(i), "730");
		        }
		        for (int i = 740; i < 750; i++) {
		            deweyRameauMap.put(String.valueOf(i), "740");
		        }
		        for (int i = 750; i < 760; i++) {
		            deweyRameauMap.put(String.valueOf(i), "750");
		        }
		        for (int i = 760; i < 770; i++) {
		            deweyRameauMap.put(String.valueOf(i), "760");
		        }
		        for (int i = 770; i < 780; i++) {
		            deweyRameauMap.put(String.valueOf(i), "770");
		        }
		        for (int i = 780; i < 790; i++) {
		            deweyRameauMap.put(String.valueOf(i), "780");
		        }
		        for (int i = 790; i < 793; i++) {
		            deweyRameauMap.put(String.valueOf(i), "790");
		        }
		        for (int i = 793; i < 800; i++) {
		            deweyRameauMap.put(String.valueOf(i), "793");
		        }
		        for (int i = 800; i < 900; i++) {
		            deweyRameauMap.put(String.valueOf(i), "801");
		        }
		        for (int i = 900; i < 910; i++) {
		            deweyRameauMap.put(String.valueOf(i), "900");
		        }
		        for (int i = 910; i < 915; i++) {
		            deweyRameauMap.put(String.valueOf(i), "910");
		        }
		        deweyRameauMap.put("914", "914");
		        for (int i = 915; i < 920; i++) {
		            deweyRameauMap.put(String.valueOf(i), "915");
		        }
		        for (int i = 920; i < 930; i++) {
		            deweyRameauMap.put(String.valueOf(i), "900");
		        }
		        for (int i = 930; i < 940; i++) {
		            deweyRameauMap.put(String.valueOf(i), "930");
		        }
		        for (int i = 940; i < 950; i++) {
		            if (i != 944) {
		                deweyRameauMap.put(String.valueOf(i), "940");
		            }
		        }
		        for (int i = 950; i < 1000; i++) {
		            deweyRameauMap.put(String.valueOf(i), "950");
		        }
		
		        deweyRameauMap.put("944", "944");
		        deweyRameauMap.put("950", "950");
		        deweyRameauMap.put("960", "950");
		        deweyRameauMap.put("970", "950");
		        deweyRameauMap.put("980", "950");
		        deweyRameauMap.put("990", "950");
		        deweyRameauMap = Collections.unmodifiableMap(deweyRameauMap);
	    	}
    	}
        return deweyRameauMap;
    }

}
