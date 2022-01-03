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
import org.pepstock.charba.client.annotation.callbacks.LabelAlignPositionCallback;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a LABEL annotation which draws a content in the a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelAnnotation extends AbstractPointedAnnotation implements IsDefaultsLabelAnnotation, HasLabel, HasBorderRadius, HasExtendedBorderOptions {

	/**
	 * Default label annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default label annotation background color, <b>{@link HtmlColor#TRANSPARENT}</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = HtmlColor.TRANSPARENT;

	/**
	 * Default label annotation background color as string, <b>{@link HtmlColor#TRANSPARENT}</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = DEFAULT_BACKGROUND_COLOR.toRGBA();

	/**
	 * Default label text color, <b>{@link HtmlColor#WHITE}</b>.
	 */
	public static final IsColor DEFAULT_COLOR = HtmlColor.BLACK;

	/**
	 * Default label font color as string, <b>rgb(255, 255, 255)</b>.
	 */
	public static final String DEFAULT_COLOR_AS_STRING = DEFAULT_COLOR.toRGB();

	/**
	 * Default label padding, <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 6;

	/**
	 * Default label border radius, <b>{@value DEFAULT_BORDER_RADIUS}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 6;

	/**
	 * Default label border cap style, <b>{@link CapStyle#BUTT}</b>.
	 */
	public static final CapStyle DEFAULT_BORDER_CAP_STYLE = CapStyle.BUTT;

	/**
	 * Default label border join style, <b>{@link JoinStyle#MITER}</b>.
	 */
	public static final JoinStyle DEFAULT_BORDER_JOIN_STYLE = JoinStyle.MITER;

	/**
	 * Default text align for labels, <b>{@link TextAlign#CENTER}</b>.
	 */
	public static final TextAlign DEFAULT_TEXT_ALIGN = TextAlign.CENTER;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POSITION("position");

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
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyNativeObjectCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle position options
	private static final CallbackPropertyHandler<LabelAlignPositionCallback> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POSITION);

	// defaults options
	private final IsDefaultsLabelAnnotation defaultValues;
	// label handler
	private final LabelHandler labelHandler;
	// border radius handler
	private final BorderRadiusHandler borderRadiusHandler;
	// extended border options handler
	private final ExtendedBorderOptionsHandler extendedBorderOptionsHandler;
	// position instance
	private final AlignPosition position;

	/**
	 * Creates a label annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public LabelAnnotation() {
		this(AnnotationType.LABEL.createId(), AnnotationType.LABEL.getDefaultsValues());
	}

	/**
	 * Creates a label annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public LabelAnnotation(String id) {
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a label annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public LabelAnnotation(AnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.LABEL, id));
	}

	/**
	 * Creates a label annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public LabelAnnotation(String id, IsChart chart) {
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a label annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public LabelAnnotation(AnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.LABEL, id, chart));
	}

	/**
	 * Creates a label annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private LabelAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.LABEL, id == null ? AnnotationType.LABEL.createId() : id, defaultValues == null ? AnnotationType.LABEL.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsLabelAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.LABEL.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsLabelAnnotation) getDefaultsValues();
		// creates label handler
		this.labelHandler = new LabelHandler(this, this, this.defaultValues, getNativeObject());
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this, this.defaultValues, getNativeObject());
		// creates border options handler
		this.extendedBorderOptionsHandler = new ExtendedBorderOptionsHandler(this, this.defaultValues, getNativeObject());
		// loads position
		this.position = new AlignPosition(this, Property.POSITION, getValue(Property.POSITION), this.defaultValues.getPosition());
		// initialized
		initLabelAnnotation();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	LabelAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsLabelAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POINT.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsLabelAnnotation) getDefaultsValues();
		// creates label handler
		this.labelHandler = new LabelHandler(this, this, this.defaultValues, getNativeObject());
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this, this.defaultValues, getNativeObject());
		// creates border options handler
		this.extendedBorderOptionsHandler = new ExtendedBorderOptionsHandler(this, this.defaultValues, getNativeObject());
		// loads position
		this.position = new AlignPosition(this, Property.POSITION, getValue(Property.POSITION), this.defaultValues.getPosition());
		// initialized
		initLabelAnnotation();
	}

	/**
	 * Initializes the annotation setting the final and callbacks items.
	 */
	private void initLabelAnnotation() {
		// checks if already stored
		if (!has(Property.POSITION)) {
			// stores position
			setValue(Property.POSITION, this.position);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> onPosition(new AnnotationContext(this, context)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasExtendedBorderOptions#getExtendedBorderOptionsHandler()
	 */
	@Override
	public ExtendedBorderOptionsHandler getExtendedBorderOptionsHandler() {
		return extendedBorderOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBorderRadius#getBorderRadiusHandler()
	 */
	@Override
	public BorderRadiusHandler getBorderRadiusHandler() {
		return borderRadiusHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasLabel#getLabelHandler()
	 */
	@Override
	public LabelHandler getLabelHandler() {
		return labelHandler;
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	@Override
	public AlignPosition getPosition() {
		return position;
	}

	// --------------------
	// INTERNALS
	// --------------------

	/**
	 * Returns a native object of a {@link AlignPosition} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @return a native object of a {@link AlignPosition}
	 */
	private NativeObject onPosition(AnnotationContext context) {
		// prepares the result
		AlignPosition resultPosition = null;
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, getPositionCallback());
		// checks if consistent
		if (result instanceof AlignPosition) {
			// casts
			resultPosition = (AlignPosition) result;
		} else if (result instanceof LabelPosition) {
			// casts
			LabelPosition labelPosition = (LabelPosition) result;
			// create align position
			resultPosition = new AlignPosition(labelPosition);
		} else if (result instanceof Number) {
			// is a percentage
			// casts
			Number number = (Number) result;
			// create align position
			resultPosition = new AlignPosition(number.doubleValue());
		} else {
			// if here the result is not consistent
			// then returns the default
			resultPosition = new AlignPosition();
		}
		// returns the object
		return resultPosition.nativeObject();
	}

	// ---------------------
	// CALLBACKS
	// ---------------------
	/**
	 * Returns the callback called to set the anchor position of label on box.
	 * 
	 * @return the callback called to set the anchor position of label on box
	 */
	@Override
	public LabelAlignPositionCallback getPositionCallback() {
		return POSITION_PROPERTY_HANDLER.getCallback(this, defaultValues.getPositionCallback());
	}

	/**
	 * Sets the callback to set the anchor position of label on box.
	 * 
	 * @param positionCallback to set the anchor position of label on box
	 */
	public void setPosition(LabelAlignPositionCallback positionCallback) {
		POSITION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, positionCallback, positionCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the anchor position of label on box.
	 * 
	 * @param positionCallback to set the anchor position of label on box
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((LabelAlignPositionCallback) null);
		// stores values
		setValueAndAddToParent(Property.POSITION, positionCallback);
	}

}
