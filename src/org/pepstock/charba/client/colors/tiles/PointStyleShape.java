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
import org.pepstock.charba.client.enums.PointStyle;

/**
 * Enumerates all available shapes applicable onto a tile by a {@link PointStyle}.
 * 
 * @author Andrea "Stock" Stocchero
 */
enum PointStyleShape implements IsShape
{
	/**
	 * Shape instance related to {@link PointStyle#CIRCLE}.
	 */
	CIRCLE(PointStyle.CIRCLE, new PointStyleCircle()),
	/**
	 * Shape instance related to {@link PointStyle#CROSS}.
	 */
	CROSS(PointStyle.CROSS, new PointStyleCross()),
	/**
	 * Shape instance related to {@link PointStyle#CROSS_ROT}.
	 */
	CROSS_ROT(PointStyle.CROSS_ROT, new PointStyleCrossRot()),
	/**
	 * Shape instance related to {@link PointStyle#DASH}.
	 */
	DASH(PointStyle.DASH, new PointStyleDash()),
	/**
	 * Shape instance related to {@link PointStyle#LINE}.
	 */
	LINE(PointStyle.LINE, new PointStyleLine()),
	/**
	 * Shape instance related to {@link PointStyle#RECT}.
	 */
	RECT(PointStyle.RECT, new PointStyleRect()),
	/**
	 * Shape instance related to {@link PointStyle#RECT_ROUNDED}.
	 */
	RECT_ROUNDED(PointStyle.RECT_ROUNDED, new PointStyleRectRounded()),
	/**
	 * Shape instance related to {@link PointStyle#RECT_ROT}.
	 */
	RECT_ROT(PointStyle.RECT_ROT, new PointStyleRectRot()),
	/**
	 * Shape instance related to {@link PointStyle#STAR}.
	 */
	STAR(PointStyle.STAR, new PointStyleStar()),
	/**
	 * Shape instance related to {@link PointStyle#TRIANGLE}.
	 */
	TRIANGLE(PointStyle.TRIANGLE, new PointStyleTriangle());

	// prefix of all enums
	private static final String KEY_PREFIX = "POINT_STYLE_";

	// instance of point style
	private final PointStyle pointStyle;
	// instance of shape drawer
	private final ShapeDrawer drawer;
	// instance of key prefix
	private final String keyPrefix;

	/**
	 * Creates a shaper with own drawer and the related point style instance to draw.
	 * 
	 * @param pointStyle point style instance
	 * @param the shape drawer instance
	 */
	private PointStyleShape(PointStyle pointStyle, ShapeDrawer drawer) {
		this.pointStyle = pointStyle;
		this.drawer = drawer;
		this.keyPrefix = KEY_PREFIX + name();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return pointStyle.value();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getKeyPrefix()
	 */
	@Override
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getDrawer()
	 */
	@Override
	public ShapeDrawer getDrawer() {
		return drawer;
	}

	/**
	 * Returns the point style instance which must be represents by this shape.
	 * 
	 * @return the point style instance.
	 */
	public PointStyle getPointStyle() {
		return pointStyle;
	}

	/**
	 * Returns a shape looking from the passed point style instance.
	 * 
	 * @param pointStyle point style instance to use to get the related shape.
	 * @return the related shape to poitn style
	 */
	static PointStyleShape get(PointStyle pointStyle) {
		// gets temp instance
		PointStyle changedPointStyle = null;
		// checks if argument is consistent
		if (pointStyle != null) {
			// stores the argument because consistent
			changedPointStyle = pointStyle;
		} else {
			// argument not consistent
			// then gets the default point style
			changedPointStyle = Defaults.get().getGlobal().getElements().getPoint().getPointStyle();
		}
		// scans the shapes
		for (PointStyleShape shape : values()) {
			// checks if the equals to poitn style
			if (shape.getPointStyle().equals(changedPointStyle)) {
				return shape;
			}
		}
		// it should not happen anytime
		// if here means that the enum of point style is not aligned with this one.
		return PointStyleShape.CIRCLE;
	}

}