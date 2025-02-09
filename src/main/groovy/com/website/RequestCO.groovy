package com.website

import grails.validation.Validateable

class RequestCO implements Validateable {

    String url

    static constraints = {
        url nullable: false, blank: false, validator: { val, obj, errors ->
            if (val) {
                String regexUrl = "(https://www\\.|http://www\\.|https://|http://)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?" // basic url regex
                if (!val.matches(regexUrl)) {
                    errors.rejectValue('url', "typeMismatch.java.net.URL", "The URL not valid.")
                }
            }
        }
    }
}
