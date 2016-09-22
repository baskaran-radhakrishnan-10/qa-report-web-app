/**
 * @name		jQuery Pagination Plugin
 * @author		Baskaran Radhakrishnan
 * @version 	1.0
 * @url			-------------------------
 * @license		Madrone S/W Technologies PVT LTD
 */

(function($){
	
	// Creating the plugin
	$.fn.pagination = function(prop){
		
		var options = $.extend({
			totalNoOfColumns : 0 ,
			columnsPerPage   : 0 ,
			currentIndex     : 0
		},prop);
		
		$(this).html("");
		
		// Initialize the plugin
		constructPager(this, options);
		
		return this;
	};
	
	function constructPager(elem,options){
		
		var totalNoOfColumns=options.totalNoOfColumns;
		var columnsPerPage=options.columnsPerPage;
		var currentPageIndex=options.currentIndex;
		var noOfSplitUp=Math.ceil(totalNoOfColumns/columnsPerPage);
		var startIndex=(currentPageIndex*columnsPerPage)+1;
		var endIndex=(currentPageIndex*columnsPerPage)+(columnsPerPage);
		if(endIndex > totalNoOfColumns){
			endIndex=totalNoOfColumns;
		}
		if(currentPageIndex > 0){
			elem.append('<li><a id="navLiColumnPrev" href="#" onclick="return navPrevButton('+parseInt(currentPageIndex-1)+');"><i class="fa fa-angle-left"></i></a></li>');
		}else{
			elem.append('<li class="disabled"><a id="navLiColumnPrev" href="#"><i class="fa fa-angle-left"></i></a></li>');
		}
		
		for (var index=startIndex; index <= endIndex; index++) { 

			if(index == startIndex){
				elem.append('<li id="navigationClick_'+index+'" class="active"><a href="#" onclick="return navLiButton('+index+');">'+index+'</a></li>');
				continue;
			}

			elem.append('<li id="navigationClick_'+index+'" ><a href="#" onclick="return navLiButton('+index+');">'+index+'</a></li>');

		}

		if(currentPageIndex < (noOfSplitUp-1)){
			elem.append('<li><a id="navLiColumnNext" href="#" onclick="return navNextButton('+parseInt(currentPageIndex+1)+');"><i class="fa fa-angle-right"></i></a></li>');
		}else{
			elem.append('<li class="disabled"><a id="navLiColumnNext" href="#"><i class="fa fa-angle-right"></i></a></li>');
		}
		
	}
	
})(jQuery);