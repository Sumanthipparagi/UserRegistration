package userregistration

import UserRegistration.BookDetails
import grails.converters.JSON
import org.grails.web.json.JSONObject

class BookDetailsController {

    BookDetailsService bookDetailsService

    def index(){
        render(view: "/bookDetails/book")
    }

    def getAll() {

        def results = bookDetailsService.getAll()
        def responseData = [
                "results": results
        ]
        render responseData as JSON
    }

    def save() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
        def results = bookDetailsService.save(jsonObject)
        def responseData = [
                "results": results,
                "status": "success"
        ]
        render responseData as JSON
    }

    def getById() {
        JSONObject jsonObject = new JSONObject(params)

        long id = Long.parseLong(jsonObject.get("id").toString())
        def results = BookDetails.findById(id)
        def responseData = [
                "results": results
        ]
        render responseData as JSON

    }

    def update() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject

        long id = Long.parseLong(jsonObject.get("id").toString())


        def updateResult = bookDetailsService.update(jsonObject, id)

        def responseData = [
                result: updateResult,
                status: 'success'
        ]

        render responseData as JSON

    }

    def delete() {

        JSONObject jsonObject = new JSONObject(params)

        long id = Long.parseLong(jsonObject.get("id").toString())

        BookDetails bookDetails = BookDetails.findById(id)
        if (bookDetails) {
            bookDetails.delete(flush: true)

            if (!bookDetails.hasErrors()) {
                def responseData = [
                        'results': "success"
                ]
                render responseData as JSON
            } else
                throw new Exception()
        } else {
            def responseData = [
                    'results': "Not Found"
            ]
            render responseData as JSON
        }

    }

    def viewTable2() {
        render(view: "/bookDetails/bookTable")
    }
}

