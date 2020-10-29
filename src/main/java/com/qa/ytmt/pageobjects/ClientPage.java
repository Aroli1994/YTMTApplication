package com.qa.ytmt.pageobjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientPage {

	WebDriver ldriver;

	@FindBy(xpath = "//a[@href='/ytm/admin']")
	WebElement lnkAdmin;

	@FindBy(xpath = "//nav[@id='sidebar']/div/div[2]//li[4]/a")
	WebElement lnkProjects;

	@FindBy(xpath = "//a[@href='/ytm/admin/clients']")
	WebElement lnkClients;

	@FindBy(xpath = "//a[@id='btnNewProgram']")
	WebElement lnkNew;

	@FindBy(id = "name")
	WebElement txtClientName;

	@FindBy(id = "contactName")
	WebElement txtContactName;

	@FindBy(id = "country")
	WebElement txtCountry;

	@FindBy(xpath = "//button[@type='submit']") // button[text()='Save'] ;;; //button[contains(text(),'Save')]
												// //button[contains(@type,'sub')]
	WebElement btnSave;

	@FindBy(id = "btnCancel")
	WebElement btnCancel;

	@FindBy(xpath = "//div[contains(@class,'alert-success')]/span")
	WebElement lblSuccessMessage;

	@FindBy(id = "btnBack")
	WebElement btnBack;

	@FindBy(id = "clients_table")
	WebElement tblClient;

	@FindBy(xpath = "//button[contains(@class,'search-trigger')]")
	WebElement btnSearch;

	@FindBy(xpath = "//input[contains(@class,'text-filter')]")
	WebElement txtSearchClient;

	@FindBy(xpath = "//td[text()='No matching records found']")
	WebElement txtNoClient;

	public ClientPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}

	public void clickAdministration() {
		explicitWait(ldriver, lnkAdmin);
		lnkAdmin.click();
	}

	public void clickProjects() {
		explicitWait(ldriver, lnkProjects);
		lnkProjects.click();
	}

	public void clickClients()  {
		explicitWait(ldriver, lnkClients);
		lnkClients.click();
	}

	public void clickNew() {
		explicitWait(ldriver, lnkNew);
		lnkNew.click();
	}

	public void setClientName(String clientName) {
		explicitWait(ldriver, txtClientName);
		txtClientName.sendKeys(clientName);
	}

	public void setContactName(String contactName) {
		explicitWait(ldriver, txtContactName);
		txtContactName.sendKeys(contactName);
	}

	public void setCountry(String country) {
		explicitWait(ldriver, txtCountry);
		Select select=new Select(txtCountry);
		select.selectByVisibleText(country);
	}

	public void clickSave() {
		btnSave.click();
	}

	public void clickCancel() {
		btnCancel.click();
	}

	public String getMessage() {
		return lblSuccessMessage.getText();
	}

	public void clickBack() {
		explicitWait(ldriver, btnBack);
		btnBack.click();
	}

	public void clickSearch() {
		explicitWait(ldriver, btnSearch);
		btnSearch.click();
	}

	public void setSearch(String clientName) {
		explicitWait(ldriver, txtSearchClient);
		txtSearchClient.sendKeys(clientName);
	}

	public boolean verifyClientInClientTable(String client) {
		List<WebElement> Rows = tblClient.findElements(By.xpath("//tbody/tr"));
		int rowsCount = Rows.size();
		System.out.println("Total rows: " + rowsCount);

		/*
		 * List<WebElement> Columns = tblClient.findElements(By.xpath("//thead/tr/th"));
		 * int columnsCount = Columns.size(); System.out.println("Total columns: " +
		 * columnsCount);
		 */
		boolean flag = false;
		for (int i = 1; i <= rowsCount; i++) {
			WebElement cellElement = tblClient.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/a"));
			String clientCellValue = cellElement.getText();
			if (clientCellValue.equals(client)) {
				System.out.println(clientCellValue + ": exists in client table");
				flag = true;
				break;
			} else {
				System.out.println(clientCellValue + ": does not exist in client table");
				flag = false;
			}
		}
		return flag;

	}

	public void printClientsFromClientTable() {
		List<WebElement> Rows = tblClient.findElements(By.xpath("//tbody/tr"));
		int rowsCount = Rows.size();
		System.out.println("Total rows: " + rowsCount);

		/*
		 * List<WebElement> Columns = tblClient.findElements(By.xpath("//thead/tr/th"));
		 * int columnsCount = Columns.size(); System.out.println("Total columns: " +
		 * columnsCount);
		 */

		System.out.println("**********Clients**************");
		for (int i = 1; i <= rowsCount; i++) {
			WebElement cellElement = tblClient.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/a"));
			String clientCellValue = cellElement.getText();
			System.out.println(clientCellValue);
		}
	}

	public void printAllCellValuesFromClientTable() {
		List<WebElement> Rows = tblClient.findElements(By.xpath("//tbody/tr"));
		int rowsCount = Rows.size();
		System.out.println("Total rows: " + rowsCount);

		List<WebElement> Columns = tblClient.findElements(By.xpath("//thead/tr/th"));
		int columnsCount = Columns.size();
		System.out.println("Total columns: " + columnsCount);

		System.out.println("**********Clients**************");
		for (int i = 1; i <= rowsCount; i++) {
			System.out.println("-------------CLIENT ROW=" + i + " VALUES----------------");
			for (int j = 1; j <= columnsCount; j++) {
				if (i > 2) {
					WebElement cellElement = tblClient.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + j + "]"));
					String clientCellValue = cellElement.getText();
					System.out.println(clientCellValue);
				} else {
					WebElement cellElement = tblClient.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + j + "]/a"));
					String clientCellValue = cellElement.getText();
					System.out.println(clientCellValue);
				}
			}
		}
	}

	public boolean searchClient(String client) {
		boolean flag = false;
		try {
			String clientValue1 = txtNoClient.getText();
			if (clientValue1.contains("No matching records found")) {
				System.out.println(clientValue1 + ": is NOT present");
				flag = false;
			}
		} catch (NoSuchElementException e) {
			WebElement element = tblClient.findElement(By.xpath("//table//tbody/tr/td/a"));
			String clientValue2 = element.getText();
			if (clientValue2.contains(client)) {
				System.out.println(clientValue2 + ": is present");
				flag = true;
			} else {
				System.out.println(clientValue2 + ": is NOT present");
				flag = false;
			}

		}
		return flag;
	}

	public void explicitWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
