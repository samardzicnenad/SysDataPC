package datareporter

import org.springframework.dao.DataIntegrityViolationException

class CPUutilController {

    static allowedMethods = []

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [CPUutilInstanceList: CPUutil.list(params), CPUutilInstanceTotal: CPUutil.count()]
    }

    def show(Long id) {
        def CPUutilInstance = CPUutil.get(id)
        if (!CPUutilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'CPUutil.label', default: 'CPUutil'), id])
            redirect(action: "list")
            return
        }

        [CPUutilInstance: CPUutilInstance]
    }
}
