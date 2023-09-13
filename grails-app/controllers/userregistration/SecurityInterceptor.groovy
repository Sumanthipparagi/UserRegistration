package userregistration


class SecurityInterceptor {


    SecurityInterceptor(){
        matchAll()
    }

    boolean before() {

//        boolean login = session.getAttribute("login")
//        if(controllerName == null || controllerName== "signup"){
//            true
//        }
//        else if(controllerName == "auth") {
//            true
//        }else{
//
//            if(login){
//                true
//            }else{
//                false
//                redirect(uri: '/')
//            }
//
//        }
true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}




