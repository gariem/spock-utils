package org.gariem.samples.test

import org.gariem.samples.page.MapsHome
import org.gariem.samples.setup.BaseTest
import spock.lang.Title

@Title("Basic map navigation")
class BasicMapsNavigationTest extends BaseTest {


    def 'Menu navigation to settings screen'() {
        given: 'I launch google maps'
        MapsHome mapsHome = new MapsHome(driver)

        when: 'I go to the settings menu and return'
        driver.navigate().to("http://www.google.com/maps")

        then: 'No verification is performed'
        true
    }


}
