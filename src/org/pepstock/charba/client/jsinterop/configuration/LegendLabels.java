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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.callbacks.LegendFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.jsinterop.items.LegendItem;
import org.pepstock.charba.client.jsinterop.items.LegendLabelItem;
import org.pepstock.charba.client.jsinterop.options.EventableOptions;
import org.pepstock.charba.client.jsinterop.options.LegendLabels.FilterCallback;
import org.pepstock.charba.client.jsinterop.options.LegendLabels.GenerateLabelsCallback;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabels extends ConfigurationContainer<EventableOptions> {

	private final GenerateLabelsCallback generateLabelsCallback;
	
	private final FilterCallback filterCallback;
	
	private LegendLabelsCallback labelsCallBack = null;

	private LegendFilterHandler filterHandler = null;
	
	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	LegendLabels(AbstractChart<?, ?> chart, EventableOptions options) {
		super(chart, options);
		
		generateLabelsCallback = new GenerateLabelsCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.LegendLabels.GenerateLabelsCallback#call(java.lang.Object, java.lang.Object)
			 */
			@Override
			public LegendLabelItem[] call(Object context, Object chart) {
				// checks if callback is consistent
				if (labelsCallBack != null) {
					// calls callback
					LegendLabelItem[] result = labelsCallBack.generateLegendLabels(getChart());
					// if result is null..
					if (result == null) {
						// .. returns a empty array.
						return new LegendLabelItem[0];
					}
					// returns the generated array of legend items.
					return result;
				}
				// returns a empty array
				return new LegendLabelItem[0];
			}
			//FIXME
//	    	var self = this;
//		    options.generateLabels = function(chart){
//		    	// calls the default generateLabels function
//		    	// to get the default.
//		    	var labels = $wnd.Chart.defaults.global.legend.labels.generateLabels(chart);
//		        var newLabels = self.@org.pepstock.charba.client.options.LegendLabels::generateLegendLabels()();
//		        // checke if array is empty
//		        // if empty, returns the default labels.
//		        if (newLabels.length == 0){
//		        	return labels;
//		        } else {
//		        	return newLabels;
//		        }
//		    }

		};
		
		filterCallback = new FilterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.LegendLabels.FilterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public boolean call(Object context, LegendItem item) {
				// checks if callback is consistent
				if (filterHandler != null) {
					// calls callback
					return filterHandler.onFilter(getChart(), item);
				}
				return true;
			}
			
		};
	}

	/**
	 * @return the legendCallBack
	 */
	public LegendLabelsCallback getLabelsCallBack() {
		return labelsCallBack;
	}

	/**
	 * @param legendLabelsCallBack the legendCallBack to set
	 */
	public void setLabelsCallBack(LegendLabelsCallback labelsCallBack) {
		if (labelsCallBack == null) {
			getConfiguration().getLegend().getLabels().setGenerateLabelsCallback(null);
		} else {
			getConfiguration().getLegend().getLabels().setGenerateLabelsCallback(generateLabelsCallback);
		}
		this.labelsCallBack = labelsCallBack;
	}

	/**
	 * @return the legendFilterHandler
	 */
	public LegendFilterHandler getFilterHandler() {
		return filterHandler;
	}

	/**
	 * @param filterHandler the legendFilterHandler to set
	 */
	public void setLegendFilterHandler(LegendFilterHandler filterHandler) {
		if (filterHandler == null) {
			getConfiguration().getLegend().getLabels().setFilterCallback(null);
		} else {
			getConfiguration().getLegend().getLabels().setFilterCallback(filterCallback);
		}
		this.filterHandler = filterHandler;
	}
	
	/**
	 * Sets the font size for label.
	 * 
	 * @param fontSize Font size for label.
	 */
	public void setFontSize(int fontSize) {
		getConfiguration().getLegend().getLabels().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for label.
	 * 
	 * @return Font size for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getFontSize() {
		return getConfiguration().getLegend().getLabels().getFontSize();
	}

	/**
	 * Sets the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getConfiguration().getLegend().getLabels().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getConfiguration().getLegend().getLabels().getFontStyle();
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(IsColor fontColor) {
		getConfiguration().getLegend().getLabels().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(String fontColor) {
		getConfiguration().getLegend().getLabels().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public String getFontColorAsString() {
		return getConfiguration().getLegend().getLabels().getFontColorAsString();
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public IsColor getFontColor() {
		return getConfiguration().getLegend().getLabels().getFontColor();
	}

	/**
	 * Sets the font family for the label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the label, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		getConfiguration().getLegend().getLabels().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the label, follows CSS font-family options.
	 * 
	 * @return Font family for the label, follows CSS font-family options. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public String getFontFamily() {
		return getConfiguration().getLegend().getLabels().getFontFamily();
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		getConfiguration().getLegend().getLabels().setUsePointStyle(usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public boolean isUsePointStyle() {
		return getConfiguration().getLegend().getLabels().isUsePointStyle();
	}

	/**
	 * Sets the width of coloured box.
	 * 
	 * @param boxWidth width of coloured box.
	 */
	public void setBoxWidth(int boxWidth) {
		getConfiguration().getLegend().getLabels().setBoxWidth(boxWidth);
	}

	/**
	 * Returns the width of coloured box.
	 * 
	 * @return width of coloured box. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getBoxWidth() {
		return getConfiguration().getLegend().getLabels().getBoxWidth();
	}
	
	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		getConfiguration().getLegend().getLabels().setPadding(padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getPadding() {
		return getConfiguration().getLegend().getLabels().getPadding();
	}

}