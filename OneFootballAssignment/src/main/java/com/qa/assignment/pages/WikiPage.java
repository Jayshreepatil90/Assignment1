package com.qa.assignment.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.assignment.utils.Constants;
import com.qa.assignment.utils.ElementUtil;


public class WikiPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
  
	// private By locator
  private By searchBox = By.name("search");
  private By searchIcon = By.id("searchButton");
  private By suggesstionList = By.xpath("//div[@class='suggestions-results']/a");
  
  
   // 2. Page constructor
  
   public WikiPage(WebDriver driver) {
	   this.driver = driver;
	   eleUtil = new ElementUtil(driver);
   }
   
   
   public String getWikiPageTitle() {
	   return eleUtil.doGetPageTitleContains(Constants.WIKI_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
   }
  
   
   public String getWikiPageUrl() {
	   return eleUtil.waitForUrlContains(Constants.WIKI_PAGE_URL,Constants.DEFAULT_TIME_OUT);
   }

   public boolean iSSearchTextFieldPresent() {
	   return eleUtil.doIsDisplayed(searchIcon);
   }
   
   public ResultsPage searchFromSuggessions(String value,String mainSearchItem) {
	   System.out.println("Main search item : " + mainSearchItem);
	   eleUtil.doSendKeys(searchBox, value);
	   List<WebElement> searchList = eleUtil.waitForElementsVisible(suggesstionList, 5);
	   System.out.println(" second Print : " + mainSearchItem);
	   for (WebElement e : searchList ) {
		   String text = e.getText();
		   System.out.println(" text Print : " + text);   
		   if(text.contains(mainSearchItem)) {
			   e.click();
		   }
	   }
	   return new ResultsPage(driver); 
   }
   
  /* public ResultsPage doSearch(String text ) {
	   
	   if(iSSearchTextFieldPresent()) {
	   eleUtil.doSendKeys(searchBox, text);
	   eleUtil.doClick(searchIcon);
       }
	  return new ResultsPage(driver);
	   	
   }*/
   
   
}


