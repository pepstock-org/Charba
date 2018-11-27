package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;

public final class DefaultHover implements IsDefaultHover{

	private static final boolean DEFAULT_INTERSECT = true;

	private static final int DEFAULT_ANIMATION_DURATION = 400;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return InteractionMode.nearest;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return DEFAULT_INTERSECT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#getAnimationDuration()
	 */
	@Override
	public int getAnimationDuration() {
		return DEFAULT_ANIMATION_DURATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return InteractionAxis.x;
	}

}
