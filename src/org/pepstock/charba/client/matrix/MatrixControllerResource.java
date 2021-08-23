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
			"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self).Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";function i(t){return t&&\"object\"==typeof t&&\"default\"in t?t:{default:t}}var r=i(t);class n extends t.DatasetController{initialize(){this.enableOptionSharing=!0,super.initialize()}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements(t,e,i,r){const n=this,o=\"reset\"===r,{xScale:h,yScale:s}=n._cachedMeta,a=n.resolveDataElementOptions(e,r),l=n.getSharedOptions(r,t[e],a);for(let a=e;a<e+i;a++){const e=!o&&n.getParsed(a),i=o?h.getBasePixel():h.getPixelForValue(e.x),l=o?s.getBasePixel():s.getPixelForValue(e.y),d=n.resolveDataElementOptions(a,r),{width:c,height:u,anchorX:f,anchorY:g}=d,p={x:\"left\"===f?i:i-c/(\"right\"===f",
			"?1:2),y:\"top\"===g?l:l-u/(\"bottom\"===g?1:2),width:c,height:u,options:d};n.updateElement(t[a],a,p,r)}n.updateSharedOptions(l,r)}draw(){const t=this,e=t.getMeta().data||[];let i,r;for(i=0,r=e.length;i<r;++i)e[i].draw(t._ctx)}}function o(t,e){const{x:i,y:r,width:n,height:o}=t.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{left:i,top:r,right:i+n,bottom:r+o}}function h(t,e,i){return Math.max(Math.min(t,i),e)}function s(t){const i=o(t),r=i.right-i.left,n=i.bottom-i.top,s=function(t,i,r){const n=t.options.borderWidth;let o,s,a,l;return e.isObject(n)?(o=+n.top||0,s=+n.right||0,a=+n.bottom||0,l=+n.left||0):o=s=a=l=+n||0,{t:h(o,0,r),r:h(s,0,i),b:h(a,0,r),l:h(l,0,i)}}(t,r/2,n/2);return{outer:{x:i.left,y:i.top,w:r,h:n},inner:{x:i.left+s.l,y:i.top+s.t,w:r-s.l-s.r,h:n-s.t-s.b}}}function a(t,e,i,r){const n=null===e,h=null===i,s=!(!t||n&&h)&&o(t,r);return s&&(n||e>=s.left&&e<=s.right)&&(h||i>=s.top&&i<=s.bottom)}n.id=\"matrix\",n.version=\"1.0.2\",n.defaults={dataElementType:\"matrix\",animat",
			"ions:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"width\",\"height\"]}},anchorX:\"center\",anchorY:\"center\"},n.overrides={interaction:{mode:\"nearest\",intersect:!0},scales:{x:{type:\"linear\",offset:!0},y:{type:\"linear\",reverse:!0}}};class l extends t.Element{constructor(t){super(),this.options=void 0,this.width=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const e=this.options,{inner:i,outer:r}=s(this);t.save(),r.w!==i.w||r.h!==i.h?(t.beginPath(),t.rect(r.x,r.y,r.w,r.h),t.clip(),t.rect(i.x,i.y,i.w,i.h),t.fillStyle=e.backgroundColor,t.fill(),t.fillStyle=e.borderColor,t.fill(\"evenodd\")):(t.fillStyle=e.backgroundColor,t.fillRect(i.x,i.y,i.w,i.h)),t.restore()}inRange(t,e,i){return a(this,t,e,i)}inXRange(t,e){return a(this,t,null,e)}inYRange(t,e){return a(this,null,t,e)}getCenterPoint(t){const{x:e,y:i,width:r,height:n}=this.getProps([\"x\",\"y\",\"width\",\"height\"],t);return{x:e+r/2,y:i+n/2}}tooltipPosition(){return this.getCenterPoint()}getRange(t){return\"x\"=",
			"==t?this.width/2:this.height/2}}l.id=\"matrix\",l.defaults={backgroundColor:void 0,borderColor:void 0,borderWidth:void 0,anchorX:void 0,anchorY:void 0,width:20,height:20},r.default.register(n,l)}));",
			"//# sourceMappingURL=chartjs-chart-matrix.min.js.map" };

	/**
	 * Creates the injectable resource with <code>chartjs-chart-matrix.min.js</code> content.
	 */
	MatrixControllerResource() {
		super(ResourceName.MATRIX_CONTROLLER, CONTENT);
	}

}
