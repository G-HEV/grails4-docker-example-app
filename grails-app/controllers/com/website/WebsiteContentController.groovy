package com.website


class WebsiteContentController {

    PageContentService pageContentService;

    def index() {
        render "jestes w kontrolerze WebsiteContent"

    }

    def getPageContent(RequestCO requestCO) {
        if(requestCO.validate()){
            /*todo przekazać odpowiednie dane*/
            render pageContentService.getPageContent()
            return
        }
        response.sendError(400);
    }
}
