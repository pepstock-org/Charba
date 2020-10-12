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
package org.pepstock.charba.client.colors;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.pepstock.charba.client.colors.GradientColor.GradientColorFactory;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * A gradient is an image consisting of a progressive transition between two or more colors.<br>
 * Could be <code>Linear</code> or <code>Radial</code>.<br>
 * Can be created using the size of <code>CANVAS</code> or <code>CHART</code> area.<br>
 * The orientation to have a progressive transition, is defined by an enumeration in order to creates the gradient using the right coordinates and dimension, based on existing
 * items (canvas and chart).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Gradient extends CanvasObject {

	// internal comparator to sort colors by own offset
	private static final Comparator<GradientColor> COMPARATOR = (GradientColor o1, GradientColor o2) -> Double.compare(o1.getOffset(), o2.getOffset());
	// contains the gradient colors
	private final ArrayObjectContainerList<GradientColor> colors;
	// factory to creates color by native object
	private final GradientColorFactory factory = new GradientColorFactory();

	/**
	 * Name of properties of native object. ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		CHARBA_GRADIENT_COLORS("_charbaGradientColors", ObjectType.ARRAY),
		CHARBA_GRADIENT_TYPE("_charbaGradientType", ObjectType.STRING),
		CHARBA_GRADIENT_ORIENTATION("_charbaGradientOrientation", ObjectType.STRING),
		CHARBA_GRADIENT_SCOPE("_charbaGradientScope", ObjectType.STRING);

		// name value of property
		private final String value;
		// object type of property
		private final ObjectType type;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 * @param type object type of property
		 */
		private Property(String value, ObjectType type) {
			this.value = value;
			this.type = type;
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
		 * Returns the type of the property
		 * 
		 * @return the type of the property
		 */
		ObjectType type() {
			return type;
		}
	}

	/**
	 * Creates a gradient by a type, an orientation and a scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @param scope scope of gradient
	 */
	Gradient(GradientType type, GradientOrientation orientation, GradientScope scope) {
		super();
		// checks if type is consistent
		if (type == null) {
			// then throws an exception
			throw new IllegalArgumentException("Gradient type argument is null");
		}
		// checks if orientation is consistent
		if (orientation == null) {
			// then throws an exception
			throw new IllegalArgumentException("Gradient orientation argument is null");
		}
		// checks if scope is consistent
		if (scope == null) {
			// then throws an exception
			throw new IllegalArgumentException("Gradient scope argument is null");
		}
		// creates color list
		colors = new ArrayObjectContainerList<>();
		// stores gradient type
		setValue(Property.CHARBA_GRADIENT_TYPE, type);
		// checks if orientation is aligned with type
		if (type.equals(orientation.getType())) {
			// stores the passed orientation
			setValue(Property.CHARBA_GRADIENT_ORIENTATION, orientation);
		} else {
			// if here, the type is not aligned with orientation
			// type wins on orientation
			// and then ignore orientation and stores
			// default orientation for that gradient
			setValue(Property.CHARBA_GRADIENT_ORIENTATION, GradientOrientation.getDefaultByType(type));
		}
		// stores scope
		setValue(Property.CHARBA_GRADIENT_SCOPE, scope);
		// stores the color list
		setArrayValue(Property.CHARBA_GRADIENT_COLORS, colors);
	}

	/**
	 * Internal constructor to create a gradient, previously stored into a native java script object
	 * 
	 * @param nativeObject native java script object wrapped by gradient.
	 */
	Gradient(NativeObject nativeObject) {
		// creates the object with native one
		super(nativeObject);
		// checks if native object is consistent for a canvas object
		// scans properties
		for (Property property : Property.values()) {
			// checks the consistency of properties
			checkNativeObject(property, property.type());
		}
		// gets array of color
		ArrayObject array = getArrayValue(Property.CHARBA_GRADIENT_COLORS);
		// if null
		if (array == null) {
			// creates an empty list
			colors = new ArrayObjectContainerList<>();
		} else {
			// otherwise creates a list using the native object
			colors = ArrayListHelper.list(array, factory);
		}
	}

	/**
	 * Returns the gradient type.
	 * 
	 * @return the gradient type
	 */
	public GradientType getType() {
		return getValue(Property.CHARBA_GRADIENT_TYPE, GradientType.values(), GradientType.LINEAR);
	}

	/**
	 * Returns the gradient orientation.
	 * 
	 * @return the gradient orientation
	 */
	public GradientOrientation getOrientation() {
		return getValue(Property.CHARBA_GRADIENT_ORIENTATION, GradientOrientation.values(), GradientOrientation.getDefaultByType(getType()));
	}

	/**
	 * Returns the gradient scope.
	 * 
	 * @return the gradient scope
	 */
	public GradientScope getScope() {
		return getValue(Property.CHARBA_GRADIENT_SCOPE, GradientScope.values(), GradientScope.CHART);
	}

	/**
	 * Sets the start and stop color of gradient, when the gradient will have only 2 colors.
	 * 
	 * @param start starting color, with offset 0
	 * @param stop stopping color, with offset 1
	 */
	public void addColorsStartStop(IsColor start, IsColor stop) {
		addColorsStartStop(IsColor.checkAndGetValue(start), IsColor.checkAndGetValue(stop));
	}

	/**
	 * Returns the unmodifiable list of stopping colors.
	 * 
	 * @return the unmodifiable list of stopping colors.
	 */
	public List<GradientColor> getColors() {
		return Collections.unmodifiableList(colors);
	}

	/**
	 * Sets the start and stop color of gradient, when the gradient will have only 2 colors.
	 * 
	 * @param start starting color, with offset 0
	 * @param stop stopping color, with offset 1
	 */
	void addColorsStartStop(String start, String stop) {
		colors.clear();
		addColorStop(GradientColor.DEFAULT_OFFSET_START, start);
		addColorStop(GradientColor.DEFAULT_OFFSET_STOP, stop);
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 */
	void addColorStop(double offset, IsColor color) {
		addColorStop(offset, IsColor.checkAndGetValue(color));
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 */
	void addColorStop(double offset, String color) {
		addColorStop(new GradientColor(offset, color));
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param color color instance
	 */
	void addColorStop(GradientColor color) {
		// checks if argument is consistent
		if (color != null) {
			// if consistent, adds color
			colors.add(color);
		}
	}

	/**
	 * Returns <code>true</code> if at least a color has been stored.
	 * 
	 * @return <code>true</code> if at least a color has been stored
	 */
	boolean hasColors() {
		return !colors.isEmpty();
	}

	/**
	 * Sorts all colors by thier offset.
	 */
	void sortColors() {
		// sorts the color in order to have the list from less to greater
		Collections.sort(colors, COMPARATOR);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hasId()) ? 0 : getId().hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if is the same object
		if (this == obj) {
			return true;
		}
		// checks if argument is null
		if (obj == null) {
			return false;
		}
		// checks if the class is the same
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to a gradient
		Gradient other = (Gradient) obj;
		// checks if there is an id
		if (hasId()) {
			// checks with other id
			return getId().equals(other.getId());
		}
		// if here, the this does not have the id
		// then if the other is has id is NOT equals
		// otherwise they are equals because both are null
		return !other.hasId();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObject#generateUniqueId()
	 */
	@Override
	String generateUniqueId() {
		StringBuilder sb = new StringBuilder();
		sb.append(getValue(Property.CHARBA_GRADIENT_TYPE, UndefinedValues.STRING)).append(Constants.MINUS);
		sb.append(getValue(Property.CHARBA_GRADIENT_ORIENTATION, UndefinedValues.STRING)).append(Constants.MINUS);
		sb.append(getValue(Property.CHARBA_GRADIENT_SCOPE, UndefinedValues.STRING)).append(Constants.MINUS);
		sb.append(colors.toString());
		return sb.toString();
	}

	/**
	 * Returns a color using the gradient as source of colors.
	 * 
	 * @param offset offset to search into the gradient colors
	 * @return a color based on offset.
	 */
	public IsColor getInterpolatedColorByOffset(double offset) {
		// checks if offset is consistent
		GradientColor.checkOffsetWithinBounds(offset);
		// sorts the color in order to have the list from less to greater
		Collections.sort(colors, COMPARATOR);
		// instances of start and end colors
		// which will contains the passed offset
		IsColor startColor = null;
		IsColor endColor = null;
		// scans all gradient colors loaded
		for (GradientColor color : colors) {
			// if offset is the same of already set to a color
			if (Double.compare(color.getOffset(), offset) == 0) {
				// returns it
				return color.getColor();
			}
			// checks if passed offset is less the passed offset
			if (color.getOffset() < offset) {
				// stores multiple time this value till
				// the first color greater than passed offset
				startColor = color.getColor();
			} else if (color.getOffset() > offset && endColor == null) {
				// gets the first color with offset greater than passed offset
				endColor = color.getColor();
			}
		}
		// checks starting colors are found otherwise exception
		if (startColor == null) {
			throw new IllegalArgumentException("Unable to get the start and stop color based on passed offset " + offset);
		} else if (endColor == null) {
			// if end colors is not found, use the starting color
			return startColor;
		}
		// gets the sRGB for starting color
		int startRGBs = startColor.toRGBs();
		// extract all RGB elements
		// convert from sRGB to linear
		double startA = ((startRGBs >> 24) & 0xff) / 255.0D;
		double startR = fromRGBs(((startRGBs >> 16) & 0xff) / 255.0D);
		double startG = fromRGBs(((startRGBs >> 8) & 0xff) / 255.0D);
		double startB = fromRGBs((startRGBs & 0xff) / 255.0D);
		// extract all RGB elements
		// convert from sRGB to linear
		int endRGBs = endColor.toRGBs();
		double endA = ((endRGBs >> 24) & 0xff) / 255.0D;
		double endR = fromRGBs(((endRGBs >> 16) & 0xff) / 255.0D);
		double endG = fromRGBs(((endRGBs >> 8) & 0xff) / 255.0D);
		double endB = fromRGBs((endRGBs & 0xff) / 255.0D);
		// compute the interpolated color in linear space
		// convert back to sRGB in the [0..255] range
		// rounds them to int
		double a = startA + offset * (endA - startA);
		int r = (int) Math.round(toRGBs(startR + offset * (endR - startR)) * 255.0D);
		int g = (int) Math.round(toRGBs(startG + offset * (endG - startG)) * 255.0D);
		int b = (int) Math.round(toRGBs(startB + offset * (endB - startB)) * 255.0D);
		// creates and return color
		return new Color(r, g, b, a);
	}

	/**
	 * Performs the conversion for the sRGB color space and converts it to a linear sRGB value.
	 * 
	 * @param linear optical electronic color value
	 * @return linear sRGB value
	 */
	private double toRGBs(double linear) {
		// IEC 61966-2-1:1999
		return linear <= 0.0031308D ? linear * 12.92D : (double) ((Math.pow(linear, 1.0D / 2.4D) * 1.055D) - 0.055D);
	}

	/**
	 * Performs the conversion for a linear sRGB value to a gamma-encoded sRGB value
	 * 
	 * @param srgb electronic optical color value
	 * @return gamma-encoded sRGB value
	 */
	private double fromRGBs(double srgb) {
		// IEC 61966-2-1:1999
		return srgb <= 0.04045D ? srgb / 12.92D : (double) Math.pow((srgb + 0.055D) / 1.055D, 2.4D);
	}

}
