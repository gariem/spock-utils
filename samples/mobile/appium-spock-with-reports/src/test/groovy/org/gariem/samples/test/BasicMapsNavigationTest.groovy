package org.gariem.samples.test

import org.gariem.samples.page.MapsHome
import org.gariem.samples.page.Settings
import org.gariem.samples.page.SideMenu
import org.gariem.samples.setup.BaseTest
import org.gariem.utils.spock.datapipe.DataPipeProvider
import org.gariem.utils.spock.reports.listener.reportmanager.ReportTestManager
import spock.lang.Shared
import spock.lang.Title

import java.text.SimpleDateFormat

@Title("Basic map navigation")
class BasicMapsNavigationTest extends BaseTest {

    @Shared
    DataPipeProvider provider

    static final FULL_REPORT_TAG = "<full_report_data>"
    static final DATE_TAG = "<date_info>"
    static final ENVIRONMENT_TAG = "<environment>"

    def setupSpec() {
        def inputFile = getClass().getResource("/input_files/input_data.xlsx").getFile()
        provider = new DataPipeProvider(inputFile, "MyDataSheet")
    }

    def 'Menu navigation to settings screen'() {
        given: 'I launch google maps'
        MapsHome mapsHome = new MapsHome(driver)
        SideMenu sideMenu = new SideMenu(driver)
        Settings settings = new Settings(driver)
        ReportTestManager.registerDriver(driver)

        ReportTestManager.addText(DATE_TAG,  new SimpleDateFormat("dd/MM/yyyy").format(new Date()))
        ReportTestManager.addText(ENVIRONMENT_TAG, "Calidad Perú")

        when: 'I go to the settings menu and return'
        mapsHome.clickOnMenuButton()
        ReportTestManager.addStep(FULL_REPORT_TAG, "After click on menu button")
        sideMenu.clickOnSettingsOption()
        ReportTestManager.addStep(FULL_REPORT_TAG, "After click on settings button")
        settings.clickOnReturnButton()

        then: 'No verification is performed'
        true
    }

    def 'Maps navigation by search'() {
        given: 'I launch google maps'
        MapsHome mapsHome = new MapsHome(driver)
        ReportTestManager.registerDriver(driver)

        when: 'I search for a place and click on the first result'
        mapsHome.searchByText(place)
        ReportTestManager.addStep(FULL_REPORT_TAG, "Search for place")
        mapsHome.tapOnFirstResult()
        def actualPlaceName = mapsHome.getPlaceName()
        ReportTestManager.addStep(FULL_REPORT_TAG, "Select first result")

        then: 'The place found is expected'
        actualPlaceName == expectedPlaceName

        where:
        [place, expectedPlaceName] << provider.getDataIterator()
    }

}
