package com.website


class WebsiteContentController {

    PageContentService pageContentService

    static allowedMethods = [getPageContent: 'GET']

    def index() {
        render "jestes w kontrolerze WebsiteContent"

    }

    def getPageContent(RequestCO requestCO) {
        if (requestCO.validate()) {
            render pageContentService.getPageContent(requestCO.url)
            return
        }
        response.sendError(400)
    }
}
