import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.click(findTestObject('Guest/Homepage/prodcuct_card'))

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/txt_product_title'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/product_name'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/product_img'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/detailed_product_price'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/product_desription'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/product_rating'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/product_quanntity'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/btn_increase_product_quantity'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/btn_decrease_product_quantity'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/btn_LoginToAdd'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/btn_LoginToBuy'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/CustomerReviews'), 0)

WebUI.verifyElementPresent(findTestObject('Guest/Homepage/div_Related_Products'), 0)

WebUI.closeBrowser()

