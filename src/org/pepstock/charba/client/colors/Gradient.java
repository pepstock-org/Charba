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
import java.util.List;

import org.pepstock.charba.client.colors.GradientColor.GradientColorFactory;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * A gradient is an image consisting of a progressive transition between two or more colors.<br>
 * Could be <code>Linear</code> or <code>Radial</code>.<br>
 * Can be created using the size of <code>CANVAS</code> or <code>CHART</code> area.<br>
 * The orientation to have a progressive transition, is defined by an enumeration in order to creates the gradient using the
 * right coordinates and dimension, based on existing items (canvas and chart).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Gradient extends CanvasObject {

	// contains the gradient colors
	private final ArrayObjectContainerList<GradientColor> colors;
	// factory to creates color by native object
	private final GradientColorFactory factory = new GradientColorFactory();

	/**
	 * Name of properties of native object. ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		_charbaGradientColors,
		_charbaGradientType,
		_charbaGradientOrientation,
		_charbaGradientScope
	}

	/**
	 * Creates a LINEAR gradient, with <code>topDown</code> orientation and <code>chart</code> scope.
	 */
	public Gradient() {
		this(GradientType.linear);
	}

	/**
	 * Creates a gradient by a type, with <code>chart</code> scope.
	 * 
	 * @param type gradient type
	 */
	public Gradient(GradientType type) {
		this(type, GradientOrientation.getDefaultByType(type));
	}

	/**
	 * Creates a gradient by a type and an orientation, with <code>chart</code> scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 */
	public Gradient(GradientType type, GradientOrientation orientation) {
		this(type, orientation, GradientScope.chart);
	}

	/**
	 * Creates a gradient by a type and a scope.
	 * 
	 * @param type gradient type
	 * @param scope scope of gradient
	 */
	public Gradient(GradientType type, GradientScope scope) {
		this(type, GradientOrientation.getDefaultByType(type), scope);
	}

	/**
	 * Creates a gradient by a type, an orientation and a scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @param scope scope of gradient
	 */
	public Gradient(GradientType type, GradientOrientation orientation, GradientScope scope) {
		// creates color list
		colors = new ArrayObjectContainerList<>();
		// stores gradient type
		setValue(Property._charbaGradientType, type);
		// checks if orientation is aligned with type
		if (type.equals(orientation.getType())) {
			// stores the passed orientation
			setValue(Property._charbaGradientOrientation, orientation);
		} else {
			// if here, the type is not aligned with orientation
			// type wins on orientation
			// and then ignore orientation and stores
			// default orientation for that gradient
			setValue(Property._charbaGradientOrientation, GradientOrientation.getDefaultByType(type));
		}
		// stores scope
		setValue(Property._charbaGradientScope, scope);
		// stores the color list
		setArrayValue(Property._charbaGradientColors, colors);
	}

	/**
	 * Internal constructor to create a gradient, previously stored into a native java script object
	 * 
	 * @param nativeObject native java script object wrapped by gradient.
	 */
	Gradient(NativeObject nativeObject) {
		super(nativeObject);
		ArrayObject array = getArrayValue(Property._charbaGradientColors);
		if (array == null) {
			colors = new ArrayObjectContainerList<GradientColor>();
		} else {
			colors = ArrayListHelper.list(array, factory);
		}
	}

	public final GradientType getType() {
		return getValue(Property._charbaGradientType, GradientType.class, GradientType.linear);
	}

	public final GradientOrientation getOrientation() {
		return getValue(Property._charbaGradientOrientation, GradientOrientation.class, GradientOrientation.getDefaultByType(getType()));
	}

	public final GradientScope getScope() {
		return getValue(Property._charbaGradientScope, GradientScope.class, GradientScope.chart);
	}

	public void addColorsStartStop(IsColor start, IsColor stop) {
		addColorsStartStop(start.toRGBA(), stop.toRGBA());
	}

	public void addColorsStartStop(String start, String stop) {
		colors.clear();
		addColorStop(GradientColor.OFFSET_START, start);
		addColorStop(GradientColor.OFFSET_STOP, stop);
	}

	public void addColorStop(double offset, IsColor color) {
		addColorStop(offset, color.toRGBA());
	}

	public void addColorStop(double offset, String color) {
		addColorStop(new GradientColor(offset, color));
	}

	public void addColorStop(GradientColor color) {
		colors.add(color);
	}

	public List<GradientColor> getColors() {
		return Collections.unmodifiableList(colors);
	}

	/**
	 * Inner class to create gradient by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static final class GradientFactory implements NativeObjectContainerFactory<Gradient> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public Gradient create(NativeObject nativeObject) {
			return new Gradient(nativeObject);
		}
	}

}
