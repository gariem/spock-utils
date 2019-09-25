package org.gariem.samples.test

import groovyx.net.http.RESTClient
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class ExampleAPITest extends Specification{
    RESTClient restClient = new RESTClient("https://jsonplaceholder.typicode.com", JSON)
    def 'post posts'(){

        given:
        def requestBody = [

                title: "foo",
                body: "prg",
                userId: 11
        ]

        when:


        def response = restClient.get(
                path: '/posts',
                requestContentType: JSON
        )

        def response1 = restClient.post(
                path: '/posts',
                body: requestBody,
                requestContentType: JSON)

        def response2 = restClient.get(
                path: '/posts',
                requestContentType: JSON
        )
        then:
        response.status == 200
        response.responseData.body != "prg"
        response.responseData.size()==100
        response1.status == 201
        response1.responseData.body == "prg"
        response2.status == 200
        response2.responseData.size()==100

    }
}
