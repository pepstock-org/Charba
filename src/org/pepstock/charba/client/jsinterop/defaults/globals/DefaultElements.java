package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;

public final class DefaultElements implements IsDefaultElements{
	
	
	private final DefaultArc arc = new DefaultArc();
	
	private final DefaultLine line = new DefaultLine();
	
	private final DefaultPoint point = new DefaultPoint();

	private final DefaultRectangle rectangle = new DefaultRectangle();

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

}
