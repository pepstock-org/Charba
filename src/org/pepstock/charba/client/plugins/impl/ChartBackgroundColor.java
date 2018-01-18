package org.pepstock.charba.client.plugins.impl;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.JavaScriptObject;

public final class ChartBackgroundColor extends AbstractPlugin {
	
	static final String DEFAULT_BACKGROUND_COLOR = "white";
	
	public static final String ID = "backgroundcolor";
	
	private final String color;
	
	public ChartBackgroundColor() {
		this(DEFAULT_BACKGROUND_COLOR);
	}

	public ChartBackgroundColor(String color) {
		super();
		this.color = (color != null) ? color : DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
		ChartBackgroundColorOptions bgOptions  = new ChartBackgroundColorOptions(options, color);
		Context2d ctx = chart.getCanvas().getContext2d();
		ctx.setFillStyle(bgOptions.getBackgroundColor());
		ctx.fillRect(0, 0, chart.getCanvas().getWidth(), chart.getCanvas().getHeight());
		return true;
	}
	
	@Override
	public String getId() {
		return ID;
	}

}
