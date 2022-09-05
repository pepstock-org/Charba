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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.ArcBorderRadius;
import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents arcs on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class ArcElementOptions extends CommonElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// ARC element
		BORDER_ALIGN("borderAlign"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		BORDER_RADIUS("borderRadius"),
		CIRCULAR("circular"),
		WEIGHT("weight"),
		ANGLE("angle"),
		OFFSET("offset");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ArcElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return Defaults.get().getGlobal().getElements().getArc().getBorderWidth();
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getValue(Property.BORDER_ALIGN, BorderAlign.values(), Defaults.get().getGlobal().getElements().getArc().getBorderAlign());
	}

	/**
	 * Returns the relative thickness of the dataset.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getValue(Property.WEIGHT, Defaults.get().getGlobal().getElements().getArc().getWeight());
	}

	/**
	 * Returns the arc angle to cover.
	 * 
	 * @return the arc angle to cover
	 */
	public double getAngle() {
		return getValue(Property.ANGLE, Defaults.get().getGlobal().getElements().getArc().getAngle());
	}

	/**
	 * Returns the arc offset (in pixels).
	 * 
	 * @return the arc offset
	 */
	public int getOffset() {
		return getValue(Property.OFFSET, Defaults.get().getGlobal().getElements().getArc().getOffset());
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), Defaults.get().getGlobal().getElements().getArc().getBorderJoinStyle());
	}

	/**
	 * Returns the list of arc border radius (in pixels).<br>
	 * If a callback has been set, returns an empty list.
	 * 
	 * @return the list of arc border radius (in pixels).<br>
	 *         If a callback has been set, returns an empty list
	 */
	public int getBorderRadius() {
		// checks if value stored as integer
		if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			// gets value as integer
			return getValue(Property.BORDER_RADIUS, Defaults.get().getGlobal().getElements().getArc().getBorderRadius());
		}
		// if here, it's not a number
		return Undefined.INTEGER;
	}

	/**
	 * Returns the list of arc border radius objects.<br>
	 * If a callback or an array have been set, returns an empty object.
	 * 
	 * @return the list of arc border radius objects.<br>
	 *         If a callback or an array have been set, returns an empty object
	 */
	public ArcBorderRadius getArcBorderRadius() {
		// checks if value stored as integer
		if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			// gets value as object
			return ArcBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		}
		// if here, it's not a an object
		// then create a border radius object by the number
		return new ArcBorderRadius(getBorderRadius());
	}

	/**
	 * Returns <code>true</code> if the arc is curved.
	 * 
	 * @return <code>true</code> if the arc is curved
	 */
	public boolean isCircular() {
		return getValue(Property.CIRCULAR, Defaults.get().getGlobal().getElements().getArc().isCircular());
	}
}