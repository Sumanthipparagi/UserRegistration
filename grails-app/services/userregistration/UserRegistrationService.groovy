package userregistration

import UserRegistration.UserRegistration
import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import org.grails.web.json.JSONObject

import java.sql.SQLException

@Transactional
class UserRegistrationService {

    def dataSource

    def save(JSONObject jsonObject) {
        UserRegistration userRegistration = new UserRegistration()
        userRegistration.name = jsonObject.get("name").toString()
        userRegistration.loginid = jsonObject.get("loginId").toString()
        userRegistration.password = jsonObject.get("hashedPassword").toString()
        userRegistration.address = jsonObject.get("address").toString()
        userRegistration.mobileno = jsonObject.get("mobileNo").toString()
        userRegistration.designation = Long.parseLong(jsonObject.get("designation").toString())

        userRegistration.save(flush: true)
        if (!userRegistration.hasErrors())
            return userRegistration
        else
            throw new Exception()
    }

    def update(JSONObject jsonObject, long id) {
        UserRegistration userRegistration = UserRegistration.findById(id)
        if (userRegistration) {

            userRegistration.name = jsonObject.get("name").toString()
            userRegistration.loginid = jsonObject.get("loginId").toString()
            userRegistration.password = jsonObject.get("password").toString()
            userRegistration.address = jsonObject.get("address").toString()
            userRegistration.mobileno = jsonObject.get("mobileNo").toString()
            userRegistration.designation = Long.parseLong(jsonObject.get("designation").toString())

            userRegistration.save(flush: true)
            if (!userRegistration.hasErrors())
                return userRegistration
            else
                throw new Exception()
        }
    }

    def getAll(){
        try {
            final Sql sql = new Sql(dataSource)

            String query = '''\
         select ur.id, ur.name, ur.loginid, ur.address , ur.mobileno ,ur.designation as 'designationId' ,dm.designation as 'designationName' from user_registration 
         ur
 left    join designation_master dm on dm.id = ur.designation;

            ''';

            def results = sql.rows(query)
            return results
        }
        catch (SQLException ex)
        {

        }

    }

}



