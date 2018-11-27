package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;

public final class DefaultLayout implements IsDefaultLayout{
	
	private final DefaultPadding padding = new DefaultPadding();

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}
}
