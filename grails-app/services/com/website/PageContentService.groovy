package com.website

import com.rjina.RJinaConnector

class PageContentService {

    RJinaConnector rjinaConnector

    String getPageContent(String url) {
       def result = rjinaConnector.getContent(url)

        return result
    }
}
