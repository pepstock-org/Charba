package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.jsinterop.options.Legend;

public final class DefaultChartLegend implements IsDefaultLegend {

	private final Legend legend;
	
	private final DefaultChartLegendLabels labels;

	/**
	 * @param legend
	 */
	DefaultChartLegend(Legend legend) {
		this.legend = legend;
		labels = new DefaultChartLegendLabels(legend.getLabels());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend#getLabels()
	 */
	@Override
	public IsDefaultLegendLabels getLabels() {
		return labels;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return legend.isDisplay();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return legend.isFullWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return legend.isReverse();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend#getPosition()
	 */
	@Override
	public Position getPosition() {
		return legend.getPosition();
	}
	
}
