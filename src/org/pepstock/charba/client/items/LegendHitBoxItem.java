package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.enums.Position;

public final class LegendHitBoxItem extends SizeItem{
	
	LegendHitBoxItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	public final int getLeft() {
		return getValue(Position.left, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	public final int getTop() {
		return getValue(Position.top, UndefinedValues.INTEGER);
	}

}
