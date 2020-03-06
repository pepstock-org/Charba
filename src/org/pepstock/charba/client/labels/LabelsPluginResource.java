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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-labels.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-labels.min.js
	private static final String[] CONTENT = {
		"/*",
		" Chen, Yi-Cyuan 2017-2018",
		" @license MIT",
		"*/",
		"(function(){function $Label$$(){this.renderToDataset=this.renderToDataset.bind(this)}if(\"undefined\"===typeof Chart)console.error(\"Can not find Chart object.\");else{\"function\"!=typeof Object.assign&&(Object.assign=function $Object$assign$($target$$,$varArgs$$){if(null==$target$$)throw new TypeError(\"Cannot convert undefined or null to object\");for(var $to$$=Object($target$$),$index$$=1;$index$$<arguments.length;$index$$++){var $nextSource$$=arguments[$index$$];if(null!=$nextSource$$)for(var $nextKey$$ in $nextSource$$)Object.prototype.hasOwnProperty.call($nextSource$$,",
		"$nextKey$$)&&($to$$[$nextKey$$]=$nextSource$$[$nextKey$$])}return $to$$});var $SUPPORTED_TYPES$$={};[\"pie\",\"doughnut\",\"polarArea\",\"bar\"].forEach(function($t$$){$SUPPORTED_TYPES$$[$t$$]=!0});$Label$$.prototype.setup=function $$Label$$$$setup$($chart$$,$options$$){this.chart=$chart$$;this.ctx=$chart$$.ctx;this.args={};this.barTotal={};var $chartOptions$$=$chart$$.config.options;this.options=Object.assign({position:\"default\",precision:0,fontSize:$chartOptions$$.defaultFontSize,fontColor:$chartOptions$$.defaultFontColor,",
		"fontStyle:$chartOptions$$.defaultFontStyle,fontFamily:$chartOptions$$.defaultFontFamily,shadowOffsetX:3,shadowOffsetY:3,shadowColor:\"rgba(0,0,0,0.3)\",shadowBlur:6,images:[],outsidePadding:2,textMargin:2,overlap:!0},$options$$);\"bar\"===$chart$$.config.type&&(this.options.position=\"default\",this.options.arc=!1,this.options.overlap=!0)};$Label$$.prototype.render=function $$Label$$$$render$(){this.labelBounds=[];this.chart.data.datasets.forEach(this.renderToDataset)};$Label$$.prototype.renderToDataset=",
		"function $$Label$$$$renderToDataset$($dataset$$,$index$$){this.totalPercentage=0;this.total=null;var $arg$$=this.args[$index$$];$arg$$.meta.data.forEach(function($element$$,$forEachIndex$$){this.renderToElement($dataset$$,$arg$$,$element$$,$forEachIndex$$)}.bind(this))};$Label$$.prototype.renderToElement=function $$Label$$$$renderToElement$($dataset$$,$arg$jscomp$7_label$$,$element$$,$index$$){if(this.shouldRenderToElement($arg$jscomp$7_label$$.meta,$element$$)&&(this.percentage=null,$arg$jscomp$7_label$$=",
		"this.getLabel($dataset$$,$element$$,$index$$))){var $ctx$$=this.ctx;$ctx$$.save();$ctx$$.font=Chart.helpers.fontString(this.options.fontSize,this.options.fontStyle,this.options.fontFamily);var $renderInfo$$=this.getRenderInfo($element$$,$arg$jscomp$7_label$$);this.drawable($element$$,$arg$jscomp$7_label$$,$renderInfo$$)&&($ctx$$.beginPath(),$ctx$$.fillStyle=this.getFontColor($dataset$$,$element$$,$index$$),this.renderLabel($arg$jscomp$7_label$$,$renderInfo$$));$ctx$$.restore()}};$Label$$.prototype.renderLabel=",
		"function $$Label$$$$renderLabel$($label$$,$renderInfo$$){return this.options.arc?this.renderArcLabel($label$$,$renderInfo$$):this.renderBaseLabel($label$$,$renderInfo$$)};$Label$$.prototype.renderBaseLabel=function $$Label$$$$renderBaseLabel$($label$$,$position$$){var $ctx$$=this.ctx;if(\"object\"===typeof $label$$)$ctx$$.drawImage($label$$,$position$$.x-$label$$.width/2,$position$$.y-$label$$.height/2,$label$$.width,$label$$.height);else{$ctx$$.save();$ctx$$.textBaseline=\"top\";$ctx$$.textAlign=\"center\";",
		"this.options.textShadow&&($ctx$$.shadowOffsetX=this.options.shadowOffsetX,$ctx$$.shadowOffsetY=this.options.shadowOffsetY,$ctx$$.shadowColor=this.options.shadowColor,$ctx$$.shadowBlur=this.options.shadowBlur);for(var $lines$$=$label$$.split(\"\\n\"),$i$$=0;$i$$<$lines$$.length;$i$$++)$ctx$$.fillText($lines$$[$i$$],$position$$.x,$position$$.y-this.options.fontSize/2*$lines$$.length+this.options.fontSize*$i$$);$ctx$$.restore()}};$Label$$.prototype.renderArcLabel=function $$Label$$$$renderArcLabel$($label$$,",
		"$renderInfo$$){var $ctx$$=this.ctx,$radius$$=$renderInfo$$.radius,$lines$jscomp$1_view$$=$renderInfo$$.view;$ctx$$.save();$ctx$$.translate($lines$jscomp$1_view$$.x,$lines$jscomp$1_view$$.y);if(\"string\"===typeof $label$$){$ctx$$.rotate($renderInfo$$.startAngle);$ctx$$.textBaseline=\"middle\";$ctx$$.textAlign=\"left\";$lines$jscomp$1_view$$=$label$$.split(\"\\n\");var $max$$=0,$widths$$=[],$offset$$=0;\"border\"===this.options.position&&($offset$$=($lines$jscomp$1_view$$.length-1)*this.options.fontSize/2);for(var $j_k$$=",
		"0;$j_k$$<$lines$jscomp$1_view$$.length;++$j_k$$){var $mertrics$$=$ctx$$.measureText($lines$jscomp$1_view$$[$j_k$$]);$mertrics$$.width>$max$$&&($max$$=$mertrics$$.width);$widths$$.push($mertrics$$.width)}for($j_k$$=0;$j_k$$<$lines$jscomp$1_view$$.length;++$j_k$$){var $line$$=$lines$jscomp$1_view$$[$j_k$$],$y$$=($lines$jscomp$1_view$$.length-1-$j_k$$)*-this.options.fontSize+$offset$$;$ctx$$.save();$ctx$$.rotate(($max$$-$widths$$[$j_k$$])/2/$radius$$);for(var $i$$=0;$i$$<$line$$.length;$i$$++){var $char$$=",
		"$line$$.charAt($i$$);$mertrics$$=$ctx$$.measureText($char$$);$ctx$$.save();$ctx$$.translate(0,-1*$radius$$);$ctx$$.fillText($char$$,0,$y$$);$ctx$$.restore();$ctx$$.rotate($mertrics$$.width/$radius$$)}$ctx$$.restore()}}else $ctx$$.rotate(($lines$jscomp$1_view$$.startAngle+Math.PI/2+$renderInfo$$.endAngle)/2),$ctx$$.translate(0,-1*$radius$$),this.renderLabel($label$$,{x:0,y:0});$ctx$$.restore()};$Label$$.prototype.shouldRenderToElement=function $$Label$$$$shouldRenderToElement$($meta$$,$element$$){return!$meta$$.hidden&&",
		"!$element$$.hidden&&(this.options.showZero||\"polarArea\"===this.chart.config.type?0!==$element$$._view.outerRadius:0!==$element$$._view.circumference)};$Label$$.prototype.getLabel=function $$Label$$$$getLabel$($dataset$jscomp$2_label$$,$element$$,$index$$){if(\"function\"===typeof this.options.render)$dataset$jscomp$2_label$$=this.options.render({label:this.chart.config.data.labels[$index$$],value:$dataset$jscomp$2_label$$.data[$index$$],percentage:this.getPercentage($dataset$jscomp$2_label$$,$element$$,",
		"$index$$),dataset:$dataset$jscomp$2_label$$,index:$index$$,datasetIndex:$element$$._datasetIndex,chart:this.chart});else switch(this.options.render){case \"value\":$dataset$jscomp$2_label$$=$dataset$jscomp$2_label$$.data[$index$$];break;case \"label\":$dataset$jscomp$2_label$$=this.chart.config.data.labels[$index$$];break;case \"image\":$dataset$jscomp$2_label$$=this.options.images[$index$$]?this.loadImage(this.options.images[$index$$]):\"\";break;default:$dataset$jscomp$2_label$$=this.getPercentage($dataset$jscomp$2_label$$,",
		"$element$$,$index$$)+\"%\"}\"object\"===typeof $dataset$jscomp$2_label$$?$dataset$jscomp$2_label$$=this.loadImage($dataset$jscomp$2_label$$):$dataset$jscomp$2_label$$&&($dataset$jscomp$2_label$$=$dataset$jscomp$2_label$$.toString());return $dataset$jscomp$2_label$$};$Label$$.prototype.getFontColor=function $$Label$$$$getFontColor$($dataset$$,$element$$,$index$$){var $fontColor$$=this.options.fontColor;\"function\"===typeof $fontColor$$?$fontColor$$=$fontColor$$({label:this.chart.config.data.labels[$index$$],",
		"value:$dataset$$.data[$index$$],percentage:this.getPercentage($dataset$$,$element$$,$index$$),backgroundColor:$dataset$$.backgroundColor[$index$$],dataset:$dataset$$,index:$index$$,datasetIndex:$element$$._datasetIndex,chart:this.chart}):\"string\"!==typeof $fontColor$$&&($fontColor$$=$fontColor$$[$index$$]||this.chart.config.options.defaultFontColor);return $fontColor$$};$Label$$.prototype.getPercentage=function $$Label$$$$getPercentage$($dataset$$,$element$jscomp$13_i$jscomp$5_k$$,$index$$){if(null!==",
		"this.percentage)return this.percentage;if(\"polarArea\"===this.chart.config.type){if(null===this.total)for($element$jscomp$13_i$jscomp$5_k$$=this.total=0;$element$jscomp$13_i$jscomp$5_k$$<$dataset$$.data.length;++$element$jscomp$13_i$jscomp$5_k$$)this.total+=$dataset$$.data[$element$jscomp$13_i$jscomp$5_k$$];$dataset$$=$dataset$$.data[$index$$]/this.total*100}else if(\"bar\"===this.chart.config.type){if(void 0===this.barTotal[$index$$])for($element$jscomp$13_i$jscomp$5_k$$=this.barTotal[$index$$]=0;$element$jscomp$13_i$jscomp$5_k$$<",
		"this.chart.data.datasets.length;++$element$jscomp$13_i$jscomp$5_k$$)this.barTotal[$index$$]+=this.chart.data.datasets[$element$jscomp$13_i$jscomp$5_k$$].data[$index$$];$dataset$$=$dataset$$.data[$index$$]/this.barTotal[$index$$]*100}else $dataset$$=$element$jscomp$13_i$jscomp$5_k$$._view.circumference/this.chart.config.options.circumference*100;$dataset$$=parseFloat($dataset$$.toFixed(this.options.precision));this.options.showActualPercentages||(\"bar\"===this.chart.config.type&&(this.totalPercentage=",
		"this.barTotalPercentage[$index$$]||0),this.totalPercentage+=$dataset$$,100<this.totalPercentage&&($dataset$$-=this.totalPercentage-100,$dataset$$=parseFloat($dataset$$.toFixed(this.options.precision))),\"bar\"===this.chart.config.type&&(this.barTotalPercentage[$index$$]=this.totalPercentage));return this.percentage=$dataset$$};$Label$$.prototype.getRenderInfo=function $$Label$$$$getRenderInfo$($element$$,$label$$){return\"bar\"===this.chart.config.type?this.getBarRenderInfo($element$$,$label$$):this.options.arc?",
		"this.getArcRenderInfo($element$$,$label$$):this.getBaseRenderInfo($element$$,$label$$)};$Label$$.prototype.getBaseRenderInfo=function $$Label$$$$getBaseRenderInfo$($element$$,$label$$){if(\"outside\"===this.options.position||\"border\"===this.options.position){var $rangeFromCentre_renderInfo$$,$view$$=$element$$._view,$centreAngle_offset$$=$view$$.startAngle+($view$$.endAngle-$view$$.startAngle)/2,$innerRadius$$=$view$$.outerRadius/2;\"border\"===this.options.position?$rangeFromCentre_renderInfo$$=($view$$.outerRadius-",
		"$innerRadius$$)/2+$innerRadius$$:\"outside\"===this.options.position&&($rangeFromCentre_renderInfo$$=$view$$.outerRadius-$innerRadius$$+$innerRadius$$+this.options.textMargin);$rangeFromCentre_renderInfo$$={x:$view$$.x+Math.cos($centreAngle_offset$$)*$rangeFromCentre_renderInfo$$,y:$view$$.y+Math.sin($centreAngle_offset$$)*$rangeFromCentre_renderInfo$$};\"outside\"===this.options.position&&($centreAngle_offset$$=this.options.textMargin+this.measureLabel($label$$).width/2,$rangeFromCentre_renderInfo$$.x+=",
		"$rangeFromCentre_renderInfo$$.x<$view$$.x?-$centreAngle_offset$$:$centreAngle_offset$$);return $rangeFromCentre_renderInfo$$}return $element$$.tooltipPosition()};$Label$$.prototype.getArcRenderInfo=function $$Label$$$$getArcRenderInfo$($element$$,$label$$){var $view$$=$element$$._view;var $radius$$=\"outside\"===this.options.position?$view$$.outerRadius+this.options.fontSize+this.options.textMargin:\"border\"===this.options.position?($view$$.outerRadius/2+$view$$.outerRadius)/2:($view$$.innerRadius+$view$$.outerRadius)/",
		"2;var $startAngle$$=$view$$.startAngle,$endAngle$$=$view$$.endAngle,$totalAngle$$=$endAngle$$-$startAngle$$;$startAngle$$+=Math.PI/2;$endAngle$$+=Math.PI/2;var $mertrics$$=this.measureLabel($label$$);$startAngle$$+=($endAngle$$-($mertrics$$.width/$radius$$+$startAngle$$))/2;return{radius:$radius$$,startAngle:$startAngle$$,endAngle:$endAngle$$,totalAngle:$totalAngle$$,view:$view$$}};$Label$$.prototype.getBarRenderInfo=function $$Label$$$$getBarRenderInfo$($element$$,$label$$){var $renderInfo$$=$element$$.tooltipPosition();",
		"$renderInfo$$.y-=this.measureLabel($label$$).height/2+this.options.textMargin;return $renderInfo$$};$Label$$.prototype.drawable=function $$Label$$$$drawable$($element$$,$label$jscomp$18_left$$,$bottom$jscomp$1_renderInfo$$){if(this.options.overlap)return!0;if(this.options.arc)return $bottom$jscomp$1_renderInfo$$.endAngle-$bottom$jscomp$1_renderInfo$$.startAngle<=$bottom$jscomp$1_renderInfo$$.totalAngle;var $mertrics$$=this.measureLabel($label$jscomp$18_left$$);$label$jscomp$18_left$$=$bottom$jscomp$1_renderInfo$$.x-",
		"$mertrics$$.width/2;var $right$$=$bottom$jscomp$1_renderInfo$$.x+$mertrics$$.width/2,$top$$=$bottom$jscomp$1_renderInfo$$.y-$mertrics$$.height/2;$bottom$jscomp$1_renderInfo$$=$bottom$jscomp$1_renderInfo$$.y+$mertrics$$.height/2;return\"outside\"===this.options.position?this.outsideInRange($label$jscomp$18_left$$,$right$$,$top$$,$bottom$jscomp$1_renderInfo$$):$element$$.inRange($label$jscomp$18_left$$,$top$$)&&$element$$.inRange($label$jscomp$18_left$$,$bottom$jscomp$1_renderInfo$$)&&$element$$.inRange($right$$,",
		"$top$$)&&$element$$.inRange($right$$,$bottom$jscomp$1_renderInfo$$)};$Label$$.prototype.outsideInRange=function $$Label$$$$outsideInRange$($left$$,$right$$,$top$$,$bottom$$){for(var $labelBounds$$=this.labelBounds,$i$$=0;$i$$<$labelBounds$$.length;++$i$$){for(var $bound$jscomp$2_k$$=$labelBounds$$[$i$$],$potins$$=[[$left$$,$top$$],[$left$$,$bottom$$],[$right$$,$top$$],[$right$$,$bottom$$]],$j$jscomp$1_x1$$=0;$j$jscomp$1_x1$$<$potins$$.length;++$j$jscomp$1_x1$$){var $x$jscomp$82_y1$$=$potins$$[$j$jscomp$1_x1$$][0],",
		"$y$$=$potins$$[$j$jscomp$1_x1$$][1];if($x$jscomp$82_y1$$>=$bound$jscomp$2_k$$.left&&$x$jscomp$82_y1$$<=$bound$jscomp$2_k$$.right&&$y$$>=$bound$jscomp$2_k$$.top&&$y$$<=$bound$jscomp$2_k$$.bottom)return!1}$potins$$=[[$bound$jscomp$2_k$$.left,$bound$jscomp$2_k$$.top],[$bound$jscomp$2_k$$.left,$bound$jscomp$2_k$$.bottom],[$bound$jscomp$2_k$$.right,$bound$jscomp$2_k$$.top],[$bound$jscomp$2_k$$.right,$bound$jscomp$2_k$$.bottom]];for($bound$jscomp$2_k$$=0;$bound$jscomp$2_k$$<$potins$$.length;++$bound$jscomp$2_k$$)if($j$jscomp$1_x1$$=",
		"$potins$$[$bound$jscomp$2_k$$][0],$x$jscomp$82_y1$$=$potins$$[$bound$jscomp$2_k$$][1],$j$jscomp$1_x1$$>=$left$$&&$j$jscomp$1_x1$$<=$right$$&&$x$jscomp$82_y1$$>=$top$$&&$x$jscomp$82_y1$$<=$bottom$$)return!1}$labelBounds$$.push({left:$left$$,right:$right$$,top:$top$$,bottom:$bottom$$});return!0};$Label$$.prototype.measureLabel=function $$Label$$$$measureLabel$($label$jscomp$19_lines$$){if(\"object\"===typeof $label$jscomp$19_lines$$)return{width:$label$jscomp$19_lines$$.width,height:$label$jscomp$19_lines$$.height};",
		"var $width$$=0;$label$jscomp$19_lines$$=$label$jscomp$19_lines$$.split(\"\\n\");for(var $i$$=0;$i$$<$label$jscomp$19_lines$$.length;++$i$$){var $result$$=this.ctx.measureText($label$jscomp$19_lines$$[$i$$]);$result$$.width>$width$$&&($width$$=$result$$.width)}return{width:$width$$,height:this.options.fontSize*$label$jscomp$19_lines$$.length}};$Label$$.prototype.loadImage=function $$Label$$$$loadImage$($obj$$){var $image$$=new Image;$image$$.src=$obj$$.src;$image$$.width=$obj$$.width;$image$$.height=",
		"$obj$$.height;return $image$$};Chart.plugins.register({id:\"labels\",beforeDatasetsUpdate:function($chart$$,$optionParams$$){if($SUPPORTED_TYPES$$[$chart$$.config.type]){var $options$$=$optionParams$$;Array.isArray($optionParams$$)||($options$$=[$optionParams$$]);var $count$$=$options$$.length;$chart$$._labels&&$count$$===$chart$$._labels.length||($chart$$._labels=$options$$.map(function(){return new $Label$$}));for(var $someOutside$$=!1,$maxPadding$$=0,$i$$=0;$i$$<$count$$;++$i$$){var $label$jscomp$20_padding$$=",
		"$chart$$._labels[$i$$];$label$jscomp$20_padding$$.setup($chart$$,$options$$[$i$$]);\"outside\"===$label$jscomp$20_padding$$.options.position&&($someOutside$$=!0,$label$jscomp$20_padding$$=1.5*$label$jscomp$20_padding$$.options.fontSize+$label$jscomp$20_padding$$.options.outsidePadding,$label$jscomp$20_padding$$>$maxPadding$$&&($maxPadding$$=$label$jscomp$20_padding$$))}$someOutside$$&&($chart$$.chartArea.top+=$maxPadding$$,$chart$$.chartArea.bottom-=$maxPadding$$)}},afterDatasetUpdate:function($chart$$,",
		"$args$$,$options$$){$SUPPORTED_TYPES$$[$chart$$.config.type]&&$chart$$._labels.forEach(function($label$$){$label$$.args[$args$$.index]=$args$$})},beforeDraw:function($chart$$){$SUPPORTED_TYPES$$[$chart$$.config.type]&&$chart$$._labels.forEach(function($label$$){$label$$.barTotalPercentage={}})},afterDatasetsDraw:function($chart$$){$SUPPORTED_TYPES$$[$chart$$.config.type]&&$chart$$._labels.forEach(function($label$$){$label$$.render()})}})}})();"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-labels.min.js</code> content.
	 */
	LabelsPluginResource() {
		super(ResourceName.LABELS_PLUGIN, CONTENT);
	}

}
