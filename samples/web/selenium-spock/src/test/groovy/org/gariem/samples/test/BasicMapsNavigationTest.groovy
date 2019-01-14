package org.gariem.samples.test

import org.gariem.samples.page.MapsHomePage
import org.gariem.samples.page.UniqueSearchResultPage
import org.gariem.samples.setup.BaseTest
import spock.lang.Title

@Title("Basic map navigation")
class BasicMapsNavigationTest extends BaseTest {


    def 'Menu navigation to settings screen'() {
        given: 'I navigate to the google maps site'
        MapsHomePage mapsHome = new MapsHomePage(driver)
        UniqueSearchResultPage resultPage = new UniqueSearchResultPage(driver)

        driver.navigate().to("http://www.google.com/maps")

        when: 'Search for Accenture'
        mapsHome.enterSearchText("Accenture Perú")
        mapsHome.clickSearchButton()

        def placeTitle = resultPage.getPlaceTitle()
        def placeAddress = resultPage.getPlaceAddress()

        then: 'Unique place is found for Accenture Perú'
        placeTitle == 'Accenture Perú'
        placeAddress == 'Av. Rivera Navarrete 475, San Isidro 15046, Peru'
    }


}
