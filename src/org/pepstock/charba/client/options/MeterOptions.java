/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.LegendCallback;
import org.pepstock.charba.client.commons.Color;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Specific options for DOUGHNUT chart. 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MeterOptions extends AbstractPieOptions {
	
	public static final String DEFAULT_FORMAT = "##0";
	
	public static final String DEFAULT_LABEL_COLOR = new Color(128, 128, 128).toRGBA();
	
	private static final double DEFAULT_CUTOUT_PERCENTAGE = 90D;
	
	private boolean showPercentage = false;
	
	private boolean showLabel = false;
	
	private String format = DEFAULT_FORMAT;
	
	private String fontFamily = Defaults.getGlobal().getDefaultFontFamily();
	
	private FontStyle fontStyle = FontStyle.normal;
	
	private String labelFontColor = DEFAULT_LABEL_COLOR;
	
	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public MeterOptions(AbstractChart<?, ?> chart) {
		super(chart);
		super.getLegend().setDisplay(false);
		super.getTitle().setDisplay(false);
		super.getTooltips().setEnabled(false);
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.AbstractPieOptions#setCutoutPercentage(double)
	 */
	@Override
	public void setCutoutPercentage(double cutoutPercentage) {
		// ignore the passed value. is ALWAYS 90
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getHover()
	 */
	@Override
	public Hover getHover() {
		throw new UnsupportedOperationException("Not usable into meter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getLayout()
	 */
	@Override
	public Layout getLayout() {
		throw new UnsupportedOperationException("Not usable into meter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getLegend()
	 */
	@Override
	public Legend getLegend() {
		throw new UnsupportedOperationException("Not usable into meter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getTitle()
	 */
	@Override
	public Title getTitle() {
		throw new UnsupportedOperationException("Not usable into meter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getTooltips()
	 */
	@Override
	public Tooltips getTooltips() {
		throw new UnsupportedOperationException("Not usable into meter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#setLegendCallBack(org.pepstock.charba.client.callbacks.LegendCallback)
	 */
	@Override
	public void setLegendCallBack(LegendCallback legendCallBack) {
		throw new UnsupportedOperationException("Not usable into meter chart");
	}

	/**
	 * @return the showLabel
	 */
	public boolean isShowLabel() {
		return showLabel;
	}

	/**
	 * @param showLabel the showLabel to set
	 */
	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
	}

	/**
	 * @return the showPercentage
	 */
	public boolean isShowPercentage() {
		return showPercentage;
	}

	/**
	 * @param showPercentage the showPercentage to set
	 */
	public void setShowPercentage(boolean showPercentage) {
		this.showPercentage = showPercentage;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the fontFamily
	 */
	public String getFontFamily() {
		return fontFamily;
	}

	/**
	 * @param fontFamily the fontFamily to set
	 */
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	/**
	 * @return the fontStyle
	 */
	public FontStyle getFontStyle() {
		return fontStyle;
	}

	/**
	 * @param fontStyle the fontStyle to set
	 */
	public void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}

	/**
	 * @return the labelFontColor
	 */
	public String getLabelFontColor() {
		return labelFontColor;
	}

	/**
	 * @param labelFontColor the labelFontColor to set
	 */
	public void setLabelFontColor(String labelFontColor) {
		this.labelFontColor = labelFontColor;
	}
}