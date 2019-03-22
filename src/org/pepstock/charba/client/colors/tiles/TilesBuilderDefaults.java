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
package org.pepstock.charba.client.colors.tiles;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;

import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

/**
 * Contains the defaults configuration of tile builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TilesBuilderDefaults {

	/**
	 * Default background color, {@value DEFAULT_BACKGROUND_COLOR}
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = "rgba(100, 100, 100, 0.7)";

	/**
	 * Default shape color, {@value DEFAULT_SHAPE_COLOR}
	 */
	public static final String DEFAULT_SHAPE_COLOR = "rgba(255, 255, 255, 0.8)";

	/**
	 * Default image size, 20
	 */
	public static final int DEFAULT_SIZE = 20;

	/**
	 * Default to determine the shape used to draw the end points of lines, {@link LineCap#ROUND}.
	 */
	public static final LineCap DEFAULT_LINE_CAP = LineCap.ROUND;

	/**
	 * Default the shape, {@link Shape#square}.
	 */
	public static final Shape DEFAULT_SHAPE = Shape.square;

	/**
	 * Default to determine the shape used to join two line segments where they meet, {@link LineJoin#ROUND}.
	 */
	public static final LineJoin DEFAULT_LINE_JOIN = LineJoin.ROUND;

	private Shape shape = DEFAULT_SHAPE;

	private int size = DEFAULT_SIZE;

	private String backgroundColor = DEFAULT_BACKGROUND_COLOR;

	private String shapeColor = DEFAULT_SHAPE_COLOR;

	private LineCap lineCap = DEFAULT_LINE_CAP;

	private LineJoin lineJoin = DEFAULT_LINE_JOIN;

	/**
	 * To avoid any external implementation
	 */
	TilesBuilderDefaults() {
		// do nothing
	}

	/**
	 * Returns the shape of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the shape of tile. If <code>null</code>, returns the default one
	 */
	public final Shape getShape() {
		return shape;
	}

	/**
	 * Sets the shape of the tile.
	 * 
	 * @param shape the shape of tile
	 */
	public final void setShape(Shape shape) {
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
	public final void setSize(int size) {
		this.size = Math.max(size, DEFAULT_SIZE);
	}

	/**
	 * Returns the background color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the background color of tile. If <code>null</code>, returns the default one
	 */
	public final String getBackgroundColorAsString() {
		return backgroundColor;
	}

	/**
	 * Returns the background color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the background color of tile. If <code>null</code>, returns the default one
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the background color of tile as string.
	 * 
	 * @param backgroundColor the background color of tile as string
	 */
	public final void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor == null ? DEFAULT_BACKGROUND_COLOR : backgroundColor;
	}

	/**
	 * Sets the background color of tile.
	 * 
	 * @param backgroundColor the background color of tile
	 */
	public final void setBackgroundColor(IsColor backgroundColor) {
		this.backgroundColor = backgroundColor == null ? DEFAULT_BACKGROUND_COLOR : backgroundColor.toRGBA();
	}

	/**
	 * Returns the shape color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the shape color of tile. If <code>null</code>, returns the default one
	 */
	public final String getShapeColorAsString() {
		return shapeColor;
	}

	/**
	 * Returns the shape color of tile. If <code>null</code>, returns the default one.
	 * 
	 * @return the shape color of tile. If <code>null</code>, returns the default one
	 */
	public final IsColor getShapeColor() {
		return ColorBuilder.parse(getShapeColorAsString());
	}

	/**
	 * Sets the shape color of tile.
	 * 
	 * @param shapeColor the shape color of tile
	 */
	public final void setShapeColor(String shapeColor) {
		this.shapeColor = shapeColor == null ? DEFAULT_SHAPE_COLOR : shapeColor;
	}

	/**
	 * Sets the shape color of tile.
	 * 
	 * @param shapeColor the shape color of tile
	 */
	public final void setShapeColor(IsColor shapeColor) {
		this.shapeColor = shapeColor == null ? DEFAULT_SHAPE_COLOR : shapeColor.toRGBA();
	}

	/**
	 * Returns the lineCap, to determine the shape used to draw the end points of lines. If <code>null</code>, returns the
	 * default one.
	 * 
	 * @return lineCap the lineCap to determine the shape used to draw the end points of lines. If <code>null</code>, returns
	 *         the default one
	 */
	public final LineCap getLineCap() {
		return lineCap;
	}

	/**
	 * Sets the lineCap, to determine the shape used to draw the end points of lines.
	 * 
	 * @param lineCap the lineCap to determine the shape used to draw the end points of lines
	 */
	public final void setLineCap(LineCap lineCap) {
		this.lineCap = lineCap == null ? DEFAULT_LINE_CAP : lineCap;
	}

	/**
	 * Returns the lineJoin, to determine the shape used to join two line segments where they meet. If <code>null</code>,
	 * returns the default one.
	 * 
	 * @return lineJoin the lineJoin to determine the shape used to join two line segments where they meet. If
	 *         <code>null</code>, returns the default one
	 */
	public final LineJoin getLineJoin() {
		return lineJoin;
	}

	/**
	 * Sets the lineJoin, to determine the shape used to join two line segments where they meet.
	 * 
	 * @param lineJoin the lineJoin, to determine the shape used to join two line segments where they meet.
	 */
	public final void setLineJoin(LineJoin lineJoin) {
		this.lineJoin = lineJoin;
	}

}
