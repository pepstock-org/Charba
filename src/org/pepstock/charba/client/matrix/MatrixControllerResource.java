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
package org.pepstock.charba.client.matrix;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-chart-matrix.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class MatrixControllerResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-chart-matrix.min.js
	private static final String[] CONTENT = { "/*!", " * chartjs-chart-matrix v1.0.2", " * https://chartjs-chart-matrix.pages.dev/", " * (c) 2021 Jukka Kurkela", " * Released under the MIT license", " */",
			"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self).Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";function i(t){return t&&\"object\"==typeof t&&\"default\"in t?t:{default:t}}var r=i(t);class o extends t.DatasetController{initialize(){this.enableOptionSharing=!0,super.initialize()}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements(t,e,i,r){const o=this,n=\"reset\"===r,{xScale:a,yScale:h}=o._cachedMeta,s=o.resolveDataElementOptions(e,r),d=o.getSharedOptions(r,t[e],s);for(let s=e;s<e+i;s++){const e=!n&&o.getParsed(s),i=n?a.getBasePixel():a.getPixelForValue(e.x),d=n?h.getBasePixel():h.getPixelForValue(e.y),l=o.resolveDataElementOptions(s,r),{width:u,height:c,anchorX:g,anchorY:f}=l,p={x:\"left\"===g?i:i-u/(\"right\"===g",
			"?1:2),y:\"top\"===f?d:d-c/(\"bottom\"===f?1:2),width:u,height:c,options:l};o.updateElement(t[s],s,p,r)}o.updateSharedOptions(d,r)}draw(){const t=this,e=t.getMeta().data||[];let i,r;for(i=0,r=e.length;i<r;++i)e[i].draw(t._ctx)}}function n(t,e){const{x:i,y:r,width:o,height:n}=t.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{left:i,top:r,right:i+o,bottom:r+n}}function a(t,e,i){return Math.max(Math.min(t,i),e)}function h(t){const i=n(t),r=i.right-i.left,o=i.bottom-i.top,h=function(t,i,r){const o=t.options.borderWidth;let n,h,s,d;return e.isObject(o)?(n=+o.top||0,h=+o.right||0,s=+o.bottom||0,d=+o.left||0):n=h=s=d=+o||0,{t:a(n,0,r),r:a(h,0,i),b:a(s,0,r),l:a(d,0,i)}}(t,r/2,o/2);return{outer:{x:i.left,y:i.top,w:r,h:o},inner:{x:i.left+h.l,y:i.top+h.t,w:r-h.l-h.r,h:o-h.t-h.b}}}function s(t,e,i,r){const o=null===e,a=null===i,h=!(!t||o&&a)&&n(t,r);return h&&(o||e>=h.left&&e<=h.right)&&(a||i>=h.top&&i<=h.bottom)}o.id=\"matrix\",o.version=\"1.0.2\",o.defaults={dataElementType:\"matrix\",animat",
			"ions:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"width\",\"height\"]}},anchorX:\"center\",anchorY:\"center\"},o.overrides={interaction:{mode:\"nearest\",intersect:!0},scales:{x:{type:\"linear\",offset:!0},y:{type:\"linear\",reverse:!0}}};class d extends t.Element{constructor(t){super(),this.options=void 0,this.width=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const i=this.options,{inner:r,outer:o}=h(this),n=e.toTRBLCorners(i.borderRadius);t.save(),o.w!==r.w||o.h!==r.h?(t.beginPath(),e.addRoundedRectPath(t,{x:o.x,y:o.y,w:o.w,h:o.h,radius:n}),e.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:n}),t.fillStyle=i.backgroundColor,t.fill(),t.fillStyle=i.borderColor,t.fill(\"evenodd\")):(t.beginPath(),e.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:n}),t.fillStyle=i.backgroundColor,t.fill()),t.restore()}inRange(t,e,i){return s(this,t,e,i)}inXRange(t,e){return s(this,t,null,e)}inYRange(t,e){return s(this,null,t,e)}getCenterPoint(t){const{x:e,y:i,width:r,height:o",
			"}=this.getProps([\"x\",\"y\",\"width\",\"height\"],t);return{x:e+r/2,y:i+o/2}}tooltipPosition(){return this.getCenterPoint()}getRange(t){return\"x\"===t?this.width/2:this.height/2}}d.id=\"matrix\",d.defaults={backgroundColor:void 0,borderColor:void 0,borderWidth:void 0,borderRadius:0,anchorX:void 0,anchorY:void 0,width:20,height:20},r.default.register(o,d)}));",
			"//# sourceMappingURL=chartjs-chart-matrix.min.js.map" };

	/**
	 * Creates the injectable resource with <code>chartjs-chart-matrix.min.js</code> content.
	 */
	MatrixControllerResource() {
		super(ResourceName.MATRIX_CONTROLLER, CONTENT);
	}

}
