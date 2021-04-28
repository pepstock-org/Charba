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
* @return builder instance */
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Comfortable object to create {@link DatasetsItemsSelector#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetsItemsSelectorOptionsBuilder {
	// creates the options
	private final DatasetsItemsSelectorOptions options;

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private DatasetsItemsSelectorOptionsBuilder(IsChart chart) {
		options = new DatasetsItemsSelectorOptions(chart);
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static DatasetsItemsSelectorOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using the chart global options.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static DatasetsItemsSelectorOptionsBuilder create(IsChart chart) {
		return new DatasetsItemsSelectorOptionsBuilder(chart);
	}

	/**
	 * Returns a configured plugin options.
	 * 
	 * @return a configured plugin options.
	 */
	public DatasetsItemsSelectorOptions build() {
		// returns options
		return options;
	}

	/**
	 * Sets <code>true</code> if plugin is enabled.
	 * 
	 * @param enabled <code>true</code> if plugin is enabled.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setEnabled(boolean enabled) {
		options.setEnabled(enabled);
		return this;
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setXAxisID(String xAxisID) {
		options.setXAxisID(xAxisID);
		return this;
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setXAxisID(IsScaleId xAxisID) {
		options.setXAxisID(xAxisID);
		return this;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setColor(String color) {
		options.setColor(color);
		return this;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setColor(IsColor color) {
		options.setColor(color);
		return this;
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setBorderDash(int... borderDash) {
		options.setBorderDash(borderDash);
		return this;
	}

	/**
	 * Sets the border width of the selection.
	 * 
	 * @param borderWidth the border width of the selection.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setBorderWidth(int borderWidth) {
		options.setBorderWidth(borderWidth);
		return this;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setBorderColor(String color) {
		options.setBorderColor(color);
		return this;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setBorderColor(IsColor color) {
		options.setBorderColor(color);
		return this;
	}

	/**
	 * Sets <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setDisplay(boolean display) {
		options.getSelectionCleaner().setDisplay(display);
		return this;
	}

	/**
	 * Sets the selection cleaner label.
	 * 
	 * @param label the selection cleaner label
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setLabel(String label) {
		options.getSelectionCleaner().setLabel(label);
		return this;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setFontSize(int fontSize) {
		options.getSelectionCleaner().getFont().setSize(fontSize);
		return this;
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setFontStyle(FontStyle fontStyle) {
		options.getSelectionCleaner().getFont().setStyle(fontStyle);
		return this;
	}

	/**
	 * Sets the font family, follows CSS font-family in selection cleaner.
	 * 
	 * @param fontFamily Font family, follows CSS font-family in selection cleaner.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setFontFamily(String fontFamily) {
		options.getSelectionCleaner().getFont().setFamily(fontFamily);
		return this;
	}

	/**
	 * Sets the selection cleaner label font color.
	 * 
	 * @param color the selection cleaner label font color.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setFontColor(String color) {
		options.getSelectionCleaner().setColor(color);
		return this;
	}

	/**
	 * Set the selection cleaner label font color.
	 * 
	 * @param color the selection cleaner label font color.
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setFontColor(IsColor color) {
		options.getSelectionCleaner().setColor(color);
		return this;
	}

	/**
	 * Sets the selection cleaner align.
	 * 
	 * @param align the selection cleaner align
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setAlign(Align align) {
		options.getSelectionCleaner().setAlign(align);
		return this;
	}

	/**
	 * Sets the selection cleaner render.
	 * 
	 * @param render the selection cleaner render
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setRender(Render render) {
		options.getSelectionCleaner().setRender(render);
		return this;
	}

	/**
	 * Sets the selection cleaner label position.
	 * 
	 * @param position the selection cleaner label position
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setPosition(Position position) {
		options.getSelectionCleaner().setPosition(position);
		return this;
	}

	/**
	 * Sets the selection cleaner image.
	 * 
	 * @param image the selection cleaner image
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setImage(Img image) {
		options.getSelectionCleaner().setImage(image);
		return this;
	}

	/**
	 * Sets the margin from canvas border.
	 * 
	 * @param margin margin from canvas border
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setMargin(int margin) {
		options.getSelectionCleaner().setMargin(margin);
		return this;
	}

	/**
	 * Sets the padding of selection cleaner element.
	 * 
	 * @param padding padding of selection cleaner element
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setPadding(int padding) {
		options.getSelectionCleaner().setPadding(padding);
		return this;
	}

	/**
	 * Sets the spacing between label and image for selection cleaner element.
	 * 
	 * @param spacing spacing between label and image for selection cleaner element
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setSpacing(int spacing) {
		options.getSelectionCleaner().setSpacing(spacing);
		return this;
	}

	/**
	 * Sets <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param useSelectionStyle <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>
	 * @return builder instance
	 */
	public DatasetsItemsSelectorOptionsBuilder setUseSelectionStyle(boolean useSelectionStyle) {
		options.getSelectionCleaner().setUseSelectionStyle(useSelectionStyle);
		return this;
	}

}
