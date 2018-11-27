package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

public final class ExtendedOptions extends Options {

	public ExtendedOptions(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}
	
	// ---------------
	// OPTIONS properties
	// ---------------
	public void setCharbaId(String id) {
		setValue(Options.CharbaProperty.charbaId, id);
	}
	
	// ---------------
	// OPTIONS events
	// ---------------
	public void setEvent(Key property, CallbackProxy.Proxy proxy) {
		setEventToModel(this, property, proxy);
	}
	// ---------------
	// OPTIONS callbacks
	// ---------------
	public void setCallback(Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(this, property, proxy);
	}
	// ---------------
	// ANIMATION events
	// ---------------
	public void setEvent(Animation animation, Key property, CallbackProxy.Proxy proxy) {
		setEventToModel(animation, property, proxy);
	}
	// ---------------
	// LEGEND events
	// ---------------
	public void setEvent(Legend legend, Key property, CallbackProxy.Proxy proxy) {
		setEventToModel(legend, property, proxy);
	}
	// ---------------
	// LEGENDLABELS callbacks
	// ---------------
	public void setCallback(LegendLabels labels, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(labels, property, proxy);
	}

	// ---------------
	// TOOLTIPS callbacks
	// ---------------
	public void setCallback(Tooltips tooltips, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(tooltips, property, proxy);
	}
	
	// ---------------
	// TOOLTIPSCALLBACKS callbacks
	// ---------------
	public void setCallback(TooltipsCallbacks tooltips, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(tooltips, property, proxy);
	}

}
