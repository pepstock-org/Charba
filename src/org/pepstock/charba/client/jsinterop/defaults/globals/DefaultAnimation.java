package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;

public final class DefaultAnimation implements IsDefaultAnimation{

	private static final int DEFAULT_DURATION = 1000;

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return Easing.easeOutQuart;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return DEFAULT_DURATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return DEFAULT_ANIMATE_ROTATE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return DEFAULT_ANIMATE_SCALE;
	}

}
