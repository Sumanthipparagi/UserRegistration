package userregistration

import UserRegistration.DesignationMaster
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

@Transactional
class DesignationMasterService {

    def save(JSONObject jsonObject) {
        DesignationMaster designationMaster = new DesignationMaster()
        designationMaster.designation = jsonObject.get("designation").toString()
        designationMaster.save(flush: true)
        if (!designationMaster.hasErrors())
            return designationMaster
        else
            throw new Exception()
    }

    def update(JSONObject jsonObject, long id) {
        DesignationMaster designationMaster = DesignationMaster.findById(id)
        if (designationMaster) {

            designationMaster.designation = jsonObject.get("designation").toString()
            designationMaster.save(flush: true)
            if (!designationMaster.hasErrors())
                return designationMaster
            else
                throw new Exception()
        }
    }

    }
