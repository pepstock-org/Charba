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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.Img;

/**
 * This is a shape which can draw an image on the tile.<br>
 * This object is also a shape drawer.<br>
 * The image is scaled to the size of the tile. It designs an image in the the following tile sections (only A):<br>
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
public final class ImageShape extends AbstractShape {

	/**
	 * Name of shape to draw an image, <b>{@value IMAGE_SHAPE_NAME}</b>.
	 */
	public static final String IMAGE_SHAPE_NAME = "image";
	// image to draw on the tile
	private final Img imageElement;

	/**
	 * Creates a shape with an image.
	 * 
	 * @param image image to draw on tile
	 */
	public ImageShape(Img image) {
		super(IMAGE_SHAPE_NAME);
		// checks if image is not consistent
		Checker.checkIfValid(image, "Image argument");
		// stores the image
		this.imageElement = image;
		// key prefix for caching is NAME plus source of image
		super.setKeyPrefix(IMAGE_SHAPE_NAME + imageElement.getSrc());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem, java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// 1 pox of margin for all dimensions
		final double realSize = size - 2D;
		// draws a scaled image
		context.drawImage(imageElement, 1D, 1D, realSize, realSize);
	}

}
