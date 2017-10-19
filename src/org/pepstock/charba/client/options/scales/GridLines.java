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
package org.pepstock.charba.client.options.scales;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public class GridLines extends JavaScriptObjectContainer{
	
	private static final boolean DEFAULT_DISPLAY = true;
	
	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";
	
	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;
	
	private static final int DEFAULT_LINE_WIDTH = 1;
	
	private static final boolean DEFAULT_DRAW_BORDER = true;
	
	private static final boolean DEFAULT_DRAW_ON_CHART_AREA = true;
	
	private static final boolean DEFAULT_DRAW_TICKS = true;
	
	private static final int DEFAULT_TICK_MARK_LENGTH = 10;
	
	private static final int DEFAULT_ZERO_LINE_WIDTH = 1;
	
	private static final String DEFAULT_ZERO_LINE_COLOR = "rgba(0,0,0,0.25)";

	private static final int DEFAULT_ZERO_LINE_BORDER_DASH_OFFSET = 0;
	
	private static final boolean DEFAULT_OFFSET_GRID_LINES = false;
	
	private boolean isColorArray = false;
	
	private boolean isLineWidthArray = false;
	
	private enum Property implements Key {
		display,
		color,
		borderDash,
		borderDashOffset,
		lineWidth,
		drawBorder,
		drawOnChartArea,
		drawTicks,
		tickMarkLength,
		zeroLineWidth,
		zeroLineColor,
		zeroLineBorderDash,
		zeroLineBorderDashOffset,
		offsetGridLines
	}
	
//	display Boolean true If false, do not display grid lines for this axis.
//	color Color or Color[] 'rgba(0, 0, 0, 0.1)' The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
//	borderDash Number[] [] Length and spacing of dashes on grid lines. See MDN
//	borderDashOffset Number 0 Offset for line dashes. See MDN
//	lineWidth Number or Number[] 1 Stroke width of grid lines.
//	drawBorder Boolean true If true, draw border at the edge between the axis and the chart area.
//	drawOnChartArea Boolean true If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid lines are drawn.
//	drawTicks Boolean true If true, draw lines beside the ticks in the axis area beside the chart.
//	tickMarkLength Number 10 Length in pixels that the grid lines will draw into the axis area.
//	zeroLineWidth Number 1 Stroke width of the grid line for the first index (index 0).
//	zeroLineColor Color 'rgba(0, 0, 0, 0.25)' Stroke color of the grid line for the first index (index 0).
//	zeroLineBorderDash Number[] [] Length and spacing of dashes of the grid line for the first index (index 0). See MDN
//	zeroLineBorderDashOffset Number 0 Offset for line dashes of the grid line for the first index (index 0). See MDN
//	offsetGridLines Boolean false If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.

	GridLines() {
	}
	
	public void setDisplay(boolean display){
		  setValue(Property.display, display);
	}

	public boolean isDisplay(){
		  return getValue(Property.display, DEFAULT_DISPLAY);
	}

	public void setColor(String... color){
		setColor(ArrayListHelper.build(color));
	}

	private void setColor(JsStringArrayList color){
		isColorArray = checkAndSetStringValues(Property.color, color);
	}

	public List<String> getColor(){
		JsStringArrayList values = checkAndGetStringValues(Property.color, isColorArray);
		if (values.isEmpty()){
			values.add(DEFAULT_COLOR);
		}
		return values;
	}

	public void setBorderDash(int... borderDash){
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	private void setBorderDash(JsIntegerArrayList borderDash){
		  setIntegerArray(Property.borderDash, borderDash);
	}

	public List<Integer> getBorderDash(){
		  return getIntegerArray(Property.borderDash);
	}

	public void setBorderDashOffset(int borderDashOffset){
		  setValue(Property.borderDashOffset, borderDashOffset);
	}

	public int getBorderDashOffset(){
		  return getValue(Property.borderDashOffset, DEFAULT_BORDER_DASH_OFFSET);
	}

	public void setLineWidth(int... lineWidth){
		setLineWidth(ArrayListHelper.build(lineWidth));
	}

	private void setLineWidth(JsIntegerArrayList lineWidth){
		isLineWidthArray = checkAndSetIntegerValues(Property.lineWidth, lineWidth);
	}

	public List<Integer> getLineWidth(){
		JsIntegerArrayList values = checkAndGetIntegerValues(Property.lineWidth, isLineWidthArray);
		if (values.isEmpty()){
			values.add(DEFAULT_LINE_WIDTH);
		}
		return values;
	}

	public void setDrawBorder(boolean drawBorder){
		  setValue(Property.drawBorder, drawBorder);
	}

	public boolean isDrawBorder(){
		  return getValue(Property.drawBorder, DEFAULT_DRAW_BORDER);
	}

	public void setDrawOnChartArea(boolean drawOnChartArea){
		  setValue(Property.drawOnChartArea, drawOnChartArea);
	}

	public boolean isDrawOnChartArea(){
		  return getValue(Property.drawOnChartArea, DEFAULT_DRAW_ON_CHART_AREA);
	}

	public void setDrawTicks(boolean drawTicks){
		  setValue(Property.drawTicks, drawTicks);
	}

	public boolean isDrawTicks(){
		  return getValue(Property.drawTicks, DEFAULT_DRAW_TICKS);
	}

	public void setTickMarkLength(int tickMarkLength){
		  setValue(Property.tickMarkLength, tickMarkLength);
	}

	public int getTickMarkLength(){
		  return getValue(Property.tickMarkLength, DEFAULT_TICK_MARK_LENGTH);
	}

	public void setZeroLineWidth(int zeroLineWidth){
		  setValue(Property.zeroLineWidth, zeroLineWidth);
	}

	public int getZeroLineWidth(){
		  return getValue(Property.zeroLineWidth, DEFAULT_ZERO_LINE_WIDTH);
	}

	public void setZeroLineColor(String zeroLineColor){
		  setValue(Property.zeroLineColor, zeroLineColor);
	}

	public String getZeroLineColor(){
		  return getValue(Property.zeroLineColor, DEFAULT_ZERO_LINE_COLOR);
	}

	public void setZeroLineBorderDash(int... zeroLineBorderDash){
		setZeroLineBorderDash(ArrayListHelper.build(zeroLineBorderDash));
	}

	private void setZeroLineBorderDash(JsIntegerArrayList zeroLineBorderDash){
		  setIntegerArray(Property.zeroLineBorderDash, zeroLineBorderDash);
	}

	public List<Integer> getZeroLineBorderDash(){
		  return getIntegerArray(Property.zeroLineBorderDash);
	}

	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset){
		  setValue(Property.zeroLineBorderDashOffset, zeroLineBorderDashOffset);
	}

	public int getZeroLineBorderDashOffset(){
		  return getValue(Property.zeroLineBorderDashOffset, DEFAULT_ZERO_LINE_BORDER_DASH_OFFSET);
	}

	public void setOffsetGridLines(boolean offsetGridLines){
		  setValue(Property.offsetGridLines, offsetGridLines);
	}

	public boolean isOffsetGridLines(){
		  return getValue(Property.offsetGridLines, DEFAULT_OFFSET_GRID_LINES);
	}

}