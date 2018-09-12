package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.Window;
import org.pepstock.charba.client.jsinterop.options.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.options.elements.arc.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.options.elements.rectangle.IsDefaultRectangle;
import org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover;
import org.pepstock.charba.client.jsinterop.options.layout.padding.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle;

public final class DefaultOptions implements IsDefaultOptions{
	
	// default values
	private static final boolean DEFAULT_RESPONSIVE = true;

	private static final int DEFAULT_RESPONSIVE_ANIMATION_DURATION = 0;

	private static final boolean DEFAULT_MAINTAIN_ASPECT_RATIO = true;

	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final boolean DEFAULT_SHOW_LINES = true;

	private static final boolean DEFAULT_SPAN_GAPS = false;

	private static final double DEFAULT_CUTOUT_PERCENTAGE = 0D;

	private static final double DEFAULT_ROTATION = -0.5 * Math.PI;

	private static final double DEFAULT_CIRCUMFERENCE = 2 * Math.PI;

	private static final double DEFAULT_START_ANGLE = -0.5 * Math.PI;
	
	private final DefaultAnimation animation = new DefaultAnimation();
	
	private final DefaultHover hover = new DefaultHover();
	
	private final DefaultArc arc = new DefaultArc();
	
	private final DefaultLine line = new DefaultLine();
	
	private final DefaultPoint point = new DefaultPoint();

	private final DefaultRectangle rectangle = new DefaultRectangle();
	
	private final DefaultPadding padding = new DefaultPadding();
	
	private final DefaultTitle title = new DefaultTitle();


	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return animation;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getArc()
	 */
	@Override
	public IsDefaultArc getArc() {
		return arc;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getLine()
	 */
	@Override
	public IsDefaultLine getLine() {
		return line;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getPoint()
	 */
	@Override
	public IsDefaultPoint getPoint() {
		return point;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getRectangle()
	 */
	@Override
	public IsDefaultRectangle getRectangle() {
		return rectangle;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getHover()
	 */
	@Override
	public IsDefaultHover getHover() {
		return hover;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getTitle()
	 */
	@Override
	public IsDefaultTitle getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isResponsive()
	 */
	@Override
	public boolean isResponsive() {
		return DEFAULT_RESPONSIVE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getEvents()
	 */
	@Override
	public ArrayString getEvents() {
		return AssignHelper.serialize(Event.values());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getResponsiveAnimationDuration()
	 */
	@Override
	public int getResponsiveAnimationDuration() {
		return DEFAULT_RESPONSIVE_ANIMATION_DURATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return DEFAULT_MAINTAIN_ASPECT_RATIO;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public double getDevicePixelRatio() {
		return Window.getDevicePixelRatio();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultColor()
	 */
	@Override
	public String getDefaultColor() {
		return DEFAULT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontColor()
	 */
	@Override
	public String getDefaultFontColor() {
		return DEFAULT_FONT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontSize()
	 */
	@Override
	public int getDefaultFontSize() {
		return DEFAULT_FONT_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontStyle()
	 */
	@Override
	public String getDefaultFontStyle() {
		return FontStyle.normal.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontFamily()
	 */
	@Override
	public String getDefaultFontFamily() {
		return DEFAULT_FONT_FAMILY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isShowLines()
	 */
	@Override
	public boolean isShowLines() {
		return DEFAULT_SHOW_LINES;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return DEFAULT_SPAN_GAPS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getCutoutPercentage()
	 */
	@Override
	public double getCutoutPercentage() {
		return DEFAULT_CUTOUT_PERCENTAGE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getRotation()
	 */
	@Override
	public double getRotation() {
		return DEFAULT_ROTATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getCircumference()
	 */
	@Override
	public double getCircumference() {
		return DEFAULT_CIRCUMFERENCE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return DEFAULT_START_ANGLE;
	}
}
