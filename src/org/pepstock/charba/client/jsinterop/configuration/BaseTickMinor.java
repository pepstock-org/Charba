package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.options.TickMinor;

public class BaseTickMinor extends AbstractTick<TickMinor> {

	BaseTickMinor(Axis axis, TickMinor configuration) {
		super(axis, configuration);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.AbstractTick#getTick()
	 */
	@Override
	TickMinor getDefaultTick() {
		return getAxis().getDefaultScale().getTicks().getMinor();
	}
	
}
