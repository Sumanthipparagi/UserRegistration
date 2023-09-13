package userregistration
import UserRegistration.GenreMaster
import grails.converters.JSON
import org.grails.web.json.JSONObject

class GenreMasterController {

    GenreMasterService genreMasterService

    def save() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
        def results = genreMasterService.save(jsonObject)
        def responseData = [
                "results": results
        ]
        render responseData as JSON
    }

    def getAll() {

        def results = GenreMaster.findAll()
        def responseData = [
                "results": results
        ]
        render responseData as JSON
    }

    def delete() {

        JSONObject jsonObject = new JSONObject(params)

        long id = Long.parseLong(jsonObject.get("id").toString())

        GenreMaster genreMaster = GenreMaster.findById(id)
        if (genreMaster) {
            genreMaster.delete(flush: true)

            if (!genreMaster.hasErrors()) {
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

    def update() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject

        long id = Long.parseLong(jsonObject.get("id").toString())


        def updateResult = genreMasterService.update(jsonObject, id)

        def responseData = [
                result: updateResult,
                status: 'success'
        ]

        render responseData as JSON

    }

    def index() { }
}