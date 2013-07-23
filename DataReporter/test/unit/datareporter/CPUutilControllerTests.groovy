package datareporter



import org.junit.*
import grails.test.mixin.*

@TestFor(CPUutilController)
@Mock(CPUutil)
class CPUutilControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/CPUutil/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.CPUutilInstanceList.size() == 0
        assert model.CPUutilInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.CPUutilInstance != null
    }

    void testSave() {
        controller.save()

        assert model.CPUutilInstance != null
        assert view == '/CPUutil/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/CPUutil/show/1'
        assert controller.flash.message != null
        assert CPUutil.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/CPUutil/list'

        populateValidParams(params)
        def CPUutil = new CPUutil(params)

        assert CPUutil.save() != null

        params.id = CPUutil.id

        def model = controller.show()

        assert model.CPUutilInstance == CPUutil
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/CPUutil/list'

        populateValidParams(params)
        def CPUutil = new CPUutil(params)

        assert CPUutil.save() != null

        params.id = CPUutil.id

        def model = controller.edit()

        assert model.CPUutilInstance == CPUutil
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/CPUutil/list'

        response.reset()

        populateValidParams(params)
        def CPUutil = new CPUutil(params)

        assert CPUutil.save() != null

        // test invalid parameters in update
        params.id = CPUutil.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/CPUutil/edit"
        assert model.CPUutilInstance != null

        CPUutil.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/CPUutil/show/$CPUutil.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        CPUutil.clearErrors()

        populateValidParams(params)
        params.id = CPUutil.id
        params.version = -1
        controller.update()

        assert view == "/CPUutil/edit"
        assert model.CPUutilInstance != null
        assert model.CPUutilInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/CPUutil/list'

        response.reset()

        populateValidParams(params)
        def CPUutil = new CPUutil(params)

        assert CPUutil.save() != null
        assert CPUutil.count() == 1

        params.id = CPUutil.id

        controller.delete()

        assert CPUutil.count() == 0
        assert CPUutil.get(CPUutil.id) == null
        assert response.redirectedUrl == '/CPUutil/list'
    }
}
