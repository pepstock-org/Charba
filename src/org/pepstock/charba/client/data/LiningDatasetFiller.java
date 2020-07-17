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

import org.pepstock.charba.client.callbacks.FillCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.FillingMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.options.Filler;

/**
 * Manages the FILL property of options for lining datasets, implementing the FILL callback for scriptable options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LiningDatasetFiller extends Filler {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the fill function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> fillCallbackProxy = JsHelper.get().newCallbackProxy();

	// fill callback instance
	private FillCallback fillCallback = null;

	/**
	 * Creates a filler with the native object where FILL property must be managed and the default value to use when the property does not exist.
	 * 
	 * @param nativeObject native object where FILL property must be managed
	 * @param defaultValue default value of FILL to use when the property does not exist
	 */
	LiningDatasetFiller(NativeObject nativeObject, IsFill defaultValue) {
		super(new DataEnvelop<NativeObject>(nativeObject), defaultValue);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		fillCallbackProxy.setCallback((contextFunction, context) -> onFill(new ScriptableContext(new DataEnvelop<NativeObject>(context))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.Filler#setFill(boolean)
	 */
	@Override
	public void setFill(boolean fill) {
		// reset callback
		setFill((FillCallback) null);
		// call set value
		super.setFill(fill);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.Filler#setFill(org.pepstock.charba.client.enums.IsFill)
	 */
	@Override
	public void setFill(IsFill fill) {
		// reset callback
		setFill((FillCallback) null);
		// call set value
		super.setFill(fill);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.Filler#getFill()
	 */
	@Override
	public IsFill getFill() {
		// checks if a callback has been set for this property
		if (getFillCallback() == null) {
			return super.getFill();
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValue();
	}

	/**
	 * Returns the fill callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the fill callback, if set, otherwise <code>null</code>.
	 */
	FillCallback getFillCallback() {
		return fillCallback;
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	void setFill(FillCallback fillCallback) {
		// sets the callback
		this.fillCallback = fillCallback;
		// checks if callback is consistent
		if (fillCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Filler.Property.FILL, fillCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Filler.Property.FILL);
		}
		// remove if exist flag
		removeIfExists(Filler.Property.CHARBA_FILLING_MODE);
	}

	/**
	 * Returns a object which can be a boolean, integer, string or {@link IsFill} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value
	 */
	private Object onFill(ScriptableContext context) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, fillCallback);
		// checks result type
		if (result instanceof Boolean) {
			// is boolean
			// cast to boolean
			return result;
		} else if (result instanceof Integer) {
			// is integer and then wants an absolute fill
			// cast to integer
			Integer resultAsInt = (Integer) result;
			// returns the absolute fill, passing thru a isFill
			return transformFill(Fill.getFill(resultAsInt));
		} else if (result instanceof IsFill) {
			// is a IsFill instance
			// cast to IsFill
			IsFill resultAsFill = (IsFill) result;
			// returns the fill
			return transformFill(resultAsFill);
		} else if (result != null) {
			// use the string representation of object
			// as relative fill
			// returns the relative fill, passing thru a isFill
			return transformFill(Fill.getFill(result.toString()));
		}
		// if here, result is null
		// then returns default
		return transformFill(getDefaultValue());
	}

	/**
	 * Transforms a {@link IsFill} instance into a CHART.JS FILL property accepted value.
	 * 
	 * @param fill fill instance to transform
	 * @return a CHART.JS FILL property accepted value
	 */
	private Object transformFill(IsFill fill) {
		// checks if is no fill
		if (Fill.FALSE.equals(fill)) {
			// boolean
			return false;
		} else if (Fill.isPredefined(fill)) {
			// is predefined
			// returns the string
			return fill.value();
		} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(fill.getMode())) {
			// is absolute
			// returns the integer
			return fill.getValueAsInt();
		} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(fill.getMode())) {
			// is relative
			// returns the string
			return fill.getValue();
		}
		// default result
		return false;
	}

}
