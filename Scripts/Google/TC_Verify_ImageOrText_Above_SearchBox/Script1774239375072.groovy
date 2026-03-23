import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String url = 'https://www.google.com/'
int timeout = 15

TestObject byXpath(String xpath) {
    TestObject to = new TestObject()
    to.addProperty('xpath', ConditionType.EQUALS, xpath)
    return to
}

try {
    WebUI.openBrowser('')
    WebUI.maximizeWindow()
    WebUI.navigateToUrl(url)

    // 1) Wait for the search box
    TestObject searchBox = byXpath("//textarea[@name='q'] | //input[@name='q']")
    WebUI.verifyElementPresent(searchBox, timeout)
    WebUI.verifyElementVisible(searchBo)

    // 2) Verify there is either an image OR non-empty text above the search box (layout-wise)
    // Use the nearest preceding element that is an <img> or has non-empty visible text.
    TestObject aboveSearch_hasImageOrText = byXpath('''
        (
          //textarea[@name='q'] | //input[@name='q']
        )
        /preceding::*[
            (self::img)
            or
            (not(self::script) and not(self::style) and normalize-space(.) != '')
        ][1]
    ''')

    boolean found = WebUI.verifyElementPresent(aboveSearch_hasImageOrText, timeout, FailureHandling.OPTIONAL)
    WebUI.verifyEqual(found, true)

    KeywordUtil.logInfo('Verified: there is an image or non-empty text above the search box.')

} finally {
    WebUI.closeBrowser()
}
