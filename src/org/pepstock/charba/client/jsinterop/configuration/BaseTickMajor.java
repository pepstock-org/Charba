package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.options.TickMajor;

public class BaseTickMajor extends AbstractTick<TickMajor> {

	BaseTickMajor(Axis axis, TickMajor configuration) {
		super(axis, configuration);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.AbstractTick#getTick()
	 */
	@Override
	TickMajor getDefaultTick() {
		return getAxis().getDefaultScale().getTicks().getMajor();
	}
	
}
