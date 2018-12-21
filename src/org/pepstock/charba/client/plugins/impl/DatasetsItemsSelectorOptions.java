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

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.GwtMaterialColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
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
	private static final IsColor DEFAULT_COLOR = GwtMaterialColor.ORANGE_LIGHTEN_3.alpha(DEFAULT_ALPHA);

	/**
	 * Default X axis id
	 */
	private  static final String DEFAULT_AXIS_ID = ScalesNode.DEFAULT_X_AXIS_ID;

	/**
	 * Default border width of selection area
	 */
	private  static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default border color for area
	 */
	private static final IsColor DEFAULT_BORDER_COLOR = GwtMaterialColor.GREY_DARKEN_2;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key{
		color,
		xAxisID,
		borderColor,
		borderDash,
		borderWidth
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
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	private void setBorderDash(JsIntegerArrayList borderDash) {
		setIntegerArray(Property.borderDash, borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines. 
	 */
	public List<Integer> getBorderDash() {
		return getIntegerArray(Property.borderDash);
	}
	
	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines. 
	 */
	JavaScriptObject getBorderDashAsJavaScriptObject() {
		return getValue(Property.borderDash);
	}
	
	/**
	 * Sets the border width of the selection.
	 * @param borderWidth the border width of the selection.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the border width of the selection.
	 * @return list of the border width of the selection.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, DEFAULT_BORDER_WIDTH);
	}
	
	/**
	 * Returns the  color.
	 * 
	 * @return the  color.
	 */
	public String getBorderColorAsString(){
		return getValue(Property.color, DEFAULT_BORDER_COLOR.toRGBA());
	}
	
	/**
	 * Returns the  color.
	 * 
	 * @return the  color.
	 */
	public IsColor getBorderColor(){
		return ColorBuilder.parse(getColorAsString());
	}
	
	/**
	 * Sets the  color.
	 * @param color the  color.
	 */
	public void setBorderColor(String color){
		setValue(Property.borderColor, color);
	}

	/**
	 * Sets the  color.
	 * @param color the  color.
	 */
	public void setBorderColor(IsColor color){
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
