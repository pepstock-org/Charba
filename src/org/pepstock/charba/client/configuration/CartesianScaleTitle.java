/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.ScaleTitleAlign;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale title configuration defines options for the scale title.<br>
 * Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianScaleTitle extends AxisContainer {

	// padding instance
	private final Padding padding;
	// font instance
	private final Font font;

	/**
	 * Builds the object storing the axis which this scale title belongs to.
	 * 
	 * @param axis axis which this scale title belongs to.
	 */
	CartesianScaleTitle(Axis axis) {
		super(axis);
		// gets embedded elements
		this.padding = new Padding(() -> getAxis().getScale().getTitle().getPadding());
		this.font = new Font(() -> getAxis().getConfiguration().getTitle().getFont());
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(String color) {
		getAxis().getScale().getTitle().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getAxis().getScale().getTitle().getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @param display If true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getTitle().setDisplay(display);
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @return If true, display the axis title.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getTitle().isDisplay();
	}

	/**
	 * Sets the title text to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		getAxis().getScale().getTitle().setText(text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return getAxis().getScale().getTitle().getText();
	}

	/**
	 * Sets the alignment of the axis title.
	 * 
	 * @param align the alignment of the axis title
	 */
	public void setAlign(ScaleTitleAlign align) {
		getAxis().getScale().getTitle().setAlign(align);
	}

	/**
	 * Returns the alignment of the axis title.
	 * 
	 * @return the alignment of the axis title
	 */
	public ScaleTitleAlign getAlign() {
		return getAxis().getScale().getTitle().getAlign();
	}

}