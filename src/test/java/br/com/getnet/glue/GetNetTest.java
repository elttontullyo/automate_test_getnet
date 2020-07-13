package br.com.getnet.glue;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.getnet.page.Home;
import br.com.getnet.page.Modal;
import br.com.getnet.page.Search;
import br.com.getnet.utils.Utils;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetNetTest {

	private static WebDriver driver;
	private static String LINK_WEB_SITE = "https://site.getnet.com.br/";
	private static String GECKO = "webdrive.gecko.drive";
	private static String GECKO_PATH = "/usr/local/bin";
	private static String SUPERGET_RESEARCH = "superget";
	private static String FIND_RESEARCH = "Posso contratar a condição de recebimento de 2% para qualquer maquininha?";
	private static String MODAL_TEXT_REQUIRED = "Observação: essa condição de recebimento não é válida para E-commerce";

	private List<WebElement> resultsTitle;
	private WebElement searchLabel;
	private WebElement searchInputText;
	private WebElement searchButton;
	private WebElement titleResearch;
	private WebElement nextPage;
	private WebElement modal;
	private WebElement modalText;

	
	@Before
	public void AcessPage() throws Throwable {
		System.setProperty(GECKO, GECKO_PATH);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(LINK_WEB_SITE);
        
	}
	
	@Given("^search field is visible$")
	public void search_field_is_visible() throws Throwable {
		searchLabel = driver.findElement(By.cssSelector(Home.SEARCH_FIELD));
		assertTrue("Campo de busca não encontrado", searchLabel.isDisplayed());
	
	}
	
	@When("click in the search field$")
	public void click_in_the_search_field() throws Throwable {
		searchLabel.click();
	}
	
	@When("^input text is present$")
	public void input_text_is_present() throws Throwable {
		searchInputText = driver.findElement(By.cssSelector(Home.SEARCH_INPUT_TEXT));
		assertTrue(searchInputText.isDisplayed());
	}
	
	@When("^insert text$")
	public void insert_text() throws Throwable {
		searchInputText.sendKeys(SUPERGET_RESEARCH);
	}

	@When("^click button search$")
	public void click_button_search() throws Throwable {
		searchButton = driver.findElement(By.cssSelector(Home.SEARCH_BUTTON));
		searchButton.click();
	}
	
		
	@When("^results screen appears$")
	public void results_screen_appears() throws Throwable {
		titleResearch = driver.findElement(By.cssSelector(Search.TITLE_SEARCH));
		assertTrue("Pesquisa não realizada", titleResearch.getText().contains(SUPERGET_RESEARCH));
	}
	
	@When("^seek result$")
	public void seek_result() throws Throwable {
		int numberPage = 1;
		boolean elementoEncontrado = false;
		
		while( !elementoEncontrado) {
			
			resultsTitle = driver.findElements(By.className(Search.TITLE_RESULTS));
			elementoEncontrado = Utils.isTextPresent(resultsTitle, FIND_RESEARCH);			
			
			if(!elementoEncontrado) {
				try {
					numberPage++;
					nextPage = driver.findElement(By.cssSelector(Search.numberNextPage(numberPage)));
					JavascriptExecutor script = (JavascriptExecutor) driver;
					script.executeScript("arguments[0].scrollIntoView(true);", nextPage);
					script.executeScript("arguments[0].click();", nextPage);
					Thread.sleep(5000);
				}catch (NoSuchElementException e ) {
					assertTrue("Nenhum resultado encontrado para pesquisa", elementoEncontrado);
				}
			}
		}
		assertTrue(elementoEncontrado);
	}
	
	@When("^click on result$")
	public void click_on_result() throws Throwable {
		WebElement elementSearch = driver.findElement(By.linkText(FIND_RESEARCH));
		elementSearch.click();
	}
	
	@When("^modal is open$")
	public void modal_is_open() throws Throwable {
		modal = driver.findElement(By.xpath(Modal.MODAL_OPEN_XPATH));
		assertTrue("Modal not showed", modal.isDisplayed());
	}
	
	
	@Then("^message expected displayed$")
	public void message_expected_displayed() throws Throwable {
		modalText = driver.findElement(By.xpath(Modal.MODAL_TEXT_XPATH));
		assertTrue("Message not found", modalText.getText().contains(MODAL_TEXT_REQUIRED));
	}
}
