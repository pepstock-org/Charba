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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.DatasetAnimationCallback;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.Animation;
import org.pepstock.charba.client.options.AnimationContainer;
import org.pepstock.charba.client.options.HasAnimation;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;
import org.pepstock.charba.client.plugins.PluginIdChecker;
import org.pepstock.charba.client.utils.JSON;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class Dataset extends AbstractNode implements HasDataset, HasAnimation {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> hoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> hoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the animation callback function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> animationCallbackProxy = JsHelper.get().newCallbackProxy();

	// hover background color callback instance
	private BackgroundColorCallback hoverBackgroundColorCallback = null;
	// hover border color callback instance
	private BorderColorCallback hoverBorderColorCallback = null;
	// hover borderWidth callback instance
	private BorderWidthCallback hoverBorderWidthCallback = null;
	// background color callback instance
	private BackgroundColorCallback backgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback borderColorCallback = null;
	// borderWidth callback instance
	private BorderWidthCallback borderWidthCallback = null;
	// animation callback
	private DatasetAnimationCallback animationCallback = null;

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// default for hidden property
	protected static final boolean DEFAULT_HIDDEN = false;
	// factory to create data points
	static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();
	// factory to create time series items
	static final TimeSeriesItemFactory TIMESERIES_ITEMS_FACTORY = new TimeSeriesItemFactory();
	// exception message when it's not using data points
	static final String DATA_USAGE_MESSAGE = "Use datapoints instead of data for this dataset";
	// exception string message for setting ore getting data
	static final String TIME_SERIES_DATA_USAGE_MESSAGE = "setData and getData methods are not invokable by a time series chart";
	// patterns container
	private final PatternsContainer patternsContainer;
	// gradients container
	private final GradientsContainer gradientsContainer;
	// cache for gradients created by callbacks
	// K = key + dataset locator, V = gradient
	private final Map<String, Gradient> callbackGradientsContainer = new HashMap<>();
	// cache for patterns created by callbacks
	// K = key + dataset locator, V = pattern
	private final Map<String, Pattern> callbackPatternsContainer = new HashMap<>();
	// default options values
	private final IsDefaultOptions defaultValues;
	// chart type related to dataset
	private final Type type;
	// scope instance
	private final String scope;
	// animation container
	private final AnimationContainer animationContainer;
	// internal comparator to sort time series items
	private static final Comparator<TimeSeriesItem> COMPARATOR = (TimeSeriesItem o1, TimeSeriesItem o2) -> o1.getTime().compareTo(o2.getTime());

	/**
	 * Name of common properties of native object related to a dataset.
	 */
	protected enum CanvasObjectProperty implements CanvasObjectKey
	{
		BACKGROUND_COLOR("backgroundColor", true),
		BORDER_COLOR("borderColor", false),
		HOVER_BACKGROUND_COLOR("hoverBackgroundColor", true),
		HOVER_BORDER_COLOR("hoverBorderColor", false);

		// name value of property
		private final String value;
		// flag for managing patterns
		private final boolean hasPattern;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 * @param hasPattern <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them
		 */
		private CanvasObjectProperty(String value, boolean hasPattern) {
			this.value = value;
			this.hasPattern = hasPattern;
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.data.Dataset.CanvasObjectKey#hasPattern()
		 */
		@Override
		public boolean hasPattern() {
			return hasPattern;
		}

	}

	/**
	 * Name of common properties of native object related to a dataset.
	 */
	protected enum CommonProperty implements Key
	{
		CLIP("clip"),
		BORDER_WIDTH("borderWidth"),
		HOVER_BORDER_WIDTH("hoverBorderWidth");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private CommonProperty(String value) {
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
	 * Name of private properties of native object.
	 */
	enum InternalProperty implements Key
	{
		ANIMATION("animation"),
		LABEL("label"),
		DATA("data"),
		TYPE("type"),
		HIDDEN("hidden"),
		// internal key to store a unique id
		CHARBA_ID("_charbaId"),
		// internal key to store patterns and gradients
		CHARBA_PATTERNS("_charbaPatterns"),
		CHARBA_GRADIENTS("_charbaGradients"),
		// internal key to store data type
		CHARBA_DATA_TYPE("_charbaDataType");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private InternalProperty(String value) {
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
	 * Creates the dataset using a default and chart type related to the dataset, adding patterns and gradients element.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected Dataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(null);
		this.defaultValues = defaultValues == null ? Defaults.get().getOptions(Type.checkAndGetIfValid(type)) : defaultValues;
		// sets animation container
		this.animationContainer = new AnimationContainer(getDefaultValues().getAnimation(), new DataEnvelop<>(getNativeObject()));
		// stores the type
		this.type = type;
		// stores the type
		setValue(InternalProperty.TYPE, type);
		// checks and stores visibility
		if (hidden) {
			// stores visibility
			setHidden(hidden);
		}
		// patterns container
		this.patternsContainer = new PatternsContainer(this);
		// gradients container
		this.gradientsContainer = new GradientsContainer(this);
		// stores the id based on a counter
		setValue(InternalProperty.CHARBA_ID, COUNTER.getAndIncrement());
		// sets the Charba containers into dataset java script configuration
		setValue(InternalProperty.CHARBA_PATTERNS, patternsContainer);
		setValue(InternalProperty.CHARBA_GRADIENTS, gradientsContainer);
		// sets default data type
		setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.UNKNOWN);
		// creates scope, checking form default
		this.scope = createScope(getId());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		backgroundColorCallbackProxy.setCallback((contextFunction, context) -> invokeColorCallback(new ScriptableContext(new DataEnvelop<>(context)), backgroundColorCallback, CanvasObjectProperty.BACKGROUND_COLOR, getDefaultBackgroundColorAsString()));
		// gets value calling callback
		borderColorCallbackProxy.setCallback((contextFunction, context) -> invokeColorCallback(new ScriptableContext(new DataEnvelop<>(context)), borderColorCallback, CanvasObjectProperty.BORDER_COLOR, getDefaultBorderColorAsString()));
		// gets value calling callback
		borderWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataEnvelop<>(context)), borderWidthCallback, getDefaultBorderWidth()).intValue());
		// gets value calling callback
		hoverBackgroundColorCallbackProxy
				.setCallback((contextFunction, context) -> invokeColorCallback(new ScriptableContext(new DataEnvelop<>(context)), hoverBackgroundColorCallback, CanvasObjectProperty.HOVER_BACKGROUND_COLOR, getDefaultBackgroundColorAsString()));
		// gets value calling callback
		hoverBorderColorCallbackProxy.setCallback((contextFunction, context) -> invokeColorCallback(new ScriptableContext(new DataEnvelop<>(context)), hoverBorderColorCallback, CanvasObjectProperty.HOVER_BORDER_COLOR, getDefaultBorderColorAsString()));
		// gets value calling callback
		hoverBorderWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataEnvelop<>(context)), hoverBorderWidthCallback, getDefaultBorderWidth()).intValue());
		// invokes callback
		animationCallbackProxy.setCallback((contextFunction, context) -> onAnimationCallback(new ScriptableContext(new DataEnvelop<>(context))));
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animationContainer.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimation#setAnimationEnabled(boolean)
	 */
	@Override
	public void setAnimationEnabled(boolean enabled) {
		HasAnimation.super.setAnimationEnabled(enabled);
		// here must check if the callback was set before
		// because the previous method put the animation object if enabling
		if (enabled && animationCallback != null) {
			// sets again the callback
			setAnimation(animationCallback);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/**
	 * Returns the animation callback, used to create the animation options at runtime.
	 * 
	 * @return the animation callback, used to create the animation options at runtime
	 */
	public DatasetAnimationCallback getAnimationCallback() {
		return animationCallback;
	}

	/**
	 * Sets the animation callback, used to create the animation options at runtime.
	 * 
	 * @param animationCallback the animation callback, used to create the animation options at runtime
	 */
	public void setAnimation(DatasetAnimationCallback animationCallback) {
		// sets the callback
		this.animationCallback = animationCallback;
		// checks if callback is consistent
		if (animationCallback != null) {
			// sets the callback proxy function to java script object
			setValue(InternalProperty.ANIMATION, animationCallbackProxy.getProxy());
		} else {
			// otherwise sets the animation object
			setValue(InternalProperty.ANIMATION, getAnimation());
		}
	}

	/**
	 * Returns the unique id of datasets.
	 * 
	 * @return the unique id of datasets
	 */
	public final int getId() {
		return getValue(InternalProperty.CHARBA_ID, UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the scope of the dataset, which is the options are used for defaults, chart defaults or chart.
	 * 
	 * @return the scope of the dataset
	 */
	public final String getScope() {
		return scope;
	}

	/**
	 * Returns the data type of datasets.
	 * 
	 * @return the data type of datasets
	 */
	public final DataType getDataType() {
		return getValue(InternalProperty.CHARBA_DATA_TYPE, DataType.values(), DataType.UNKNOWN);
	}

	/**
	 * Returns the patterns container element.
	 * 
	 * @return the patterns container
	 */
	final PatternsContainer getPatternsContainer() {
		return patternsContainer;
	}

	/**
	 * Returns the gradients container element.
	 * 
	 * @return the gradients container
	 */
	final GradientsContainer getGradientsContainer() {
		return gradientsContainer;
	}

	/**
	 * Returns the default options instance.
	 * 
	 * @return the default options instance.
	 */
	protected final IsDefaultOptions getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is not both a gradient not a pattern, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is not both a gradient not a pattern.
	 */
	final boolean hasColors(Key key) {
		return !getPatternsContainer().hasObjects(key) && !getGradientsContainer().hasObjects(key);
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is a pattern, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is a pattern.
	 */
	final boolean hasPatterns(Key key) {
		return getPatternsContainer().hasObjects(key);
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is a gradient, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is a gradient.
	 */
	final boolean hasGradients(Key key) {
		return getGradientsContainer().hasObjects(key);
	}

	/**
	 * Removes the property key related to the color from pattern and gradient container if color is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingColors(Key key) {
		// remove from patterns
		getPatternsContainer().removeObjects(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * Removes the property key related to the color from dataset object and gradient container if pattern is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingPatterns(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
		// here must be stored!!
		setValue(key, getPatternsContainer().getProxy(key));
	}

	/**
	 * Removes the property key related to the color from dataset object and pattern container if gradient is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingGradients(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from patterns
		getPatternsContainer().removeObjects(key);
		// here must be stored!!
		setValue(key, getGradientsContainer().getProxy(key));
	}

	/**
	 * Removes the property key related to the color from dataset object and pattern and gradient containers if callback is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingCallback(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from patterns
		getPatternsContainer().removeObjects(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public void setBackgroundColor(BackgroundColorCallback backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// checks if callback is consistent
		if (backgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(CanvasObjectProperty.BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(CanvasObjectProperty.BACKGROUND_COLOR, backgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CanvasObjectProperty.BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public void setBorderColor(BorderColorCallback borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// checks if callback is consistent
		if (borderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(CanvasObjectProperty.BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(CanvasObjectProperty.BORDER_COLOR, borderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CanvasObjectProperty.BORDER_COLOR);
		}
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	final BorderWidthCallback getInternalBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	final void setInternalBorderWidth(BorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if callback is consistent
		if (borderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(CommonProperty.BORDER_WIDTH, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CommonProperty.BORDER_WIDTH);
		}
	}

	/**
	 * Returns the hover background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover background color callback, if set, otherwise <code>null</code>.
	 */
	public BackgroundColorCallback getHoverBackgroundColorCallback() {
		return hoverBackgroundColorCallback;
	}

	/**
	 * Sets the hover background color callback.
	 * 
	 * @param hoverBackgroundColorCallback the hover background color callback.
	 */
	public void setHoverBackgroundColor(BackgroundColorCallback hoverBackgroundColorCallback) {
		// sets the callback
		this.hoverBackgroundColorCallback = hoverBackgroundColorCallback;
		// checks if callback is consistent
		if (hoverBackgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(CanvasObjectProperty.HOVER_BACKGROUND_COLOR, hoverBackgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CanvasObjectProperty.HOVER_BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the hover border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border color callback, if set, otherwise <code>null</code>.
	 */
	public BorderColorCallback getHoverBorderColorCallback() {
		return hoverBorderColorCallback;
	}

	/**
	 * Sets the hover border color callback.
	 * 
	 * @param hoverBorderColorCallback the hover border color callback.
	 */
	public void setHoverBorderColor(BorderColorCallback hoverBorderColorCallback) {
		// sets the callback
		this.hoverBorderColorCallback = hoverBorderColorCallback;
		// checks if callback is consistent
		if (hoverBorderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(CanvasObjectProperty.HOVER_BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(CanvasObjectProperty.HOVER_BORDER_COLOR, hoverBorderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CanvasObjectProperty.HOVER_BORDER_COLOR);
		}
	}

	/**
	 * Returns the hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border width callback, if set, otherwise <code>null</code>.
	 */
	final BorderWidthCallback getInternalHoverBorderWidthCallback() {
		return hoverBorderWidthCallback;
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	final void setInternalHoverBorderWidth(BorderWidthCallback hoverBorderWidthCallback) {
		// sets the callback
		this.hoverBorderWidthCallback = hoverBorderWidthCallback;
		// checks if callback is consistent
		if (hoverBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(CommonProperty.HOVER_BORDER_WIDTH, hoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(CommonProperty.HOVER_BORDER_WIDTH);
		}
	}

	/**
	 * Returns the default background color value based on type of chart.
	 * 
	 * @return the default background color value based on type of chart.
	 */
	protected String getDefaultBackgroundColorAsString() {
		// returns the ARC default value because is MOSTLY used
		return getDefaultValues().getElements().getArc().getBackgroundColorAsString();
	}

	/**
	 * Returns the default border color value based on type of chart.
	 * 
	 * @return the default border color value based on type of chart.
	 */
	protected String getDefaultBorderColorAsString() {
		// returns the ARC default value because is MOSTLY used
		return getDefaultValues().getElements().getArc().getBorderColorAsString();
	}

	/**
	 * Returns the default border width value based on type of chart.
	 * 
	 * @return the default border width value based on type of chart.
	 */
	protected int getDefaultBorderWidth() {
		// returns the ARC default value because is MOSTLY used
		return getDefaultValues().getElements().getArc().getBorderWidth();
	}

	/**
	 * Sets if the dataset will appear or not.
	 * 
	 * @param hidden if the dataset will appear or not.
	 */
	protected final void setHidden(boolean hidden) {
		// checks if is hidden
		if (hidden) {
			// then sets it
			setValue(InternalProperty.HIDDEN, hidden);
		} else {
			// if is not hidden
			// remove the property
			remove(InternalProperty.HIDDEN);
		}
	}

	/**
	 * Returns if the dataset at first drawing will appear or not.
	 * 
	 * @return if the dataset at first drawing will appear or not.
	 */
	public final boolean isHidden() {
		return getValue(InternalProperty.HIDDEN, DEFAULT_HIDDEN);
	}

	/**
	 * Sets the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @param label the label for the dataset which appears in the legend and tooltips.
	 */
	public void setLabel(String label) {
		setValue(InternalProperty.LABEL, label);
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	public String getLabel() {
		return getValue(InternalProperty.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Sets how to clip relative to chartArea.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.
	 * 
	 * @param clip positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea
	 */
	public void setClip(double clip) {
		// sets value
		setValue(CommonProperty.CLIP, clip);
	}

	/**
	 * Sets how to clip relative to chartArea, by an object which configures clipping per side.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.
	 * 
	 * @param clip object which configures clipping per side
	 */
	public void setClip(Clip clip) {
		// sets value
		setValue(CommonProperty.CLIP, clip);
	}

	/**
	 * Returns how to clip relative to chartArea.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.<br>
	 * If the clip was set by a {@link Clip} object, returns {@link UndefinedValues#DOUBLE}.
	 * 
	 * @return positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.<br>
	 *         If the clip was set by a {@link Clip} object, returns {@link UndefinedValues#DOUBLE}
	 */
	public double getClip() {
		// gets the type stored
		ObjectType clipType = type(CommonProperty.CLIP);
		// checks if previously was set to a clip object
		// therefore NaN
		if (ObjectType.OBJECT.equals(clipType)) {
			// if object returns NaN
			return UndefinedValues.DOUBLE;
		}
		// gets value as number
		return getValue(CommonProperty.CLIP, Defaults.get().getGlobal().getElements().getLine().getBorderWidth() / 2D);
	}

	/**
	 * Returns how to clip relative to chartArea.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.<br>
	 * If the clip was NOT set by a {@link Clip} object, returns a {@link Clip} instance with the same values.
	 * 
	 * @return clip positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.<br>
	 *         If the clip was NOT set by a {@link Clip} object, returns a {@link Clip} instance with the same values.
	 */
	public Clip getClipAsObject() {
		// gets the type stored
		ObjectType clipType = type(CommonProperty.CLIP);
		// checks if previously was set to a number
		// therefore new object with the same values
		if (ObjectType.NUMBER.equals(clipType)) {
			// new object
			// with the same value
			return new Clip(getClip());
		}
		// creates new value with previous item
		// if there is otherwise an empyt object
		return new Clip(getValue(CommonProperty.CLIP));
	}

	/**
	 * Returns <code>true</code> if dataset must use only data points otherwise <code>false</code>.<br>
	 * The dataset which can set this capabilities, must override this method.
	 * 
	 * @return <code>true</code> if dataset must use only data points otherwise <code>false</code>
	 */
	boolean mustUseDataPoints() {
		return false;
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * 
	 * @param values an array of numbers
	 */
	public void setData(double... values) {
		// checks if it can use data as double
		if (mustUseDataPoints()) {
			// if not, exception
			throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
		}
		// set value. If null, removes key and then..
		setArrayValue(InternalProperty.DATA, ArrayDouble.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.NUMBERS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * 
	 * @param values list of numbers.
	 */
	public void setData(List<Double> values) {
		// checks if it can use data as double
		if (mustUseDataPoints()) {
			// if not, exception
			throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
		}
		// set value. If null, removes key and then..
		setArrayValue(InternalProperty.DATA, ArrayDouble.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.NUMBERS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x
	 * axis.
	 * 
	 * @return list of numbers or an empty list of numbers if the data type is not {@link DataType#NUMBERS}.
	 */
	public List<Double> getData() {
		return getData(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x
	 * axis.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return list of numbers or an empty list of numbers if the data type is not {@link DataType#NUMBERS}.
	 */
	public List<Double> getData(boolean binding) {
		// checks if it can use data as double
		if (mustUseDataPoints()) {
			// if not, exception
			throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
		}
		// checks if is a numbers data type
		if (has(InternalProperty.DATA) && DataType.NUMBERS.equals(getDataType())) {
			// returns numbers
			ArrayDouble array = getArrayValue(InternalProperty.DATA);
			// returns array
			return ArrayListHelper.list(array);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayDoubleList result = new ArrayDoubleList();
			// set value
			setArrayValue(InternalProperty.DATA, ArrayDouble.fromOrEmpty(result));
			// sets data type
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.NUMBERS);
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param factory datapoint object factory
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	final List<DataPoint> getDataPoints(DataPointFactory factory, boolean binding) {
		// checks if is a numbers data type
		if (has(InternalProperty.DATA) && DataType.POINTS.equals(getDataType())) {
			// gets array
			ArrayObject array = getArrayValue(InternalProperty.DATA);
			// returns points
			return ArrayListHelper.list(array, factory);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayObjectContainerList<DataPoint> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(InternalProperty.DATA, ArrayObject.fromOrEmpty(result));
			// sets data type
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.POINTS);
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	final void setInternalDataPoints(DataPoint... datapoints) {
		setArrayValue(InternalProperty.DATA, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	final void setInternalDataPoints(List<DataPoint> datapoints) {
		setArrayValue(InternalProperty.DATA, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param factory datapoint object factory
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	final List<TimeSeriesItem> getTimeSeriesItems(TimeSeriesItemFactory factory, boolean binding) {
		// checks if is a points data type
		if (has(InternalProperty.DATA) && DataType.POINTS.equals(getDataType())) {
			// gets array
			ArrayObject array = getArrayValue(InternalProperty.DATA);
			// returns points
			return ArrayListHelper.list(array, factory);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayObjectContainerList<TimeSeriesItem> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(InternalProperty.DATA, ArrayObject.fromOrEmpty(result));
			// sets data type
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.POINTS);
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series item.
	 * 
	 * @param timeSeriesItems an array of time series item
	 */
	final void setInternalTimeSeriesItems(TimeSeriesItem... timeSeriesItems) {
		// checks if array is consistent
		if (timeSeriesItems != null) {
			Arrays.sort(timeSeriesItems, COMPARATOR);
		}
		setArrayValue(InternalProperty.DATA, ArrayObject.fromOrNull(timeSeriesItems));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @param timeSeriesItems a list of time series items
	 */
	final void setInternalTimeSeriesItems(List<TimeSeriesItem> timeSeriesItems) {
		// checks if list is consistent
		if (timeSeriesItems != null) {
			Collections.sort(timeSeriesItems, COMPARATOR);
		}
		setArrayValue(InternalProperty.DATA, ArrayObject.fromOrNull(timeSeriesItems));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasDataset#getDataset()
	 */
	@Override
	public Dataset getDataset() {
		return this;
	}

	/**
	 * Returns the type of dataset, based on type of chart.
	 * 
	 * @return type of dataset.
	 */
	public final Type getType() {
		return type;
	}

	/**
	 * Removes the plugin options.
	 * 
	 * @param pluginId plugin id.
	 */
	public void removeOptions(String pluginId) {
		// checks if there is a stored plugin options
		if (hasOptions(pluginId)) {
			// checks plugin ids
			Key pluginIdKey = PluginIdChecker.key(pluginId);
			// removes configuration if exists
			remove(pluginIdKey);
		}
	}

	/**
	 * Sets the plugin options.
	 * 
	 * @param options plugin options used to configure the plugin
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(T options) {
		// checks if options is consistent and not a default plugin
		if (options != null && !DefaultPluginId.is(options.getPluginId())) {
			// checks plugin ids
			Key pluginIdKey = PluginIdChecker.key(options.getPluginId());
			// stores configuration
			setValue(pluginIdKey, options);
		}
	}

	/**
	 * Sets the plugin dataset configuration.<br>
	 * If dataset configuration options is <code>null</code>, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options options used to configure the plugin.<br>
	 *            Pass <code>null</code> to remove the configuration if exist.
	 * @param <T> type of plugin options to store
	 */
	public final <T extends AbstractPluginOptions> void setOptions(String pluginId, T options) {
		// checks is a default plugin
		if (!DefaultPluginId.is(pluginId)) {
			// if null, removes the configuration
			if (options == null && !DefaultPluginId.is(pluginId)) {
				// removes configuration if exists
				remove(PluginIdChecker.key(pluginId));
			} else {
				// stores configuration
				setValue(PluginIdChecker.key(pluginId), options);
			}
		}
	}

	/**
	 * Checks if there is any dataset configuration for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public final boolean hasOptions(String pluginId) {
		// checks if exists and is not a default plugin
		return has(PluginIdChecker.key(pluginId)) && !DefaultPluginId.is(pluginId);
	}

	/**
	 * Returns the plugin dataset configuration, if exist.<br>
	 * It uses a factory instance to create a plugin options.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a plugin options.
	 * @param <T> type of plugin options to return
	 * @return options instance used to configure the plugin or <code>null</code> if factory is <code>null</code>.
	 */
	public final <T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		// checks if factory argument is consistent and not a default plugin
		if (factory != null && !DefaultPluginId.is(pluginId)) {
			// creates the options by the factory
			return factory.create(getValue(PluginIdChecker.key(pluginId)), defaultValues.getPlugins());
		}
		// if here, factory is not consistent
		return null;
	}

	/**
	 * Returns the plugin options, if exist.<br>
	 * It uses a factory instance to create a plugin options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a plugin options
	 * @param <T> type of plugin options to return
	 * @return plugin options used to configure the plugin or an empty object if not exist.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public <T extends AbstractPluginOptions> T getOptions(AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent and not a default plugin
		if (factory != null && !DefaultPluginId.is(factory.getPluginId())) {
			// creates the object using the defaults options
			return factory.create(getValue(PluginIdChecker.key(factory.getPluginId())), defaultValues.getPlugins());
		}
		// if here factory is not consistent
		return null;
	}

	/**
	 * Clears the cache of patterns and gradients created by callbacks.
	 */
	final void clearCallbackPatternsAndGradients() {
		callbackGradientsContainer.clear();
		callbackPatternsContainer.clear();
	}

	/**
	 * Clears the cache ONLY of gradients created by callbacks.
	 */
	final void resetCallbackGradients() {
		callbackGradientsContainer.clear();
	}

	/**
	 * Returns the gradient configured by callback for a specific dataset and data index, for a specific property.
	 * 
	 * @param property property of dataset which have stored the gradient
	 * @param datasetIndex dataset index to get gradient
	 * @param index data index to get the gradient
	 * @return the gradient instance or <code>null</code> if not defined
	 */
	final Gradient getCallbackGradient(Key property, int datasetIndex, int index) {
		// checks consistency of key and if there is any gradient stored in cache
		if (Key.isValid(property) && !callbackGradientsContainer.isEmpty()) {
			// creates the key used to store the gradient
			String key = createCallbackCanvasObjectKey(property, datasetIndex, index);
			// access to cache to get the gradient by key
			return callbackGradientsContainer.get(key);
		}
		// if here the arguments are not consistent
		return null;
	}

	/**
	 * Returns the pattern configured by callback for a specific dataset and data index, for a specific property.
	 * 
	 * @param property property of dataset which have stored the pattern
	 * @param datasetIndex dataset index to get pattern
	 * @param index data index to get the pattern
	 * @return the pattern instance or <code>null</code> if not defined
	 */
	final Pattern getCallbackPattern(Key property, int datasetIndex, int index) {
		// checks consistency of key and if there is any pattern stored in cache
		if (Key.isValid(property) && !callbackPatternsContainer.isEmpty()) {
			// creates the key used to store the pattern
			String key = createCallbackCanvasObjectKey(property, datasetIndex, index);
			// access to cache to get the pattern by key
			return callbackPatternsContainer.get(key);
		}
		// if here the arguments are not consistent
		return null;
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param property property of dataset used to store the color
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @return a value of property as color
	 */
	protected final Object invokeColorCallback(ScriptableContext context, Scriptable<?> callback, CanvasObjectKey property, String defaultValue) {
		// checks if the context and chart are correct
		if (context != null && IsChart.isValid(context.getChart())) {
			// gets chart instance
			IsChart chart = context.getChart();
			// calls callback
			Object result = callback.invoke(chart, context);
			if (result instanceof Gradient) {
				String key = createCallbackCanvasObjectKey(property, context.getDatasetIndex(), context.getDataIndex());
				Gradient gradient = (Gradient) result;
				callbackGradientsContainer.put(key, gradient);
			} else if (result instanceof Pattern) {
				String key = createCallbackCanvasObjectKey(property, context.getDatasetIndex(), context.getDataIndex());
				Pattern pattern = (Pattern) result;
				callbackPatternsContainer.put(key, pattern);
			}
			return ScriptableUtils.handleCallbackResultAsColor(context, result, defaultValue, property.hasPattern());
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns a unique key to store canvas objects, created by callbacks, into a cache.<br>
	 * The format is <code>[property],[datasetIndex],[dataIndex]</code>.
	 * 
	 * @param property property of dataset
	 * @param datasetIndex dataset index
	 * @param index data index
	 * @return the key to use to store canvas object into cache
	 */
	private String createCallbackCanvasObjectKey(Key property, int datasetIndex, int index) {
		// checks if property is consistent
		Key.checkIfValid(property);
		// creates a builder
		StringBuilder sb = new StringBuilder();
		// adds property value
		sb.append(property.value()).append(Constants.COMMA);
		// adds dataset index getting the max with 0
		// because where the dataset is not defined, the value is integer min value
		sb.append(Math.max(datasetIndex, 0)).append(Constants.COMMA);
		// adds data index getting the max with 0
		// because where the dataset is not defined, the value is integer min value
		sb.append(Math.max(index, 0));
		return sb.toString();
	}

	/**
	 * Returns a native object as animation options when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @return a native object as animation
	 */
	private NativeObject onAnimationCallback(ScriptableContext context) {
		// gets chart instance
		IsChart chart = ScriptableUtils.retrieveChart(context, animationCallback);
		// checks if the chart is correct
		if (IsChart.isValid(chart) && animationCallback != null) {
			DatasetAnimationOptions result = animationCallback.invoke(chart, context, new DatasetAnimationOptions(getAnimation()));
			// checks if consistent
			if (result != null) {
				return result.nativeObject();
			}
		}
		// if here, returns the default of global configuration
		return Defaults.get().getGlobal().getDatasets().createAnimationOptions().nativeObject();
	}

	/**
	 * Returns the JSON representation of dataset. This is used y canvas object handler to know if the dataset has been changed.
	 * 
	 * @return JSON representation of dataset
	 */
	String toFilteredJSON() {
		return JSON.stringifyNativeObject(getNativeObject(), -1);
	}
	
	/**
	 * Creates a key for the dataset options.<br>
	 * The format is the following:<br>
	 * <br>
	 * <code>&lt;dataset-[datasetId]&gt;</code><br>
	 * <br>
	 * where dataset id if is the id of the dataset.
	 * 
	 * @param id the id of dataset
	 * @return a key for the dataet options
	 */
	private static final String createScope(int id) {
		// creates a string builder
		StringBuilder sb = new StringBuilder("dataset-");
		// formats teh key and returns it
		return sb.append(id).toString();
	}
	
	
	/**
	 * Interface to map color property which can or can not manage {@link Pattern} or {@link CanvasPatternItem}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	interface CanvasObjectKey extends Key {

		/**
		 * Returns <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them.
		 * 
		 * @return <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them
		 */
		boolean hasPattern();

	}

	/**
	 * Factory to create a data point from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DataPointFactory implements NativeObjectContainerFactory<DataPoint> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public DataPoint create(NativeObject nativeObject) {
			return new DataPoint(nativeObject);
		}

	}

	/**
	 * Factory to create a time series item from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class TimeSeriesItemFactory implements NativeObjectContainerFactory<TimeSeriesItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public TimeSeriesItem create(NativeObject nativeObject) {
			return new TimeSeriesItem(nativeObject);
		}

	}
}