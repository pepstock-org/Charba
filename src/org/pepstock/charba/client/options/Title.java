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
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title extends AbstractLabel{
	
	private static final boolean DEFAULT_DISPLAY = false;
	// flag to check if a multiple line title has been set
	private boolean isTextArray = false;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key {
		display,
		fontStyle,
		position,
		text
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Title(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.AbstractLabel#getFontStyle()
	 */
	@Override
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, FontStyle.bold);
	}

	/**
	 * Sets if the title is shown.
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns if the title is shown.
	 * @return if the title is shown. Default is true.
	 */
	public boolean isDisplay(){
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		setText(ArrayListHelper.build(text));
	}
	
	/**
	 * Sets the title text to display as an array.
	 * @param text the title text to display as an array.
	 */
	private void setText(JsStringArrayList text) {
		isTextArray = checkAndSetStringValues(Property.text, text);
	}
	
	/**
	 * Returns the title text to display, as a list of strings.
	 * @return a list of strings
	 */
	public List<String> getText(){
		return checkAndGetStringValues(Property.text, isTextArray);
	}

	/**
	 * Sets the position of title.
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position){
		setValue(Property.position, position);
	}

	/**
	 * Returns the position of title.
	 * @return the position of title. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition(){
		return getValue(Property.position, Position.class, Position.top);
	}    

}