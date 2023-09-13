package userregistration

import UserRegistration.UserRegistration
import grails.converters.JSON
import org.grails.web.json.JSONObject
import org.mindrot.jbcrypt.BCrypt
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import org.mindrot.jbcrypt.BCrypt

class UserRegistrationController {

    UserRegistrationService userRegistrationService

    def index() {
        render(view: "/userRegistration/index")
    }

    //The below function getAll() will display all the data from UserRegistration db, you can check it using postman

    def getAll() {
        def results = userRegistrationService.getAll()
        def responseData = [
                "results": results
        ]
        render responseData as JSON
    }

    //save() function is used to save the data into the database ie UserRegistration db, you can check it using postman

    def save() {

        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject

            String mobileNo = jsonObject.get("mobileNo").toString()
            String password = jsonObject.get("password").toString()
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())

            jsonObject.put("hashedPassword", hashedPassword)

            def findResult = UserRegistration.findByMobileno(mobileNo);
            if (!findResult) {
                def results = userRegistrationService.save(jsonObject)

                def responseData = [
                        result: results,
                        status: 'success'
                ]
                render responseData as JSON
            } else {
                def responseData = [
                        status: "Mobile Number already exists"
                ]
                render responseData as JSON
            }
        } catch (Exception ex) {

        }

    }

    //getById() function will get or display all the data of particular id which is passed in postman from UserRegistration db, you can check it using postman

    def getById() {
        JSONObject jsonObject = new JSONObject(params)

        long id = Long.parseLong(jsonObject.get("id").toString())
        def results = UserRegistration.findById(id)
        def responseData = [
                "results": results
        ]
        render responseData as JSON

    }

    // update function is used to update a data of particular id from a table, you can check it using postman

    def update() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject

        long id = Long.parseLong(jsonObject.get("id").toString())
        def updateResult = userRegistrationService.update(jsonObject, id)

        def responseData = [
                result: updateResult,
                status: 'success'
        ]

        render responseData as JSON

    }

    // delete() function is used to delete a particular data from the table by referring to an id, before deleting you need to implement the findby() id function, you can check it using postmans

    def delete() {

        JSONObject jsonObject = new JSONObject(params)

        long id = Long.parseLong(jsonObject.get("id").toString())

        UserRegistration userRegister = UserRegistration.findById(id)
        if (userRegister) {
            userRegister.delete(flush: true)

            if (!userRegister.hasErrors()) {
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

    def viewLogin() {
        render(view: "/login/login")
    }

    def viewTable() {
        render(view: "/userRegistration/table")
    }

    /* The below getAll2() function is to get the data from the designationDemo project, which is running on another port 8086.
     this function is used to implement microservices. Here the designation name is being added to the userList by referring the id from the
     designationList table which is in designationDemo database
     */

    def getAll2() {

        try {

            //get designation data-----------
            def apiResponse = getAllDesignation()
            JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
            def designationResults = responseObject?.results

            //get user data ---------------------
            def results = UserRegistration.findAll()

            //convert data to json arraylist
            List<JSONObject> userList = new ArrayList<JSONObject>();
            for (def JSONObject : results) {
                userList.add(
                        id: JSONObject.id,
                        userName: JSONObject.name,
                        designation: JSONObject.designation,
                )
            }

            //compare user designation to get designationName --------------------
            for (def data : userList) {
                // for loop which is looping through the userList json array.

                def designationId = data.designation

                for (def data2 : designationResults) {
                    //for loop which is looping through the designationResult json array.

                    if (Integer.parseInt(designationId.toString()) == data2.id) {

                        data.put("designationName", data2.designation)
                    }
                }

            }

            def responseData = [
                    'results': userList
            ]
            render responseData as JSON
        }
        catch (Exception ex) {

        }
    }


    def getAllDesignation() {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8086");

        try {
            Response apiResponse = target
                    .path("/designationdemo")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :registrationService , action :  getAllDesignation  , Ex:' + ex)
            log.error('Service :registrationService , action :  getAllDesignation  , Ex:' + ex)
        }


    }

    def saveDesignation() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
        def apiResponse = saveAllDesignationService(jsonObject)
        JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
        def designationResults = responseObject?.results
        def responseData = [
                'results': designationResults
        ]
        render responseData as JSON
    }

    def saveAllDesignationService(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8086");

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path("/designationdemo")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :registrationService , action :  saveAllDesignation  , Ex:' + ex)
            log.error('Service :registrationService , action :  saveAllDesignation  , Ex:' + ex)
        }

    }

//    def deleteDesignation() {
//        JSONObject jsonObject = new JSONObject(params)
//        def apiResponse = deleteAllDesignationService(jsonObject)
//        JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
//        def designationResults = responseObject?.results
//        def responseData = [
//                'results': designationResults
//        ]
//        render responseData as JSON
//    }
//
//
//    def deleteAllDesignationService(JSONObject jsonObject) {
//        Client client = ClientBuilder.newClient();
//
//        WebTarget target = client.target("http://localhost:8086");
//
//        try {
//            println(jsonObject)
//            Response apiResponse = target
//                    .path("/designationdemo")
//                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .method("DELETE", Entity.json(jsonObject.toString()));
//            println(apiResponse)
//            return apiResponse
//        }
//        catch (Exception ex) {
//            System.err.println('Service :registrationService , action :  deleteAllDesignation  , Ex:' + ex)
//            log.error('Service :registrationService , action :  deleteAllDesignation  , Ex:' + ex)
//        }
//
//    }

    def updateDesignation() {
        JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
        def apiResponse = updateAllDesignationService(jsonObject)
        JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
        def designationResults = responseObject?.results
        def responseData = [
                'results': designationResults
        ]
        render responseData as JSON
    }

    def updateAllDesignationService(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8086");

        try {
            Response apiResponse = target
                    .path("/designationdemo")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :registrationService , action :  deleteAllDesignationService  , Ex:' + ex)
            log.error('Service :registrationService , action :  updateAllDesignationService  , Ex:' + ex)
        }

    }
}
