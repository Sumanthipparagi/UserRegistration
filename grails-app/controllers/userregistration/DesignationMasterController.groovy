package userregistration

import UserRegistration.DesignationMaster
import grails.converters.JSON
import org.grails.web.json.JSONObject

class DesignationMasterController {

    DesignationMasterService designationMasterService

    def index() {
        render(view:"/designationMaster/designationmaster")
    }

    def save() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
        def results = designationMasterService.save(jsonObject)
        def responseData = [
                "results": results
        ]
        render responseData as JSON
    }

    def getAll() {

        def results = DesignationMaster.findAll()
        def responseData = [
                "results": results
        ]
        render responseData as JSON
    }

    def delete() {

        JSONObject jsonObject = new JSONObject(params)

        long id = Long.parseLong(jsonObject.get("id").toString())

        DesignationMaster designationMaster = DesignationMaster.findById(id)
        if (designationMaster) {
            designationMaster.delete(flush: true)

            if (!designationMaster.hasErrors()) {
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


        def updateResult = designationMasterService.update(jsonObject, id)

        def responseData = [
                result: updateResult,
                status: 'success'
        ]

        render responseData as JSON

    }


}
