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

import org.pepstock.charba.client.dom.elements.Context2dItem;

/**
 * STAR drawer to design a star (empty) into tile.<br>
 * It designs a star into the following tile sections (only A):<br>
 * <br>
 * 
 * <pre>
 * +-------------------+
 * |                   |
 * |                   |
 * |                   |
 * |         A         |
 * |                   |
 * |                   |
 * |                   |
 * +-------------------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class EmptyStar extends ShapeDrawer {

	// number of spikes of star
	static final int SPIKES = 5;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates quarter dimension
		final double quarterSize = size / 4D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// override line width to 1
		context.setLineWidth(1D);
		// applies a translation to the current transform
		context.translate(size, size);
		// applies rotation (90 degrees) to the current transform
		context.rotate(ROTATION_180_DEGREES);
		// designs the shape into A section
		drawStar(context, halfSize, halfSize, SPIKES, halfSize - 2D, quarterSize - 1D);
		// strokes the current path
		context.stroke();
	}

	/**
	 * Designs a star into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param offsetX offset X where starts drawing
	 * @param offsetY offset Y where starts drawing
	 * @param spikes number of spikes of star
	 * @param innerRadius inner radius of star
	 * @param outerRadius outer radius of star
	 */
	final void drawStar(Context2dItem context, double offsetX, double offsetY, int spikes, double innerRadius, double outerRadius) {
		// calculates quarter dimension
		double rot = Math.PI / 2D * 3D;
		// calculates the center X of arc
		double x;
		// calculates the center Y of arc
		double y;
		// calculates of step
		final double step = Math.PI / spikes;
		// draws shape
		// scans spikes
		for (int i = 0; i < spikes; i++) {
			// draws spike first line
			x = offsetX + Math.cos(rot) * outerRadius;
			y = offsetY + Math.sin(rot) * outerRadius;
			context.lineTo(x, y);
			rot += step;
			// draws spike second line
			x = offsetX + Math.cos(rot) * innerRadius;
			y = offsetY + Math.sin(rot) * innerRadius;
			context.lineTo(x, y);
			rot += step;
		}
		// last line of last spkie
		context.lineTo(offsetX, offsetY - outerRadius);
	}

}
