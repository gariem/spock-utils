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
        given:
        MapsHome mapsHome = new MapsHome(driver)
        SideMenu sideMenu = new SideMenu(driver)
        Settings settings = new Settings(driver)

        when:
        mapsHome.clickOnMenuButton()
        sideMenu.clickOnSettingsOption()
        settings.clickOnReturnButton()

        then:
        true
    }

    def 'Maps navigation by search'() {
        given:
        MapsHome mapsHome = new MapsHome(driver)

        when:
        mapsHome.searchByText(place)
        mapsHome.tapOnFirstResult()
        def actualPlaceName = mapsHome.getPlaceName()

        then:
        actualPlaceName == expectedPlaceName

        where:
        [place, expectedPlaceName] << provider.getDataIterator()
    }

}
