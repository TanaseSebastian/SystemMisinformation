/**
 * 
 */

 function changeSearch(searchType){
	 var formNews = document.getElementById("newsSearch");
	var formSource = document.getElementById("sourceSearch");
	 switch(searchType){
		 case"news": 
		 	formNews.style.display = "inline";
		 	formSource.style.display = "none";
			 break;
		 case"source": 
		 	formNews.style.display = "none";
		 	formSource.style.display = "inline";
			 break;		 
		 	
		 	
	 }
 }