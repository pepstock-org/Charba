package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.options.legend.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.options.legend.labels.IsDefaultLegendLabels;

public final class DefaultLegend implements IsDefaultLegend{

	private static final boolean DEFAULT_DISPLAY = true;

	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final boolean DEFAULT_REVERSE = false;
	
	private final DefaultLegendLabels legendLabels = new DefaultLegendLabels();

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.IsDefaultLegend#getLabels()
	 */
	@Override
	public IsDefaultLegendLabels getLabels() {
		return legendLabels;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.IsDefaultLegend#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.IsDefaultLegend#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return DEFAULT_FULL_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.IsDefaultLegend#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return DEFAULT_REVERSE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.IsDefaultLegend#getPosition()
	 */
	@Override
	public String getPosition() {
		return Position.top.name();
	}

}
