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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.enums.PointStyle;

import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * Comfortable object to create tiles (as CHARBA pattern or canvas pattern) by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TilesBuilder {

	// fields used to create a tile
	private IsShape shape = null;

	private String backgroundColor = null;

	String shapeColor = null;

	int size = Integer.MIN_VALUE;

	/**
	 * To avoid any instantiation
	 */
	private TilesBuilder() {
		// sets to all filed the defaults
		// as starting value
		shape = TilesFactory.getDefaults().getShape();
		backgroundColor = TilesFactory.getDefaults().getBackgroundColorAsString();
		shapeColor = TilesFactory.getDefaults().getShapeColorAsString();
		size = TilesFactory.getDefaults().getSize();
	}

	/**
	 * Returns new tiles builder instance.
	 * 
	 * @return tiles builder instance
	 */
	public static TilesBuilder create() {
		return new TilesBuilder();
	}

	/**
	 * Sets the shape of the tile.
	 * 
	 * @param shape the shape of tile
	 * @return tiles builder instance
	 */
	public TilesBuilder setShape(IsShape shape) {
		this.shape = shape == null ? TilesFactory.getDefaults().getShape() : shape;
		return this;
	}

	/**
	 * Sets the point style as shape of the tile.
	 * 
	 * @param pointStyle the point style as shape of the tile
	 * @return tiles builder instance
	 */
	public TilesBuilder setPointStyle(PointStyle pointStyle) {
		this.shape = PointStyleShape.get(pointStyle);
		return this;
	}

	/**
	 * Sets the size of the tile, minimum size is the default size.
	 * 
	 * @param size the size to set
	 * @return tiles builder instance
	 */
	public TilesBuilder setSize(int size) {
		this.size = Math.max(size, TilesFactory.getDefaults().getSize());
		return this;
	}

	/**
	 * Sets the background color of tile as string.
	 * 
	 * @param backgroundColor the background color of tile as string
	 * @return tiles builder instance
	 */
	public TilesBuilder setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor == null ? TilesFactory.getDefaults().getBackgroundColorAsString() : backgroundColor;
		return this;
	}

	/**
	 * Sets the background color of tile.
	 * 
	 * @param backgroundColor the background color of tile
	 * @return tiles builder instance
	 */
	public TilesBuilder setBackgroundColor(IsColor backgroundColor) {
		this.backgroundColor = IsColor.isConsistent(backgroundColor) ? backgroundColor.toRGBA() : TilesFactory.getDefaults().getBackgroundColorAsString();
		return this;
	}

	/**
	 * Sets the shape color of tile.
	 * 
	 * @param shapeColor the shape color of tile
	 * @return tiles builder instance
	 */
	public TilesBuilder setShapeColor(String shapeColor) {
		this.shapeColor = shapeColor == null ? TilesFactory.getDefaults().getShapeColorAsString() : shapeColor;
		return this;
	}

	/**
	 * Sets the shape color of tile.
	 * 
	 * @param shapeColor the shape color of tile
	 * @return tiles builder instance
	 */
	public TilesBuilder setShapeColor(IsColor shapeColor) {
		this.shapeColor = IsColor.isConsistent(shapeColor) ? shapeColor.toRGBA() : TilesFactory.getDefaults().getShapeColorAsString();
		return this;
	}

	/**
	 * Returns a canvas pattern, using the shape, background color, shape color and size.
	 * 
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern asTile() {
		return TilesFactory.createTile(shape, backgroundColor, shapeColor, size);
	}

	/**
	 * Returns a CHARBA pattern, using the shape, background color, shape color and size.
	 * 
	 * @return a tile as CHARBA pattern
	 */
	public Pattern asPattern() {
		return TilesFactory.createPattern(shape, backgroundColor, shapeColor, size);
	}
}
