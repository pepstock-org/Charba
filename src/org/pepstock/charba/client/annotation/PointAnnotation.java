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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPointStyleHandler;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.HasPointStyle;
import org.pepstock.charba.client.options.PointStyleHandler;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a POINT annotation which draws a point in the a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointAnnotation extends AbstractPointedAnnotation implements IsDefaultsPointAnnotation, HasPointStyle {

	/**
	 * Default point annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Default point annotation radius, <b>{@value DEFAULT_RADIUS}</b>.
	 */
	public static final double DEFAULT_RADIUS = 10D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINT_STYLE("pointStyle");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback instance to handle yAdjustg options
	private static final CallbackPropertyHandler<PointStyleCallback<AnnotationContext>> POINT_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POINT_STYLE);

	// defaults options
	private final IsDefaultsPointAnnotation defaultValues;
	// instance of style of points manager
	private final InternalPointStyleHandler pointStyleHandler;

	/**
	 * Creates a point annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public PointAnnotation() {
		this(AnnotationType.POINT.createId(), AnnotationType.POINT.getDefaultsValues());
	}

	/**
	 * Creates a point annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public PointAnnotation(String id) {
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a point annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public PointAnnotation(AnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.POINT, id));
	}

	/**
	 * Creates a point annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public PointAnnotation(String id, IsChart chart) {
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a point annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public PointAnnotation(AnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.POINT, id, chart));
	}

	/**
	 * Creates a point annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private PointAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.POINT, id == null ? AnnotationType.POINT.createId() : id, defaultValues == null ? AnnotationType.POINT.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsPointAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POINT.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsPointAnnotation) getDefaultsValues();
		// creates point style handler
		this.pointStyleHandler = new InternalPointStyleHandler(this, this.defaultValues, getNativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(new AnnotationContext(this, context), getPointStyleCallback(), this.defaultValues.getPointStyle()));
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	PointAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsPointAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POINT.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsPointAnnotation) getDefaultsValues();
		// creates point style handler
		this.pointStyleHandler = new InternalPointStyleHandler(this, this.defaultValues, getNativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(new AnnotationContext(this, context), getPointStyleCallback(), this.defaultValues.getPointStyle()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasPointStyle#getPointStyleHandler()
	 */
	@Override
	public PointStyleHandler getPointStyleHandler() {
		return pointStyleHandler;
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasPointStyle#setPointStyle(org.pepstock.charba.client.enums.PointStyle)
	 */
	@Override
	public void setPointStyle(PointStyle pointStyle) {
		// reset callback
		setPointStyle((PointStyleCallback<AnnotationContext>) null);
		// stores values
		HasPointStyle.super.setPointStyle(pointStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasPointStyle#setPointStyle(org.pepstock.charba.client.dom.elements.Img)
	 */
	@Override
	public void setPointStyle(Img pointStyle) {
		// reset callback
		setPointStyle((PointStyleCallback<AnnotationContext>) null);
		// stores values
		HasPointStyle.super.setPointStyle(pointStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasPointStyle#setPointStyle(org.pepstock.charba.client.dom.elements.Canvas)
	 */
	@Override
	public void setPointStyle(Canvas pointStyle) {
		// reset callback
		setPointStyle((PointStyleCallback<AnnotationContext>) null);
		// stores values
		HasPointStyle.super.setPointStyle(pointStyle);
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public PointStyleCallback<AnnotationContext> getPointStyleCallback() {
		return POINT_STYLE_PROPERTY_HANDLER.getCallback(this, defaultValues.getPointStyleCallback());
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback<AnnotationContext> pointStyleCallback) {
		POINT_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, pointStyleCallback, pointStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(NativeCallback pointStyleCallback) {
		// resets callback
		setPointStyle((PointStyleCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.POINT_STYLE, pointStyleCallback);
	}

	// ---------------------
	// INTERNALS
	// ---------------------

	/**
	 * Returns a {@link PointStyle} or {@link Img} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback callback instance to be invoked
	 * @param defaultValue default point style value
	 * @return a object property value, as {@link PointStyle} or {@link Img}
	 */
	final Object onPointStyle(AnnotationContext context, PointStyleCallback<AnnotationContext> callback, PointStyle defaultValue) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, callback);
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
		// checks defaults
		Checker.checkIfValid(defaultValue, "Default point style argument");
		// default result
		return defaultValue.value();
	}

	/**
	 * Internal class to implement a point style handler.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalPointStyleHandler extends PointStyleHandler {

		/**
		 * Creates a point style handler with the native object where POINTSTYLE property must be managed and the default value to use when the property does not exist.
		 * 
		 * @param parent model which contains the point style handler.
		 * @param defaultValues default value of point style to use when the properties do not exist
		 * @param nativeObject native object where point style handler properties must be managed
		 */
		protected InternalPointStyleHandler(AbstractNode parent, IsDefaultPointStyleHandler defaultValues, NativeObject nativeObject) {
			super(parent, defaultValues, nativeObject);
		}

	}
}
