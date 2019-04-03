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

import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * This is a shape which can draw an image on the tile.<br>
 * This object is also a shape drawer.<br>
 * The image is scaled to the size of the tile. It designs an image into the following tile sections (only A):<br>
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
public final class ImageShape extends ShapeDrawer implements IsShape {

	/**
	 * Name of shape to draw an image, <b>{@value NAME}</b>.
	 */
	public static final String NAME = "image";
	// image to draw on the tile
	private final ImageElement imageElement;
	// key prefix
	private final String keyPrefix;

	/**
	 * Creates a shape with an image.
	 * 
	 * @param image image to draw on tile
	 */
	public ImageShape(ImageResource image) {
		this(Utilities.toImageElement(image));
	}

	/**
	 * Creates a shape with an image.
	 * 
	 * @param image image to draw on tile
	 */
	public ImageShape(Image image) {
		this(Utilities.toImageElement(image));
	}

	/**
	 * Creates a shape with an image.
	 * 
	 * @param image image to draw on tile
	 */
	public ImageShape(ImageElement image) {
		// checks if image is not consistent
		if (image != null) {
			// stores the image
			this.imageElement = image;
			// key prefix for caching is NAME plus source of image
			this.keyPrefix = NAME+imageElement.getSrc();
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException("Image instance is not consitent or null!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#name()
	 */
	@Override
	public String name() {
		return NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getDrawer()
	 */
	@Override
	public ShapeDrawer getDrawer() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getKeyPrefix()
	 */
	@Override
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(com.google.gwt.canvas.dom.client.Context2d,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2d context, String backgroundColor, String shapeColor, int size) {
		// 1 pox of margin for all dimensions
		final double realSize = size - 2;
		// draws a scaled image
		context.drawImage(imageElement, 1, 1, realSize, realSize);
	}

}
