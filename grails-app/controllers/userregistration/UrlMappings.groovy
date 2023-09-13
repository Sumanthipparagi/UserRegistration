package userregistration

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/login/login")
        "/signup"(view: "/information/sign")

        "500"(view: '/error')
        "404"(view: '/notFound')


        "/userregistration"(controller: "userRegistration") {
            action = ["GET": "getAll", "POST": "save", "PUT": "update", "DELETE": "delete"]
        }

        "/userregistration/getbyid"(controller: "userRegistration") {
            action = ["GET": "getById"]
        }

        "/userregistration/view(.$format)"(controller: 'userRegistration'){
            action = [GET: 'index']
        }

        "/userregistration/viewtable(.$format)"(controller: 'userRegistration'){
            action = [GET: 'viewTable']
        }

        "/designationmaster"(controller: "designationMaster") {
            action = ["GET": "getAll", "POST": "save", "PUT": "update", "DELETE": "delete"]
        }

        "/designationmaster/view(.$format)"(controller: 'designationMaster') {
            action = [GET: 'index']
        }

        "/bookdetails"(controller: "bookDetails") {
            action = ["GET": "getAll", "POST": "save", "PUT": "update", "DELETE": "delete"]
        }

        "/bookdetails/getbyid"(controller: "bookDetails") {
            action = ["GET": "getById"]
        }

        "/bookdetails/view(.$format)"(controller: 'bookDetails') {
            action = [GET: 'index']
        }

        "/genremaster"(controller: "genreMaster") {
            action = ["GET": "getAll", "POST": "save", "PUT": "update", "DELETE": "delete"]
        }

        "/userregistration/login(.$format)"(controller: 'auth'){
            action = [GET: 'login']
        }

        "/login(.$format)"(controller: 'userRegistration'){
            action = [GET: 'viewLogin']
        }

        "/userregistration/getAll2(.$format)"(controller: 'userRegistration') {
            action = [ GET: 'getAll2']
        }

        "/userregistration/saveDesignation(.$format)"(controller: 'userRegistration') {
            action = [ POST: 'saveDesignation']
        }

        "/userregistration/deleteDesignation(.$format)"(controller: 'userRegistration') {
            action = [DELETE: 'deleteDesignation']
        }

        "/userregistration/updateDesignation(.$format)"(controller: 'userRegistration') {
            action = [PUT: 'updateDesignation']
        }
    }
}

