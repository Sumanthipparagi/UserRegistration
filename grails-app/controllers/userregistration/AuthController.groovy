
package userregistration

import UserRegistration.UserRegistration
import grails.converters.JSON
import org.grails.web.json.JSONObject
import org.mindrot.jbcrypt.BCrypt

class AuthController {

    def index() { }

    def login() {
        try {
            JSONObject jsonObject = new JSONObject(params)

            String loginId = jsonObject.get("loginId").toString()
            String passwordEntered = jsonObject.get("password").toString()

            def loginResult = UserRegistration.findByLoginid(loginId)
            def passwordFromDB = loginResult.password

            if (BCrypt.checkpw(passwordEntered, passwordFromDB)) {
                println("Password matched!")
                session.setAttribute("login", true)
                def responseData = [
                        status: 'success'
                ]
                render responseData as JSON
            } else {
                println("Password did not match.")
                def responseData = [
                        status: "fail"
                ]
                render responseData as JSON
            }
        } catch (Exception ex) {

        }

    }
}
