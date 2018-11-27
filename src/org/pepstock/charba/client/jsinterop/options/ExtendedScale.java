package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;

public class ExtendedScale extends Scale {

	public ExtendedScale(IsDefaultScale defaultValues) {
		super(defaultValues);
	}

	// ---------------
	// SCALE callbacks
	// ---------------
	public void setCallback(Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(this, property, proxy);
	}

	// ---------------
	// TICK callbacks
	// ---------------
	public void setCallback(AbstractTick<?,?> model, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(model, property, proxy);
	}

	// ---------------
	// RADIAL POINT LABELS callbacks
	// ---------------
	public void setCallback(PointLabels model, Key property, CallbackProxy.Proxy proxy) {
		setCallbackToModel(model, property, proxy);
	}
}
