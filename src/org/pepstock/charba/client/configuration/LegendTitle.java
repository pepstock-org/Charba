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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.items.HasLegendText;
import org.pepstock.charba.client.items.LegendTextHandler;
import org.pepstock.charba.client.items.Undefined;

/**
 * This is the title configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LegendTitle extends ConfigurationOptionsContainer implements HasLegendText {

	// font instance
	private final Font font;
	// instance of padding
	private final Padding padding;

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	LegendTitle(ConfigurationOptions options) {
		super(options);
		// get embedded elements
		this.font = new Font(() -> getConfiguration().getLegend().getTitle().getFont());
		this.padding = new Padding(() -> getConfiguration().getLegend().getTitle().getPadding());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.HasLegendText#getLegendTextHandler()
	 */
	@Override
	public LegendTextHandler getLegendTextHandler() {
		return getConfiguration().getLegend().getTitle().getLegendTextHandler();
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
		getConfiguration().getLegend().getTitle().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getConfiguration().getLegend().getTitle().getColorAsString();
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
	 * Sets <code>true</code> if the title is shown.
	 * 
	 * @param display if <code>true</code> the title is shown.
	 */
	public void setDisplay(boolean display) {
		getConfiguration().getLegend().getTitle().setDisplay(display);
	}

	/**
	 * Returns <code>true</code> if the title is shown.
	 * 
	 * @return if <code>true</code> the title is shown.
	 */
	public boolean isDisplay() {
		return getConfiguration().getLegend().getTitle().isDisplay();
	}

	/**
	 * Sets the title text to display.
	 * 
	 * @param text the title text to display.
	 */
	@Override
	public void setText(String text) {
		getConfiguration().getLegend().getTitle().setText(text);
	}

	/**
	 * Returns the title text to display.
	 * 
	 * @return the title text to display
	 */
	@Override
	public String getText() {
		return getConfiguration().getLegend().getTitle().getText();
	}

	/**
	 * Sets the text that will be displayed, as HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param text the text that will be displayed, as HTML
	 */
	@Override
	public void setText(SafeHtml text) {
		getConfiguration().getLegend().getTitle().setText(text);
	}

	/**
	 * Returns the text that will be displayed, as HTML.<br>
	 * If is not HTML, returns {@link Undefined#STRING}. This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return the text that will be displayed, as HTML.
	 */
	@Override
	public SafeHtml getTextAsHtml() {
		return getConfiguration().getLegend().getTitle().getTextAsHtml();
	}

	/**
	 * Returns <code>true</code> if the text of legend's title is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return <code>true</code> if the text of legend's title is HTML
	 */
	@Override
	public boolean isHtmlText() {
		return getConfiguration().getLegend().getTitle().isHtmlText();
	}

	/**
	 * Sets <code>true</code> if the text of legend's titleis HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param htmlText <code>true</code> if the text of legend's title is HTML
	 */
	@Override
	public void setHtmlText(boolean htmlText) {
		getConfiguration().getLegend().getTitle().setHtmlText(htmlText);
	}
}