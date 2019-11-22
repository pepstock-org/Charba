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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.impl.plugins.HtmlLegendItem;
import org.pepstock.charba.client.items.LegendItem;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;

/**
 * Implements a shape drawer in order to use {@link PointStyle} as shape for tiles.<br>
 * Extends the usage of shape drawer for draw point styles for {@link HtmlLegend} plugin.<br>
 * Implements the drawing of point styles using CHART.JS starting point.<br>
 * See <a href="https://github.com/chartjs/Chart.js/blob/master/src/helpers/helpers.canvas.js">here</a>, at method
 * <code>drawPoint</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractPointStyleShapeDrawer extends ShapeDrawer {

	// Default rotation, <b>{@value DEFAULT_ROTATIOn}</b>.
	private static final double DEFAULT_ROTATION = Defaults.get().getGlobal().getElements().getPoint().getRotation();
	// constants used to draw the point styles got from CHART.JS code
	protected static final double PI = Math.PI;
	protected static final double RAD_PER_DEG = PI / 180;
	protected static final double DOUBLE_PI = PI * 2;
	protected static final double HALF_PI = PI / 2;
	protected static final double QUARTER_PI = PI / 4;
	protected static final double TWO_THIRDS_PI = PI * 2 / 3;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(com.google.gwt.canvas.dom.client.Context2d,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected final void drawTile(Context2d context, String backgroundColor, String shapeColor, int size) {
		// gets all needed variables
		double rotation = DEFAULT_ROTATION;
		double radius = size / 2D;
		double rad = rotation * RAD_PER_DEG;
		double x = size / 2D;
		double y = size / 2D;
		// applies stroke and fill properties to canvas
		applyStrokeProperties(context, shapeColor, size);
		applyFillProperties(context, backgroundColor);
		// closes the path
		context.beginPath();
		// invokes the drawing
		drawPointStyle(context, size, x, y, radius, rotation, rad);
		// closes the path
		context.closePath();
		// apply fill and stroke
		context.fill();
		context.stroke();
	}

	/**
	 * Draws the point style for {@link HtmlLegend} plugin.
	 * 
	 * @param outerCanvas canvas reference of tiles factory.
	 * @param htmlLegendItem legend item with all attributes to draw the canvas
	 * @return a data URL for the current content of the canvas element
	 */
	final String drawTile(CanvasElement outerCanvas, HtmlLegendItem htmlLegendItem) {
		// gets all variables needed for drawing
		double radius = htmlLegendItem.getRadius();
		int size = htmlLegendItem.getSize();
		double rotation = htmlLegendItem.getLegendItem().getRotation();
		rotation = Double.isNaN(rotation) ? DEFAULT_ROTATION : Math.max(rotation, 0D) == 0D ? DEFAULT_ROTATION : rotation;
		double rad = rotation * RAD_PER_DEG;
		double x = size / 2D;
		double y = size / 2D;
		// gets the initialized canvas element
		CanvasElement canvas = initCanvas(outerCanvas, size);
		// gets the context
		Context2d context = canvas.getContext2d();
		// clears the canvas for new design
		// sets the TRANSPARENT background color
		context.setFillStyle(HtmlColor.TRANSPARENT.toRGBA());
		context.fillRect(0D, 0D, size, size);
		// applies stroke and fill properties to canvas
		// by legend item
		applyStrokeProperties(context, size, htmlLegendItem.getLegendItem());
		applyFillProperties(context, htmlLegendItem.getLegendItem());
		// closes the path
		context.beginPath();
		// invokes the drawing
		drawPointStyle(context, size, x, y, radius, rotation, rad);
		// closes the path
		context.closePath();
		// apply fill and stroke
		context.fill();
		context.stroke();
		// returns canvas data
		return canvas.toDataUrl();
	}

	/**
	 * Draws the tile using the point style applying the requested shape.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param x x point center of canvas
	 * @param y y point center of canvas
	 * @param radius the radius of the tile, only for {@link PointStyle} drawing
	 * @param rotation the rotation (in degrees) to create the tile, only for {@link PointStyle} drawing
	 * @param rad rad calculates on rotation
	 */
	protected abstract void drawPointStyle(Context2d context, int size, double x, double y, double radius, double rotation, double rad);

	/**
	 * Applies the common configuration to context for stroke designing, by a {@link LegendItem}.<br>
	 * Used only for {@link PointStyle} drawing.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param legendItem the legend item instance to create the tile
	 */
	private void applyStrokeProperties(Context2d context, int size, LegendItem legendItem) {
		// checks which kind of fill has been set
		if (legendItem.isFillStyleAsColor()) {
			// applies the fill color
			context.setStrokeStyle(legendItem.getFillStyle().toRGBA());
		} else if (legendItem.isFillStyleAsCanvasPattern()) {
			// applies the fill pattern
			context.setStrokeStyle(legendItem.getFillStyleAsCanvasPattern());
		} else if (legendItem.isFillStyleAsCanvasGradient()) {
			// applies the fill gradient
			context.setStrokeStyle(legendItem.getFillStyleAsCanvasGradient());
		}
		double lineWidth = Math.min(legendItem.getLineWidth(), size / 4);
		// line width is by default tenth size
		context.setLineWidth(lineWidth);
		// sets line cap and join
		context.setLineJoin(legendItem.getLineJoin().getLineJoin());
		context.setLineCap(legendItem.getLineCap().getLineCap());
		// sets line dash
		JsHelper.get().setLineDash(context, legendItem.getLineDash());
	}

	/**
	 * Applies the common configuration to context for fill designing by a {@link LegendItem}.<br>
	 * Used only for {@link PointStyle} drawing.
	 * 
	 * @param context context of canvas to design the shape
	 * @param legendItem the legend item instance to create the tile
	 */
	private void applyFillProperties(Context2d context, LegendItem legendItem) {
		// checks which kind of fill has been set
		if (legendItem.isFillStyleAsColor()) {
			// applies the fill color
			context.setFillStyle(legendItem.getFillStyle().toRGBA());
		} else if (legendItem.isFillStyleAsCanvasPattern()) {
			// applies the fill pattern
			context.setFillStyle(legendItem.getFillStyleAsCanvasPattern());
		} else if (legendItem.isFillStyleAsCanvasGradient()) {
			// applies the fill gradient
			context.setFillStyle(legendItem.getFillStyleAsCanvasGradient());
		}
	}

}
