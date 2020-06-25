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
package org.pepstock.charba.client.enums;

import java.util.Locale;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Enumerates the default scale id.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultScaleId implements IsScaleId
{
	/**
	 * Default scale id for X cartesian axis.
	 */
	X("x", AxisKind.X),
	/**
	 * Default scale id for Y cartesian axis.
	 */
	Y("y",AxisKind.Y),
	/**
	 * Default scale id for radial linear axis.
	 */
	R("r",AxisKind.R),
	/**
	 * Default scale id for chart with a single axis.
	 */
	UNKNOWN("_charbaunknown", null);
	
	/**
	 * Default scale id for X scale defined into chart defaults for {@link ChartType#BAR} and {@link ChartType#LINE}.
	 */
	public static final String DEFAULT_X_FOR_BAR_AND_LINE_OPTIONS = "_index_"; 

	/**
	 * Default scale id for Y scale defined into chart defaults for {@link ChartType#BAR} and {@link ChartType#LINE}.
	 */
	public static final String DEFAULT_Y_FOR_BAR_AND_LINE_OPTIONS = "_value_"; 

	// name value of property
	private final String value;

	// default axis kind
	private final AxisKind axisKind;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param kind default axis kind for this default scale id
	 */
	private DefaultScaleId(String value, AxisKind kind) {
		this.value = value;
		this.axisKind = kind;
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

	/**
	 * Returns the default axis kind for this default scale id.
	 * 
	 * @return the default axis kind for this default scale id
	 */
	public AxisKind getAxisKind() {
		return axisKind;
	}

	/**
	 * Returns <code>true</code> if the scale id is related to this axis id.
	 * 
	 * @param scaleId scale id to be checked
	 * @return <code>true</code> if the scale id is related to this axis id
	 */
	public boolean is(String scaleId) {
		// checks id if consistent
		if (scaleId != null) {
			// put to lower case
			String id = scaleId.toLowerCase(Locale.getDefault());
			// checks if starts with
			return id.startsWith(value());
		}
		// if here the argument is not consistent
		return false;
	}

	/**
	 * Returns <code>true</code> if the scale id is related to this axis id.
	 * 
	 * @param scaleId scale id to be checked
	 * @return <code>true</code> if the scale id is related to this axis id
	 */
	public boolean is(IsScaleId scaleId) {
		return is(Key.checkAndGetIfValid(scaleId).value());
	}
	
	/**
	 * Returns the default scale id instance by axis kind, otherwise will return <code>null</code> if not found.
	 * 
	 * @param kind axis kind to use to get the related default scale id
	 * @param defaultValue default axis id to use if it's not matching 
	 * @return the default scale id instance, otherwise will return <code>null</code> if not found
	 */
	public static DefaultScaleId getByAxisKind(AxisKind kind, DefaultScaleId defaultValue) {
		// checks if kind is consistent
		if (Key.isValid(kind)) {
			// scans all defaults to check with the argument
			for (DefaultScaleId scaleId : values()) {
				// checks if default axis kind matches with argument
				if (scaleId.getAxisKind().equals(kind)) {
					return scaleId;
				}
			}
		}
		// if here, the id does not match
		return Key.checkAndGetIfValid(defaultValue);
	}

	/**
	 * Returns the axis kind inferring from the first character of the scale id which should be 'x', 'y' or 'r'.
	 * 
	 * @param scaleId scale id
	 * @param defaultValue default value for axis kind when it can not be recognized by scale id
	 * @return the axis kind inferring from the first character of the scale id which should be 'x', 'y' or 'r'
	 */
	public static AxisKind getAxisKindByScaleId(Key scaleId, AxisKind defaultValue) {
		return getAxisKindByScaleId(Key.checkAndGetIfValid(scaleId).value(), defaultValue);
	}

	/**
	 * Returns the axis kind inferring from the first character of the scale id which should be 'x', 'y' or 'r'.
	 * 
	 * @param scaleId scale id
	 * @param defaultValue default value for axis kind when it can not be recognized by scale id
	 * @return the axis kind inferring from the first character of the scale id which should be 'x' or 'y'
	 */
	public static AxisKind getAxisKindByScaleId(String scaleId, AxisKind defaultValue) {
		// checks if argument is consistent
		if (scaleId != null) {
			// scans all types
			for (DefaultScaleId defaultScaleId : values()) {
				// checks if scale id starts with the default
				// and is consistent, ignoring unknown scale id
				if (defaultScaleId.is(scaleId) && defaultScaleId.getAxisKind() != null) {
					// matches with default scale id
					return defaultScaleId.getAxisKind();
				}
			}
		}
		// further check for default of bar/line default chart options.
		if (DEFAULT_X_FOR_BAR_AND_LINE_OPTIONS.equals(scaleId)) {
			// returns x
			return AxisKind.X;
		} else 	if (DEFAULT_Y_FOR_BAR_AND_LINE_OPTIONS.equals(scaleId)) {
			// returns x
			return AxisKind.Y;
		}
		// checks if default value is consistent
		// and returns it
		return Key.checkAndGetIfValid(defaultValue);
	}

}