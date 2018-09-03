package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.ChartNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.ScaleItem;

public final class StyleElement extends DatasetItem {
	
	private final ChartNode chartNode;
	
	private final InternalScaleItem xScale;
	
	private final InternalScaleItem yScale;

	/**
	 * Needed for GWt injection
	 */
	private enum Property implements Key
	{
		_chart,
		_xScale,
		_yScale
	}

	StyleElement(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		if (has(Property._chart)) {
			chartNode = new ChartNode((GenericJavaScriptObject) getValue(Property._chart));
		} else {
			chartNode = null;
		}
		if (has(Property._xScale)) {
			xScale = new InternalScaleItem((GenericJavaScriptObject) getValue(Property._xScale));
		} else {
			xScale = null;
		}
		if (has(Property._yScale)) {
			yScale = new InternalScaleItem((GenericJavaScriptObject) getValue(Property._yScale));
		} else {
			yScale = null;
		}
	}
	
	public ChartNode getChartNode() {
		return chartNode;
	}

	public ScaleItem getXScale() {
		return xScale;
	}

	public ScaleItem getYScale() {
		return yScale;
	}
	
	GenericJavaScriptObject getObject() {
		return super.getJavaScriptObject();
	}
	
	protected static class InternalScaleItem extends ScaleItem{

		protected InternalScaleItem(GenericJavaScriptObject javaScriptObject) {
			super(javaScriptObject);
		}
		
	}
	
}
