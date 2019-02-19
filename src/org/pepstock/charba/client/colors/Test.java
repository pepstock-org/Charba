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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//
		
		System.out.println("<table style=\"border-width: 1px;\">");
		System.out.println("  <tr>");
		System.out.println("    <th>Name</th>");
		System.out.println("    <th>Hex</th>");
		System.out.println("    <th>RGB</th>");
		System.out.println("    <th>Color</th>");
		System.out.println("  </tr>");

		for (GwtMaterialColor color : GwtMaterialColor.values()) {
			System.out.println("  <tr>");
			System.out.println("    <td>"+color.name()+"</td>");
			System.out.println("    <td>"+color.toHex()+"</td>");
			System.out.println("    <td>"+color.toRGB()+"</td>");
			System.out.println("    <td style=\"background-color:"+color.toHex()+";\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
			System.out.println("  </tr>");
		}
		System.out.println("</table>");

	}

}
