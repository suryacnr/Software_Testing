package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseClass;
import iSAFE.ALR;
import iSAFE.ApplicationKeywords;
import iSAFE.GOR;


public class CommonUtilities extends ApplicationKeywords {

	public CommonUtilities(BaseClass obj) {
		super(obj);
	}

	/**
	 * Description : Method to click on hyperlink tag
	 */
	public void hyperLinkTag() {
		try {
			String links = "Link#xpath=//a[@href='javascript:void(0);']";
			List<WebElement> grid = findWebElements(links);
			for (int index = 0; index < grid.size(); index++) {
				grid.get(index).getText().trim();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Description : Method to click on header buttons
	 * @param labelName is the name of the labels
	 */
	public void organizationHeaderButtons(String labelName) {
		try {
			List<WebElement> orgTabs = findWebElements(GOR.headerBtns_org);
			if (!orgTabs.isEmpty()) {
				for (int orgTab = 0; orgTab < orgTabs.size(); orgTab++) {
					String orgTabValue = orgTabs.get(orgTab).getText();
					if (orgTabValue.equals(labelName)) {
						if (orgTabs.get(orgTab).isEnabled()) {
							orgTabs.get(orgTab).click();
							testStepPassed(" Tab is clicked aganist  " + labelName);
							break;
						}
					}
				}
			} else {
				testStepFailed("Application is still loading ");
			}
		} catch (Exception e) {
			testStepFailed("Application is still loading ");
		}
	}

	/**
	 * Description : Method to click on the side tabs
	 * @param element is given for the tab names
	 * @param tabName is the name of the tabs
	 * @param isClick - true, false 
	 * @return tabPresent
	 */
	
	/*public void clickOnSideTabs(String element, String tabName, boolean isClick) {
		//boolean tabPresent = true;
	clickOn(GOR.click);
	clickOn(GOR.OwnershipTransfer);
	}*/
	public boolean clickOnSideTabs(String element, String tabName, boolean isClick) {
		boolean tabPresent = true;
		try {
			//expandMenuItems();
			
			clickOnExpandMenuButtonIfCollapsed();
			waitForAngularLoad();
			if (isElementDisplayed(element)) {
				List<WebElement> tab_labels = findWebElements(element);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					System.out.println(label_text);
					if (label_text.equals(tabName)) {
						testStepPassed("\"" + label_text + "\" Tab is displayed.");
						if (isClick) {
							Actions act = new Actions(driver);
							JavascriptExecutor jscript = (JavascriptExecutor) driver;
							jscript.executeScript("arguments[0].scrollIntoView(true);", tab_labels.get(label));
							//act.click(tab_labels.get(label)).build().perform();
							tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" Tab is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						tabPresent = false;
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + tabName + "tab.");
		}
		return tabPresent;
	}

	/**
	 * Description : Method to get the details of mandatory fields
	 * @param filedName is the name of the fields
	 * @return warningMsg  
	 */
	public boolean getMandatoryFiledDetails(String filedName) {
		boolean warningMsg = false;
		try {
			List<WebElement> tab_labels = findWebElements(GOR.mandatoryFieldsWarning_msg);
			if (tab_labels.size() > 0) {
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if ("".equals("")) {
						warningMsg = true;
						System.out.println(label_text);
						testStepInfo("\"" + label_text + "\" is displayed.");
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + filedName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + filedName + ".");
		}
		return warningMsg;
	}

	/**
	 * Description : Method to click on the add record icon
	 * @param isClick - true, false
	 */
	public void clickPlusIcon(boolean isClick) {
		try {			
			waitTime(10);
			if (isElementPresent(GOR.addNewRecord_plusIcon)) {
				waitTime(5);
				if (isClick) {
					clickOn(GOR.addNewRecord_plusIcon);
					testStepPassed("Add Record icon is clicked Successfully");
				}
			} else {
				highLighterMethod(GOR.plusIconBar);
				testStepFailed("Unable to find the add Record Icon.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the add Record Icon.");
		}
	}

	/**
	 * Description : Method to close the mandatory fields popup
	 */
	public void clickMandatoryFiledsPopupClose() {
		try {
			if (isElementPresent(GOR.mandatoryFieldsWarning_closeIcon)) {
				clickOn(GOR.mandatoryFieldsWarning_closeIcon);
				testStepPassed("Forward icon is clicked Successfully");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the forward Icon.");
		}
	}

	/**
	 * Description : Method to select the value from drop down
	 * @param value is the value of drop downs
	 */
	public void selectDropDownValue(String value) {
		try {
			waitForElement(GOR.dropDown_values);
			if (isElementPresent(GOR.dropDown_values)) {
				List<WebElement> values = findWebElements(GOR.dropDown_values);
				for (int gridValue = 0; gridValue < values.size(); gridValue++) {
					String value_rt = values.get(gridValue).getText().trim();
					if (value_rt.contains(value)) {
						values.get(gridValue).click();
						testStepPassed("\"" + value + "\" is selected from the dropdown.");
						break;
					} else if (gridValue == (values.size() - 1)) {
						highLighterMethod(GOR.dropDownValue);
						testStepFailed("Unable to find " + value);
					}
				}

			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + value);
		}
	}

	/**
	 * Description : Method to validate the save message
	 */
	public void validateSaveMessage() {
		try {

			if (isElementPresent(GOR.successMessage)) {
				if (getText(GOR.successMessage).contains("Validation Failed")) {
					//manualScreenshot("Validation Failed");
					testStepInfo("Message is displayed as :" + getText(GOR.successMessage));
				} else {
					//manualScreenshot("");
					testStepPassed("Message is displayed as :" + getText(GOR.successMessage));
				}
			}
		} catch (Exception e) {
			testStepFailed("No message is displayed.");
		}
	}

	/**
	 * Description : Method to validate the status
	 * @param status_ip is the value of status
	 * @return inwardID_rt
	 */
	public String validateStatus(String status_ip) {
		String inwardID_rt = "";
		String status_rt = "";
		try {
			waitTime(3);
			inwardID_rt = getText(GOR.generatedStatus).split("\n")[0];
			status_rt = getText(GOR.generatedStatus).split("\n")[1];
			if (status_ip.equalsIgnoreCase(status_rt)) {
				testStepInfo("\"" + status_rt + "\"  status is displayed for the Corresponding " + inwardID_rt + "");

			} else {
				highLighterMethod(GOR.generatedStatus);
				testStepFailed("Expected status is not displayed as " + status_ip);
			}
		} catch (Exception e) {
			testStepFailed("Expected status is not displayed as " + status_ip);
		}
		return inwardID_rt.split("\n")[0];
	}

	/**
	 * Description : Method to click the inward tabs
	 * @param tabName is the name of the tabs
	 * @param isClick - true, false
	 */
	public void clickInwardTabs(String tabName, boolean isClick) {
		try {
			List<WebElement> tab_labels = findWebElements(GOR.tabsList);
			for (int label = 0; label < tab_labels.size(); label++) {
				String label_text = tab_labels.get(label).getText().trim();
				if (label_text.equals(tabName)) {
					testStepPassed("\"" + label_text + "\" Tab is displayed in Menu bar.");
					if (isClick) {
						tab_labels.get(label).click();
						testStepPassed("\"" + label_text + "\" Tab is clicked successfully.");
					}
					break;
				} else if (label == (tab_labels.size() - 1)) {
					testStepFailed("Unable to find the " + tabName + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + tabName + ".");
		}
	}

	/**
	 * Description : Method to click on the footer button
	 * @param buttonName is the name of the buttons
	 * @param isClick - true, false
	 */
	public void clickFooterButton(String buttonName, boolean isClick) {
		try {
			String pageFooter_btns="footer buttons#xpath=//button[contains(text(),'"+buttonName+"')]";
			if (isElementPresent(pageFooter_btns)) {
				List<WebElement> tab_labels = findWebElements(pageFooter_btns);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equals(buttonName)) {
						testStepPassed("\"" + label_text + "\" button is displayed in footer section.");
						if (isClick) {
							Actions act = new Actions(driver);
							//act.click(tab_labels.get(label)).build().perform();
							tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" button is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						highLighterMethod(GOR.footerBtns);
						testStepFailed("Unable to find the " + buttonName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonName + ".");
		}
	}

	/**
	 * Description : Method to click on the finalize option 
	 * @param buttonName is the name of the buttons
	 * @param isClick - true, false
	 */
	public void clickFinalizeOption(String buttonName, boolean isClick) {
		try {
			if (isElementPresent(GOR.popup_yesNo)) {
				List<WebElement> tab_labels = findWebElements(GOR.popup_yesNo);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equalsIgnoreCase(buttonName)) {
						testStepPassed("\"" + label_text + "\" button is displayed.");
						if (isClick) {
							Actions act = new Actions(driver);
							act.click(tab_labels.get(label)).build().perform();
							//tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" button is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						highLighterMethod(GOR.popUpFooterBtns);
						testStepInfo("Unable to find the " + buttonName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonName + ".");
		}
	}

	/**
	 * Description : Method to click on the inward cancel button
	 * @param buttonName is the name of the buttons
	 * @param isClick - true, false
	 */
	public void clickCancelInwardButton(String buttonName, boolean isClick) {
		try {
			if (isElementPresent(GOR.cancelInwardPopupbutton)) {
				List<WebElement> tab_labels = findWebElements(GOR.cancelInwardPopupbutton);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equals("OK")) {
						testStepPassed("\"" + label_text + "\" button is displayed in Cancel Inward Popup.");
						if (isClick) {
							tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" button is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						highLighterMethod(GOR.popUpFooterBtns);
						testStepFailed("Unable to find the " + buttonName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonName + ".");
		}
	}

	/**
	 * Description : Method to validate the table header labels
	 * @param headerLabel is the name of the headers
	 */
	public void validateTableHeaderLabels(String headerLabel) {
		try {
			List<WebElement> tab_labels = findWebElements(GOR.table_heads);
			for (int label = 0; label < tab_labels.size(); label++) {
				String label_text = tab_labels.get(label).getText().trim();
				if (label_text.equals(headerLabel)) {
					testStepPassed("\"" + label_text + "\" Tab is displayed in Menu bar.");
					break;
				} else if (label == (tab_labels.size() - 1)) {
					testStepFailed("Unable to find the " + headerLabel + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + headerLabel + ".");
		}
	}

	/**
	 * Description : Method to click on the drop down header
	 * @param buttonName is the name of the buttons
	 * @param isClick - true, false
	 */
	public void clickHeaderDropdown(String buttonName, boolean isClick) {
		try {
			if (isElementPresent(GOR.headerDropdowns)) {
				List<WebElement> tab_labels = findWebElements(GOR.headerDropdowns);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equals(buttonName)) {
						testStepPassed("\"" + label_text + "\" dropdown is displayed in grid section.");
						if (isClick) {
							tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" dropdown is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonName + ".");
		}
	}

	/**
	 * Description : Method to enter value in input field
	 * @param inputValue is the value of the inputs
	 */
	public void enterInputValue(String element,String inputValue) {
		try {
			if (isElementDisplayed(element)) {
				clearEditBox(element);
				typeIn(element, inputValue);
				testStepPassed(inputValue + " is entered in input field.");
			} else if (!isElementDisplayed(element)) {
				testStepPassed("Input Field is disabled.");
			} else {
				testStepFailed("Unable to enter value in the input field");
			}
		} catch (Exception e) {
			testStepFailed("Unable to enter value in the input field");
		}
	}

	/**
	 * Description : Method to validate the pagination icons
	 */
	public void validatePaginationIcons() {
		try {
			if (isElementPresent(GOR.pagination_icons)) {
				testStepPassed("Pagination icons is displayed successfully.");
			} else {
				testStepFailed("Pagination icons is not displayed.");
			}
		} catch (Exception e) {
			testStepFailed("Pagination icons is not displayed.");
		}
	}

	/**
	 * Description : Method to click on the header button displayed in grid section
	 * @param element is given for the tab names
	 * @param buttonName is the name of the buttons
	 * @param isClick - true, false
	 */
	/*public void clickHeaderButton(String element, String buttonName, boolean isClick) {
		try {
			if (isElementPresent(element)) {
				List<WebElement> tab_labels = findWebElements(element);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equals(buttonName)) {
						testStepPassed("\"" + label_text + "\" button is displayed in grid section.");
						if (isClick) {
							tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" button is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonName + ".");
		}
	}
*/
	
	public void clickHeaderButton(String element, String buttonName, boolean isClick) {
		String label_text = null;
		try {
			if (isElementPresent(element)) {
				List<WebElement> tab_labels = findWebElements(element);
				for (int label = 0; label < tab_labels.size(); label++) {
					label_text = tab_labels.get(label).getText().trim();					
					System.out.println(label_text);
					if (label_text.equals(buttonName)) {
						testStepPassed("\"" + label_text + "\" button is displayed in grid section.");
						if (isClick) {
							tab_labels.get(label).click();
							testStepPassed("\"" + label_text + "\" button is clicked successfully.");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonName + ".");
		}
	}
	
	/**
	 * Description : Method to enter input with tab
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterInputWithTab(String inputLabel, String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							tab_labels.get(label).clear();
							tab_labels.get(label).sendKeys(inputValue);
							if (withTab) {
								clickTab(tab_labels.get(label));
							}
							testStepPassed("\"" + inputValue + "\"  is entered into the text field.");
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}

	/**
	 * Description : Method to click on the drop down
	 * @param labelName is the name of the labels
	 */
	public void clickDropDown(String labelName) {
		try {
			String dropdown_Icon = "Dropdown Icon#xpath=//*[text()='" + labelName
					+ "']/../..//span[@class='select2-selection__rendered']";
			if (isElementPresent(dropdown_Icon)) {
				clickOn(dropdown_Icon);
				testStepPassed("Dropdown icon is clicked Successfully");
			} else {
				testStepFailed("Not able to find Dropdown"+labelName);
			}
		} catch (Exception e) {
			testStepFailed("Not able to find Dropdown");

		}
	}

	/**
	 * Description : Method to enter value in input field with tab
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterInputValueWithTab(String inputLabel, String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							tab_labels.get(label).clear();
							tab_labels.get(label).sendKeys(inputValue);
							waitTime(5);
							if (withTab) {
								waitForAngularLoad();
								clickTab(tab_labels.get(label));
							}
							testStepPassed("\"" + inputValue + "\"  is entered into the " + inputLabel + " field.");
							break;
						} else {
							//testStepPassed(inputLabel + " Field is disabled.");
						}
					} else if (label == (tab_labels.size() - 1)) {
						
						testStepInfo("Unable to find and enter values in " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
						testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}

	/**
	 * Description : Method to enter value in the pack field
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterPackInputValue(String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.packInput)) {
				List<WebElement> tab_labels = findWebElements(GOR.packInput);
				for (int label = 0; label < tab_labels.size(); label++) {
					if (tab_labels.get(label).isEnabled()) {
						tab_labels.get(label).clear();
						tab_labels.get(label).sendKeys(inputValue);
						testStepPassed(inputValue + " is entered in Pack Field.");
						break;
					} else {
						testStepFailed("Pack Field is disabled.");
					}

				}
			}
		} catch (Exception e) {
			testStepFailed("Pack Field is disabled.");
		}
	}

	/**
	 * Description : Method to enter input
	 * @param label is the value of the labels
	 * @param inputValue is the given input value
	 */
	public void enterIntoTextField(String fieldName, String fieldValue) {
		try {

			String labelXpath = "Label#xpath=//label[contains(text(),'" + fieldName + "')]/..";
			WebElement labelField = findWebElement(labelXpath);
			String textFieldXpath = "Textfield[" + fieldName + "]#xpath=//input";
			WebElement textField = findWebElementFrom(labelField, textFieldXpath);
			textField.clear();
			textField.sendKeys(fieldValue);
			testStepPassed("[" + fieldValue + "] is entered into Textfield[" + fieldName + "]");
		} catch (Exception e) {
			testStepFailed("Enter value into the field. Error:" + e.toString());
		}
	}


	/**
	 * Description : Method to click on the buttons displayed in grid section 
	 * @param element is given for the tab names
	 * @param buttonLabel is the value of the button labels
	 * @param isClick - true, false
	 */
	public void clickGridButtons(String element, String buttonLabel, boolean isClick) {
		try {
			waitForAngularLoad();
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
			if (isElementPresent(GOR.gridButtons)) {
				List<WebElement> tab_labels = findWebElements(GOR.gridButtons);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getAttribute("title").trim();
					if (buttonLabel_rt.equals(buttonLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							testStepPassed("\"" + buttonLabel_rt + "\" button is displayed in grid section.");
							if (isClick) {
								waitForAngularLoad();
								tab_labels.get(label).click();
								testStepPassed("\"" + buttonLabel_rt + "\" button is clicked successfully.");
							}
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepInfo("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to click on the tab
	 * @param element is given for the tab names
	 */
	public void clickTab(WebElement element) {
		try {
			if (isElementPresent(GOR.valuesList)) {
				element.sendKeys(Keys.TAB);
			}

		} catch (Exception e) {
			testStepFailed("Tab is not clicked.");
		}
	}

	/**
	 * Description : Method to  click on the allocate location button
	 * @param isClick - true, false
	 */
	public void clickAllocateLocation(boolean isClick) {
		try {
			if (isElementPresent(GOR.allocateLocation_btn)) {
				if (isClick) {
					clickOn(GOR.allocateLocation_btn);
					testStepPassed("Allocate Location button is clicked successfully");
				}
			} else {
				testStepFailed("Unable to find the Allocate Location button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the Allocate Location button.");
		}
	}

	/**
	 * Description : Method to click on convert all receipt button
	 * @param isClick - true, false
	 */
	public void clickConvertAllReceipt(boolean isClick) {
		try {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			if (isElementPresent(GOR.convertAllReceipt_btn)) {
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",
						findWebElement(GOR.convertAllReceipt_btn));
				if (isClick) {
					clickOn(GOR.convertAllReceipt_btn);

				}
			} else {
				testStepFailed("Unable to find the Allocate Location button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the Allocate Location button.");
		}
	}

	/**
	 * Description : Method to select date
	 * @param inputDate is the value of the date
	 */
	public void selectDate(String inputDate) {
		try {
			String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
			List<String> mnths = Arrays.asList(months);
			if (inputDate.contains("-")) {
				String[] splitDate = inputDate.split("-");
				if (splitDate.length == 3) {
					if (isElementPresent("#cssSelector=li[class='date-picker-menu']")) {
						WebElement rtMonthName = findWebElement(
								"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(2)");
						String[] splitRtMonthName = rtMonthName.getText().split(" ");
						int refMonth = Month.valueOf(splitRtMonthName[0].toUpperCase()).getValue();
						int ipDateYear = Integer.valueOf(splitDate[2]);
						int rtDateYear = Integer.valueOf(splitRtMonthName[1]);
						if (ipDateYear > rtDateYear) {
							WebElement rightArrow = findWebElement(
									"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(3)");
							while (ipDateYear != rtDateYear) {
								rightArrow.click();
								waitTime(1);
								rtMonthName = findWebElement(
										"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(2)");
								splitRtMonthName = rtMonthName.getText().split(" ");
								rtDateYear = Integer.valueOf(splitRtMonthName[1]);
							}
						} else {
							WebElement rightArrow = findWebElement(
									"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(1)");
							while (ipDateYear != rtDateYear) {
								rightArrow.click();
								waitTime(1);
								rtMonthName = findWebElement(
										"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(2)");
								splitRtMonthName = rtMonthName.getText().split(" ");
								rtDateYear = Integer.valueOf(splitRtMonthName[1]);
							}
						}

						splitRtMonthName = rtMonthName.getText().split(" ");
						refMonth = Month.valueOf(splitRtMonthName[0].toUpperCase()).getValue();

						int ipDateMonth = mnths.indexOf(splitDate[1]) + 1;
						if (ipDateMonth > refMonth) {
							WebElement rightArrow = findWebElement(
									"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(3)");
							while (ipDateMonth != refMonth) {
								rightArrow.click();
								waitTime(1);
								rtMonthName = findWebElement(
										"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(2)");
								splitRtMonthName = rtMonthName.getText().split(" ");
								refMonth = Month.valueOf(splitRtMonthName[0].toUpperCase()).getValue();
							}
						} else {
							WebElement rightArrow = findWebElement(
									"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(1)");
							while (ipDateMonth != refMonth) {
								rightArrow.click();
								waitTime(1);
								rtMonthName = findWebElement(
										"#cssSelector=li[class='date-picker-menu'] thead>tr:nth-of-type(1)>th:nth-of-type(2)");
								splitRtMonthName = rtMonthName.getText().split(" ");
								refMonth = Month.valueOf(splitRtMonthName[0].toUpperCase()).getValue();
							}
						}

						WebElement dateSelection = findWebElement(
								"#xpath=//button/span[(@class='ng-binding text-info' or @class='ng-binding') and text()='"
										+ splitDate[0] + "']");
						waitTime(1);
						dateSelection.click();
						testStepPassed(inputDate + " Date is selected from Calendar window");
					} else {
						testStepFailed("Failed to display Calendar window");
					}
				} else {
					testStepFailed("The date format should be in dd//mm//yyyy");
				}
			} else {
				testStepFailed("The date format should be in dd//mm//yyyy");
			}
		} catch (Exception e) {
			testStepFailed("The date format should be in dd//mm//yyyy");
		}

	}

	/**
	 * Description : Method to click on the date icon
	 * @param labelName is the name of the labels 
	 * @return lableEnable
	 */
	public boolean clickDateIcon(String labelName) {
		boolean lableEnable = false;
		try {
			WebElement label = findWebElement(
					"label #xpath=//button[contains(@data-ng-click,'" + labelName.replaceAll(" ", "") + "')]");
			if (label.isEnabled()) {
				label.click();
				lableEnable = true;
			} else if (!label.isEnabled()) {
				testStepPassed("Field is disabled " + labelName);
			} else {
				testStepFailed("Unable to enter date in " + labelName);
			}

		} catch (Exception e) {
			testStepFailed("Unable to enter date in " + labelName);
		}
		return lableEnable;
	}

	/**
	 * Description : Method to validate organization page
	 */
	public void organizationHeaderValidation() {
		try {
			if (isElementPresent(GOR.organization_header)) {
				testStepPassed("Organization page is present");
			} else {
				testStepFailed("Organization page is failed to display");
			}
		} catch (Exception e) {
			testStepFailed("Organization page is failed to display");
		}
	}

	/**
	 * Description : Method to click on the organization check box
	 * @param labelName is the name of the labels
	 */
	public void VerifyCheckbox(String labelName) {
		try {
			List<WebElement> checkboxs = findWebElements(GOR.checkbox);
			if (!checkboxs.isEmpty()) {
				for (int checkbox = 0; checkbox < checkboxs.size(); checkbox++) {
					String checkboxName = checkboxs.get(checkbox).getText();					
					if (checkboxName.equals(labelName)) {
						WebElement cBox = checkboxs.get(checkbox);
						String isChecked = cBox.findElement(By.xpath("//input")).getAttribute("class");
						if (!isChecked.contains("not-empty")) {
							cBox.click();							
							testStepPassed(" CheckBox is enabled aganist  " + labelName);
							break;
						} else {							
							 testStepPassed(" CheckBox is disabled aganist " + labelName);
							break;
						}
					} else if (checkbox == (checkboxs.size() - 1)) {
						testStepFailed("Not able to find " + labelName);
					}
				}
			} else {
				testStepFailed("Unable to find " + labelName + ".");
			}
		} catch (Exception e) {
			testStepFailed("Exception:" + e.getMessage());
		}
	}

	/**
	 * Description : Method to click on the footer tabs
	 * @param labelName is the name of the labels
	 */
	public void footerTabs(String labelName) {
		try {
			List<WebElement> footerTabs = findWebElements(GOR.footer_btns);
			if (!footerTabs.isEmpty()) {
				for (int footTab = 0; footTab < footerTabs.size(); footTab++) {
					String orgTabValue = footerTabs.get(footTab).getText();
					if (orgTabValue.equals(labelName)) {
						footerTabs.get(footTab).click();
						testStepPassed(labelName + " is clicked successfully.");
						break;
					}
				}
			} else {
				highLighterMethod(GOR.pagePopUpFooterBtns);
				testStepFailed("Unable to find " + labelName + ".");
			}
		} catch (Exception e) {
			testStepFailed("Exception:" + e.getMessage());
		}
	}

	/**
	 * Description : Method to pick options in outward
	 * @param labelName is the name of the labels
	 */
	public void pickOptInOutward(String labelName) {
		try {
			WebElement pickOption = findWebElement(
					"Pick Option#xpath=//div[contains(@class,'clearfix padding')]//label[text()='" + labelName
					+ "']/..//div");
			if (pickOption.isDisplayed()) {
				String pickOptions = pickOption.getText();
				testStepPassed(pickOptions + "value is get aganist  " + labelName);
			} else {
				testStepFailed("Not able to find " + labelName);
			}

		} catch (Exception e) {
			testStepFailed("Not able to find " + labelName);
		}
	}

	/**
	 * Description : Method to click on batch upload and release buttons
	 * @param labelName is the name of the labels
	 */
	public void clickBatchUpload(String labelName) {
		try {
			WebElement batchUpload_Btn = findWebElement(
					"Batch Upload and release btn #xpath=//button[text()='" + labelName + "']");
			if (batchUpload_Btn.isDisplayed()) {
				batchUpload_Btn.click();
				testStepPassed("Batch Upload Button is clicked aganist");
			} else {
				testStepFailed("Not able to find Batch Upload Button");
			}
		} catch (Exception e) {
			testStepFailed("Not able to find Batch Upload Button");
		}
	}

	/**
	 * Description : Method to validate the user defined fields
	 * @param textBoxValue s the value of text box
	 * @param dropdownValue is the value of drop downs
	 * @param checkBoxVerify is to verify the check box
	 */
	public void userDefinedFieldValidation(String textBoxValue, String dropdownValue, String checkBoxVerify) {
		try {
			List<WebElement> userDefinetable = findWebElements(GOR.userDefinedFields);
			if (!userDefinetable.isEmpty()) {
				for (int footTab = 0; footTab < userDefinetable.size(); footTab++) {
					String orgTabValue = userDefinetable.get(footTab).getText();
					if (orgTabValue.equals(textBoxValue) || orgTabValue.equals(dropdownValue)
							|| orgTabValue.equals(checkBoxVerify)) {
						testStepPassed(textBoxValue + " Value Is Present In the table ");
						break;
					}
				}
			} else {
				testStepFailed("Unable to find" + textBoxValue + ".");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find" + textBoxValue + ".");
		}
	}

	/**
	 * Description : Method to click on the address type check box
	 * @param labelName is the name of the labels
	 */
	public void AddressTypeCheckBox(String labelName) {
		try {
			if (isElementDisplayed(GOR.checkBoxes_AddressType)) {
				List<WebElement> checkBox = findWebElements(GOR.checkBoxes_AddressType);
				List<WebElement> labelsNames = findWebElements(GOR.label_checkBox);
				for (int index = 0; index < checkBox.size(); index++) {
					String txt_checkBox = labelsNames.get(index).getText();
					if (labelName.contains(txt_checkBox)) {
						checkBox.get(index).click();
						testStepPassed("\"" + labelName + "\" is clicked successfully");
						break;
					} else if (index == (checkBox.size() - 1)) {
						testStepFailed("Unable to find " + labelName);
					}
				}
			} else {
				testStepFailed(labelName + "is not displayed");
			}
		} catch (Exception e) {
			testStepFailed(labelName + "is not displayed");
		}
	}

	/**
	 * Description : Method to click and verify the switch to label 
	 * @param roleName is the name of the role
	 */
	public void clickandverifySwitchRole(String roleName) {
		try {
			String role_code = "Role Code #xpath=//span[contains(text(),'" + roleName + "')]";
			if (isElementDisplayed(GOR.label_switchto)) {
				List<WebElement> roles = findWebElements(role_code);
				for (int index = 0; index < roles.size(); index++) {
					String rolecode = roles.get(index).getText().trim();
					if (rolecode.equals(roleName)) {
						roles.get(index).click();
						testStepPassed("\"" + roleName + "\" is clicked successfully");
						break;
					} else if (index == (roles.size() - 1)) {
						testStepFailed("Unable to find " + roleName);
					}
				}
			} else {
				testStepFailed(roleName + "is not displayed");
			}
		} catch (Exception e) {
			testStepFailed(roleName + "is not displayed");
		}
	}

	/**
	 * Description : Method to edit the ellipsis
	 * @param labelName is the name of the labels
	 * @param isClick - true, false
	 */
	public void editEllipsis(String menuName,String labelName, boolean isClick) {
		try {
			String btn_ellipsisValue="btn_ellipsis#xpath=//div[contains(@data-ng-if,'Organization"+menuName+"')]//i[contains(@class,'ellipsis')]";
			WebElement btn_ellipsis=findWebElement(btn_ellipsisValue);
			if (isElementDisplayed(btn_ellipsis)) {
				clickOn(btn_ellipsisValue);
				if (isClick) {
					List<WebElement> options = findWebElements(GOR.drpDown_ellipsis);
					for (int index = 0; index < options.size(); index++) {
						String drpDownLabel = options.get(index).getText().trim();
						if (drpDownLabel.equals(labelName)) {
							options.get(index).click();
							testStepPassed("\"" + labelName + "\" is clicked Successfully.");
							break;
						} else if (index == (options.size() - 1)) {
							testStepFailed("Unable to find " + labelName);
						}
					}
				} else {
					testStepFailed("Organization Address type buttons are not displayed");
				}
			}
		} catch (Exception e) {
			testStepFailed("Organization Address type buttons are not displayed");

		}
	}

	/**
	 * Description : Method to get rows in the table 
	 * @param element is given for the tab names
	 * @return rowCount
	 */
	public int getRowsInTable(String element) {
		int rowCount = 0;
		try {
			rowCount = findWebElements(element + "//tr").size();
			testStepInfo("No. of rows in the table: "+rowCount);
		} catch (Exception e) {
			testStepInfo("Unable to find rows in table.");
		}
		return rowCount;
	}

	/**
	 * Description : Method to get the table header position
	 * @param element is given for the tab names
	 * @param headerLabel is the name of the headers 
	 * @return columnPosition
	 */
	public int getTableHeaderPosition(String element, String headerLabel) {
		int columnPosition = 0;
		try {
			List<WebElement> tableHeader = findWebElements(element + "//th");
			if (!tableHeader.isEmpty()) {
				for (int header = 0; header < tableHeader.size(); header++) {
					String headerText = tableHeader.get(header).getText();
					if (headerText.equalsIgnoreCase(headerLabel)) {
						testStepPassed(headerLabel + " is existed in the table");
						columnPosition = header + 1;
						break;
					} else if (header == (tableHeader.size() - 1)) {
						testStepFailed("Not able to find " + headerLabel);
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Not able to find " + headerLabel);
		}
		return columnPosition;
	}

	/**
	 * Description : Method to get the row position value
	 * @param element is given for the tab names
	 * @param columnPosition is the position of column
	 * @param value is the value of drop downs
	 * @return rowPosition is the position of the row
	 */
	public int getValueRowPosition(String element, int columnPosition, String value) {
		System.out.println();
		int rowPosition = 0;
		try {
			List<WebElement> columnValues = findWebElements(element + "//tr//td[" + columnPosition + "]");
			if (!columnValues.isEmpty()) {
				for (int col = 0; col < columnValues.size(); col++) {
					String columnValue = columnValues.get(col).getText();
					if (columnValue.contains(value)) {
						testStepPassed(value + " is existed in the table");
						rowPosition = col + 1;
						break;
					} else if (col == (columnValues.size() - 1)) {
						testStepFailed("Not able to find " + value);
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Not able to find " + value);
		}
		return rowPosition;
	}

	/**
	 * Description : Method to get the position value
	 * @param element is given for the tab names
	 * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 * @return table_Value
	 */
	public String getPositionValue(String element, int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = element + "//tr[" + rowPosition + "]//td[" + columnPosition + "]";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				//testStepPassed(table_Value + " is get from the table");
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
					+ rowPosition + "," + columnPosition);
		}
		return table_Value;
	}

	/**
	 * Description : Method to click the check box value
	 * @param element is given for the tab names
	 * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 * @return table_Value
	 */
	public String clickPositionCheckBoxValue(String element, int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = element + "//tr[" + rowPosition + "]//td[" + columnPosition + "]//label";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				clickOn(tablePosition);
				testStepPassed(table_Value + "is Successfully Clicked");

				testStepPassed(table_Value + " is get from the table");
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Unable to click the value");
		}
		return table_Value;
	}

	/**
	 * Description : Method to click the position value
	 * @param element is given for the tab names
	 * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 * @return table_Value
	 */
	public String clickPositionValue(String element, int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = element + "//tr[" + rowPosition + "]//td[" + columnPosition + "]";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				waitForElement(tablePosition);
				waitTime(3);
				findWebElement(tablePosition).click();
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Unable to click the position value");
		}
		return table_Value;
	}

	/**
	 * Description : Method to get the check box table header position
	 * @param element is given for the tab names
	 * @param headerLabel is the name of the headers
	 * @return columnPosition
	 */
	public int getCheckboxTableHeaderPosition(String element, String headerLabel) {
		int columnPosition = 0;
		try {
			List<WebElement> tableHeader = findWebElements(element + "//th");
			if (!tableHeader.isEmpty()) {
				for (int header = 0; header < tableHeader.size(); header++) {
					String headerText = tableHeader.get(header).getAttribute("id");
					if (headerText.contains(headerLabel)) {
						testStepPassed(headerLabel + " is existed in the table");
						columnPosition = header + 1;
						break;
					} else if (header == (tableHeader.size() - 1)) {
						testStepFailed("Unable to find " + headerLabel);
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + headerLabel);
		}
		return columnPosition;
	}

	/**
	 * Description : Method to retrieve the column position
	 * @param headerName is the name of the headers
	 * @return columnPosition + 1
	 */
	public int retrieveColumnPosition(String headerName) {
		int columnPosition = 0;
		try {
			if (isElementDisplayed(GOR.TableHeader)) {
				List<WebElement> headerValues = findWebElements(GOR.TableHeader);
				for (int i = 0; i < headerValues.size(); i++) {
					JavascriptExecutor jscript = (JavascriptExecutor) driver;
					jscript.executeScript("arguments[0].scrollIntoView(true);", headerValues.get(i));
					String headervalue = headerValues.get(i).getText().trim();
					if (headervalue.contains(headerName)) {
						columnPosition = i;
						break;
					} else {
						if (i == (headerValues.size() - 1)) {
							testStepInfo("Table does not contain the header value - " + headerName);
						}
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Table does not contain the header value - " + headerName);
		}
		return columnPosition + 1;
	}

	/**
	 * Description : Method to retrieve the row position
	 * @param columnPosition is the position of column
	 * @param value is the value of drop downs
	 * @return rowPosition + 1
	 */
	public int retrieveRowPosition(int columnPosition, String value) {
		int rowPosition = 0;
		try {
			String tableRow = "Header values#xpath=//div[not(contains(@class,'pinned'))]/div[@role='presentation']//div[@class='ui-grid-row ng-scope']//div[@role='row']/div["
					+ columnPosition + "]";
			List<WebElement> rowValues = findWebElements(tableRow);
			for (int i = 0; i < rowValues.size(); i++) {
				String cellvalue = rowValues.get(i).getText().trim();
				if (cellvalue.contains(value)) {
					rowPosition = i;
					break;
				} else {
					if (i == (rowValues.size() - 1)) {
						testStepFailed("Table does not contain the row value - " + value);
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Table does not contain the row value - " + value);
		}
		return rowPosition + 1;
	}

	/**
	 * Description : Method to get the table value
	 * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 * @return table_Value
	 */
	/*
	 * public String gettableValue(int rowPosition, int columnPosition) { String
	 * table_Value = null; try { String tablePosition =
	 * "table values#xpath=//div[@class='ui-grid-canvas']//div[@class='ui-grid-row ng-scope']["
	 * + rowPosition + "]//div[@role='row']//div[" + columnPosition + "]/div//a"; if
	 * (isElementDisplayed(tablePosition)) { table_Value = getText(tablePosition);
	 * testStepPassed(table_Value + " is required value from the table"); } else {
	 * testStepFailed("Not able to get the " + table_Value +
	 * " at rowPosition and column Position " + rowPosition + "," + columnPosition);
	 * } } catch (Exception e) { testStepFailed("Not able to get the " + table_Value
	 * + " at rowPosition and column Position " + rowPosition + "," +
	 * columnPosition); } return table_Value; }
	 */
	
	public String gettableValue(int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
//			String tablePosition = "table values#xpath=//div[@class='ui-grid-canvas']//div[@class='ui-grid-row ng-scope']["
//					+ rowPosition + "]//div[@role='row']//div[" + columnPosition + "]/div//a";
			
			String tablePosition = "table values#xpath=//div[@role='presentation'][contains(@id,'body-grid-container')]/div/following-sibling::div/div[@class='ui-grid-canvas']/div["+ rowPosition +"]//div[contains(@class,'ui-grid-cell ng-scope')]["+ columnPosition +"]";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				testStepPassed(table_Value + " is required value from the table");
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position " + rowPosition
					+ "," + columnPosition);
		}
		return table_Value;
	}

	/**
	 * Description : Method to click on the table position value
	 * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 * @return table_Value
	 */
	public String clickTablePositionValue(int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = "table values#xpath=//div[@class='ui-grid-canvas']//div[@class='ui-grid-row ng-scope']["
					+ rowPosition + "]//div[@role='row']//div[" + columnPosition + "]//a";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				clickOn(tablePosition);
				testStepPassed(table_Value + "is Successfully Clicked");

				testStepPassed(table_Value + " is get from the table");
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
					+ rowPosition + "," + columnPosition);
		}
		return table_Value;
	}

	/**
	 * Description : Method to click on the edit button
	 * @param isClick - true, false
	 */
	public void clickEdit(boolean isClick) {
		try {
			String edit_btn = "Warehouse Edit#xpath=//span[text()='Product Configuration']/..//button";
			if (isElementDisplayed(edit_btn)) {
				if (isClick) {
					clickOn(edit_btn);
					testStepPassed("Edit is Successfully Clicked");
				}
			} else {
				testStepFailed("Not able to find Edit");
			}
		} catch (Exception e) {
			testStepFailed("Not able to find Edit");
		}
	}

	/**
	 * Description : Method to click on the footer button - apply
	 */
	public void clickOnFooterApply() {
		try {
			if (isElementPresent(GOR.filterbutton_Apply)) {
				clickOn(GOR.filterbutton_Apply);
				waitTime(3);
			} else {
				highLighterMethod(GOR.applyBtnBar);
				testStepFailed("Not able to find Apply");
			}
		} catch (Exception e) {
			testStepFailed("Not able to find Apply");

		}
	}

	/**
	 * Description : Method to validate the status
	 * @param actualValue is the data value
	 * @param expectedStatus is the value of the status
	 */
	public void validateStatus(String actualValue, String expectedStatus) {
		try {
			if (actualValue.equals(expectedStatus)) {
				testStepPassed(actualValue + " Status is displayed as expected");
			} else {
				testStepFailed(actualValue + " Status is not displayed as expected");
			}
		} catch (Exception e) {
			testStepFailed(actualValue + " Status is not displayed as expected");
		}
	}

	/**
	 * Description : Method to validate the product summary details
	 * @param productCode is the value of the product code label
	 * @param productCode_PS is the another value of the product code label
	 */
	public void validateProductSummaryDetails(String productCode, String productCode_PS) {
		try {
			if (productCode_PS.contains(productCode)) {
				testStepPassed("Recive Line details are displayed in Product Summary Page as expected.");
			} else {
				testStepFailed("Recive Line details are not displayed in Product Summary Page as expected.");
			}
		} catch (Exception e) {
			testStepFailed("Recive Line details are not displayed in Product Summary Page as expected.");
		}
	}

	/**
	 * Description : Method to click on the more options button
	 * @param optionValue is the value of the options
	 * @param isClick - true/false
	 */
	public void clickMoreOptions(String optionValue, boolean isClick) {
		try {
			List<WebElement> tab_labels = findWebElements(GOR.moreOptions);
			for (int label = 0; label < tab_labels.size(); label++) {
				String label_text = tab_labels.get(label).getText().trim();
				if (label_text.equals(optionValue)) {
					testStepPassed("\"" + label_text + "\" button is displayed in More Options.");
					if (isClick) {
						tab_labels.get(label).click();
						waitTime(1);
						testStepPassed("\"" + label_text + "\" button is clicked successfully.");
					}
					break;

				} else if (label == (tab_labels.size() - 1)) {
					highLighterMethod(GOR.moreOption);
					testStepFailed("Unable to find the " + optionValue + ".");
				}
			}

		} catch (Exception e) {
			testStepFailed("Unable to find the " + optionValue + ".");
		}
	}

	/**
	 * Description : Method to compare the pack value
	 * @param inputPack is the value of the pack
	 * @param receiveLinePack is the value of the receive line pack
	 */
	public void comparePackValue(int inputPack, int receiveLinePack) {
		try {
			if (inputPack == receiveLinePack) {
				testStepPassed("All line's are converted to Receive lines as expected.");
			} else {
				testStepFailed("Lines are not converted as expected.");
			}
		} catch (Exception e) {
			testStepFailed("Lines are not converted as expected.");
		}
	}

	/**
	 * Description : Method to validate the non-editable fields
	 */
	public void validateNonEditableFields() {
		try {
			String value = getAttributeValue(GOR.nonEditableField_RL, "data-ng-if");
			if (value.contains("NonEditable")) {
				testStepPassed("Receive Line Fields are non-editable.");
			} else {
				testStepFailed("Receive Line Fields are non-editable.");
			}

		} catch (Exception e) {
			testStepFailed("Receive Line Fields are non-editable.");
		}
	}

	/**
	 * Description : Method to click on the header tabs
	 * @param buttonLabel is the value of the button labels
	 * @param isClick - true, false
	 */
	public void clickOnHeaderTabs(String buttonLabel, boolean isClick) {
		try {
			if (isElementPresent(GOR.tabs_header)) {
				List<WebElement> tab_labels = findWebElements(GOR.tabs_header);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getText().trim();
					if (buttonLabel_rt.equals(buttonLabel)) {
						if (tab_labels.get(label).isEnabled()) {
							testStepPassed("\"" + buttonLabel_rt + "\" button is displayed in grid section.");
							if (isClick) {
								waitForAngularLoad();
								tab_labels.get(label).click();								
								testStepPassed("\"" + buttonLabel_rt + "\" button is clicked successfully.");
							}
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						highLighterMethod(GOR.headerTabs);
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to get the tab url
	 * @param tabName is the name of the tab
	 * @return tabUrl
	 */
	public String getTabUrl(String tabName) {
		String tabUrl = "";
		try {
			if (isElementDisplayed(GOR.menu_Labels)) {
				List<WebElement> tab_labels = findWebElements(GOR.menu_Labels);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equals(tabName)) {
						tabUrl = tab_labels.get(label).findElement(By.xpath("./parent::a")).getAttribute("href");
						System.out.println(tabUrl);
						break;
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + tabName + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + tabName + ".");
		}
		return tabUrl;
	}

	/**
	 * Description : Method to open url in the new tab
	 * @param url is the hyperlink value
	 */
	public void openUrlInNewTab(String url) {
		try {
			if (!url.isEmpty()) {
				((JavascriptExecutor) driver).executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.navigate().to(url);
			} else {
				testStepPassed("");
			}
		} catch (Exception e) {
			testStepFailed("Failed to open.");
		}
	}

	/**
	 * Description : Method to click on the header org buttons
	 * @param labelName is the name of the labels
	 */
	public void orgHeaderBtns(String labelName) {
		try {
			List<WebElement> orgTabs = findWebElements(GOR.headerBtns_org);
			if (!orgTabs.isEmpty()) {
				for (int orgTab = 0; orgTab < orgTabs.size(); orgTab++) {
					String orgTabValue = orgTabs.get(orgTab).getText();
					if (orgTabValue.equals(labelName)) {
						if (orgTabs.get(orgTab).isEnabled()) {
							orgTabs.get(orgTab).click();
							testStepPassed(" Tab is clicked aganist  " + labelName);
							break;
						}
					}
				}
			} else {
				testStepFailed("Unable to find " + labelName);
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + labelName);
		}
	}

	/**
	 * Description : Method to verify the pop up message
	 * @param expectedMessage is the value of the message
	 * @return popUpMessage
	 */
	public boolean verifyEditPopupMessage(String expectedMessage) {
		boolean popUpMessage = false;
		try {
			if (isElementDisplayed(GOR.Msg_EditOrgAvailable)) {
				String messageText = getText(GOR.Msg_EditOrgAvailable);
				if (messageText.contains(expectedMessage)) {
					testStepPassed(messageText + "is displayed");
				} else {
					testStepFailed(messageText + "is not displayed");
				}
			}
			popUpMessage = isElementDisplayed(GOR.Msg_EditOrgAvailable);
		} catch (Exception e) {
			testStepFailed("Message is not displayed");
			return popUpMessage;
		}
		return popUpMessage;
	}

	/**
	 * Description : Method to click on the close button
	 */
	public void clickOncloseButton() {
		try {
			if (isElementPresent(GOR.Btn_close)) {
				clickOn(GOR.Btn_close);
				testStepPassed("Page is closed successfully.");
			} else {
				highLighterMethod(GOR.plusIconBar);
				testStepFailed("Page close button is not found to click");
			}

		} catch (Exception e) {
			testStepFailed("Page close button is not found to click");
		}

	}

	/**
	 * Description : Method to get the disabled drop down field value
	 * @param labelValue is the value of the labels
	 * @param inputValue is the value of the inputs
	 * @return existingValue
	 */
	public String getDisabledDropDownfieldValue(String labelValue, String inputValue) {
		String existingValue = "";
		try {
			List<WebElement> labels = findWebElements(GOR.filterLabels);
			List<WebElement> Values = findWebElements(GOR.disabledDropDownField);
			if (!labels.isEmpty()) {
				for (int index = 0; index < labels.size(); index++) {
					String label = labels.get(index).getText();
					if (label.equals(labelValue)) {
						for (int index1 = 0; index1 < Values.size(); index1++) {
							String textValue = Values.get(index1).getAttribute("class");
							if (textValue.contains(inputValue)) {
								existingValue = Values.get(index1).getText();
								testStepPassed(label + "field is" + existingValue);
							}
						}
						break;
					}
				}
			} else {
				testStepFailed("Unable to find " + labelValue);
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + labelValue);
		}
		return existingValue;

	}

	/**
	 * Description : Method to get disabled ellipsis field value
	 * @param labelValue is the value of the labels
	 * @param inputValue is the value of the inputs
	 * @return textvalue
	 */
	/*public String getDisabledellipsisfieldValue(String labelValue, String inputValue) {
		String textvalue = "";
		try {

			List<WebElement> labels = findWebElements(GOR.filterLabels);
			List<WebElement> Values = findWebElements(GOR.disabledEllipsisField);
			if (!labels.isEmpty()) {
				for (int index = 0; index < labels.size(); index++) {
					String label = labels.get(index).getText();
					if (label.equals(labelValue)) {
						for (int index1 = 0; index1 < Values.size(); index1++) {
							String textValue = Values.get(index1).getAttribute("class");
							if (textValue.contains(inputValue)) {
								JavascriptExecutor jscript = (JavascriptExecutor) driver;
								textvalue = (String) jscript.executeScript("return arguments[0].value",
										findWebElement(GOR.disabledEllipsisField));
								testStepPassed(label + "field is" + textvalue);
							}
						}
						break;
					}
				}
			} else {
				testStepFailed("Unable to find " + labelValue);
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + labelValue);
		}
		return textvalue;
	}*/
	
	
	public String getDisabledellipsisfieldValue(String labelValue, String inputValue) {
		String textvalue = "";
		try {
			List<WebElement> labels = findWebElements(GOR.filterLabels);//div[contains(@id,'filterSideBar')]//label[@title]
			List<WebElement> Values = findWebElements(GOR.disabledEllipsisField);//div[contains(@id,'filterSideBar')]//label[@title]/..//input
			if (!labels.isEmpty()) {
				for (int index = 0; index < labels.size(); index++) {
					String label = labels.get(index).getText();
					if (label.equals(labelValue)) {
						for (int index1 = 0; index1 < Values.size(); index1++) {
							 textvalue = Values.get(index1).getAttribute("value");
							System.out.println(textvalue);
							if (textvalue.contains(inputValue)) {
//								JavascriptExecutor jscript = (JavascriptExecutor) driver;
//								textvalue = (String) jscript.executeScript("return arguments[0].value",
//										findWebElement(GOR.disabledEllipsisField));
								testStepPassed(label + " field is " + textvalue);;
								break;
							}
						}
						break;
					}
				}
			} else {
				testStepFailed("Unable to find " + labelValue);
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + labelValue);;
		}
		return textvalue;
	}
	

	/**
	 * Description : Method to click on the ellipsis close button
	 */
	public void elipsisButtonClose() {
		try {

			if (isElementPresent(GOR.btn_ElipseClose)) {
				clickOn(GOR.btn_ElipseClose);
				testStepPassed("Elipsis page is closed successfully.");
			} else {
				testStepFailed("Elipsis close button is not found to click");
			}
		} catch (Exception e) {
			testStepFailed("Elipsis close button is not found to click");
		}
	}
	

	/**
	 * Description : Method to click on the check box position value
	 * @param element is given for the tab names
	 * @param rowPosition is the position of row
	 * @param columnPosition  is the position of column
	 * @return table_Value
	 */
	public String clickCheckboxPositionValue(String element, int rowPosition, int columnPosition) {
		String table_Value = null;
		try {

			String tablePosition = element + "//tr[" + rowPosition + "]//td[" + columnPosition + "]";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				clickOn(tablePosition);
				testStepPassed(table_Value + "is Successfully Clicked");
				String isChecked = findWebElement(tablePosition).findElement(By.xpath("//preceding-sibling::input"))
						.getAttribute("class");
				if (!isChecked.contains("not-empty")) {
					clickOn(tablePosition);
				}
				clickOn(tablePosition);
				testStepPassed(table_Value + " is get from the table");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find "+table_Value+" from the table.");
		}
		return table_Value;
	}

	/**
	 * Description : Method to select the time
	 * @param time is the time value
	 */
	public void selectTime(String time) {
		try {
			if (time.equalsIgnoreCase("now")) {
				clickOn(GOR.timeNow_btn);
			} else {
				String hour = time.split(":")[0];
				String minutes = time.split(":")[1].split(" ")[0];
				String ampm = time.split(":")[1].split(" ")[1];
				enterInputValueWithTab("HH", hour, false);
				enterInputValueWithTab("MM", minutes, false);
				if (!ampm.equalsIgnoreCase(getText(GOR.ampm_l))) {
					clickOn(GOR.ampm_l);
				}
				testStepPassed(time + " time is selected from Calendar Window.");
			}
		} catch (Exception e) {
			testStepFailed("Please enter valid time format.");
		}
	}

	/**
	 * Description : Method to get the details of mandatory field
	 * @param filedName is the name of the fields
	 */
	public void getMandatoryFieldDetails(String filedName) {
		try {
			List<WebElement> tab_labels = findWebElements(GOR.mandatoryFieldsWarning_msg);
			for (int label = 0; label < tab_labels.size(); label++) {
				String label_text = tab_labels.get(label).getText().trim();
				if (label_text.contains(filedName)) {
					testStepPassed("\"" + label_text + "\" Tab is displayed in Menu bar.");
					break;
				} else if (label == (tab_labels.size() - 1)) {
					testStepFailed("Unable to find the " + label_text + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the tab.");
		}
	}

	/**
	 * Description : Method to validate the grid button
	 * @param buttonLabel is the value of the button labels
	 */
	public void gridButtonValidation(String buttonLabel) {
		try {
			if (isElementPresent(GOR.gridButtons)) {
				List<WebElement> tab_labels = findWebElements(GOR.gridButtons);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getText().trim();
					if (buttonLabel_rt.equals(buttonLabel)) {

						if (tab_labels.get(label).isEnabled()) {
							testStepPassed(buttonLabel + " Is Enabled");
						} else {
							testStepPassed(buttonLabel + " Is Disabled");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}

				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to click on the Load New StockTake button
	 * @param isClick - true, false
	 */
	public void loadNewStockTakeButton(boolean isClick) {
		try {
			if (isElementPresent(GOR.loadNewStocktake_button)) {
				if (isClick) {
					clickOn(GOR.loadNewStocktake_button);
				}
			} else {
				testStepFailed("Unable to find the load new stock take button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the load new stock take button.");
		}
	}

	/**
	 * Description : Method to select the page drop down icon
	 */
	public void selectPageDropDown() {
		try {
			List<String> dropdownOptions = new ArrayList<>();
			if (isElementPresent(GOR.pageDropdown)) {
				List<WebElement> labels = findWebElements(GOR.pageDropdownValue);
				for (WebElement label : labels) {
					dropdownOptions.add(label.getText());
				}
				Select select = new Select(findWebElement(GOR.pageDropdown));
				select.selectByVisibleText(Collections.max(dropdownOptions));
				testStepPassed(Collections.max(dropdownOptions) + " maximum record is selected from page drop down");
			} else {
				highLighterMethod(GOR.pageDropdownBar);
				testStepFailed("Unable to find the page drop down.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the page drop down.");

		}
	}

	/**
	 * Description : Method to get disabled text field value
	 * @param labelValue is the value of the labels
	 * @return textvalue
	 */
	public String getDisabledTextFieldValue(String labelValue) {
		String textvalue = "";
		try {
			List<WebElement> labels = findWebElements(GOR.cycleCountGeneralLabel);
			if (!labels.isEmpty()) {
				for (int index = 0; index < labels.size(); index++) {
					String label = labels.get(index).getText();
					if (label.equals(labelValue)) {
						WebElement textField = labels.get(index).findElement(By.xpath("./..//input"));
						JavascriptExecutor jscript = (JavascriptExecutor) driver;
						textvalue = (String) jscript.executeScript("return arguments[0].value", textField);
						testStepPassed(textvalue + " Is retrieve from " + labelValue);
						break;
					}
				}
			} else {
				testStepFailed("Unable to find " + labelValue);
			}

		} catch (Exception e) {
			testStepFailed("Unable to find " + labelValue);
		}
		return textvalue;
	}

	/**
	 * Description : Method to get the current date
	 * @return currentDate
	 */
	public String getCurrentDate() {
		String currentDate = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
			Date date = new Date();
			currentDate = dateFormat.format(date);
		} catch (Exception e) {
			testStepFailed("Unable to find the current date.");
		}
		return currentDate;
	}

	/**
	 * Description : Method to get the cycle count lines tab header position
	 * @param element is given for the tab names
	 * @param headerLabel is the name of the headers
	 * @return columnPosition
	 */
	public int getCycleCountLinesTableHeaderPosition(String element, String headerLabel) {
		int columnPosition = 0;
		try {
			WebElement tableElement = findWebElement(element);
			List<WebElement> tableHeader = findWebElementsFrom(tableElement, "#xpath=//th");
			if (!tableHeader.isEmpty()) {
				for (int header = 0; header < tableHeader.size(); header++) {
					String headerText = tableHeader.get(header).getAttribute("title");
					if (headerText.equalsIgnoreCase(headerLabel)) {
						testStepPassed(headerLabel + " is existed in the table");
						columnPosition = header + 1;
						break;
					} else if (header == (tableHeader.size() - 1)) {
						testStepFailed("Not able to find " + headerLabel);
					}
				}
			}

		} catch (Exception e) {
			testStepFailed("Not able to find " + headerLabel);
		}
		return columnPosition;
	}

	/**
	 * Description : Method to click on the select all check box
	 * @param isClick - true, false
	 */
	public void checkboxSelectAll(boolean isClick) {
		try {
			if (isElementPresent(GOR.customCheckBox)) {
				List<WebElement> checkbox = findWebElements(GOR.customCheckBox);
				for (int i = 0; i < checkbox.size(); i++) {
					if (isElementDisplay(checkbox.get(i))) {
						if (isClick) {
							String value = findWebElement(GOR.customCheckBox).findElement(By.tagName("input"))
									.getAttribute("class");
							if (value.contains("ng-empty")) {
								checkbox.get(i).click();
								break;
							} else {
								testStepPassed("Check Box Already Selected");
							}
						}
					}
				}
			} else {
				testStepFailed("Unable to find the custom checkbox.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the custom checkbox.");
		}
	}

	/**
	 * Description : Method to click on the close lines button
	 * @param isClick - true, false
	 */
	public void clickCloseLines(boolean isClick) {
		try {
			if (isElementPresent(GOR.closeLines_button)) {
				if (isClick) {
					clickOn(GOR.closeLines_button);
				}
			} else {
				testStepFailed("Unable to find the Close Lines button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the Close Lines button.");
		}
	}

	/**
	 * Description : Method to enter value in the text box
	 * @param labelName is the name of the labels
	 * @param Value is the value of the inputs
	 */
	public void enterValueInTextbox(String labelName, String Value) {
		try {
			WebElement label = findWebElement("label #xpath=//label[text()='" + labelName + "']/../..//input");

			if (label.isEnabled()) {
				label.clear();
				label.sendKeys(Value);
				testStepPassed(Value + " Entered is clicked aganist " + labelName);
			} else {
				testStepFailed("Not able to find  " + labelName);
			}
		} catch (Exception e) {
			testStepFailed("Not able to find  " + labelName);
		}
	}

	/**
	 * Description : Method to click on barcode and update button
	 * @param inputValue is the value of the inputs
	 */
	public void clickGenBarcodeAndUpdate(String inputValue) {
		try {
			if (findWebElement(GOR.genBarcodeAndUpdateLabels).isEnabled()) {
				List<WebElement> labels = findWebElements(GOR.genBarcodeAndUpdateLabels);
				if (!labels.isEmpty()) {
					for (int label = 0; label < labels.size(); label++) {
						String labelName = labels.get(label).getText();
						if (labelName.equals(inputValue)) {
							labels.get(label).click();
							testStepPassed(inputValue + " is clicked ");
							break;
						} else if (label == (labels.size() - 1)) {
							testStepFailed("Not able to find " + inputValue);
						}
					}
				}
			} else {
				testStepInfo("Records need to select for" + inputValue);
			}
		} catch (Exception e) {
			testStepFailed("Not able to find " + inputValue);
		}
	}

	/**
	 * Description : Method to click on the barcode close button
	 * @param buttonName is the name of the buttons
	 */
	public void clickbarcodeCloseButton(String buttonName) {
		try {
			if (isElementPresent(GOR.pageFooter_btn)) {
				clickOnSpecialElement(GOR.pageFooter_btn);
			} else {
				testStepFailed("Unable to find the close button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the close button.");
		}
	}

	/**
	 * Description : Method to validate updation field input box
	 * @param labelValue is the value of the labels
	 * @param inputValue is the label of the inputs
	 * @return textvalue
	 */
	public String updationFieldInputboxValidation(String labelValue, String inputValue) {
		String textvalue = "";
		try {
			List<WebElement> labels = findWebElements(GOR.updationLabels);
			if (!labels.isEmpty()) {
				for (int index = 0; index < labels.size(); index++) {
					String label = labels.get(index).getText();
					if (label.equals(labelValue)) {
						String textValue = labels.get(index).findElement(By.xpath("./..//input")).getAttribute("class");
						if (textValue.contains(inputValue)) {
							testStepPassed("After update system clear the data in" + labelValue + "field.");

						} else {
							testStepFailed("After update system not clear the data in" + labelValue + "field.");
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("After update system not clear the data in" + labelValue + "field.");
		}
		return textvalue;
	}

	/**
	 * Description : Method to validate the cycle count color
	 * @param index is the value of the index
	 */
	public void cycleCountColorValidation(int index) {
		try {

			List<WebElement> rowcount = findWebElements(GOR.color_cyclecountLines);

			String textValue = rowcount.get(index).getAttribute("class");

			if (textValue.contains("green") || (textValue.contains("red"))) {
				testStepPassed("The row color changed to "
						+ textValue.substring(textValue.indexOf("active_") + 7, textValue.length()));
			} else if (!textValue.contains("green") || (!textValue.contains("red"))) {
				testStepPassed("The row color changed to Blue");
			}
		} catch (Exception e) {
			testStepFailed("Unable to change row color.");
		}
	}

	/**
	 * Description : Method to validate pick finalized message
	 */
	public void validatePickFinalizedMessage() {
		try {
			if (isElementPresent(GOR.pickFinalizedMessage)) {
				List<WebElement> finalizeMsg = findWebElements(GOR.pickFinalizedMessage);
				for (int msg = 0; msg < finalizeMsg.size(); msg++) {
					if (isElementDisplay(finalizeMsg.get(msg))) {
						testStepPassed("Success message is displayed as " + finalizeMsg.get(msg).getText());
						break;
					}
				}
			} else {
				testStepFailed("Message is not displayed.");
			}
		} catch (Exception e) {
			testStepFailed("Message is not displayed.");
		}
	}

	/**
	 * Description : Method to click on the adjustment inventory footer apply button 
	 */
	public void clickOnAdjustmentInventoryFooterApply() {
		try {
			if (isElementDisplayed(GOR.adjustmentApplyButton)) {
				clickOn(GOR.adjustmentApplyButton);
			}
		} catch (Exception e) {
			testStepFailed("Unable to click apply button.");

		}
	}

	/**
	 * Description : Method to get the current date and time
	 * @return date returns the date value
	 */
	public String getCurrentDateTime() {
		try {
			Date currentDate = new Date();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.format(currentDate);
			String date = format.format(currentDate).toString();
			System.err.println("System Date" + date);
			return date;

		} catch (Exception e) {
			testStepFailed("Unable to get current date and time.");
			return null;
		}
	}

	/**
	 * Description : Method to click on the ellipsis icon
	 * @param labelName is the name of the labels
	 */
	public void clickEllipsisIcon(String labelName) {
		try {
			WebElement ellipsisIcon = findWebElement(
					"Ellipsis Icon#xpath=//input[@placeholder='" + labelName + "']/../..//button");
			if (ellipsisIcon.isDisplayed()) {
				ellipsisIcon.click();
				testStepPassed("Ellipsis Icon is clicked aganist  " + labelName);
			} else {
				testStepFailed("Not able to find " + labelName);
			}

		} catch (Exception e) {
			testStepFailed("Not able to find " + labelName);
		}
	}

	/**
	 * Description : Method to verfiy close, filter, previous page and next page
	 */
	public void clientEllipsisIcon() {
		try {
			String previousPage = "Previous Page#xpath=//div[@id='modal-body']//button[@class='ui-grid-pager-previous']";
			String nextPage = "Next Page#xpath=//div[@id='modal-body']//button[@class='ui-grid-pager-next']";
			if (isElementPresent(GOR.close)) {
				testStepPassed("Close is available");
				if (isElementPresent(GOR.filter)) {
					testStepPassed("Filter is available");
					//waitTillElemetToBeClickable(findWebElement(GOR.filterWaitHandle));
					waitForAngularLoad();
					waitTime(5);
					//waitForElementToDisplay(GOR.filterButton, implicitlyWaitTime);
					if (isElementPresent(previousPage)) {
						testStepPassed("Previous page icon is available");
						if (isElementPresent(nextPage)) {
							testStepPassed("Next Page is available");
						}
					} else {
						testStepFailed("Icons not available");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Icons not available");
		}
	}

	/**
	 * Description : Method to click on button
	 * @param buttonName is the name of the buttons
	 */
	public void clickOnButton(String buttonName) {
		try {
			String button = buttonName + "#xpath=//button[contains(text(),'" + buttonName + "')]";
			clickOn(button);
			testStepPassed(buttonName + " is clicked");
		} catch (Exception e) {
			testStepFailed(buttonName + " is not clicked");
		}
	}

	/**
	 * Description : Method to check the status
	 * @param fieldName is the name of the fields
	 */
	public void statusCheck(String fieldName) {
		WebElement status = findWebElement("Status#xpath=//*[@id='rstatus']");
		if (status.isEnabled()) {
			testStepPassed("Status is disabled");

		} else {
			testStepInfo("Status is enabled");
		}
	}

	/**
	 * Description : Method to click move button
	 * @param inputValue is the value of the inputs
	 */
	public void clickMoveButton(String inputValue) {
		try {

			if (isElementDisplayed(GOR.btns_move)) {
				List<WebElement> moveButtons = findWebElements(GOR.btns_move);
				for (int buttons = 0; buttons < moveButtons.size(); buttons++) {
					String buttonName = moveButtons.get(buttons).getAttribute("class");
					if (buttonName.contains(inputValue)) {
						if (moveButtons.get(buttons).isDisplayed()) {
							moveButtons.get(buttons).click();
							testStepPassed(buttonName + "is clicked");
							break;
						}
					}
				}
			} else {
				testStepFailed(inputValue + "button is not displayed.");
			}

		} catch (Exception e) {
			testStepFailed(inputValue + "button is not displayed.");
		}
	}

	/**
	 * Description : Method to click on the calender buttons
	 * @param labelName is the name of the labels
	 */
	public void clickCalenderButtons(String labelName) {
		try {

			if (isElementPresent(GOR.btns_Calender)) {
				List<WebElement> calButtons = findWebElements(GOR.btns_Calender);
				for (int buttons = 0; buttons < calButtons.size(); buttons++) {
					String buttonName = calButtons.get(buttons).getText();
					if (buttonName.equals(labelName)) {
						calButtons.get(buttons).click();
						testStepPassed(buttonName + " is clicked");
						break;
					}
				}
			} else {
				testStepFailed(labelName + "button is not displayed.");
			}

		} catch (Exception e) {
			testStepFailed(labelName + "button is not displayed.");
		}
	}

	/**
	 * Description : Method to verify if the check box is empty or not
	 * @param inputLabel is the label of the inputs
	 */
	public void verifyTextBoxIsEmpty(String inputLabel) {
		try {

			List<WebElement> tab_labels = findWebElements(GOR.inward_labels);
			for (int label = 0; label < tab_labels.size(); label++) {
				String label_text = tab_labels.get(label).getText().trim();
				if (label_text.equals(inputLabel)) {
					String textValue = tab_labels.get(label).findElement(By.xpath("./..//input")).getText();
					if (textValue.isEmpty()) {
						testStepPassed(label_text + " is empty");
						break;
					}

					else {
						testStepPassed(inputLabel + " is not empty");
						break;
					}
				} else if (label == (tab_labels.size() - 1)) {
					testStepFailed("Unable to find the " + inputLabel + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}

	/**
	 * Description : Method to get lines disabled text field value
	 * @param element is given for the tab names
	 * @param labelValue is the value of the labels
	 * @return textvalue
	 */
	public String getLinesDisabledTextFieldValue(String element, String labelValue) {
		String textvalue = "";
		try {
			List<WebElement> textFields = findWebElements(element);
			if (!textFields.isEmpty()) {
				for (int index = 0; index < textFields.size(); index++) {
					String label = textFields.get(index).getAttribute("placeholder");
					if (label.equals(labelValue)) {
						WebElement textField = textFields.get(index);
						if (textField.isDisplayed()) {
							JavascriptExecutor jscript = (JavascriptExecutor) driver;
							textvalue = (String) jscript.executeScript("return arguments[0].value", textField);
							testStepPassed(textvalue + " Is displayed and retrieve from " + labelValue);
							break;
						}
					}
				}
			} else {
				testStepFailed("Unable to find " + labelValue);
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + labelValue);
		}
		return textvalue;
	}

	/**
	 * Description : Method to verify if the lines text box is empty or not 
	 * @param element is given for the tab names
	 * @param inputValue is the value of the inputs
	 */
	public void verifyLinesTextBoxIsEmpty(String element, String inputValue) {
		try {

			List<WebElement> textFields = findWebElements(element);

			for (int index = 0; index < textFields.size(); index++) {
				String text = textFields.get(index).getAttribute("placeholder");
				if (text.equals(inputValue)) {
					String textValue = textFields.get(index).getText();
					if (textValue.isEmpty()) {
						testStepPassed(text + "is empty");
						break;
					}
					else {
						testStepPassed(inputValue + " is not empty");
						break;
					}
				} else if (index == (textFields.size() - 1)) {
					testStepFailed("Unable to find the " + inputValue + ".");
				}
			}
		}
		catch (Exception e) {
			testStepFailed("Unable to find the " + inputValue + ".");
		}
	}

	/**
	 * Description : Method to validate inward grid button
	 * @param buttonLabel is the value of the button labels
	 */
	public void inwardGridButtonValidation(String buttonLabel) {
		try {
			if (isElementPresent(GOR.gridButtons)) {
				List<WebElement> tab_labels = findWebElements(GOR.gridButtons);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getAttribute("title").trim();
					if (buttonLabel_rt.equals(buttonLabel)) {

						if (tab_labels.get(label).isEnabled()) {
							testStepPassed(buttonLabel + " Is Enabled");
						} else {
							testStepPassed(buttonLabel + " Is Disabled");
						}
						break;
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to verify if the text box is displayed or not
	 * @param inputLabel is the label of the inputs
	 */
	public void verifyTextBoxisDisplayed(String inputLabel) {
		try {

			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (tab_labels.get(label).isEnabled()) {

							testStepPassed("\"" + label_text + "\" textbox is displayed");
							break;
						} else {
							testStepPassed(label_text + " textbox is not displayed");
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}

	/**
	 * Description : Method to verify the select page drop down field
	 * @param labelName is the name of the labels
	 */
	public void verifySelectPageDropDownfield(String labelName) {
		try {

			List<WebElement> labels = findWebElements(GOR.pageDropdown);
			List<WebElement> page_dropdown = findWebElements(GOR.recordsPerPage_label);
			for (int index = 0; index < labels.size(); index++) {
				if (labels.get(index).isDisplayed()) {
					String label_text = page_dropdown.get(index).getText();
					if (label_text.contains(labelName))
						testStepPassed(labelName + "is enabled in the page");
				} else if (index == (labels.size() - 1)) {
					testStepFailed("Unable to find the page_dropdown field ");
				}

			}
		} catch (Exception e) {
			testStepFailed("Unable to find the page_dropdown field ");
		}
	}

	/**
	 * Description : Method to verify allocate location button
	 */
	public void VerifyAllocateLocationbutton() {
		try {
			if (isElementPresent(GOR.allocateLocation_btn)) {
				testStepPassed("Allocate Loction button is displayed");
			} else {
				testStepFailed("Unable to find the Allocate Location button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the Allocate Location button.");
		}
	}

	/**
	 * Description : Method to verify the lines drop down is displayed
	 * @param labelName is the name of the labels
	 */
	public void VerifyLinesDropDownIsDisplayed(String labelName) {
		try {
			String dropdown_Icon = "Dropdown Icon #xpath=//*[text()='" + labelName
					+ "']/../..//span[@class='select2-selection__rendered']";
			if (isElementDisplayed(dropdown_Icon)) {

				testStepPassed("Dropdown is displayed Successfully");
			} else {
				testStepInfo("Not able to find Dropdown");
			}
		} catch (Exception e) {
			testStepInfo("Not able to find Dropdown");

		}
	}

	/**
	 * Description : Method to verify input text no results found message
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 */
	public void verifyInputTextNoResultsFoundMessage(String inputLabel, String inputValue) {
		try {
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						tab_labels.get(label).sendKeys("$^*((^%%#@");
						String invalidValue = tab_labels.get(label).getAttribute("class");
						if (invalidValue.contains("invalid-parse")) {
							testStepPassed("invalid text entered in the textbox- No results Found");
							break;
						} else {
							testStepPassed("invalid text is not entered in the textbox");
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Textbox is empty.");
		}
	}

	/**
	 * Description : Method to verify page navigation buttons 
	 * @param inputValue is the value of the inputs
	 */
	public void verifyPageNavigationButtons(String inputValue) {
		try {
			List<WebElement> buttons = findWebElements(GOR.button_Navigation);

			for (int index = 0; index < buttons.size(); index++) {
				String labelText = buttons.get(index).getAttribute("title");
				if (labelText.equals(inputValue)) {
					if (isElementDisplay(buttons.get(index))) {
						testStepPassed(labelText + "is displayed");

						break;
					} else {
						testStepPassed(labelText + " is not displayed");
						break;
					}
				} else if (index == (buttons.size() - 1)) {
					testStepFailed("Unable to find the " + inputValue + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputValue + ".");
		}
	}

	/**
	 * Description : Method to verify the inner ellipsis header
	 * @param inputValue is the value of the inputs
	 */
	public void verifyInnerEllipsisHeader(String inputValue) {
		try {
			if (isElementDisplayed(GOR.elipsis_innerHeader)) {
				String LocationHeader = getText(GOR.elipsis_innerHeader);

				if (LocationHeader.equals(inputValue)) {
					testStepPassed(inputValue + " is displayed in the Location Header");
				} else {
					testStepFailed(inputValue + " is not displayed in the Location Header");
				}
			}
		} catch (Exception e) {
			testStepFailed(inputValue + " is not displayed in the Location Header");
		}
	}

	/**
	 * Description : Method to click location
	 * @param locationStatus is the status value of location
	 */
	public void clickLocation(String locationStatus) {
		try {
			List<WebElement> locations = findWebElements(GOR.locRowName);
			if (!locations.isEmpty()) {
				for (int loc = 0; loc < locations.size(); loc++) {
					String locStatus = locations.get(loc).getAttribute("class");
					String allocatedCount = locations.get(loc).getAttribute("ng-class").split("!")[0];
					String locationName = locations.get(loc).getText();
					int count = Integer.valueOf(allocatedCount);
					if (locStatus.contains(locationStatus) && count <= 10 && count != 0) {
						locations.get(loc).click();
						testStepPassed("Location Allocated In " + locationName);
						break;
					} else if (locStatus.contains(locationStatus) && count == 0) {
						locations.get(loc).click();
						testStepPassed("Location Allocated In " + locationName);
						break;
					} else if (loc == (locations.size() - 1)) {
						testStepFailed("Locaion Is Not Available as expected");
					}
				}
			} else {
				testStepFailed("Unable to find Locations");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find Locations");
		}
	}

	/**
	 * Description : Method to verify no results found in drop down
	 * @param labelName is the name of the labels
	 * @param inputValue is the value of the inputs
	 */
	public void verifyNoresultsFoundInDropdown(String labelName, String inputValue) {
		try {
			System.out.println();
			if (isElementDisplayed(GOR.dropDown_searchField)) {
				clickOn(GOR.dropDown_searchField);
				typeIn(GOR.dropDown_searchField, inputValue);
				if (isElementDisplayed(GOR.noResultsFoundVerify)) {
					testStepPassed("No results found for " + inputValue);
				} else {
					testStepPassed("Results found for " + inputValue);
				}
			}
		} catch (Exception e) {
			testStepFailed("No Results found for " + inputValue+ " value entered in the "+labelName+" text field");
		}
	}

	/**
	 * Description : Method to verify the progress bar
	 * @param labelName is the name of the labels
	 * @param InputValue is the value of the inputs
	 */
	public void verifyProgressbar(String labelName, String InputValue) {
		try {

			List<WebElement> bars = findWebElements(GOR.progressBars);
			List<WebElement> bars_label = findWebElements(GOR.progressBarlabels);
			for (int index = 0; index < bars.size(); index++) {
				String progressBar = bars.get(index).getText().trim();

				String progressBarLabel = bars_label.get(index).getText().trim();
				if (progressBarLabel.equals(labelName)) {

					if (progressBar.equals(InputValue)) {
						testStepPassed(labelName + " is updated successfully");
						break;
					} else {
						testStepPassed(labelName + " is not updated");
						break;
					}

				} else if (index == (bars.size() - 1)) {
					testStepFailed("Unable to find the " + labelName + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + labelName + ".");
		}
	}

	/**
	 * Description : Method to verify the circle block
	 * @param labelName is the name of the labels
	 * @param InputValue is the value of the inputs
	 */
	public void verifyCircleBlock(String labelName, String InputValue) {
		try {
			System.out.println();
			List<WebElement> blocks = findWebElements(GOR.unitValue_CircleBlock);
			List<WebElement> blocks_label = findWebElements(GOR.label_circleBlock);
			for (int index = 0; index < blocks.size(); index++) {
				String blockUnit = blocks.get(index).getText().trim();

				String blockLabels = blocks_label.get(index).getText().trim();
				if (blockLabels.equals(labelName)) {

					if (blockUnit.equals(InputValue)) {
						testStepPassed(labelName + " is updated successfully");
						break;
					} else {
						testStepPassed(labelName + " is not updated");
						break;
					}
				} else if (index == (blocks.size() - 1)) {
					testStepFailed("Unable to find the " + labelName + ".");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + labelName + ".");
		}
	}

	/**
	 * Description : Method to check if a label is present in system filters
	 * @param label is the value of the labels
	 */
	public void systemFilterLabels(String label) {
		try {
			String labels = "Label#Xpath=//*[@title='" + label + "']";
			if (isElementPresent(labels)) {
				testStepPassed(label + " is available in System filters");

			} else {
				testStepInfo(label + " is not available");
			}
		} catch (Exception e) {
			testStepFailed(label + " is not available");
		}
	}

	/**
	 * Description : Method to get rows in the outer table
	 * @param element is given for the tab names
	 * @return rowCount
	 */
	public int getRowsInOuterTable(String element) {
		int rowCount = 0;
		try {
			rowCount = findWebElements(element).size();
		} catch (Exception e) {
			testStepFailed("Unable to find the rows in outer table.");
		}
		return rowCount;
	}

	/**
	 * Description : Method to click on the filter footer button 
	 * @param labelName is the name of the labels
	 */
	public void clickOnFilterFooterButtons(String labelName) {
		try {
			if (isElementPresent(GOR.footerBtns_filter)) {
				List<WebElement> buttons = findWebElements(GOR.footerBtns_filter);
				for (int index = 0; index < buttons.size(); index++) {
					String buttonLabel = buttons.get(index).getText().trim();
					if (buttonLabel.equals(labelName)) {
						buttons.get(index).click();
						testStepPassed(labelName + "  is clicked successfully");
						break;
					} else if (index == (buttons.size() - 1)) {
						highLighterMethod(GOR.filterFooterBar);
						testStepFailed("Unable to find " + labelName);
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find " + labelName);
		}
	}

	/**
	 * Description : Method to click the radio button
	 * @param button is the value of the button
	 * @param isClick - true, false
	 */
	public void radioButton(String button, boolean isClick) {
		try {
			String radioButton = "Radio button#xpath=//label//input[@type='radio' and @value='" + button
					+ "']/..//span";
			if (isElementPresent(radioButton)) {
				testStepPassed(button + " radio button is available");
				if (isClick) {
					clickOn(radioButton);
					testStepPassed("" + button + " button is clicked successfully.");
				}
			} else {
				testStepInfo(button + " radio button is not available");
			}
		} catch (Exception e) {
			testStepFailed(button + " radio button is not available");
		}
	}

	/**
	 * Description : Method to get text inside the column
	 */
	/*public void getTextFilter() {
		try {
			String button = "Button#xpath=//div[contains(@data-ng-show,'Existing')]//div[contains(@class,'text-single-line')]";

			if(isElementPresent(button)){
				
			String data = findWebElement(button).getText();
			testStepPassed(data + " is found inside the column");
			}else {
				testStepPassed("No records found message is displayed");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find data inside the column.");
		}
	}
*/
	
	public void getTextFilter() {
		try {
			String button = "Button#xpath=//div[contains(@class,'clearfix text-center')]//i";
			String data = findWebElement(button).getText();
			testStepPassed(data + " is found inside the column");
		} catch (Exception e) {
			testStepInfo("Unable to find data inside the column.");
		}
	}
	
	
	/**
	 * Description : Method to check if filter button is available or not
	 * @param newButton is the value of new button
	 */
	public void saveAsFilterText(String newButton) {
		try {
			List<WebElement> buttons = findWebElements(GOR.filterText);
			for (int index = 0; index < buttons.size(); index++) {
				String buttonLabel = buttons.get(index).getText().trim();

				if (buttonLabel.equals(newButton)) {
					testStepPassed(newButton + " is available in the filter");
					break;
				} else {					
				}
			}
		} catch (Exception e) {
			testStepFailed(newButton+" is not available in the filter");
		}
	}

	/**
	 * Description : Method to check if button label is empty or not.
	 */
	public void saveAsFilterFields() {
		try {
			List<WebElement> buttons = findWebElements(GOR.filterText);
			for (int index = 0; index < buttons.size(); index++) {
				String buttonLabel = buttons.get(index).getText().trim();
				if (buttonLabel.isEmpty()) {
					testStepPassed("No records available. Create a filter.");
				} else {
					testStepPassed(buttonLabel + " is available");
				}
			}
		} catch (Exception e) {
			testStepFailed("ButtonLabel is available");
		}
	}

	/**
	 * Description : Method to navigated to Organization filter page
	 */
	/*public void organizationFilter() {
		if (isElementPresent(GOR.organizationFilter)) {
			testStepPassed("Navigated to Organization filter page ");
		} else {
			clickHeaderButton(GOR.headerButtons, ALR.filterLabel, true);
		}
	}*/

	/**
	 * Description : Method to verify grid header button
	 * @param labelName is the name of the labels
	 */
	public void verifyGridHeaderButton(String labelName) {
		try {
			if (isElementDisplayed(GOR.headerGridButtons)) {
				String textValue = getText(GOR.headerGridButtons);
				if (labelName.equals(textValue)) {
					testStepPassed(labelName + " button is displayed in the grid header");
				} else {
					testStepPassed("unable to find the grid button" + labelName);
				}
			}
		} catch (Exception e) {
			testStepFailed("unable to find the grid button" + labelName);
		}
	}

	/**
	 * Description : Method to check if button label is available
	 */
	public void aSNLinesHeader() {
		try {
			String header = "Header Label#xpath=//table[contains(@id,'InwardAsnLinesCtrl')]//th";
			List<WebElement> headerLabel = findWebElements(header);
			for (int index = 2; index <= 15/*headerLabel.size()*/; index++) {
				String buttonLabel = headerLabel.get(index).getText().trim();
				testStepPassed(buttonLabel + " is available");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find button label");
		}
	}

	/**
	 * Description : Method to verify if date text box is displayed or not
	 * @param inputLabel is the label of the inputs
	 */
	public void verifyDateTextBoxisDisplayed(String inputLabel) {
		try {
			WebElement label = findWebElement(
					"label #xpath=//button[contains(@data-ng-click,'" + inputLabel.replaceAll(" ", "") + "')]");
			if (isElementDisplay(label)) {
				testStepPassed(inputLabel + "Textbox is displayed");
			} else if (!label.isEnabled()) {
				testStepPassed("Field is disabled " + inputLabel);
			} else {
				testStepFailed("Unable to enter date in " + inputLabel);
			}
		} catch (Exception e) {
			testStepFailed("Unable to enter date in " + inputLabel);
		}
	}

	/**
	 * Description : Method to verify current date, month and year
	 */
	public void verifyCurrentDateMonthAndYear() {
		try {
			LocalDate current = LocalDate.now();
			String currentMonth = String.valueOf(current.getMonth());
			String currentYear = String.valueOf(current.getYear());
			String currentDate = String.valueOf(current.getDayOfMonth());
			if (isElementDisplayed(GOR.header_calendar)) {
				String MonthAndYearInHeader = getText(GOR.header_calendar);

				if (MonthAndYearInHeader.split(" ")[0].toLowerCase().equals(currentMonth.toLowerCase())) {
					testStepPassed(currentMonth + " is displayed in the calendar header");
				}

				if (MonthAndYearInHeader.contains(currentYear)) {
					testStepPassed(currentYear + " is displayed in the calendar header");
				}
			}
			if (isElementDisplayed(GOR.highlighted_Date)) {
				String highlightedDate = getText(GOR.highlighted_Date);
				if (highlightedDate.equals(currentDate)) {
					testStepPassed(currentDate + " is highlighted in the calendar");
				}
			}
		} catch (Exception e) {
			testStepFailed("CurrentDate is not highlighted in the calendar.");
		}
	}

	/**
	 * Description : Method to enter input and verify the tab
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterInputAndVerifyTab(String inputLabel, String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							tab_labels.get(label).clear();
							tab_labels.get(label).sendKeys(inputValue);
							if (isElementPresent(GOR.tab_ellipsis)) {
								testStepPassed("The tab is displayed with" + inputValue);
								if (withTab) {
									clickTab(tab_labels.get(label));
								}

								testStepPassed("\"" + inputValue + "\" is entered into the text field.");
								break;
							}
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}

	/**
	 * Description : Method to validate the switch tab
	 */
	public void validateTabSwitch() {
		try {
			if (isElementPresent(GOR.inwardGeneral)) {
				testStepPassed("Navigated to new tab");

			} else {
				clickPlusIcon(true);
			}

		} catch (Exception e) {
			testStepFailed("Unable to navigate to new tab.");
		}
	}

	/**
	 * Description : Method to click on the filter labels
	 * @param labelName is the name of the filter label
	 */
	public void systemFiltersLabel(String labelName) {
		try {
			String label = "Label#xpath=//div[@title='" + labelName + "']/../..//div//button[text()='View']";
			clickOn(label);
			waitForElement(GOR.tableGrid);
		} catch (Exception e) {
			testStepFailed("Unable to click on filter labels.");
		}
	}

	/**
	 * Description : Method to click on buttons
	 * @param element is given for the tab names
	 * @param isClick - true, false
	 */
	public void clickButtons(String element, boolean isClick) {
		try {
			if (isElementPresent(element)) {
				testStepPassed("");
				if (isClick) {					
					clickOn(element);
					waitForAngularLoad();
				}
			} else {
				testStepFailed("Unable to find the button.");
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the button.");
		}
	}

	/**
	 * Description : Method to upload autoit file
	 * @param documentname is the name of the document
	 * @param element is given for the tab names
	 */
	public void autoitfileupload(String documentname, String element) {
		String filepath = System.getProperty("user.dir") + "\\downloads\\" + documentname;
		writeToLogFile("INFO", "File Location:" + filepath);
		String autoITpath = System.getProperty("user.dir") + "\\auto\\fileupload.exe";
		writeToLogFile("INFO", "AutoIT Location:" + autoITpath);
		String location = filepath;
		try {
			if (isElementPresent(element)) {
				clickOn(element);
				writeToLogFile("INFO", "Upload button is clicked");
				ProcessBuilder pb = new ProcessBuilder(autoITpath, location);
				pb.start();
			} }catch (Exception e) {
				testStepFailed("failed to upload file" + documentname);
				writeToLogFile("ERROR", "failed to upload file" + documentname + ".ERROR: " + e.getMessage());
			}
	}

	/**
	 * Description : Method to schedule the button
	 */
	public void scheduleButton() {
		try {
			String newButton="Button#xpath=//input[@placeholder='Name']";
			if (isElementPresent(newButton)) {
				testStepPassed("New text box is available");
			}if (isElementPresent(GOR.filterSave)) {
				testStepPassed("Save is available");
			}else {
				testStepFailed("Buttons not available");
			}
		}catch (Exception e) {
			testStepFailed("Buttons not available");
		}
	}

	/**
	 * Description : Method to verify if text field is disabled or not and if text can be entered
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void VerifyTextFieldIsDisabledAndEnterText(String inputLabel, String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							String disabledField=tab_labels.get(label).getAttribute("disabled");
							if(disabledField.equals("true"))
							{
								testStepPassed(inputLabel + " Field is disabled.");
								break;
							}
							else{
								tab_labels.get(label).sendKeys(inputValue);
								waitForElement(GOR.tab_ellipsis);
								if (withTab) {
									clickTab(tab_labels.get(label));
								}
								testStepPassed("\"" + inputValue + "\"  is entered into the " + inputLabel + " field.");
								break;
							} 
						} else if (label == (tab_labels.size() - 1)) {
							testStepFailed("Unable to find the " + inputLabel + ".");
						}
					}
				}
			}
		}
		catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}	

	/**
	 * Description : Method to click on the dock drop down
	 */
	public void clickDockDropDown()
	{
		try
		{
			if(isElementDisplayed(GOR.selectDock_dropdown))
			{
				clickOn(GOR.selectDock_dropdown);
			}
			else
			{
				testStepPassed("Select Dock Number Drop down is not displayed");
			}
		}catch(Exception e)
		{
			testStepFailed("Select Dock Number Drop down is not displayed.");
		}
	}	

	/**
	 * Description : Method to select value from dock drop down
	 * @param inputValue is the value of the inputs
	 */
	public void selectDockDropDown(String inputValue)
	{
		try
		{
			List<WebElement> drpValues = findWebElements(GOR.DockDropdownValue);
			for (int index = 0; index < drpValues.size(); index++) {
				String optionText = drpValues.get(index).getText();
				if (optionText.equals(inputValue)) {
					drpValues.get(index).click(); 
					break;
				} 
				else if (index == (drpValues.size() - 1)) {
					testStepFailed("Unable to find the " + inputValue + ".");
				}
			}	
		}
		catch(Exception e)
		{
			testStepFailed("Unable to find the " + inputValue + ".");
		}
	}	

	/**
	 * Description : Method to verify the detach gate pass button enabled
	 */
	public void verifyDetachGatePassButtonEnabled() {
		try {
			List<WebElement> buttons = findWebElements(GOR.button_dettacGatepass);
			for (int index = 0; index < buttons.size(); index++) {
				if (buttons.get(index).isDisplayed()) {

					if (buttons.get(index).isEnabled()) {
						testStepPassed("Detach GatePass button is Enabled");
					}

				} else {
					testStepPassed("Detach GatePass button is Disabled");
				}
			}

		} catch (Exception e) {
			testStepFailed("Detach GatePass button is Disabled");
		}
	}

	/**
	 * Description : Method to click on the existing filter button
	 * @param Value is the value of the inputs
	 */
	/*public void existingFilter(String Value) {
		try {
			String updateButton="ButtonXpath=//div[contains(@data-ng-show,'Existing')]//div[text()='"+Value+"']/../..//div//button[text()='Update']";

			String text="Text#xpath=//div[contains(@data-ng-show,'Existing')]";
			List<WebElement> element=findWebElements(text);
			for (int index = 1; index < element.size(); index++) {
				String newText=element.get(index).getText().trim();
				if (newText.equals(Value)) {
					clickOn(updateButton);

				}else {
					testStepFailed(Value+" is not found");
				}
			}
		}catch (Exception e) {
			testStepFailed(Value+" is not found");
		}
	}
*/
	
	
	public void existingFilter(String Value) {
		boolean flag = false;
		try {
//			String updateButton = "ButtonXpath=//div[contains(@data-ng-show,'Existing')]//div[text()='" + Value
//					+ "']/../..//div//button[text()='Update']";
			
			String updateButton = "Button#Xpath=//div[text()='"+ Value +"']/parent::div[contains(@class,'col-sm')]/following-sibling::div/button";

			//String text = "Text#xpath=//div[contains(@data-ng-show,'Existing')]";
			//div[text()='test12']/parent::div[contains(@class,'col-sm')]/following-sibling::div/button";
			String text = "Text#xpath=//div[contains(@class,'col-sm')]/button/../preceding-sibling::div/div";
			List<WebElement> element = findWebElements(text);
			for (int index = 1; index < element.size(); index++) {
				String newText = element.get(index).getText().trim();
				System.out.println(newText);
				if (newText.equals(Value)) {
					clickOn(updateButton);
					flag = true;
				}
//				} else {
//					testStepFailed(Value + " is not found");;
//				}
			}
			
			if(flag==false) {
				testStepFailed(Value + " is not found");;
			}
		} catch (Exception e) {
			testStepFailed(Value + " is not found");
		}
	}

	
	
	/**
	 * Description : Method to pick options from drop down
	 */
	public void pickOptionDropDown() {
		try {
			String header="Header Label#xpath=//li[contains(@class,'select2-results__option')]";
			List<WebElement> headerLabel = findWebElements(header);
			for (int index = 1; index < headerLabel.size(); index++) {
				String buttonLabel = headerLabel.get(index).getText().trim();

				testStepPassed(buttonLabel+" is available");
			}
		}catch (Exception e) {
			testStepFailed("Button is not available.");
		}
	}

	/**
	 * Description : Method to get the text link
	 * @param id is the id value
	 * @param header is the value of the table headers
	 */
	public void getTextLink(String id, String header) {
		try {
			String text="Text#xpath=//td[@id='"+id+"']";
			WebElement element=findWebElement(text);
			String newText=element.getText();
			testStepInfo(newText+" is present inside "+header);
		}catch (Exception e) {
			testStepFailed("Text is not present inside "+header);
		}
	}

	/**
	 * Description : Method to get the text line quantity
	 * @param header is the header value
	 */
	public void getTextLineQty(String header) {
		try {
			String text="Text#xpath=//input[@placeholder='"+header+"']";
			WebElement element=findWebElement(text);
			String newText=element.getText();
			testStepInfo(newText+"Value is present inside");
		}catch (Exception e) {
			testStepFailed("Text is not present inside "+header);
		}
	}

	/**
	 * Description : Method to get the text pick
	 */
	public void getTextPick() {
		try {
			String button="Button#xpath=//div[@class='form-group']//label";
			String data=findWebElement(button).getText();
			testStepPassed(data+" is found");
		}catch (Exception e) {
			testStepFailed("Data is not found");
		}
	}

	/**
	 * Description : Method to click on the pick number
	 * @param labelName is the name of the labels
	 * @param isClick - true, false
	 * @return pickValue
	 */	
	public String clickPickNumber(String labelName, boolean isClick) {
		String pickValue ="";
		try {
			List<WebElement> pick_Labels = findWebElements(GOR.pickLabels);
			List<WebElement> pick_Values = findWebElements(GOR.pickValues);
			if (!pick_Labels.isEmpty()) {
				for (int index = 0; index < pick_Labels.size(); index++) {
					String pickLabel = pick_Labels.get(index).getText();
					if (pickLabel.equals(labelName)) {
						pickValue = pick_Values.get(index).getText();
						if (isClick) {
							pick_Values.get(index).click();
						}
						break;
					}
				}
			} else {
				testStepFailed("Unable to find the " + labelName );
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + labelName );
		}
		return pickValue;
	}

	/**
	 * Description : Method to click outward pick release button
	 */
	public void clickOutwardPickReleaseButtons() {
		try {
			if (isElementDisplayed(GOR.releaseOutwardButton)) {
				waitTillElemetToBeClickable(findWebElement(GOR.releaseOutwardButton));
				clickOn(GOR.releaseOutwardButton);
			} else {
				testStepFailed("Release button is not displayed ");
			}
		} catch (Exception e) {
			testStepFailed("Release button is not displayed ");
		}
	}

	/**
	 * Description Method to get pick header
	 */
	public void getPickHeader() {
		try {
			String header="Button#xpath=//div[@class='form-group']//label";
			List<WebElement> headerLabel = findWebElements(header);
			for (int index = 1; index < headerLabel.size(); index++) {
				String buttonLabel = headerLabel.get(index).getText().trim();
				testStepPassed(buttonLabel+" is available");
			}
		}catch (Exception e) {
			testStepFailed("Button is not available.");
		}
	}

	/**
	 * Description : Method to validate non editable fields in pick slip
	 */
	public void validateNonEditableFieldsinPickSlip() {
		try {
			String value = getAttributeValue(GOR.pickSlipTableNonEditable, "class");
			if (value.contains("empty") && !findWebElement(GOR.pickSlipTableNonEditable).isEnabled()) {
				testStepInfo("Fields in Pick Slip are non editable.");
			} else {
				testStepFailed("Fields in Pick Slip are editable.");
			}
		} catch (Exception e) {
			testStepFailed("Fields in Pick Slip are editable.");
		}
	}

	/**
	 * Description : Method to verify total packages value
	 * @param Value is the value of the inputs
	 * @param header is the header value
	 */
	public void totalPackagesvalue(String Value, String header) {
		try {
			String text="Text#xpath=//div[@class='form-group']//label[text()='"+header+"']/..//div//input";
			WebElement element=findWebElement(text);
			String newText=element.getText();
			if (newText!=Value) {
				testStepPassed(Value+" is not accepted");

			}else {
				testStepFailed(Value+" is accepted");
			}

		}catch (Exception e) {
			testStepFailed(Value+" is accepted");
		}
	}

	/**
	 * Description : Method to pick outward
	 * @param value is the value of the inputs
	 */
	public void pickOutward(String value){
		try{
			String text="Text#xpath=//td[@id='ooutwardno'][text()='"+value+"']";
			if (isElementPresent(text)) {
				testStepPassed(value+" details is avialable in the pick number" );

			}else {
				testStepFailed("outward number is not found");
			}
		}catch (Exception e) {
			testStepFailed("outward number is not found");
		}
	}

	/**
	 * Description : Method to get text after finalize
	 */
	public void getTextAfterFinalize() {
		try {
			String header="Button#xpath=//button[contains(@ng-disabled,'DisableSave')]";
			List<WebElement> headerLabel = findWebElements(header);
			for (int index = 1; index < headerLabel.size(); index++) {
				String buttonLabel = headerLabel.get(index).getText().trim();
				testStepPassed(buttonLabel+" is disabled");
			}
		}catch (Exception e) {
			testStepFailed("Button is enabled");
		}
	}

	/**
	 * Description : Method to verify side tabs
	 * @param element is given for the tab names
	 * @param tabName is the name of the tabs
	 */
	public void verifySideTabs(String element, String tabName)
	{
		try {
			if (isElementDisplayed(element)) {
				List<WebElement> tab_labels = findWebElements(element);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getText().trim();
					if (label_text.equals(tabName)) {
						if(tab_labels.get(label).isDisplayed())
						{
							testStepPassed("\"" + tabName + "\" Tab is displayed.");
						}
					}
					else if (label == (tab_labels.size() - 1)) {
					}
				}
			}
		}
		catch (Exception e) {
			testStepFailed("Unable to find the " + tabName + " tab.");
		}
	}

	/**
	 * Description : Method to click other grid buttons
	 * @param element is given for the tab names
	 * @param buttonLabel is the value of the button labels
	 * @param isClick - true, false
	 */
	public void clickOtherGridButtons(String element, String buttonLabel, boolean isClick) {
		try {
			if (isElementPresent(GOR.gridButtons)) {
				List<WebElement> tab_labels = findWebElements(GOR.gridButtons);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getText().trim();
					if (buttonLabel_rt.equals(buttonLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							testStepPassed("\"" + buttonLabel_rt + "\" button is displayed in grid section.");
							if (isClick) {
								tab_labels.get(label).click();
								testStepPassed("\"" + buttonLabel_rt + "\" button is clicked successfully.");
							}
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to verify labels
	 * @param labelName is the name of the labels
	 */
	public void verifyLabels(String labelName)
	{
		try
		{
			String labelNamepath="LabelName #xpath=//label[@title='"+labelName+"']";

			if(isElementDisplayed(labelNamepath))
			{
				testStepPassed(labelName+ "is displayed");
			}
			else
			{
				testStepFailed(labelName+ "is not displayed");
			}
		}catch(Exception e)
		{
			testStepFailed(labelName+ "is not displayed");
		}
	}
	


	/**
	 * Description : Method to verify lines drop down is not empty
	 * @param labelName is the name of the labels
	 */
	public void VerifyLinesDropDownIsNotEmpty(String labelName) {
		try {
			String dropdown_Icon = "Dropdown Icon #xpath=//*[text()='" + labelName
					+ "']/../..//span[@class='select2-selection__rendered']";
			if (!dropdown_Icon.isEmpty()) {

				testStepPassed(labelName+ "Dropdown is not empty");
			} else {
				testStepFailed("Not able to find Dropdown");
			}
		} catch (Exception e) {
			testStepFailed("Not able to find Dropdown");
		}
	}

	/**
	 * Description : Method to enter input value in search field
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterInputValueInSearchField(String inputLabel, String inputValue, boolean withTab) {
		try {			
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textbox);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();					
					if (label_text.equals(inputLabel)) {
						if (tab_labels.get(label).isDisplayed()) {
							tab_labels.get(label).clear();
							tab_labels.get(label).sendKeys(inputValue);
							if (withTab) {
								clickTab(tab_labels.get(label));
							}
							testStepPassed("\"" + inputValue + "\" is entered into the " + inputLabel + " field.");
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}

	/**
	 * Description : Method to click on the stock transfer grid buttons
	 * @param element is given for the tab names
	 * @param buttonLabel is the value of the button labels
	 * @param isClick - true, false
	 */
	public void clickStockTransferGridButtons(String element, String buttonLabel, boolean isClick) {
		try {
			if (isElementPresent(GOR.gridButtons)) {
				List<WebElement> tab_labels = findWebElements(GOR.gridButtons);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getText().trim();
					if (buttonLabel_rt.equals(buttonLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							testStepPassed("\"" + buttonLabel_rt + "\" button is displayed in grid section.");
							if (isClick) {
								tab_labels.get(label).click();
								testStepPassed("\"" + buttonLabel_rt + "\" button is clicked successfully.");
							}
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to verify the configuration tab
	 * @param field is the name of the field
	 * @return value
	 */
	public String ConfigurationTab(String field) {
		String value = "";
		try {
			value = "Header#xpath=//div[@class='tab-pane ng-scope active']//*[text()='" + field + "']";
			if (isElementPresent(value)) {
				testStepPassed(field + " is present in the table");
			} else {
				testStepInfo("Data not available in the table");
			}
		} catch (Exception e) {
			testStepInfo("Data not available in the table");
		}
		return value;
	}

	/**
	 * Description : Method to get attribute input value
	 * @param labe1name is the name of the filter label
	 * @param ExpectedText is the value in the data sheet
	 */
	public void getAttributeInputValue(String labe1name, String ExpectedText) {
		String webelement = "#xpath=.//input[@placeholder='" + labe1name + "']";
		try {
			String ActualText = findWebElement(webelement).getAttribute("value");
			if (ActualText.equalsIgnoreCase(ExpectedText)) {
				testStepPassed("passing input value is available in " + labe1name + " input vield");
			} else {
				testStepFailed(labe1name + " accepted the value entered");

			}
		} catch (Exception e) {
			testStepFailed("passing input value is available in " + labe1name + " input vield");
		}
	}

	/**
	 * Description : Method to get warning message icon
	 * @param label is the value of the labels
	 * @param check - true, false
	 * @return result
	 */
	public boolean getWarningIconMessage(String label, Boolean check) {
		Boolean result = null;
		String webelement = "#xpath=//*[text()='" + label + "']/../span//i";
		if (check) {
			try {
				findWebElement(webelement);
				boolean status = isElementDisplayed(webelement);
				if (status) {
					result = true;
				} else {
					result = false;
				}
			} catch (NoSuchElementException e) {
				result = false;
			}
		} else {
			try {
				findWebElement(webelement);

			} catch (NoSuchElementException e) {
				result = true;
			} catch (Exception e) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Description : Method to verify contact text box
	 * @param label is the value of the labels
	 * @param text is the value in the contact box
	 */
	public void contactBoxText(String label, String text) {
		try {

			String webelement = "#xpath=.//div[contains(@data-ng-if,'" + label + "')]//div[contains(text(),'" + text
					+ "')]";
			boolean Status = findWebElement(webelement).isDisplayed();
			if (Status) {
				testStepPassed("It should display the created organization details with " + text + "in contact box");
			} else {
				testStepFailed("It should display the created organization details" + text + "in contact box");
			}
		} catch (NoSuchElementException e) {
			testStepFailed("It should display the created organization details" + text + "in contact box");
		}
	}

	/**
	 * Description : Method to select tag drop down
	 * @param label is the value of the labels
	 * @param text is the value in the drop down
	 */
	public void selectTagDropDownSelect(String label, String text) {
		try {
			String element = "#xpath=.//select[@name='" + label + "']";
			Select select = new Select(findWebElement(element));
			select.selectByVisibleText(text);
			testStepPassed("select the value in " + label + "dropdown");
		} catch (Exception e) {
			testStepFailed("select the value in " + label + "dropdown");
		}
	}

	/**
	 * Description : Method to verify organization warehouse
	 * @param labelValue is the value of the labels
	 */	
	public void orgWarehouse(String labelValue) {
		try {
			String label = "Label#xpath=//div[@id='wmsBasic']//a//uib-tab-heading[@title='" + labelValue + "']";
			if (isElementPresent(label)) {
				testStepPassed(labelValue + " is available");
			} else {
				testStepFailed(labelValue + " is not available");
			}
		} catch (Exception e) {
			testStepFailed(labelValue + " is not available");
		}
	}

	/**
	 * Description : Method to edit organization
	 */	
	public void organizationEdit() {
		try {
			String warehouseLabel = "Label#xpath=//span[@class='modal-title modalheader ng-binding']";
			String save = "Button#xpath=//div[contains(@class,'modal-footer')]//button[text()='Save']";
			String Close = "Button#xpath=//div[contains(@class,'modal-footer')]//button[text()='Close']";
			if (isElementPresent(warehouseLabel)) {
				testStepPassed("Warehouse is available");
			}
			if (isElementPresent(GOR.packingcheckBox)) {
				testStepPassed("Packing Date checkbox is available");
			}
			if (isElementPresent(GOR.expirycheckBox)) {
				testStepPassed("Expiry Date checkbox is available");
			}
			if (isElementPresent(save)) {
				testStepPassed("Save is available");
			}
			if (isElementPresent(Close)) {
				testStepPassed("Close is available");
			} else {
				testStepFailed("Buttons not available");
			}
		} catch (Exception e) {
			testStepFailed("Buttons not available");
		}
	}

	/**
	 * Description : Method to verify organization labels
	 * @param label is the value of the labels
	 */
	public void organizationLabel(String label) {
		try {
			String labelName = "label#xpath=//label[text()='" + label + "']";
			if (isElementPresent(labelName)) {
				testStepPassed(label + " is available");
			} else {
				testStepFailed(label + " not available");
			}
		} catch (Exception e) {
			testStepFailed(label + " not available");
		}
	}

	/**
	 * Description : Method to verify organization drop down type
	 * @param dropdown is the drop down value
	 */
	public void organizationDropdownType(String dropdown) {
		try {
			String labelName = "Dropdown#xpath=//div[@class='form-group']//label[text()='" + dropdown + "']";
			if (isElementPresent(labelName)) {
				testStepPassed(dropdown + " is available");
			} else {
				testStepFailed(dropdown + " not available");
			}
		} catch (Exception e) {
			testStepFailed(dropdown + " not available");
		}
	}

	/**
	 * Description : Method to verify product configuration table
	 * @param field is the name of each field
	 * @param name is the name of the product
	 */
	public void productConfigurationTable(String field, String name) {
		try {
			String value = "Table#xpath=//tbody[@class='borderless-table']//tr//*[text()='" + field
					+ "']/..//td[normalize-space()='" + name + "']";
			if (isElementPresent(value)) {
				testStepPassed(field + name + " is present in the table");
			} else {
				testStepInfo("Data not available in the table");
			}
		} catch (Exception e) {
			testStepFailed("Data not available in the table");
		}
	}

	/**
	 * Description : Method to verify bar code text area
	 * @param textbox is the value of the text box
	 * @param inputValue is the value of the inputs
	 */
	public void barcodeTextArea(String textbox, String inputValue) {
		try {
			String value = "Table#xpath=//div//label/following::textarea[contains(@ng-model,'" + textbox + "')]";
			if (isElementPresent(value)) {
				testStepPassed(textbox + " is available");
				typeIn(value, inputValue);
			} else {
				testStepFailed(textbox + " is not available");
			}
		} catch (Exception e) {
			testStepFailed(textbox + " is not available");
		}
	}

	/**
	 * Description : Method to verify pick drop down list
	 * @param header is the header value
	 */
	public void pickDropdownList(String header) {
		try {
			String value = "Values#xpath=//select[@ng-model='" + header + "']//option";
			List<WebElement> grid = findWebElements(value);
			for (int index = 1; index < grid.size(); index++) {
				String v = grid.get(index).getText().trim();
				testStepPassed(v + " is available");
			}
		} catch (Exception e) {
			testStepFailed("Data is not available");
		}
	}

	/**
	 * Description : Method to enter input with tab cycle count
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterInputWithTabCycleCount(String inputLabel, String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.textBoxCycleCount)) {
				List<WebElement> tab_labels = findWebElements(GOR.textBoxCycleCount);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							tab_labels.get(label).clear();
							tab_labels.get(label).sendKeys(inputValue);
							if (withTab) {
								clickTab(tab_labels.get(label));
							}
							testStepPassed("\"" + inputValue + "\"  is entered into the text field.");
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}	

	/**
	 * Description : Method to click on the grid button cycle count
	 * @param element is given for the tab names
	 * @param buttonLabel is the value of the button labels
	 * @param isClick - true, false
	 */
	public void clickGridButtonsCycleCount(String element, String buttonLabel, boolean isClick) {
		try {
			if (isElementPresent(GOR.gridButtonCycleCount)) {
				List<WebElement> tab_labels = findWebElements(GOR.gridButtonCycleCount);
				for (int label = 0; label < tab_labels.size(); label++) {
					String buttonLabel_rt = tab_labels.get(label).getText().trim();
					if (buttonLabel_rt.equals(buttonLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							testStepPassed("\"" + buttonLabel_rt + "\" button is displayed in grid section.");
							if (isClick) {
								tab_labels.get(label).click();
								testStepPassed("\"" + buttonLabel_rt + "\" button is clicked successfully.");
							}
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + buttonLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + buttonLabel + ".");
		}
	}

	/**
	 * Description : Method to click on the drop down cycle count
	 * @param labelName is the name of the filter label
	 */
	public void clickDropDownCycleCount(String labelName) {
		try {
			String dropdown_Icon = "Dropdown Icon#xpath=//label[text()='"+labelName+"']/../..//span[@class='select2-selection__rendered']";
			if (isElementPresent(dropdown_Icon)) {
				clickOn(dropdown_Icon);
				testStepPassed("Dropdown icon is clicked Successfully");
			} else {
				testStepFailed("Not able to find Dropdown");
			}
		} catch (Exception e) {
			testStepFailed("Not able to find Dropdown");
		}
	}

	/**
	 * Description : Method to click cycle count ellipsis icon
	 * @param labelName is the name of the filter label
	 */
	public void clickCycleCountEllipsisIcon(String labelName) {
		try {
			WebElement ellipsisIcon = findWebElement(
					"Ellipsis Icon#xpath=//div[@ng-switch-when='General']//input[@placeholder='"+labelName +"']/../..//button");
			if (ellipsisIcon.isDisplayed()) {
				ellipsisIcon.click();
				testStepPassed("Ellipsis Icon is clicked aganist  " + labelName);
			} else {
				testStepFailed("Not able to find " + labelName);
			}
		} catch (Exception e) {
			testStepFailed("Not able to find " + labelName);
		}
	}

	/**
	 * Description : Method to verify input text no results found message cycle count
	 * @param inputLabel is the label of the inputs
	 * @param inputValue is the value of the inputs
	 */
	public void verifyInputTextNoResultsFoundMessageCycleCount(String inputLabel, String inputValue) {
		try {
			if (isElementPresent(GOR.textbox)) {
				List<WebElement> tab_labels = findWebElements(GOR.textBoxCycleCount);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						tab_labels.get(label).sendKeys(inputValue);
						String invalidValue = tab_labels.get(label).getAttribute("class");
						if (invalidValue.contains("invalid-parse")) {
							testStepPassed("invalid text entered in the textbox and No results found");
							break;
						} else {
							testStepPassed("invalid text is not entered in the textbox and No results found");
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("invalid text is not entered in the textbox and No results found");
		}
	}

	/**
	 * Description : Method to click on the documents drop down
	 * @param inputValue  is the value of the inputs
	 * @param documentName is the name of the document
	 */
	public void clickDocumentsDropDown(String inputValue, String documentName) {
		try {

			String documents_list = "Document List #xpath=//div[@title='" + inputValue + "']//..//..//i";
			List<WebElement> tab_labels = findWebElements(GOR.documents_listDescription);
			List<WebElement> documents = findWebElements(documents_list);
			for (int label = 0; label < tab_labels.size(); label++) {
				String label_text = tab_labels.get(label).getText().trim();
				if (label_text.equals(inputValue)) {
					for (int label1 = 0; label1 < tab_labels.size(); label1++) {

						String document_text = documents.get(label1).getAttribute("title").trim();

						if (document_text.equals(documentName)) {
							documents.get(label1).click();
							testStepPassed(label_text + "is clicked against" + document_text);
							break;
						}
					}
					break;
				}
				else if (label == (tab_labels.size() - 1)) {
					testStepFailed("Unable to find the " + inputValue + " dropDown");
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputValue + " dropDown");
		}
	}

	/**
	 * Description : Method to enter input with tab stock transfer
	 * @param inputLabel is the label of the inputs
	 * @param inputValue  is the value of the inputs
	 * @param withTab - true, false
	 */
	public void enterInputWithTabStockTransfer(String inputLabel, String inputValue, boolean withTab) {
		try {
			if (isElementPresent(GOR.textBoxStockTransfer)) {
				List<WebElement> tab_labels = findWebElements(GOR.textBoxStockTransfer);
				for (int label = 0; label < tab_labels.size(); label++) {
					String label_text = tab_labels.get(label).getAttribute("placeholder").trim();
					if (label_text.equals(inputLabel)) {
						if (isElementDisplay(tab_labels.get(label))) {
							tab_labels.get(label).clear();
							tab_labels.get(label).sendKeys(inputValue);
							String getTextValue = tab_labels.get(label).getText();
							System.out.println(" 1111111 " + getTextValue);
							if (withTab) {
								clickTab(tab_labels.get(label));
							}
							testStepPassed("\"" + inputValue + "\" is entered into the text field.");
							break;
						}
					} else if (label == (tab_labels.size() - 1)) {
						testStepFailed("Unable to find the " + inputLabel + ".");
					}
				}
			}
		} catch (Exception e) {
			testStepFailed("Unable to find the " + inputLabel + ".");
		}
	}
	
	/**
	 * Description : Method to verify mandatory field icon check
	 * @param LabelName is the name of the filter label
	 * @param icon - true, false
	 */
	public void mandatoryfiledIconCheck(String LabelName, Boolean icon) {
		String webelement = "#xpath=.//*[.='" + LabelName + "']/..//button/i";
		boolean Status;
		if (icon) {
			try {
				Status = findWebElement(webelement).isDisplayed();
				if (Status) {
					testStepPassed("mandatory field icon is displayed under " + LabelName + "");

				} else {
					testStepFailed("mandatory field icon not displayed under " + LabelName + " fields");

				}
			} catch (NoSuchElementException e) {
				testStepFailed("mandatory field icon not displayed under " + LabelName + " fields");
			}
		}
		else if (!icon) {
			try {
				findWebElement(webelement);
				testStepFailed("mandatory field icon is displayed under " + LabelName + " fields");
			} catch (Exception e) {
				testStepPassed("mandatory field icon is not displayed under " + LabelName + "");
			}
		}
	}

	/**
	 * Description : method is used to find whether the element is displayed or not.
	 * @param locator is a web element used to locate the particular element in the page.
	 * @return locatorFlag returns boolean value whether the element is displayed or not.
	 */
	public boolean isElementDisplayed(WebElement locator) {
		boolean locatorFlag = false;
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			waitTime(1);
			if (locator.isDisplayed() && locator.isEnabled()) {
				locatorFlag = true;
			}
		} catch (NoSuchElementException e) {
			locatorFlag = false;
			return locatorFlag;
		} catch (ElementNotVisibleException e2) {
			locatorFlag = false;
			return locatorFlag;
		}
		return locatorFlag;
	}

	/**
	 * Description : Method to wait for toast message
	 */
	public void waitForToastMessage() {
		try {
			String toast_Path ="Success message#xpath=//div[@class='toast-message' and contains(text(),'Saved Successfully')]";
			if(isElementDisplayed(toast_Path)){
				testStepPassed("Success message is displayed");
			}
			else {
				testStepFailed("Success message not displayed");
			}
		} catch (Exception e) {
			testStepFailed("Failed to display  message");
			writeToLogFile("ERROR", "Failed to display message.ERROR:"+e.getMessage());
		}
	}

	/**
	 * Description : Method to wait till element appears
	 * @param objectLocator is the locator
	 */
	public void waitTillElementAppears(String objectLocator) {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			waitForElementToDisplay(objectLocator, implicitlyWaitTime);
			parseidentifyByAndlocator(objectLocator);
			if (counter <= elementLoadWaitTime && findWebElement(objectLocator).isDisplayed()) {
				testStepPassed("Element appeared in " + counter + " seconds");
			} else {
				testStepFailed(objectLocator.split("#")[0] + " is not loaded completely.");
			}
		} catch (NoSuchElementException ex) {
			writeToLogFile("INFO", "Waiting for element to appear");
			waitTime(1);
			counter++;
			waitTillElementAppears(objectLocator);
		} catch (Exception e) {
			testStepFailed("Failed to wait untill the element appears");
			writeToLogFile("ERROR", "Failed to wait untill the element appraes. ERROR: " + e.getMessage());
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitlyWaitTime, TimeUnit.SECONDS);
			counter = 0;
		}
	}

	/**
	 * Description : Method to wait for angular load
	 */
	public void waitForAngularLoad() {
		String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
		angularLoads(angularReadyScript);
	}

	/**
	 * Description Method to 
	 * @param angularReadyScript is the wait time
	 */
	public void angularLoads(String angularReadyScript) {
		WebDriverWait jsWait = new WebDriverWait(driver, implicitlyWaitTime);
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		try {
			ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
					.executeScript(angularReadyScript).toString());

			boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

			if (!angularReady) {
				jsWait.until(angularLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	/**
	 * Description : Method to wait till element to be clickable 
	 * @param element is given for the tab names
	 * @return status is the value of status
	 */
	public boolean waitTillElemetToBeClickable(WebElement element) {
		boolean status = false;
		try {
			WebDriverWait wait=new WebDriverWait(driver, elementLoadWaitTime);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			status = true;
		} catch (Exception e) {
			writeToLogFile("ERROR", "Element is not able to click within : "+elementLoadWaitTime+" Seconds. ERROR : "+e.getMessage());
			testStepFailed("Element is not able to click within : "+elementLoadWaitTime+" Seconds");
			status = false;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitlyWaitTime, TimeUnit.SECONDS);
		}
		return status;
	}
	
	/**
	 * Description : Method to click ellipsis icon in stock transfer
	 * @param labelName is the label of the inputs
	 */
	public void clickSTWarehouseEllipsisIcon(String labelName) {
		try {
			WebElement ellipsisIcon = findWebElement(
					"Ellipsis Icon#xpath=(//input[@placeholder='" + labelName + "']/../..//button)[2]");
			if (ellipsisIcon.isDisplayed()) {
				ellipsisIcon.click();
				testStepPassed("Ellipsis Icon is clicked aganist  " + labelName);
			} else {
				testStepFailed("Not able to find " + labelName);
			}

		} catch (Exception e) {
			testStepFailed("Not able to find " + labelName);
		}
	}
	
	/**
	 * Description : Method to retrieve value where the text is not visible in dom
	 * @param element is the locator
	 */
	public String getTextValue(String element) {
		String textvalue = "";
		try {
			WebElement textField = findWebElement(element);
			JavascriptExecutor jscript = (JavascriptExecutor) driver;
			textvalue = (String) jscript.executeScript("return arguments[0].value",textField);
		} catch (Exception e) {
			testStepFailed("Unable to find warehouse");
		}
		return textvalue;
	}
	
	/**
	 * Description : Method to get value from table
	 * @param element is the locator
     * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 */
	public String getPositionValueST(String element, int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = element + "//tr[" + rowPosition + "]//td[" + columnPosition + "]";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
					+ rowPosition + "," + columnPosition);
		}
		return table_Value;
	}
	
	/**
	 * Description : Method to get click check box
	 * @param element is the locator
     * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 */
	public String clickPositionCheckBoxValueST(String element, int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = element + "//tr[" + rowPosition + "]//td[" + columnPosition + "]//label";
			if (isElementDisplayed(tablePosition)) {
				JavascriptExecutor jscript = (JavascriptExecutor) driver;
				jscript.executeScript("arguments[0].scrollIntoView(true);", findWebElement(tablePosition));
				table_Value = getText(tablePosition);
				clickOn(tablePosition);
				testStepPassed(table_Value + "is Successfully Clicked");
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
					+ rowPosition + "," + columnPosition);
		}
		return table_Value;
	}
	
	/**
	 * Description : Method to get link value from table
     * @param rowPosition is the position of row
	 * @param columnPosition is the position of column
	 */
	
	public String getTablePositionValue(int rowPosition, int columnPosition) {
		String table_Value = null;
		try {
			String tablePosition = "table values#xpath=//div[@class='ui-grid-canvas']//div[@class='ui-grid-row ng-scope']["
					+ rowPosition + "]//div[@role='row']//div[" + columnPosition + "]//a";
			if (isElementDisplayed(tablePosition)) {
				table_Value = getText(tablePosition);
				
				testStepPassed(table_Value + " is get from the table");
			} else {
				testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
						+ rowPosition + "," + columnPosition);
			}
		} catch (Exception e) {
			testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
					+ rowPosition + "," + columnPosition);
		}
		return table_Value;
	}
	

	
	public void expandMenuItems() {
		try {
			if (isElementPresent(GOR.expandMenu)) {
				clickOn(GOR.expandMenu);
				testStepPassed("Expand menu items is clicked");
			}else {
				testStepInfo("Expand Menu items is already selected");
			}
		}catch (Exception e) {
			testStepFailed("Expand menu is not available");
		}
	}
	
	 /**
	    * Description: method is used to click on expand menu button if collapsed
	    */
	    public void clickOnExpandMenuButtonIfCollapsed1() {
	       
	        try {
	            waitForPageToLoad();
	            String sideMenu = "sideMenu#xpath=//side-bar[@class='page-sidebar sidebar-fixed ng-scope ng-isolate-scope']";
	            String expandMenuBtn = "Expand menu button#xpath=//div[@id='sidebarcollapse']";
	            List<WebElement> tab_labels = findWebElements(sideMenu);
	            if(tab_labels.size() ==0) {
	                clickOn(expandMenuBtn);
	            }
	               
	        }catch(Exception e) {
	            e.printStackTrace();
	            testStepFailed("Exception has been occured while identifying the element.");
	        }
	    }
	    
	    /**
		    * Description: method is used to get the value of the label 
		    */
	    
	    public void inwardFilterMenu(String labelFieldValue, String labelName) {
	    	try {
	    		String label="Label#xpath=//label[text()='"+labelName+"']/..//span[@title='Any Time']";
	    		String labelValue=findWebElement(label).getText().trim();
	    		
	    		if(labelValue==labelFieldValue) {
	    			testStepInfo(labelName+" is selected");
	    		}			
	    				
	    	}catch(Exception e) {
	            e.printStackTrace();
	            testStepFailed("Unable to fetch the text");
	        }
	    }
	    
	    public void inwardFilterPalletValue(String valueOfPallet) {
	    	try {
	    		String totalPallet="Total Pallets#xpath=//div[contains(@ng-dblclick,'Masters')]//div[@class='ui-grid-cell-contents ng-binding ng-scope']";
	    		String palletValue=findWebElement(totalPallet).getText().trim();
	    		if(palletValue==valueOfPallet) {
	    			testStepInfo(valueOfPallet+" is available");
	    		}
	    	}catch(Exception e) {
	            e.printStackTrace();
	            testStepFailed("Unable to fetch the text");
	        }
	    }

	    public void noResultFoundVerification(String labelName) {
	    	try {
	    		String randomClick="Text#xpath=//div[text()='New']";
	    		String field="//input[contains(@class,'ng-empty') and @placeholder='"+labelName+"']";
	    		clickOn(randomClick);
	    		if (isElementPresent(field)) {
					testStepPassed("No results found is displayed");
				}
	    		
	    	}catch(Exception e) {
	            e.printStackTrace();
	            testStepFailed("No result is found");
	        }
	    }
	    
	    public String getStatusValue() {
	    	String newText = null;
			try {
				String text="Text#xpath=//div[@title='IGA - Goods Arrived']";
				WebElement element=findWebElement(text);
				 newText=element.getText();
				testStepInfo(newText+" is the status value");
			}catch (Exception e) {
				testStepFailed("Text is not present ");
			}return newText;
		}
	    
	   public String getTotalPackagesValue(String labelName) {
	    	String newText = null;
			try {
				String text="Text#xpath=//div[contains(@value,'"+labelName+"')]//span";
				WebElement element=findWebElement(text);
				 newText=element.getText();
				testStepInfo(newText+" is the status value present in the Receive Lines");
			}catch (Exception e) {
				testStepFailed("Text is not present ");
			}return newText;
		}
	    
	   public String findtableValue(int rowPosition, int columnPosition) {	        
	        String table_Value = null;
	        try {
	            String tablePosition = "table values#xpath=//div[@class='ui-grid-canvas']//div[@class='ui-grid-row ng-scope']["
	                    + rowPosition + "]//div[@role='row']//div[" + columnPosition + "]";
	           
	            if (isElementDisplayed(tablePosition)) {
	                table_Value = getText(tablePosition);
	                testStepPassed(table_Value + " is required value from the table");
	            } else {
	                testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
	                        + rowPosition + "," + columnPosition);
	            }
	        } catch (Exception e) {
	            testStepFailed("Not able to get the " + table_Value + " at rowPosition and column Position "
	                    + rowPosition + "," + columnPosition);
	        }
	        return table_Value;
	    }
	    
	    public void clickOn_FooterApply() {
			try {
				if (isElementPresent(GOR.filterApply)) {
					clickOn(GOR.filterApply);
					waitTime(3);
				} else {
					highLighterMethod(GOR.applyBtnBar);
					testStepFailed("Not able to find Apply");
				}
			} catch (Exception e) {
				testStepFailed("Not able to find Apply");

			}
		}
	    
	    
	   public void clickDropDownOrg(String labelName,String value) {
			try {
				String dropdown_Icon = "Dropdown Icon#xpath=//*[text()='"+labelName+"']/../../../following::div//select[contains(@ng-change,'"+value+"')]";
				if (isElementPresent(dropdown_Icon)) {
					clickOn(dropdown_Icon);
					testStepPassed("Dropdown icon is clicked Successfully");
				} else {
					testStepFailed("Not able to find Dropdown"+labelName);
				}
			} catch (Exception e) {
				testStepFailed("Not able to find Dropdown");

			}
		}
	   public void selectFromDropdown(String fieldName, String optionValue) {
			String step = String.format("Set dropdown '%s' to '%s'", fieldName, optionValue);
			try {
				String labelXpath = "Label#xpath=//label[contains(text(),'" + fieldName + "')]/..";
				WebElement labelField = findWebElement(labelXpath);
				String textFieldXpath = "DropDown[" + fieldName + "]#xpath=//select";
				WebElement dropDown = findWebElementFrom(labelField, textFieldXpath);
				Select select = new Select(dropDown);
				select.selectByVisibleText(optionValue);
				testStepPassed("[+] PASS :: " + step);

			} catch (Exception e) {
				testStepFailed("[-] FAIL :: " + step);
				writeToLogFile("Error", e.toString());
			}
		}
	    public void scrollPage(String fieldName) {
	    	try {
	    	String labelXpath = "Label#xpath=//*[contains(text(),'" + fieldName + "')]/..";
			WebElement labelField = findWebElement(labelXpath);
			JavascriptExecutor jscript = (JavascriptExecutor) driver;
			jscript.executeScript("arguments[0].scrollIntoView(true);", labelField);
			testStepPassed("Page successfully scrolled to view: "+fieldName);
	    	}catch (Exception e) {
				testStepFailed("Failed to scroll the page");
				writeToLogFile("Error", e.toString());
			}
	    }
	    
	    public String getTextFieldValue(String label) {
			try {
				String text = "TextField #xpath=//label[text()='" + label + "']/parent::div//input";
				if (isElementPresent(text)) {
					WebElement element = findWebElement(text);
					String temp = element.getAttribute("value");
					testStepPassed("captured text :" + temp);
					if (temp.equals("")) {
						return "-";
					} else {
						return temp;
					}
				} else {
					testStepFailed("Failed to locate element");
					highLighterMethod(text);
				}
			} catch (Exception e) {
				testStepFailed("Failed to capture text.");
			}
			return null;
		}
	    
	    public void enterTextIntosearchBox(String labelName,String value) {
	 	   try {
	 		String labelXpath = "Label#xpath=//label[text()='"+labelName+"']//following-sibling::div//input";
	 		WebElement labelField = findWebElement(labelXpath);	
	 		labelField.clear();
	 		labelField.sendKeys(value);
	 		waitForAngularLoad();	
	 		waitTime(3);
	 		labelField.sendKeys(Keys.ENTER);
	 		testStepPassed("[" + value + "] is entered into Textfield [" + labelName + "]");
	 	} catch (Exception e) {
	 		testStepFailed("[-] FAIL :: '" + e.getClass() + "' thrown, please do check log file for more details");
	 		writeToLogFile("ERROR", "[E] :: Exception thrown: " + e);
	 	}
	   }
 	  public void enterDataIntoTextField(String labelName,String tagName,String value) {
	 	   try {
	 		String labelXpath = "Label#xpath=//label[contains(text(),'"+labelName+"')]//following-sibling::div//"+tagName+"";
	 		WebElement labelField = findWebElement(labelXpath);	
	 		labelField.clear();
	 		labelField.sendKeys(value);
	 		waitForAngularLoad();
	 		testStepPassed("[" + value + "] is entered into Textfield [" + labelName + "]");
	 	} catch (Exception e) {
	 		testStepFailed("[-] FAIL :: '" + e.getClass() + "' thrown, please do check log file for more details");
	 		writeToLogFile("ERROR", "[E] :: Exception thrown: " + e);
	 	}
    } 
	    public void verifyBookingCheckBox(String labelName) {    	
			try {
				String labelXpath="label#xpath=//label[contains(text(),'"+labelName+"')]";
				WebElement checkBox=findWebElement(labelXpath);				
				String checkboxName = checkBox.getText();
				if (checkboxName.equals(labelName)) {
				String checkBoxXpath = "checkbox[" + labelName + "]#xpath=//following-sibling::fieldset//input";
				WebElement checkboxField = findWebElementFrom(checkBox, checkBoxXpath);	
				String isChecked = checkboxField.getAttribute("class");
				System.out.println(isChecked);
				if (!isChecked.contains("ng-empty")) {									
					testStepPassed(" CheckBox is selected: "+labelName);								
					}else if (!isChecked.contains("ng-not-empty")) {
						testStepPassed(" CheckBox is Disabled: "+labelName);
					}else {								
					testStepFailed(" CheckBox is Not selected: " + labelName);
					}
				}else{
					testStepFailed("Unable to find: " + labelName);
					}				
				} catch (Exception e) {
				testStepFailed("Exception: " + e.getMessage());
				}	    	
	    	}
	   	public void clickOnLeftMenu(String menuValue ) {	 	    	
	    		try {
				clickOnExpandMenuButtonIfCollapsed();
				waitForAngularLoad();
				String menuField="Menu label#xpath=//li/a[@data-ng-class=\"{'menu-dropdown' : menu.MenuList.length > 0}\"]/span[text()='"+menuValue+"']";
				WebElement menuXpath=findWebElement(menuField);
				if (isElementDisplayed(menuXpath)) {
					menuXpath.click();
					testStepPassed( menuValue + " Tab is clicked successfully.");
					}						
				} catch (Exception e) {
				testStepFailed("Unable to find the " + menuValue + " tab.");
				}
	       }  
	   	public void applyFilter(String element) {
	    		try {
	    			waitTime(10);
	    			WebElement filterButton=findWebElement(element);
	    			filterButton.click();
	    			testStepPassed("\"" + element + "\" Tab is clicked successfully.");
	    		}catch(Exception e) {
	    			testStepFailed("Unable to find the " + element + "tab.");
	    		}
	    	}
	   public void clickOnTableFields(String colValue,String table_Cell) {
    		try {
    		List<WebElement> cols=findWebElements(GOR.master_Table_Header);
    		for(int col=0;col<=cols.size()-1;col++) {
    			String colHeader=cols.get(col).getText();
    			if(colValue.equalsIgnoreCase(colHeader)) {
    				String tableCell="tableCell#xpath=//a[text()='"+table_Cell+"']";
    				WebElement tableCellXpath=findWebElement(tableCell);
    				tableCellXpath.click();
    				testStepPassed("\"" + table_Cell + "\" clicked successfully.");
    			}
    			}
    		}catch(Exception e) {
    			testStepFailed("Unable to click on Table cell " + table_Cell + "tab.");
    			}
    		}
	   public void clickOnOrgLeftMenu(String menuValue) {
		   try {
			   String menuLabel="Organization Address#xpath=//uib-tab-heading[@title='"+menuValue+"']";
			   WebElement menu=findWebElement(menuLabel);
			   menu.click();
			   testStepPassed(menuValue + " clicked successfully.");	    			
	    			
		   }catch(Exception e) {
			   testStepFailed("Unable to click on Table cell " + menuValue + "tab.");
		   }
	   }
	   public String getTableValue(String colValue,String table_Cell) {
		String tableText = null;
   		try {
   		List<WebElement> cols=findWebElements(GOR.master_Table_Header);
   		for(int col=0;col<=cols.size()-1;col++) {
   			String colHeader=cols.get(col).getText();
   			if(colValue.equalsIgnoreCase(colHeader)) {
   				String tableCell="tableCell#xpath=//a[text()='"+table_Cell+"']";
   				WebElement tableCellXpath=findWebElement(tableCell);
   				tableText= tableCellXpath.getText();
   				testStepPassed("\"" + table_Cell + "\" text Captured successfully.");
   			}
   			}
   		}catch(Exception e) {
   			testStepFailed("Unable to capture text on Table cell " + table_Cell + "tab.");
   			}
   		return tableText;
   		}
	   public void navigateTo(String element) {
		   try {
		   driver.navigate().to(element);
		   testStepPassed("User successfully navigated to Url."+element);
		   }catch(Exception e) {
			testStepFailed("Unable to navigate to Url."+element);
	   	 }
	   }
	   public void compareTwoStrings(String str1,String str2) {
		   try {
		   if(str1.contains(str2))
		   testStepPassed(str2 +" value is related to port");
		   }catch(Exception e) {
		   testStepFailed(str2 +" value is not related to port");
		   }
	   }
	   public void validationErrors() {
		 try {
			 List<WebElement> errors= driver.findElements(By.xpath("//div[@class='message ng-binding']"));		 
			 if(errors.size()>0) {
			  for(int i=0;i<=errors.size()-1;i++) {
				  
				  String geterror = errors.get(i).getText();
				  testStepInfo("Info: " +geterror);			 
				  testStepPassed("Validation Messages captured successfully");
			  }
		  }
	   }catch(Exception e) {
		   testStepFailed("No validation errors");
	   }   
	}
	   public void editIcon(String labelValue) {
		   String editIcon="EditIcon#xpath=//span[contains(text(),'"+labelValue+"')]//following-sibling::i[contains(@data-ng-click,'EditCusContentClick')]";
		   try {
		   clickOn(editIcon);
		   testStepPassed("clicked on Edit Icon successfully");
		   }catch(Exception e) {
		   testStepFailed("Unable to click on Edit icon");
		   }
	   }
	   public void clickOnOrganizationCheckBox(String labelName) {  
		try {
			String checkboxLabelXpath="label#xpath=//div[contains(text(),'"+labelName+"')]//parent::div//preceding-sibling::div//span[@class='text']";
			WebElement checkBox=findWebElement(checkboxLabelXpath);	
			String checkboxXpath="label#xpath=//div[contains(text(),'"+labelName+"')]//parent::div//preceding-sibling::div//input";
			String isCheckBox=findWebElement(checkboxXpath).getAttribute("class");			
			if(!isCheckBox.contains("not-empty")) {
				checkBox.click();
				testStepPassed(" CheckBox is not selected and clicked against on: " + labelName);
			
			} else {
				testStepPassed("CheckBox is selected");			
			}
			} catch (Exception e) {
			testStepFailed("Unable to find: " + labelName);
			}
    	}
	   public boolean verifyAddrTypeChekbox(String labelName) {
		   boolean checkBoxStatus=false;
		   try {				
				String checkboxXpath="label#xpath=//div[contains(text(),'"+labelName+"')]//parent::div//preceding-sibling::div//input";
				String isCheckBox=findWebElement(checkboxXpath).getAttribute("class");			
				if(isCheckBox.contains("not-empty")) {
					checkBoxStatus=true;
					testStepPassed(" CheckBox is selected against: " + labelName);
				
				} else {
					testStepPassed("CheckBox is not selected");			
				}
				} catch (Exception e) {
				testStepFailed("Unable to find: " + labelName);
				}
		   return checkBoxStatus;
	   }
	   public void setAsDefaultEllipsis(String menuName,String labelName, boolean isClick) {
			try {
				String btn_ellipsisValue="btn_ellipsis#xpath=//div[contains(@data-ng-if,'Organization"+menuName+"')]//i[contains(@class,'ellipsis')]";
				List<WebElement> btn_ellipsis=findWebElements(btn_ellipsisValue);
				for(int i=0;i<btn_ellipsis.size()-1;i++) {
					if (isElementDisplayed(btn_ellipsis.get(i))) {
						btn_ellipsis.get(i).click();
						if (isClick) {
							List<WebElement> options = findWebElements(GOR.drpDown_ellipsis);
							for (int index = 0; index < options.size(); index++) {
								String drpDownLabel = options.get(index).getText().trim();
								if (labelName.contains(drpDownLabel)) {
									options.get(index).click();
									testStepPassed("\"" + labelName + "\" is clicked Successfully.");
									break;
								} else if (index == (options.size() - 1)) {
									testStepFailed("Unable to find " + labelName);
								}
							}
						} else {
							testStepFailed("Organization Address type buttons are not displayed");
						}
					}
				}
				
			} catch (Exception e) {
				testStepFailed("Organization Address type buttons are not displayed");

			}
		}
	   public void getValuesFromDropDown(String fieldName) {
		   try {
				String labelXpath = "Label#xpath=//label[contains(text(),'" + fieldName + "')]/..";
				WebElement labelField = findWebElement(labelXpath);
				String textFieldXpath = "DropDown[" + fieldName + "]#xpath=//select";
				WebElement dropDownFld = findWebElementFrom(labelField, textFieldXpath);
				Select sel=new Select(dropDownFld);
				List <WebElement> dropDown = sel.getOptions();
		      	for(int label=0;label<=dropDown.size()-1;label++) {
		      		String dropdnValues=dropDown.get(label).getAttribute("label");
		      		testStepInfo("Drop Down values: "+dropdnValues);
				}
				testStepPassed("Captured dropdown values: " + fieldName);

			} catch (Exception e) {
				testStepFailed("Unable to capture dropdown values " + fieldName);
				writeToLogFile("Error", e.toString());
			}
		}
	   public String getSelectedDropDownValue(String fieldName) {
		   String selectedValue=null;
		   try {
			   String labelXpath = "Label#xpath=//label[contains(text(),'" + fieldName + "')]/..";
				WebElement labelField = findWebElement(labelXpath);
				String textFieldXpath = "DropDown[" + fieldName + "]#xpath=//select";
				WebElement dropDownFld = findWebElementFrom(labelField, textFieldXpath);
				Select sel=new Select(dropDownFld);
				WebElement dropdownValue=sel.getFirstSelectedOption();
				selectedValue=dropdownValue.getText();
				testStepInfo("Drop Down values: "+selectedValue);
				testStepPassed("Captured dropdown values: " + fieldName);
		   } catch (Exception e) {
			testStepFailed("Unable to capture dropdown values " + fieldName);
			writeToLogFile("Error", e.toString());
		}
		   return selectedValue;
		   
	   }
	   
	   
	 
	   
	   
	   
	   
	   
	   public void clickOnCrossIcon(String labelValue) {
		  String crossIcon="CrossIcon#xpath=//span[contains(text(),'"+labelValue+"')]//following-sibling::i[contains(@data-ng-click,'FwdOrganizationCtrl.ePage.Masters.OnchangeValues(FwdOrganizationCtrl.obj')]";
		  WebElement icon=findWebElement(crossIcon);
		 try { 
			 if(isElementDisplayed(icon)) {
			 icon.click();
			 testStepPassed("clicked on cross symbol successfully " + labelValue);
		   }
		 } catch (Exception e) {
			testStepFailed("Unable to clicked on cross symbol " + labelValue);
			writeToLogFile("Error", e.toString());
		} 
	}
	 public void clearTextBox(String labelName) {
		 String labelXpath = "Label#xpath=//label[text()='"+labelName+"']//following-sibling::div//input";
	 		WebElement labelField = findWebElement(labelXpath);	
	 		try{
	 			labelField.clear();
	 			 testStepPassed("cleared text Text in text field " + labelName);
	 		}catch (Exception e) {
				testStepFailed("Unable to clear Text in text field " + labelName);
				writeToLogFile("Error", e.toString());
			}
	 }
	 public void clickOnDatePicker(String labelName) {
		 String datePickerLabel="DatePicker label#xpath=//label[contains(text(),'"+labelName+"')]//following-sibling::div//input";
		 WebElement datePickerIcon=findWebElement(datePickerLabel);
		 
		 try { 
			 if(isElementDisplayed(datePickerIcon)) {
			 datePickerIcon.click();
			 testStepPassed("clicked on date picker icon successfully " + labelName);
		   }
		 } catch (Exception e) {
			testStepFailed("Unable to clicked on date picker icon " + labelName);
			writeToLogFile("Error", e.toString());
		} 
	 }
	 public void verifyAvailableFields(String labelValue) {
		 try {
		 		String labelXpath = "Label#xpath=//label[contains(text(),'"+labelValue+"')]//following-sibling::div//input";
		 		WebElement labelField = findWebElement(labelXpath);	
		 		System.out.println(isElementDisplay(labelField));
		 		if(isElementDisplay(labelField)) {
		 		waitForAngularLoad();
		 		testStepFailed(labelValue +" ::field is displayed ");
		 		}
		 	} catch (Exception e) {
		 		testStepPassed(labelValue +" ::field is not displayed");
		 		writeToLogFile("ERROR", "[E] :: Exception thrown: " + e);
		 	}
	 }
	 public void clickOnMenuCollapse(String menuValue ) {	 	    	
 		try {
			clickOnExpandMenuButtonIfCollapsed();
			waitForAngularLoad();
			String menuField="Menu label#xpath=//span[contains(text(),'"+menuValue+"')]//following-sibling::span";
			WebElement menuXpath=findWebElement(menuField);
			if (isElementDisplayed(menuXpath)) {
				menuXpath.click();
				testStepPassed( menuValue + " Tab is clicked successfully.");
				}						
			} catch (Exception e) {
			testStepFailed("Unable to find the " + menuValue + " tab.");
			}
    }
	public boolean isElementDisplayed(String objectLocator, int timeOut) {       
	     driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	     boolean flag = false;
	       try {
	            parseidentifyByAndlocator(objectLocator);
	            flag = driver.findElement(By.xpath(locator)).isDisplayed();
	            return flag;
	        } catch (NoSuchElementException e) {
	            writeToLogFile("ERROR", objectLocator + " is not found.");
	            return flag;
	        } catch (Exception e) {
	            writeToLogFile("ERROR", objectLocator + " is not found.");
	            return flag;
	        }
	    }
	    /**
	    * Description: method is used to click on expand menu button if collapsed
	    */
	    public void clickOnExpandMenuButtonIfCollapsed() {
	       
	        try {
	            waitForPageToLoad();
	            String sideMenu = "sideMenu#xpath=//side-bar[@class='page-sidebar sidebar-fixed ng-scope ng-isolate-scope']";
	            String expandMenuBtn = "Expand menu button#xpath=//div[@id='sidebarcollapse']";           
	            if(!(isElementDisplayed(sideMenu, 5))) {
	                clickOnSpecialElement(expandMenuBtn);
	            }
	               
	        }catch(Exception e) {
	            e.printStackTrace();
	            testStepFailed("Exception has been occured while identifying the element.");
	        }
	    }
	    public String getTextSelectedInAutoTextField(String labelName) {
			String temp = "";
			try {
				String textField = labelName + "#xpath=//label[normalize-space(text())='" + labelName
						+ "']/parent::div//following-sibling::div//input[@aria-autocomplete='list']";
				if (isElementDisplayed(textField, 10)) {
					temp = findWebElement(textField).getAttribute("value");
					testStepPassed(labelName + " field has the value: " + temp);
					return temp;
				} else {
					testStepFailed(labelName + " auto suggestive text field is not dispalyed in 10 seconds.");
					highLighterMethod(textField);
				}
			} catch (Exception e) {
				testStepFailed("Failed to fetch the value selected in auto populated text field");
			}
			return null;
		}
	    /**
		 * Description: Method to handle filter
		 * 
		 * @param label
		 * @param expectedData
		 */
		public void handleFilter(String label, String expectedData) {
			String current_Value = "";
			boolean status = false;
			try {
				if (isElementDisplayed(GOR.filter, 20)) {
					waitTime(2);
					clickOn(GOR.filter);
					String fieldName = label + "#xpath=//label[@title='" + label + "']/following-sibling::div//input";
					String listOfSuggestion = expectedData + "#xpath=//ul[@class='dropdown-menu ng-isolate-scope']//a";
					if (isElementDisplayed(fieldName, 20)) {
						typeIn(fieldName, expectedData);
						List<WebElement> list = findWebElements(listOfSuggestion);
						if (list.size() != 0) {
							for (int iterator = 1; iterator <= list.size(); iterator++) {
								String currentPath = expectedData
										+ "#xpath=(//ul[@class='dropdown-menu ng-isolate-scope']//a)[" + iterator + "]";
								scrollToElement(currentPath);
								current_Value = findWebElement(currentPath).getAttribute("title");
								if ((current_Value.toUpperCase()).contains(expectedData.toUpperCase())) {
									clickOn(currentPath);
									status = true;
									break;
								} else {
									continue;
								}
							}
							if (status)
								testStepPassed(expectedData + " " + label + " is clicked.");
							else
								testStepFailed(expectedData + " " + label + " is not clicked.");
						} else
							testStepPassed("No Record Found");
					} else
						testStepFailed(label + " is not dispalyed in 10 seconds.");
				} else
					testStepFailed("Filter button is not dispalyed in 10 seconds.");
			} catch (Exception e) {
				writeToLogFile("ERROR", "Failed to handle filter. Error: " + e.getMessage());
				testStepFailed("Failed to handle filter.");
			}
		}
		public void clickOnOrganizationSideTabs(String label) {
			try {
				String sideTab = "Side tabs #xpath=//span[contains(text(),'" + label + "') and @class='ng-binding']";
				waitForElement(sideTab, 10);
				if (isElementDisplayed(sideTab, 5)) {
					clickOn(sideTab);
				} else {
					testStepFailed("Failed to locate element");
					highLighterMethod(sideTab);
				}
			} catch (Exception e) {
				testStepFailed("Failed to click on tab.");
			}
		}
		public String captureDetails(String label,String detail) {
			try {
				switch (detail) {
				case "Organization code":
					String organizationCode = "Organization Code #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[1]//span/b";
					if (isElementPresent(organizationCode)) {
						WebElement organization = findWebElement(organizationCode);
						String orgName = organization.getText();
						testStepPassed("Organization name: " + orgName);
						return orgName;
					} else {
						testStepFailed("Organization name cannot be captured.");
					}
					break;

				case "Address":
					String orgAddress1 = "Address line #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[1]//p";
					if (isElementPresent(orgAddress1)) {
						WebElement organizationAddress1 = findWebElement(orgAddress1);
						String orgAddrss1 = organizationAddress1.getText();												
						testStepPassed("Organization address line:" + orgAddrss1);
						return orgAddrss1;
					} else {
						testStepFailed("Organization address1 cannot be captured.");
					}
					break;				

				case "Organization Full Name":
					String orgName = "Organization Full Name #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[1]//div/b";
					if (isElementPresent(orgName)) {
						WebElement organizationName = findWebElement(orgName);
						String organizationFullName = organizationName.getText();
						testStepPassed("Organization Full Name:" + organizationFullName);
						return organizationFullName;
					} else {
						testStepFailed("organization Full Name cannot be captured.");
					}
					break;
				case "Contact Person":
					String contactPerson = "contactPersonName #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[2]//div/b";
					if (isElementPresent(contactPerson)) {
						WebElement contactPersonName = findWebElement(contactPerson);
						String contactPrsnName = contactPersonName.getText();
						testStepPassed("contact Person Name:" + contactPrsnName);
						return contactPrsnName;
					} else {
						testStepFailed("contact Person Name cannot be captured.");
					}
					break;

				case "Email":
					String mail = "mail #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[2]//div[2]";
					if (isElementPresent(mail)) {
						WebElement mailId = findWebElement(mail);
						String email = mailId.getText();
						testStepPassed("Mail:" + email);
						return email;
					} else {
						testStepFailed("Mail id cannot be captured.");
					}
					break;

				case "Phone":
					String phoneNumber = "PhoneNumber #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[2]//div[3]";
					if (isElementPresent(phoneNumber)) {
						WebElement phoneNum = findWebElement(phoneNumber);
						String phone = phoneNum.getText();
						testStepPassed("Phone:" + phone);
						return phone;
					} else {
						testStepFailed("Phone number cannot be captured.");
					}
					break;
				}
			} catch (Exception e) {
				testStepFailed("Failed to capture details");
			}
			return null;
		}
		/**
		 * Description: method to click on edit button
		 * 
		 * @paramlabelName
		 */
		public void ClicOnEdit(String labelName) {
			int iterator = 1;
			boolean status = false;
			try {
				String editfield = labelName + "#xpath=//label[text()='" + labelName
						+ "']/../following-sibling::div/child::div/div/div/div/label/input/following-sibling::span[text()='Edit']";
				List<WebElement> edit_Elements = findWebElements(editfield);
				for (WebElement element : edit_Elements) {
					String edit = labelName + "#xpath=(//label[text()='" + labelName
							+ "']/../following-sibling::div/child::div/div/div/div/label/input/following-sibling::span[text()='Edit'])["
							+ iterator + "]";
					if (isElementDisplayed(edit, 5) && waitTillElemetToBeClickable(element)) {
						scrollToElement(element);
						element.click();
						status = true;
						break;
					} else {
						iterator++;
					}

				}
				if (!status) {
					testStepFailed("" + labelName + " edit button is not present");
				}
			} catch (Exception e) {
				testStepFailed("Failed to verify contact  " + labelName + ".");

			}
		}
		/**
		 * Description: Method to change details of consignor, consignee and notify
		 * party
		 * 
		 * @author
		 * @param label
		 * @param value
		 * @param field
		 */
		public void changeDetailsinaddressfeild(String field, String value) {
			try {
				String textField = field + " #xpath=//label[contains(text(),'"+field+"')]//following-sibling::input";
				if (isElementPresent(textField)) {
					findWebElement(textField).clear();
					typeIn(textField, value);
				} else {
					testStepInfo("Failed to change " + field);
				}
			} catch (Exception e) {
				testStepFailed("Failed to change details");
			}
		}
		/**
		 * Description: Method to save details of consignor, consignee and notify party
		 * 
		 * @param label
		 */
		public void saveDetails(String label) {
			try {
				String saveDetails = "Save button #xpath=//label[contains(text(),'" + label
						+ "')]/parent::div/following-sibling::div//button[contains(text(),'Save')]";
				if (isElementPresent(saveDetails)) {
					clickOn(saveDetails);
				} else {
					testStepInfo("Failed to locate save button");
					highLighterMethod(GOR.pagebody);
				}
			} catch (Exception e) {
				testStepFailed("Failed to save the details");
			}
		}
		/**
		 * Description: Method to verify whether the details are changed
		 * 
		 *
		 * @param label
		 * @param label
		 * @param label
		 */
		public void isDetailsChanged(String label, String address1, String address2, String name, String mail,
				String phone) {
			try {			
				String orgAddress = "Address line #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[1]//p";		
				String contactPerson = "contactPersonName #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[2]//div/b";
				String email = "mail #xpath=//span[contains(text(),'"+label+"')]/..//following-sibling::div/div[2]//div[2]";
				String phoneNumber = "PhoneNumber #xpath=//span[contains(text(),'" + label+"')]/..//following-sibling::div/div[2]//div[3]";			
				String organizationAddress = findWebElement(orgAddress).getText();				
				String[] orgAddr=organizationAddress.split(",");
				// organization address 1
				if (isElementPresent(orgAddress)) {								
					if (orgAddr[0].equalsIgnoreCase(address1)) {
						testStepInfo("Address 1 is changed");
					} else {
						testStepInfo("Address 1 is not changed");
					}
				} else {
					testStepInfo("Organization address1 cannot be captured.");
				}

				// organization address 2
				if (isElementPresent(orgAddress)) {					
					if (orgAddr[1].equalsIgnoreCase(address2)) {
						testStepInfo("Address 2 is changed");
					} else {
						testStepInfo("Address 2 is not changed");
					}
				} else {
					testStepInfo("Organization address2 cannot be captured.");
				}

				// contact person
				if (isElementPresent(contactPerson)) {
					WebElement contactPersonName = findWebElement(contactPerson);
					if (contactPersonName.getText().equalsIgnoreCase(name)) {
						testStepInfo("Contact person name is changed");
					} else {
						testStepInfo("Contact person name is not changed");
					}
				} else {
					testStepInfo("Contact Person Name cannot be captured.");
				}
				// mail and phone
				if (isElementPresent(email)) {
					WebElement mailID = findWebElement(email);
					if (mailID.getText().equalsIgnoreCase(mail)) {
						testStepInfo("Mail Id is changed");
					} else {
						testStepInfo("Mail Id are not changed");
					}
				} else {
					testStepInfo("Mail id cannot be captured.");
				}
				if (isElementPresent(phoneNumber)) {
					WebElement phoneNum = findWebElement(phoneNumber);
					if (phoneNum.getText().equalsIgnoreCase(mail)) {
						testStepInfo("phone is changed");
					} else {
						testStepInfo("phone are not changed");
					}
				} else {
					testStepInfo("phone number cannot be captured.");
				}
			} catch (Exception e) {
				testStepFailed("Failed to capture details");
			}
		}
		public void VerifyContactfield(String labelName, String expectedname) {
			try {
				String contactfield = labelName + "#xpath=//span[contains(text(),'"+labelName+"')]/..//following-sibling::div/div[2]//div/b";
				WebElement contact = findWebElement(contactfield);
				if (contact.isDisplayed()) {
					String actualcontact = contact.getText();
					if (expectedname.contains(actualcontact)) {
						testStepPassed("" + actualcontact + "  contact is displayed");
					} else {
						testStepFailed("" + actualcontact + " contact is not present");
					}
				}

			} catch (Exception e) {
				testStepFailed("Failed to verify contact  " + labelName + ".");

			}
		}
			public void attachOrder(int num) {
				String attachOrder="Attach Order#xpath=//a/i[contains(@ng-class,'fa-star')]";
				try {				
					if(isElementPresent(attachOrder,10)) {
						testStepPassed("Orders displayed and clicking on checkbox");
						for(int i=1;i<=num;i++) {
							String clickOrder="click order#xpath=//div[@class='ui-grid-canvas']/div["+i+"]/div[1]/div[1]/div[1]/div[1]";
							isElementDisplayed(clickOrder);
							waitForAngularLoad();
							clickOn(clickOrder);
							}
						clickOnButton(ALR.attachLabel);
						testStepPassed("Orders attached successfully");
					}else if(isElementPresent(GOR.noRecord,10)) {
						waitForAngularLoad();
						String noRecords=findWebElement(GOR.noRecord).getText();
						testStepPassed(noRecords);
						clickOn(GOR.close);
					}
						
					
				}catch (Exception e) {
					testStepFailed("Orders are not displayed");
		
				}
			}
			
			
			
		public void	captureTableHeaderValues(String[] tableHeaderValue){
			String tableHeader="Table Header#xpath=//th";
			List<WebElement> tableHeaderList=findWebElements(tableHeader);
			for(int i=0;i<=tableHeaderValue.length-1;i++) {
				for(int j=0;j<=tableHeaderList.size()-1;j++) {
					if(tableHeaderValue[i].equalsIgnoreCase(tableHeaderList.get(j).getText())){
						String headerId=tableHeaderList.get(j).getAttribute("id");
						String tableCellValue="Table cell value#xpath=//td[@id='"+headerId+"']/div";
						if(isElementPresent(tableCellValue)) {
							String cellValue=findWebElement(tableCellValue).getText();
							testStepPassed("Captured custom field "+tableHeaderValue[i]+" value: "+cellValue);
						}
						break;
						}
					
					}
				}
				
			}
	
		
		public void	captureTablerouHeaderValues(String placeholdervalue , String tableHeaderValue){
			String tableCellValue="Table cell value#xpath=//input[@type='text'][@placeholder='"+placeholdervalue+"']";
			if(isElementPresent(tableCellValue)) {
				String cellValue=findWebElement(tableCellValue).getText();
				testStepPassed("Captured custom field "+tableHeaderValue+" value: "+cellValue);
			}
		}
		
		
		
		public void	captureTableroutingHeaderValues(String[] tableHeaderValue){
			String tableHeader="Table Header#xpath=//td";
			List<WebElement> tableHeaderList=findWebElements(tableHeader);
			for(int i=0;i<=tableHeaderValue.length-1;i++) {
				for(int j=0;j<=tableHeaderList.size()-1;j++) {
					if(tableHeaderValue[i].equalsIgnoreCase(tableHeaderList.get(j).getText())){
						String headerId=tableHeaderList.get(j).getAttribute("id");
						String tableCellValue="Table cell value#xpath=//td[@id='"+headerId+"']/div";
						if(isElementPresent(tableCellValue)) {
							String cellValue=findWebElement(tableCellValue).getText();
							testStepPassed("Captured custom field "+tableHeaderValue[i]+" value: "+cellValue);
						}
						break;
						}
					
					}
				}
				
			}
		
		
		
		public void	captureTableCustomFields(String[] tableHeaderValue){
			String tableHeader="Table Header#xpath=//th";
			List<WebElement> tableHeaderList=findWebElements(tableHeader);			
			for(int i=0;i<=tableHeaderValue.length-1;i++) {
				for(int j=0;j<=tableHeaderList.size()-1;j++) {	
					if(tableHeaderValue[i].equalsIgnoreCase(tableHeaderList.get(j).getText())){						
						testStepPassed("Captured custom fields: "+tableHeaderList.get(j).getText());
						break;
						}
					
						}
					}
				}
		
		
		public void updateCustomFields(String tableHeaderValue,String tagName,String value){
			String tableHeader="Table Header#xpath=//th";
			List<WebElement> tableHeaderList=findWebElements(tableHeader);
			for(int i=0;i<=tableHeaderList.size()-1;i++) {				
					if(tableHeaderValue.equalsIgnoreCase(tableHeaderList.get(i).getText())){
						String headerId=tableHeaderList.get(i).getAttribute("id");
						switch(tagName) {
						case "input":
							String inputField="Table cell value#xpath=//td[@id='"+headerId+"']//"+tagName+"";
							findWebElement(inputField).clear();
							findWebElement(inputField).sendKeys(value);
							testStepInfo("Entered "+value+" into custom field "+tableHeaderValue);
							break;
						case "select":
							String selDropDown="Table cell value#xpath=//td[@id='"+headerId+"']//"+tagName+"";							
							Select sel=new Select(findWebElement(selDropDown));
							sel.selectByVisibleText(value);
							testStepInfo("Selected "+value+" into custom field "+tableHeaderValue);		
							break;
						}
							testStepPassed("Captured custom field "+tableHeaderValue+" value: ");
						}
						}
					
					}
		
		
		public void enterDataIntogridTextField(String labelName,String tagName,String value) {
		 	   try {
		 		String labelXpath = "Label#xpath=//td[@id='"+labelName+"']//"+tagName+"";
		 		WebElement labelField = findWebElement(labelXpath);	
		 		labelField.clear();
		 		labelField.sendKeys(value);
		 		waitForAngularLoad();
		 		testStepPassed("[" + value + "] is entered into Textfield [" + labelName + "]");
		 	} catch (Exception e) {
		 		testStepFailed("[-] FAIL :: '" + e.getClass() + "' thrown, please do check log file for more details");
		 		writeToLogFile("ERROR", "[E] :: Exception thrown: " + e);
		 	}
	    } 
		
		
		 public void clickOnConsolLeftMenu(String menuValue) {
			   try {
				   String menuLabel="Consol Address#xpath=//uib-tab-heading[@title='"+menuValue+"']";
				   WebElement menu=findWebElement(menuLabel);
				   menu.click();
				   testStepPassed(menuValue + " clicked successfully.");	    			
		    			
			   }catch(Exception e) {
				   testStepFailed("Unable to click on Table cell " + menuValue + "tab.");
			   }
		   }
		
		 
		 /**
			 * Description : Method to check the status
			 * @param element is the name of the fields
			 */
			public void gridstatusCheck(String element) {
				WebElement status = findWebElement(element);
				if (status.isEnabled()) {
					testStepPassed("Status is enabled");

				} else {
					testStepInfo("Status is disabled");
				}
			}

			
			/**
			 * Description : Method to click on Search the ellipsis button
			 * Module      : consol 
			 */
			public void SearchelipsisButton(String Labelname) {
				try {
					
					String SearchElipsis = "Consol Elipsis#xpath=//label[contains(text(),'"+Labelname+"')]//following::button";

					if (isElementPresent(SearchElipsis)) {
						clickOn(SearchElipsis);
						testStepPassed("Search Elipsis is click successfully.");
					} else {
						testStepFailed("Search Elipsis button is not found to click");
					}
				} catch (Exception e) {
					testStepFailed("earch Elipsis button is not found to click");
				}
			}
			
			
			
			public void cleargridTextBox(String labelName) {
				 String labelXpath = "Text#xpath=//input[@type='text'][@placeholder='"+labelName+"']";
			 		WebElement labelField = findWebElement(labelXpath);	
			 		try{
			 			labelField.clear();
			 			 testStepPassed("cleared text Text in text field " + labelName);
			 		}catch (Exception e) {
						testStepFailed("Unable to clear Text in text field " + labelName);
						writeToLogFile("Error", e.toString());
					}
			}

			
			public void validationErrors(String lablename) {

				try {
					WebElement Errors= driver.findElement(By.xpath("//div[@class='meta-object ng-binding']"
						                              	+ "/preceding-sibling::div[contains(text(),'"+lablename+"')]"));		 
					String getError = Errors.getText();
					testStepInfo("Info: " +getError);			 
					testStepPassed("Validation Messages captured successfully");

				}catch (Exception e) {
					testStepFailed("Unable to find the " + lablename + ".");
				}
				
			}
			
			
			  public void getValuesgridDropDownValue(String fieldName) {
				
				   try {
					   String labelXpath = "Label#xpath=//td[@id='" + fieldName + "']";
					   WebElement labelField = findWebElement(labelXpath);
						String textFieldXpath = "DropDown[" + fieldName + "]#xpath=//select";
						WebElement dropDownFld = findWebElementFrom(labelField, textFieldXpath);
						Select sel=new Select(dropDownFld);
						List <WebElement> dropDown = sel.getOptions();
				      	for(int label=0;label<=dropDown.size()-1;label++) {
				      		String dropdnValues=dropDown.get(label).getAttribute("label");
				      		testStepInfo("Drop Down values: "+dropdnValues);
						}
						testStepPassed("Captured dropdown values: " + fieldName);

					} catch (Exception e) {
						testStepFailed("Unable to capture dropdown values " + fieldName);
						writeToLogFile("Error", e.toString());
					}
				}
			  
			  
			  public void enterTextIntoconsigner(String labelName,String value) {
			 	   try {
			 		String labelXpath = "consignor clicked#xpath=//body/div[@id='body']/div[2]/div[1]/div[1]/div[1]/div[1]/ui-view[1]/div[1]/div[1]/div[2]/div[1]/fwd-order-menu[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]";
			 		WebElement labelField = findWebElement(labelXpath);	
			 		labelField.clear();
			 		labelField.sendKeys(value);
			 		waitForAngularLoad();	
			 		waitTime(3);
			 		labelField.sendKeys(Keys.ENTER);
			 		testStepPassed("[" + value + "] is entered into Textfield [" + labelName + "]");
			 	} catch (Exception e) {
			 		testStepFailed("[-] FAIL :: '" + e.getClass() + "' thrown, please do check log file for more details");
			 		writeToLogFile("ERROR", "[E] :: Exception thrown: " + e);
			 	}
			   }

			   
}
