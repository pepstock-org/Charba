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
import org.pepstock.charba.client.enums.Position;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar, the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.Dataset
 */
public class BarDataset extends Dataset{

	// set of boolean flags to know if the value has been set as single value or as array
	private boolean isBackgroundColorArray = false;
	
	private boolean isBorderColorArray = false;
	
	private boolean isBorderWidthArray = false;
	
	private boolean isHoverBackgroundColorArray = false;
	
	private boolean isHoverBorderColorArray = false;
	
	private boolean isHoverBorderWidthArray = false;
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		backgroundColor,
		borderColor,
		borderWidth,
		hoverBackgroundColor,
		hoverBorderColor,
		hoverBorderWidth,
		xAxisID,
		yAxisID,
		borderSkipped
	}

	/**
	 * Sets the fill colors of the bars.
	 * @param backgroundColor the fill colors of the bars.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setBackgroundColor(ArrayListHelper.build(backgroundColor));
	}

	/**
	 * Sets the fill colors of the bars.
	 * @param backgroundColor the fill colors of the bars.
	 */
	private void setBackgroundColor(JsStringArrayList backgroundColor) {
		isBackgroundColorArray = checkAndSetStringValues(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the fill colors of the bars
	 * @return list of the fill colors of the bars
	 */
	public List<String> getBackgroundColor() {
		return checkAndGetStringValues(Property.backgroundColor, isBackgroundColorArray);
	}

	/**
	 * Sets the color of the bar border
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		setBorderColor(ArrayListHelper.build(borderColor));
	}
	
	/**
	 * Sets the color of the bar border
	 * @param borderColor the color of the bar border
	 */
	private void setBorderColor(JsStringArrayList borderColor) {
		isBorderColorArray = checkAndSetStringValues(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the bar border
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColor() {
		return checkAndGetStringValues(Property.borderColor, isBorderColorArray);
	}
	
	/**
	 * Sets the stroke width of the bar in pixels.
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(int... borderWidth) {
		setBorderWidth(ArrayListHelper.build(borderWidth));
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	private void setBorderWidth(JsIntegerArrayList borderWidth) {
		isBorderWidthArray = checkAndSetIntegerValues(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels.
	 * @return list of the stroke width of the bar in pixels.
	 */
	public List<Integer> getBorderWidth() {
		return checkAndGetIntegerValues(Property.borderWidth, isBorderWidthArray);
	}

	/**
	 * Sets the fill color of the bars when hovered
	 * @param colors the fill color of the bars when hovered
	 */
	public void setHoverBackgroundColor(String... colors){
		setHoverBackgroundColor(ArrayListHelper.build(colors));
	}
	
	/**
	 * Sets the fill color of the bars when hovered
	 * @param colors the fill color of the bars when hovered
	 */
	private void setHoverBackgroundColor(JsStringArrayList colors){
		isHoverBackgroundColorArray = checkAndSetStringValues(Property.hoverBackgroundColor, colors);
	}
	
	/**
	 * Returns the fill color of the bars when hovered
	 * @return list of the fill color of the bars when hovered
	 */
	public List<String> getHoverBackgroundColor(){
		return checkAndGetStringValues(Property.hoverBackgroundColor, isHoverBackgroundColorArray);
	}

	/**
	 * Sets the stroke color of the bars when hovered
	 * @param colors the stroke color of the bars when hovered
	 */
	public void setHoverBorderColor(String... colors){
		setHoverBorderColor(ArrayListHelper.build(colors));
	}
	
	/**
	 * Sets the stroke color of the bars when hovered
	 * @param colors the stroke color of the bars when hovered
	 */
	private void setHoverBorderColor(JsStringArrayList colors){
		isHoverBorderColorArray = checkAndSetStringValues(Property.hoverBorderColor, colors);
	}
	
	/**
	 * Returns the stroke color of the bars when hovered
	 * @return list of the stroke color of the bars when hovered
	 */
	public List<String> getHoverBorderColor(){
		return checkAndGetStringValues(Property.hoverBorderColor, isHoverBorderColorArray);
	}

	/**
	 * Sets the stroke width of the bars when hovered.
	 * @param widths the stroke width of the bars when hovered.
	 */
	public void setHoverBorderWidth(int... widths){
		setHoverBorderWidth(ArrayListHelper.build(widths));
	}

	/**
	 * Sets the stroke width of the bars when hovered.
	 * @param widths the stroke width of the bars when hovered.
	 */
	private void setHoverBorderWidth(JsIntegerArrayList widths){
		isHoverBorderWidthArray = checkAndSetIntegerValues(Property.hoverBorderWidth, widths);
	}
	
	/**
	 * Returns the stroke width of the bars when hovered.
	 * @return list of the stroke width of the bars when hovered.
	 */
	public List<Integer> getHoverBorderWidth(){
		return checkAndGetIntegerValues(Property.hoverBorderWidth, isHoverBorderWidthArray);
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
		  return getValue(Property.xAxisID, null);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public void setYAxisID(String yAxisID){
		  setValue(Property.yAxisID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public String getYAxisID(){
		  return getValue(Property.yAxisID, null);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(Position position){
		 setValue(Property.borderSkipped, position);
	}
	
	/**
	 * Returns the edge to skip drawing the border for.
	 * @return the edge to skip drawing the border for.
	 */
	public Position getBorderSkipped(){
		return getValue(Property.borderSkipped, Position.class, Position.top);
	}

}