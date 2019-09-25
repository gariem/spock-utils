package org.gariem.samples.test

import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Title

import static  groovyx.net.http.ContentType.JSON


@Title("Basic map navigation")
class BasicMapsNavigationTest extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080", JSON)

    def 'Check volunteers controller'() {
        //restClient.auth.basic 'blaze', 'Q1w2e3r4'

        when: 'get all volunteers'
        def response = restClient.get(
                path: '/volunteers',
                requestContentType: JSON
        )

        then: 'Status is 200'
        response.status == 200
        response.responseData[0].name == "Sara"
        response.responseData.size()==2
    }

    def 'post volunteer'(){

        given:
        def requestBody = [psId: "887745",
                           name: "Roberto",
                           email: "Roberto@prueba.com",
                           birthDate: "2020-10-25T04:07:37+00:00",
                           phone: "+51955179518",
                           gender: "I",
                           district: "LM",
                           occupation: "Vago"]

        when:

        def response = restClient.post(path: '/volunteers', body: requestBody, requestContentType: JSON)

        then:
        response.status == 200

    }

}
