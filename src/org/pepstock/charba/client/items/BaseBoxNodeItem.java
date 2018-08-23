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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;

/**
 * This object is created by CHART.JS and passed to all callbacks and events handlers related to legend entity.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class BaseBoxNodeItem extends BaseBoxItem {
	
	private final MarginsItem margins;
	
	private final SizeItem minSize;

	/**
	 * Name of fields of JavaScript object.
	 */
	protected enum Property implements Key
	{
		fullWidth,
		position,
		weight,
		width,
		height,
		maxWidth,
		maxHeight,
		margins,
		paddingTop,
		paddingRight,
		paddingLeft,
		paddingBottom,
		minSize
	}

	protected BaseBoxNodeItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		margins = new MarginsItem((GenericJavaScriptObject)getValue(Property.margins));
		minSize= new SizeItem((GenericJavaScriptObject)getValue(Property.minSize));
	}

	public final boolean isFullWidth() {
		return getValue(Property.fullWidth, UndefinedValues.BOOLEAN);
	}
	
	public final Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}

	public final double getWeight(){
		return getValue(Property.weight, UndefinedValues.DOUBLE);
	}

	public final int getWidth(){
		return getValue(Property.width, UndefinedValues.INTEGER);
	}

	public final int getHeight(){
		return getValue(Property.height, UndefinedValues.INTEGER);
	}

	public final double getMaxWidth(){
		return getValue(Property.maxWidth, UndefinedValues.DOUBLE);
	}

	public final double getMaxHeight(){
		return getValue(Property.maxHeight, UndefinedValues.DOUBLE);
	}

	public final int getPaddingTop(){
		return getValue(Property.paddingTop, UndefinedValues.INTEGER);
	}

	public final int getPaddingRight(){
		return getValue(Property.paddingRight, UndefinedValues.INTEGER);
	}

	public final int getPaddingBottom(){
		return getValue(Property.paddingBottom, UndefinedValues.INTEGER);
	}

	public final int getPaddingLeft(){
		return getValue(Property.paddingLeft, UndefinedValues.INTEGER);
	}

	public final MarginsItem getMargins(){
		return margins;
	}

	public final SizeItem getMinSize(){
		return minSize; 
	}

}