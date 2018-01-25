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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.pepstock.charba.client.enums.GaugeThreshold;
import org.pepstock.charba.client.enums.IsThreshold;
import org.pepstock.charba.client.enums.Threshold;

/**
 * The Meter chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Is equals of Doughnut dataset.
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.DoughnutDataset
 */
public class GaugeDataset extends MeterDataset{
	
	Logger log = Logger.getLogger("gauge");
	
	private final List<Threshold> thresholds = new LinkedList<Threshold>();
	
	private IsThreshold current = GaugeThreshold.normal.getThreshold();
	
	private boolean percentageThresholds = true;
	
	private final static Comparator<Threshold> COMPARATOR = new Comparator<Threshold>() {

		@Override
		public int compare(Threshold o1, Threshold o2) {
			return (int)(o1.getValue() - o2.getValue());
		}
	};
	
	public GaugeDataset(double max) {
		super(max);
		for (GaugeThreshold t: GaugeThreshold.values()){
			thresholds.add(t.getThreshold());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.HovingDataset#setBackgroundColor(java.lang.String[])
	 */
	@Override
	public void setBackgroundColor(String... backgroundColor) {
		super.setBackgroundColor(backgroundColor);
		checkAndSetColor();
	}

	/**
	 * @return the current
	 */
	public IsThreshold getCurrent() {
		return current;
	}

	/**
	 * @return the percentageThresholds
	 */
	public boolean isPercentageThresholds() {
		return percentageThresholds;
	}

	/**
	 * @param percentageThresholds the percentageThresholds to set
	 */
	public void setPercentageThresholds(boolean percentageThresholds) {
		this.percentageThresholds = percentageThresholds;
		current = checkLevel();
		checkAndSetColor();
	}
	
	public void setThresholds(Threshold... thres){
		thresholds.clear();
		thresholds.addAll(Arrays.asList(thres));
		current = checkLevel();
		checkAndSetColor();
	}
	
	public List<Threshold> getThresholds(){
		return thresholds;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.MeterDataset#setValue(double)
	 */
	@Override
	public void setValue(double value) {
		super.setValue(value);
		current = checkLevel();
		checkAndSetColor();
	}
	
	private void checkAndSetColor(){
		List<String> colors = getBackgroundColor();
		if (colors.isEmpty()){
			super.setBackgroundColor(getCurrent().getColor(), DEFAULT_EMPTY_VALUE_COLOR);
		} else if (colors.size() > 1){
			super.setBackgroundColor(getCurrent().getColor(), colors.get(1));
		}
	}
	
	private Threshold checkLevel(){
		if (!thresholds.isEmpty()){
			Collections.sort(thresholds, COMPARATOR);
			log.info(thresholds.toString());
			final double valueToCheck = isPercentageThresholds() ? getValue() / getMax() * 100 : getValue() ;
			double lowLimit = 0;
			for (Threshold t : thresholds){
				log.info(t.getName()+" "+t.getValue()+ " " + valueToCheck);
				if (t.isInRange(valueToCheck, lowLimit)){
					log.info("Scelto "+t.getName());
					return t;
				}
				lowLimit = t.getValue();
			}
			log.info("Scelto ultimo "+((LinkedList<Threshold>)thresholds).getLast().getName());
			return ((LinkedList<Threshold>)thresholds).getLast();
		}
		return GaugeThreshold.normal.getThreshold();
	}

}