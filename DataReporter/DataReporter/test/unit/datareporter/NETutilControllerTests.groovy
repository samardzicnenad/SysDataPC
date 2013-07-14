package datareporter



import org.junit.*
import grails.test.mixin.*

@TestFor(NETutilController)
@Mock(NETutil)
class NETutilControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/NETutil/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.NETutilInstanceList.size() == 0
        assert model.NETutilInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.NETutilInstance != null
    }

    void testSave() {
        controller.save()

        assert model.NETutilInstance != null
        assert view == '/NETutil/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/NETutil/show/1'
        assert controller.flash.message != null
        assert NETutil.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/NETutil/list'

        populateValidParams(params)
        def NETutil = new NETutil(params)

        assert NETutil.save() != null

        params.id = NETutil.id

        def model = controller.show()

        assert model.NETutilInstance == NETutil
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/NETutil/list'

        populateValidParams(params)
        def NETutil = new NETutil(params)

        assert NETutil.save() != null

        params.id = NETutil.id

        def model = controller.edit()

        assert model.NETutilInstance == NETutil
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/NETutil/list'

        response.reset()

        populateValidParams(params)
        def NETutil = new NETutil(params)

        assert NETutil.save() != null

        // test invalid parameters in update
        params.id = NETutil.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/NETutil/edit"
        assert model.NETutilInstance != null

        NETutil.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/NETutil/show/$NETutil.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        NETutil.clearErrors()

        populateValidParams(params)
        params.id = NETutil.id
        params.version = -1
        controller.update()

        assert view == "/NETutil/edit"
        assert model.NETutilInstance != null
        assert model.NETutilInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/NETutil/list'

        response.reset()

        populateValidParams(params)
        def NETutil = new NETutil(params)

        assert NETutil.save() != null
        assert NETutil.count() == 1

        params.id = NETutil.id

        controller.delete()

        assert NETutil.count() == 0
        assert NETutil.get(NETutil.id) == null
        assert response.redirectedUrl == '/NETutil/list'
    }
}
