package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.jsinterop.options.Elements;

public final class DefaultChartElements implements IsDefaultElements{

	private final DefaultChartArc arc;
	
	private final DefaultChartLine line;
	
	private final DefaultChartPoint point;
	
	private final DefaultChartRectangle rectangle;
	
	/**
	 * @param chartOptions
	 */
	public DefaultChartElements(Elements elements) {
		arc = new DefaultChartArc(elements.getArc());
		line = new DefaultChartLine(elements.getLine());
		point = new DefaultChartPoint(elements.getPoint());
		rectangle = new DefaultChartRectangle(elements.getRectangle());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getArc()
	 */
	@Override
	public IsDefaultArc getArc() {
		return arc;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getLine()
	 */
	@Override
	public IsDefaultLine getLine() {
		return line;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getPoint()
	 */
	@Override
	public IsDefaultPoint getPoint() {
		return point;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getRectangle()
	 */
	@Override
	public IsDefaultRectangle getRectangle() {
		return rectangle;
	}

}
