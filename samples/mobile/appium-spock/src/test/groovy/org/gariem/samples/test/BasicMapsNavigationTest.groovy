package org.gariem.samples.test

import org.gariem.samples.page.MapsHome
import org.gariem.samples.page.Settings
import org.gariem.samples.page.SideMenu
import org.gariem.samples.setup.BaseTest
import org.gariem.utils.spock.datapipe.DataPipeProvider
import spock.lang.Shared
import spock.lang.Title

@Title("Basic map navigation")
class BasicMapsNavigationTest extends BaseTest {

    @Shared
    DataPipeProvider provider

    def setupSpec() {
        def inputFile = getClass().getResource("/input_files/input_data.xlsx").getFile()
        provider = new DataPipeProvider(inputFile, "MyDataSheet")
    }

    def 'Menu navigation to settings screen'() {
        given: 'I launch google maps'

        MapsHome mapsHome = new MapsHome(driver)
        SideMenu sideMenu = new SideMenu(driver)
        Settings settings = new Settings(driver)

        when: 'I go to the settings menu and return'
        mapsHome.clickOnMenuButton()
        sideMenu.clickOnSettingsOption()
        settings.clickOnReturnButton()

        then: 'No verification is performed'
        true
    }

    def 'Maps navigation by search'() {
        given: 'I launch google maps'
        MapsHome mapsHome = new MapsHome(driver)

        when: 'I search for a place and click on the first result'
        mapsHome.searchByText(place)
        mapsHome.tapOnFirstResult()
        def actualPlaceName = mapsHome.getPlaceName()

        then: 'The place found is expected'
        actualPlaceName == expectedPlaceName

        where:
        [place, expectedPlaceName] << provider.getDataIterator()
    }

}
