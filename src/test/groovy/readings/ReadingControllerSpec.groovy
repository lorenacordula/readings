package readings

import grails.test.mixin.*
import spock.lang.*

@TestFor(ReadingController)
@Mock(Reading)
class ReadingControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.readingList
            model.readingCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.reading!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def reading = new Reading()
            reading.validate()
            controller.save(reading)

        then:"The create view is rendered again with the correct model"
            model.reading!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            reading = new Reading(params)

            controller.save(reading)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/reading/show/1'
            controller.flash.message != null
            Reading.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def reading = new Reading(params)
            controller.show(reading)

        then:"A model is populated containing the domain instance"
            model.reading == reading
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def reading = new Reading(params)
            controller.edit(reading)

        then:"A model is populated containing the domain instance"
            model.reading == reading
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/reading/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def reading = new Reading()
            reading.validate()
            controller.update(reading)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.reading == reading

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            reading = new Reading(params).save(flush: true)
            controller.update(reading)

        then:"A redirect is issued to the show action"
            reading != null
            response.redirectedUrl == "/reading/show/$reading.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/reading/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def reading = new Reading(params).save(flush: true)

        then:"It exists"
            Reading.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(reading)

        then:"The instance is deleted"
            Reading.count() == 0
            response.redirectedUrl == '/reading/index'
            flash.message != null
    }
}
