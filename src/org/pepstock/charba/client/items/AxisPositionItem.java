/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.items;

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.options.OptionsEnvelop;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This item is used to define the position of an axis related to a value of another axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AxisPositionItem extends NativeObjectContainer {

	/**
	 * Creates the item using an envelop of native java script object which contains all properties.
	 * 
	 * @param envelop envelop of native java script object which contains all properties.
	 */
	public AxisPositionItem(OptionsEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an item with the position.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public AxisPositionItem(ScaleId id, double value) {
		this();
		// stores value
		setPosition(id, value);
	}

	/**
	 * Creates an item with the position.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public AxisPositionItem(String id, double value) {
		this();
		// stores value
		setPosition(id, value);
	}

	/**
	 * Creates an item with the position.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public AxisPositionItem(ScaleId id, String value) {
		this();
		// stores value
		setPosition(id, value);
	}

	/**
	 * Creates an item with the position.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public AxisPositionItem(String id, String value) {
		this();
		// stores value
		setPosition(id, value);
	}

	/**
	 * Creates an item with the position.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public AxisPositionItem(ScaleId id, Date value) {
		this();
		// stores value
		setPosition(id, value);
	}

	/**
	 * Creates an item with the position.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public AxisPositionItem(String id, Date value) {
		this();
		// stores value
		setPosition(id, value);
	}

	/**
	 * Creates an empty item.
	 */
	public AxisPositionItem() {
		super(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AxisPositionItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the scale id set for positioning.
	 * 
	 * @return the scale id set for positioning
	 */
	public ScaleId getScaleId() {
		// gets keys
		List<Key> keys = keys();
		// gets is there is any key
		if (!keys.isEmpty()) {
			// gets the key
			// getting at index 0
			// there should be ONLY 1 key
			return ScaleId.create(keys.get(0).value());
		}
		// if here, native object without any key
		// then returns null
		return null;
	}

	/**
	 * Returns the value on the axis used for positioning.
	 * 
	 * @return the value on the axis used for positioning
	 */
	public double getValue() {
		// gets scale id
		ScaleId id = getScaleId();
		// checks if the data is stored as number
		if (isType(id, ObjectType.NUMBER)) {
			return getValue(id, Undefined.DOUBLE);
		}
		// if here, native object without any key
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the value on the axis used for positioning.
	 * 
	 * @return the value on the axis used for positioning
	 */
	public String getValueAsString() {
		// gets scale id
		ScaleId id = getScaleId();
		// checks if the data is stored as number
		if (isType(id, ObjectType.STRING)) {
			return getValue(id, Undefined.STRING);
		}
		// gets value as number
		double value = getValue();
		// checks if number value is consistent with a date
		if (Undefined.isNot(value)) {
			// creates and returns a date
			return String.valueOf(value);
		}
		// if here, native object without any key
		// then returns undefined
		return Undefined.STRING;
	}

	/**
	 * Returns the value on the axis used for positioning.
	 * 
	 * @return the value on the axis used for positioning
	 */
	public Date getValueAsDate() {
		// gets value as number
		double value = getValue();
		// checks if number value is consistent with a date
		if (Undefined.isNot(value)) {
			// creates and returns a date
			return new ImmutableDate((long) value);
		}
		// the property is not a number
		// then returns undefined value
		return null;
	}

	/**
	 * Sets the position of another scale.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public void setPosition(ScaleId id, double value) {
		// checks scale id consistency
		ScaleId.checkIfValid(id);
		// stores value
		setValue(id, value);
	}

	/**
	 * Sets the position of another scale.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public void setPosition(String id, double value) {
		setPosition(ScaleId.create(id), value);
	}

	/**
	 * Sets the position of another scale.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public void setPosition(ScaleId id, String value) {
		// checks scale id consistency
		ScaleId.checkIfValid(id);
		// stores value
		setValue(id, value);
	}

	/**
	 * Sets the position of another scale.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public void setPosition(String id, String value) {
		setPosition(ScaleId.create(id), value);
	}

	/**
	 * Sets the position of another scale.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public void setPosition(ScaleId id, Date value) {
		// checks scale id consistency
		ScaleId.checkIfValid(id);
		// stores value
		setValue(id, value);
	}

	/**
	 * Sets the position of another scale.
	 * 
	 * @param id scale id to use for positioning
	 * @param value value of the scale id to use as position
	 */
	public void setPosition(String id, Date value) {
		setPosition(ScaleId.create(id), value);
	}

	/**
	 * Returns <code>true</code> if the item is consistent.
	 * 
	 * @return<code>true</code> if the item is consistent
	 */
	public boolean isConsistent() {
		return empty();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return getNativeObject();
	}
}