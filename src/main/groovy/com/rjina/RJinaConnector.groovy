package com.rjina

import org.springframework.http.HttpStatus

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


class RJinaConnector {

    private final String R_JINA_URL = "https://r.jina.ai/"

    String getContent(final String url) {
        HttpClient client = HttpClient.newHttpClient()

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(R_JINA_URL + url))
                .header("Accept", "text/plain")
                .GET()
                .build()

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() == HttpStatus.OK.value()) {
            return response.body()
        } else {
            throw new RuntimeException("Error occurred! HTTP code: ${response.statusCode()}")
        }
    }
}