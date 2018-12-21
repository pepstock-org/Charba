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
package org.pepstock.charba.client.plugins.impl;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.GwtMaterialColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.ScalesNode;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Configuration options of selection plugin.<br>
 * It is managing:<br>
 * <ul>
 * <li> the selection color
 * <li> the X axis ID
 * </ul>
 *  
 * @author Andrea "Stock" Stocchero
 * @since 1.8
 */
public final class DatasetsItemsSelectorOptions extends JavaScriptObjectContainer{
	
	// default alpha of selecting/selection colors
	private static final double DEFAULT_ALPHA = 0.3D;
	
	/**
	 * Default color for area
	 */
	public static final IsColor DEFAULT_COLOR = GwtMaterialColor.ORANGE_LIGHTEN_3.alpha(DEFAULT_ALPHA);


	/**
	 * Default X axis id
	 */
	public static final String DEFAULT_AXIS_ID = ScalesNode.DEFAULT_X_AXIS_ID;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key{
		color,
		xAxisID
	}
	
	/**
	 * Builds the object with a new java script object setting the default value of plugin.
	 */
	public DatasetsItemsSelectorOptions() {
		super();
	}

	/**
	 * Builds the object using the java script object of options, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param javaScriptObject configuration of plugin.
	 */
	DatasetsItemsSelectorOptions(JavaScriptObject javaScriptObject) {
		super((GenericJavaScriptObject)javaScriptObject);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID){
		  setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public String getXAxisID(){
		  return getValue(Property.xAxisID, DEFAULT_AXIS_ID);
	}
	
	/**
	 * Returns the  color.
	 * 
	 * @return the  color.
	 */
	public String getColorAsString(){
		return getValue(Property.color, DEFAULT_COLOR.toRGBA());
	}
	
	/**
	 * Returns the  color.
	 * 
	 * @return the  color.
	 */
	public IsColor getColor(){
		return ColorBuilder.parse(getColorAsString());
	}
	
	/**
	 * Sets the  color.
	 * @param color the  color.
	 */
	public void setColor(String color){
		setValue(Property.color, color);
	}

	/**
	 * Sets the  color.
	 * @param color the  color.
	 */
	public void setColor(IsColor color){
		setColor(color.toRGBA());
	}

	/**
	 * Returns the java script object of this options.
	 * 
	 * @return the java script object of this options.
	 */
	public JavaScriptObject getObject(){
		return super.getJavaScriptObject();
	}

}
