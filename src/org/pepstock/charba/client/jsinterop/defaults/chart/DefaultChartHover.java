package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;
import org.pepstock.charba.client.jsinterop.options.Hover;

public final class DefaultChartHover implements IsDefaultHover {
	
	private final Hover hover;

	/**
	 * @param hover
	 */
	DefaultChartHover(Hover hover) {
		this.hover = hover;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return hover.getMode();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return hover.isIntersect();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#getAnimationDuration()
	 */
	@Override
	public int getAnimationDuration() {
		return hover.getAnimationDuration();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return hover.getAxis();
	}

}
