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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;

public final class AnimationItem  extends BaseItem {
    
    private enum Property implements Key{
    	currentStep,
    	numSteps,
    	easing
    }

    /** 
     * Needed for GWt injection
     */
    protected AnimationItem() {
	}

	public final double getCurrentStep() {
        return getDouble(Property.currentStep.name());
    }
    
    public final double getNumSteps() {
        return getDouble(Property.numSteps.name());
    }

    public final Easing getEasing() {
    	return getValue(Property.easing, Easing.class, Easing.easeOutQuart);
    }

	public String toContentString()  {
		return "AnimationItem [getCurrentStep()=" + getCurrentStep() + ", getNumSteps()=" + getNumSteps() + ", getEasing()=" + getEasing() + "]";
	}

}