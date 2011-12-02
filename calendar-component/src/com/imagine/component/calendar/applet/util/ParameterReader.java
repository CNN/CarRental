package com.imagine.component.calendar.applet.util;

import java.awt.*;
import java.util.StringTokenizer;

import javax.swing.JApplet;

/**
 * Utility class to read values of different types from an applet parameters.
 *
 */
public class ParameterReader {
	
  /**
   * Read an int value from the specified paramName. If the paramName is not found the value returned is defaultValue.
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param defaultValue The default value returned in case the paramName is  not found. 
   * @return An int value read from the specified paramName. If the paramName is not found the value returned is defaultValue.
   */
	public static int getIntParam(JApplet source, String paramName, int defaultValue) {
    return getIntParam(source, paramName, 10, defaultValue);
  }
    
  /**
   * Read an int value from the specified paramName in the specified base. If the paramName is not found the value returned is defaultValue.
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param base The numeration base.
   * @param defaultValue The default value returned in case the paramName is  not found.
   * @return An int value read from the specified paramName in the specified base. If the paramName is not found the value returned is defaultValue.
   */
	public static int getIntParam(JApplet source, String paramName, int base, int defaultValue) {
    try {
      return Integer.parseInt(get(source, paramName), base);
    } catch (Exception e) {
      return defaultValue;
    } 
  }
  
	/**
   * Read a double value from the specified paramName. If the paramName is not found the value returned is defaultValue.
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
	 * @param defaultValue The default value returned in case the paramName is  not found.
	 * @return A double value read from the specified paramName. If the paramName is not found the value returned is defaultValue.
	 */
  public static double getDoubleParam(JApplet source, String paramName, double defaultValue) {
    try {
      return new Double(get(source, paramName)).doubleValue();
    } catch (Exception e) {
      return defaultValue;
    } 
  }
  
  /**
   * Read a String value from the specified paramName. If the paramName is not found the value returned is defaultValue.
   * 
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param defaultValue The default value returned in case the paramName is  not found.
   * @return A String value read from the specified paramName. If the paramName is not found the value returned is defaultValue.
   */
  public static String getStringParameter(JApplet source, String paramName, String defaultValue) {
    String ret = get(source, paramName);
    if (ret == null || ret.equals("")) {
      ret = defaultValue;
    } 
    return ret;
  }
  
  /**
   * Read a Color value from the specified paramName. If the paramName is not found the value returned is computed from defaultRGB parameter.
   * 
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param defaultRGB The default value for color.
   * @return A Color value read from the specified paramName. If the paramName is not found the value returned is computed from defaultRGB parameter.
   */
  public static Color getColor(JApplet source, String paramName, int defaultRGB) {
    String s = get(source, paramName);
    if (s != null) {
  	  try {
		  int rgb = Integer.parseInt(s, 16);
		  return new Color(rgb);
	  } catch (Exception e) {
	  }
	  
      try {
        StringTokenizer st = new StringTokenizer(s, ",");
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        return new Color(r, g, b);
      } catch (Exception e) {
      }
    }
    
      return new Color(defaultRGB);
  }
  
  /**
   * Get an applet parameter.
   * 
   * @param source The source applet.
   * @param key The parameter name.
   * @return The parameter value.
   */
  private static String get(JApplet source, String key) {
  	return source.getParameter(key); 
  }
  
  /**
   * Get an applet parameter. In case the parameter is not found the defaultValue is returned.
   * @param source The source applet.
   * @param key The parameter name.
   * @param defaultValue The default value returned in case the paramName is  not found.
   * @return The parameer value.
   */
  private static String get(JApplet source, String key, String defaultValue) {
    String ret = get(source, key);
    if (ret == null || ret.equals("")) {
      return defaultValue; 
    } else {
      return ret;
    }
  }
  
  /**
   * Read a Font value from the specified paramName. If the paramName is not found the value returned is computed from the parameters defaultName, defaultStyle, defaultSize.
   * 
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param defaultName The default font name.
   * @param defaultStyle The default font style.
   * @param defaultSize The default font size.
   * @return A Font value read from the specified paramName. If the paramName is not found the value returned is computed from the parameters defaultName, defaultStyle, defaultSize.
   */
  public static Font getFont(JApplet source, String paramName, String defaultName, int defaultStyle, int defaultSize) {
    String s = get(source, paramName);
    String name = defaultName;
    int size = defaultSize;
    int style = defaultStyle;
    
    if (s != null) {
      StringTokenizer st = new StringTokenizer(s, ",");
      if (st.hasMoreTokens()) {
        name = st.nextToken(); 
      }  
      
      if (st.hasMoreTokens()) {
        StringTokenizer strtok = new StringTokenizer(st.nextToken(), "|");
        style = 0;
        while (strtok.hasMoreTokens()) {
          s = strtok.nextToken().trim();
          if (s.equalsIgnoreCase("BOLD")) {
            style |= Font.BOLD;
          } else if (s.equalsIgnoreCase("ITALIC")) {
            style |= Font.ITALIC;
          } else {
            style = defaultStyle;
            break; 
          }
        }
      }
      if (st.hasMoreTokens()) {
        try {
          size = Integer.parseInt(st.nextToken());
        } catch (Exception e) {
        }
      }
    }

    return new Font(name, style, size);
  }
  
  /**
   * Read a boolean value from the specified paramName. If the paramName is not found the value returned is defaultValue.
   * 
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param defaultValue The default value returned in case the paramName is  not found.
   * @return A boolean value read from the specified paramName. If the paramName is not found the value returned is defaultValue.
   */
  public static boolean getBoolean(JApplet source, String paramName, boolean defaultValue) {
  	boolean result = defaultValue;
		String s = get(source, paramName);
		if (s != null && s.length() > 0) {
			if (s.equalsIgnoreCase("true")) {
				result = true;
			} else {
				result = false;
			}
		}
  	
  	return result;
  }
  
  /**
   * Read an int array value from the specified paramName. If the paramName is not found the value returned is defaultValue.
   * 
   * @param source The source applet.
   * @param paramName The parameter from which the value is read.
   * @param defaultValue The default value returned in case the paramName is  not found.
   * @return An int array value read from the specified paramName. If the paramName is not found the value returned is defaultValue.
   */
	public static int[] getIntArray(JApplet source, String paramName, int[] defaultValue) {
		int[] result = defaultValue;
		String s = get(source, paramName);
		if (s != null && s.length() > 0) {
			try {
				StringTokenizer tokenizer = new StringTokenizer(s, ",");
				result = new int[tokenizer.countTokens()];
				for (int i = 0; i < result.length; i++) {
					result[i] = Integer.parseInt(tokenizer.nextToken());
				}
			} catch (Exception e) {
			}
		}
		return result;
	}
}
