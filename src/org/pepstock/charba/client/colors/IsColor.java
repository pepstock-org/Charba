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
 * Defines the methods that all colors must have.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsColor {

	/**
	 * Returns the red value.
	 * 
	 * @return the red
	 */
	int getRed();

	/**
	 * Returns the green value.
	 * 
	 * @return the green
	 */
	int getGreen();

	/**
	 * Returns the blue value.
	 * 
	 * @return the blue
	 */
	int getBlue();

	/**
	 * Returns the alpha rate.
	 * 
	 * @return the alpha
	 */
	double getAlpha();

	/**
	 * Clones the color applying the alpha value.
	 * 
	 * @param alpha the alpha to set
	 * @return new color with the alpha value
	 */
	IsColor alpha(double alpha);

	/**
	 * Returns RGBA string value which represents the color.
	 * 
	 * @return RGBA string value which represents the color
	 */
	String toRGBA();

	/**
	 * Returns RGB string value which represents the color.
	 * 
	 * @return RGB string value which represents the color
	 */
	String toRGB();

	/**
	 * Returns HEX string value which represents the color.
	 * 
	 * @return HEX string value which represents the color.
	 */
	String toHex();

	/**
	 * As <a href="https://webstore.iec.ch/publication/6169">IEC 61966-2-1:1999</a>, applies to the encoding and communication
	 * of RGB colors used in computer systems and similar applications, by defining encoding transformations for use in defined
	 * reference conditions.
	 * 
	 * @return a long value which represents the RGB color
	 */
	default long toRGBs() {
		return Math.round(getAlpha() * 255L) << 24 | getRed() << 16 | getGreen() << 8 | getBlue();
	}

}
