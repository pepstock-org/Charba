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
 * 
 */
public final class Title extends AbstractLabel{
	
	private static final boolean DEFAULT_DISPLAY = false;
	
	private boolean isTextArray = false;
	
	private enum Property implements Key {
		display,
		fontStyle,
		position,
		text
	}
  
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

	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	public boolean isDisplay(){
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	public void setText(String... text) {
		setText(ArrayListHelper.build(text));
	}
	
	private void setText(JsStringArrayList text) {
		isTextArray = checkAndSetStringValues(Property.text, text);
	}
	
	public List<String> getText(){
		return checkAndGetStringValues(Property.text, isTextArray);
	}

	public void setPosition(Position position){
		setValue(Property.position, position);
	}

	public Position getPosition(){
		return getValue(Property.position, Position.class, Position.top);
	}    

}