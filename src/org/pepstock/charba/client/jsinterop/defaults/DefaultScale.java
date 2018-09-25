package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;

public final class DefaultScale implements IsDefaultScale{
	
	private static final boolean DEFAULT_DISPLAY = true;
	// default offset
	private static final boolean DEFAULT_OFFSET = false;
	// default weight
	private static final int DEFAULT_WEIGHT = 0;
	
	private static final double DEFAULT_BAR_PERCENTAGE = 0.9F;

	private static final double DEFAULT_CATEGORY_PERCENTAGE = 0.8F;

	private static final int DEFAULT_BAR_THICKNESS = 0;

	private static final int DEFAULT_MAX_BAR_THICKNESS = 0;

	// default if is stacked
	private static final boolean DEFAULT_STACKED = false;
	
	private final DefaultAngleLines angleLines = new DefaultAngleLines();
	
	private final DefaultGridLines gridLines = new DefaultGridLines();
	
	private final DefaultPointLabels pointLabels = new DefaultPointLabels();
	
	private final DefaultScaleLabel scaleLabel = new DefaultScaleLabel();
	
	private final DefaultTicks ticks = new DefaultTicks();
	
	private final DefaultTime time = new DefaultTime();

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getAngleLines()
	 */
	@Override
	public IsDefaultAngleLines getAngleLines() {
		return angleLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getGrideLines()
	 */
	@Override
	public IsDefaultGridLines getGrideLines() {
		return gridLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getPointLabels()
	 */
	@Override
	public IsDefaultPointLabels getPointLabels() {
		return pointLabels;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getScaleLabel()
	 */
	@Override
	public IsDefaultScaleLabel getScaleLabel() {
		return scaleLabel;
	}
	

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getTicks()
	 */
	@Override
	public IsDefaultTicks getTicks() {
		return ticks;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getTime()
	 */
	@Override
	public IsDefaultTime getTime() {
		return time;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isStacked()
	 */
	@Override
	public boolean isStacked() {
		return DEFAULT_STACKED;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getType()
	 */
	@Override
	public String getType() {
		return AxisType.linear.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getWeight()
	 */
	@Override
	public int getWeight() {
		return DEFAULT_WEIGHT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return DEFAULT_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getPosition()
	 */
	@Override
	public String getPosition() {
		return Position.top.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBarPercentage()
	 */
	@Override
	public double getBarPercentage() {
		return DEFAULT_BAR_PERCENTAGE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getCategoryPercentage()
	 */
	@Override
	public double getCategoryPercentage() {
		return DEFAULT_CATEGORY_PERCENTAGE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBarThickness()
	 */
	@Override
	public int getBarThickness() {
		return DEFAULT_BAR_THICKNESS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getMaxBarThickness()
	 */
	@Override
	public int getMaxBarThickness() {
		return DEFAULT_MAX_BAR_THICKNESS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getDistribution()
	 */
	@Override
	public String getDistribution() {
		return ScaleDistribution.linear.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBounds()
	 */
	@Override
	public String getBounds() {
		return ScaleBounds.data.name();
	}
	
}
