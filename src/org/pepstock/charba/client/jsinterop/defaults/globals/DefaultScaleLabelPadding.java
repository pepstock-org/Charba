package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;

public final class DefaultScaleLabelPadding implements IsDefaultPadding{

	private static final int DEFAULT_PADDING = 4;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getLeft()
	 */
	@Override
	public int getLeft() {
		return DEFAULT_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getRight()
	 */
	@Override
	public int getRight() {
		return DEFAULT_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getTop()
	 */
	@Override
	public int getTop() {
		return DEFAULT_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding#getBottom()
	 */
	@Override
	public int getBottom() {
		return DEFAULT_PADDING;
	}
}
