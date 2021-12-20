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
import org.pepstock.charba.client.annotation.callbacks.SidesCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a POLYGON annotation which draws a polygon in the a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PolygonAnnotation extends AbstractPointedAnnotation implements IsDefaultsPolygonAnnotation {

	/**
	 * Default polygon annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Default polygon annotation radius, <b>{@value DEFAULT_RADIUS}</b>.
	 */
	public static final double DEFAULT_RADIUS = 10D;

	/**
	 * Default polygon annotation sides, <b>{@value DEFAULT_SIDES}</b>.
	 */
	public static final int DEFAULT_SIDES = 3;

	/**
	 * Minimum value of polygon annotation sides, <b>{@value MINIMUM_SIDES}</b>.
	 */
	public static final int MINIMUM_SIDES = 3;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		SIDES("sides");

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
	// callback proxy to invoke the capstyle function
	private final CallbackProxy<ProxyStringCallback> capStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the joinstyle function
	private final CallbackProxy<ProxyStringCallback> joinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the sides function
	private final CallbackProxy<ProxyIntegerCallback> sidesCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle capstyle options
	private static final CallbackPropertyHandler<CapStyleCallback<AnnotationContext>> BORDER_CAP_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_CAP_STYLE);
	// callback instance to handle joinstyle options
	private static final CallbackPropertyHandler<JoinStyleCallback<AnnotationContext>> BORDER_JOIN_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_JOIN_STYLE);
	// callback instance to handle sides options
	private static final CallbackPropertyHandler<SidesCallback> SIDES_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.SIDES);

	// defaults options
	private final IsDefaultsPolygonAnnotation defaultValues;

	/**
	 * Creates a polygon annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public PolygonAnnotation() {
		this(AnnotationType.POLYGON.createId(), AnnotationType.POLYGON.getDefaultsValues());
	}

	/**
	 * Creates a polygon annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public PolygonAnnotation(String id) {
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a polygon annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public PolygonAnnotation(AnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.POLYGON, id));
	}

	/**
	 * Creates a polygon annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public PolygonAnnotation(String id, IsChart chart) {
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a polygon annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public PolygonAnnotation(AnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.POLYGON, id, chart));
	}

	/**
	 * Creates a polygon annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private PolygonAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.POLYGON, id == null ? AnnotationType.POLYGON.createId() : id, defaultValues == null ? AnnotationType.POLYGON.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsPolygonAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POLYGON.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsPolygonAnnotation) getDefaultsValues();
		// sets callbacks proxies
		initPolygonCallbacks();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	PolygonAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsPolygonAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POLYGON.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsPolygonAnnotation) getDefaultsValues();
		// sets callbacks proxies
		initPolygonCallbacks();
	}

	/**
	 * Initializes the callbacks proxies for the options which can be scriptable.
	 */
	private void initPolygonCallbacks() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.capStyleCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getBorderCapStyleCallback(), defaultValues.getBorderCapStyle()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.joinStyleCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getBorderJoinStyleCallback(), defaultValues.getBorderJoinStyle()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.sidesCallbackProxy.setCallback(context -> Math.max(ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this, context), getSidesCallback(), defaultValues.getSides()).intValue(), MINIMUM_SIDES));
	}

	/**
	 * Sets the sides of the polygon shape.
	 * 
	 * @param sides the sides of the polygon shape
	 */
	public void setSides(int sides) {
		// resets callback
		setSides((SidesCallback) null);
		// stores value
		setValue(Property.SIDES, Checker.greaterThanOrDefault(sides, MINIMUM_SIDES, DEFAULT_SIDES));
	}

	/**
	 * Returns the sides of the polygon shape.
	 * 
	 * @return the sides of the polygon shape.
	 */
	@Override
	public int getSides() {
		return getValue(Property.SIDES, defaultValues.getSides());
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.values(), defaultValues.getBorderCapStyle());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), defaultValues.getBorderJoinStyle());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the border capstyle.
	 * 
	 * @return the callback called to set the border capstyle
	 */
	@Override
	public CapStyleCallback<AnnotationContext> getBorderCapStyleCallback() {
		return BORDER_CAP_STYLE_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderCapStyleCallback());
	}

	/**
	 * Sets the callback to set the border capstyle.
	 * 
	 * @param capStyleCallback to set the border capstyle
	 */
	public void setBorderCapStyle(CapStyleCallback<AnnotationContext> capStyleCallback) {
		BORDER_CAP_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, capStyleCallback, capStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the border capstyle.
	 * 
	 * @param capStyleCallback to set the border capstyle
	 */
	public void setBorderCapStyle(NativeCallback capStyleCallback) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.BORDER_CAP_STYLE, capStyleCallback);
	}

	/**
	 * Returns the callback called to set the border joinstyle.
	 * 
	 * @return the callback called to set the border joinstyle
	 */
	@Override
	public JoinStyleCallback<AnnotationContext> getBorderJoinStyleCallback() {
		return BORDER_JOIN_STYLE_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderJoinStyleCallback());
	}

	/**
	 * Sets the callback to set the border joinstyle.
	 * 
	 * @param joinStyleCallback to set the border joinstyle
	 */
	public void setBorderJoinStyle(JoinStyleCallback<AnnotationContext> joinStyleCallback) {
		BORDER_JOIN_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, joinStyleCallback, joinStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the border joinstyle.
	 * 
	 * @param joinStyleCallback to set the border joinstyle
	 */
	public void setBorderJoinStyle(NativeCallback joinStyleCallback) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.BORDER_JOIN_STYLE, joinStyleCallback);
	}

	/**
	 * Returns the callback called to set the border sides.
	 * 
	 * @return the callback called to set the border sides
	 */
	@Override
	public SidesCallback getSidesCallback() {
		return SIDES_PROPERTY_HANDLER.getCallback(this, defaultValues.getSidesCallback());
	}

	/**
	 * Sets the callback to set the border sides.
	 * 
	 * @param sidesCallback to set the border sides
	 */
	public void setSides(SidesCallback sidesCallback) {
		SIDES_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, sidesCallback, sidesCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the border sides.
	 * 
	 * @param sidesCallback to set the border sides
	 */
	public void setSides(NativeCallback sidesCallback) {
		// resets callback
		setSides((SidesCallback) null);
		// stores values
		setValue(Property.SIDES, sidesCallback);
	}
}
