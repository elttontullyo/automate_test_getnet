package br.com.getnet.page;

public class Search {

	public static String TITLE_SEARCH = "h2.c-search-page__title";
	public static String TITLE_RESULTS = "c-search-page__result-title";
	public static String NEXT_PAGE; 

	
	public static String numberNextPage(int number) {
		return NEXT_PAGE = "a.page-numbers:nth-child("+number+")"; 
	}
}
