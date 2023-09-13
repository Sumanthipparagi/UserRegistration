package userregistration
import UserRegistration.GenreMaster
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

@Transactional
class GenreMasterService {

    def save(JSONObject jsonObject) {
        GenreMaster genreMaster = new GenreMaster()
        genreMaster.genre = jsonObject.get("genre").toString()
        genreMaster.save(flush: true)
        if (!genreMaster.hasErrors())
            return genreMaster
        else
            throw new Exception()
    }

    def update(JSONObject jsonObject, long id) {
        GenreMaster genreMaster = GenreMaster.findById(id)
        if (genreMaster) {

            genreMaster.genre = jsonObject.get("genre").toString()
            genreMaster.save(flush: true)
            if (!genreMaster.hasErrors())
                return genreMaster
            else
                throw new Exception()
        }
    }
}