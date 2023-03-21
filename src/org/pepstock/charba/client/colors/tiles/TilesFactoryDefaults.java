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
package org.pepstock.charba.client.colors.tiles;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Contains the defaults configuration of tile builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TilesFactoryDefaults {

	/**
	 * Default background color as string, <b>{@value DEFAULT_BACKGROUND_COLOR_AS_STRING}</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = "rgba(100, 100, 100, 0.7)";

	/**
	 * Default shape color as string, <b>{@value DEFAULT_SHAPE_COLOR_AS_STRING}</b>.
	 */
	public static final String DEFAULT_SHAPE_COLOR_AS_STRING = "rgba(255, 255, 255, 0.8)";

	/**
	 * Default background color, <b>{@value DEFAULT_BACKGROUND_COLOR_AS_STRING}</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = ColorBuilder.parse(DEFAULT_BACKGROUND_COLOR_AS_STRING);

	/**
	 * Default shape color, <b>{@value DEFAULT_SHAPE_COLOR_AS_STRING}</b>.
	 */
	public static final IsColor DEFAULT_SHAPE_COLOR = ColorBuilder.parse(DEFAULT_SHAPE_COLOR_AS_STRING);

	/**
	 * Default tile size, <b>{@value DEFAULT_SIZE}</b>.
	 */
	public static final int DEFAULT_SIZE = 20;

	// minimum size
	static final int MINIMUM_SIZE = 10;

	/**
	 * Default to determine the shape used to draw the end points of lines, {@link CapStyle#ROUND}.
	 */
	public static final CapStyle DEFAULT_LINE_CAP = CapStyle.ROUND;

	/**
	 * Default the shape, {@link Shape#SQUARE}.
	 */
	public static final IsShape DEFAULT_SHAPE = Shape.SQUARE;

	/**
	 * Default to determine the shape used to join two line segments where they meet, {@link JoinStyle#ROUND}.
	 */
	public static final JoinStyle DEFAULT_LINE_JOIN = JoinStyle.ROUND;

	private IsShape shape = DEFAULT_SHAPE;

	private int size = DEFAULT_SIZE;

	private String backgroundColor = DEFAULT_BACKGROUND_COLOR_AS_STRING;

	private String shapeColor = DEFAULT_SHAPE_COLOR_AS_STRING;

	private CapStyle lineCap = DEFAULT_LINE_CAP;

	private JoinStyle lineJoin = DEFAULT_LINE_JOIN;

	/**
	 * To avoid any external implementation
	 */
	TilesFactoryDefaults() {
		// do nothing
	}

	/**
	 * Returns the shape of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the shape of tile. If <code>null</code>, returns the default one
	 */
	public IsShape getShape() {
		return shape;
	}

	/**
	 * Sets the shape of the tile.
	 * 
	 * @param shape the shape of tile
	 */
	public void setShape(IsShape shape) {
		this.shape = shape == null ? DEFAULT_SHAPE : shape;
	}

	/**
	 * Returns the size of the tile.
	 * 
	 * @return the size of the tile
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * Sets the size of the tile, minimum size is the default size.
	 * 
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = Math.max(size, MINIMUM_SIZE);
	}

	/**
	 * Returns the background color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the background color of tile. If <code>null</code>, returns the default one
	 */
	public String getBackgroundColorAsString() {
		return backgroundColor;
	}

	/**
	 * Returns the background color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the background color of tile. If <code>null</code>, returns the default one
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the background color of tile as string.
	 * 
	 * @param backgroundColor the background color of tile as string
	 */
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor == null ? DEFAULT_BACKGROUND_COLOR_AS_STRING : backgroundColor;
	}

	/**
	 * Sets the background color of tile.
	 * 
	 * @param backgroundColor the background color of tile
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		this.backgroundColor = IsColor.isConsistent(backgroundColor) ? backgroundColor.toRGBA() : DEFAULT_BACKGROUND_COLOR_AS_STRING;
	}

	/**
	 * Returns the shape color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the shape color of tile. If <code>null</code>, returns the default one
	 */
	public String getShapeColorAsString() {
		return shapeColor;
	}

	/**
	 * Returns the shape color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the shape color of tile. If <code>null</code>, returns the default one
	 */
	public IsColor getShapeColor() {
		return ColorBuilder.parse(getShapeColorAsString());
	}

	/**
	 * Sets the shape color of tile.
	 * 
	 * @param shapeColor the shape color of tile
	 */
	public void setShapeColor(String shapeColor) {
		this.shapeColor = shapeColor == null ? DEFAULT_SHAPE_COLOR_AS_STRING : shapeColor;
	}

	/**
	 * Sets the shape color of tile.
	 * 
	 * @param shapeColor the shape color of tile
	 */
	public void setShapeColor(IsColor shapeColor) {
		this.shapeColor = IsColor.isConsistent(shapeColor) ? shapeColor.toRGBA() : DEFAULT_SHAPE_COLOR_AS_STRING;
	}

	/**
	 * Returns the lineCap, to determine the shape used to draw the end points of lines. If <code>null</code>, returns the default one.
	 * 
	 * @return lineCap the lineCap to determine the shape used to draw the end points of lines. If <code>null</code>, returns the default one
	 */
	public CapStyle getLineCap() {
		return lineCap;
	}

	/**
	 * Sets the lineCap, to determine the shape used to draw the end points of lines.
	 * 
	 * @param lineCap the lineCap to determine the shape used to draw the end points of lines
	 */
	public void setLineCap(CapStyle lineCap) {
		this.lineCap = lineCap == null ? DEFAULT_LINE_CAP : lineCap;
	}

	/**
	 * Returns the lineJoin, to determine the shape used to join two line segments where they meet. If <code>null</code>, returns the default one.
	 * 
	 * @return lineJoin the lineJoin to determine the shape used to join two line segments where they meet. If <code>null</code>, returns the default one
	 */
	public JoinStyle getLineJoin() {
		return lineJoin;
	}

	/**
	 * Sets the lineJoin, to determine the shape used to join two line segments where they meet.
	 * 
	 * @param lineJoin the lineJoin, to determine the shape used to join two line segments where they meet.
	 */
	public void setLineJoin(JoinStyle lineJoin) {
		this.lineJoin = lineJoin;
	}

}