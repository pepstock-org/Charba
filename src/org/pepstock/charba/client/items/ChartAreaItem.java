package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;

public class ChartAreaItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		top,
		right,
		bottom,
		left
	}
	
 	/**
	 * Needed for GWt injection
	 */
	protected ChartAreaItem() {
		// do nothing
	}
	
	public final int getTop(){
		return getInt(Property.top.name());
	}
	
	public final int getRight(){
		return getInt(Property.right.name());
	}

	public final int getBottom(){
		return getInt(Property.bottom.name());
	}

	public final int getLeft(){
		return getInt(Property.left.name());
	}

}