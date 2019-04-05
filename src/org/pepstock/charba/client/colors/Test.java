/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.colors;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public class Test {
	
	private static String change(String name) {
		StringBuilder sb = new StringBuilder();
		int index = 0;
    	for (char charString : name.toCharArray()) {
    		if (Character.isUpperCase(charString)){
    			if (index > 0) {
    				sb.append("_");
    			}
    			sb.append(charString);
    		} else {
    			sb.append(Character.toUpperCase(charString));
    		}
    		index++;
    	}
    	return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * HTML color name "AliceBlue" - <span style="background-color:#F0F8FF; border-style: solid; border-width:
		 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		 */
		
		for (HtmlColor color : HtmlColor.values()) {
			String newName = change(color.name());
			StringBuilder sb = new StringBuilder();
			sb.append("/**").append("\n");
			sb.append(" * HTML color name \"").append(newName).append("\" - ");
			sb.append("<span style=\"background-color:").append(color.toHex()).append("; border-style: solid; border-width: 1px;\">");
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
			sb.append("\n");
			sb.append("*/").append("\n");
			sb.append(newName).append("(\"").append(color.toHex()).append("\"),");
			System.out.println(sb.toString());
		}
	}

}
