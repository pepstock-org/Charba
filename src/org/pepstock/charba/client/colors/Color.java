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
 * This class contains information about a color.<br>
 * You can requests a specific alpha (starting form source color and cloning it).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Color implements IsColor {

	/**
	 * Default transparency is <b>{@value DEFAULT_ALPHA}</b>, (no transparency).
	 */
	public static final double DEFAULT_ALPHA = 1D;
	/**
	 * Main color linked to CHARBA, <b>#f27173</b>.
	 */
	public static final Color CHARBA = new Color(242, 113, 115);
	// colors
	private final int red;
	private final int green;
	private final int blue;
	// alpha
	private double alpha = DEFAULT_ALPHA;

	/**
	 * Creates the color with RGB values.
	 * 
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 */
	public Color(int r, int g, int b) {
		this(r, g, b, DEFAULT_ALPHA);
	}

	/**
	 * Creates the color with RGB values.
	 * 
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 * @param a alpha value
	 */
	Color(int r, int g, int b, double a) {
		// checks if values are consistent
		ColorUtil.checkChannelWithinBounds(r);
		ColorUtil.checkChannelWithinBounds(g);
		ColorUtil.checkChannelWithinBounds(b);
		ColorUtil.checkAlphaWithinBounds(a);
		// sets values
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getRed()
	 */
	@Override
	public int getRed() {
		return red;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getGreen()
	 */
	@Override
	public int getGreen() {
		return green;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getBlue()
	 */
	@Override
	public int getBlue() {
		return blue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getAlpha()
	 */
	@Override
	public double getAlpha() {
		return alpha;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + ", hex=" + toHex() + "]";
	}
}