package userregistration

import UserRegistration.BookDetails
import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import org.grails.web.json.JSONObject
import java.sql.SQLException


@Transactional
class BookDetailsService {

    def dataSource

    def save(JSONObject jsonObject) {


        BookDetails bookDetails = new BookDetails()
        // here BookDetails refers to domain name and bookDetails is an variable

        bookDetails.bookName = jsonObject.get("bookName").toString()
        // bookName, authorName, genre, bookPrice these all are variables of Strings in domain
        bookDetails.authorName = jsonObject.get("authorName").toString()
        bookDetails.genre = Long.parseLong(jsonObject.get("genre").toString())
        bookDetails.bookPrice = Double.parseDouble(jsonObject.get("bookPrice").toString())

        bookDetails.save(flush: true)

        if (!bookDetails.hasErrors())
            return bookDetails
        else
            throw new Exception()
    }

    def update(JSONObject jsonObject, long id) {
        BookDetails bookDetails = BookDetails.findById(id)
        if (bookDetails) {

            bookDetails.bookName = jsonObject.get("bookName").toString()
            bookDetails.authorName = jsonObject.get("authorName").toString()
            bookDetails.genre = Long.parseLong(jsonObject.get("genre").toString())
            bookDetails.bookPrice = Double.parseDouble(jsonObject.get("bookPrice").toString())

            bookDetails.save(flush: true)
            if (!bookDetails.hasErrors())
                return bookDetails
            else
                throw new Exception()
        }
    }

    def getAll(){
        try {
            final Sql sql = new Sql(dataSource)


//         select ur.id, ur.bookPrice, ur.bookName, ur.authorName, ur.genre as 'genreId' ,dm.genre as 'genreName' from book_details
//         ur
//        left    join genre_master dm on dm.id = ur.genre;
//
//            ''';
            String query = '''\

            
            select ur.id, ur.book_price as 'bookPrice', ur.book_name 'bookName', ur.author_name 'authorName', ur.genre AS 'genreId' , dm.genre AS 'genreName'
            from book_details as ur
            LEFT JOIN genre_master AS dm ON dm.id = ur.genre;
            
            ''';

            def results = sql.rows(query)
            return results
        }
        catch (SQLException ex)
        {

        }

    }

}