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
package org.pepstock.charba.client.data;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayCanvas;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultPoint;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;

/**
 * Base object to to manage point style property in the DATASET options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DataPointStyleHandler extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINT_STYLE("pointStyle"),
		// internal key to store if point style is an image or not
		CHARBA_POINT_STYLE("charbaPointStyle");

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

	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// point style callback instance
	private PointStyleCallback<DatasetContext> pointStyleCallback = null;
	// default options
	private final IsDefaultPoint defaultValues;

	/**
	 * Creates the utility using dataset native object and default point values.
	 * 
	 * @param dataset parent of handler
	 * @param nativeObject native object to update with options
	 * @param defaultValues default values for the point
	 */
	DataPointStyleHandler(Dataset dataset, NativeObject nativeObject, IsDefaultPoint defaultValues) {
		super(nativeObject);
		// checks and gets defaults
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(dataset.createContext(context)));
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	PointStyleType getPointStyleType() {
		return getValue(Property.CHARBA_POINT_STYLE, PointStyleType.values(), defaultValues.getPointStyleType());
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	void setPointStyle(PointStyle... pointStyle) {
		// resets callback and flags
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// manage type
		managePointStyleType(PointStyleType.STRING);
	}

	/**
	 * Returns the style of the point. If property is missing or not a point style, returns an empty list.
	 * 
	 * @return list of the style of the point. If property is missing or not a point style, returns an empty list.
	 */
	List<PointStyle> getPointStyle() {
		// checks if string as point style has been used
		if (PointStyleType.STRING.equals(getPointStyleType())) {
			// if not, returns point styles
			ArrayString array = getValueOrArray(Property.POINT_STYLE, defaultValues.getPointStyle());
			return ArrayListHelper.list(PointStyle.values(), array);
		}
		// if here, means the point style as stored as images or callback or canvas
		return ArrayListHelper.list(PointStyle.values(), new PointStyle[0]);
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	void setPointStyle(Img... pointStyle) {
		// resets callback and
		// also flags
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores values
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// manage type
		managePointStyleType(PointStyleType.IMAGE);
	}

	/**
	 * Returns the style of the point as image. If property is missing or not an image, returns an empty list.
	 * 
	 * @return list of the style of the point as image. If property is missing or not a image, returns an empty list.
	 */
	List<Img> getPointStyleAsImages() {
		// checks if image as point style has been used
		if (PointStyleType.IMAGE.equals(getPointStyleType())) {
			// gets array
			ArrayImage array = getValueOrArray(Property.POINT_STYLE, defaultValues.getPointStyleAsImage());
			return ArrayListHelper.list(array);
		}
		// if here, means the point style as stored as string or canvas
		return Collections.emptyList();
	}

	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle array of the style of the point as canvas.
	 */
	void setPointStyle(Canvas... pointStyle) {
		// resets callback and
		// also flags
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores values
		setValueOrArray(Property.POINT_STYLE, pointStyle);
		// manage type
		managePointStyleType(PointStyleType.CANVAS);
	}

	/**
	 * Returns the style of the point as canvas. If property is missing or not an canvas, returns an empty list.
	 * 
	 * @return list of the style of the point as canvas. If property is missing or not a canvas, returns an empty list.
	 */
	List<Canvas> getPointStyleAsCanvas() {
		// checks if canvas as point style has been used
		if (PointStyleType.CANVAS.equals(getPointStyleType())) {
			// gets array
			ArrayCanvas array = getValueOrArray(Property.POINT_STYLE, defaultValues.getPointStyleAsCanvas());
			return ArrayListHelper.list(array);
		}
		// if here, means the point style as stored as string or image
		return Collections.emptyList();
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	PointStyleCallback<DatasetContext> getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	void setPointStyle(PointStyleCallback<DatasetContext> pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// checks if callback is consistent
		if (pointStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_STYLE, pointStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_STYLE);
		}
		// remove if exist flag
		remove(Property.CHARBA_POINT_STYLE);
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	void setPointStyle(NativeCallback pointStyleCallback) {
		// resets callback
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.POINT_STYLE, pointStyleCallback);
	}

	/**
	 * Manages the type of point style.
	 * 
	 * @param type the type to set
	 */
	private void managePointStyleType(PointStyleType type) {
		// checks if options is stored
		if (has(Property.POINT_STYLE)) {
			setValue(Property.CHARBA_POINT_STYLE, type);
		} else {
			// if here, property is not there
			// then remove property
			remove(Property.CHARBA_POINT_STYLE);
		}
	}

	/**
	 * Returns a {@link PointStyle}, {@link Img} or {@link Canvas} the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link PointStyle}, {@link Img} or {@link Canvas}
	 */
	private Object onPointStyle(DatasetContext context) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, getPointStyleCallback());
		// checks result
		if (result instanceof PointStyle) {
			// is point style instance
			PointStyle style = (PointStyle) result;
			return style.value();
		} else if (result instanceof Img) {
			// is image element instance
			return result;
		} else if (result instanceof Canvas) {
			// is canvas element instance
			return result;
		}
		// default result
		return defaultValues.getPointStyle().value();
	}

}