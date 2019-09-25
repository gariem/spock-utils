package org.gariem.samples.test

import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Title

import static  groovyx.net.http.ContentType.JSON


@Title("Basic map navigation")
class BasicApiTest extends Specification {

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

    def 'post volunteer32'(){

        given:
        def requestBody = [psId: "456756",
                           name: "Alejandro",
                           email: "Alejandro@prueba.com",
                           birthDate: "2020-05-25T04:07:37+00:00",
                           phone: "+51958451258",
                           gender: "M",
                           district: "PL",
                           occupation: "Developer"]

        when:

        def response1 = restClient.get(
                path: '/volunteers',
                requestContentType: JSON
        )

        def response2 = restClient.post(
                path: '/volunteers',
                body: requestBody,
                requestContentType: JSON)

        def response3 = restClient.get(
                path: '/volunteers',
                requestContentType: JSON
        )

        then:
        response1.status == 200
        response1.responseData.size()==3
        response2.status == 200
        response2.responseData.name == "Alejandro"
        response2.status == 200
        response3.responseData.size()==4

    }
}
