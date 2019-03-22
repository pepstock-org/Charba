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

import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;

/**
 * Base class for all shape drawer, classes which must designer on canvas the shape.<br>
 * A tile is always a square and for some shapes is divided in 4 sections and not all sections are designed.<br>
 * A tile is designed as following:<br>
 * 
 * <pre>
 * +---------+---------+
 * |         |         |
 * |         |         |
 * |         |         |
 * +---------+---------+
 * |         |         |
 * |         |         |
 * |         |         |
 * +---------+---------+
 * </pre>
 * 
 * Depending on shape, the parts can be designed or not.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class ShapeDrawer {

	// rotation of 90 degrees to apply on rotation
	static final double ROTATION_90_DEGREES = 90 * Math.PI / 180;
	// rotation of 180 degrees to apply on rotation
	static final double ROTATION_180_DEGREES = 180 * Math.PI / 180;
	// canvas element to draw
	private final CanvasElement canvas = Document.get().createCanvasElement();

	/**
	 * Creates a tile based on arguments passed. This is the entry point of every drawer.
	 * 
	 * @param canvas canvas where the drawer must design the shape
	 * @param backgroundColor background of tile
	 * @param shapeColor color of shape
	 * @param size the size of tile, which is a square
	 * @return a canvas pattern which represents a tile.
	 */
	final CanvasPattern createTile(CanvasElement outerCanvas, String backgroundColor, String shapeColor, int size) {
		// changes dimensions of outer canvas
		outerCanvas.setWidth(size);
		outerCanvas.setHeight(size);
		// sets dimensions of canvas, always a square
		canvas.setWidth(size);
		canvas.setHeight(size);
		// gets the context
		Context2d context = canvas.getContext2d();
		// clears the canvas for new design
		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// sets the background color
		context.setFillStyle(backgroundColor);
		context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// begins a new path.
		context.beginPath();
		// invoke the drawer to design the shape
		drawTile(canvas.getContext2d(), backgroundColor, shapeColor, size);
		// closes the path
		context.closePath();
		// transforms the canvas into a pattern
		return outerCanvas.getContext2d().createPattern(canvas, Context2d.Repetition.REPEAT);
	}

	/**
	 * Draws the tile applying the requested shape.
	 * 
	 * @param context context of canvas to design the shape
	 * @param backgroundColor background of tile
	 * @param shapeColor color of shape
	 * @param size the size of tile, which is a square
	 */
	abstract void drawTile(Context2d context, String backgroundColor, String shapeColor, int size);

	/**
	 * Applies the common configuration to context for stroke designing.
	 * 
	 * @param context context of canvas to design the shape
	 * @param shapeColor color of shape
	 * @param size the size of tile, which is a square
	 */
	protected final void applyStrokeProperties(Context2d context, String shapeColor, int size) {
		// applies the stroke color
		context.setStrokeStyle(shapeColor);
		// line width is by default tenth size
		context.setLineWidth(size / 10D);
		// sets line cap and join
		context.setLineJoin(TilesBuilder.get().getDefaults().getLineJoin());
		context.setLineCap(TilesBuilder.get().getDefaults().getLineCap());
	}

	/**
	 * Applies the common configuration to context for fill designing.
	 * 
	 * @param context context of canvas to design the shape
	 * @param shapeColor color of shape
	 */
	protected final void applyFillProperties(Context2d context, String shapeColor) {
		// applies the stroke color
		context.setFillStyle(shapeColor);
	}

}
