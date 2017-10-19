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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * This class collects a set of common field for Pie and Polar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class HovingDataset extends Dataset{

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		backgroundColor,
		borderColor,
		borderWidth,
		hoverBackgroundColor,
		hoverBorderColor,
		hoverBorderWidth
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setBackgroundColor(ArrayListHelper.build(backgroundColor));
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	private void setBackgroundColor(JsStringArrayList backgroundColor) {
		setStringArray(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the fill color of the arcs in the dataset.
	 * @return list of the fill color of the arcs in the dataset.
	 */
	public List<String> getBackgroundColor() {
		return getStringArray(Property.backgroundColor);
	}

	/**
	 * Sets the border color of the arcs in the dataset. 
	 * @param borderColor the border color of the arcs in the dataset. 
	 */
	public void setBorderColor(String... borderColor) {
		setBorderColor(ArrayListHelper.build(borderColor));
	}

	/**
	 * Sets the border color of the arcs in the dataset. 
	 * @param borderColor the border color of the arcs in the dataset. 
	 */
	private void setBorderColor(JsStringArrayList borderColor) {
		setStringArray(Property.borderColor, borderColor);
	}

	/**
	 * Returns the border color of the arcs in the dataset. 
	 * @return list of the border color of the arcs in the dataset. 
	 */
	public List<String> getBorderColor() {
		return getStringArray(Property.borderColor);
	}

	/**
	 * Sets the border width of the arcs in the dataset.
	 * @param borderWidth the border width of the arcs in the dataset.
	 */
	public void setBorderWidth(int... borderWidth) {
		setBorderWidth(ArrayListHelper.build(borderWidth));
	}

	/**
	 * Sets the border width of the arcs in the dataset.
	 * @param borderWidth the border width of the arcs in the dataset.
	 */
	private void setBorderWidth(JsIntegerArrayList borderWidth) {
		setIntegerArray(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the border width of the arcs in the dataset.
	 * @return list of the border width of the arcs in the dataset.
	 */
	public List<Integer> getBorderWidth() {
		return getIntegerArray(Property.borderWidth);
	}

	/**
	 * Sets the fill color of the arcs when hovered
	 * @param colors the fill color of the arcs when hovered
	 */
	public void setHoverBackgroundColor(String... colors){
		setHoverBackgroundColor(ArrayListHelper.build(colors));
	}

	/**
	 * Sets the fill color of the arcs when hovered
	 * @param colors the fill color of the arcs when hovered
	 */
	private void setHoverBackgroundColor(JsStringArrayList colors){
		setStringArray(Property.hoverBackgroundColor, colors);
	}
	
	/**
	 * Returns the fill color of the arcs when hovered
	 * @return list of the fill color of the arcs when hovered
	 */
	public List<String> getHoverBackgroundColor(){
		return getStringArray(Property.hoverBackgroundColor);
	}

	/**
	 * Sets the stroke color of the arcs when hovered.
	 * @param colors the stroke color of the arcs when hovered.
	 */
	public void setHoverBorderColor(String... colors){
		setHoverBorderColor(ArrayListHelper.build(colors));
	}

	/**
	 * Sets the stroke color of the arcs when hovered.
	 * @param colors the stroke color of the arcs when hovered.
	 */
	private void setHoverBorderColor(JsStringArrayList colors){
		setStringArray(Property.hoverBorderColor, colors);
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * @return list of the stroke color of the arcs when hovered.
	 */
	public List<String> getHoverBorderColor(){
		return getStringArray(Property.hoverBorderColor);
	}

	/**
	 * Sets the stroke width of the arcs when hovered.
	 * @param widths the stroke width of the arcs when hovered.
	 */
	public void setHoverBorderWidth(int... widths){
		setHoverBorderWidth(ArrayListHelper.build(widths));
	}

	/**
	 * Sets the stroke width of the arcs when hovered.
	 * @param widths the stroke width of the arcs when hovered.
	 */
	private void setHoverBorderWidth(JsIntegerArrayList widths){
		setIntegerArray(Property.hoverBorderWidth, widths);
	}
	
	/**
	 * Returns the stroke width of the arcs when hovered.
	 * @return list of the stroke width of the arcs when hovered.
	 */
	public List<Integer> getHoverBorderWidth(){
		return getIntegerArray(Property.hoverBorderWidth);
	}

}